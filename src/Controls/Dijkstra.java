package Controls;

import Data.GameData;
import java.util.ArrayList;
import java.util.Collections;

public class Dijkstra
{

    private ArrayList<ListItem> route;
    private ArrayList<ListItem> nodesToBeChecked;

    private ListItem start;
    private ListItem stop;
    private ListItem actualItem;
    private int map[][];
    private int rows = GameData.MAP_HEIGHT / GameData.BOXSIZE;
    private int columns = GameData.MAP_WIDTH / GameData.BOXSIZE;
    private ListItem[][] temp = new ListItem[rows][columns];

    private int actualValue;

    public Dijkstra(ListItem start, ListItem end, int mapInt[][])
    {
        route = new ArrayList<ListItem>();
        nodesToBeChecked = new ArrayList<ListItem>();
        stop = end;
        this.start = start;
        map = mapInt;
        //actualValue = 1;

        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < columns; j++)
            {
                temp[i][j] = new ListItem(i, j);
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

    public void search()
    {
        {
            for (int i = 0; i < nodesToBeChecked.size(); i++)
            {
                actualItem = nodesToBeChecked.get(i);
                if ((actualItem.getX() + 1) < columns)
                {
                    if ((temp[actualItem.getX() + 1][actualItem.getY()].notVisited()) && (temp[actualItem.getX() + 1][actualItem.getY()].notExamined()) && (map[actualItem.getY() + 1][actualItem.getX()] == 0))
                    {
                        temp[actualItem.getX() + 1][actualItem.getY()].setValue(actualItem.getValue() + 1);
                        temp[actualItem.getX() + 1][actualItem.getY()].setExamined();
                        nodesToBeChecked.add(temp[actualItem.getX() + 1][actualItem.getY()]);

                        if (gay(stop, temp[actualItem.getX() + 1][actualItem.getY()]))
                        {
                            break;
                        }
                    }
                }

                if (((actualItem.getX() + 1) < columns) && (actualItem.getY() + 1 < rows))
                {
                    if ((temp[actualItem.getX() + 1][actualItem.getY() + 1].notVisited()) && (temp[actualItem.getX() + 1][actualItem.getY() + 1].notExamined()) && (map[actualItem.getY() + 1][actualItem.getX() + 1] == 0))
                    {
                        temp[actualItem.getX() + 1][actualItem.getY() + 1].setValue(actualItem.getValue() + 1);
                        temp[actualItem.getX() + 1][actualItem.getY() + 1].setExamined();
                        nodesToBeChecked.add(temp[actualItem.getX() + 1][actualItem.getY() + 1]);

                        if (gay(stop, temp[actualItem.getX() + 1][actualItem.getY() + 1]))
                        {
                            break;
                        }
                    }
                }

                if ((actualItem.getY() + 1) < rows)
                {
                    if ((temp[actualItem.getX()][actualItem.getY() + 1].notVisited()) && (temp[actualItem.getX()][actualItem.getY() + 1].notExamined()) && (map[actualItem.getY()][actualItem.getX() + 1] == 0))
                    {
                        temp[actualItem.getX()][actualItem.getY() + 1].setValue(actualItem.getValue() + 1);
                        temp[actualItem.getX()][actualItem.getY() + 1].setExamined();
                        nodesToBeChecked.add(temp[actualItem.getX()][actualItem.getY() + 1]);

                        if (gay(stop, temp[actualItem.getX()][actualItem.getY() + 1]))
                        {
                            break;
                        }
                    }
                }

                if ((actualItem.getY() - 1) >= 0)
                {
                    if ((temp[actualItem.getX()][actualItem.getY() - 1].notVisited()) && (temp[actualItem.getX()][actualItem.getY() - 1].notExamined()) && (map[actualItem.getY()][actualItem.getX() - 1] == 0))
                    {
                        temp[actualItem.getX()][actualItem.getY() - 1].setValue(actualItem.getValue() + 1);
                        temp[actualItem.getX()][actualItem.getY() - 1].setExamined();
                        nodesToBeChecked.add(temp[actualItem.getX()][actualItem.getY() - 1]);

                        if (gay(stop, temp[actualItem.getX()][actualItem.getY() - 1]))
                        {
                            break;
                        }
                    }
                }

                if ((actualItem.getX() - 1) >= 0)
                {
                    if ((temp[actualItem.getX() - 1][actualItem.getY()].notVisited()) && (temp[actualItem.getX() - 1][actualItem.getY()].notExamined()) && (map[actualItem.getY() - 1][actualItem.getX()] == 0))
                    {
                        temp[actualItem.getX() - 1][actualItem.getY()].setValue(actualItem.getValue() + 1);
                        temp[actualItem.getX() - 1][actualItem.getY()].setExamined();
                        nodesToBeChecked.add(temp[actualItem.getX() - 1][actualItem.getY()]);

                        if (gay(stop, temp[actualItem.getX() - 1][actualItem.getY()]))
                        {
                            break;
                        }
                    }
                }

                if (((actualItem.getX() - 1) >= 0) && (actualItem.getY() - 1 >= 0))
                {
                    if ((temp[actualItem.getX() - 1][actualItem.getY() - 1].notVisited()) && (temp[actualItem.getX() - 1][actualItem.getY() - 1].notExamined()) && (map[actualItem.getY() - 1][actualItem.getX() - 1] == 0))
                    {
                        temp[actualItem.getX() - 1][actualItem.getY() - 1].setValue(actualItem.getValue() + 1);
                        temp[actualItem.getX() - 1][actualItem.getY() - 1].setExamined();
                        nodesToBeChecked.add(temp[actualItem.getX() - 1][actualItem.getY() - 1]);

                        if (gay(stop, temp[actualItem.getX() - 1][actualItem.getY() - 1]))
                        {
                            break;
                        }
                    }
                }

                if (((actualItem.getX() + 1) < columns) && (actualItem.getY() - 1 >= 0))
                {
                    if ((temp[actualItem.getX() + 1][actualItem.getY() - 1].notVisited()) && (temp[actualItem.getX() + 1][actualItem.getY() - 1].notExamined()) && (map[actualItem.getY() + 1][actualItem.getX() - 1] == 0))
                    {
                        temp[actualItem.getX() + 1][actualItem.getY() - 1].setValue(actualItem.getValue() + 1);
                        temp[actualItem.getX() + 1][actualItem.getY() - 1].setExamined();
                        nodesToBeChecked.add(temp[actualItem.getX() + 1][actualItem.getY() - 1]);

                        if (gay(stop, temp[actualItem.getX() + 1][actualItem.getY() - 1]))
                        {
                            break;
                        }
                    }
                }

                if (((actualItem.getX() - 1) >= 0) && (actualItem.getY() + 1 < rows))
                {
                    if ((temp[actualItem.getX() - 1][actualItem.getY() + 1].notVisited()) && (temp[actualItem.getX() - 1][actualItem.getY() + 1].notExamined()) && (map[actualItem.getY() - 1][actualItem.getX() + 1] == 0))
                    {
                        temp[actualItem.getX() - 1][actualItem.getY() + 1].setValue(actualItem.getValue() + 1);
                        temp[actualItem.getX() - 1][actualItem.getY() + 1].setExamined();
                        nodesToBeChecked.add(temp[actualItem.getX() - 1][actualItem.getY() + 1]);

                        if (gay(stop, temp[actualItem.getX() - 1][actualItem.getY() + 1]))
                        {
                            break;
                        }
                    }
                }
                actualItem.setVisited();
            }

            actualValue++;

        }
        getRoute();

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
            if ((path.getX() + 1) < columns)
            {
                if (temp[path.getX() + 1][path.getY()].getValue() < path.getValue())
                {
                    path = temp[path.getX() + 1][path.getY()];
                    route.add(path);
                    continue;
                }
            }

            if (((path.getX() + 1) < columns) && (path.getY() + 1 < rows))
            {
                if (temp[path.getX() + 1][path.getY() + 1].getValue() < path.getValue())
                {
                    path = temp[path.getX() + 1][path.getY() + 1];
                    route.add(path);
                    continue;
                }
            }

            if ((path.getY() + 1) < rows)
            {
                if (temp[path.getX()][path.getY() + 1].getValue() < path.getValue())
                {
                    path = temp[path.getX()][path.getY() + 1];
                    route.add(path);
                    continue;
                }
            }

            if ((path.getY() - 1) >= 0)
            {
                if (temp[path.getX()][path.getY() - 1].getValue() < path.getValue())
                {
                    path = temp[path.getX()][path.getY() - 1];
                    route.add(path);
                    continue;
                }
            }

            if ((path.getX() - 1) >= 0)
            {
                if (temp[path.getX() - 1][path.getY()].getValue() < path.getValue())
                {
                    path = temp[path.getX() - 1][path.getY()];
                    route.add(path);
                    continue;
                }
            }

            if (((path.getX() - 1) >= 0) && (path.getY() - 1 >= 0))
            {
                if (temp[path.getX() - 1][path.getY() - 1].getValue() < path.getValue())
                {
                    path = temp[path.getX() - 1][path.getY() - 1];
                    route.add(path);
                    continue;
                }
            }

            if (((path.getX() + 1) < columns) && (path.getY() - 1 >= 0))
            {
                if (temp[path.getX() + 1][path.getY() - 1].getValue() < path.getValue())
                {
                    path = temp[path.getX() + 1][path.getY() - 1];
                    route.add(path);
                    continue;
                }
            }

            if (((path.getX() - 1) >= 0) && (path.getY() + 1 < rows))
            {
                if (temp[path.getX() - 1][path.getY() + 1].getValue() < path.getValue())
                {
                    path = temp[path.getX() - 1][path.getY() + 1];
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
