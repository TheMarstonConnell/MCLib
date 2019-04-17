package xyz.marstonconnell.testing;
import java.awt.Color;

import xyz.marstonconnell.graphics.GraphicsFrame;

public class addonframe extends GraphicsFrame{
	int x = 0;
	
	public addonframe() {
		super(800, 800);
	}
	
	@Override
	public void redraw() {
		super.redraw();
		x++;
		this.graphics.setColor(Color.BLACK);
		this.graphics.fillRect(x + 30, 30, 30, 30);
		
	}
}
