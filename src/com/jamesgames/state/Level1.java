/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jamesgames.state;

import com.jamesgames.entity.Player;
import com.jamesgames.main.LevelPanel;
import com.jamesgames.tilemap.TileMapManager;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;

/**
 *
 * @author 1623326
 */
public class Level1 extends LevelState{
    
    private Player p;
    
    private TileMapManager tmm;
    
    private boolean win;
    
    //private Enemy[] enemies;
    
    //private Collectable[] collectables;
    
    public Level1(LevelManager lm)
    {
        super(lm);
        tmm = new TileMapManager();
        p = new Player("/images/Player.png", tmm);
        
        win = false;
        init();
        
        /*
        initEnemies();
        initCollectables();
        */
    }
    
    private void init()
    {
        
    }

    @Override
    public void keyPressed(int keyCode) {
        if(keyCode == KeyEvent.VK_A)
            p.moveLeft(true);
        
        if(keyCode == KeyEvent.VK_D)
            p.moveRight(true);
        
        //if(keyCode == KeyEvent.VK_W)
            //p.Jump();
    }

    @Override
    public void keyReleased(int keyCode) {
        if(keyCode == KeyEvent.VK_A)
            p.moveLeft(false);
        
        if(keyCode == KeyEvent.VK_D)
            p.moveRight(false);
        
        if(keyCode == KeyEvent.VK_W)
            p.Jump();
    }

    @Override
    public void mouseClicked(Point mouseClick) {
        
    }

    @Override
    public void update() {
        p.update();
       
        //p.checkEnemyCollision(enemies);
        //p.checkCollectableCollision(collectables);
        
        tmm.setCamPos((int) LevelPanel.PANEL_WIDTH/2 - p.getX(), (int)LevelPanel.PANEL_HEIGHT/2 - p.getY());
        
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.BLUE);
        g.fillRect(0, 0, LevelPanel.PANEL_WIDTH, LevelPanel.PANEL_HEIGHT);
        
        tmm.draw(g);
        p.draw(g);
    }
    
    /*
    private void initEnemies()
    {
        enemies = new Enemy[5];
        
        for(int i=0; i < enemies.length; i++)
            enemies[i] = new Enemy();
    }
    
    private void initCollectables()
    {
        collectables = new Collectables[5];
        
        for (int i = 0; i < collectables.length; i++)
            collectables[i] = new Collectable(); 
    }
    */
    
    
    
}
