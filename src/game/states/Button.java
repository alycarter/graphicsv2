package game.states;



import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class Button {
	private int x;
	private int y;
	private int width;
	private int height;
	private BufferedImage image =null;
	public boolean enabled = true;
	
	public Button(int x, int y, int width, int height) {
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
	}
	public Button(int x, int y, int width, int height, BufferedImage image) {
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
		this.image=image;
	}
	
	public BufferedImage getImage(){
		return image;
	}
	public int getX(){
		return x;
	}
	
	public void enable(){
		this.enabled=true;
	}
	
	public void disable(){
		this.enabled=false;
	}
	
	public int getY(){
		return y;
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
	public void checkClick(MouseEvent m){
		if(this.isClicked(m)){
			this.onClick();
		}
	}
	
	private boolean isClicked(MouseEvent m){
		if(m.getPoint().getX()>x&&m.getPoint().getY()>y&&x+width>m.getPoint().getX()&&y+height>m.getPoint().getY()){
			return true;
		}else{
			return false;
		}
		
	}
	
	public void onClick(){
		
	}

}
