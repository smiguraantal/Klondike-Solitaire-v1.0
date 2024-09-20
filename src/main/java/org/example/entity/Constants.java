package org.example.entity;

import java.util.GregorianCalendar;

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
 * A program által használt konstansadatokat tartalmazó interfész
 */
public interface Constants {

    /**
     * Az ász értéke
     */
    public static final int ACE = 1;

    /**
     * A király értéke
     */
    public static final int KING = 13;

    /**
     * a kártya hátoldala
     */
    public static final String BACK = "images/back_01.png";

    /**
     * A kártya szélessége
     */
    public static final int CARD_WIDTH = 66;

    /**
     * A kártya magassága
     */
    public static final int CARD_HEIGTH = 90;

    /**
     * A felfordított kártyák egymáshoz képesti eltolása a kupacokban
     */
    public static final int VISIBLE_CARD_TRANSLATE_IN_PACK = 17;

    /**
     * A lefedett kártyák egymáshoz képesti eltolása a kupacokban
     */
    public static final int NON_VISIBLE_CARD_TRANSLATE_IN_PACK = 7;

    /**
     * A talonban lévő kártyák vízszintes eltolása
     */
    public static final int HORIZONTAL_CARD_TRANSLATE_IN_TALON = 4;

    /**
     * A talonban lévő kártyák függőleges eltolása
     */
    public static final int VERTICAL_CARD_TRANSLATE_IN_TALON = 2;

    /**
     * A kártyakupacok között hely
     */
    public static final int GAP_SIZE = 20;

    /**
     * A talon lapjainak kezdeti száma
     */
    public static final int TALON_SIZE = 24;

    /**
     * A házak száma
     */
    public static final int NUMBER_OF_HOUSES = 4;

    /**
     * A kupacok száma
     */
    public static final int NUMBER_OF_PACKS = 7;

    /**
     * A kártyapakli lapjainak száma (összes kártya)
     */
    public static final int NUMBER_OF_DECK_OF_CARDS = 52;

    /**
     * A kártyák színeinek száma (kör, tref, káró, pikk)
     */
    public static final int NUMBER_OF_CARD_COLORS = 4;

    /**
     * A kártya értékeinek száma (ász, 2, 3, ... király)
     */
    public static final int NUMBER_OF_CARD_VALUES = 13;

    /**
     * Az asztal szélessége
     */
    public static final int TABLE_WIDTH =
            NUMBER_OF_PACKS * CARD_WIDTH +
                    GAP_SIZE * 8;

    /**
     * Az asztal magassága
     */
    public static final int TABLE_HEIGHT =
            GAP_SIZE * 3 +
                    CARD_HEIGTH * 2 +
                    NON_VISIBLE_CARD_TRANSLATE_IN_PACK * 6 +
                    VISIBLE_CARD_TRANSLATE_IN_PACK * 12;

    /**
     * A kinézet lehetséges értékei
     */
    public static final int WINDOWS_LOOK_AND_FEEL = 100;
    public static final int METAL_LOOK_AND_FEEL = 101;
    public static final int MOTIF_LOOK_AND_FEEL = 102;

    /**
     * A pontozási rendszer lehetséges értékei
     */
    public static final int NORMAL_POINTING_SYSTEM = 200;
    public static final int VEGAS_POINTING_SYSTEM = 201;
    public static final int NONE_POINTING_SYSTEM = 202;

    /**
     * A játék lehetséges állapotai
     */
    public static final int STOP = 0;
    public static final int PLAY = 1;
    public static final int PAUSE = 2; // még nincs szerepe!

    /**
     * Maximális játékidő
     */
    public static final int MAX_TIME = 99999;

    /**
     * Eddig számolja a pontokat a játék
     */
    public static final int MAX_POINTS = 99999;

    /**
     * Ennél kevesebb pont nem érhető el
     */
    public static final int MIN_POINTS = -99999;

    /**
     * Kitöltetlen név helyett
     */
    public static final String ANONYM = "Névtelen";

    /**
     * Alapértelmezett dátum
     */
    public static final GregorianCalendar DEFAULT_DATE =
            new GregorianCalendar(2004, 0, 1);

    /**
     * A kezdő tét a vegas pontozási rendszerben
     */
    public static final int BEGINNING_POT = -52;
}




