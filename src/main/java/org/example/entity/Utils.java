package org.example.entity;

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
 * Főleg teszteléshez használandó metódusokat tartalmazó osztály
 */
public class Utils {

    /**
     * Konstruktor letiltva
     */
    private Utils() {
    }

    //----------------------------------------------------------------------

    /**
     * Visszaadja a kapott max kétjegyű számot a következő formában:
     * 00, 01, 02, ... 10, 11, ... 99
     */
    public static String format(int number) {
        if (number < 0 || number > 99)
            throw new BadValueException("Bad value: " + number);
        return number < 10 ? "0" + number : "" + number;
    }

    //----------------------------------------------------------------------

    /**
     * Kiírja a kapott szöveget és az értéket
     */
    public static void print(String s, int value) {
        System.out.println(s + ":  " + value);
    }

    //----------------------------------------------------------------------

    /**
     * Kiírja a kapott szöveget és az értéket
     */
    public static void print(String s, long value) {
        System.out.println(s + ":  " + value);
    }

    //----------------------------------------------------------------------

    /**
     * Kiírja a kapott szöveget és az értéket
     */
    public static void print(String s, float value) {
        System.out.println(s + ":  " + value);
    }

    //----------------------------------------------------------------------

    /**
     * Kiírja a kapott szöveget és az értéket
     */
    public static void print(String s, double value) {
        System.out.println(s + ":  " + value);
    }

    //----------------------------------------------------------------------

    /**
     * Kiírja a kapott szöveget és az értéket
     */
    public static void print(String s, char value) {
        System.out.println(s + ":  " + value);
    }

    //----------------------------------------------------------------------

    /**
     * Kiírja a kapott szöveget és az értéket
     */
    public static void print(String s, boolean value) {
        System.out.println(s + ":  " + value);
    }

    //----------------------------------------------------------------------

    /**
     * Kiírja a kapott szöveget és az objektumot
     */
    public static void print(String s, Object object) {
        System.out.println(s + ":  " + object);
    }
}

//############################################################################

class BadValueException extends RuntimeException {

    public BadValueException(String message) {
        super(message);
    }
}


