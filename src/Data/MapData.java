/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import GameElement.Gold;
import GameElement.Relic;
import GameElement.Stone;
import GameElement.Tree;
import View.GameBoard;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Adam
 */
public class MapData
{

    private List<Tree> treeList;
    private List<Gold> goldList;
    private List<Stone> stoneList;
    private List<Relic> relicList;

    int matrixX;
    int matrixY;
    private char mapMatrix[][];

    private final char treeChar = 'T';
    private final char stoneChar = 'S';
    private final char goldChar = 'G';
    private final char relicChar = 'R';

    // WHAT THE FUCK ???
    private GameBoard gameBoard;
    // REALLY ???

    public MapData(GameBoard gameBoard)
    {
        this.gameBoard = gameBoard;

        treeList = new ArrayList<>();
        goldList = new ArrayList<>();
        stoneList = new ArrayList<>();
        relicList = new ArrayList<>();
        initMatrix();
        fillLists();
    }

    private void initMatrix()
    {
        try
        {
            Scanner reader = new Scanner(new FileInputStream("src/Resources/map.data"));

            List<String> loadedStrings = new ArrayList<>();

            while (reader.hasNextLine())
            {
                loadedStrings.add(reader.nextLine());
            }

            matrixX = loadedStrings.size();
            matrixY = loadedStrings.get(0).length();

            mapMatrix = new char[matrixX][matrixY];

            int i = 0;
            for (String string : loadedStrings)
            {
                for (int k = 0; k < matrixY; k++)
                {
                    mapMatrix[i][k] = string.charAt(k);
                }
                i++;
            }

            /* test jestli se dobre naplnilo
             for (int j = 0; j < matrixX; j++) {
             for (int k = 0;k < matrixY; k++) {
             System.out.print(mapMatrix[j][k]);
             }
             System.out.println();
             }
             */
        }
        catch (FileNotFoundException ex)
        {
            Logger.getLogger(MapData.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void fillLists()
    {
        for (int i = 0; i < matrixX; i++)
        {
            for (int j = 0; j < matrixY; j++)
            {
                switch (mapMatrix[i][j])
                {
                    case treeChar:
                        treeList.add(new Tree(gameBoard, i * 25, j * 25));
                        System.out.println("Added tree");
                        break;
                    case goldChar:
                        goldList.add(new Gold(gameBoard, i * 25, j * 25));
                        System.out.println("Added gold");
                        break;
                    case stoneChar:
                        stoneList.add(new Stone(gameBoard, i * 25, j * 25));
                        System.out.println("Added stone");
                        break;
                    case relicChar:
                        relicList.add(new Relic(gameBoard, i * 25, j * 25));
                        System.out.println("Added relic");
                        break;
                }
            }
        }
    }

    ;


    public List<Tree> getTreeList()
    {
        return treeList;
    }

    public List<Stone> getStoneList()
    {
        return stoneList;
    }

    public List<Gold> getGoldList()
    {
        return goldList;
    }

    public List<Relic> getRelicList()
    {
        return relicList;
    }
}
