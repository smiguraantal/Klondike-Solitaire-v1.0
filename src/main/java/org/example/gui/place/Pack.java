package org.example.gui.place;

import org.example.entity.DataModule;
import org.example.gui.Card;
import org.example.gui.Cards;
import org.example.gui.Table;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
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
 * A kupacokat karbantartó osztály
 */
public class Pack extends Place {

    /**
     * Adatmodul
     */
    private DataModule dataModule = DataModule.getDataModule();

    /**
     * A kupac azon kártyái, melyek felvannak fordítva.
     */
    private Cards numberSeries = new Cards();

    //----------------------------------------------------------------------

    /**
     * Konstruktor
     */
    public Pack(Table table, Point point) {
        super(table, point);
    }

    //----------------------------------------------------------------------

    /**
     * Visszaadja, hogy a kupac lapjainak száma nulla-e
     */
    public boolean isEmpty() {
        return super.isEmpty() && numberSeries.isEmpty();
    }

    //----------------------------------------------------------------------

    /**
     * Meghatározza a számsorozat első lapját
     */
    public void initNumberSeries(Card card) {
        numberSeries.addCard(card);
        card.setPlace(this);
    }

    //----------------------------------------------------------------------

    /**
     * Hozzáad a kupachoz a kaoptt kártyát
     */
    public void addDraggedCard(Card otherCard) {
        numberSeries.addCard(otherCard);
        otherCard.setPlace(this);
    }

    //----------------------------------------------------------------------

    /**
     * Visszaadja a kupac legfelső kártyáját
     */
    public Card getTopCard() {
        if (!numberSeries.isEmpty()) // ha van számsorozat
            return numberSeries.getTopCard();
        else if (!cards.isEmpty()) // ha vannak lefedett kártyák
            return cards.getTopCard();
        return null; // ha üres a kupac
    }

    //----------------------------------------------------------------------

    /**
     * Visszaadja a megfogott kártyákat, melyek a kapott kártya és a felette
     * lévő lapokból állnak.
     */
    public Vector getTakenCards(Card takenCard) {
        Vector takenCards = new Vector();
        for (int i = 0; i < numberSeries.getSize(); i++) { // végig a számsorozat lapjain
            Card card = (Card) numberSeries.getCards().get(i);
            if (card.getLayer() >= takenCard.getLayer()) // ha a rétegszáma nagyobb vagy egyenlő a kapott kártyáénál
                takenCards.add(card);
        }
        return takenCards;
    }

    //----------------------------------------------------------------------

    /**
     * Kiveszi a legfelső lapot
     */
    public Card removeTopCard() {
        if (!numberSeries.isEmpty()) // ha van számsorozat
            return numberSeries.removeTopCard();
        else if (!cards.isEmpty()) // ha vannak lefedett kártyák
            return cards.removeTopCard();
        return null; // ha üres a kupac
    }


    //----------------------------------------------------------------------

    /**
     * Visszaadja, hogy a kapott kártya helyileg hova fog kerülni
     */
    public Rectangle getNextBounds() {
        if (isEmpty()) // ha üres a kupac
            return getBounds();
        Card topCard = getTopCard();
        Rectangle rec = topCard.getBounds();
        if (!topCard.isVisibility()) // ha a felgelső lap nem látható
            rec.y = rec.y + NON_VISIBLE_CARD_TRANSLATE_IN_PACK;
        else // ha látható a fegfelső lap
            rec.y = rec.y + VISIBLE_CARD_TRANSLATE_IN_PACK;
        return rec;
    }

    //----------------------------------------------------------------------

    /**
     * Visszaadja a legfelső rétegszámot
     */
    public int getTopLayer() {
        if (!numberSeries.isEmpty()) // ha van számsorozat
            return numberSeries.getTopCard().getLayer();
        else if (!cards.isEmpty()) // ha vannak lefedett kártyák
            return cards.getTopCard().getLayer();
        return 0;
    }

    //----------------------------------------------------------------------

    /**
     * Visszaadja, hogy a kapott lap hozzáadható-e a kupachoz
     */
    public boolean putableCard(Card otherCard) {
        return (isEmpty() && // ha még üres a kupac és a kapott lap király
                otherCard.isKing() ||

                !numberSeries.isEmpty() && // vagy ha nem üres a számsorozat
                        !getTopCard().similarColor(otherCard) && // és nem hasonló színű
                        getTopCard().getValue() == otherCard.getValue() + 1); // és értékben stimmel
    }

    //----------------------------------------------------------------------

    /**
     * Egérgomb lenyomásának lekezelése
     */
    public void mousePressed(Card card, MouseEvent e) {
        if (e.getModifiers() == e.BUTTON1_MASK) // bal egérkattintásra
            if (getTopCard().equals((Card) e.getSource())) // ha a legfelsőn kattintottak
                if (!getTopCard().isVisibility()) { // ha lefordított a lap
                    reserve();
                    return;
                }
        super.mousePressed(card, e);
    }

    //----------------------------------------------------------------------

    /**
     * A legfelső lap felfordítása
     */
    public void reserve() {
        numberSeries.addCard(removeTopCard()); // átrakás a számsorozatba
        getTopCard().setVisibility(true);
        dataModule.getDraggingManager().setTakenCard(null); // a kupacból való felfordítás után egyből nem vonszolható a lap
        dataModule.getPointManager().packReserve();
        table.getMainFrame().cancelable(false);
    }

    //----------------------------------------------------------------------

    /**
     * Egérkattintás lekezelése
     */
    public void mouseClicked(MouseEvent e) {
        if (e.getModifiers() == e.BUTTON1_MASK)  // bal egérkattintásra
            if (e.getClickCount() == 2) // ha a klikkelések száma kettő
                if (getTopCard().equals((Card) e.getSource())) // ha a legfelsőn kattintottak
                    if (getTopCard().isVisibility()) // ha felfordított a lap
                        table.tryToPutToHouse(this);
    }

    //----------------------------------------------------------------------

    public Cards getNumberSeries() {
        return numberSeries;
    }

    public void setNumberSeries(Cards numberSeries) {
        this.numberSeries = numberSeries;
    }
}