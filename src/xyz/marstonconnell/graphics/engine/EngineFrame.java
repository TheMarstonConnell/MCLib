package xyz.marstonconnell.graphics.engine;

import java.awt.Color;

import xyz.marstonconnell.graphics.GraphicsFrame;
import xyz.marstonconnell.graphics.engine.drawing.LayerContainer;

/**
 * Starting place for all engine drawing and handling.
 * 
 * @author Marston Connell
 *
 */
public class EngineFrame extends GraphicsFrame{

	private LayerContainer layerContainer;

	public EngineFrame(int width, int height, String title) {
		super(width, height, title);
		setLayerContainer(new LayerContainer());
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
