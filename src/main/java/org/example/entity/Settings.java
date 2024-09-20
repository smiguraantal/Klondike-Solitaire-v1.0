package org.example.entity;

import java.io.Serializable;

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
 * A beállításokat tároló osztály
 */
public class Settings implements Serializable, Constants {

    /**
     * Az időeredmény
     */
    private int time;

    /**
     * Az elért pontszám
     */
    private int points;

    /**
     * A hátlap képének útvonala
     */
    private String imagePath;

    /**
     * A játék kinézete
     */
    private int lookAndFeel;

    /**
     * A pontozási rendszer
     */
    private int pointingSystem;

    /**
     * Látszik-e az állapotsor
     */
    private boolean statusField;

    /**
     * Időre megy-e a játék
     */
    private boolean useTimer;

    /**
     * Folyamatosan számolódnak-e a pontok
     */
    private boolean continuousGame;

    //----------------------------------------------------------------------

    /**
     * Konstruktor
     */
    public Settings(int time,
                    int points,
                    String imagePath,
                    int lookAndFeel,
                    int pointingSystem,
                    boolean statusField,
                    boolean useTimer,
                    boolean continuousGame) {
        this.time = time;
        this.points = points;
        this.imagePath = imagePath;
        this.lookAndFeel = lookAndFeel;
        this.pointingSystem = pointingSystem;
        this.statusField = statusField;
        this.useTimer = useTimer;
        this.continuousGame = continuousGame;
    }

    //----------------------------------------------------------------------

    /**
     * Konstruktor
     */
    public Settings() {
        this(0, 0, BACK, METAL_LOOK_AND_FEEL, NORMAL_POINTING_SYSTEM, true, true, false);
    }

    //----------------------------------------------------------------------

    public String getImagePath() {
        return imagePath;
    }

    public int getLookAndFeel() {
        return lookAndFeel;
    }

    public int getPoints() {
        return points;
    }

    public int getTime() {
        return time;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public void setLookAndFeel(int lookAndFeel) {
        this.lookAndFeel = lookAndFeel;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public boolean isContinuousGame() {
        return continuousGame;
    }

    public void setContinuousGame(boolean continuousGame) {
        this.continuousGame = continuousGame;
    }

    public int getPointingSystem() {
        return pointingSystem;
    }

    public void setPointingSystem(int pointingSystem) {
        this.pointingSystem = pointingSystem;
    }

    public boolean isStatusField() {
        return statusField;
    }

    public boolean isUseTimer() {
        return useTimer;
    }

    public void setUseTimer(boolean useTimer) {
        this.useTimer = useTimer;
    }

    public void setStatusField(boolean statusField) {
        this.statusField = statusField;
    }

}