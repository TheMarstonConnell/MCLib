package xyz.marstonconnell.graphics.engine;

public class AnimatedDrawable extends Drawable{

	public AnimatedDrawable(int x, int y, int width, int height, DrawingLayer drawingLayer) {
		super(x, y, width, height, drawingLayer);
	}
	
	public AnimatedDrawable(int x, int y, int size, DrawingLayer drawingLayer) {
		super(x, y, size, drawingLayer);
	}

}
