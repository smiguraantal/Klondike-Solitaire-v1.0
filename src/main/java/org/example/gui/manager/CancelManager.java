package org.example.gui.manager;

import org.example.gui.Card;
import org.example.gui.Table;
import org.example.gui.place.Place;

import java.util.Vector;

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
 * A visszavonás megoldását vezérlő osztály
 */
public class CancelManager {

    /**
     * Kártyák áthelyezés előtti állapota
     */
    Vector cardsBefore = new Vector();

    /**
     * Kártyák áthelyezés utáni állapota
     */
    Vector cardsAfter = new Vector();

    /**
     * A főablak
     */
    private Table table;

    //----------------------------------------------------------------------

    /**
     * Konstruktor
     */
    public CancelManager(Table table) {
        this.table = table;
    }

    //----------------------------------------------------------------------

    /**
     * Hozzáadja a lap másolatát az áthelyezés előtti lapokhoz
     */
    public void addToCardsBefore(Card cardBefore) {
        try {
            cardsBefore.add((Card) cardBefore.clone());
        } catch (CloneNotSupportedException ex) {
            ex.printStackTrace();
        }
    }

    //----------------------------------------------------------------------

    /**
     * Hozzáadja a lapot az áthelyezés utáni lapokhoz
     */
    public void addToCardsAfter(Card cardAfter) {
        cardsAfter.add(cardAfter);
        table.getMainFrame().cancelable(true); // innentől kezdve már visszavonható!
    }

    //----------------------------------------------------------------------

    /**
     * Visszavonja az utolsó lap vagy lapok áthelyezését
     */
    public void cancel() {
        Place newPlace = null;
        for (int i = 0; i < cardsAfter.size(); i++) { // visszarakás a régi helyre
            Card card = (Card) cardsAfter.get(i); // új
            Card cardBefore = (Card) cardsBefore.get(i); // régi
            newPlace = card.getPlace(); // új hely
            Place oldPlace = cardBefore.getPlace(); // régi hely
            card.setBounds(oldPlace.getNextBounds());
            int layer = oldPlace.getNextLayer();
            table.setLayer(card, layer);
            card.setLayer(layer);
            card.setVisibility(cardBefore.isVisibility());
            oldPlace.addDraggedCard(card);
        }
        for (int i = 0; i < cardsAfter.size(); i++) { // törlés az új helyről
            newPlace.removeTopCard();
        }
    }

    //----------------------------------------------------------------------

    public Vector getCardsAfter() {
        return cardsAfter;
    }

    public Vector getCardsBefore() {
        return cardsBefore;
    }


}








