package game.states.mainGameState.level.entity.mobs;

import game.Game;
import game.states.mainGameState.level.Level;
import game.states.mainGameState.level.entity.animation.Animation;
import game.states.mainGameState.level.entity.mobs.contollers.WonderingController;

public class Talkable extends Mob{

	private String dialogText;
	private char direction='d';
	public Talkable(Game game, Level level, double x, double y, String dialogText) {
		super(game,level,x,y);
		this.dialogText=dialogText;
		this.speed=1.5;
		this.addAnimation(new Animation("veranRight.png", "rw", 128, 4, 1.0/speed, game));
		this.addAnimation(new Animation("veranLeft.png", "lw", 128, 4, 1.0/speed, game));
		this.addAnimation(new Animation("veranDown.png", "dw", 128, 4, 1.0/speed, game));
		this.addAnimation(new Animation("veranUp.png", "uw", 128, 4, 1.0/speed, game));
		this.addAnimation(new Animation("veranRight.png", "rs", 128, 1, 0, game));
		this.addAnimation(new Animation("veranLeft.png", "ls", 128, 1, 0, game));
		this.addAnimation(new Animation("veranDown.png", "ds", 128, 1, 0, game));
		this.addAnimation(new Animation("veranUp.png", "us", 128, 1, 0, game));
		this.setAnimation("ds", true);
		this.setMobController(new WonderingController(this,1,game));
	}
	
	public void interact(){
		if(! level.delayDialog){
			level.showDialog(this.dialogText);
		}
	}
	public void onMoveLeft(){
		direction='l';
		setAnimation("lw", true);
	}
	public void onMoveRight(){
		direction='r';
		setAnimation("rw", true);
	}
	public void onMoveUp(){
		direction='u';
		setAnimation("uw", true);
	}
	public void onMoveDown(){
		direction='d';
		setAnimation("dw", true);
	}
	public void onStop(){
		switch (direction){
			case 'u':
				setAnimation("us", false);
				break;
			case 'd':
				setAnimation("ds", false);
				break;
			case 'l':
				setAnimation("ls", false);
				break;
			case 'r':
				setAnimation("rs", false);
				break;
			default:
				break;
		}
	}

}
