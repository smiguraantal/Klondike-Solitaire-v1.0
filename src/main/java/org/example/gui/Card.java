package org.example.gui;

import org.example.entity.Constants;
import org.example.entity.DataModule;
import org.example.gui.listener.CardMouseListener;
import org.example.gui.listener.CardMouseMotionListener;
import org.example.gui.place.Place;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;

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
 * Egy kártyalapot megvalósító osztály
 */
public class Card extends JPanel
        implements Constants, Cloneable {

    /**
     * Adatmodul
     */
    private DataModule dataModule = DataModule.getDataModule();

    /**
     * felfordított-e a lap
     */
    private boolean visibility;

    /**
     * érték 1-től 13-ig
     */
    private int value;

    /**
     * szin 1-től 4-ig (káró, tref, kör, pikk)
     */
    private int color;

    /**
     * a kártya képe
     */
    private Image image;

    /**
     * a lap helye (lefordított talon, felfordított talon, kupac, ház
     */
    private Place place;

    /**
     * A képfájl elérési útja
     */
    private String fileName;

    /**
     * A kártya rétegének száma
     */
    private int layer;

    /**
     * Egéresemény figyelő
     */
    private CardMouseListener cardMouseListener = new CardMouseListener(this);

    /**
     * Egérmozgás figyelő
     */
    private CardMouseMotionListener cardMouseMotionListener =
            new CardMouseMotionListener(this);

    BorderLayout borderLayout1 = new BorderLayout();

    //----------------------------------------------------------------------

    /**
     * Konstruktor
     */
    public Card(String fileName, int value, int color) {
        this.fileName = fileName;
        this.value = value;
        this.color = color;
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //----------------------------------------------------------------------

    private void jbInit() throws Exception {
        this.setLayout(borderLayout1);
        setPreferredSize(new Dimension(CARD_WIDTH, CARD_HEIGTH));
        loadImage();
        addMouseListener(cardMouseListener);
        addMouseMotionListener(cardMouseMotionListener);
    }

    //----------------------------------------------------------------------

    /**
     * Betölti a kártya képét egy fáljból
     */
    private void loadImage() {
        MediaTracker tr = new MediaTracker(this);
        image = Toolkit.getDefaultToolkit().getImage(
                visibility ? fileName : dataModule.getImagePath());
        tr.addImage(image, 0);
        try {
            tr.waitForID(0);
        } catch (InterruptedException e) {
        }
    }

    //----------------------------------------------------------------------

    /**
     * Visszaadja, hogy a lap ász-e
     */
    public boolean isAce() {
        return value == ACE;
    }

    //----------------------------------------------------------------------

    /**
     * Visszaadja, hogy a kapott lap király-e
     */
    public boolean isKing() {
        return value == KING;
    }

    //----------------------------------------------------------------------

    /**
     * Visszaadja, hogy a kapott lap ugyanolyan színű-e
     */
    public boolean sameColor(Card otherCard) {
        return color == otherCard.color;
    }

    //----------------------------------------------------------------------

    /**
     * visszaadja, hogy két lap hasonló színű-e (pl.: piros-pisor)
     * Két szín akkor hasonló ha összegük páros.
     * 0 = káró (piros)
     * 1 = tref (fekete)
     * 2 = kör (piros)
     * 3 = pikk (fekete)
     */
    public boolean similarColor(Card otherCard) {
        return (color + otherCard.color) % 2 == 0;
    }

    //----------------------------------------------------------------------

    /**
     * Törli az egéresemény figyelőket
     */
    public void removeAllListeners() {
        removeMouseListener(cardMouseListener);
        removeMouseMotionListener(cardMouseMotionListener);
    }

    //----------------------------------------------------------------------

    /**
     * Megrajzolja a kártya képét
     */
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) // ha esetleg elvesztek a képek
            g.drawImage(image, 0, 0, this);
        g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
    }

    //----------------------------------------------------------------------

    /**
     * Megváltoztatja a hátlapot
     */
    public void changeBackboard() {
        loadImage();
        repaint();
    }

    //----------------------------------------------------------------------

    /**
     * Klónozható a kártya!
     */
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    //----------------------------------------------------------------------

    /**
     * Továbbadja a helyének az egéreseményt
     */
    public void mousePressed(MouseEvent e) {
        place.getTable().getMainFrame().startTimer();
        place.mousePressed(this, e);
    }

    //----------------------------------------------------------------------

    /**
     * Továbbadja a helyének az egéreseményt
     */
    public void mouseReleased(MouseEvent e) {
        place.mouseReleased(this, e);
    }

    //----------------------------------------------------------------------

    /**
     * Továbbadja a helyének az egéreseményt
     */
    public void mouseClicked(MouseEvent e) {
        place.mouseClicked(e);
    }

    //----------------------------------------------------------------------

    /**
     * Továbbadja a helyének az egéreseményt
     */
    public void mouseDragged(MouseEvent e) {
        place.mouseDragged(this, e);
    }

    //----------------------------------------------------------------------

    /**
     * Láthatóság változtatása után más kép lesz a laphoz rendelve
     */
    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
        loadImage();
        repaint();
    }

    //----------------------------------------------------------------------

    public boolean isVisibility() {
        return visibility;
    }

    public int getColor() {
        return color;
    }

    public Image getImage() {
        return image;
    }

    public int getValue() {
        return value;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public int getLayer() {
        return layer;
    }

    public void setLayer(int layer) {
        this.layer = layer;
    }

    public String getFileName() {
        return fileName;
    }


}

//############################################################################

