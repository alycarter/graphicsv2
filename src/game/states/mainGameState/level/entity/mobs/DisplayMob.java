package game.states.mainGameState.level.entity.mobs;

import game.Game;
import game.states.mainGameState.level.Level;
import game.states.mainGameState.level.entity.animation.Animation;

public class DisplayMob extends Mob{

	private String imageSet;
	
	public DisplayMob(Game game, Level level, double x, double y, String imageSet) {
		super(game,level,x,y);
		this.imageSet=imageSet;
		this.speed=0;
		this.addAnimation(new Animation("jazSpriteSheet.png", "wave", 128, 6, 1.5, game));
	}
	
	public void interact(){
		game.imageViewer.loadImageSet(imageSet);
		game.requestState(2);
	}

}
