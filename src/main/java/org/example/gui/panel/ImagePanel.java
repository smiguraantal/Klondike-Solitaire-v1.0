package org.example.gui.panel;

import org.example.entity.Constants;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;

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
 * A kártya hátlapját kirajozoló képpanel
 */
public class ImagePanel extends JPanel implements Constants {

    /**
     * A képfájl elérési útja
     */
    private String fileName;

    /**
     * a kártya képe
     */
    private Image image;

    BorderLayout borderLayout1 = new BorderLayout();

    //----------------------------------------------------------------------

    /**
     * Konstruktor
     */
    public ImagePanel(String fileName) {
        this.fileName = fileName;
        try {
            jbInit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    //----------------------------------------------------------------------

    void jbInit() throws Exception {
        this.setLayout(borderLayout1);
        setPreferredSize(new Dimension(CARD_WIDTH + 6, CARD_HEIGTH + 6));
        loadImage();
    }

    //----------------------------------------------------------------------

    /**
     * Betölti a kártya képét egy fáljból
     */
    private void loadImage() {
        MediaTracker tr = new MediaTracker(this);
        image = Toolkit.getDefaultToolkit().getImage(fileName);
        tr.addImage(image, 0);
        try {
            tr.waitForID(0);
        } catch (InterruptedException e) {
        }
    }

    //----------------------------------------------------------------------

    /**
     * Megrajzolja a kártya képét
     */
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) // ha esetleg elvesztek a képek
            g.drawImage(image, 3, 3, this);
        g.drawRect(3, 3, getWidth() - 7, getHeight() - 7);
    }

    //----------------------------------------------------------------------

    public String getFileName() {
        return fileName;
    }
}






