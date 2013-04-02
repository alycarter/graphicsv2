package game.states.mainGameState;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;

import game.Game;
import game.states.State;
import game.states.mainGameState.level.Level;

public class MainGameState extends State{

	private static final long serialVersionUID = 1L;
	
	private final int resolution = 128;
	
	private Level level;
	
	private static boolean debug = false;
	
	public MainGameState(Game game) {
		super(game);
	}
	
	private void loadLevel(){
		level = new Level(this.game);
	}

	@Override
	public void stateSpecificRender(Graphics g) {
		if(level!=null){
			if(level.loaded){
				g.setColor(Color.WHITE);
				g.fillRect(0, 0, this.getWidth(), this.getHeight());
				g.drawImage(level.map.image, (int)((0-level.player.getX())*resolution)-(resolution/2)+(this.getWidth()/2), (int)((0-level.player.getY())*resolution)-(resolution/2)+(this.getHeight()/2), level.map.mapWidth*this.resolution,  level.map.mapHeight*this.resolution, null);
				g.setColor(Color.BLUE);
				for (int m =0; m<level.mobs.size();m++){
					g.drawImage(level.mobs.get(m).getSprite(),(int)((level.mobs.get(m).getX()-level.player.getX())*resolution)-(resolution/2)+(this.getWidth()/2), (int)((level.mobs.get(m).getY()-level.player.getY())*resolution)-(resolution/2)+(this.getHeight()/2), 1*resolution, 1*resolution, null);
				}
				if(level.showDialog){
					g.setColor(Color.ORANGE);
					g.fillRect(getWidth()/5, (int)((getHeight()/5)*3.5), (this.getWidth()/5)*3, this.getHeight()/5);
					g.setColor(Color.WHITE);
					g.fillRect(getWidth()/5+20, (int)((getHeight()/5)*3.5)+20, -40+(this.getWidth()/5)*3, -40+this.getHeight()/5);
					g.setColor(new Color(0x143E57));
					g.drawString(level.dialogText,getWidth()/5+40, (int)((getHeight()/5)*3.5)+(this.getHeight()/10));
				}
				if(debug){
					g.setColor(Color.WHITE);
					g.fillRect(0, 0, 200, 75);
					g.setColor(Color.blue);
					g.drawString("fps: "+(int)game.fps, 10, 15);
					g.drawString("ups: "+(int)game.ups, 10, 30);
					g.drawString("deltaTime: "+new DecimalFormat("#.######").format(game.deltaTime), 10, 45);
					g.drawString("x: "+level.player.getX()+" y: "+ level.player.getY(), 10, 60);
				}
			}else{
				g.setColor(Color.WHITE);
				g.fillRect(0, 0, this.getWidth(), this.getHeight());
				g.setColor(Color.BLUE);
				g.drawString("loading", getWidth()/2, getHeight()/2);
			}
		}
	}

	@Override
	public void stateSpecificUpdate() {
		if(level!=null){
			if(level.loaded){
				if(game.controls.isPressed(KeyEvent.VK_CONTROL)&&game.controls.isTyped(KeyEvent.VK_D)){
					debug = !debug;
				}
				level.update();
			}
		}else{
			loadLevel();
		}
	}

	
	

}
