/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Data.GameData;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author Honza
 */
public class BotPanel extends JPanel
{

    private GameBoard gameBoard;
    private MiniMap miniMap;
    private SelectView selectView;
    private ActionView actionView;

    BotPanel(GameBoard gameBoard)
    {
        this.gameBoard = gameBoard;
        setLayout(new BorderLayout());
        miniMap = new MiniMap(gameBoard);
        selectView = new SelectView(gameBoard);
        actionView = new ActionView(gameBoard);

        add(miniMap, BorderLayout.EAST);
        add(actionView, BorderLayout.WEST);
        add(selectView, BorderLayout.CENTER);

        setPreferredSize(new Dimension(GameData.WINDOW_WIDTH, 200));
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
    }

    public MiniMap getMiniMapPanel()
    {
        return miniMap;
    }

    public SelectView getSelectPanel()
    {
        return selectView;
    }

    public ActionView getActionPanel()
    {
        return actionView;
    }

}
