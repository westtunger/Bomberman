package Interface;

import Entities.Classes.Entity;
import Entities.Classes.Player;
import Entities.Classes.Wall;
import Entities.Enum.Direction;
import KeyMapping.Key;
import org.w3c.dom.css.RGBColor;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.Timer;
import javax.xml.stream.util.StreamReaderDelegate;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Playground Class.
 *
 * @author Nicolas Viseur
 * @version 1.0
 */
public class PlayGround extends JPanel implements ActionListener, KeyListener{
    Player playerOne;
    Player playerTwo;
    int i = 0;
    ArrayList<Entity> entities = null;
    Timer t = new Timer(100,this);

    public PlayGround(int level)
    {
        this.createLevel(level);
        this.setBackground(Color.lightGray);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        //Copy the collection to avoid possible java.util.ConcurrentModificationException
        entities = new ArrayList<>(Entity.getEntities());

        for (Entity entity : entities)
        {
            BufferedImage img = null;

            try {
                img = ImageIO.read(new File(entity.getImageEnum().getImages()[entity.getImageIndex()]));
            } catch (IOException e) {
                e.printStackTrace();
            }

            g.drawImage(img,entity.getPosition().x,entity.getPosition().y,Window.getWindowSize().width/15,Window.getWindowSize().width/15,null);
            entity.changeImageIndex();
        }
    }

    public void createLevel(int id)
    {
        String[] array = new String[]{};
        int mult = Window.getWindowSize().width/15;

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
                        playerOne = new Player(0,"p1",pos);
                        break;

                    case 'C':
                        playerTwo = new Player(1,"p2",pos);
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
        i++;

        this.repaint();

        for(int j = 0;j<Entity.getEntities().size();j++)
        {
            Entity.getEntities().get(j).tick();
        }

        if(i>=1000)
            t.stop();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode())
        {
            case KeyEvent.VK_S:
                playerOne.move(Direction.down);
                break;

            case KeyEvent.VK_Z:
                playerOne.move(Direction.up);
                break;

            case KeyEvent.VK_Q:
                playerOne.move(Direction.left);
                break;

            case KeyEvent.VK_D:
                playerOne.move(Direction.right);
                break;

            case KeyEvent.VK_SPACE:
                playerOne.plant();
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}