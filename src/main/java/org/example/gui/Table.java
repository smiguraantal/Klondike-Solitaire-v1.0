package org.example.gui;

import org.example.entity.Colors;
import org.example.entity.Constants;
import org.example.entity.DataModule;
import org.example.entity.Utils;
import org.example.gui.listener.TableMouseListener;
import org.example.gui.manager.CancelManager;
import org.example.gui.manager.DraggingManager;
import org.example.gui.manager.PointManager;
import org.example.gui.place.House;
import org.example.gui.place.NonVisibleTalon;
import org.example.gui.place.Pack;
import org.example.gui.place.Place;
import org.example.gui.place.VisibleTalon;

import javax.swing.JLayeredPane;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.util.Collections;
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
 * Az asztal, melyen helyet foglalnak a kártlyalapok
 */
public class Table extends JLayeredPane
        implements Constants, Colors {

    /**
     * Adatmodul
     */
    private DataModule dataModule = DataModule.getDataModule();

    /**
     * A lefelé fordított talon
     */
    private Place nonVisibleTalon;

    /**
     * A felfelé fordított talon
     */
    private Place visibleTalon;

    /**
     * házak
     */
    private Vector houses;

    /**
     * kupacok
     */
    private Vector packs;

    /**
     * A teljes kártyapakli
     */
    private Cards deckOfCards;

    /**
     * A főablak
     */
    private MainFrame mainFrame;

    /**
     * A tábla egéresemény figyelője
     */
    private TableMouseListener tableMouseListener = new TableMouseListener(this);

    //----------------------------------------------------------------------

    /**
     * Konstruktor
     */
    public Table(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        try {
            jbInit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    //----------------------------------------------------------------------

    void jbInit() throws Exception {
        this.setLayout(null);
        setPreferredSize(new Dimension(TABLE_WIDTH, TABLE_HEIGHT));
        this.addMouseListener(tableMouseListener);
        setOpaque(true);
        setBackground(BAIZE);

        newGame();
        dataModule.setDraggingManager(new DraggingManager(this));
        dataModule.setPointManager(new PointManager(mainFrame));
    }

    //----------------------------------------------------------------------

    /**
     * Új játék kezdése
     */
    public void newGame() {
        initDeckOfCards();
        initTalons();
        initHouses();
        initPacks();
        dealToPacks();
        dealToTalon();
    }

    //----------------------------------------------------------------------

    /**
     * Feltölti és megkeveri a kártyapaklit
     */
    private void initDeckOfCards() {
        deckOfCards = new Cards();
        for (int color = 1; color <= NUMBER_OF_CARD_COLORS; color++) {
            for (int value = 1; value <= NUMBER_OF_CARD_VALUES; value++) {
                String fileName =
                        "images/card_" +
                                "" + color + "_" +
                                Utils.format(value) +
                                ".png";
                Card card = new Card(fileName, value, color);
                deckOfCards.addCard(card);
            }
        }
        Collections.shuffle(deckOfCards.getCards());
    }

    //----------------------------------------------------------------------

    /**
     * Létrehozza a talonokat
     */
    private void initTalons() {
        Point point = new Point(GAP_SIZE, GAP_SIZE);
        nonVisibleTalon = new NonVisibleTalon(this, point);

        point = new Point(GAP_SIZE * 2 + CARD_WIDTH, GAP_SIZE);
        visibleTalon = new VisibleTalon(this, point);
    }

    //----------------------------------------------------------------------

    /**
     * Létrehozza a házakat
     */
    private void initHouses() {
        houses = new Vector();
        for (int i = 0; i < NUMBER_OF_HOUSES; i++) {
            Point point = new Point(GAP_SIZE * (i + 4) + CARD_WIDTH * (i + 3),
                    GAP_SIZE);
            House house = new House(this, point);
            houses.add(house);
        }
    }

    //----------------------------------------------------------------------

    /**
     * Létrehozza a kupacokat
     */
    private void initPacks() {
        packs = new Vector();
        for (int i = 0; i < NUMBER_OF_PACKS; i++) {
            Point point = new Point(GAP_SIZE * (i + 1) + CARD_WIDTH * i,
                    GAP_SIZE * 2 + CARD_HEIGTH);
            Pack pack = new Pack(this, point);
            packs.add(pack);
        }
    }

    //----------------------------------------------------------------------

    /**
     * Kiosztja a lapokat a talonba
     */
    private void dealToTalon() {
        for (int i = 0; i < TALON_SIZE; i++) {
            Card card = deckOfCards.removeTopCard();
            card.setBounds(nonVisibleTalon.getNextBounds());
            int layer = nonVisibleTalon.getNextLayer();
            card.setLayer(layer);
            nonVisibleTalon.addCard(card);
            add(card, new Integer(layer));
        }
    }

    //----------------------------------------------------------------------

    /**
     * Kiosztja a lapokat a kupacokra
     */
    private void dealToPacks() {
        // kupacok
        for (int i = 0; i < NUMBER_OF_PACKS; i++) {
            Pack pack = (Pack) packs.get(i);
            for (int j = 0; j < i; j++) {
                Card card = deckOfCards.removeTopCard();
                card.setBounds(pack.getNextBounds());
                int layer = pack.getNextLayer();
                card.setLayer(layer);
                pack.addCard(card);
                add(card, new Integer(layer));
            }
        }
        // minden kupacra egy felfordított lap
        for (int i = 0; i < NUMBER_OF_PACKS; i++) {
            Pack pack = (Pack) packs.get(i);
            Rectangle rec = pack.getTopBounds();
            Card card = deckOfCards.removeTopCard();
            card.setBounds(pack.getNextBounds());
            int layer = pack.getNextLayer();
            card.setLayer(layer);
            pack.initNumberSeries(card);
            card.setVisibility(true);
            add(card, new Integer(layer));
        }
    }

    //----------------------------------------------------------------------

    /**
     * Felfordít egy kártyát a talonból és átteszi a látható talonba
     */
    public void pullFromTalon() {
        Card card = nonVisibleTalon.getCards().removeTopCard();
        dataModule.setCancelManager(new CancelManager(this));
        dataModule.getCancelManager().addToCardsBefore(card);
        int layer = visibleTalon.getNextLayer();
        card.setLayer(layer);
        setLayer(card, layer);
        card.setBounds(visibleTalon.getNextBounds());
        visibleTalon.addCard(card);
        card.setVisibility(true);
        dataModule.getCancelManager().addToCardsAfter(card);
    }

    //----------------------------------------------------------------------

    /**
     * Visszafordítja a talon látható lapjait
     */
    public void turnBackTalon() {
        dataModule.setCancelManager(new CancelManager(this));
        while (!visibleTalon.isEmpty()) {
            Card card = (Card) visibleTalon.getCards().removeTopCard();
            dataModule.getCancelManager().addToCardsBefore(card);
            int layer = nonVisibleTalon.getNextLayer();
            card.setLayer(layer);
            nonVisibleTalon.addCard(card);
            setLayer(card, layer);
            card.setBounds(nonVisibleTalon.getNextBounds());
            card.setVisibility(false);
            dataModule.getCancelManager().addToCardsAfter(card);
        }
        // a látható talon át lesz fordítva, ezért vissza kell fordítani
        // az elmentett lapok tárolóit!
        Collections.reverse(dataModule.getCancelManager().getCardsBefore());
        Collections.reverse(dataModule.getCancelManager().getCardsAfter());
    }

    //----------------------------------------------------------------------

    /**
     * Megpróbálja betenni a kapott hely legfelső lapját a házak egyikébe
     */
    public void tryToPutToHouse(Place place) {
        dataModule.setCancelManager(new CancelManager(this));
        Card topCard = place.getTopCard(); // itt csak le lesz kérdezve
        for (int i = 0; i < NUMBER_OF_HOUSES; i++) {
            House house = (House) houses.get(i);
            if (house.putableCard(topCard)) {
                putToHouse(house, place.removeTopCard());
                break; // máshova már ne próbálja betenni
            }
        }
    }

    //----------------------------------------------------------------------

    /**
     * Beteszi a házba a kapott kártyát
     */
    public void putToHouse(House house, Card topCard) {
        dataModule.getCancelManager().addToCardsBefore(topCard); // előtte
        int layer = house.getNextLayer();
        topCard.setLayer(layer);
        setLayer(topCard, layer);
        house.addCard(topCard);
        topCard.setBounds(house.getBounds());
        dataModule.getCancelManager().addToCardsAfter(topCard); // utána
        dataModule.getPointManager().putToHouse();
    }

    //----------------------------------------------------------------------

    /**
     * Visszaadja hogy összesen hány lap van a házakban
     */
    private int getNumberOfCardsInHouses() {
        int numberOfCardsInHouses = 0;
        for (int i = 0; i < NUMBER_OF_HOUSES; i++) {
            House house = (House) houses.get(i);
            numberOfCardsInHouses += house.getColorSeries().getSize();
        }
        return numberOfCardsInHouses;
    }

    //----------------------------------------------------------------------

    /**
     * Megpróbálja betenni a látható talon legfelső lapját a házba
     */
    private void toHouseFromVisibleTalon() {
        if (!visibleTalon.isEmpty()) { // ha nem üres a látható talon
            Card topCard = visibleTalon.getTopCard();
            for (int i = 0; i < NUMBER_OF_HOUSES; i++) { // végig a házakon
                House house = (House) houses.get(i);
                if (house.putableCard(topCard)) { // ha berakható
                    dataModule.setCancelManager(new CancelManager(this)); // csak egy kártya lesz visszavonható
                    putToHouse(house, visibleTalon.removeTopCard());
                    break; // másik házba már ne próbálja, ha egyszer már sikerült
                }
            }
        }
    }

    //----------------------------------------------------------------------

    /**
     * Megpróbálja betenni a számsorozatok legfelső lapjait a házba
     */
    private void toHouseFromPacks() {
        for (int i = 0; i < NUMBER_OF_PACKS; i++) { // végig a kupacokon
            Pack pack = (Pack) packs.get(i);
            if (!pack.getNumberSeries().isEmpty()) { // ha nem üres a kupac számsorozata
                Card topCard = pack.getNumberSeries().getTopCard();
                for (int j = 0; j < NUMBER_OF_HOUSES; j++) { // végig a házakon
                    House house = (House) houses.get(j);
                    if (house.putableCard(topCard)) { // ha berakható
                        dataModule.setCancelManager(new CancelManager(this)); // csak egy kártya lesz visszavonható
                        putToHouse(house, pack.getNumberSeries().removeTopCard());
                        break; // másik házba már ne próbálja, ha egyszer már sikerült
                    }
                }
            }
        }
    }

    //----------------------------------------------------------------------

    /**
     * Beteszi az összes berakható kártyát a házba
     */
    public void putAllToHouse() {
        // megadja, hogy sikerült-e kártyát betenni a házba. kezdeti értéke: igaz,
        // hogy a próbálkozásra lehetőséget adjon.
        boolean success = true;
        int numberOfCardsInHouses;
        // ha egy betétel sikeres volt, akkor újra végignézzük az összes lapot,
        // hátha a berakott alatt ismét egy berakhatót találunk
        while (success) {
            numberOfCardsInHouses = getNumberOfCardsInHouses();
            toHouseFromVisibleTalon();
            toHouseFromPacks();
            success = numberOfCardsInHouses < getNumberOfCardsInHouses();
        }
    }

    //----------------------------------------------------------------------

    /**
     * Megrajzolja az asztalon található helyeket a kártyáknak.
     */
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // lefedett talon
        g.drawRect(nonVisibleTalon.x,
                nonVisibleTalon.y,
                nonVisibleTalon.width - 1,
                nonVisibleTalon.height - 1);
        // felfordított talon
        g.drawRect(visibleTalon.x,
                visibleTalon.y,
                visibleTalon.width - 1,
                visibleTalon.height - 1);
        // házak
        for (int i = 0; i < houses.size(); i++) {
            Place house = (Place) houses.get(i);
            g.drawRect(house.x,
                    house.y,
                    house.width - 1,
                    house.height - 1);
        }
        // kupacok
        for (int i = 0; i < packs.size(); i++) {
            Place pack = (Place) packs.get(i);
            g.drawRect(pack.x,
                    pack.y,
                    pack.width - 1,
                    pack.height - 1);
        }


        Graphics2D g2 = (Graphics2D) g;
        BasicStroke stroke = (BasicStroke) g2.getStroke();
        BasicStroke wideStroke = new BasicStroke(7.0f);
        g2.setStroke(wideStroke);

        if (dataModule.getPointingSystem() == VEGAS_POINTING_SYSTEM) { // vegas
            g2.setColor(Color.red);
            g.drawLine(nonVisibleTalon.x + 8,
                    nonVisibleTalon.y + 20,
                    nonVisibleTalon.x + CARD_WIDTH - 8 - 1,
                    nonVisibleTalon.y + CARD_HEIGTH - 20 - 1);

            g.drawLine(nonVisibleTalon.x + CARD_WIDTH - 8 - 1,
                    nonVisibleTalon.y + 20,
                    nonVisibleTalon.x + 8,
                    nonVisibleTalon.y + CARD_HEIGTH - 20 - 1);
        } else { // normál vagy pontozás nélküli rendszer
            g2.setColor(Color.green);
            g2.draw(new Ellipse2D.Double(nonVisibleTalon.x + 8,
                    nonVisibleTalon.y + 20,
                    50, // a kör átmérője
                    50));
        }

        g2.setStroke(stroke);
    }

    //----------------------------------------------------------------------

    /**
     * Visszaadja azokat a házakat, melyek képesek fogadni a kapott lapot.
     */
    private Vector getReceiveableHouses(Card takenCard) {
        Vector receiveableHouses = new Vector();
        for (int i = 0; i < NUMBER_OF_HOUSES; i++) { // végig a házakon
            House house = (House) houses.get(i);
            if (isOverlapping(takenCard.getBounds(), house.getBounds())) // ha átfedésben van
                if (!takenCard.getPlace().equals(house)) // ha nem a saját háza
                    if (house.putableCard(takenCard))  // ha passzol a lapokhoz
                        receiveableHouses.add(house);
        }
        return receiveableHouses;
    }

    //----------------------------------------------------------------------

    /**
     * Visszaadja azokat a kupacokat, melyek képesek fogadni a kapott lapot.
     */
    private Vector getReceiveablePacks(Card takenCard) {
        Vector receiveablePacks = new Vector();
        for (int i = 0; i < NUMBER_OF_PACKS; i++) { // végig a kupacokon
            Pack pack = (Pack) packs.get(i);
            if (pack.isEmpty()) // ha üres a kupac
                if (isOverlapping(takenCard.getBounds(), pack.getBounds())) // ha átfedésben van
                    if (!takenCard.getPlace().equals(pack)) // ha nem a saját háza
                        if (pack.putableCard(takenCard))  // ha passzol a lapokhoz
                            receiveablePacks.add(pack);
        }
        for (int i = 0; i < NUMBER_OF_PACKS; i++) { // végig a kupacokon
            Pack pack = (Pack) packs.get(i);
            if (!pack.isEmpty()) // ha nem üres a kupac
                if (pack.getTopCard().isVisibility()) // ha a felső lapja látható
                    if (isOverlapping(takenCard.getBounds(), pack.getTopCard().getBounds())) // ha átfedésben van
                        if (!takenCard.getPlace().equals(pack)) // ha nem a saját háza
                            if (pack.putableCard(takenCard)) // ha passzol a lapokhoz
                                receiveablePacks.add(pack);
        }
        return receiveablePacks;
    }

    //----------------------------------------------------------------------

    /**
     * Visszaadja, hogy a két téglalap átfedésben van-e egymással
     */
    private boolean isOverlapping(Rectangle rec_1, Rectangle rec_2) {
        Rectangle intersection = rec_1.intersection(rec_2);
        return intersection.width > 0 && intersection.height > 0;
    }

    //----------------------------------------------------------------------

    /**
     * Megváltoztatja a kártyák hátlapját
     */
    public void changeBackboards() {
        for (int i = 0; i < getComponentCount(); i++) {
            Card card = (Card) this.getComponent(i);
            if (!card.isVisibility())
                card.changeBackboard();
        }
    }

    //----------------------------------------------------------------------

    /**
     * Megvizsgálja, hogy vége van-e a játéknak. Ha  igen, törli az egéresemény
     * figyelőket és megállítja az időt.
     */
    public void maybeGameEnds() {
        if (getNumberOfCardsInHouses() == Constants.NUMBER_OF_DECK_OF_CARDS) {
            for (int i = 0; i < getComponentCount(); i++) {
                Card card = (Card) getComponent(i);
                card.removeAllListeners();
            }
            removeMouseListener(tableMouseListener);
            mainFrame.endOfGame();
        }
    }

    //----------------------------------------------------------------------

    /**
     * Egérgomb lenyomás
     */
    public void mousePressed(MouseEvent e) {
        mainFrame.startTimer();
        if (e.getModifiers() == e.BUTTON1_MASK)  // bal egérkattintás
            if (nonVisibleTalon.getBounds().contains(e.getPoint()))  // ha a nem látható talon helyén történt a kattintás
                if (!visibleTalon.getCards().isEmpty())  // ha van mit visszafordítani
                    if (dataModule.getPointingSystem() != VEGAS_POINTING_SYSTEM) { // vegas pontozási rendszerben csak egyszer mehet végig a talonon!
                        turnBackTalon();
                        dataModule.getPointManager().talonTurning();
                    }
        if (e.getModifiers() == e.BUTTON3_MASK) { // jobb egérkattintás
            putAllToHouse();
            maybeGameEnds();
        }
    }

    //----------------------------------------------------------------------

    /**
     * Kiválasztja a lap fogadására legmegfelelőbb helyet. A választáskor a
     * házak előnyt élveznek a kupacokkal szemben.
     */
    public Place getPrimaryPlace() {
        DraggingManager draggingManager = dataModule.getDraggingManager();
        Vector receiveableHouses = getReceiveableHouses(draggingManager.getTakenCard());
        if (!receiveableHouses.isEmpty())
            return (Place) receiveableHouses.get(0);
        Vector receiveablePacks = getReceiveablePacks(draggingManager.getTakenCard());
        if (!receiveablePacks.isEmpty())
            return (Place) receiveablePacks.get(0);
        return null;
    }

    //----------------------------------------------------------------------

    public Place getVisibleTalon() {
        return visibleTalon;
    }

    public Place getNonVisibleTalon() {
        return nonVisibleTalon;
    }

    public Vector getHouses() {
        return houses;
    }

    public Vector getPacks() {
        return packs;
    }

    public MainFrame getMainFrame() {
        return mainFrame;
    }
}
