package View;

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
import Unit.Unit;
import Unit.Villager.Villager;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

public class GameBoard extends JPanel
{

    private int currentWindowX;
    private int currentWindowY;
    private final int columns = GameData.MAP_WIDTH / GameData.BOXSIZE;
    private final int rows = GameData.MAP_HEIGHT / GameData.BOXSIZE;
    private int[][] field = new int[rows][columns];
    private ObjectElement[][] objectField = new ObjectElement[columns][rows];
    private Unit[][] unitField = new Unit[columns][rows];
    private MapData mapData;
    private List<ObjectElement> objects;
    private List<Unit> units;
    private Mouse mouse;

    public GameBoard()
    {
        initGameBoard();

    }

    private void initGameBoard()
    {
        setPreferredSize(new Dimension(GameData.WINDOW_WIDTH, GameData.WINDOW_HEIGHT - 45 - 200));//velikost bot a top panelu -vysky jejich
        objects = new ArrayList<>();
        mapData = new MapData(this);
        mouse = new Mouse(this);
        
        objects = mapData.getMapData();
        units = new ArrayList<Unit>();
        
        generateGrass();
        
        
        units.add(new Villager(this, 30, 80, 0));
        setFocusable(true);
        addMouseListener(mouse);
        addMouseMotionListener(mouse);
        addKeyListener(new Key(this));
        
//        for(int i=0;i<columns;i++)
//        {
//            for(int j =0;j<rows;j++)
//            {
//                System.out.println(getFieldIndex(i,j));
//            }
//        }
        //generateDebug();
    }

    private void generateDebug()
    {

        objects.add(new Relic(this, 3, 4));
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

        for (ObjectElement ob : objects)
        {
            if (ob instanceof Water)
            {
                ob.drawObject(g);
            }
        }

        for (ObjectElement ob : objects)
        {
            if (ob instanceof Grass)
            {
                ob.drawObject(g);
            }
        }

        for (ObjectElement ob : objects)
        {
            if (ob instanceof Shoal)
            {
                ob.drawObject(g);
            }
        }

        for (ObjectElement ob : objects)
        {
            if (ob instanceof Sand)
            {
                ob.drawObject(g);
            }
        }

        for (ObjectElement ob : objects)
        {
            if (ob instanceof Tree)
            {
                ob.drawObject(g);
            }
        }

        for (ObjectElement ob : objects)
        {
            if (ob instanceof Bush)
            {
                ob.drawObject(g);
            }
        }

        for (ObjectElement ob : objects)
        {
            if (ob instanceof Gold)
            {
                ob.drawObject(g);
            }
        }

        for (ObjectElement ob : objects)
        {
            if (ob instanceof Stone)
            {
                ob.drawObject(g);
            }
        }

        for (ObjectElement ob : objects)
        {
            if (ob instanceof Relic)
            {
                ob.drawObject(g);
            }
        }

        for (ObjectElement ob : objects)
        {
            if (ob instanceof Cactus)
            {
                ob.drawObject(g);
            }
        }

        for (Unit u : units)
        {
            u.drawUnit(g);
        }
        mouse.drawRect(g);

        g.setColor(Color.red);
                
        for(int i=0;i<columns;i++)
        {
            for(int j =0;j<rows;j++)
            {
                g.drawString(""+getFieldIndex(i,j), convertX(i*25+12), convertY(j*25+12));
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

    List<ObjectElement> getObjects()
    {
        return objects;
    }

}
