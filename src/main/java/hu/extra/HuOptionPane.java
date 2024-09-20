/*
 * Projekt: javalib
 *
 * Csomag: extra.hu
 * HuOptionPane.java
 *
 * Ezt a komponenst még lehet bővíteni.
 * Csak a legfontosabb metódusok bannak definiálva.
 *
 * Angster Erzsébet: OO tervezés és programozás, Java 2. kötet
 * 2002.09.01.
 */

package hu.extra;

import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.Component;

public class HuOptionPane extends JOptionPane {
    /* MessageDialog. Lehetséges paraméterei:
       Component parent, Object message, String title, int message_type, Icon icon
       Alapértelmezés: Gomb:Rendben, title:"Üzenet".
     */
    public static void showMessageDialog(Component parent,
                                         Object message, String title, int message_type, Icon icon) {
        Object[] options = {"Rendben"};
        JOptionPane.showOptionDialog(parent, message, title, JOptionPane.YES_OPTION,
                message_type, icon, options, options[0]);
    }

    public static void showMessageDialog(Component parent,
                                         Object message, String title, int message_type) {
        Object[] options = {"Rendben"};
        JOptionPane.showOptionDialog(parent, message, title, JOptionPane.YES_OPTION,
                message_type, null, options, options[0]);
    }

    public static void showMessageDialog(Component parent, Object message) {
        Object[] options = {"Rendben"};
        JOptionPane.showOptionDialog(parent, message, "Üzenet", JOptionPane.YES_OPTION,
                JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
    }

    /* ConfirmDialog. Lehetséges paraméterei:
       Component parent, Object message, String title, int option_type, int message_type, Icon icon
       Alapértelmezés: Gomb:Igen/Nem/Mégse, title:"Kérdés".
     */
    public static int showConfirmDialog(Component parent,
                                        Object message, String title, int option_type, int message_type, Icon icon) {
        Object[] options = {"Igen", "Nem", "Mégse"};
    /*
    Kár, hogy nem megy így:
    JButton[] options = new JButton[3];
    options[0] = new JButton("Igen");
    options[0].setMnemonic('I');
    options[1] = new JButton("Nem");
    options[1].setMnemonic('N');
    options[2] = new JButton("Mégse");
    options[2].setMnemonic('M');
    */
        return JOptionPane.showOptionDialog(parent, message, title, option_type,
                message_type, icon, options, options[1]);
    }

    public static int showConfirmDialog(Component parent,
                                        Object message, String title, int option_type, int message_type) {
        Object[] options = {"Igen", "Nem", "Mégse"};
        return JOptionPane.showOptionDialog(parent, message, title, option_type,
                message_type, null, options, options[1]);
    }

    public static int showConfirmDialog(Component parent,
                                        Object message, String title, int option_type) {
        Object[] options = {"Igen", "Nem", "Mégse"};
        return JOptionPane.showOptionDialog(parent, message, title, option_type,
                JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
    }

    public static int showConfirmDialog(Component parent, Object message) {
        Object[] options = {"Igen", "Nem", "Mégse"};
        return JOptionPane.showOptionDialog(parent, message, "Választás",
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, options, options[1]);
    }

    /* InputDialog. Lehetséges paraméterei:
       JFrame parent, Object message, String title, int message_type, Icon icon,
       Object intitValue
       Alapértelmezés: Gomb:Rendbenh/Mégse, title:"Bevitel".
     */
    public static String showInputDialog(JFrame parent, Object message,
                                         String title, Object initValue) {
        return new HuInputDialog(parent, title).show(message, initValue);
    }

    public static String showInputDialog(JFrame parent, Object message,
                                         Object initValue) {
        return new HuInputDialog(parent, "Bevitel").show(message, initValue);
    }

    public static String showInputDialog(Object message, Object initValue) {
        return new HuInputDialog(null, "Bevitel").show(message, initValue);
    }

    public static String showInputDialog(JFrame parent, Object message) {
        return new HuInputDialog(parent, "Bevitel").show(message, "");
    }

    public static String showInputDialog(JFrame parent) {
        return new HuInputDialog(parent, "Bevitel").show("", "");
    }
}
