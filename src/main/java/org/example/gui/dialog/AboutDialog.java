package org.example.gui.dialog;

import com.borland.jbcl.layout.VerticalFlowLayout;
import com.borland.jbcl.layout.XYConstraints;
import com.borland.jbcl.layout.XYLayout;
import org.example.gui.MainFrame;
import org.example.gui.WindowLocation;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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
 * A program keletkezésével kapcsolatos információkat megjelenítő dialógus.
 */
public class AboutDialog extends JDialog {

    /**
     * A megjelenő szöveg betűtípusa
     */
    private Font font = new Font("Times New Roman", Font.PLAIN, 15);

    /**
     * Időzítő a vonalak animálásához
     */
    private Timer lineTimer = new Timer(0, null);

    /**
     * Időzítő az információ animálásához
     */
    private Timer infoTimer = new Timer(0, null);

    /**
     * A megjelenítendő sor száma
     */
    private int infoRow;

    /**
     * A kiírandó információk
     */
    private String gameName = "Pasziánsz";
    private String version = "Verziószám: 1.0";
    private String copyright = "Copyright © Smigura Antal";
    private String date = "Budapest, 2004";

    Border border1;
    Border border2;
    Border border3;
    Border border4;
    Border border5;
    Border border6;
    Border border7;
    Border border8;
    JPanel jPanel1 = new JPanel();
    GridBagLayout gridBagLayout1 = new GridBagLayout();
    JPanel jPanel2 = new JPanel();
    BorderLayout borderLayout1 = new BorderLayout();
    JPanel jPanel3 = new JPanel();
    JPanel jPanel4 = new JPanel();
    JPanel jPanel5 = new JPanel();
    BorderLayout borderLayout2 = new BorderLayout();
    JPanel jPanel6 = new JPanel();
    JPanel jPanel7 = new JPanel();
    VerticalFlowLayout verticalFlowLayout1 = new VerticalFlowLayout();
    JPanel jPanel8 = new JPanel();
    JLabel lbGameName = new JLabel();
    JPanel jPanel10 = new JPanel();
    JLabel lbCopyright = new JLabel();
    JPanel jPanel11 = new JPanel();
    JLabel lbDate = new JLabel();
    XYLayout xYLayout1 = new XYLayout();
    JPanel line04 = new JPanel();
    Border border9;
    JPanel line05 = new JPanel();
    Border border10;
    JPanel line06 = new JPanel();
    Border border11;
    JPanel line01 = new JPanel();
    Border border12;
    JPanel line02 = new JPanel();
    Border border13;
    JPanel line03 = new JPanel();
    Border border14;
    XYLayout xYLayout2 = new XYLayout();
    JPanel line07 = new JPanel();
    Border border15;
    JPanel line08 = new JPanel();
    Border border16;
    JPanel line09 = new JPanel();
    Border border17;
    JPanel line10 = new JPanel();
    Border border18;
    JPanel line11 = new JPanel();
    Border border19;
    JPanel line12 = new JPanel();
    Border border20;
    GridBagLayout gridBagLayout2 = new GridBagLayout();
    JButton btClose = new JButton();
    JPanel jPanel21 = new JPanel();
    JLabel lbVersion = new JLabel();
    Border border21;
    Border border22;
    Border border23;

    //----------------------------------------------------------------------

    /**
     * Konstrutktor
     */
    public AboutDialog(MainFrame mainFrame) {
        super(mainFrame, "Névjegy", true);
        try {
            jbInit();
            pack();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    //----------------------------------------------------------------------

    private void jbInit() throws Exception {
        border21 = BorderFactory.createCompoundBorder(BorderFactory.createEtchedBorder(), BorderFactory.createEmptyBorder(5, 15, 5, 15));
        border22 = BorderFactory.createEmptyBorder(0, 0, 5, 0);
        border23 = BorderFactory.createEmptyBorder();
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        setResizable(false);
        border1 = BorderFactory.createBevelBorder(BevelBorder.RAISED);
        border2 = BorderFactory.createBevelBorder(BevelBorder.RAISED);
        border3 = BorderFactory.createBevelBorder(BevelBorder.RAISED);
        border4 = BorderFactory.createBevelBorder(BevelBorder.RAISED);
        border5 = BorderFactory.createBevelBorder(BevelBorder.RAISED);
        border6 = BorderFactory.createBevelBorder(BevelBorder.RAISED);
        border7 = BorderFactory.createBevelBorder(BevelBorder.RAISED);
        border8 = BorderFactory.createBevelBorder(BevelBorder.RAISED);
        border9 = BorderFactory.createBevelBorder(BevelBorder.RAISED);
        border10 = BorderFactory.createBevelBorder(BevelBorder.RAISED);
        border11 = BorderFactory.createBevelBorder(BevelBorder.RAISED);
        border12 = BorderFactory.createBevelBorder(BevelBorder.RAISED);
        border13 = BorderFactory.createBevelBorder(BevelBorder.RAISED);
        border14 = BorderFactory.createBevelBorder(BevelBorder.RAISED);
        border15 = BorderFactory.createBevelBorder(BevelBorder.RAISED);
        border16 = BorderFactory.createBevelBorder(BevelBorder.RAISED);
        border17 = BorderFactory.createBevelBorder(BevelBorder.RAISED);
        border18 = BorderFactory.createBevelBorder(BevelBorder.RAISED);
        border19 = BorderFactory.createBevelBorder(BevelBorder.RAISED);
        border20 = BorderFactory.createBevelBorder(BevelBorder.RAISED);
        jPanel1.setLayout(gridBagLayout1);
        jPanel2.setLayout(borderLayout1);
        jPanel4.setLayout(borderLayout2);
        jPanel7.setLayout(verticalFlowLayout1);

        lbGameName.setFont(new Font("Serif", Font.PLAIN, 18));
        lbGameName.setForeground(Color.darkGray);
        lbGameName.setBorder(border22);
        lbCopyright.setFont(font);
        lbDate.setFont(font);
        lbDate.setBorder(border23);
        lbVersion.setFont(font);

        lbGameName.setText(gameName);
        lbVersion.setText(version);
        lbCopyright.setText(copyright);
        lbDate.setText(date);

        jPanel5.setLayout(xYLayout1);
        jPanel5.setPreferredSize(new Dimension(200, 25));
        line04.setBorder(border9);
        line04.setPreferredSize(new Dimension(3, 0));
        line05.setBorder(border10);
        line05.setPreferredSize(new Dimension(3, 0));
        line06.setBorder(border11);
        line06.setPreferredSize(new Dimension(3, 0));
        line01.setBorder(border12);
        line01.setPreferredSize(new Dimension(0, 3));
        line02.setBorder(border13);
        line02.setPreferredSize(new Dimension(0, 3));
        line03.setBorder(border14);
        line03.setPreferredSize(new Dimension(0, 3));
        jPanel6.setLayout(xYLayout2);
        jPanel6.setMinimumSize(new Dimension(200, 39));
        jPanel6.setPreferredSize(new Dimension(200, 25));
        line07.setBorder(border15);
        line07.setOpaque(false);
        line07.setPreferredSize(new Dimension(0, 3));
        line08.setBorder(border16);
        line08.setPreferredSize(new Dimension(0, 3));
        line09.setBorder(border17);
        line09.setPreferredSize(new Dimension(0, 3));
        line10.setBorder(border18);
        line10.setPreferredSize(new Dimension(3, 0));
        line11.setBorder(border19);
        line11.setPreferredSize(new Dimension(3, 0));
        line12.setBorder(border20);
        line12.setPreferredSize(new Dimension(3, 0));
        jPanel3.setLayout(gridBagLayout2);
        btClose.setText("Bezárás");
        btClose.addActionListener(new About_btClose_actionAdapter(this));
        jPanel7.setPreferredSize(new Dimension(200, 148));
        jPanel10.setMinimumSize(new Dimension(200, 28));
        jPanel4.setPreferredSize(new Dimension(200, 198));
        this.getContentPane().add(jPanel1, BorderLayout.CENTER);
        jPanel1.add(jPanel2, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0
                , GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(10, 5, 0, 5), 0, 0));
        jPanel2.add(jPanel3, BorderLayout.SOUTH);
        jPanel3.add(btClose, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
                , GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(10, 0, 5, 0), 0, 0));
        jPanel2.add(jPanel4, BorderLayout.CENTER);
        jPanel4.add(jPanel5, BorderLayout.NORTH);

        jPanel5.add(line01, new XYConstraints(0, 3, -1, -1));
        jPanel5.add(line02, new XYConstraints(0, 8, -1, -1));
        jPanel5.add(line03, new XYConstraints(0, 13, -1, -1));
        jPanel5.add(line04, new XYConstraints(3, 0, -1, -1));
        jPanel5.add(line05, new XYConstraints(8, 0, -1, -1));
        jPanel5.add(line06, new XYConstraints(13, 0, -1, -1));

        jPanel4.add(jPanel6, BorderLayout.SOUTH);
        jPanel6.add(line07, new XYConstraints(200, 19, -1, -1));
        jPanel6.add(line08, new XYConstraints(200, 14, -1, -1));
        jPanel6.add(line09, new XYConstraints(200, 9, -1, -1));
        jPanel6.add(line10, new XYConstraints(194, 25, -1, -1));
        jPanel6.add(line11, new XYConstraints(189, 25, -1, -1));
        jPanel6.add(line12, new XYConstraints(184, 25, -1, -1));
        jPanel4.add(jPanel7, BorderLayout.CENTER);
        jPanel7.add(jPanel8, null);
        jPanel8.add(lbGameName, null);
        jPanel7.add(jPanel21, null);
        jPanel21.add(lbVersion, null);
        jPanel7.add(jPanel10, null);
        jPanel10.add(lbCopyright, null);
        jPanel7.add(jPanel11, null);
        jPanel11.add(lbDate, null);

        btClose.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                keyListening(e);
            }
        });

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                lineTimer.stop();
                infoTimer.stop();
                hide();
            }
        });
    }

    //----------------------------------------------------------------------

    /**
     * Escape-re a dialógus eltűnik.
     */
    private void keyListening(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
            lineTimer.stop();
        infoTimer.stop();
        hide();
    }

    //----------------------------------------------------------------------

    /**
     * Beállítja a vonalak rajzolásához használt időzítőt
     */
    private void initLineTimer() {
        lineTimer = (new Timer(10, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                drawLines();
            }
        }));
        lineTimer.setInitialDelay(500);
        lineTimer.start();
    }

    //----------------------------------------------------------------------

    /**
     * Meghúzza a vonalakat
     */
    private void drawLines() {
        // felső vízszintesek
        if (line01.getWidth() < 200) {
            line01.setSize(new Dimension(line01.getWidth() + 1, line01.getHeight()));
        }
        if (line02.getWidth() < 150) {
            line02.setSize(new Dimension(line02.getWidth() + 1, line02.getHeight()));
        }
        if (line03.getWidth() < 100) {
            line03.setSize(new Dimension(line03.getWidth() + 1, line03.getHeight()));
        }
        // alsó vízszintesek
        if (line04.getHeight() < 25) {
            line04.setSize(new Dimension(line04.getWidth(), line04.getHeight() + 1));
        }
        if (line05.getHeight() < 22) {
            line05.setSize(new Dimension(line05.getWidth(), line05.getHeight() + 1));
        }
        if (line06.getHeight() < 19) {
            line06.setSize(new Dimension(line06.getWidth(), line06.getHeight() + 1));
        }
        // felső függőlegesek
        if (line07.getWidth() < 200) {
            line07.setSize(new Dimension(line07.getWidth() + 1, line07.getHeight()));
            line07.setLocation(line07.getX() - 1, line07.getY());
        }
        if (line08.getWidth() < 150) {
            line08.setSize(new Dimension(line08.getWidth() + 1, line08.getHeight()));
            line08.setLocation(line08.getX() - 1, line08.getY());
        }
        if (line09.getWidth() < 100) {
            line09.setSize(new Dimension(line09.getWidth() + 1, line09.getHeight()));
            line09.setLocation(line09.getX() - 1, line09.getY());
        }
        // alsó függőlegesek
        if (line10.getHeight() < 25) {
            line10.setSize(new Dimension(line10.getWidth(), line10.getHeight() + 1));
            line10.setLocation(line10.getX(), line10.getY() - 1);
        }
        if (line11.getHeight() < 22) {
            line11.setSize(new Dimension(line11.getWidth(), line11.getHeight() + 1));
            line11.setLocation(line11.getX(), line11.getY() - 1);
        }
        if (line12.getHeight() < 19) {
            line12.setSize(new Dimension(line12.getWidth(), line12.getHeight() + 1));
            line12.setLocation(line12.getX(), line12.getY() - 1);
        }
        if (line01.getWidth() == 200) {
            lineTimer.stop();
            initInfoTimer();
        }
    }

    //----------------------------------------------------------------------

    /**
     * Beállítja az info kiírásához használt időzítőt
     */
    private void initInfoTimer() {
        infoRow = 0;
        infoTimer = (new Timer(100, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (infoRow == 0) {
                    lbGameName.setForeground(Color.darkGray);
                }
                if (infoRow == 1) {
                    lbVersion.setForeground(Color.black);
                }
                if (infoRow == 2) {
                    lbCopyright.setForeground(Color.black);
                }
                if (infoRow == 3) {
                    lbDate.setForeground(Color.black);
                    infoTimer.stop();
                }
                ++infoRow;
            }
        }));
        infoTimer.start();
    }

    //----------------------------------------------------------------------

    /**
     * Megjeleníti a dialógust.
     */
    public void showDialog() {
        pack();
        WindowLocation.center(this);
        hideInformation();
        infoTimer.stop();
        lineTimer.stop();
        initLineTimer();
        show();
    }

//----------------------------------------------------------------------

    /**
     * Elrejti a szöveget
     */
    private void hideInformation() {
        Color panelBackground = (Color) UIManager.get("Panel.background");
        lbGameName.setForeground(panelBackground);
        lbVersion.setForeground(panelBackground);
        lbCopyright.setForeground(panelBackground);
        lbDate.setForeground(panelBackground);
    }

    //----------------------------------------------------------------------

    /**
     * Elrejti a dialógust.
     */
    void btClose_actionPerformed(ActionEvent e) {
        lineTimer.stop();
        infoTimer.stop();
        hide();
    }
}

//############################################################################

class About_btClose_actionAdapter implements ActionListener {
    AboutDialog adaptee;

    About_btClose_actionAdapter(AboutDialog adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.btClose_actionPerformed(e);
    }
}