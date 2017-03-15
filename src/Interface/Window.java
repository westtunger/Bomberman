package Interface;

import Entities.Classes.*;
import Entities.Enum.Direction;
import Entities.Enum.Images;
import Entities.Enum.Panels;
import Entities.Enum.PowerUpTypes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

/**
 * Window Class.
 *
 * @author Nicolas Viseur
 * @version 1.0
 */
public class Window extends JFrame{

    private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private static final Dimension windowSize = new Dimension();
    private PlayGround pg;

    public Window()
    {
        this.setTitle("Bomberman V1.0 By Nicolas Viseur");
        windowSize.setSize((int)(screenSize.getHeight()-screenSize.getHeight()/10),(int)(screenSize.getHeight()-screenSize.getHeight()/10));
        pg = new PlayGround(0);
        this.addKeyListener(pg);
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
                //Get the focus so we can listen to the keyboard
                this.requestFocusInWindow();
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

    public static Dimension getWindowSize()
    {
        return windowSize;
    }


}
