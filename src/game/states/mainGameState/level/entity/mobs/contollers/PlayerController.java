package game.states.mainGameState.level.entity.mobs.contollers;

import java.awt.event.KeyEvent;

import game.Game;
import game.states.mainGameState.level.entity.mobs.Mob;
import game.states.mainGameState.level.entity.mobs.Player;

public class PlayerController extends MobController{
	private Game game;
	private Player player;
	
	public PlayerController(Game game, Player player) {
		this.game=game;
		this.player=player;
	}

	@Override
	public void update() {
		if(game.controls.isPressed(KeyEvent.VK_A)||game.controls.isPressed(KeyEvent.VK_LEFT)){
			player.moveLeft();
			player.direction= 'l';
		}
		if(game.controls.isPressed(KeyEvent.VK_D)||game.controls.isPressed(KeyEvent.VK_RIGHT)){
			player.moveRight();
			player.direction= 'r';
		}
		if(game.controls.isPressed(KeyEvent.VK_W)||game.controls.isPressed(KeyEvent.VK_UP)){
			player.moveUp();
			player.direction= 'u';
		}
		if(game.controls.isPressed(KeyEvent.VK_S)||game.controls.isPressed(KeyEvent.VK_DOWN)){
			player.moveDown();
			player.direction= 'd';
		}
		if(game.controls.isTyped(KeyEvent.VK_ENTER)){
			checkInteracts();
		}
	}

	private void checkInteracts() {
		for (int m = 0; m<player.level.mobs.size();m++){
			Mob temp = player.level.mobs.get(m);
			double distanceX= temp.getX()-player.getX();
			double distanceY= temp.getY()-player.getY();
			if(distanceX==player.directionX){
				if(distanceY==player.directionY){
					temp.interact();
				}
			}
		}
	}

}
