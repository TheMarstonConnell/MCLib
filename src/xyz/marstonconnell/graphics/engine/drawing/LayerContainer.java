package xyz.marstonconnell.graphics.engine.drawing;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import xyz.marstonconnell.graphics.GraphicsFrame;

public class LayerContainer {
	HashMap<Integer, DrawingLayer> layers;
	public double resizeRate = 1;
	public LayerContainer(double resizeRate) {
		this.resizeRate = resizeRate;
		layers = new HashMap<Integer, DrawingLayer>();
	}

	public void insertLayer(int index, DrawingLayer dl) {
		layers.put(index, dl);
	}
<<<<<<< HEAD:src/xyz/marstonconnell/graphics/engine/drawing/LayerContainer.java

	public void removeLayer(int index) {
		layers.remove(index);
	}

	public int findHighestLayer() {
		if (!layers.isEmpty()) {
			Set<Integer> s = layers.keySet();
			List<Integer> ls = new ArrayList<Integer>(s);
			Collections.sort(ls);
			return ls.get(ls.size() - 1);
		}
		return -1;
	}

=======
	
	public void setResizeRate(double rate) {
		this.resizeRate = rate;
	}
	
>>>>>>> b1ac4f943efd797a744b6a4914ea8c189e5c912c:src/xyz/marstonconnell/graphics/engine/LayerContainer.java
	public void draw(Graphics2D g, GraphicsFrame f) {
		if (!layers.isEmpty()) {

			Set<Integer> s = layers.keySet();
			List<Integer> ls = new ArrayList<Integer>(s);
			Collections.sort(ls);
<<<<<<< HEAD:src/xyz/marstonconnell/graphics/engine/drawing/LayerContainer.java

			for (int x = 0; x < ls.size(); x++) {
				layers.get(ls.get(x)).draw(g, f.getInsets().left, f.getInsets().top);
=======
			
			for(int x = 0; x < ls.size(); x ++) {
				layers.get(ls.get(x)).draw(g, f.getInsets().left, f.getInsets().top, resizeRate);
>>>>>>> b1ac4f943efd797a744b6a4914ea8c189e5c912c:src/xyz/marstonconnell/graphics/engine/LayerContainer.java
			}
		}
	}

	public void draw(Graphics2D g) {
		if (!layers.isEmpty()) {

			Set<Integer> s = layers.keySet();
			List<Integer> ls = new ArrayList<Integer>(s);
			Collections.sort(ls);
<<<<<<< HEAD:src/xyz/marstonconnell/graphics/engine/drawing/LayerContainer.java

			for (int x = 0; x < ls.size(); x++) {
				layers.get(ls.get(x)).draw(g);
=======
			
			for(int x = 0; x < ls.size(); x ++) {
				layers.get(ls.get(x)).draw(g, resizeRate);
>>>>>>> b1ac4f943efd797a744b6a4914ea8c189e5c912c:src/xyz/marstonconnell/graphics/engine/LayerContainer.java
			}
		}
	}

}
