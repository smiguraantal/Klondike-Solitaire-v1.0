package org.example.gui.listener;

import org.example.gui.Card;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


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
 * A kártyalap egéreseményeit figyelő osztály
 */
public class CardMouseListener extends MouseAdapter {

    /**
     * Ez a lap lesz figyelve
     */
    Card card;

    //----------------------------------------------------------------------

    /**
     * Konstruktor
     */
    public CardMouseListener(Card card) {
        this.card = card;
    }

    //----------------------------------------------------------------------

    /**
     * Egérgomb lenyomása
     */
    public void mousePressed(MouseEvent e) {
        card.mousePressed(e);
    }

    //----------------------------------------------------------------------

    /**
     * Egérgombbal való klikkelés
     */
    public void mouseClicked(MouseEvent e) {
        card.mouseClicked(e);
    }

    //----------------------------------------------------------------------

    /**
     * Egérgomb felengedése
     */
    public void mouseReleased(MouseEvent e) {
        card.mouseReleased(e);
    }

}