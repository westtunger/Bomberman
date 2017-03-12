package Interface;

import Entities.Enum.Panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Menu Class.
 *
 * @author Nicolas Viseur
 * @version 1.0
 */
public class Menu extends JPanel implements ActionListener{
    private JButton play = new JButton("Play");
    private JButton exit = new JButton("Exit");
    private Window window;

    public Menu(Window window)
    {

        this.window = window;

        //this.setBorder(BorderFactory.createEmptyBorder(screenSize.width/8,screenSize.width/8,screenSize.width/8,screenSize.width/8));

        this.setLayout(new GridLayout(5,0));
        this.add(new Box(0));
        this.add(play);
        this.add(new Box(0));
        this.add(exit);
        this.add(new Box(0));
        exit.addActionListener(this);
        play.addActionListener(this);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == exit)
        {
            System.exit(0);
        }
        else if(e.getSource() == play)
        {
            this.window.changePanel(Panels.game);
        }
    }


}
