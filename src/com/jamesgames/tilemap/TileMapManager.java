/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jamesgames.tilemap;

import com.jamesgames.main.LevelPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import javax.imageio.ImageIO;

/**
 *
 * @author 1623326
 */
public class TileMapManager {
    
    public static final int TILE_SIZE = 64;
    
    private Tile[] tiles;
    
    private final int[][]map =
    {
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,1,0,1,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,1,1,1,0,0,0,0,0,0,0,0,0,0},
        {0,0,2,0,2,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,2,0,0,2,0,0,0,0,0,0,0,0,0},
        {2,2,2,2,2,2,0,0,0,0,0,0,0,0,0},
        {2,2,2,0,0,0,0,0,0,0,0,0,0,0,0},
    };
    
    private double cameraX, cameraY;
    
    private int xMax, yMax, xMin, yMin;
    
    private int noOfColumns, noOfRows;
    
    private int rowOffSet, colOffSet;
    
    private int numColumnsToDraw, numRowsToDraw;


public TileMapManager()
{
    int width, height;
    
    noOfRows = map.length;
    noOfColumns = map[0].length;
    
    numColumnsToDraw = LevelPanel.PANEL_WIDTH / TILE_SIZE +2;
    
    numRowsToDraw = LevelPanel.PANEL_HEIGHT / TILE_SIZE + 2;
    
    width = noOfColumns * TILE_SIZE;
    height = noOfRows * TILE_SIZE;
    
    xMin = LevelPanel.PANEL_WIDTH - width;
    xMax = 0;
    yMin = LevelPanel.PANEL_HEIGHT - height;
    yMax = 0;
    
    cameraX = 0;
    cameraY = 0;
    
    //loadMap();
    loadTiles();
}

private void loadTiles()
{
    tiles = new Tile[3];
    try
    {
        tiles[0] = new Tile(ImageIO.read(getClass().getResource("/images/col1.png")), Tile.TYPE_NORMAL);
        tiles[1] = new Tile(ImageIO.read(getClass().getResource("/images/col2.png")), Tile.TYPE_BLOCKED);
        tiles[2] = new Tile(ImageIO.read(getClass().getResource("/images/col3.png")), Tile.TYPE_BLOCKED);
    }catch(java.io.IOException ex)
    {
        System.err.println("Error loading tiles");
    }
}

public Tile getTileAt(int row, int col)
{
    //x = x - cameraX;
    //y = y - cameraY;
    
    
    
    int tileID = map[row][col];
    
    tileID = matchTile(tileID);
    System.out.println("x: " + row + "Y: " + col);
    System.out.println("In Tile: " + row +", " + col);
    return tiles[tileID];
}

public void setCamPos(double x, double y)
{
    cameraX +=(x - cameraX);
    cameraY +=(y - cameraY);
    
    fixCamBounds();
    
    colOffSet = (int) -cameraX / TILE_SIZE;
    rowOffSet = (int) -cameraY / TILE_SIZE;
}

private void fixCamBounds()
{
    if(cameraX < xMin)
        cameraX = xMin;
    
    if(cameraX > xMax)
        cameraX = xMax;
    
    if(cameraY < yMin)
        cameraY = yMin;
    
    if(cameraY > yMax)
        cameraY = yMax;
}

private int matchTile(int tileMapID)
{
    int mappedTile = 0;
    
    switch(tileMapID)
    {
        case 1:
            mappedTile = 0;
            break;
        case 2:
            mappedTile = 1;
            break;
        case 3:
            mappedTile = 2;
    }
    
    return mappedTile;
}

public void draw(Graphics2D g)
{
    int tileImage;
    double tempX, tempY;
    
    g.setFont(new Font("Arial", Font.PLAIN, 14));
    g.setColor(Color.WHITE);
    g.drawString("Camera X: " + cameraX + " Camera Y: " +cameraY, 20, 20);
    
    g.setColor(Color.BLACK);
    
    for(int row = rowOffSet; row < rowOffSet + numRowsToDraw; row++)
    {
        if(row >= noOfRows) break;
        
        tempY = cameraY + row * TILE_SIZE;
        for(int col = colOffSet; col < colOffSet + numColumnsToDraw; col++)
        {
            if(col >= noOfColumns) break;
            
            tempX = cameraX + col * TILE_SIZE;
            
            if(map[row][col] != 0)
            {
                tileImage = matchTile(map[row][col]);
                
                g.drawImage(tiles[tileImage].getImage(), (int)tempX, (int)tempY, null);
                //g.drawRect((int)tempX,(int)tempY, TILE_SIZE, TILE_SIZE);
            }
        }
    }
}

public double getCameraX()
{
    return cameraX;
}

public double getCameraY()
{
    return cameraY;
}

}