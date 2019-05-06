package xyz.marstonconnell.graphics.engine;

import java.awt.Graphics2D;
import java.util.HashMap;

public class LayerContainer {
	HashMap<Integer, DrawingLayer> layers;

	public LayerContainer() {
		layers = new HashMap<Integer, DrawingLayer>();
	}
	
	public void insertLayer(int index, DrawingLayer dl) {
		layers.put(index, dl);
	}
	
	public void draw(Graphics2D g) {
		if(!layers.isEmpty()) {
			for(int x = 0; x < layers.size(); x ++) {
				layers.get(x).draw(g);
			}
		}
	}
	
	
}
