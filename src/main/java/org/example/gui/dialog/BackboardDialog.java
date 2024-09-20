package org.example.gui.dialog;

import org.example.entity.Constants;
import org.example.entity.DataModule;
import org.example.entity.Utils;
import org.example.gui.MainFrame;
import org.example.gui.WindowLocation;
import org.example.gui.panel.BackboardPanel;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.Border;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
 * A kártyák hátlapjának változtatásához használt dialógus
 */
public class BackboardDialog extends JDialog implements Constants {

    /**
     * Adatmodul
     */
    private DataModule dataModule = DataModule.getDataModule();

    /**
     * Hátlappanelek
     */
    private Vector backboardPanels = new Vector();

    /**
     * Az éppen kijelölt hátlap panelja
     */
    private BackboardPanel activeBackboardPanel;

    /**
     * A hátlappanelek sorainak száma
     */
    private final int COUNT_OF_ROWS = 2;

    /**
     * A hátlappanelek oszlopainak száma
     */
    private final int COUNT_OF_CLOLUMNS = 6;

    JPanel panel1 = new JPanel();
    GridBagLayout gridBagLayout1 = new GridBagLayout();
    JPanel jPanel1 = new JPanel();
    BorderLayout borderLayout1 = new BorderLayout();
    JPanel jPanel2 = new JPanel();
    JButton btOk = new JButton();
    JButton btCancel = new JButton();
    JPanel pnCenter = new JPanel();
    GridLayout gridLayout1 = new GridLayout();
    Border border1;

    //----------------------------------------------------------------------

    /**
     * Konstruktor
     */
    public BackboardDialog(MainFrame mainFrame) {
        super(mainFrame);
        try {
            jbInit();
            pack();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    //----------------------------------------------------------------------

    private void jbInit() throws Exception {
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        setModal(true);
        border1 = BorderFactory.createEmptyBorder(0, 0, 5, 0);
        setTitle("Hátlap kiválasztása");
        this.addWindowListener(new BackboardDialog_this_windowAdapter(this));
        setResizable(false);
        panel1.setLayout(gridBagLayout1);
        jPanel1.setLayout(borderLayout1);
        btOk.setText("Rendben");
        btOk.addActionListener(new BackboardDialog_btOk_actionAdapter(this));
        btCancel.setText("Mégse");
        btCancel.addActionListener(new BackboardDialog_btCancel_actionAdapter(this));
        pnCenter.setLayout(gridLayout1);
        gridLayout1.setColumns(6);
        gridLayout1.setHgap(0);
        gridLayout1.setRows(2);
        pnCenter.setBorder(border1);
        getContentPane().add(panel1);
        panel1.add(jPanel1, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0
                , GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(5, 5, 0, 5), 0, 0));
        jPanel1.add(jPanel2, BorderLayout.SOUTH);
        jPanel2.add(btOk, null);
        jPanel2.add(btCancel, null);
        jPanel1.add(pnCenter, BorderLayout.CENTER);

        btOk.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                keyListening(e);
            }
        });

        btCancel.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                keyListening(e);
            }
        });

        initBackboardPanels();
    }

    //----------------------------------------------------------------------

    /**
     * Escapa-re a dialógus eltűnik, Enterre a hátlap kiválasztásra kerül
     */
    private void keyListening(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
            hide();
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            dataModule.setImagePath(activeBackboardPanel.getFileName());
            hide();
        }
        manualSetting(e);
    }

    //----------------------------------------------------------------------

    /**
     * Felépíti a hátlappanelokat
     */
    private void initBackboardPanels() {
        int number = 0;
        for (int i = 1; i <= COUNT_OF_ROWS; i++) {
            for (int j = 1; j <= COUNT_OF_CLOLUMNS; j++) {
                String fileName = // hátlap elérisé útjának összeállítása
                        "images/back_" +
                                Utils.format(++number) +
                                ".png";
                BackboardPanel backboardPanel = new BackboardPanel(this, fileName, i, j);
                backboardPanels.add(backboardPanel);
                pnCenter.add(backboardPanel);
            }
        }
    }

    //----------------------------------------------------------------------

    /**
     * Meghatározza hogy melyik hátlap legyen kijelölve
     */
    public void setActiveBackboardPanel(String fileName) {
        for (int i = 0; i < backboardPanels.size(); i++) { // végig a hátlapokon
            BackboardPanel backboardPanel = (BackboardPanel) backboardPanels.get(i);
            if (backboardPanel.getFileName().equals(fileName)) { // ha ugyanaz az elérési út
                backboardPanel.setBackground(Color.darkGray);
                activeBackboardPanel = backboardPanel;
            } else
                backboardPanel.setBackground((Color) UIManager.get("Panel.background"));
        }
    }

    //----------------------------------------------------------------------

    /**
     * Megjeleníti a dialógust
     */
    public void showDialog() {
        setActiveBackboardPanel(dataModule.getImagePath());
        pack();
        WindowLocation.center(this);
        show();
    }

    //----------------------------------------------------------------------

    /**
     * Rendben gomb lenyomása
     */
    void btOk_actionPerformed(ActionEvent e) {
        dataModule.setImagePath(activeBackboardPanel.getFileName());
        hide();
    }

    //----------------------------------------------------------------------

    /**
     * Mégse gomb lenyomása
     */
    void btCancel_actionPerformed(ActionEvent e) {
        hide();
    }

    //----------------------------------------------------------------------

    /**
     * Ablakbecsukás
     */
    void this_windowClosing(WindowEvent e) {
        hide();
    }

    //----------------------------------------------------------------------

    public BackboardPanel getActiveBackboardPanel() {
        return activeBackboardPanel;
    }

    //----------------------------------------------------------------------

    /**
     * Visszaadja, hogy a kapott sor és oszlopindex belül van-e a gridpanelon
     */
    private boolean within(int row, int column) {
        return row > 0
                && row <= COUNT_OF_ROWS
                && column > 0
                && column <= COUNT_OF_CLOLUMNS;
    }

    //----------------------------------------------------------------------

    /**
     * Lekezeli a nyílgombok leütését, és beállítja a kijelölt hátteret
     */
    private void manualSetting(KeyEvent e) {
        int row = activeBackboardPanel.getRow();
        int column = activeBackboardPanel.getColumn();
        if (e.getKeyCode() == KeyEvent.VK_UP)
            --row;
        if (e.getKeyCode() == KeyEvent.VK_DOWN)
            ++row;
        if (e.getKeyCode() == KeyEvent.VK_LEFT)
            --column;
        if (e.getKeyCode() == KeyEvent.VK_RIGHT)
            ++column;
        if (within(row, column)) { // ha még belül vannak az indexek
            for (int i = 0; i < backboardPanels.size(); i++) { // végig a hátlapokon
                BackboardPanel backboardPanel = (BackboardPanel) backboardPanels.get(i);
                if (row == backboardPanel.getRow() && column == backboardPanel.getColumn()) { // ha azonos a sor és oszlopindex, akkor keretet kap
                    backboardPanel.setBackground(Color.darkGray);
                    activeBackboardPanel = backboardPanel;
                } else
                    backboardPanel.setBackground((Color) UIManager.get("Panel.background"));
            }
        }
    }

}

//############################################################################

class BackboardDialog_btOk_actionAdapter implements ActionListener {
    BackboardDialog adaptee;

    BackboardDialog_btOk_actionAdapter(BackboardDialog adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.btOk_actionPerformed(e);
    }
}

class BackboardDialog_btCancel_actionAdapter implements ActionListener {
    BackboardDialog adaptee;

    BackboardDialog_btCancel_actionAdapter(BackboardDialog adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.btCancel_actionPerformed(e);
    }
}

class BackboardDialog_this_windowAdapter extends WindowAdapter {
    BackboardDialog adaptee;

    BackboardDialog_this_windowAdapter(BackboardDialog adaptee) {
        this.adaptee = adaptee;
    }

    public void windowClosing(WindowEvent e) {
        adaptee.this_windowClosing(e);
    }
}


