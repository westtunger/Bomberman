package Visual;

import Entities.Classes.*;
import Entities.EntityManager;
import Entities.Interfaces.Layers;
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
 * @version 1.2
 */
class PlayGround extends JPanel implements ActionListener{
    private final Timer t = new Timer(50,this);
    private final Window window;
    private final EntityManager entityManager;

    public PlayGround(Window window)
    {
        entityManager = EntityManager.getManager();
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
                        EntityManager.getManager().addEntity(new Wall(false,pos));
                        break;

                    case 'W':
                        EntityManager.getManager().addEntity(new Wall(true,pos));
                        break;

                    case 'P':
                        EntityManager.getManager().addEntity(new Player("p1",pos));
                        break;

                    case 'C':
                        EntityManager.getManager().addEntity(new Player("p2",pos));
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

    /**
     * Process every inputs contained in the inputs list from the inputListener.
     */
    private void processInput()
    {
        for(int code : window.getInputListener().getInputs())
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

    /**
     * Loop trough each entity and call their tick method.
     */
    private void processTick()
    {
        for(Entity entity : entityManager.getEntities())
        {
            entity.tick();
        }
    }

    /**
     * Process the collision for all the entities.
     */
    private void processCollision()
    {
        processPlayerCollision();

        processBombCollision();

        processExplosionCollision();
    }

    /**
     * Process the collision for the two players.
     */
    private void processPlayerCollision()
    {
        for(Player player : entityManager.getPlayers())
        {
            Entity entity = player.checkCollision();

            if(entity != null)
            {
                if(entity.getLayer().equals(Layers.collidable))
                {
                    player.cancelMove();
                }
                else if(entity.getLayer().equals(Layers.pickable))
                {
                    player.pickUpPowerUp((PowerUp) entity);
                }
            }
        }
    }

    /**
     * Process the collisions for all the explosions.
     */
    private void processExplosionCollision()
    {
        for(Explosion explosion : entityManager.getExplosions())
        {
            Entity entity = explosion.checkCollision();

            if(entity != null)
            {
                explosion.destroy();

                String entityClass = entity.getClass().getSimpleName();

                entity.onExplode();

                if(entityClass.equals("Player"))
                {
                    this.endGame(((Player) entity).getOtherPlayerNumber());
                }
            }
        }
    }

    /**
     * Process the collisions for all the bombs
     */
    private void processBombCollision()
    {
        for(Bomb bomb : EntityManager.getManager().getBombs())
        {
            bomb.processCollision();
        }
    }

    /**
     * Stop the loop, reset the entityManager and show the winner.
     * @param winner
     */
    private void endGame(int winner)
    {
        Player.resetNumbers();
        t.stop();
        EntityManager.getManager().clear();
        JOptionPane.showMessageDialog(this,"Victoire du joueur "+winner+" !");
        window.changePanel(this);
    }
}