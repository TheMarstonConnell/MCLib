package xyz.marstonconnell.graphics.engine.drawing;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.imageio.ImageIO;
import javax.swing.Timer;

import xyz.marstonconnell.graphics.engine.physics.AnimationState;

public class AnimatedDrawable extends Drawable {

	Timer animationLoop;
	HashMap<String, AnimationState> states;
	AnimationState currentState;
	int currentIndex = 0;
	boolean isLadder = false;

	public AnimatedDrawable(int x, int y, int width, int height, double refreshTime, DrawingLayer drawingLayer) {
		super(x, y, width, height, drawingLayer);
		states = new HashMap<String, AnimationState>();
		createLoop(refreshTime);
		currentState = new AnimationState(new Image[0], 1, null);
	}
	
	public String getState() {
		return currentState.getName();
	}
	
	public void createLoop(double refreshTime) {
		animationLoop = new Timer((int) (1000 * refreshTime), new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				changeTexture();
			}
		});
		animationLoop.start();

	}

	protected void changeTexture() {
		if(currentState.getImages().length > 0) {
			if(currentIndex >= currentState.getImages().length - 1) {
				currentIndex = 0;
			}else {
				currentIndex ++;
			}
			
			currentImage = currentState.getImages()[currentIndex];
		}else {
			return;
		}
		
	}

	public void createState(String name, int frames, double refreshTime, Class resourceGrabber) {
		Image[] state = new Image[frames];
		for (int x = 0; x < frames; x++) {
//			System.out.println(x);
			InputStream is = resourceGrabber.getResourceAsStream("/" + name + "/" + x + ".png");
			try {
				state[x] = ImageIO.read(is);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		states.put(name, new AnimationState(state, refreshTime, name));
	}
	
	public void setState(String name) {
		animationLoop.stop();
		currentState = states.get(name);
		currentImage = currentState.getImages()[0];
		
		createLoop(currentState.getRefreshTime());
	}
	
	

	public AnimatedDrawable(int x, int y, int size, DrawingLayer drawingLayer, double refreshTime) {
		this(x, y, size, size, refreshTime, drawingLayer);

	}

	public boolean isLadder() {
		return isLadder;
	}

}
