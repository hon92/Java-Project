/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Unit;

import Building.TownCenter.TownCenter;
import Buildings.Building;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tom
 */
public class Player
{

    private int wood;
    private int gold;
    private int stone;
    private int food;
    private int pop;
    private int actualPop;

    private String team;

    private List<Unit> units;
    private List<Building> buildings;
    private TownCenter tc;

    public Player(int wood, int gold, int stone, int food, String team)
    {
        units = new ArrayList<Unit>();
        buildings = new ArrayList<Building>();

        pop = 5;
        actualPop = 0;

        this.wood = wood;
        this.gold = gold;
        this.stone = stone;
        this.food = food;
        this.team = team;
    }

    public void setTownCenter(TownCenter tc)
    {
        this.tc = tc;
    }

    public boolean isTownCenterAlive()
    {
        if (tc != null)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public List<Unit> getUnits()
    {
        return units;
    }

    public List<Building> getBuildings()
    {
        return buildings;
    }

    public String getTeamName()
    {
        return team;
    }

    public void addUnit(Unit u)
    {
        units.add(u);
    }

    public void addBuilding(Building b)
    {
        buildings.add(b);
    }

    public int getWood()
    {
        return wood;
    }

    public int getFood()
    {
        return food;
    }

    public int getStone()
    {
        return stone;
    }

    public void setFood(int x)
    {
        food += x;
    }

    public void setGold(int x)
    {
        gold += x;
    }

    public void setStone(int x)
    {
        stone += x;
    }

    public void setWood(int x)
    {
        wood += x;
    }

    public int getGold()
    {
        return gold;
    }

    public int getMaxPop()
    {
        return pop;
    }

    public int getActualPop()
    {
        return actualPop;
    }

    public void setActualPop(int x)
    {
        actualPop += x;
    }

    public void setMaxPop(int x)
    {
        pop += x;
    }
}
