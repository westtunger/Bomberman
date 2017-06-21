package Visual;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import static KeyMapping.Key.*;

/**
 * Input Listener.
 *
 * @author Nicolas Viseur
 * @version 1.2
 */
class InputListener implements KeyListener {
    private final ArrayList<Integer> inputs;

    public InputListener() {
        inputs = new ArrayList<>();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * Add a new inputs in the inputs list.
     * @param input The inputs to add.
     */
    public void addInput(Integer input)
    {
        inputs.add(input);
    }

    /**
     * Return the list containing all the inputs waiting to be processed.
     * @return The list containing all the inputs waiting to be processed.
     */
    public ArrayList<Integer> getInputs()
    {
        return new ArrayList<>(inputs);
    }

    /**
     * Look if the inputs list contain the given input.
     * @param input The input to look for.
     * @return True if the input is in the list, else false.
     */
    public boolean containInput(Integer input)
    {
        return inputs.contains(input);
    }

    /**
     * Remove the given input from the list.
     * @param input The input to remove.
     */
    public void removeInput(Integer input)
    {
        inputs.remove(input);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == p1Up.getCode() || e.getKeyCode() == p1Down.getCode() || e.getKeyCode() == p1Left.getCode() || e.getKeyCode() == p1Right.getCode())
        {
            removeInput(p1Up.getCode());

            removeInput(p1Down.getCode());

            removeInput(p1Left.getCode());

            removeInput(p1Right.getCode());

            addInput(e.getKeyCode());
        }
        else if(e.getKeyCode() == p2Up.getCode() || e.getKeyCode() == p2Down.getCode() || e.getKeyCode() == p2Left.getCode() || e.getKeyCode() == p2Right.getCode())
        {
            removeInput(p2Up.getCode());

            removeInput(p2Down.getCode());

            removeInput(p2Left.getCode());

            removeInput(p2Right.getCode());

            addInput(e.getKeyCode());
        }
        else if(!containInput(e.getKeyCode()))
            addInput(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        removeInput(e.getKeyCode());
    }
}
