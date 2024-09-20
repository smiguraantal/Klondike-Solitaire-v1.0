package org.example.gui.panel;

import org.example.entity.Constants;
import org.example.gui.dialog.BackboardDialog;

import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
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
 * Egy a kártya hátlapját reprezentáló képet "tartó" panel
 */
public class BackboardPanel extends JPanel implements Constants {

    /**
     * A hátlap beállításához használt dialógus
     */
    private BackboardDialog backboardDialog;

    /**
     * A hátlap képének elérési útja
     */
    private String fileName;

    /**
     * A képpanel
     */
    private ImagePanel imagePanel;

    /**
     * A panel sorindexe a dialógusablak gridLayout menedzserénél
     */
    private int row;

    /**
     * A panel oszlopindexe a dialógusablak gridLayout menedzserénél
     */
    private int column;

    GridBagLayout gridBagLayout1 = new GridBagLayout();

    //----------------------------------------------------------------------

    /**
     * Konstruktor
     */
    public BackboardPanel(BackboardDialog backboardDialog,
                          String fileName,
                          int row,
                          int column) {
        this.backboardDialog = backboardDialog;
        this.fileName = fileName;
        this.row = row;
        this.column = column;
        try {
            jbInit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    //----------------------------------------------------------------------

    /**
     * Konsturktor a hasonlítóobjektum készítéséhez
     */
    public BackboardPanel(BackboardDialog backboardDialog,
                          String fileName) {
        this(backboardDialog, fileName, 0, 0);
    }

    //----------------------------------------------------------------------

    void jbInit() throws Exception {
        imagePanel = new ImagePanel(fileName);
        this.setLayout(gridBagLayout1);
        setPreferredSize(new Dimension(CARD_WIDTH + 12, CARD_HEIGTH + 12));
        this.add(imagePanel, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0
                , GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(3, 3, 3, 3), 0, 0));

        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                backboardDialog.setActiveBackboardPanel(fileName);
            }
        });
    }

    //----------------------------------------------------------------------

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public String getFileName() {
        return fileName;
    }
}


