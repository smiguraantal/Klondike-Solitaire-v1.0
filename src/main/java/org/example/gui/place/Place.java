package org.example.gui.place;

import org.example.entity.Colors;
import org.example.entity.Constants;
import org.example.entity.DataModule;
import org.example.gui.Card;
import org.example.gui.Cards;
import org.example.gui.Table;
import org.example.gui.manager.DraggingManager;

import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.awt.Dimension;
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
 * A kártyák lehetséges helyének absztrakt osztály
 */
abstract public class Place extends Rectangle implements Constants, Colors {

    /**
     * Adatmodul
     */
    protected DataModule dataModule = DataModule.getDataModule();

    /**
     * Az asztal
     */
    protected Table table;

    /**
     * A lapok
     */
    protected Cards cards = new Cards();

    BorderLayout borderLayout1 = new BorderLayout();

    //----------------------------------------------------------------------

    /**
     * Konstruktor
     */
    protected Place(Table table, Point point) {
        this.table = table;
        setBounds(new Rectangle(point, new Dimension(CARD_WIDTH, CARD_HEIGTH)));
    }

    //----------------------------------------------------------------------

    /**
     * Visszaadja, hogy vannak-e kártya ezen a helyen
     */
    public boolean isEmpty() {
        return cards.isEmpty();
    }

    //----------------------------------------------------------------------

    /**
     * Hozzáadja a kapott kártyákat
     */
    public void addCard(Card otherCard) {
        cards.addCard(otherCard);
        otherCard.setPlace(this);
    }

    //----------------------------------------------------------------------

    /**
     * Hozzáadja a vonszolt lapot a többi laphoz
     */
    public void addDraggedCard(Card otherCard) {
        addCard(otherCard);
    }

    //----------------------------------------------------------------------

    /**
     * Az ősökben lesz megírva
     */
    public boolean putableCard(Card otherCard) {
        return false;
    }

    //----------------------------------------------------------------------

    /**
     * Visszaadja a felfelső lapot
     */
    public Card getTopCard() {
        return cards.getTopCard();
    }

    //----------------------------------------------------------------------

    /**
     * Visszaadja a megfogott kártyákat.
     */
    public Vector getTakenCards(Card takenCard) {
        Vector takenCards = new Vector();
        takenCards.add(takenCard);
        return takenCards;
    }

    //----------------------------------------------------------------------

    /**
     * Kiveszi a legfelső lapot
     */
    public Card removeTopCard() {
        return cards.removeTopCard();
    }

    //----------------------------------------------------------------------

    /**
     * Visszaadja a legfelső réteget
     */
    public int getTopLayer() {
        return isEmpty() ? 0 : cards.getTopCard().getLayer();
    }

    //----------------------------------------------------------------------

    /**
     * Visszaadja a fegfelső után következő réteget (ez lesz a következő ide-
     * kerülő lap rétegszáma
     */
    public int getNextLayer() {
        return isEmpty() ? 0 : getTopLayer() + 1;
    }

    //----------------------------------------------------------------------

    /**
     * Visszaadja a kupac legfelső pozícióját
     */
    public Rectangle getTopBounds() {
        return getTopCard() != null ? getTopCard().getBounds() : getBounds();
    }

    //----------------------------------------------------------------------

    /**
     * Visszaadja a következő lap pozícióját
     */
    public Rectangle getNextBounds() {
        return getBounds();
    }

    //----------------------------------------------------------------------

    /**
     * Egérgomb lenyomásának lekezelése
     */
    public void mousePressed(Card card, MouseEvent e) {
        if (e.getModifiers() == e.BUTTON1_MASK) { // bal egérkattintás
            Point draggedPoint =
                    SwingUtilities.convertPoint(card,
                            e.getPoint(),
                            table); // transzformálás az asztal koordináta rendszerébe

            dataModule.getDraggingManager().setTakenCard(card);
            dataModule.getDraggingManager().setDraggedPoint(draggedPoint);
            dataModule.getDraggingManager().setInnerValues();
        }
        if (e.getModifiers() == e.BUTTON3_MASK)  // jobb egérkattintás
            table.putAllToHouse();
    }

    //----------------------------------------------------------------------

    /**
     * Egérgomb elengedése
     */
    public void mouseReleased(Card card, MouseEvent e) {
        if (e.getModifiers() == e.BUTTON1_MASK) { // ha a bal egérgomb lesz felengedve
            DraggingManager draggingManager = dataModule.getDraggingManager();
            if (draggingManager.getTakenCard() != null) { // ha van vonszolt kártya, vagyis a legutolsó kattintás egy vonszolható kártyán történt
                Place newPlace = table.getPrimaryPlace();
                if (newPlace != null)
                    draggingManager.released(newPlace);
                else  // ha nincs átfedésben
                    draggingManager.backToOriginalPlace();
            }
        }
        table.maybeGameEnds();
    }

    //----------------------------------------------------------------------

    /**
     * Egérkattintás esemény lekezelése (az utódokban lesz kifejtve)
     */
    public void mouseClicked(MouseEvent e) {
    }

    //----------------------------------------------------------------------

    /**
     * Egérvonszolás
     */
    public void mouseDragged(Card card, MouseEvent e) {
        if (e.getModifiers() == e.BUTTON1_MASK) { // bal gomb vonszolhat
            Point point =
                    SwingUtilities.convertPoint(card,
                            e.getPoint(),
                            table); // transzformálás a tartalompanel koordináta rendszerébe
            DraggingManager draggingManager = dataModule.getDraggingManager();
            if (draggingManager.getTakenCard() != null) // ha van vonszolt kártya
                draggingManager.drag(point);
        }
    }

    //----------------------------------------------------------------------

    public Cards getCards() {
        return cards;
    }

    public void setCards(Cards cards) {
        this.cards = cards;
    }

    public Table getTable() {
        return table;
    }
}






