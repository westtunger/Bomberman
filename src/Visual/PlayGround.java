package Visual;

import Entities.Classes.*;
import Entities.EntityManager;
import KeyMapping.Key;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import static Entities.Enum.Direction.*;

/**
 * Playground Class.
 *
 * @author Nicolas Viseur
 * @version 1.0
 */
class PlayGround extends JPanel implements ActionListener{
    private final Timer t = new Timer(50,this);
    private final Window window;
    private final EntityManager entityManager;

    public PlayGround(Window window)
    {
        entityManager = new EntityManager();
        Entity.setEntityManager(entityManager);
        this.setBackground(Color.lightGray);
        this.window = window;
        this.createLevel();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        //Copy the collection to avoid possible java.util.ConcurrentModificationException
        ArrayList<Entity> entities = entityManager.getEntities();

        for (Entity entity : entities)
        {
            if(entity instanceof Player || entity instanceof Bomb || entity instanceof Explosion)
                g.drawImage(entity.getImage(),entity.getPosition().x,entity.getPosition().y,Window.getWindowSize().width/20,Window.getWindowSize().width/20,null);
            else
                g.drawImage(entity.getImage(),entity.getPosition().x,entity.getPosition().y,Window.getWindowSize().width/15,Window.getWindowSize().width/15,null);

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
                        new Player("p1",pos);
                        break;

                    case 'C':
                        new Player("p2",pos);
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

        processTick();

        processCollision();

        repaint();
    }

    private void processInput()
    {
        for(int code : window.getInputListener().getKeys())
        {
            switch (Key.fromCode(code))
            {
                case p1Down:
                    entityManager.getPlayers().get(0).move(down);
                    break;

                case p1Up:
                    entityManager.getPlayers().get(0).move(up);
                    break;

                case p1Left:
                    entityManager.getPlayers().get(0).move(left);
                    break;

                case p1Right:
                    entityManager.getPlayers().get(0).move(right);
                    break;

                case p1PoseBomb:
                    entityManager.getPlayers().get(0).plant();
                    break;

                case p2Down:
                    entityManager.getPlayers().get(1).move(down);
                    break;

                case p2Up:
                    entityManager.getPlayers().get(1).move(up);
                    break;

                case p2Left:
                    entityManager.getPlayers().get(1).move(left);
                    break;

                case p2Right:
                    entityManager.getPlayers().get(1).move(right);
                    break;

                case p2PoseBomb:
                    entityManager.getPlayers().get(1).plant();
                    break;

                default:

            }
        }
    }

    private void processTick()
    {
        for(Entity entity : entityManager.getEntities())
        {
            entity.tick();
        }
    }

    private void processCollision()
    {
        processPlayerCollision();

        processExplosionCollision();
    }

    private void processPlayerCollision()
    {
        for(Player player : entityManager.getPlayers())
        {
            Entity entity = player.checkCollision();

            if(entity != null)
            {
                if(entity instanceof Wall ||
                        entity instanceof Player ||
                        (entity instanceof Bomb  && (!player.getBombs().contains(entity) ||
                                player.getBombs().contains(entity) && !player.isWalkingOnBombs())))
                {
                    player.cancelMove();
                }
                else if(entity instanceof PowerUp)
                {
                    player.pickUpPowerUp((PowerUp) entity);

                    entity.destroy();
                }
            }
        }
    }

    private void processExplosionCollision()
    {
        for(Explosion explosion : entityManager.getExplosions())
        {
            Entity entity = explosion.checkCollision();

            if(entity != null)
            {
                explosion.destroy();

                if(((entity instanceof Wall && ((Wall) entity).isBreakable()) || (entity instanceof PowerUp && !((PowerUp)entity).isInvincible())))
                {
                    entity.destroy();
                }
                else if(entity instanceof Bomb)
                {
                    ((Bomb) entity).explode();
                }
                else if(entity instanceof Player)
                {
                    int nb = ((Player)entity).getPlayerNumber() == 2 ? 1 : 2;
                    entity.destroy();
                    Player.resetNumbers();
                    t.stop();
                    JOptionPane.showMessageDialog(this,"Victoire du joueur "+nb+" !");
                    window.changePanel(this);
                }
            }
        }
    }
}