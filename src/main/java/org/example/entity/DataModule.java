package org.example.entity;

import org.example.gui.manager.CancelManager;
import org.example.gui.manager.DraggingManager;
import org.example.gui.manager.PointManager;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

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
 * Több osztály számára is fontos adatokat tároló osztály
 */
public class DataModule implements Constants {

    /**
     * Adatmodul
     */
    private static DataModule dataModule;

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
     * A kártyavonszolás kezelő objektum
     */
    private DraggingManager draggingManager;

    /**
     * A visszavonásért felelős objektum
     */
    private CancelManager cancelManager;

    /**
     * A pontozásért felelős objektum
     */
    private PointManager pointManager;

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
    public DataModule() {
        Settings settings = readSettings();
        this.time = settings.getTime();
        this.points = settings.getPoints();
        this.imagePath = settings.getImagePath();
        this.lookAndFeel = settings.getLookAndFeel();
        this.pointingSystem = settings.getPointingSystem();
        this.statusField = settings.isStatusField();
        this.useTimer = settings.isUseTimer();
        this.continuousGame = settings.isContinuousGame();
    }

    //----------------------------------------------------------------------

    /**
     * Visszaadja az adatmodult
     */
    public static DataModule getDataModule() {
        if (dataModule == null)
            dataModule = new DataModule();
        return dataModule;
    }

    //----------------------------------------------------------------------

    /**
     * Növeli az időt
     */
    public void increaseTime() {
        if (time < MAX_TIME) // csak eddig növeli az időt
            ++time;
        if (pointingSystem == NORMAL_POINTING_SYSTEM) // normál pontozási rendszer
            if (useTimer) // időre megy a játék
                if (time % 10 == 0)
                    points -= 2;

    }

    //----------------------------------------------------------------------

    /**
     * Az osztályadatok alapján visszaad egy objektumot, mely a beállításokat
     * tartalmazza.
     */
    private Settings getSettings() {
        return new Settings(time,
                points,
                imagePath,
                lookAndFeel,
                pointingSystem,
                statusField,
                useTimer,
                continuousGame);
    }

    //----------------------------------------------------------------------

    /**
     * Kiírja a megadott beállításokat
     */
    public void writeSettings(Object object) {
        ObjectOutputStream os = null;
        try {
            os = new ObjectOutputStream(new FileOutputStream("settings.dat"));
            os.writeObject(object);
            os.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    //----------------------------------------------------------------------

    /**
     * Beolvassa a beállításokat
     */
    public Settings readSettings() {
        ObjectInputStream os = null;
        try {
            os = new ObjectInputStream(new FileInputStream("settings.dat"));
            Settings settings = (Settings) os.readObject();
            os.close();
            return settings;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new Settings();
    }

    //----------------------------------------------------------------------

    /**
     * Beállítja a kép elérési útját, menti a változtatást
     */
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
        writeSettings(getSettings());
    }

    //----------------------------------------------------------------------

    /**
     * Beállítja a kinézetet, menti a változtatást
     */
    public void setLookAndFeel(int lookAndFeel) {
        this.lookAndFeel = lookAndFeel;
        writeSettings(getSettings());
    }

    //----------------------------------------------------------------------

    /**
     * Beállítja és menti a folyamatos pontozás beállítottságát
     */
    public void setContinuousGame(boolean continuousGame) {
        this.continuousGame = continuousGame;
        writeSettings(getSettings());
    }

    //----------------------------------------------------------------------

    /**
     * Beállítja és menti a pontozási rendszert
     */
    public void setPointingSystem(int pointingSystem) {
        this.pointingSystem = pointingSystem;
        writeSettings(getSettings());
    }

    //----------------------------------------------------------------------

    /**
     * Beállítja és menti az állapotsor láthatóságát
     */
    public void setStatusField(boolean statusField) {
        this.statusField = statusField;
        writeSettings(getSettings());
    }

    //----------------------------------------------------------------------

    /**
     * Beállítja és menti az "játék időre" beállítást
     */
    public void setUseTimer(boolean useTimer) {
        this.useTimer = useTimer;
        writeSettings(getSettings());
    }

    //----------------------------------------------------------------------

    public int getLookAndFeel() {
        return lookAndFeel;
    }

    public String getImagePath() {
        return imagePath;
    }

    public int getPoints() {
        return points;
    }

    public int getTime() {
        return time;
    }

    public void setPoints(int points) {
        this.points = points;
        if (this.points > MAX_POINTS) // nem lehet több pontot elérni
            this.points = MAX_POINTS;
        if (this.points < MIN_POINTS) // nem lehet kevesebb pontot elérni
            this.points = MIN_POINTS;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public DraggingManager getDraggingManager() {
        return draggingManager;
    }

    public void setDraggingManager(DraggingManager draggingManager) {
        this.draggingManager = draggingManager;
    }

    public CancelManager getCancelManager() {
        return cancelManager;
    }

    public void setCancelManager(CancelManager cancelManager) {
        this.cancelManager = cancelManager;
    }

    public boolean isContinuousGame() {
        return continuousGame;
    }

    public int getPointingSystem() {
        return pointingSystem;
    }


    public boolean isStatusField() {
        return statusField;
    }

    public boolean isUseTimer() {
        return useTimer;
    }

    public PointManager getPointManager() {
        return pointManager;
    }

    public void setPointManager(PointManager pointManager) {
        this.pointManager = pointManager;
    }

}