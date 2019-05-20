package xyz.marstonconnell.graphics.engine;

import java.awt.Color;

import xyz.marstonconnell.graphics.GraphicsFrame;

/**
 * Starting place for all engine drawing and handling.
 * 
 * @author Marston Connell
 *
 */
public class EngineFrame extends GraphicsFrame{
	
	public double resizeRate = 1;

	private LayerContainer layerContainer;

	public EngineFrame(int width, int height, String title) {
		super(width, height, title);
		setLayerContainer(new LayerContainer(resizeRate));
	}
	
	public void setResizeRate(double rate) {
		this.resizeRate = rate;
		this.getLayerContainer().setResizeRate(rate);
	}
	

	@Override
	public void redraw() {
		super.redraw();
		
		clearDrawings(Color.white);
		
		if (getLayerContainer() != null) {
			getLayerContainer().draw(graphics, this);
		}

	}

	public LayerContainer getLayerContainer() {
		return layerContainer;
	}

	public void setLayerContainer(LayerContainer layerContainer) {
		this.layerContainer = layerContainer;
	}
}
