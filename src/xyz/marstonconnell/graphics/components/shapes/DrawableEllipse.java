package xyz.marstonconnell.graphics.components.shapes;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import xyz.marstonconnell.graphics.engine.drawing.Drawable;
import xyz.marstonconnell.graphics.engine.drawing.DrawingLayer;

public class DrawableEllipse extends Drawable {

	public DrawableEllipse(int x, int y, int width, int height, DrawingLayer drawingLayer, boolean filled, Color color,
			Color fillColor) {
		super(x, y, width, height, drawingLayer);

		BufferedImage image = new BufferedImage(width + 1, height + 1, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = image.createGraphics();
		g2d.setColor(fillColor);

		if (filled) {
			g2d.fillOval(0, 0, width, height);
		}

		g2d.setColor(color);

		g2d.drawOval(0, 0, width, height);

		this.setImage(image);

	}

	public DrawableEllipse(int x, int y, int width, int height, DrawingLayer drawingLayer, Color color) {
		this(x, y, width, height, drawingLayer, false, color, color);

	}

}
