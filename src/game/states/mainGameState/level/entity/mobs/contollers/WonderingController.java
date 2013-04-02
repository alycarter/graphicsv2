package game.states.mainGameState.level.entity.mobs.contollers;

import game.Game;
import game.states.mainGameState.level.entity.mobs.Mob;

public class WonderingController extends MobController{
	
	private Mob mob;
	
	private Game game;
	
	private int teatherX;
	private int teatherY;
	private int range;
	
	private double walkDelay=5;
	private double walkTimer;
	
	public WonderingController(Mob mob, int range, Game game) {
		this.mob=mob;
		this.game=game;
		this.teatherX=(int)mob.getX();
		this.teatherY=(int)mob.getY();
		this.range = range;
		this.walkTimer=Math.random()*this.walkDelay;
	}

	@Override
	public void update() {
		if(walkTimer <= 0){
			walkTimer = walkDelay+((Math.random()*2)-1);
			if(Math.random()<0.5){
				if(Math.abs(mob.getX()-this.teatherX)>=this.range){
					if(mob.getX()>this.teatherX){
						mob.moveLeft();
					}else{
						mob.moveRight();
					}
				}else{
					if(Math.random()<0.5){
						mob.moveLeft();
					}else{
						mob.moveRight();
					}
				}
			}else{
				if(Math.abs(mob.getY()-this.teatherY)>=this.range){
					if(mob.getY()>this.teatherY){
						mob.moveUp();
					}else{
						mob.moveDown();
					}
				}else{
					if(Math.random()<0.5){
						mob.moveUp();
					}else{
						mob.moveDown();
					}
				}
			}
		}else{
			walkTimer -= game.deltaTime;
		}
	}

}
