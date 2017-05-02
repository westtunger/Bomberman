package Interface;

import Entities.Classes.*;
import KeyMapping.Key;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import static Entities.Enum.Direction.*;
import static Entities.Enum.Panels.menu;

/**
 * Playground Class.
 *
 * @author Nicolas Viseur
 * @version 1.0
 */
class PlayGround extends JPanel implements ActionListener{
    private final Player[] players = new Player[2];
    private final ArrayList<Integer> keys= new ArrayList<>();
    private final Timer t = new Timer(50,this);
    private final Window window;

    public PlayGround(Window window)
    {
        this.createLevel();
        this.setBackground(Color.lightGray);
        this.window = window;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        //Copy the collection to avoid possible java.util.ConcurrentModificationException
        ArrayList<Entity> entities = new ArrayList<>(Entity.getEntities());

        for (Entity entity : entities)
        {
            BufferedImage img = null;

            try {
                img = ImageIO.read(new File(entity.getImageEnum().getImages()[entity.getImageIndex()]));
            } catch (IOException e) {
                e.printStackTrace();
            }

            if(entity instanceof Bomb || entity instanceof  Explosion || entity instanceof Player)
            {
                g.drawImage(img,entity.getPosition().x,entity.getPosition().y,Window.getWindowSize().width/20,Window.getWindowSize().width/20,null);
            }
            else
                g.drawImage(img,entity.getPosition().x,entity.getPosition().y,Window.getWindowSize().width/15,Window.getWindowSize().width/15,null);

            entity.changeImageIndex();
        }
    }

    /**
     * Place all the entity on the map according the pattern in the levelX.txt file.
     */
    private void createLevel()
    {
        String[] array = new String[]{};
        int mult = Window.getWindowSize().width/15;
        @SuppressWarnings("ConstantConditions") int nbOfLevels = new File("Levels").list().length;
        int id = (int)(Math.random()*nbOfLevels-0.1);

        try {
            java.util.List<String> list = Files.readAllLines(Paths.get("Levels/level"+id+".txt"));
            array = list.toArray(new String[]{});
        } catch (IOException e) {
            e.printStackTrace();
        }

        for(int i = 0; i < 15;i++)
        {
            for(int j = 0;j<15;j++)
            {
                char chr = array[i].charAt(j);
                Point pos = new Point(j*mult,i*mult);

                switch (chr)
                {
                    case 'X':
                        new Wall(false,pos);
                        break;

                    case 'W':
                        new Wall(true,pos);
                        break;

                    case 'P':
                        players[0] = new Player(0,"p1",pos);
                        break;

                    case 'C':
                        players[1] = new Player(1,"p2",pos);
                        break;
                }
            }
            System.out.println();
        }
    }

    /**
     * Start the timer to launch the game loop.
     * @see Timer
     */
    public void play()
    {
        t.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        processInput();

        processCollision();

        repaint();
    }

    private void processInput()
    {
        for(int code : keys)
        {
            switch (Key.fromCode(code))
            {
                case p1Down:
                    players[0].move(down);
                    break;

                case p1Up:
                    players[0].move(up);
                    break;

                case p1Left:
                    players[0].move(left);
                    break;

                case p1Right:
                    players[0].move(right);
                    break;

                case p1PoseBomb:
                    players[0].plant();
                    break;

                case p2Down:
                    players[1].move(down);
                    break;

                case p2Up:
                    players[1].move(up);
                    break;

                case p2Left:
                    players[1].move(left);
                    break;

                case p2Right:
                    players[1].move(right);
                    break;

                case p2PoseBomb:
                    players[1].plant();
                    break;

                default:
                    
            }
        }
    }

    private void processCollision()
    {
        int sizea = Window.getWindowSize().width/15;
        int sizeb = Window.getWindowSize().width/20;

        for(int j = 0;j<Entity.getEntities().size();j++)
        {
            Entity entity = Entity.getEntities().get(j);

            entity.tick();

            for(int l = 0;l<=1;l++)
            {
                if(players[l].checkCollision(entity))
                {
                    if(entity != players[l] && !(entity instanceof Explosion)
                            && !(entity instanceof PowerUp)
                            && !(entity instanceof Bomb && ((Bomb)entity).getOwner().equals(players[l]) && players[l].isCanWalkOnBomb()))
                    {
                        switch (players[l].getDir())
                        {
                            case up:
                                players[l].setPosition(players[l].getPosition().x,entity.getPosition().y+sizea);
                                break;

                            case right:
                                players[l].setPosition(entity.getPosition().x-sizeb,players[l].getPosition().y);
                                break;

                            case down:
                                players[l].setPosition(players[l].getPosition().x,entity.getPosition().y-sizeb);
                                break;

                            case left:
                                players[l].setPosition(entity.getPosition().x+sizea,players[l].getPosition().y);
                                break;
                        }
                    }
                    else if(entity instanceof Explosion)
                    {
                        int nb = l == 0 ? 2 : 1;
                        players[l].destroy();
                        t.stop();
                        JOptionPane.showMessageDialog(this,"Victoire du joueur "+nb+" !");
                        Entity.clear();
                        window.changePanel(menu);
                    }
                    else if(entity instanceof PowerUp)
                    {
                        switch (((PowerUp) entity).getType())
                        {
                            case bomb:
                                players[l].augmentNbBombMax();
                                break;

                            case power:
                                players[l].augmentPower();
                                break;

                            case speed:
                                players[l].augmentSpeed();
                                break;
                        }

                        entity.destroy();
                    }
                }
            }

            if(entity instanceof  Explosion)
                for(int l = 0;l<Entity.getEntities().size();l++)
                {
                    Entity ent = Entity.getEntities().get(l);
                    if(entity.checkCollision(ent) && ((ent instanceof Wall && ((Wall) ent).isBreakable()) || (ent instanceof PowerUp && !((PowerUp)ent).isInvinsible())))
                    {
                        entity.destroy();
                        ent.destroy();
                    }
                    else if(entity.checkCollision(ent) && ent instanceof Bomb)
                    {
                        entity.destroy();
                        ((Bomb) ent).explode();
                    }
                }
        }
    }

    public void addKey(Integer key)
    {
        keys.add(key);
    }

    public boolean getKey(Integer key)
    {
        return keys.contains(key);
    }

    public void removeKey(Integer key)
    {
        //Use equals instead of == since we are comparing two Object of class Integer and not two int
        keys.removeIf(i -> i.equals(key));
    }
}