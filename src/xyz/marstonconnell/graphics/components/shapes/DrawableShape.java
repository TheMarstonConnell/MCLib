package xyz.marstonconnell.graphics.components.shapes;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.image.BufferedImage;

import xyz.marstonconnell.graphics.engine.drawing.Drawable;
import xyz.marstonconnell.graphics.engine.drawing.DrawingLayer;

public class DrawableShape extends Drawable {

	
	
	public DrawableShape(int x, int y, DrawingLayer drawingLayer, boolean filled,
			Color color, Color fillColor, Shape shape) {
		super(x, y, shape.getBounds().width, shape.getBounds().height, drawingLayer);

		BufferedImage image = new BufferedImage(shape.getBounds().width + 1, shape.getBounds().height + 1, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = image.createGraphics();
		g2d.setColor(fillColor);

		if (filled) {
			g2d.fill(shape);
		}
		
		

		g2d.setColor(color);

		g2d.draw(shape);

		this.setImage(image);

	}

	public DrawableShape(int x, int y, DrawingLayer drawingLayer, Color color, Shape shape) {
		this(x, y, drawingLayer, false, color, color, shape);

	}

}
