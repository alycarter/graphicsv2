package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;


public class Controls implements KeyListener{
	
	private ArrayList<Integer> tempKeysPressed = new ArrayList<Integer>();
	private ArrayList<Integer> tempKeysTyped = new ArrayList<Integer>();
	private ArrayList<Integer> keysPressed = new ArrayList<Integer>();
	private ArrayList<Integer> keysTyped = new ArrayList<Integer>();
	
	
	public Controls(){
		
	}
	
	public void update(){
		this.keysPressed.clear();
		for (int i = 0;i<tempKeysPressed.size();i++){
			this.keysPressed.add(tempKeysPressed.get(i).intValue());
		}
		this.keysTyped.clear();
		for (int i = 0;i<tempKeysTyped.size();i++){
			this.keysTyped.add(tempKeysTyped.get(0).intValue());
			this.tempKeysTyped.remove(0);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(!this.tempKeysPressed.contains(new Integer(e.getKeyCode()))){
			this.tempKeysPressed.add(e.getKeyCode());
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		this.tempKeysPressed.remove(new Integer(e.getKeyCode()));
		if(!this.tempKeysTyped.contains(new Integer(e.getKeyCode()))){
			this.tempKeysTyped.add(e.getKeyCode());
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	
	public boolean isPressed(int key){
		return this.keysPressed.contains(new Integer(key));
	}
	public boolean isTyped(int key){
		return this.keysTyped.contains(new Integer(key));
	}
	
}
