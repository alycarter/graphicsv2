package game.states.mainGameState.level.spriteSheet;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class SpriteSheet {
	private BufferedImage sheet;
	private int resolution;
	
	public SpriteSheet(String spriteSheetName,int resolution) {
		try {
			sheet =ImageIO.read(SpriteSheet.class.getResourceAsStream("spriteSheets/"+spriteSheetName));
		} catch (Exception e) {
			System.err.println("error reading spriteSheet: "+spriteSheetName+" details: "+e.getMessage());
		}
		this.resolution=resolution;
	}

	public BufferedImage getTile(int tile){
		BufferedImage image = new BufferedImage(resolution, resolution, BufferedImage.TYPE_INT_ARGB);
		int sheetWidth = sheet.getWidth()/this.resolution;
		int yOrigin = tile / sheetWidth;
		int xOrigin = tile % sheetWidth;
		if(xOrigin<sheet.getWidth()&&yOrigin<sheet.getHeight()){
			for (int x = 0;x<resolution;x++){
				for (int y =0; y<resolution;y++){
					image.setRGB(x,y,sheet.getRGB((xOrigin*resolution)+x, (yOrigin*resolution)+y));
				}	
			}
		}
		return image;
	}
}
