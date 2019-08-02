package xyz.marstonconnell.graphics.engine;

import java.awt.Color;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JRootPane;
import javax.swing.Timer;

import xyz.marstonconnell.graphics.GraphicsFrame;
import xyz.marstonconnell.graphics.engine.drawing.Drawable;
import xyz.marstonconnell.graphics.engine.drawing.LayerContainer;

/**
 * Starting place for all engine drawing and handling.
 * 
 * @author Marston Connell
 *
 */
public class EngineFrame extends GraphicsFrame {

	private int gameWidth = 800;
	private int gameHeight = 800;

	Random rand;
	List<Drawable> particleBlacklist;

	public Timer gameTick;

	public Random getRand() {
		return rand;
	}

	public void setRand(Random rand) {
		this.rand = rand;
	}

	public double resizeRate = 1;

	private LayerContainer layerContainer;

	public EngineFrame(int width, int height, String title) {
		super(width, height, title);
		setLayerContainer(new LayerContainer(resizeRate));
		this.getRootPane().addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				JRootPane c = (JRootPane) e.getSource();

				double widthSize = (double) c.getWidth() / (double) getGameWidth();

				double heightSize = (double) c.getHeight() / (double) getGameHeight();

				if (heightSize > widthSize) {
					setResizeRate(widthSize);
				} else {
					setResizeRate(heightSize);
				}

			}
		});
		rand = new Random();
		particleBlacklist = new ArrayList<Drawable>();
		gameTick = new Timer(1000 / 60, null);
		gameTick.start();
	}

	public void addToBlackList(Drawable d) {

		particleBlacklist.add(d);
	}

	public void setResizeRate(double rate) {
		this.resizeRate = rate;
		this.getLayerContainer().setResizeRate(rate);
	}

	public static synchronized void playSound(String soundName, Class resourceGrabber) {

		try {
			Clip clip = AudioSystem.getClip();
			AudioInputStream inputStream = AudioSystem
					.getAudioInputStream(resourceGrabber.getResourceAsStream("/assets/audio/" + soundName + "/" + soundName + ".wav"));
			clip.open(inputStream);
			clip.start();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

	}

	@Override
	public void redraw() {
		super.redraw();

		clearDrawings(Color.white);

		if (getLayerContainer() != null) {
			getLayerContainer().draw(graphics, this, true);
		}

		updateParticles();

	}

	protected void updateParticles() {

		if (getLayerContainer() != null) {
			getLayerContainer().updateParticles(graphics, this, particleBlacklist);
		}

	}

	public LayerContainer getLayerContainer() {
		return layerContainer;
	}

	public void setLayerContainer(LayerContainer layerContainer) {
		this.layerContainer = layerContainer;
	}

	public int getGameWidth() {
		return gameWidth;
	}

	public void setGameWidth(int gameWidth) {
		this.gameWidth = gameWidth;
	}

	public int getGameHeight() {
		return gameHeight;
	}

	public void setGameHeight(int gameHeight) {
		this.gameHeight = gameHeight;
	}
}
