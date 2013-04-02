package game.states.mainGameState.level.entity.mobs;

import game.Game;
import game.states.mainGameState.level.Level;
import game.states.mainGameState.level.entity.animation.Animation;
import game.states.mainGameState.level.entity.mobs.contollers.PlayerController;

public class Player extends Mob{
	public char direction='d';
	public Player(Game game, Level level, double x, double y) {
		super(game, level, x, y);
		this.game=game;
		this.speed=1.5;
		this.addAnimation(new Animation("jenRight.png", "rw", 128, 4, 1.0/speed, game));
		this.addAnimation(new Animation("jenLeft.png", "lw", 128, 4, 1.0/speed, game));
		this.addAnimation(new Animation("jenDown.png", "dw", 128, 4, 1.0/speed, game));
		this.addAnimation(new Animation("jenUp.png", "uw", 128, 4, 1.0/speed, game));
		this.addAnimation(new Animation("jenRight.png", "rs", 128, 1, 0, game));
		this.addAnimation(new Animation("jenLeft.png", "ls", 128, 1, 0, game));
		this.addAnimation(new Animation("jenDown.png", "ds", 128, 1, 0, game));
		this.addAnimation(new Animation("jenUp.png", "us", 128, 1, 0, game));
		this.setAnimation("ds", true);
		this.setMobController(new PlayerController(game,this));
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
