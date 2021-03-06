package View;

import Building.Barracks.Barracks;
import Building.Church.Church;
import Building.Farm.Farm;
import Building.House.House;
import Building.TownCenter.TownCenter;
import Buildings.Action;
import Buildings.Building;
import Controls.Ai;
import Controls.Dijkstra;
import Controls.Key;
import Controls.Mouse;
import Data.GameData;
import Data.MapData;
import GameElement.Bush;
import GameElement.Cactus;
import GameElement.Gold;
import GameElement.Grass;
import GameElement.ObjectElement;
import GameElement.Relic;
import GameElement.Sand;
import GameElement.Shoal;
import GameElement.Stone;
import GameElement.Tree;
import GameElement.Water;
import Unit.Melee.SwordMan;
import Unit.Player;
import Unit.Unit;
import Unit.Villager.Villager;
import Unit.Villager.VillagerAction;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.swing.JPanel;

public class GameBoard extends JPanel
{

    private MainWindow rootWindow;
    private int currentWindowX;
    private int currentWindowY;
    private final int columns = GameData.MAP_WIDTH / GameData.BOXSIZE;
    private final int rows = GameData.MAP_HEIGHT / GameData.BOXSIZE;

    private MapData mapData;
    private List<ObjectElement> objects;
    private List<Unit> units;
    private List<Building> buildings;
    private Mouse mouse;
    private BufferedImage background;
    private BotPanel botPanel;
    private TopPanel topPanel;

    private Player blue;
    private Player red;
    private Ai ai;
    private int[][] field = new int[rows][columns];
    private ObjectElement[][] objectField = new ObjectElement[columns][rows];
    private Unit[][] unitField = new Unit[columns][rows];
    private Building[][] buildingField = new Building[columns][rows];
    private Action action;
    private boolean running;

    GameBoard(MainWindow rootWindow)
    {
        this.rootWindow = rootWindow;
        initGameBoard();
        setIgnoreRepaint(true);
    }

    public void initComponents(BotPanel bP, TopPanel tP)
    {
        this.botPanel = bP;
        this.topPanel = tP;
        mouse = new Mouse(this, botPanel, topPanel);
        setFocusable(true);
        addMouseListener(mouse);
        addMouseMotionListener(mouse);
    }

    private void initGameBoard()
    {
        setPreferredSize(new Dimension(GameData.WINDOW_WIDTH, GameData.WINDOW_HEIGHT - 45 - 200));//velikost bot a top panelu -vysky jejich
        objects = new ArrayList<>();
        mapData = new MapData(this);
        objects = mapData.getMapData();

        blue = new Player(300, 300, 300, 300, "Blue");
        red = new Player(300, 300, 300, 300, "Red");
        ai = new Ai(this, red);
        units = new ArrayList<Unit>();
        buildings = new ArrayList<Building>();

        generateGrass();
        fillBackground();

        blue.setTownCenter(new TownCenter(this, 30, 80, "Blue"));
        red.setTownCenter(new TownCenter(this, 264, 87, "Red"));

        units.add(new Villager(this, 30, 70, 0, "Blue"));
        units.add(new Villager(this, 40, 70, 90, "Blue"));
        units.add(new Villager(this, 35, 70, 180, "Blue"));
        //buildings.add(new Barracks(this, 45, 60, "Blue"));
        //buildings.add(new TownCenter(this, 30, 80, "Blue"));

        //buildings.add(new TownCenter(this, 264, 87, "Red"));
        buildings.add(new Barracks(this, 220, 80, "Red"));

        //buildings.add(new House(this, 40, 70, "Blue"));
        // buildings.add(new House(this, 230, 60, "Red"));
        // buildings.add(new Church(this, 50, 70, "Blue"));
        buildings.add(new Church(this, 250, 60, "Red"));

        // buildings.add(new Farm(this, 80, 80, "Blue"));
        // buildings.add(new Farm(this, 72, 80, "Blue"));
        // units.add(new SwordMan(this, 50, 50, 180, "Red"));
//        units.add(new SwordMan(this, 50, 52, 180, "Red"));
//        units.add(new SwordMan(this, 54, 52, 180, "Red"));
//        units.add(new SwordMan(this, 58, 52, 180, "Red"));
//        units.add(new SwordMan(this, 65, 52, 180, "Red"));
        //units.add(new SwordMan(this, 45, 52, 180, "Blue"));
//        units.add(new SwordMan(this, 40, 52, 180, "Blue"));
//        units.add(new SwordMan(this, 40, 55, 180, "Blue"));
        // buildings.add(new House(this, 40, 40, "Red"));
        for (int i = 0; i < 22; i++)
        {
            for (int j = 0; j < 6; j++)
            {
                units.add(new SwordMan(this, 240 + i, 87 + j, 180, "Red"));
            }
        }
        running = true;

        addKeyListener(new Key(this));
        gameLoop();

    }

    private void gameLoop()
    {
        Thread gameThread = new Thread(new Runnable()
        {

            @Override
            public void run()
            {
                while (running)
                {

                    tick();
                    repaint();
                    try
                    {
                        //50ms - 20 fps
                        //33ms - 30fps
                        //16ms - 60 fps
                        Thread.sleep(16);
                    }
                    catch (InterruptedException ex)
                    {
                        Logger.getLogger(GameBoard.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }

        }, "gameThread");
        gameThread.start();
    }

    public void tick()
    {

        if (!blue.isTownCenterAlive())
        {
            System.err.println("BLUE TEAM LOST THE GAME");
            this.running = false;
        }
        if (!red.isTownCenterAlive())
        {
            System.err.println("RED TEAM LOST THE GAME");
            this.running = false;
        }

        ArrayList<Unit> temp = new ArrayList<Unit>(units);
        for (Unit u : temp)
        {
            u.tick();
        }
        ArrayList<Building> bluetemp = new ArrayList<>(blue.getBuildings());
        for (Building b : bluetemp)
        {
            b.tick();
            for (Action a : b.getActions())
            {
                a.tick();
            }
        }
        ArrayList<Building> redtemp = new ArrayList<>(red.getBuildings());
        for (Building b : redtemp)
        {
            b.tick();
            for (Action a : b.getActions())
            {
                a.tick();
            }
        }
        ai.tick();
    }

    private void fillBackground()
    {
        background = new BufferedImage(GameData.MAP_WIDTH, GameData.MAP_HEIGHT, BufferedImage.TYPE_INT_RGB);// new BufferedImage(GameData.MAP_WIDTH, GameData.MAP_WIDTH, BufferedImage.TYPE_INT_RGB);
        Graphics g = background.getGraphics();

        for (ObjectElement ob : objects)
        {
            if (ob instanceof Water)
            {
                ob.drawObject(g);
                continue;
            }
            if (ob instanceof Grass)
            {
                ob.drawObject(g);
                continue;
            }
            if (ob instanceof Shoal)
            {
                ob.drawObject(g);
                continue;
            }
            if (ob instanceof Sand)
            {
                ob.drawObject(g);
                continue;
            }
        }
        g.dispose();

    }

    public void addToCurrentWindowX(int increment)
    {
        this.currentWindowX += increment;
        if (currentWindowX < 0)
        {
            this.currentWindowX = 0;
        }
        if (currentWindowX > GameData.MAP_WIDTH - GameData.WINDOW_WIDTH)
        {
            currentWindowX = GameData.MAP_WIDTH - GameData.WINDOW_WIDTH;
        }

    }

    public void addToCurrentWindowY(int increment)
    {
        this.currentWindowY += increment;
        if (currentWindowY < 0)
        {
            currentWindowY = 0;
        }
        if (currentWindowY > GameData.MAP_HEIGHT - GameData.WINDOW_HEIGHT)
        {
            currentWindowY = GameData.MAP_HEIGHT - GameData.WINDOW_HEIGHT;
        }

    }

    public void setWindowsX(int increment)
    {
        int modulo = increment % 25;
        int newx = 25 - modulo + increment;
        this.currentWindowX = newx;
        if (currentWindowX < 0)
        {
            this.currentWindowX = 0;
        }
        if (currentWindowX > GameData.MAP_WIDTH - GameData.WINDOW_WIDTH)
        {
            currentWindowX = GameData.MAP_WIDTH - GameData.WINDOW_WIDTH;
        }

    }

    public void setWindowsY(int increment)
    {
        int modulo = increment % 25;
        int newy = 25 - modulo + increment;
        this.currentWindowY = newy;
        if (currentWindowY < 0)
        {
            currentWindowY = 0;
        }
        if (currentWindowY > GameData.MAP_HEIGHT - GameData.WINDOW_HEIGHT)
        {
            currentWindowY = GameData.MAP_HEIGHT - GameData.WINDOW_HEIGHT;
        }

    }

    public int convertX(int x)
    {
        return x - currentWindowX;
    }

    public int convertY(int y)
    {
        return y - currentWindowY;
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        if (background != null)
        {
            g.drawImage(background, -currentWindowX, -currentWindowY, GameData.MAP_WIDTH, GameData.MAP_HEIGHT, null);
        }
        ArrayList<ObjectElement> obtemp = new ArrayList<>(objects);
        for (ObjectElement ob : obtemp)
        {
            if (ob instanceof Tree)
            {
                ob.drawObject(g);
                continue;
            }
            if (ob instanceof Bush)
            {
                ob.drawObject(g);
                continue;
            }
            if (ob instanceof Gold)
            {
                ob.drawObject(g);
                continue;
            }
            if (ob instanceof Stone)
            {
                ob.drawObject(g);
                continue;
            }
            if (ob instanceof Relic)
            {
                ob.drawObject(g);
                continue;
            }
            if (ob instanceof Cactus)
            {
                ob.drawObject(g);
                continue;
            }
        }

        mouse.drawRect(g);

//        g.setColor(Color.blue);
//        for (Unit u : units)
//        {
//            if (u.isSelected())
//            {
//                Dijkstra d = u.getDijkstra();
//
//                if (d != null)
//                {
//                    d.drawPath(g);
//                }
//            }
//        }
        for (Building b : blue.getBuildings())
        {
            b.drawBuilding(g);
        }
        for (Building b : red.getBuildings())
        {
            b.drawBuilding(g);
        }
        for (Unit u : blue.getUnits())
        {
            u.drawUnit(g);
        }

        for (Unit u : red.getUnits())
        {
            u.drawUnit(g);
        }

//        g.setColor(Color.white);
//        for (int i = 0; i < columns; i++)
//        {
//            for (int j = 0; j < rows; j++)
//            {
//                g.drawString("" + getFieldIndex(i, j), convertX(i * 25 + 12), convertY(j * 25 + 12));
//            }
//        }
        if (action != null)
        {
            if (action instanceof VillagerAction)
            {
                VillagerAction vill = (VillagerAction) action;
                vill.drawBuildObject(g);
            }
        }

//        g.setColor(Color.white);
//        for (int i = 0; i < GameData.MAP_WIDTH; i += GameData.BOXSIZE)
//        {
//            for (int j = 0; j < GameData.MAP_HEIGHT; j += GameData.BOXSIZE)
//            {
//
//                g.drawRect(i, j, 25, 25);
//            }
//        }
        if (!running)
        {
            g.setColor(Color.black);
            g.fillRect(0, 0, GameData.WINDOW_WIDTH, GameData.WINDOW_HEIGHT);
            g.setColor(Color.white);
            g.drawString("Game End", GameData.WINDOW_WIDTH / 2 - 50, 300);
        }

        g.dispose();
        Toolkit.getDefaultToolkit().sync();
    }

    private void generateGrass()
    {
        Grass gr = new Grass(this, 0, 850);
        objects.add(gr);

        for (int i = 0; i < GameData.MAP_WIDTH - 200; i += 400)
        {
            for (int j = 850; j < GameData.MAP_HEIGHT - 400; j += 400)
            {
                gr = new Grass(this, i, j);
                objects.add(gr);
            }
        }
        for (int i = 850; i < GameData.MAP_HEIGHT - 400; i += 400)
        {
            gr = new Grass(this, GameData.MAP_WIDTH - 400, i);
            objects.add(gr);
        }

        for (int i = 0; i < GameData.MAP_WIDTH - 200; i += 400)
        {
            gr = new Grass(this, i, GameData.MAP_HEIGHT - 400);
            objects.add(gr);
        }

        gr = new Grass(this, GameData.MAP_WIDTH - 400, GameData.MAP_HEIGHT - 400);
        objects.add(gr);
    }

    public int getFieldIndex(int x, int y)
    {
        return field[y][x];
    }

    public void setFieldIndex(int x, int y, int value)
    {
        field[y][x] = value;
    }

    public ObjectElement getObjectFieldObject(int x, int y)
    {
        return objectField[x][y];
    }

    public void setObjectFieldObject(int x, int y, ObjectElement object)
    {

        objectField[x][y] = object;

    }

    public void setBuildingObjectField(int x, int y, Building building)
    {
        buildingField[x][y] = building;
    }

    public Building getBuildingFieldObject(int x, int y)
    {
        return buildingField[x][y];
    }

    public void setUnitField(int x, int y, Unit unit)
    {
        unitField[x][y] = unit;
    }

    public Unit getUnitField(int x, int y)
    {
        return unitField[x][y];
    }

    public int getCurrWinX()
    {
        return currentWindowX;
    }

    public int getCurrWinY()
    {
        return currentWindowY;
    }

    public List<ObjectElement> getObjects()
    {
        return objects;
    }

    public int[][] getFieldArray()
    {
        return field;
    }

    public List<Building> getBuildings()
    {
        return buildings;
    }

    public List<Unit> getUnits()
    {
        return units;
    }

    public SelectView getSelectView()
    {
        return botPanel.getSelectPanel();
    }

    public Player getBluePlayer()
    {
        return blue;
    }

    public Player getRedPlayer()
    {
        return red;
    }

    public TopPanel getTopPanel()
    {
        return topPanel;
    }

    public Mouse getMouse()
    {
        return mouse;

    }

    public BotPanel getBotPanel()
    {
        return botPanel;
    }

    public void playSound(File file)
    {
        try
        {
            AudioInputStream stream;
            AudioFormat format;
            DataLine.Info info;
            Clip clip;

            stream = AudioSystem.getAudioInputStream(file);
            format = stream.getFormat();
            info = new DataLine.Info(Clip.class, format);
            clip = (Clip) AudioSystem.getLine(info);
            clip.open(stream);
            clip.start();
        }
        catch (Exception e)
        {
            //whatevers
        }
    }

    public MainWindow getRootWindow()
    {
        return rootWindow;
    }

    public void setBuildAction(VillagerAction va)
    {
        this.action = va;
    }

}
