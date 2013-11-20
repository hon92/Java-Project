package View;

import Controls.Key;
import Controls.Mouse;
import Data.GameData;
import Data.MapData;
import GameElement.Bush;
import GameElement.Cactus;
import GameElement.Gold;
import GameElement.Grass;
import GameElement.Relic;
import GameElement.Sand;
import GameElement.Shoal;
import GameElement.Stone;
import GameElement.Tree;
import GameElement.Water;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class GameBoard extends JPanel
{

    private BufferedImage topPanel;
    private BufferedImage botPanel;
    private MiniMap miniMap;
    private int currentWindowX;
    private int currentWindowY;
    private final int lenX = GameData.MAP_WIDTH / GameData.BOXSIZE;
    private final int lenY = GameData.MAP_HEIGHT / GameData.BOXSIZE;
    private int[][] field = new int[lenX][lenY];
    private MapData mapData;

    private List<Cactus> cactusList;
    private List<Bush> bushList;
    private List<Grass> grassList;
    private List<Relic> relicList;
    private List<Tree> treesList;
    private List<Gold> goldList;
    private List<Stone> stoneList;
    private List<Water> waterList;
    private List<Shoal> shoalList;
    private List<Sand> sandList;

    public GameBoard()
    {
        initGameBoard();
    }

    private void initGameBoard()
    {

        miniMap = new MiniMap();
        mapData = new MapData(this);

        grassList = new ArrayList<>();
        relicList = mapData.getRelicList();
        treesList = mapData.getTreeList();
        goldList = mapData.getGoldList();
        stoneList = mapData.getStoneList();
        bushList = mapData.getBushList();
        cactusList = mapData.getCactusList();
        waterList = mapData.getWaterList();
        shoalList = mapData.getShoalList();
        sandList = mapData.getSandList();

        try
        {
            topPanel = ImageIO.read(new File("src/Resources/topPanel.png"));
            botPanel = ImageIO.read(new File("src/Resources/botPanel.png"));
        }
        catch (IOException ex)
        {
            Logger.getLogger(GameBoard.class.getName()).log(Level.SEVERE, null, ex);
        }
        setFocusable(true);
        addMouseListener(new Mouse(this));
        addKeyListener(new Key(this));

        generateGrass();
        generateWater();
        generateTrees();
        generateRelics();
        generateGold();
        generateStone();
        generateCactus();
        generateBush();
        generateShoal();
        generateSand();

    }

    private void generateSand()
    {
        Sand gr = new Sand(this, 0, 0);
        sandList.add(gr);
        for (int i = 0; i < GameData.MAP_WIDTH; i += 200)
        {
            gr = new Sand(this, i, GameData.MAP_HEIGHT / 10 + 50);
            sandList.add(gr);
        }
    }

    private void generateShoal()
    {
        Shoal gr = new Shoal(this, 0, 0);
        shoalList.add(gr);
        for (int i = 0; i < GameData.MAP_WIDTH; i += 200)
        {
            gr = new Shoal(this, i, GameData.MAP_HEIGHT / 10);
            shoalList.add(gr);
        }
    }

    private void generateWater()
    {
        Water gr = new Water(this, 0, 0);
        waterList.add(gr);
        for (int i = 0; i < GameData.MAP_WIDTH; i += 200)
        {
            for (int j = 0; j < GameData.MAP_HEIGHT / 10; j += 200)
            {
                gr = new Water(this, i, j);
                waterList.add(gr);
            }
        }
    }

    private void generateStone()
    {
        Gold gold = new Gold(this, 600, 500);
        goldList.add(gold);
    }

    private void generateGold()
    {
        Stone stone = new Stone(this, 600, 600);
        stoneList.add(stone);
    }

    private void generateTrees()
    {
        Tree tr = new Tree(this, 150, 150);
        treesList.add(tr);
        field[6][6] = 2;
        field[6][7] = 2;
        field[7][6] = 2;
        field[7][7] = 2;

        tr = new Tree(this, 400, 150);
        treesList.add(tr);
        field[16][6] = 2;
        field[16][7] = 2;
        field[17][6] = 2;
        field[17][7] = 2;

        tr = new Tree(this, 450, 150);
        treesList.add(tr);

        tr = new Tree(this, 450, 200);
        treesList.add(tr);
    }

    private void generateGrass()
    {
        Grass gr = new Grass(this, 0, 0);
        grassList.add(gr);
        for (int i = 0; i < GameData.MAP_WIDTH; i += 400)
        {
            for (int j = 0; j < GameData.MAP_HEIGHT; j += 400)
            {
                gr = new Grass(this, i, j);
                grassList.add(gr);
            }
        }

        for (int i = 0; i < lenX; i++)
        {
            for (int j = 0; j < lenY; j++)
            {
                field[i][j] = 1;
            }
        }

    }

    private void generateRelics()
    {
        Relic r = new Relic(this, 200, 200);
        relicList.add(r);
        field[8][8] = 3;

        r = new Relic(this, 500, 500);
        relicList.add(r);
        field[20][20] = 3;
    }

    private void generateCactus()
    {
        Cactus c = new Cactus(this, 75, 75);
        cactusList.add(c);
    }

    private void generateBush()
    {
        Bush b = new Bush(this, 250, 150);
        bushList.add(b);
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

        for (Grass grass : grassList)
        {
            grass.draw(g);
        }
        for (Water water : waterList)
        {
            water.draw(g);
        }
        for (Shoal shoal : shoalList)
        {
            shoal.draw(g);
        }
        for (Sand sand : sandList)
        {
            sand.draw(g);
        }

        for (Tree t : treesList)
        {
            t.draw(g);
        }
        for (Relic r : relicList)
        {
            r.draw(g);
        }
        for (Gold gold : goldList)
        {
            gold.draw(g);
        }
        for (Stone stone : stoneList)
        {
            stone.draw(g);
        }
        for (Bush bush : bushList)
        {
            bush.draw(g);
        }
        for (Cactus cactus : cactusList)
        {
            cactus.draw(g);
        }

        g.drawImage(topPanel, 0, 0, null);
        g.drawImage(botPanel, 0, GameData.WINDOW_HEIGHT - 235, null);
        miniMap.drawMiniMap(g);

    }

}
