package Interface;

import Entities.Classes.*;
import Entities.Enum.Direction;
import Entities.Enum.Panels;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * Playground Class.
 *
 * @author Nicolas Viseur
 * @version 1.0
 */
class PlayGround extends JPanel implements ActionListener, KeyListener{
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
                if(!(entity instanceof Player))
                    g.drawImage(img,entity.getPosition().x,entity.getPosition().y,Window.getWindowSize().width/20,Window.getWindowSize().width/20,null);
                else
                {
                    if(((Player) entity).getPlayerNumber() == 0)
                    {
                        g.drawImage(img,entity.getPosition().x,entity.getPosition().y,Window.getWindowSize().width/20,Window.getWindowSize().width/20,null);
                    }
                    else
                    {
                        g.drawImage(img,entity.getPosition().x,entity.getPosition().y,Window.getWindowSize().width/15,Window.getWindowSize().width/15,null);

                    }
                }
            }
            else
                g.drawImage(img,entity.getPosition().x,entity.getPosition().y,Window.getWindowSize().width/15,Window.getWindowSize().width/15,null);
            entity.changeImageIndex();
        }
    }

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

    public void play()
    {
        t.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int sizea = Window.getWindowSize().width/15;
        int sizeb = Window.getWindowSize().width/20;

        this.repaint();

        for(int j = 0;j<Entity.getEntities().size();j++)
        {
            Entity entity = Entity.getEntities().get(j);

            entity.tick();

            for(int l = 0;l<=1;l++)
            {
                if(players[l].checkCollision(entity) && entity != players[l] && !(entity instanceof Explosion)
                        && !(entity instanceof PowerUp))
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
                else if(players[l].checkCollision(entity) && entity instanceof Explosion)
                {
                    int nb = l == 0 ? 2 : 1;
                    players[l].destroy();
                    t.stop();
                    JOptionPane.showMessageDialog(this,"Victoire du joueur "+nb+" !");
                    Entity.clear();
                    window.changePanel(Panels.menu);
                }
                else if(players[l].checkCollision(entity) && entity instanceof PowerUp)
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

            if(entity instanceof  Explosion)
                for(int l = 0;l<Entity.getEntities().size();l++)
                {
                    Entity ent = Entity.getEntities().get(l);
                    if(entity.checkCollision(ent) && ent instanceof Wall && ((Wall) ent).isBreakable())
                    {
                        ent.destroy();
                    }
                    else if(entity.checkCollision(ent) && ent instanceof Bomb)
                    {
                        ((Bomb) ent).explode();
                    }
                }
        }

        for(int code : keys)
        {
            switch (code)
            {
                case KeyEvent.VK_S:
                    players[0].move(Direction.down);
                    break;

                case KeyEvent.VK_Z:
                    players[0].move(Direction.up);
                    break;

                case KeyEvent.VK_Q:
                    players[0].move(Direction.left);
                    break;

                case KeyEvent.VK_D:
                    players[0].move(Direction.right);
                    break;

                case KeyEvent.VK_SPACE:
                    players[0].plant();
                    break;

                case KeyEvent.VK_DOWN:
                    players[1].move(Direction.down);
                    break;

                case KeyEvent.VK_UP:
                    players[1].move(Direction.up);
                    break;

                case KeyEvent.VK_LEFT:
                    players[1].move(Direction.left);
                    break;

                case KeyEvent.VK_RIGHT:
                    players[1].move(Direction.right);
                    break;

                case KeyEvent.VK_NUMPAD0:
                    players[1].plant();
                    break;
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_Z || e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_Q || e.getKeyCode() == KeyEvent.VK_D)
        {
            if(keys.contains(KeyEvent.VK_Z))
                keys.removeIf(i -> i == KeyEvent.VK_Z);

            if(keys.contains(KeyEvent.VK_S))
                keys.removeIf(i -> i == KeyEvent.VK_S);

            if(keys.contains(KeyEvent.VK_Q))
                keys.removeIf(i -> i == KeyEvent.VK_Q);

            if(keys.contains(KeyEvent.VK_D))
                keys.removeIf(i -> i == KeyEvent.VK_D);

            keys.add(e.getKeyCode());
        }
        else if(e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT)
        {
            if(keys.contains(KeyEvent.VK_UP))
                keys.removeIf(i -> i == KeyEvent.VK_UP);

            if(keys.contains(KeyEvent.VK_DOWN))
                keys.removeIf(i -> i == KeyEvent.VK_DOWN);

            if(keys.contains(KeyEvent.VK_LEFT))
                keys.removeIf(i -> i == KeyEvent.VK_LEFT);

            if(keys.contains(KeyEvent.VK_RIGHT))
                keys.removeIf(i -> i == KeyEvent.VK_RIGHT);

            keys.add(e.getKeyCode());
        }
        else if(!keys.contains(e.getKeyCode()))
            keys.add(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys.removeIf(i -> i == e.getKeyCode());
    }
}