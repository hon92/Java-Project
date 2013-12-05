/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Unit;

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
    
    private String team;
    
    private List<Unit> units;
    private List<Building> buildings;
    
    public Player(int wood, int gold, int stone, int food, String team)
    {
        units = new ArrayList<Unit>();
        buildings = new ArrayList<Building>();
        
        this.wood=wood;
        this.gold=gold;
        this.stone=stone;
        this.food=food;
        this.team =team;
    }
    
    public List getUnits()
    {
        return units;
    }
    
    public List getBuildings()
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
    
    
}
