/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

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

    private List<ObjectElement> objects;

    int matrixX;
    int matrixY;
    private char mapMatrix[][];

    private final char treeChar = 'T';
    private final char stoneChar = 'S';
    private final char goldChar = 'G';
    private final char relicChar = 'R';
    // new added
    private final char bushChar = 'B';
    private final char cactusChar = 'C';
    private final char sandChar = 'A';
    private final char shoalChar = 'H';
    private final char waterChar = 'W';
    private final char grassChar = 'I';

    // WHAT THE FUCK ???
    private GameBoard gameBoard;
    // REALLY ???

    public MapData(GameBoard gameBoard)
    {
        this.gameBoard = gameBoard;
        this.objects = new ArrayList<>();

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
        for (int i = 0; i < matrixY; i++)
        {
            for (int j = 0; j < matrixX; j++)
            {
                switch (mapMatrix[j][i])
                {
                    case treeChar:
                        objects.add(new Tree(gameBoard, j, i));
                        //System.out.println("Added tree");
                        break;
                    case goldChar:
                        objects.add(new Gold(gameBoard, j, i));
                        //System.out.println("Added gold");
                        break;
                    case stoneChar:
                        objects.add(new Stone(gameBoard, j, i));
                        //System.out.println("Added stone");
                        break;
                    case relicChar:
                        objects.add(new Relic(gameBoard, j, i));
                        //System.out.println("Added relic");
                        break;
                    case bushChar:
                        objects.add(new Bush(gameBoard, j, i));
                        //System.out.println("Added bush");
                        break;
                    case cactusChar:
                        objects.add(new Cactus(gameBoard, j, i));
                        //System.out.println("Added cactus");
                        break;
                    case grassChar:
                        objects.add(new Grass(gameBoard, j, i));
                        //System.out.println("Added grass");
                        break;
                    case sandChar:
                        objects.add(new Sand(gameBoard, j, i));
                        //System.out.println("Added sand");
                        break;
                    case shoalChar:
                        objects.add(new Shoal(gameBoard, j, i));
                        //System.out.println("Added shoal");
                        break;
                    case waterChar:
                        objects.add(new Water(gameBoard, j, i));
                        //System.out.println("Added water");
                        break;
                }
            }
        }
    }

    public List<ObjectElement> getMapData()
    {
        return objects;
    }
}
