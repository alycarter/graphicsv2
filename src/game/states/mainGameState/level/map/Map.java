package game.states.mainGameState.level.map;

import game.states.mainGameState.level.spriteSheet.SpriteSheet;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Map {
	public int mapWidth;
	public int mapHeight;
	public boolean[] movable;
	public BufferedImage image;
	
	private SpriteSheet tileMap = new SpriteSheet("tileMap.png",tileTextureResolution);
	
	private static final int tileTextureResolution =128;
	
	public Map(int mapNumber) {
		BufferedReader r = new BufferedReader(new InputStreamReader(Map.class.getResourceAsStream("mapFiles/"+mapNumber+".txt")));
		try{
			this.mapHeight=Integer.valueOf(r.readLine());
			this.mapWidth=Integer.valueOf(r.readLine());
			r.readLine();
		}catch(Exception e){e.printStackTrace();}
		this.movable=new boolean[mapWidth*mapHeight];
		this.image= new BufferedImage(mapWidth*tileTextureResolution, mapHeight*tileTextureResolution, BufferedImage.TYPE_INT_RGB);
		int xt;
		int yt;
		for (xt=0;xt<mapWidth;xt++){
			for (yt=0;yt<mapHeight;yt++){
				try{
					if(r.readLine().equals("1")){
						movable[(yt*mapWidth)+xt]=true;
					}else{
						movable[(yt*mapWidth)+xt]=false;
					}
				}catch(Exception e){e.printStackTrace();}
			}
		}	
		try{
			r.readLine();
		}catch(Exception e){e.printStackTrace();}
		for (xt=0;xt<mapWidth;xt++){
			for (yt=0;yt<mapHeight;yt++){
				try{
					image.getGraphics().drawImage(tileMap.getTile(Integer.valueOf(r.readLine())),xt*tileTextureResolution, yt*tileTextureResolution,tileTextureResolution, tileTextureResolution, null);
				}catch(Exception e){e.printStackTrace();}
			}
		}	
	}
	
	public boolean isClearTile(int x, int y){
		if(x>= this.mapWidth||y>= this.mapHeight||x<0||y<0){
			return false;
		}
		return movable[(y*mapWidth)+x];
	}

}
