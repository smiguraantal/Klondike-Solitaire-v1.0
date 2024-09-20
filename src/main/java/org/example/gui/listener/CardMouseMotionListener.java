package org.example.gui.listener;

import org.example.gui.Card;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;


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
 * A kártyalap egérmozgás-eseményeit figyelő osztály
 */
public class CardMouseMotionListener extends MouseMotionAdapter {

    /**
     * Ez a lap lesz figyelve
     */
    Card card;

    //----------------------------------------------------------------------

    /**
     * Konstruktor
     */
    public CardMouseMotionListener(Card card) {
        this.card = card;
    }

    //----------------------------------------------------------------------

    /**
     * Egérvonszolás
     */
    public void mouseDragged(MouseEvent e) {
        card.mouseDragged(e);
    }

}











