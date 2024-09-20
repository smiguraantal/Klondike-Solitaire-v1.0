package org.example.gui.manager;

import org.example.entity.Constants;
import org.example.entity.DataModule;
import org.example.gui.Card;
import org.example.gui.MainFrame;
import org.example.gui.place.House;
import org.example.gui.place.Pack;
import org.example.gui.place.VisibleTalon;

/**
 * <p>Title: Klondike Solitaire</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 *
 * @author Smigura Antal
 * @version 1.0
 */

public class PointManager implements Constants {

    /**
     * Adatmodul
     */
    DataModule dataModule = DataModule.getDataModule();

    /**
     * A lap pontozás előtti állapota
     */
    Card cardBefore;

    /**
     * A lap pontozás utáni állapota
     */
    Card cardAfter;

    /**
     * Az utolsó műveletért járó pontok
     */
    int givenPoints;

    /**
     * A talon visszafordításáért járó pontok
     */
    final int TALON_TURNING = -100;

    /**
     * A kupac felső lapjának felfordításáért járó pontok
     */
    final int PACK_RESERVE = 5;

    /**
     * A házba rakárért járó pontok a vegas pontozási rendszerben
     */
    final int TO_HOUSE_VEGAS = 5;

    /**
     * A házba rakárért járó pontok a normál pontozási rendszerben
     */
    final int TO_HOUSE_NORMAL = 10;

    /**
     * A kupacra rakásért járó pontok a normál pontozási rendszerben
     */
    final int TO_PACK_FROM_HOUSE_NORMAL = -15;

    /**
     * A kupacra rakásért járó pontok a vegas pontozási rendszerben
     */
    final int TO_PACK_FROM_HOUSE_VEGAS = -5;

    /**
     * A talonból a kupacra rakásért járó pontok a normál pontozási rendszerben
     */
    final int TO_PACK_FROM_TALON_NORMAL = 5;

    /**
     * A visszavonásért járó bűntetőpontok
     */
    final int POINTS_FOR_CANCEL = -2;

    /**
     * A főablak
     */
    private MainFrame mainFrame;

    //----------------------------------------------------------------------

    /**
     * Konstruktor
     */
    public PointManager(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    //----------------------------------------------------------------------

    /**
     * A talon visszafordítása
     */
    public void talonTurning() {
        givenPoints = dataModule.getPointingSystem() == NORMAL_POINTING_SYSTEM ?
                TALON_TURNING : 0;
        refreshPoints();
    }

    //----------------------------------------------------------------------

    /**
     * A kupac legfelső lapjának felfordítása
     */
    public void packReserve() {
        givenPoints = dataModule.getPointingSystem() == NORMAL_POINTING_SYSTEM ?
                PACK_RESERVE : 0;
        refreshPoints();
    }

    //----------------------------------------------------------------------

    /**
     * Egy lap berakása a házba
     */
    public void putToHouse() {
        givenPoints = dataModule.getPointingSystem() == NORMAL_POINTING_SYSTEM ?
                TO_HOUSE_NORMAL : TO_HOUSE_VEGAS;
        refreshPoints();
    }

    //----------------------------------------------------------------------

    /**
     * Egy lap át lett helyezve valahonnan valahová
     */
    public void cardTransfer() {
        if (cardBefore.getPlace() instanceof VisibleTalon) { // talonból
            if (cardAfter.getPlace() instanceof House) { // házba
                givenPoints = dataModule.getPointingSystem() == NORMAL_POINTING_SYSTEM ?
                        TO_HOUSE_NORMAL : TO_HOUSE_VEGAS;
            } else { // kupacra
                givenPoints = dataModule.getPointingSystem() == NORMAL_POINTING_SYSTEM ?
                        TO_PACK_FROM_TALON_NORMAL : 0;
            }
        } else if (cardBefore.getPlace() instanceof House) { // házból
            if (cardAfter.getPlace() instanceof Pack) { // kupacra
                givenPoints = dataModule.getPointingSystem() == NORMAL_POINTING_SYSTEM ?
                        TO_PACK_FROM_HOUSE_NORMAL : TO_PACK_FROM_HOUSE_VEGAS;
            } else { // házba
                givenPoints = 0;
            }
        } else if (cardBefore.getPlace() instanceof Pack) { // kupacról
            if (cardAfter.getPlace() instanceof House) { // házba
                givenPoints = dataModule.getPointingSystem() == NORMAL_POINTING_SYSTEM ?
                        TO_HOUSE_NORMAL : TO_HOUSE_VEGAS;
            } else { // kupacra
                givenPoints = 0;
            }
        }
        refreshPoints();
    }

    //----------------------------------------------------------------------

    /**
     * Visszavonáskor levonja az utolsó műveletért adott pontokat
     */
    public void cancel() {
        if (dataModule.getPointingSystem() == NORMAL_POINTING_SYSTEM)
            dataModule.setPoints(dataModule.getPoints() - givenPoints + POINTS_FOR_CANCEL);
        if (dataModule.getPointingSystem() == VEGAS_POINTING_SYSTEM)
            dataModule.setPoints(dataModule.getPoints() - givenPoints);
        mainFrame.refreshPoints();
    }

    //----------------------------------------------------------------------

    /**
     * Hozzáadja a pontokat az eddigiekhez
     */
    public void refreshPoints() {
        dataModule.setPoints(dataModule.getPoints() + givenPoints);
        mainFrame.refreshPoints();
    }

    //----------------------------------------------------------------------

    public Card getCardAfter() {
        return cardAfter;
    }

    public Card getCardBefore() {
        return cardBefore;
    }

    public void setCardAfter(Card cardAfter) {
        this.cardAfter = cardAfter;
    }

    public void setCardBefore(Card cardBefore) {
        this.cardBefore = cardBefore;
    }

}




