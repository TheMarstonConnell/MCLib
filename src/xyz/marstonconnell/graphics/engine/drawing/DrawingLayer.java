package xyz.marstonconnell.graphics.engine.drawing;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

public class DrawingLayer {
	List<Drawable> drawings;

	public List<Drawable> getDrawables(){
		return drawings;
	}
	
	public DrawingLayer() {
		drawings = new ArrayList<Drawable>();
	}
	
	public void add(Drawable toDraw) {
		drawings.add(toDraw);
	}

	public void removeIndex(int index) {
		drawings.remove(index);
	}

	public void remove(Drawable toRemove) {
		drawings.remove(toRemove);
	}

	public void draw(Graphics2D g) {
		if (!drawings.isEmpty()) {
			for (int x = 0; x < drawings.size(); x++) {
				Drawable d = drawings.get(x);
				g.drawImage(d.currentImage, d.x, d.y, d.width, d.height, null);
			}
		}
	}

	public void draw(Graphics2D g, int leftOffset, int topOffset) {
		if (!drawings.isEmpty()) {
			for (int x = 0; x < drawings.size(); x++) {
				Drawable d = drawings.get(x);
				g.drawImage(d.currentImage, d.x + leftOffset, d.y + topOffset, d.width, d.height, null);
			}
		}
	}
}
