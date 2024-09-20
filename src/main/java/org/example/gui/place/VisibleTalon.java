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
 * A talon felfordított lapjait tartalmazó osztály
 */
public class VisibleTalon extends Place {

    /**
     * Konstruktor
     */
    public VisibleTalon(Table table, Point point) {
        super(table, point);
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
     * Egérkattintás
     */
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2) {
            table.tryToPutToHouse(this);
        }
    }

    //----------------------------------------------------------------------

    /**
     * Lekezeli a talonon való bal egérkattintást
     */
    public void mousePressed(Card card, MouseEvent e) {
        if (e.getModifiers() == e.BUTTON3_MASK)  // jobb egérkattintás
            table.putAllToHouse();
        if (e.getModifiers() == e.BUTTON1_MASK) { // bal egérkattintás
            if (getTopCard().equals((Card) e.getSource())) { // ha a legfelsőn kattintottak
                super.mousePressed(card, e);
            } else { // ha nem a legfelsőn kattintottak
                dataModule.getDraggingManager().setTakenCard(null); // nem vonszolható a kártya
            }
        }
    }
}




