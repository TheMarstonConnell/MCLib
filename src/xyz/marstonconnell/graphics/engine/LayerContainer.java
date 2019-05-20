package xyz.marstonconnell.graphics.engine;

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
	
	public void setResizeRate(double rate) {
		this.resizeRate = rate;
	}
	
	public void draw(Graphics2D g, GraphicsFrame f) {
		if(!layers.isEmpty()) {
			
			Set<Integer> s = layers.keySet();
			List<Integer> ls = new ArrayList<Integer>(s);
			Collections.sort(ls);
			
			for(int x = 0; x < ls.size(); x ++) {
				layers.get(ls.get(x)).draw(g, f.getInsets().left, f.getInsets().top, resizeRate);
			}
		}
	}
	
	public void draw(Graphics2D g) {
		if(!layers.isEmpty()) {
			
			Set<Integer> s = layers.keySet();
			List<Integer> ls = new ArrayList<Integer>(s);
			Collections.sort(ls);
			
			for(int x = 0; x < ls.size(); x ++) {
				layers.get(ls.get(x)).draw(g, resizeRate);
			}
		}
	}
	
	
}


