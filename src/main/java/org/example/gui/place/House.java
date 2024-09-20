package org.example.gui.place;

import org.example.gui.Card;
import org.example.gui.ColorSeries;
import org.example.gui.Table;

import java.awt.Point;

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
 * A kártyalapok végső helyét reprezentáló osztály
 */
public class House extends Place {

    /**
     * A színsor
     */
    ColorSeries colorSeries = new ColorSeries();

    //----------------------------------------------------------------------

    /**
     * Konstruktor
     */
    public House(Table table, Point point) {
        super(table, point);
    }

    //----------------------------------------------------------------------

    /**
     * Visszaadja, hogy van-e kártya a házban
     */
    public boolean isEmpty() {
        return colorSeries.isEmpty();
    }

    //----------------------------------------------------------------------

    /**
     * Hozzáadja a kapott kártyákat
     */
    public void addCard(Card otherCard) {
        colorSeries.addCard(otherCard);
        otherCard.setPlace(this);
    }

    //----------------------------------------------------------------------

    /**
     * Kiveszi a legfelső kártyát a színsorból
     */
    public Card removeTopCard() {
        return colorSeries.removeTopCard();
    }

    //----------------------------------------------------------------------

    /**
     * Visszaadja a legfelső réteget
     */
    public int getTopLayer() {
        return isEmpty() ? 0 : colorSeries.getTopCard().getLayer();
    }

    //----------------------------------------------------------------------

    /**
     * Visszaadja a legfelső lapot
     */
    public Card getTopCard() {
        return isEmpty() ? null : colorSeries.getTopCard();
    }

    //----------------------------------------------------------------------

    /**
     * Visszaadja, hogy a kapott lap berakható-e a házba
     */
    public boolean putableCard(Card otherCard) {
        if (isEmpty() && otherCard.isAce()) // ha üres a ház és a lap ász
            return true;
        if (!isEmpty() &&
                colorSeries.getTopCard().sameColor(otherCard) && // és ugyanolyan színű
                colorSeries.getTopCard().getValue() == otherCard.getValue() - 1) // és értékben stimmel
            return true;
        return false;
    }

    //----------------------------------------------------------------------

    public ColorSeries getColorSeries() {
        return colorSeries;
    }
}






