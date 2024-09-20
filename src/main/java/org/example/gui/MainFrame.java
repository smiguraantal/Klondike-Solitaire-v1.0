package org.example.gui;

import com.sun.java.swing.plaf.motif.MotifLookAndFeel;
import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;
import org.example.entity.Constants;
import org.example.entity.DataModule;
import org.example.entity.PointFormat;
import org.example.gui.dialog.AboutDialog;
import org.example.gui.dialog.BackboardDialog;
import org.example.gui.dialog.HowToUseDialog;
import org.example.gui.manager.MessageManager;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.plaf.metal.MetalLookAndFeel;
import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
 * Az alkalmazás főablaka
 */
public class MainFrame extends JFrame implements Constants {

    /**
     * Adatmodul
     */
    private DataModule dataModule = DataModule.getDataModule();

    /**
     * Az asztal
     */
    private Table table = new Table(this);

    /**
     * Az időmérő
     */
    private Timer timer = new Timer(1000, null);

    /**
     * A hátlapok beállításához használt dialógusablak
     */
    private BackboardDialog backboardDialog = new BackboardDialog(this);

    /**
     * A használat dialógus
     */
    private HowToUseDialog howToUseDialog = new HowToUseDialog(this);

    /**
     * Névjegy dialógus
     */
    private AboutDialog aboutDialog = new AboutDialog(this);

    JPanel contentPane;
    BorderLayout borderLayout1 = new BorderLayout();
    ButtonGroup bgPointingSystem = new ButtonGroup();
    ButtonGroup bgLookAndFeel = new ButtonGroup();


    JMenuBar jMenuBar1 = new JMenuBar();
    JMenu mGame = new JMenu();
    JMenu mSettings = new JMenu();
    JMenuItem miExit = new JMenuItem();
    JMenuItem miNew = new JMenuItem();
    JMenu mHelp = new JMenu();
    JMenuItem miUse = new JMenuItem();
    JMenuItem miAbout = new JMenuItem();
    JMenuItem miBackboard = new JMenuItem();
    JMenuItem miUndo = new JMenuItem();
    JPanel statusPanel = new JPanel();
    BorderLayout borderLayout2 = new BorderLayout();
    JPanel jPanel3 = new JPanel();
    JMenu miPointingSystem = new JMenu();
    JMenu miLookAndFeel = new JMenu();
    JRadioButtonMenuItem miNormal = new JRadioButtonMenuItem();
    JRadioButtonMenuItem miVegas = new JRadioButtonMenuItem();
    JRadioButtonMenuItem miNone = new JRadioButtonMenuItem();
    JRadioButtonMenuItem miWindows = new JRadioButtonMenuItem();
    JRadioButtonMenuItem miMetal = new JRadioButtonMenuItem();
    JRadioButtonMenuItem miMotif = new JRadioButtonMenuItem();
    JCheckBoxMenuItem miContinuous = new JCheckBoxMenuItem();
    JCheckBoxMenuItem miUseTimer = new JCheckBoxMenuItem();
    JCheckBoxMenuItem miStatus = new JCheckBoxMenuItem();
    Border border1;
    JPanel pointsPanel = new JPanel();
    JLabel lbPoints = new JLabel();
    JLabel jLabel3 = new JLabel();
    JPanel timePanel = new JPanel();
    JLabel lbTime = new JLabel();
    JLabel jLabel5 = new JLabel();
    Border border2;
    Border border3;
    Border border4;
    Border border5;
    Border border6;
    Border border7;
    Border border8;
    JPanel jPanel2 = new JPanel();
    JPanel jPanel5 = new JPanel();
    JLabel lbStatus = new JLabel();
    FlowLayout flowLayout1 = new FlowLayout();
    Border border9;
    GridBagLayout gridBagLayout1 = new GridBagLayout();
    FlowLayout flowLayout2 = new FlowLayout();

    //----------------------------------------------------------------------

    /**
     * Konstruktor
     */
    public MainFrame() {
        enableEvents(AWTEvent.WINDOW_EVENT_MASK);
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //----------------------------------------------------------------------

    private void jbInit() throws Exception {
        border1 = BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.white, Color.white, new Color(103, 101, 98), new Color(148, 145, 140));
        border2 = BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.white, Color.white, new Color(99, 99, 99), new Color(142, 142, 142));
        border3 = BorderFactory.createEmptyBorder();
        border4 = BorderFactory.createCompoundBorder(border3, border2);
        border5 = BorderFactory.createEmptyBorder();
        border6 = BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(), border2);
        border7 = BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.white, Color.white, new Color(99, 99, 99), new Color(142, 142, 142));
        border8 = BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.white, Color.white, new Color(99, 99, 99), new Color(142, 142, 142));
        border9 = BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.white, Color.white, new Color(99, 99, 99), new Color(142, 142, 142));
        setResizable(false);
        contentPane = (JPanel) this.getContentPane();
        contentPane.setLayout(borderLayout1);
        this.setTitle("Pasziánsz 1.0");
        this.addWindowListener(new MainFrame_this_windowAdapter(this));
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setJMenuBar(jMenuBar1);
        mGame.setMnemonic('J');
        mGame.setText("Játék");
        mSettings.setMnemonic('B');
        mSettings.setText("Beállítások");
        miExit.setActionCommand("A Pasziánszból való kilépés");
        miExit.setMnemonic('0');
        miExit.setText("Kilépés");
        miExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, Event.ALT_MASK));
        miExit.addActionListener(new MainFrame_miExit_actionAdapter(this));
        miNew.setActionCommand("Leosztás egy új játszmához");
        miNew.setText("Új leosztás");
        miNew.setAccelerator(KeyStroke.getKeyStroke("F2"));
        miNew.addActionListener(new MainFrame_miNew_actionAdapter(this));
        mHelp.setMnemonic('S');
        mHelp.setText("Súgó");
        miUse.setActionCommand("Használati útmutató");
        miUse.setText("Használat");
        miUse.setAccelerator(KeyStroke.getKeyStroke("F1"));
        miUse.addActionListener(new MainFrame_miUse_actionAdapter(this));
        miAbout.setActionCommand("Névjegy megjelenítése");
        miAbout.setMnemonic('N');
        miAbout.setText("Névjegy");
        miAbout.setAccelerator(KeyStroke.getKeyStroke('N', KeyEvent.CTRL_MASK, false));
        miAbout.addActionListener(new MainFrame_miAbout_actionAdapter(this));
        miBackboard.setActionCommand("Új hátlap kiválasztása");
        miBackboard.setMnemonic('H');
        miBackboard.setText("Hátlap");
        miBackboard.setAccelerator(KeyStroke.getKeyStroke('H', KeyEvent.CTRL_MASK | KeyEvent.ALT_MASK, false));
        miBackboard.addActionListener(new MainFrame_miBackboard_actionAdapter(this));
        miUndo.setMnemonic('Z');
        miUndo.setText("Visszavonás");
        miUndo.setAccelerator(KeyStroke.getKeyStroke('Z', KeyEvent.CTRL_MASK, false));
        miUndo.addActionListener(new MainFrame_miUndo_actionAdapter(this));
        miUndo.setEnabled(false);
        miUndo.setActionCommand("Utolsó művelet visszavonása");
        statusPanel.setLayout(borderLayout2);
        miPointingSystem.setActionCommand("Pontozási rendszer kiválasztása");
        miPointingSystem.setMnemonic('P');
        miPointingSystem.setText("Pontozási rendszer");
        miLookAndFeel.setActionCommand("Felület kiválasztása");
        miLookAndFeel.setMnemonic('E');
        miLookAndFeel.setText("Felület");
        miNormal.setAccelerator(KeyStroke.getKeyStroke('N', KeyEvent.CTRL_MASK | KeyEvent.ALT_MASK, false));
        miNormal.setActionCommand("Normál pontozási rendszer");
        miNormal.setMnemonic('N');
        miNormal.setText("Normál");
        miNormal.addActionListener(new MainFrame_miNormal_actionAdapter(this));
        miVegas.setAccelerator(KeyStroke.getKeyStroke('V', KeyEvent.CTRL_MASK | KeyEvent.ALT_MASK, false));
        miVegas.setActionCommand("Vegas pontozási rendszer");
        miVegas.setMnemonic('V');
        miVegas.setText("Vegas");
        miVegas.addActionListener(new MainFrame_miVegas_actionAdapter(this));
        miNone.setAccelerator(KeyStroke.getKeyStroke('S', KeyEvent.CTRL_MASK | KeyEvent.ALT_MASK, false));
        miNone.setActionCommand("Pontozás nélküli játék");
        miNone.setMnemonic('S');
        miNone.setText("Nincs");
        miNone.addActionListener(new MainFrame_miNone_actionAdapter(this));
        miWindows.setAccelerator(KeyStroke.getKeyStroke('W', KeyEvent.CTRL_MASK | KeyEvent.ALT_MASK, false));
        miWindows.setActionCommand("Windows-os felület");
        miWindows.setMnemonic('W');
        miWindows.setText("Windows");
        miWindows.addActionListener(new MainFrame_miWindows_actionAdapter(this));
        miMetal.setAccelerator(KeyStroke.getKeyStroke('M', KeyEvent.CTRL_MASK | KeyEvent.ALT_MASK, false));
        miMetal.setActionCommand("Metal-os felület");
        miMetal.setMnemonic('M');
        miMetal.setText("Metal");
        miMetal.addActionListener(new MainFrame_miMetal_actionAdapter(this));
        miMotif.setAccelerator(KeyStroke.getKeyStroke('C', KeyEvent.CTRL_MASK | KeyEvent.ALT_MASK, false));
        miMotif.setActionCommand("Motif-os felület");
        miMotif.setMnemonic('C');
        miMotif.setText("CDE/Motif");
        miMotif.addActionListener(new MainFrame_miMotif_actionAdapter(this));
        miContinuous.setAction(null);
        miContinuous.setActionCommand("Folyamatos pontozás");
        miContinuous.setMnemonic('F');
        miContinuous.setText("Folyamatos pontozás");
        miContinuous.setAccelerator(KeyStroke.getKeyStroke('F', KeyEvent.CTRL_MASK | KeyEvent.ALT_MASK, false));
        miContinuous.addActionListener(new MainFrame_miContinuous_actionAdapter(this));
        miUseTimer.setAlignmentY((float) 0.5);
        miUseTimer.setActionCommand("Játék időre");
        miUseTimer.setMnemonic('J');
        miUseTimer.setText("Játék időre");
        miUseTimer.setAccelerator(KeyStroke.getKeyStroke('J', KeyEvent.CTRL_MASK | KeyEvent.ALT_MASK, false));
        miUseTimer.addActionListener(new MainFrame_miUseTimer_actionAdapter(this));
        miStatus.setActionCommand("Állapotsor megjelenítése/elrejtése");
        miStatus.setMnemonic('A');
        miStatus.setText("Állapotsor");
        miStatus.setAccelerator(KeyStroke.getKeyStroke('A', KeyEvent.CTRL_MASK | KeyEvent.ALT_MASK, false));
        miStatus.addActionListener(new MainFrame_miStatus_actionAdapter(this));
        jMenuBar1.setBorder(null);
        jMenuBar1.setDoubleBuffered(false);
        jMenuBar1.setBorderPainted(true);
        lbPoints.setPreferredSize(new Dimension(50, 16));
        lbPoints.setHorizontalAlignment(SwingConstants.RIGHT);
        lbPoints.setText("0");
        jLabel3.setText("Eredmény:");
        lbTime.setPreferredSize(new Dimension(50, 16));
        lbTime.setHorizontalAlignment(SwingConstants.RIGHT);
        lbTime.setText("0");
        jLabel5.setText("Idő:");
        timePanel.setBorder(border2);
        pointsPanel.setBorder(border4);
        jPanel5.setLayout(flowLayout1);
        jPanel5.setBorder(border9);
        jPanel2.setLayout(gridBagLayout1);
        jPanel3.setLayout(flowLayout2);
        flowLayout2.setHgap(0);
        flowLayout2.setVgap(0);
        flowLayout1.setAlignment(FlowLayout.LEFT);
        contentPane.add(table);
        contentPane.add(statusPanel, BorderLayout.SOUTH);
        statusPanel.add(jPanel3, BorderLayout.EAST);
        jPanel3.add(timePanel, null);
        timePanel.add(jLabel5, null);
        timePanel.add(lbTime, null);
        jPanel3.add(pointsPanel, null);
        pointsPanel.add(jLabel3, null);
        pointsPanel.add(lbPoints, null);
        jMenuBar1.add(mGame);
        jMenuBar1.add(mSettings);
        jMenuBar1.add(mHelp);
        mGame.add(miNew);
        mGame.add(miUndo);
        mGame.addSeparator();
        mGame.add(miExit);
        mSettings.add(miPointingSystem);
        mSettings.add(miContinuous);
        mSettings.add(miUseTimer);
        mSettings.addSeparator();
        mSettings.add(miStatus);
        mSettings.add(miBackboard);
        mSettings.add(miLookAndFeel);
        mHelp.add(miUse);
        mHelp.add(miAbout);
        miPointingSystem.add(miNormal);
        miPointingSystem.add(miVegas);
        miPointingSystem.add(miNone);
        miLookAndFeel.add(miWindows);
        miLookAndFeel.add(miMetal);
        miLookAndFeel.add(miMotif);

        bgPointingSystem.add(miNormal);
        bgPointingSystem.add(miVegas);
        bgPointingSystem.add(miNone);

        bgLookAndFeel.add(miWindows);
        bgLookAndFeel.add(miMetal);
        bgLookAndFeel.add(miMotif);
        jPanel2.add(jPanel5, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0
                , GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
        jPanel5.add(lbStatus, null);
        statusPanel.add(jPanel2, BorderLayout.CENTER);
        lbStatus.setText(" ");

        setComponents();
        createTimer();
        dataModule.setPoints(
                dataModule.getPointingSystem() == NORMAL_POINTING_SYSTEM ?
                        0 : BEGINNING_POT);
        refreshPoints();

        sensitiveMenuItems(miNew);
        sensitiveMenuItems(miUndo);
        sensitiveMenuItems(miExit);

        sensitiveMenuItems(miPointingSystem);
        sensitiveMenuItems(miNormal);
        sensitiveMenuItems(miVegas);
        sensitiveMenuItems(miNone);
        sensitiveMenuItems(miContinuous);
        sensitiveMenuItems(miUseTimer);
        sensitiveMenuItems(miStatus);
        sensitiveMenuItems(miBackboard);
        sensitiveMenuItems(miLookAndFeel);
        sensitiveMenuItems(miWindows);
        sensitiveMenuItems(miMetal);
        sensitiveMenuItems(miMotif);
        sensitiveMenuItems(miUse);
        sensitiveMenuItems(miAbout);
    }

    //----------------------------------------------------------------------

    /**
     * Beállítja a komponenseket
     */
    private void setComponents() {
        // Pontozási rendszer:
        // Normál
        if (dataModule.getPointingSystem() == NORMAL_POINTING_SYSTEM) {
            miNormal.setSelected(true);
            miContinuous.setEnabled(false);
            pointsPanel.setVisible(true);
        }
        // Vegas
        else if (dataModule.getPointingSystem() == VEGAS_POINTING_SYSTEM) {
            miVegas.setSelected(true);
            miContinuous.setEnabled(true);
            pointsPanel.setVisible(true);
        }
        // Nincs
        else {
            miNone.setSelected(true);
            miContinuous.setEnabled(false);
            pointsPanel.setVisible(false);
        }
        // Folyamatos pontozás
        miContinuous.setSelected(dataModule.isContinuousGame());
        // Játék időre
        miUseTimer.setSelected(dataModule.isUseTimer());
        timePanel.setVisible(dataModule.isUseTimer());
        // Állapotsor
        miStatus.setSelected(dataModule.isStatusField());
        statusPanel.setVisible(dataModule.isStatusField());
        pack();
        // Kinézet
        if (dataModule.getLookAndFeel() == WINDOWS_LOOK_AND_FEEL)
            miWindows.setSelected(true);
        else if (dataModule.getLookAndFeel() == METAL_LOOK_AND_FEEL)
            miMetal.setSelected(true);
        else
            miMotif.setSelected(true);

    }

    //----------------------------------------------------------------------

    /**
     * Érzékennyé teszi a menütételeket az egérmozgásra
     */
    private void sensitiveMenuItems(AbstractButton abstractButton) {
        final AbstractButton ab = abstractButton;
        abstractButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                lbStatus.setText(ab.getActionCommand());
            }

            public void mouseExited(MouseEvent e) {
                lbStatus.setText(" ");
            }

            public void mouseClicked(MouseEvent e) {
                lbStatus.setText(" ");
            }

            public void mouseReleased(MouseEvent e) {
                lbStatus.setText(" ");
            }
        });
        addComponentListener(new ComponentAdapter() {
            public void componentHidden(ComponentEvent e) {
                lbStatus.setText(" ");
            }
        });
    }

    //----------------------------------------------------------------------

    /**
     * Létrehozza az órát
     */
    public void createTimer() {
        if (timer != null)
            stopTimer();
        dataModule.setTime(0);
        timer = (new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dataModule.increaseTime();
                lbTime.setText("" + dataModule.getTime());
                refreshPoints();
            }
        }));
    }

    //----------------------------------------------------------------------

    /**
     * Elindítja az órát, ha még az nem megy
     */
    public void startTimer() {
        if (!timer.isRunning())
            timer.start();
    }

    //----------------------------------------------------------------------

    /**
     * Megállítja az órát
     */
    public void stopTimer() {
        timer.stop();
        timer = null;
    }

    //----------------------------------------------------------------------

    /**
     * A visszavonás menü engedélyezését végzi
     */
    public void cancelable(boolean cancelable) {
        miUndo.setEnabled(cancelable);
    }

    //----------------------------------------------------------------------

    /**
     * A pontozási rendszert és a pontokat figyelembevéve állíja be
     * a pontszámok kijelzőjét
     */
    public void refreshPoints() {
        if (dataModule.getPointingSystem() == NORMAL_POINTING_SYSTEM)
            lbPoints.setText("" + dataModule.getPoints());
        if (dataModule.getPointingSystem() == VEGAS_POINTING_SYSTEM)
            lbPoints.setText(PointFormat.getCurrencyValue(dataModule.getPoints()));
        lbPoints.setForeground(dataModule.getPoints() < 0 ? Color.red : Color.black);
    }

    //----------------------------------------------------------------------

    /**
     * Ha a kapott kinézet eltér az aktuális kinézettől, akkor az
     * alkalmazás összes komponense új megjelenítést kap.
     */
    private void changeLookAndFeel(int lookAndFeel) {
        int actualLookAndFeel = dataModule.getLookAndFeel();
        if (actualLookAndFeel != lookAndFeel) {
            try {
                if (lookAndFeel == WINDOWS_LOOK_AND_FEEL)
                    UIManager.setLookAndFeel(new WindowsLookAndFeel());
                if (lookAndFeel == METAL_LOOK_AND_FEEL)
                    UIManager.setLookAndFeel(new MetalLookAndFeel());
                if (lookAndFeel == MOTIF_LOOK_AND_FEEL)
                    UIManager.setLookAndFeel(new MotifLookAndFeel());
                SwingUtilities.updateComponentTreeUI(getRootPane());
                // a dialógusok frissítése
                SwingUtilities.updateComponentTreeUI(backboardDialog.getRootPane());
                SwingUtilities.updateComponentTreeUI(howToUseDialog.getRootPane());
                howToUseDialog.refreshTree(); // a fa ikonjai nem frissülnek maguktók
                SwingUtilities.updateComponentTreeUI(aboutDialog.getRootPane());

                dataModule.setLookAndFeel(lookAndFeel); // az új kinézet elmentése
                getRootPane().revalidate();
                getRootPane().repaint();
                pack();
            } catch (UnsupportedLookAndFeelException ex) {
                ex.printStackTrace();
            }
        }
    }

    //----------------------------------------------------------------------

    /**
     * Új játék kezdése
     */
    private void newGame() {
        cancelable(false);
        contentPane.remove(table);
        contentPane.add(table = new Table(this));
        createTimer();

        if (dataModule.getPointingSystem() == VEGAS_POINTING_SYSTEM) // vegas pontozás
            if (!dataModule.isContinuousGame()) // nem folyamatos
                dataModule.setPoints(BEGINNING_POT);
            else // folyamatos
                dataModule.setPoints(dataModule.getPoints() + BEGINNING_POT);
        else // normál pontozás
            dataModule.setPoints(0);

        refreshPoints();
        lbTime.setText("" + dataModule.getTime());
        pack();
    }

    //----------------------------------------------------------------------

    /**
     * Megállítja az időt és nem engedélyezi többé a visszavonást
     */
    public void endOfGame() {
        stopTimer();
        cancelable(false);
    }

    //----------------------------------------------------------------------

    /**
     * Kilépés
     */
    private void exit() {
        if (MessageManager.showConfirmDialog(this, "Biztosan kilép?"))
            System.exit(0);
    }

    //----------------------------------------------------------------------

    public Table getTable() {
        return table;
    }

    //----------------------------------------------------------------------

    /**
     * Új leosztás kérése
     */
    void miNew_actionPerformed(ActionEvent e) {
        newGame();
    }

    //----------------------------------------------------------------------

    /**
     * Kilépés
     */
    void miExit_actionPerformed(ActionEvent e) {
        exit();
    }

    //----------------------------------------------------------------------

    /**
     * Hátlapdialógus hívása
     */
    void miBackboard_actionPerformed(ActionEvent e) {
        String oldImagePath = dataModule.getImagePath();
        backboardDialog.showDialog();
        if (!oldImagePath.equals(dataModule.getImagePath())) // ha új lett a fájl
            table.changeBackboards();
    }

    //----------------------------------------------------------------------

    /**
     * Visszavonás menüből
     */
    void miUndo_actionPerformed(ActionEvent e) {
        dataModule.getCancelManager().cancel();
        dataModule.getPointManager().cancel();
        cancelable(false);
    }

    //----------------------------------------------------------------------

    /**
     * Windows-os kinézet
     */
    void miWindows_actionPerformed(ActionEvent e) {
        changeLookAndFeel(WINDOWS_LOOK_AND_FEEL);
    }
    //----------------------------------------------------------------------

    /**
     * Metal-os kinézet
     */
    void miMetal_actionPerformed(ActionEvent e) {
        changeLookAndFeel(METAL_LOOK_AND_FEEL);
    }
    //----------------------------------------------------------------------

    /**
     * Motifos-os kinézet
     */
    void miMotif_actionPerformed(ActionEvent e) {
        changeLookAndFeel(MOTIF_LOOK_AND_FEEL);
    }

    //----------------------------------------------------------------------

    /**
     * Névjegy dialógus hívása
     */
    void miAbout_actionPerformed(ActionEvent e) {
        aboutDialog.showDialog();
    }

    //----------------------------------------------------------------------

    /**
     * Ablakbezásás
     */
    void this_windowClosing(WindowEvent e) {
        exit();
    }

    //----------------------------------------------------------------------

    /**
     * Állapotsor megjelenítése/eltűntetése
     */
    void miStatus_actionPerformed(ActionEvent e) {
        statusPanel.setVisible(!statusPanel.isVisible());
        dataModule.setStatusField(statusPanel.isVisible());
        pack();
    }

    //----------------------------------------------------------------------

    /**
     * Folyamatos pontozás kiválasztása
     */
    void miContinuous_actionPerformed(ActionEvent e) {
        dataModule.setContinuousGame(!dataModule.isContinuousGame());
        if (!dataModule.isContinuousGame())
            newGame();
    }

    //----------------------------------------------------------------------

    /**
     * Játék időre kiválasztása
     */
    void miUseTimer_actionPerformed(ActionEvent e) {
        dataModule.setUseTimer(!dataModule.isUseTimer());
        newGame();
        timePanel.setVisible(dataModule.isUseTimer());
    }

    //----------------------------------------------------------------------

    /**
     * Normál pontozási rendszer kiválasztása
     */
    void miNormal_actionPerformed(ActionEvent e) {
        if (dataModule.getPointingSystem() != NORMAL_POINTING_SYSTEM) {
            dataModule.setPointingSystem(NORMAL_POINTING_SYSTEM);
            pointsPanel.setVisible(true);
            miContinuous.setEnabled(false);
            newGame();
        }
    }

    //----------------------------------------------------------------------

    /**
     * Vegas pontozási rendszer kiválasztása
     */
    void miVegas_actionPerformed(ActionEvent e) {
        if (dataModule.getPointingSystem() != VEGAS_POINTING_SYSTEM) {
            dataModule.setPointingSystem(VEGAS_POINTING_SYSTEM);
            pointsPanel.setVisible(true);
            miContinuous.setEnabled(true);
            newGame();
        }
    }

    //----------------------------------------------------------------------

    /**
     * Pontozás nélküli kiválasztása
     */
    void miNone_actionPerformed(ActionEvent e) {
        if (dataModule.getPointingSystem() != NONE_POINTING_SYSTEM) {
            dataModule.setPointingSystem(NONE_POINTING_SYSTEM);
            pointsPanel.setVisible(false);
            miContinuous.setEnabled(false);
            newGame();
        }
    }

    void miUse_actionPerformed(ActionEvent e) {
        howToUseDialog.showDialog();
    }

}

//############################################################################

class MainFrame_miNew_actionAdapter implements ActionListener {
    MainFrame adaptee;

    MainFrame_miNew_actionAdapter(MainFrame adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.miNew_actionPerformed(e);
    }
}

class MainFrame_miExit_actionAdapter implements ActionListener {
    MainFrame adaptee;

    MainFrame_miExit_actionAdapter(MainFrame adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.miExit_actionPerformed(e);
    }
}

class MainFrame_miBackboard_actionAdapter implements ActionListener {
    MainFrame adaptee;

    MainFrame_miBackboard_actionAdapter(MainFrame adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.miBackboard_actionPerformed(e);
    }
}

class MainFrame_miUndo_actionAdapter implements ActionListener {
    MainFrame adaptee;

    MainFrame_miUndo_actionAdapter(MainFrame adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.miUndo_actionPerformed(e);
    }
}

class MainFrame_miAbout_actionAdapter implements ActionListener {
    MainFrame adaptee;

    MainFrame_miAbout_actionAdapter(MainFrame adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.miAbout_actionPerformed(e);
    }
}

class MainFrame_this_windowAdapter extends WindowAdapter {
    MainFrame adaptee;

    MainFrame_this_windowAdapter(MainFrame adaptee) {
        this.adaptee = adaptee;
    }

    public void windowClosing(WindowEvent e) {
        adaptee.this_windowClosing(e);
    }
}

class MainFrame_miWindows_actionAdapter implements ActionListener {
    MainFrame adaptee;

    MainFrame_miWindows_actionAdapter(MainFrame adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.miWindows_actionPerformed(e);
    }
}

class MainFrame_miMetal_actionAdapter implements ActionListener {
    MainFrame adaptee;

    MainFrame_miMetal_actionAdapter(MainFrame adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.miMetal_actionPerformed(e);
    }
}

class MainFrame_miMotif_actionAdapter implements ActionListener {
    MainFrame adaptee;

    MainFrame_miMotif_actionAdapter(MainFrame adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.miMotif_actionPerformed(e);
    }
}

class MainFrame_miStatus_actionAdapter implements ActionListener {
    MainFrame adaptee;

    MainFrame_miStatus_actionAdapter(MainFrame adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.miStatus_actionPerformed(e);
    }
}

class MainFrame_miContinuous_actionAdapter implements ActionListener {
    MainFrame adaptee;

    MainFrame_miContinuous_actionAdapter(MainFrame adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.miContinuous_actionPerformed(e);
    }
}

class MainFrame_miUseTimer_actionAdapter implements ActionListener {
    MainFrame adaptee;

    MainFrame_miUseTimer_actionAdapter(MainFrame adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.miUseTimer_actionPerformed(e);
    }
}

class MainFrame_miNormal_actionAdapter implements ActionListener {
    MainFrame adaptee;

    MainFrame_miNormal_actionAdapter(MainFrame adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.miNormal_actionPerformed(e);
    }
}

class MainFrame_miVegas_actionAdapter implements ActionListener {
    MainFrame adaptee;

    MainFrame_miVegas_actionAdapter(MainFrame adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.miVegas_actionPerformed(e);
    }
}

class MainFrame_miNone_actionAdapter implements ActionListener {
    MainFrame adaptee;

    MainFrame_miNone_actionAdapter(MainFrame adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.miNone_actionPerformed(e);
    }
}

class MainFrame_miUse_actionAdapter implements ActionListener {
    MainFrame adaptee;

    MainFrame_miUse_actionAdapter(MainFrame adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.miUse_actionPerformed(e);
    }
}


