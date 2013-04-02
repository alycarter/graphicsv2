package game.states;

import game.Game;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public abstract class State extends Canvas implements MouseListener{

	
	private static final long serialVersionUID = 1L;
	
	public Game game;
	
	public ArrayList<Button> buttons = new ArrayList<Button>();
	
	public State(Game game) {
		this.setSize(game.getSize());
		this.game=game;
		this.addMouseListener(this);
	}
	private void checkButtons(MouseEvent m){
		int b;
		for(b=0;b<buttons.size();b++){
			if(buttons.get(b).enabled){
				buttons.get(b).checkClick(m);
			}
		}
	}
	
	public void render(){
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			this.createBufferStrategy(3);
		}else{
			this.stateSpecificRender(bs.getDrawGraphics());
			for (int b=0;b<buttons.size();b++){
				if(buttons.get(b).enabled){
					BufferedImage img = buttons.get(b).getImage();
					if(img!=null){
						bs.getDrawGraphics().drawImage(img,buttons.get(b).getX(),buttons.get(b).getY(),buttons.get(b).getWidth(),buttons.get(b).getHeight(),null);
					}
				}
			}
			bs.show();
		}
	}
	
	public abstract void stateSpecificRender(Graphics g);
	
	public abstract void stateSpecificUpdate();
	
	public void update(){
		this.stateSpecificUpdate();
	}
	@Override
	public void mouseClicked(MouseEvent m) {
		this.checkButtons(m);
	}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}
	

}
