package game.states.mainGameState.level.entity.mobs;

import java.awt.image.BufferedImage;

import game.Game;
import game.states.mainGameState.level.Level;
import game.states.mainGameState.level.entity.Entity;
import game.states.mainGameState.level.entity.animation.Animation;
import game.states.mainGameState.level.entity.animation.AnimationController;
import game.states.mainGameState.level.entity.mobs.contollers.MobController;

public class Mob extends Entity{
	
	private int targetX;
	private int targetY;
	public double speed=1;
	public double directionX=0;
	public double directionY=1;
	
	private MobController mobController =null;
	private AnimationController sprite = new AnimationController();
	
	public Mob(Game game, Level level, double x, double y) {
		super (game,level);
		this.setX(x);
		this.setY(y);
		this.targetX=(int)this.getX();
		this.targetY=(int)this.getY();
	}
	
	public void update(){
		if(this.mobController!=null&&!level.showDialog){
			this.mobController.update();
		}
		this.move();
		this.sprite.update();
	}
	
	public void setMobController(MobController mobController){
		this.mobController=mobController;
	}
	
	public BufferedImage getSprite(){
		return this.sprite.getCurrentFrame();
	}
	public void setAnimation(String name, boolean restart){
		this.sprite.setAnimation(name, restart);
	}
	
	public void addAnimation(Animation animation){
		this.sprite.addAnimation(animation);
	}
	private void move(){
		double dx = targetX-this.getX();
		double dy = targetY-this.getY();
		double travel = speed*game.deltaTime;
		if(dx!=0.0){
			if (travel>=Math.abs(dx)){
				this.setX(targetX);
				this.onStop();
			}else{
				if(dx>0){
					this.setX(getX()+travel);
				}else{
					this.setX(getX()-travel);
				}
			}
		}
		if(dy!=0.0){
			if (travel>=Math.abs(dy)){
				this.setY(targetY);
				this.onStop();
			}else{
				if(dy>0){
					this.setY(getY()+travel);
				}else{
					this.setY(getY()-travel);
				}
			}
		}
	
	}
	
	public void moveLeft(){
		if(this.targetY-this.getY()==0){
			this.directionX=-1;
			this.directionY=0;
			double tempTarget;
			tempTarget=roundToTile(this.getX()-0.5,true);
			if(tempTarget<0){
				tempTarget=0;
			}else{
				if(level.map.mapWidth-1<tempTarget){
					tempTarget = level.map.mapWidth-1;
				}
			}
			if(level.tileClear((int)tempTarget, targetY,this)){
				if(this.targetX!=(int) tempTarget){
					this.targetX=(int) tempTarget;
					this.onMoveLeft();
				}
				
			}
		}
	}
	public void moveRight(){	
		if(this.targetY-this.getY()==0){
			this.directionX=1;
			this.directionY=0;
			double tempTarget;
			tempTarget=roundToTile(this.getX()+0.5,false);
			if(tempTarget<0){
				tempTarget=0;
			}else{
				if(level.map.mapWidth-1<tempTarget){
					tempTarget = level.map.mapWidth-1;
				}
			}
			if(level.tileClear((int)tempTarget, targetY,this)){
				if(this.targetX!=(int) tempTarget){
					this.targetX=(int) tempTarget;
					this.onMoveRight();
				}
				
			}
			
		}
	}
	public void moveUp(){
		if(this.targetX-this.getX()==0){
			this.directionX=0;
			this.directionY=-1;
			double tempTarget;
			tempTarget=roundToTile(this.getY()-0.5,true);
			if(tempTarget<0){
				tempTarget=0;
			}else{
				if(level.map.mapHeight-1<tempTarget){
					tempTarget = level.map.mapHeight-1;
				}
			}
			if(level.tileClear(this.targetX,(int)tempTarget,this)){
				if(this.targetY!=(int) tempTarget){
					this.targetY=(int) tempTarget;
					this.onMoveUp();
				}
			}
		}
	}
	public void moveDown(){
		if(this.targetX-this.getX()==0){
			this.directionX=0;
			this.directionY=1;
			double tempTarget;
			tempTarget=roundToTile(this.getY()+0.5,false);
			if(tempTarget<0){
				tempTarget=0;
			}else{
				if(level.map.mapHeight-1<tempTarget){
					tempTarget = level.map.mapHeight-1;
				}
			}
			if(level.tileClear(this.targetX,(int)tempTarget,this)){
				if(this.targetY!=(int) tempTarget){
					this.targetY=(int) tempTarget;
					this.onMoveDown();
				}
			}
		}
	}
	
	private double roundToTile(double num, boolean roundEqualDown){
		if((num%1==0.5)&&(roundEqualDown)){
			num-=0.1;
		}
		return Math.round(num);
	}

	public void interact(){
		
	}
	
	public void onMoveLeft(){
		
	}
	public void onMoveRight(){
		
	}
	public void onMoveUp(){
		
	}
	public void onMoveDown(){
		
	}
	public void onStop(){
		
	}

}
