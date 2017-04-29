package KeyMapping;

import java.awt.event.KeyEvent;

/**
 * KeyMapping Enum.
 *
 * @author Nicolas Viseur
 * @version 1.0
 */
public enum Key{
    p1Up(KeyEvent.VK_Z), p1Down(KeyEvent.VK_S), p1Left(KeyEvent.VK_Q), p1Right(KeyEvent.VK_D), p1PoseBomb(KeyEvent.VK_SPACE),
    p2Up(KeyEvent.VK_UP), p2Down(KeyEvent.VK_DOWN), p2Left(KeyEvent.VK_LEFT), p2Right(KeyEvent.VK_RIGHT), p2PoseBomb(KeyEvent.VK_NUMPAD0);

    private final int code;

    Key(int code)
    {
        this.code = code;
    }

    /**
     * Return the code of the key.
     * @return the code of the Key.
     */
    public int getCode()
    {
        return code;
    }

    /**
     * Return the Key enum based on the given code.
     * Used to allow switching with this enum.
     * @return the Key enum.
     * @param code : the code of the desired Key.
     */
    //By getting the code as parameter and returning the corresponding Key, we can use our enum in a switch statement.
    public static Key fromCode(int code) {
        for (Key e : Key.values()) {
            if (e.getCode() == code) {
                return e;
            }
        }
        throw new RuntimeException("Enum not found");
    }
}
