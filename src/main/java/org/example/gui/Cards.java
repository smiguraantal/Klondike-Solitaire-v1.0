package org.example.gui;

import org.example.entity.Constants;

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
 * Egy bizonyos helyen lévő kártyák összessége
 */
public class Cards implements Constants {

    /**
     * A lapok
     */
    private Vector cards = new Vector();

    /**
     * A lapok egymáshoz képesti eltolása
     */
    private int translate;

    //----------------------------------------------------------------------

    /**
     * Visszaadja, hogy a kártyák száma nulla-e
     */
    public boolean isEmpty() {
        return cards.isEmpty();
    }

    //----------------------------------------------------------------------

    /**
     * Visszaadja a kártyák számát
     */
    public int getSize() {
        return cards.size();
    }

    //----------------------------------------------------------------------

    /**
     * Visszaadja a legfelső kártyát
     */
    public Card getTopCard() {
        return isEmpty() ? null : (Card) cards.lastElement();
    }

    //----------------------------------------------------------------------

    /**
     * Kiveszi a legfelső lapot
     */
    public Card removeTopCard() {
        return isEmpty() ? null : (Card) cards.remove(getSize() - 1);
    }

    //----------------------------------------------------------------------

    /**
     * Hozzáadja a kapott lapot a kártyához
     */
    public void addCard(Card card) {
        cards.add(card);
    }

    //----------------------------------------------------------------------

    public int getTranslate() {
        return translate;
    }

    public void setTranslate(int translate) {
        this.translate = translate;
    }

    public Vector getCards() {
        return cards;
    }

    public void setCards(Vector cards) {
        this.cards = cards;
    }

}