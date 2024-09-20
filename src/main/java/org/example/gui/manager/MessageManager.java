package org.example.gui.manager;

import hu.extra.HuOptionPane;

import javax.swing.JOptionPane;
import java.awt.Component;

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
 * Az üzeneteket megjelenítő osztály.
 */
public class MessageManager {

    /**
     * Megjeleníti a hiba dialógust.
     */
    public static void showErrorDialog(Component parent, String message) {
        HuOptionPane.showMessageDialog(
                parent, message, "Hiba", JOptionPane.ERROR_MESSAGE);
    }

    //----------------------------------------------------------------------

    /**
     * Megjeleníti az info dialógust.
     */
    public static void showInfoDialog(Component parent, String message) {
        HuOptionPane.showMessageDialog(
                parent, message, "Információ", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Megjeleníti a megerősítés dialógust.
     */
    public static boolean showConfirmDialog(Component parent, String message) {
        return HuOptionPane.showConfirmDialog(parent, message,
                "Választás", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
    }
}