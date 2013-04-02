package game;

import game.states.State;
import game.states.imageViewer.ImageViewer;
import game.states.mainGameState.MainGameState;
import game.states.mainMenu.MainMenu;

import java.util.ArrayList;

import javax.swing.JFrame;

public class Game extends JFrame implements Runnable{

	private static final long serialVersionUID = 1L;

	public static final String gameName = "graphics game v0.2";
	
	private boolean running =false;
	
	public double deltaTime=0;
	public double ups=0;
	public double fps=0;
	private static final double ns = 1000000000.0;
	private static final double upsLimit = 60;
	private static final double fpsLimit = 60;
	
	public Controls controls = new Controls();
	
	public MainGameState mainGameState;
	public MainMenu mainMenu;
	public ImageViewer imageViewer;
	
	private ArrayList<State> states = new ArrayList<State>();
	public  State CurrentState;
	private int requestedState = 0;
	private boolean requestStateChanged = false;

	public Game(int x, int y) {
		this.setSize(x, y);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle(gameName);
		this.setLayout(null);
	}

	@Override
	public void run() {
		this.init();
		
		this.setVisible(true);
		
		double last = System.nanoTime();
		double now;
		double updateTime = 0;
		double frameTime = 0;
		double loopTime = 0;
		while(running){
			if(updateTime>1/upsLimit){
				this.requestFocusInWindow();
				this.setState();
				this.controls.update();
				this.CurrentState.update();
				this.ups=1/updateTime;
				this.deltaTime=updateTime;
				updateTime =0;
			}
			if(frameTime>1/fpsLimit){
				this.CurrentState.render();
				this.fps=1/frameTime;
				frameTime =0;
			}
			now = System.nanoTime();
			loopTime= (now-last)/ns;
			updateTime+=loopTime;
			frameTime+=loopTime;
			last=now;
		}
	}
	
	private void init() {
		this.running=true;
		this.addKeyListener(controls);
		mainMenu =new MainMenu(this);
		states.add(mainMenu);
		mainGameState = new MainGameState(this);
		states.add(mainGameState);
		imageViewer = new ImageViewer(this);
		states.add(imageViewer);
		this.setState();
	}
	
	private void setState(){
		if(requestedState<this.states.size()&& !this.requestStateChanged){
			this.CurrentState=states.get(requestedState);
			this.add(CurrentState,0);
		}
		this.requestStateChanged=true;
	}
	
	public void requestState(int state){
		this.requestedState=state;
		this.requestStateChanged=false;
	}
	
	public void endGame(){
		this.running=false;
		System.exit(0);
	}

	public static void main(String args[]){
		int width = 1280;
		Game game = new Game(width, 9*(width/16));//height 720
		Thread gameThead = new Thread(game);
		gameThead.start();
	}

	
}
