package Interface;

import Entities.Classes.*;
import Entities.Enum.Direction;
import Entities.Enum.Images;
import Entities.Enum.Panels;
import Entities.Enum.PowerUpTypes;

import javax.swing.*;
import java.awt.*;

/**
 * Window Class.
 *
 * @author Nicolas Viseur
 * @version 1.0
 */
public class Window extends JFrame{

    private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public static final Dimension windowSize = new Dimension();
    PlayGround pg;

    public Window()
    {
        this.setTitle("Bomberman V1.0 By Nicolas Viseur");
        windowSize.setSize((int)(screenSize.getHeight()-screenSize.getHeight()/10),(int)(screenSize.getHeight()-screenSize.getHeight()/10));
        pg = new PlayGround(0);
        this.setSize(windowSize);
        this.isAlwaysOnTop();
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.getContentPane().add(new Menu(this));
        this.setVisible(true);
    }

    public void changePanel(Panels panel)
    {
        this.getContentPane().removeAll();

        switch (panel)
        {
            case game:
                this.getContentPane().add(pg);
                this.revalidate();
                this.repaint();
                pg.play();
                break;

            case menu:
                this.setContentPane(new Menu(this));
                break;

            case scoreboard:

                break;

            default:

                break;
        }
    }

    public void play()
    {
        int i =0;
        while(i <= 1000)
        {
            i++;
            pg.repaint();

            try {
                Thread.sleep(1000/30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
