package org.example.gui.manager;

import org.example.entity.Constants;
import org.example.entity.DataModule;
import org.example.gui.Card;
import org.example.gui.Table;
import org.example.gui.place.House;
import org.example.gui.place.Place;

import java.awt.Component;
import java.awt.Point;
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
 * A lap vonszolását menedzselő osztály
 */
public class DraggingManager implements Constants {

    /**
     * Adatmodul
     */
    DataModule dataModule = DataModule.getDataModule();

    /**
     * Az asztal
     */
    private Table table;

    /**
     * A megfogott lap
     */
    private Card takenCard;

    /**
     * A megfogott lapok
     */
    private Vector takenCards;

    /**
     * A lap megfogásának koordinátája az asztalhoz viszonyítva
     */
    private Point draggedPoint;

    /**
     * A lapon való kattintás vízszintes távolsága a lap bal felső sarkától
     */
    private int innerX;

    /**
     * A lapon való kattintás függőleges távolsága a lap bal felső sarkától
     */
    private int innerY;

    /**
     * A megfogott lapok másolata
     */
    private Vector clonedCards;

    //----------------------------------------------------------------------

    /**
     * Konstruktor
     */
    public DraggingManager(Table table) {
        this.table = table;
    }

    //----------------------------------------------------------------------

    /**
     * Meghatározza a vonszolt kártyát. Ha a kapott komponens nem Card
     * osztályú vagy nem felfordított, akkor nem vonszolható a lap.
     * Ha vonszolható, akkor meghatározza az összes megfogott lapot és
     * klónozza azokat.
     */
    public void setTakenCard(Component component) {
        if (component instanceof Card) { // ha Card osztályú
            Card card = (Card) component;
            takenCard = card.isVisibility() ? card : null; // ha nem látható, akkor nem is vonszolható
        } else // ha nem Card osztályú
            takenCard = null;
        if (takenCard != null) { // ha nem null, vagyis vonszolható
            cloneCards();
        }
    }

    //----------------------------------------------------------------------

    /**
     * Meghatározza és klónozza az összes megfogott lapot
     */
    private void cloneCards() {
        takenCards = takenCard.getPlace().getTakenCards(takenCard);
        try {
            clonedCards = new Vector();
            for (int i = 0; i < takenCards.size(); i++) {
                Card card = (Card) takenCards.get(i);
                clonedCards.add((Card) card.clone());
            }
        } catch (CloneNotSupportedException ex) {
            ex.printStackTrace();
        }
    }

    //----------------------------------------------------------------------

    /**
     * Meghatározza lap szélének a kattintástól való távolságát.
     */
    public void setInnerValues() {
        if (takenCard != null) {
            innerX = draggedPoint.x - takenCard.getX();
            innerY = draggedPoint.y - takenCard.getY();
        }
    }

    //----------------------------------------------------------------------

    /**
     * Elvonszolja a lapot a pontból kiszámított helyre
     */
    public void drag(Point point) {
        for (int i = 0; i < takenCards.size(); i++) {
            Card card = (Card) takenCards.get(i);
            card.setLayer(i + 100);
            table.setLayer(card, card.getLayer());
            if (i == 0) // a megfogott lap
                point = new Point(point.x - innerX + i,
                        point.y - innerY + i);
            else { // az azt követő lapok helyének meghatározása
                Card previousCard = (Card) takenCards.get(i - 1); // mindig az előzőhöz képest
                point = new Point(previousCard.getX(),
                        previousCard.getY() + VISIBLE_CARD_TRANSLATE_IN_PACK);
            }
            card.setLocation(point);
        }
    }

    //----------------------------------------------------------------------

    /**
     * Visszahelyezi a lapot az eredeti, vonszolás előtti helyére
     */
    public void backToOriginalPlace() {
        for (int i = 0; i < takenCards.size(); i++) {
            Card card = (Card) takenCards.get(i);
            Card clone = (Card) clonedCards.get(i);
            card.setLayer(clone.getLayer());
            table.setLayer(card, card.getLayer());
            card.setBounds(clone.getBounds());
        }
    }

    //----------------------------------------------------------------------

    /**
     * A lapok vonszolás utáni elengedése
     */
    public void released(Place newPlace) {
        if (takenCards.size() > 1 && newPlace instanceof House) { // a házba csak egy lap tehető, több nem!
            backToOriginalPlace();
            return;
        }
        dataModule.setCancelManager(new CancelManager(table));
        putInNewPlace(newPlace);
        removeFromOldPlace();
    }

    //----------------------------------------------------------------------

    /**
     * Beteszi a lapokat az új helyükre
     */
    private void putInNewPlace(Place newPlace) {
        for (int i = 0; i < takenCards.size(); i++) { // kártyák új helyre tétele
            Card clone = (Card) clonedCards.get(i);
            dataModule.getCancelManager().addToCardsBefore(clone); // előző elmentése
            dataModule.getPointManager().setCardBefore(clone);
            Card card = (Card) takenCards.get(i);
            card.setBounds(newPlace.getNextBounds());
            int layer = newPlace.getNextLayer();
            table.setLayer(card, layer);
            card.setLayer(layer);
            newPlace.addDraggedCard(card);
            dataModule.getCancelManager().addToCardsAfter(card); // új elmentése
            dataModule.getPointManager().setCardAfter(card);
        }
        dataModule.getPointManager().cardTransfer();
    }

    //----------------------------------------------------------------------

    /**
     * Kitörli a lapokat a régi helyükről
     */
    private void removeFromOldPlace() {
        for (int i = 0; i < takenCards.size(); i++) { // régi helyről való törlés
            Card card = (Card) clonedCards.get(i);
            Place place = card.getPlace();
            place.removeTopCard();
        }
    }

    //----------------------------------------------------------------------

    public Point getDraggedPoint() {
        return draggedPoint;
    }

    public int getInnerX() {
        return innerX;
    }

    public int getInnerY() {
        return innerY;
    }

    public Card getTakenCard() {
        return takenCard;
    }

    public void setDraggedPoint(Point draggedPoint) {
        this.draggedPoint = draggedPoint;
    }

    public void setInnerX(int InnerX) {
        this.innerX = InnerX;
    }

    public void setInnerY(int InnerY) {
        this.innerY = InnerY;
    }

    public Vector getClonedCards() {
        return clonedCards;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }
}