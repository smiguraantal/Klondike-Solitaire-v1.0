package org.example.entity;

/**
 * <p>Title: Pasziánsz</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 *
 * @author Smigura Antal
 * @version 1.0
 */

/**
 * A pontszámot pénzösszeggé formáló osztály
 */
public class PointFormat implements Constants {

    /**
     * Konstruktor letiltva
     */
    private PointFormat() {
    }

    //----------------------------------------------------------------------

    /**
     * Pénzösszeggé formázza a kapott pontszámot
     */
    public static String getCurrencyValue(int points) {
        if (points < 0)
            return "-$" + Math.abs(points);
        return "$" + points;
    }
}


