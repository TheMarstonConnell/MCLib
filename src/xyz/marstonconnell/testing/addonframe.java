package xyz.marstonconnell.testing;

import java.awt.Color;

import xyz.marstonconnell.graphics.GraphicsFrame;
import xyz.marstonconnell.graphics.engine.LayerContainer;

public class addonframe extends GraphicsFrame {
	int x = 0;

	LayerContainer lc;

	public addonframe() {
		super(800, 800);
	}

	@Override
	public void redraw() {
		super.redraw();
		if (lc != null) {
			lc.draw(graphics);
		}

	}
}
