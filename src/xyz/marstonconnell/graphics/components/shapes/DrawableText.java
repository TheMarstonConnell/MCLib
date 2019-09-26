package xyz.marstonconnell.graphics.components.shapes;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.image.BufferedImage;

import xyz.marstonconnell.graphics.engine.drawing.Drawable;
import xyz.marstonconnell.graphics.engine.drawing.DrawingLayer;

public class DrawableText extends Drawable {

	public DrawableText(int x, int y, DrawingLayer drawingLayer
			, Color color, String text, Font font) {
		super(x, y, 0, 0, drawingLayer);
		
		BufferedImage image = new BufferedImage(this.width + 1, this.height + 1, BufferedImage.TYPE_INT_ARGB);

		
		this.width = image.getGraphics().getFontMetrics(font).stringWidth(text);
		this.height = image.getGraphics().getFontMetrics(font).getHeight();

		System.out.println(width + " : " + height);
		System.out.println(x + " : " + y);
		
		image = new BufferedImage(this.width + 1, this.height + 1, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = image.createGraphics();

		

		g2d.setFont(font);

		g2d.setColor(color);

		g2d.drawString(text,  0, image.getGraphics().getFontMetrics(font).getHeight());

		this.setImage(image);

	}
	
	public DrawableText(int x, int y, DrawingLayer drawingLayer
			, Color color, String text, int size) {

		this(x, y, drawingLayer, color, text, new Font(Font.SANS_SERIF, Font.PLAIN, size));
	}
	
	public DrawableText(int x, int y, DrawingLayer drawingLayer
			, Color color, String text) {

		this(x, y, drawingLayer, color, text, 12);
	}

}
