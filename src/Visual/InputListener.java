package Visual;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import static KeyMapping.Key.*;

/**
 * Input Listener.
 *
 * @author Nicolas Viseur
 * @version 1.0
 */
class InputListener implements KeyListener {
    private final ArrayList<Integer> keys;

    public InputListener() {
        keys = new ArrayList<>();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    public void addKey(Integer key)
    {
        keys.add(key);
    }

    public ArrayList<Integer> getKeys()
    {
        return new ArrayList<>(keys);
    }

    public boolean containKey(Integer key)
    {
        return keys.contains(key);
    }

    public void removeKey(Integer key)
    {
        keys.remove(key);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == p1Up.getCode() || e.getKeyCode() == p1Down.getCode() || e.getKeyCode() == p1Left.getCode() || e.getKeyCode() == p1Right.getCode())
        {
            removeKey(p1Up.getCode());

            removeKey(p1Down.getCode());

            removeKey(p1Left.getCode());

            removeKey(p1Right.getCode());

            addKey(e.getKeyCode());
        }
        else if(e.getKeyCode() == p2Up.getCode() || e.getKeyCode() == p2Down.getCode() || e.getKeyCode() == p2Left.getCode() || e.getKeyCode() == p2Right.getCode())
        {
            removeKey(p2Up.getCode());

            removeKey(p2Down.getCode());

            removeKey(p2Left.getCode());

            removeKey(p2Right.getCode());

            addKey(e.getKeyCode());
        }
        else if(!containKey(e.getKeyCode()))
            addKey(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        removeKey(e.getKeyCode());
    }
}
