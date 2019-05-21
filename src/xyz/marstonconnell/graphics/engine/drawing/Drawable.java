package xyz.marstonconnell.graphics.engine.drawing;

import java.awt.Image;
import java.awt.Rectangle;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.imageio.ImageIO;

public class Drawable extends Rectangle {

	Image currentImage;

	public void moveLeft(int delta) {
		this.x = this.x - delta;
	}

	public void moveRight(int delta) {
		this.x = this.x + delta;
	}

	public void moveUp(int delta) {
		this.y = this.y - delta;
	}

	public void moveDown(int delta) {
		this.y = this.y + delta;
	}

	public void setImage(String name, Class resourceGrabber) {

		InputStream is = resourceGrabber.getResourceAsStream("/" + name + "/" + name + ".png");
		try {
			currentImage = ImageIO.read(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setImage(Image image) {

		currentImage = image;
	}

	public Drawable(int x, int y, int width, int height, DrawingLayer drawingLayer) {
		super(x, y, width, height);
		drawingLayer.add(this);
	}

	public Drawable(int x, int y, int size, DrawingLayer drawingLayer) {
		super(x, y, size, size);
		drawingLayer.add(this);
	}
}
