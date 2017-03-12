package Interface;

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
    JButton play = new JButton("Play");
    JButton exit = new JButton("Exit");

    public Menu()
    {
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
    }


}
