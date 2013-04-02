package game.states.mainGameState.level.entity.animation;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class AnimationController {
	
	private ArrayList<Animation> animations = new ArrayList<Animation>();
	private Animation currentAnimation;
	
	public AnimationController() {
		this.currentAnimation=null;
	}
	
	public void setAnimation(String name, boolean restart){
		for(int a = 0 ;a<animations.size();a++){
			if(animations.get(a).name==name){
				this.currentAnimation=animations.get(a);
				if(restart){
					this.currentAnimation.restartAnimation();
				}
			}
		}
	}
	
	public void update(){
		if(this.currentAnimation!=null){
			this.currentAnimation.update();
		}
	}
	
	public void addAnimation(Animation animation){
		this.animations.add(animation);
		if(this.currentAnimation==null){
			this.currentAnimation= animation;
		}
	}
	
	public BufferedImage getCurrentFrame(){
		if(this.animations.size()>0){
			return this.currentAnimation.getCurrentFrame();
		}else{
			BufferedImage img = new BufferedImage(1,1,BufferedImage.TYPE_INT_ARGB);
			img.setRGB(0, 0, Color.BLUE.getRGB());
			return img;
		}
	}

}
