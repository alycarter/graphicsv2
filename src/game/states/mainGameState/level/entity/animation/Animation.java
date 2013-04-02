package game.states.mainGameState.level.entity.animation;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import game.Game;
import game.states.mainGameState.level.spriteSheet.SpriteSheet;

public class Animation {
	
	private Game game;
	
	private ArrayList<BufferedImage> frames = new ArrayList<BufferedImage>();
	private int maxFrames;
	private double currentFrame;
	
	public String name;

	private double animationLength;
	
	public Animation(String spriteSheetName, String name,int resolution,int frames, double animationLength, Game game) {
		this.name=name;
		this.currentFrame=0;
		this.maxFrames=frames;
		this.animationLength=animationLength;
		this.game=game;
		SpriteSheet spriteSheet = new SpriteSheet(spriteSheetName, resolution);
		for(int f = 0; f<frames;f++){
			this.frames.add(spriteSheet.getTile(f));
		}
	}
	
	public BufferedImage getCurrentFrame(){
		return frames.get((int)currentFrame);
	}
	
	public void restartAnimation(){
		this.currentFrame=0;
	}
	
	public void update(){
		this.currentFrame+=game.deltaTime*(this.maxFrames/this.animationLength);
		if(this.currentFrame>=this.maxFrames){
			this.restartAnimation();
		}
	}

}
