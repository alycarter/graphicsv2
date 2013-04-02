package game.states.imageViewer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.ArrayList;

import javax.imageio.ImageIO;

import game.Game;
import game.states.Button;
import game.states.State;


public class ImageViewer extends State{

	private ArrayList<BufferedImage> images= new ArrayList<BufferedImage>();
	private static final long serialVersionUID = 1L;
	private int currentImage =0;
	private double regionWidth = 750;
	private double regionHeight= 600;
	private int regionX;
	private int regionY;
	private NextButton nextButton;
	private PriorButton priorButton;
	
	public ImageViewer(Game game) {
		super(game);
		regionX=(int) ((getWidth()-regionWidth)/2);
		regionY=(int) ((getHeight()-regionHeight)/2);
		try{
			nextButton = new NextButton(this.getWidth()-175,(this.getHeight()/2)-75,150,150,ImageIO.read(ImageViewer.class.getResourceAsStream("icons/right.jpg")));
			priorButton = new PriorButton(25,(this.getHeight()/2)-75,150,150,ImageIO.read(ImageViewer.class.getResourceAsStream("icons/left.jpg")));
		}catch(Exception e){e.printStackTrace();}
		buttons.add(nextButton);
		buttons.add(priorButton);
	
	}
	
	public void loadImageSet(String folderName){
		images.clear();
		BufferedReader r = new BufferedReader(new InputStreamReader(ImageViewer.class.getResourceAsStream("imageSets/"+folderName+"/images.txt")));
		try{
		r.mark(1000);
		while (! r.readLine().equals("eof")){
			r.reset();
			images.add(ImageIO.read(ImageViewer.class.getResourceAsStream("imageSets/"+folderName+"/"+r.readLine())));
			r.mark(1000);
		}
		}catch(IOException e){e.printStackTrace();}
		currentImage=0;
		nextImage();
		priorImage();
	}

	@Override
	public void stateSpecificRender(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(Color.BLACK);
		if (images.size()==0){
			g.drawString("no images in imageSet", getWidth()/2, getHeight()/2);
		}else{
			if(currentImage<images.size()&&currentImage>=0){
				g.fillRect(regionX, regionY, (int)regionWidth, (int)regionHeight);
				BufferedImage img  = images.get(currentImage);
				double imageRatio = img.getWidth()/img.getHeight();
				double width=0;
				double height=0;
				if(imageRatio< 1){
					width= img.getWidth()*(regionHeight/img.getHeight());
					height= img.getHeight()*(regionHeight/img.getHeight());
				}else{
					width= img.getWidth()*(regionWidth/img.getWidth());
					height= img.getHeight()*(regionWidth/img.getWidth());
				}
				g.drawImage(img,regionX+(int)((regionWidth-width)/2),regionY+(int)((regionHeight-height)/2),(int)width,(int)height,null);
				
			}else{
				g.drawString("selected image not there", getWidth()/2, getHeight()/2);
			}
		}
	}
	
	private void nextImage(){
		if(currentImage+1<images.size()){
			currentImage++;
			if(currentImage+1>=images.size()){
				nextButton.disable();
			}
			if(currentImage-1>=0){
				priorButton.enable();
			}
		}
	}
	private void priorImage(){
		if(currentImage-1>=0){
			currentImage--;
			if(currentImage-1<0){
				priorButton.disable();
			}
			if(currentImage+1<images.size()){
				nextButton.enable();
			}
		}
	}
	@Override
	public void stateSpecificUpdate() {
		if(game.controls.isTyped(KeyEvent.VK_BACK_SPACE)){
			game.requestState(1);
		}
	}

	
	private class PriorButton extends Button{

		public PriorButton(int x, int y, int width, int height, BufferedImage img) {
			super(x, y, width, height, img);
		}
		
		public void onClick(){
			priorImage();
		}
		
	}
	
	private class NextButton extends Button{

		public NextButton(int x, int y, int width, int height, BufferedImage img) {
			super(x, y, width, height, img);
		}
		
		public void onClick(){
			nextImage();
		}
		
	}
	
	
}
