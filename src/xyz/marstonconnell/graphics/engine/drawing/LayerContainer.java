package xyz.marstonconnell.graphics.engine.drawing;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import xyz.marstonconnell.graphics.GraphicsFrame;
import xyz.marstonconnell.graphics.engine.EngineFrame;

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

	public void setResizeRate(double rate) {
		this.resizeRate = rate;
	}

	public void draw(Graphics2D g, GraphicsFrame f) {
		if (!layers.isEmpty()) {

			Set<Integer> s = layers.keySet();
			List<Integer> ls = new ArrayList<Integer>(s);
			Collections.sort(ls);

			for (int x = 0; x < ls.size(); x++) {
				layers.get(ls.get(x)).draw(g, f.getInsets().left, f.getInsets().top, resizeRate, f.width, f.height);
			}

//			for (int x = 0; x < ls.size(); x++) {
//				layers.get(ls.get(x)).draw(g, f.getInsets().left, f.getInsets().top, resizeRate, f.width, f.height);
//			}
		}
	}

	public void draw(Graphics2D g, EngineFrame f, boolean splitPhysAndGraphs) {
		if (!layers.isEmpty()) {

			Set<Integer> s = layers.keySet();
			List<Integer> ls = new ArrayList<Integer>(s);
			Collections.sort(ls);

			for (int x = 0; x < ls.size(); x++) {
				layers.get(ls.get(x)).draw(g, (int) ((f.getWidth() / 2 - ((f.getGameWidth() / 2) * resizeRate))),
						(int) (((f.getHeight() + f.getInsets().top + f.getInsets().bottom) / 2
								- ((f.getGameHeight() / 2) * resizeRate))) - f.getInsets().bottom,
						resizeRate, f.width, f.height);
			}

		}
	}

	public void draw(Graphics2D g) {
		if (!layers.isEmpty()) {

			Set<Integer> s = layers.keySet();
			List<Integer> ls = new ArrayList<Integer>(s);
			Collections.sort(ls);

			for (int x = 0; x < ls.size(); x++) {
				layers.get(ls.get(x)).draw(g, resizeRate);
			}

		}
	}

	public void updateParticles(Graphics2D g, EngineFrame f, List<Drawable> blackList) {
		if (!layers.isEmpty()) {

			Set<Integer> s = layers.keySet();
			List<Integer> ls = new ArrayList<Integer>(s);
			Collections.sort(ls);

			for (int x = 0; x < ls.size(); x++) {
				if (layers.get(ls.get(x)).isParticleLayer())
					layers.get(ls.get(x)).updateParticles(g,
							(int) ((f.getWidth() / 2 - ((f.getGameWidth() / 2) * resizeRate))),
							(int) (((f.getHeight() + f.getInsets().top + f.getInsets().bottom) / 2
									- ((f.getGameHeight() / 2) * resizeRate))) - f.getInsets().bottom,
							resizeRate, blackList);
			}

		}
	}

}
