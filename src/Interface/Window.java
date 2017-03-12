package Interface;

import Entities.Classes.*;
import Entities.Enum.Direction;
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
    PlayGround playGround = new PlayGround();


    public Window()
    {
        this.setTitle("Bomberman V1.0 By Nicolas Viseur");
        this.setSize(600,600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setContentPane(playGround);
        this.setVisible(true);

        Player p1 = new Player(0,"Test",new Point(10,10));
        Player p2 = new Player(1,"Test",new Point(10,150));
        Wall w1 = new Wall(false,new Point(150,10));
        Wall w2 = new Wall(true,new Point(150,150));
        Bomb b1 = new Bomb(5,new Point(10,300),p1);
        Explosion f1 = new Explosion(10, Direction.down,new Point(150,300));
        PowerUp pu1 = new PowerUp(PowerUpTypes.power,new Point(300,10));
        PowerUp pu2 = new PowerUp(PowerUpTypes.speed,new Point(300,150));
        PowerUp pu3 = new PowerUp(PowerUpTypes.bomb,new Point(300,300));

        play();
    }

    public void play()
    {
        int i =0;
        while(i <= 1000)
        {
            i++;
            playGround.repaint();

            try {
                Thread.sleep(1000/30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
