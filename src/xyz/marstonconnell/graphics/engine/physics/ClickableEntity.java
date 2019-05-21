package xyz.marstonconnell.graphics.engine.physics;

import xyz.marstonconnell.graphics.engine.drawing.DrawingLayer;

public class ClickableEntity extends Entity {

	public ClickableEntity(int x, int y, int width, int height, DrawingLayer drawingLayer) {
		super(x, y, width, height, drawingLayer);
	
	}
	
	public ClickableEntity(int x, int y, int size, DrawingLayer drawingLayer) {
		super(x, y, size, drawingLayer);
	
	}

}
