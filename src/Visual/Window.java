package Visual;

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
    private final InputListener inputListener;

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
        inputListener = new InputListener();
    }

    /**
     * Change the panel actually showing on the screen.
     * @param panel the panel asking to switch.
     */
    public void changePanel(JPanel panel)
    {
        this.getContentPane().removeAll();

        if(panel instanceof Menu) {
            pg = new PlayGround(this);
            this.addKeyListener(inputListener);
            this.getContentPane().add(pg);
            this.revalidate();
            this.repaint();
            //Get the focus so we can listen to the keyboard
            this.requestFocusInWindow();
            pg.play();
        }
        else {
            this.getContentPane().add(new Menu(this));
            this.getContentPane().remove(pg);
            this.revalidate();
            this.repaint();
        }
    }

    public InputListener getInputListener()
    {
        return this.inputListener;
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
