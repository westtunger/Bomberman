package Interface;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static KeyMapping.Key.*;

/**
 * Input Listener.
 *
 * @author Nicolas Viseur
 * @version 1.0
 */
class InputListener implements KeyListener {
    private final PlayGround pg;

    public InputListener(PlayGround pg) {
        this.pg = pg;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == p1Up.getCode() || e.getKeyCode() == p1Down.getCode() || e.getKeyCode() == p1Left.getCode() || e.getKeyCode() == p1Right.getCode())
        {
            pg.removeKey(p1Up.getCode());

            pg.removeKey(p1Down.getCode());

            pg.removeKey(p1Left.getCode());

            pg.removeKey(p1Right.getCode());

            pg.addKey(e.getKeyCode());
        }
        else if(e.getKeyCode() == p2Up.getCode() || e.getKeyCode() == p2Down.getCode() || e.getKeyCode() == p2Left.getCode() || e.getKeyCode() == p2Right.getCode())
        {
            pg.removeKey(p2Up.getCode());

            pg.removeKey(p2Down.getCode());

            pg.removeKey(p2Left.getCode());

            pg.removeKey(p2Right.getCode());

            pg.addKey(e.getKeyCode());
        }
        else if(!pg.getKey(e.getKeyCode()))
            pg.addKey(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        pg.removeKey(e.getKeyCode());
    }
}
