package game.states.mainGameState.level;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import game.Game;
import game.states.mainGameState.level.entity.mobs.DisplayMob;
import game.states.mainGameState.level.entity.mobs.Mob;
import game.states.mainGameState.level.entity.mobs.Player;
import game.states.mainGameState.level.entity.mobs.Talkable;
import game.states.mainGameState.level.map.Map;

public class Level extends Thread{
	
	public Game game;
	
	public Map map;
	
	public ArrayList<Mob> mobs = new ArrayList<Mob>();
	public Player player;
	
	public String dialogText;
	public boolean showDialog;
	public boolean delayDialog = false;

	public boolean loaded;
	
	public Level(Game game) {
		this.game=game;
		this.loaded=false;
		loadLevel();
	}
	
	public void loadLevel(){
		this.start();
	}
	
	public void run(){
		map = new Map(0);
		player =new Player(game, this, 4,3);
		mobs.add(player);
		mobs.add(new DisplayMob(game, this, 4, 7,"statue"));
		mobs.add(new DisplayMob(game, this, 7, 2,"market"));
		mobs.add(new Talkable(game, this, 5, 5,"There is more buildings than this. Honest!"));
		mobs.add(new Talkable(game, this, 6, 3,"Truro is great for tourists"));
		mobs.add(new Talkable(game, this, 1, 6,"I like plants"));
		this.loaded=true;
	}
	
	public void update(){
		this.delayDialog=false;
		if(game.controls.isTyped(KeyEvent.VK_ENTER)){
			if(this.showDialog){
				this.hideDialog();
			}
		}
		for (int m = 0; m<mobs.size();m++){
			mobs.get(m).update();
		}
	}
	
	public void showDialog(String dialogText){
		this.dialogText=dialogText;
		this.showDialog=true;
	}
	
	public void hideDialog(){
		this.showDialog=false;
		this.delayDialog=true;
	}

	public boolean tileClear(int targetX, int targetY, Mob mob) {
		boolean clear = true;
		if(map.isClearTile(targetX, targetY)){
			for(int m = 0;m<mobs.size();m++){
				if(mobs.get(m)!=mob){
					if(targetX== (int)mobs.get(m).getX()){
						if(targetY== (int)mobs.get(m).getY()){
							clear = false;
						}
					}
				}
			}
		}else{
			clear = false;
		}
		return clear;
	}

}

