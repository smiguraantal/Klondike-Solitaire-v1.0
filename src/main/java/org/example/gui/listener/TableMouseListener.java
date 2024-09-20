package org.example.gui.listener;

import org.example.gui.Table;

import java.awt.event.MouseAdapter;
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
 * Az asztal egéreseményeit figyelő osztály
 */
public class TableMouseListener extends MouseAdapter {

    /**
     * Ez a tábla lesz figyelve
     */
    private Table table;

    //----------------------------------------------------------------------

    /**
     * Konstruktor
     */
    public TableMouseListener(Table table) {
        this.table = table;
    }

    //----------------------------------------------------------------------

    /**
     * Egérgomb lenyomása
     */
    public void mousePressed(MouseEvent e) {
        table.mousePressed(e);
    }

}