package org.example.gui.place;

import org.example.gui.Card;
import org.example.gui.Table;

import java.awt.Point;
import java.awt.Rectangle;
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
 * A lefordított lapokból álló talon
 */
public class NonVisibleTalon extends Place {

    /**
     * Konstruktor
     */
    public NonVisibleTalon(Table table, Point point) {
        super(table, point);
    }

    //----------------------------------------------------------------------

    /**
     * Hozzáadja a kapott kártyákat
     */
    public void addCard(Card otherCard) {
        super.addCard(otherCard);
    }

    //----------------------------------------------------------------------

    /**
     * Visszaadja a következő lap pozícióját
     */
    public Rectangle getNextBounds() {
        if (cards.getSize() < 10) //
            return getBounds();
        Rectangle rec = getBounds();
        if (cards.getSize() < 20) { //
            rec.x = rec.x + HORIZONTAL_CARD_TRANSLATE_IN_TALON;
            rec.y = rec.y + VERTICAL_CARD_TRANSLATE_IN_TALON;
        } else { //
            rec.x = rec.x + HORIZONTAL_CARD_TRANSLATE_IN_TALON * 2;
            rec.y = rec.y + VERTICAL_CARD_TRANSLATE_IN_TALON * 2;
        }
        return rec;
    }

    //----------------------------------------------------------------------

    /**
     * Lekezeli a talonon való bal egérkattintást
     */
    public void mousePressed(Card card, MouseEvent e) {
        dataModule.getDraggingManager().setTakenCard(null); // nem vonszolható a kártya
        if (e.getModifiers() == e.BUTTON1_MASK) { // bal egérkattintás
            if (getTopCard().equals((Card) e.getSource())) { // ha a legfelsőn kattintottak
                table.pullFromTalon();
            }
        }
        if (e.getModifiers() == e.BUTTON3_MASK) // jobb egérkattintás
            table.putAllToHouse();
    }
}







