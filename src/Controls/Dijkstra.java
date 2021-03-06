package Controls;

import Data.GameData;
import View.GameBoard;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;

public class Dijkstra
{

    private ArrayList<ListItem> route;
    private ArrayList<ListItem> nodesToBeChecked;

    private boolean isVillager;
    private boolean targetSource;

    private ListItem start;
    private ListItem stop;
    private ListItem actualItem;
    private boolean found;
    private int rows = GameData.MAP_HEIGHT / GameData.BOXSIZE;
    private int columns = GameData.MAP_WIDTH / GameData.BOXSIZE;
    private ListItem[][] temp = new ListItem[columns][rows];

    private GameBoard gameBoard;

    public Dijkstra(ListItem start, ListItem end, GameBoard gameBoard)
    {
        route = new ArrayList<ListItem>();
        nodesToBeChecked = new ArrayList<ListItem>();
        stop = end;
        this.start = start;
        found = false;

        stop.setItem(end.getX(), end.getY() - 1);

        this.gameBoard = gameBoard;

        for (int i = 0; i < columns; i++)
        {
            for (int j = 0; j < rows; j++)
            {
                temp[i][j] = new ListItem(i, j);
                if (gameBoard.getFieldIndex(i, j) != 0 && j != 0 && gameBoard.getFieldIndex(i, j) != 20)
                {
                    temp[i][j - 1].setValue(1000);

                }
            }
        }

        for (int i = 0; i < columns; i++)
        {
            for (int j = 1; j < rows; j++)
            {

                if ((start.getX() == i) && (start.getY() == j))
                {
                    temp[i][j].setValue(0);
                    temp[i][j].setExamined();
                    start = temp[i][j];
                    actualItem = temp[i][j];
                    nodesToBeChecked.add(temp[i][j]);
                }

                if ((stop.getX() == i) && (stop.getY() == j))
                {
                    stop = temp[i][j];
                }
            }
        }

//        if(isVillager && targetSource)
//        {
//            while(!found)
//            {
//                if(start.getX()<stop.getX())
//                {
//                    if(gameBoard.getFieldIndex(stop.getX()-1, stop.getY())==0)
//                    {
//                        stop.setItem(stop.getX()-1, stop.getY());
//                        found = true;
//                        break;
//                    }
//
//                    if(gameBoard.getFieldIndex(stop.getX()-2, stop.getY())==0)
//                    {
//                        stop.setItem(stop.getX()-2, stop.getY());
//                        found = true;
//                        break;
//                    }
//
//
//
//                    if(gameBoard.getFieldIndex(stop.getX()+1, stop.getY())==0)
//                    {
//                        stop.setItem(stop.getX()+1, stop.getY());
//                        found = true;
//                        break;
//                    }
//
//                    if(gameBoard.getFieldIndex(stop.getX()+2, stop.getY())==0)
//                    {
//                        stop.setItem(stop.getX()+2, stop.getY());
//                        found = true;
//                        break;
//                    }
//
//
//
//                    if(gameBoard.getFieldIndex(stop.getX(), stop.getY()+1)==0)
//                    {
//                        stop.setItem(stop.getX(), stop.getY()+1);
//                        found = true;
//                        break;
//                    }
//
//                    if(gameBoard.getFieldIndex(stop.getX(), stop.getY()+2)==0)
//                    {
//                        stop.setItem(stop.getX(), stop.getY()+2);
//                        found = true;
//                        break;
//                    }
//
//                    if(gameBoard.getFieldIndex(stop.getX(), stop.getY()-1)==0)
//                    {
//                        stop.setItem(stop.getX(), stop.getY()-1);
//                        found = true;
//                        break;
//                    }
//
//                    if(gameBoard.getFieldIndex(stop.getX(), stop.getY()-2)==0)
//                    {
//                        stop.setItem(stop.getX(), stop.getY()-2);
//                        found = true;
//                        break;
//                    }
//                }
//
//                if(start.getX()>stop.getX())
//                {
//                    if(gameBoard.getFieldIndex(stop.getX()+1, stop.getY())==0)
//                    {
//                        stop.setItem(stop.getX()+1, stop.getY());
//                        found = true;
//                        break;
//                    }
//
//                    if(gameBoard.getFieldIndex(stop.getX()+2, stop.getY())==0)
//                    {
//                        stop.setItem(stop.getX()+2, stop.getY());
//                        found = true;
//                        break;
//                    }
//
//                   if(gameBoard.getFieldIndex(stop.getX()-1, stop.getY())==0)
//                    {
//                        stop.setItem(stop.getX()-1, stop.getY());
//                        found = true;
//                        break;
//                    }
//
//                    if(gameBoard.getFieldIndex(stop.getX()-2, stop.getY())==0)
//                    {
//                        stop.setItem(stop.getX()-2, stop.getY());
//                        found = true;
//                        break;
//                    }
//
//                    if(gameBoard.getFieldIndex(stop.getX(), stop.getY()+1)==0)
//                    {
//                        stop.setItem(stop.getX(), stop.getY()+1);
//                        found = true;
//                        break;
//                    }
//
//                    if(gameBoard.getFieldIndex(stop.getX(), stop.getY()+2)==0)
//                    {
//                        stop.setItem(stop.getX(), stop.getY()+2);
//                        found = true;
//                        break;
//                    }
//
//                    if(gameBoard.getFieldIndex(stop.getX(), stop.getY()-1)==0)
//                    {
//                        stop.setItem(stop.getX(), stop.getY()-1);
//                        found = true;
//                        break;
//                    }
//
//                    if(gameBoard.getFieldIndex(stop.getX(), stop.getY()-2)==0)
//                    {
//                        stop.setItem(stop.getX(), stop.getY()-2);
//                        found = true;
//                        break;
//                    }
//
//
//                }
//
//
//                if(start.getY()>stop.getY())
//                {
//
//                    if(gameBoard.getFieldIndex(stop.getX(), stop.getY()+1)==0)
//                    {
//                        stop.setItem(stop.getX(), stop.getY()+1);
//                        found = true;
//                        break;
//                    }
//
//                    if(gameBoard.getFieldIndex(stop.getX(), stop.getY()+2)==0)
//                    {
//                        stop.setItem(stop.getX(), stop.getY()+2);
//                        found = true;
//                        break;
//                    }
//
//                    if(gameBoard.getFieldIndex(stop.getX(), stop.getY()-1)==0)
//                    {
//                        stop.setItem(stop.getX(), stop.getY()-1);
//                        found = true;
//                        break;
//                    }
//
//                    if(gameBoard.getFieldIndex(stop.getX(), stop.getY()-2)==0)
//                    {
//                        stop.setItem(stop.getX(), stop.getY()-2);
//                        found = true;
//                        break;
//                    }
//
//                    if(gameBoard.getFieldIndex(stop.getX()+1, stop.getY())==0)
//                    {
//                        stop.setItem(stop.getX()+1, stop.getY());
//                        found = true;
//                        break;
//                    }
//
//                    if(gameBoard.getFieldIndex(stop.getX()+2, stop.getY())==0)
//                    {
//                        stop.setItem(stop.getX()+2, stop.getY());
//                        found = true;
//                        break;
//                    }
//
//                   if(gameBoard.getFieldIndex(stop.getX()-1, stop.getY())==0)
//                    {
//                        stop.setItem(stop.getX()-1, stop.getY());
//                        found = true;
//                        break;
//                    }
//
//                    if(gameBoard.getFieldIndex(stop.getX()-2, stop.getY())==0)
//                    {
//                        stop.setItem(stop.getX()-2, stop.getY());
//                        found = true;
//                        break;
//                    }
//
//
//
//
//                }
//
//                if(start.getY()>stop.getY())
//                {
//                    if(gameBoard.getFieldIndex(stop.getX()-1, stop.getY())==0)
//                    {
//                        stop.setItem(stop.getX()-1, stop.getY());
//                        found = true;
//                        break;
//                    }
//
//                    if(gameBoard.getFieldIndex(stop.getX()-2, stop.getY())==0)
//                    {
//                        stop.setItem(stop.getX()-2, stop.getY());
//                        found = true;
//                        break;
//                    }
//                    if(gameBoard.getFieldIndex(stop.getX()+1, stop.getY())==0)
//                    {
//                        stop.setItem(stop.getX()+1, stop.getY());
//                        found = true;
//                        break;
//                    }
//
//                    if(gameBoard.getFieldIndex(stop.getX()+2, stop.getY())==0)
//                    {
//                        stop.setItem(stop.getX()+2, stop.getY());
//                        found = true;
//                        break;
//                    }
//
//
//
//
//                    if(gameBoard.getFieldIndex(stop.getX(), stop.getY()+1)==0)
//                    {
//                        stop.setItem(stop.getX(), stop.getY()+1);
//                        found = true;
//                        break;
//                    }
//
//                    if(gameBoard.getFieldIndex(stop.getX(), stop.getY()+2)==0)
//                    {
//                        stop.setItem(stop.getX(), stop.getY()+2);
//                        found = true;
//                        break;
//                    }
//
//                    if(gameBoard.getFieldIndex(stop.getX(), stop.getY()-1)==0)
//                    {
//                        stop.setItem(stop.getX(), stop.getY()-1);
//                        found = true;
//                        break;
//                    }
//
//                    if(gameBoard.getFieldIndex(stop.getX(), stop.getY()-2)==0)
//                    {
//                        stop.setItem(stop.getX(), stop.getY()-2);
//                        found = true;
//                        break;
//                    }
//
//
//
//
//
//
//                }
//
//
//            }
//
//
//        }
        search();
    }

    public boolean gay(ListItem stop, ListItem curr)
    {
        String a = stop.getItem();
        String b = curr.getItem();
        if (a.equals(b))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public void drawPath(Graphics g)
    {

        g.setColor(Color.blue);
        for (int i = 1; i < route.size(); i++)
        {
            g.fillRect(gameBoard.convertX(route.get(i).getX() * 25), gameBoard.convertY((route.get(i).getY() + 1) * 25), 25, 25);
        }
        for (int i = 0; i < temp.length; i++)
        {
            for (int j = 0; j < temp[0].length - 2; j++)
            {
                String val = new Integer(temp[i][j].getValue()).toString();
                g.setColor(Color.yellow);
                g.drawString(val, gameBoard.convertX(temp[i][j + 2].getX() * 25 + 10), gameBoard.convertY((temp[i][j + 2].getY() * 25 + 1 - 10)));

            }

        }
    }

    public void search()
    {
        {
            for (int i = 0; i < nodesToBeChecked.size(); i++)
            {
                actualItem = nodesToBeChecked.get(i);
                if ((actualItem.getX() + 1) < columns)
                {
                    if ((temp[actualItem.getX() + 1][actualItem.getY()].notVisited())//test doprava
                            && (temp[actualItem.getX() + 1][actualItem.getY()].notExamined())
                            && (temp[actualItem.getX() + 1][actualItem.getY()].getValue() != 1000))

                    {
                        temp[actualItem.getX() + 1][actualItem.getY()].setValue(actualItem.getValue() + 1);
                        temp[actualItem.getX() + 1][actualItem.getY()].setExamined();
                        nodesToBeChecked.add(temp[actualItem.getX() + 1][actualItem.getY()]);

                        if (gay(stop, temp[actualItem.getX() + 1][actualItem.getY()]))
                        {
                            found = true;
                            break;
                        }
                    }
                }

                if (((actualItem.getX() + 1) < columns) && (actualItem.getY() + 1 < rows))
                {
                    if ((temp[actualItem.getX() + 1][actualItem.getY() + 1].notVisited()) // test doprava dolu
                            && (temp[actualItem.getX() + 1][actualItem.getY() + 1].notExamined())
                            && (temp[actualItem.getX() + 1][actualItem.getY() + 1].getValue() != 1000))

                    {
                        temp[actualItem.getX() + 1][actualItem.getY() + 1].setValue(actualItem.getValue() + 1);
                        temp[actualItem.getX() + 1][actualItem.getY() + 1].setExamined();
                        nodesToBeChecked.add(temp[actualItem.getX() + 1][actualItem.getY() + 1]);

                        if (gay(stop, temp[actualItem.getX() + 1][actualItem.getY() + 1]))
                        {
                            found = true;
                            break;
                        }
                    }
                }

                if ((actualItem.getY() + 1) < rows)
                {
                    if ((temp[actualItem.getX()][actualItem.getY() + 1].notVisited())// test dolu
                            && (temp[actualItem.getX()][actualItem.getY() + 1].notExamined())
                            && (temp[actualItem.getX()][actualItem.getY() + 1].getValue() != 1000))

                    {
                        temp[actualItem.getX()][actualItem.getY() + 1].setValue(actualItem.getValue() + 1);
                        temp[actualItem.getX()][actualItem.getY() + 1].setExamined();
                        nodesToBeChecked.add(temp[actualItem.getX()][actualItem.getY() + 1]);

                        if (gay(stop, temp[actualItem.getX()][actualItem.getY() + 1]))
                        {
                            found = true;
                            break;
                        }
                    }
                }

                if ((actualItem.getY() - 1) >= 0)
                {
                    if ((temp[actualItem.getX()][actualItem.getY() - 1].notVisited()) // test nahoru
                            && (temp[actualItem.getX()][actualItem.getY() - 1].notExamined())
                            && (temp[actualItem.getX()][actualItem.getY() - 1].getValue() != 1000))

                    {
                        temp[actualItem.getX()][actualItem.getY() - 1].setValue(actualItem.getValue() + 1);
                        temp[actualItem.getX()][actualItem.getY() - 1].setExamined();
                        nodesToBeChecked.add(temp[actualItem.getX()][actualItem.getY() - 1]);

                        if (gay(stop, temp[actualItem.getX()][actualItem.getY() - 1]))
                        {
                            found = true;
                            break;
                        }
                    }
                }

                if ((actualItem.getX() - 1) >= 0)
                {
                    if ((temp[actualItem.getX() - 1][actualItem.getY()].notVisited())//test doleva
                            && (temp[actualItem.getX() - 1][actualItem.getY()].notExamined())
                            && (temp[actualItem.getX() - 1][actualItem.getY()].getValue() != 1000))

                    {
                        temp[actualItem.getX() - 1][actualItem.getY()].setValue(actualItem.getValue() + 1);
                        temp[actualItem.getX() - 1][actualItem.getY()].setExamined();
                        nodesToBeChecked.add(temp[actualItem.getX() - 1][actualItem.getY()]);

                        if (gay(stop, temp[actualItem.getX() - 1][actualItem.getY()]))
                        {
                            found = true;
                            break;
                        }
                    }
                }

                if (((actualItem.getX() - 1) >= 0) && (actualItem.getY() - 1 >= 0))
                {
                    if ((temp[actualItem.getX() - 1][actualItem.getY() - 1].notVisited())//test doleva nahoru
                            && (temp[actualItem.getX() - 1][actualItem.getY() - 1].notExamined())
                            && (temp[actualItem.getX() - 1][actualItem.getY() - 1].getValue() != 1000))

                    {
                        temp[actualItem.getX() - 1][actualItem.getY() - 1].setValue(actualItem.getValue() + 1);
                        temp[actualItem.getX() - 1][actualItem.getY() - 1].setExamined();
                        nodesToBeChecked.add(temp[actualItem.getX() - 1][actualItem.getY() - 1]);

                        if (gay(stop, temp[actualItem.getX() - 1][actualItem.getY() - 1]))
                        {
                            found = true;
                            break;
                        }
                    }
                }

                if (((actualItem.getX() + 1) < columns) && (actualItem.getY() - 1 >= 0))
                {
                    if ((temp[actualItem.getX() + 1][actualItem.getY() - 1].notVisited())//test doprava nahoru
                            && (temp[actualItem.getX() + 1][actualItem.getY() - 1].notExamined())
                            && (temp[actualItem.getX() + 1][actualItem.getY() - 1].getValue() != 1000))

                    {
                        temp[actualItem.getX() + 1][actualItem.getY() - 1].setValue(actualItem.getValue() + 1);
                        temp[actualItem.getX() + 1][actualItem.getY() - 1].setExamined();
                        nodesToBeChecked.add(temp[actualItem.getX() + 1][actualItem.getY() - 1]);

                        if (gay(stop, temp[actualItem.getX() + 1][actualItem.getY() - 1]))
                        {
                            found = true;
                            break;
                        }
                    }
                }

                if (((actualItem.getX() - 1) >= 0) && (actualItem.getY() + 1 < rows))
                {
                    if ((temp[actualItem.getX() - 1][actualItem.getY() + 1].notVisited()) // test doleva dolu
                            && (temp[actualItem.getX() - 1][actualItem.getY() + 1].notExamined())
                            && (temp[actualItem.getX() - 1][actualItem.getY() + 1].getValue() != 1000))

                    {
                        temp[actualItem.getX() - 1][actualItem.getY() + 1].setValue(actualItem.getValue() + 1);
                        temp[actualItem.getX() - 1][actualItem.getY() + 1].setExamined();
                        nodesToBeChecked.add(temp[actualItem.getX() - 1][actualItem.getY() + 1]);

                        if (gay(stop, temp[actualItem.getX() - 1][actualItem.getY() + 1]))
                        {
                            found = true;
                            break;
                        }
                    }
                }
                actualItem.setVisited();
            }

        }

        if (found)
        {
            getRoute();
        }

    }

    public void writeArray()
    {
//        for (int i = 0; i < N; i++)
//        {
//            System.out.print(i + " ");
//        }
//        System.out.println();
//        for (int i = 0; i < N; i++)
//        {
//            System.out.print("- ");
//        }
//        System.out.println();
//
//        for (int i = 0; i < N; i++)
//        {
//            for (int j = 0; j < N; j++)
//            {
//                System.out.print(temp[i][j].getValue() + " ");
//
//            }
//            System.out.println("|" + i);
//        }
//        System.out.println();
//        System.out.println("Starting point: " + start.getItem());
//        System.out.println("Finish: " + stop.getItem());
    }

    public void getRoute()
    {
        ListItem path = new ListItem(stop.getX(), stop.getY());
        path = stop;
        route.add(path);

        while (!(gay(start, path)))
        {
            if ((path.getY() + 1) < rows)
            {
                if (temp[path.getX()][path.getY() + 1].getValue() < path.getValue())
                {
                    path = temp[path.getX()][path.getY() + 1];
                    path.setDirection(270);
                    route.add(path);
                    continue;
                }
            }

            if ((path.getX() + 1) < columns)
            {
                if (temp[path.getX() + 1][path.getY()].getValue() < path.getValue())
                {
                    path = temp[path.getX() + 1][path.getY()];
                    path.setDirection(180);
                    route.add(path);
                    continue;
                }
            }

            if ((path.getY() - 1) >= 0)
            {
                if (temp[path.getX()][path.getY() - 1].getValue() < path.getValue())
                {
                    path = temp[path.getX()][path.getY() - 1];
                    path.setDirection(90);
                    route.add(path);
                    continue;
                }
            }

            if ((path.getX() - 1) >= 0)
            {
                if (temp[path.getX() - 1][path.getY()].getValue() < path.getValue())
                {
                    path = temp[path.getX() - 1][path.getY()];
                    path.setDirection(0);
                    route.add(path);
                    continue;
                }
            }

            if (((path.getX() + 1) < columns) && (path.getY() + 1 < rows))
            {
                if (temp[path.getX() + 1][path.getY() + 1].getValue() < path.getValue())
                {
                    path = temp[path.getX() + 1][path.getY() + 1];
                    path.setDirection(225);
                    route.add(path);
                    continue;
                }
            }

            if (((path.getX() - 1) >= 0) && (path.getY() - 1 >= 0))
            {
                if (temp[path.getX() - 1][path.getY() - 1].getValue() < path.getValue())
                {
                    path = temp[path.getX() - 1][path.getY() - 1];
                    path.setDirection(45);
                    route.add(path);
                    continue;
                }
            }

            if (((path.getX() + 1) < columns) && (path.getY() - 1 >= 0))
            {
                if (temp[path.getX() + 1][path.getY() - 1].getValue() < path.getValue())
                {
                    path = temp[path.getX() + 1][path.getY() - 1];
                    path.setDirection(135);
                    route.add(path);
                    continue;
                }
            }

            if (((path.getX() - 1) >= 0) && (path.getY() + 1 < rows))
            {
                if (temp[path.getX() - 1][path.getY() + 1].getValue() < path.getValue())
                {
                    path = temp[path.getX() - 1][path.getY() + 1];
                    path.setDirection(315);
                    route.add(path);
                    continue;
                }
            }
        }
    }

    public void givePath()
    {
        for (int i = route.size() - 1; i >= 0; i--)
        {
            System.out.print(route.get(i).getItem() + "-> ");

        }
        System.out.println();
    }

    public ArrayList<ListItem> getPath()
    {
        Collections.reverse(route);
        return route;
    }

}
