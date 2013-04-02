package game.states.mainMenu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import game.Game;
import game.states.Button;
import game.states.State;

public class MainMenu extends State{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private BufferedImage image;
	private BufferedImage logo;
	
	public MainMenu(Game game) {
		super(game);
		try {
			buttons.add(new PlayButton(410,400,175,175,ImageIO.read(MainMenu.class.getResourceAsStream("images/play.jpg"))));
		} catch (IOException e1) {e1.printStackTrace();}
		try{
			image = ImageIO.read(MainMenu.class.getResourceAsStream("images/building.jpg"));
		}catch(Exception e){}
		try{
			logo = ImageIO.read(MainMenu.class.getResourceAsStream("images/logo.jpg"));
		}catch(Exception e){}
	}

	@Override
	public void stateSpecificRender(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		g.drawImage(image,this.getWidth()-650,-200,650,getHeight()+200, null);
		g.drawImage(logo,25,200,(int)(logo.getWidth()*0.75),(int)(logo.getHeight()*0.75), null);
	}

	@Override
	public void stateSpecificUpdate() {
	
	}
	
	private class PlayButton extends Button{

		public PlayButton(int x, int y, int width, int height,BufferedImage img) {
			super(x, y, width, height, img);
		}
		
		public void onClick(){
			game.requestState(1);
		}
		
	}

}
