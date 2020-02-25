/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jamesgames.entity;

import com.jamesgames.tilemap.TileMapManager;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author 1623326
 */
public class Player extends GameObject{
   
     
    private int health;
    private double xSpeed = 2;
    private double setGravity = 1.5;
    private double gravity;
    
    private double dx, dy;
    
    private boolean FALLING, MOVE_LEFT, MOVE_RIGHT, STANDING, JUMPING, GROUNDED;
    
    public Player (String spritefile, TileMapManager tmm)
    {
        super(spritefile, tmm);
        
        x = 100;
        y = 300;
        dx = 0;
        dy = 0;
        
        FALLING = true;
    }
    
    @Override
    public void update()
    {
        double checkX, checkY;
        
        checkX = x + dx;
        checkY = y + dy;
        
        checkTileMapCollision(checkX, checkY);
        
        if(cTopLeft && cBottomLeft)
        {
            System.out.println("CollisionLeft");
            checkX = x;
            checkX +=5;
        }
        
        if(cBottomRight && cTopRight)
        {
            System.out.println("CollisionRight");
            checkX = x;
            checkX -=5;
        }
        
        if(cTopRight || cTopLeft)
        {
            
            System.out.println("CollisionTop");
            checkY = y;
            
        }
        
        if(cBottomLeft || cBottomRight)
        {
            if(!JUMPING){
            System.out.println("collisionBottom");
            checkY = y;
            GROUNDED = true;
            FALLING = false;
            }
            
        }else
        {
            FALLING = true;
        }
        
        if(JUMPING)
        {
            if(dy >-9)
            {
                dy  -= 0.6;
            }else{
                dy = 0;
                JUMPING = false;
            }
        }
        else if(FALLING)
        {
            GROUNDED = false;
            gravity = setGravity;
        }else
        {
            gravity = 0;
        }
        
        x= checkX;
        y= checkY;
        
        y+= gravity;
    }
    
    @Override
    public void draw(Graphics2D g)
    {
       
        super.draw(g);
        
    }
    
    public void moveLeft(boolean move)
    {
        if(move == true)
        {
            MOVE_LEFT = true;
            dx = -xSpeed;
        }else
        {
            STANDING = true;
            dx = 0;
        }
    }
    
    public void moveRight(boolean move)
    {
        if(move == true)
        {
            MOVE_RIGHT = true;
            dx = xSpeed;
        }else
        {
            STANDING = true;
            dx = 0;
        }
    }
    
    public void Jump()
    {
        if(GROUNDED)
        JUMPING = true;
    }
    
    /*
    public void checkEnemyCollision(Enemy[] enemies)
    {
        for(Enemy current: enemies)
        {
            if(intersects(current))
            {
                
            }
        }
    }
    
    public void checkCollectableCollision(Collectable[] collectables)
    {
        for(Collectable current: collectables)
        {
            if(intersects(current))
            {
                
            }
        }
    }
    */
}
