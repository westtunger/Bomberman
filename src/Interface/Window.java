package Interface;

import Entities.Enum.Panels;

import javax.swing.*;
import java.awt.*;

/**
 * Window Class.
 *
 * @author Nicolas Viseur
 * @version 1.0
 */
public class Window extends JFrame{

    private static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private static final Dimension windowSize = new Dimension();
    private PlayGround pg;

    public Window()
    {
        this.setTitle("Bomberman V1.0 By Nicolas Viseur");
        windowSize.setSize((int)(screenSize.getHeight()-screenSize.getHeight()/10),(int)(screenSize.getHeight()-screenSize.getHeight()/10));
        this.setSize(windowSize);
        this.isAlwaysOnTop();
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.getContentPane().add(new Menu(this));
        this.setVisible(true);
    }

    /**
     * Change the panel actually showing on the screen.
     * @param panel the panel to use.
     * @see Panels
     */
    public void changePanel(Panels panel)
    {
        this.getContentPane().removeAll();

        switch (panel)
        {
            case game:
                pg = new PlayGround(this);
                this.addKeyListener(new InputListener(pg));
                this.getContentPane().add(pg);
                this.revalidate();
                this.repaint();
                //Get the focus so we can listen to the keyboard
                this.requestFocusInWindow();
                pg.play();
                break;

            case menu:
                this.getContentPane().add(new Menu(this));
                this.getContentPane().remove(pg);
                this.revalidate();
                this.repaint();
                break;
        }
    }

    /**
     * Give the dimensions of the window.
     *
     * @return the dimensions of the window
     * @see Dimension
     */
    public static Dimension getWindowSize()
    {
        return windowSize;
    }


}
