package org.example.gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Window;

/**
 * <p>Title: Klondike Solitaire</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 *
 * @author Smigura Antal
 * @version 1.0
 */

/**
 * A kapott ablakot a képernyőn elhelyező osztály.
 */
public class WindowLocation {

    /**
     * A képernyő mérete.
     */
    private static Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

    /**
     * Az osztályból nem lehet példányt létrehozni.
     */
    private WindowLocation() {
    }

    /**
     * A paraméterben kapott ablakot a képernyő közepére helyezi.
     */
    public static void center(Window win) {
        win.setLocation((dim.width - win.getWidth()) / 2,
                (dim.height - win.getHeight()) / 2);
    }

    /**
     * A paraméterben kapott ablakot a képernyő bal szélére helyezi.
     */
    public static void west(Window win) {
        win.setLocation(0, (dim.height - win.getHeight()) / 2);
    }

    /**
     * A paraméterben kapott ablakot a képernyő jobb szélére helyezi.
     */
    public static void east(Window win) {
        win.setLocation(dim.width - win.getWidth(),
                (dim.height - win.getHeight()) / 2);
    }

    /**
     * A paraméterben kapott ablakot a képernyő felső részére helyezi.
     */
    public static void north(Window win) {
        win.setLocation((dim.width - win.getWidth()) / 2, 0);
    }

    /**
     * A paraméterben kapott ablakot a képernyő alsó részére helyezi.
     */
    public static void south(Window win) {
        win.setLocation((dim.width - win.getWidth()) / 2,
                (dim.height - win.getHeight()));
    }

    /**
     * A paraméterben kapott ablakot a képernyő bal felső sarkába helyezi.
     */
    public static void northWest(Window win) {
        win.setLocation(0, 0);
    }

    /**
     * A paraméterben kapott ablakot a képernyő bal alsó sarkába helyezi.
     */
    public static void southWest(Window win) {
        win.setLocation(0, dim.height - win.getHeight());
    }

    /**
     * A paraméterben kapott ablakot a képernyő jobb felső sarkába helyezi.
     */
    public static void northEast(Window win) {
        win.setLocation(dim.width - win.getWidth(), 0);
    }

    /**
     * A paraméterben kapott ablakot a képernyő jobb alsó sarkába helyezi.
     */
    public static void southEast(Window win) {
        win.setLocation(dim.width - win.getWidth(), dim.height - win.getHeight());
    }
}