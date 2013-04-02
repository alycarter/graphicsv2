package game.states.mainGameState.level.entity;

import game.Game;
import game.states.mainGameState.level.Level;

public class Entity {
	private double x=0;
	private double y=0;
	
	public Game game;
	public Level level;
	
	public Entity(Game game, Level level) {
		this.game = game;
		this.level=level;
	}
	
	public double getX(){
		return this.x;
	}
	
	public double getY(){
		return this.y;
	}
	
	public void setX(double x){
		if(x<0){
			x=0;
		}
		if(x>level.map.mapWidth){
			x=level.map.mapWidth;
		}
		this.x=x;
	}
	
	public void setY(double y){
		if(y<0){
			y=0;
		}
		if(y>level.map.mapHeight){
			y=level.map.mapHeight;
		}
		this.y=y;
	}
}
