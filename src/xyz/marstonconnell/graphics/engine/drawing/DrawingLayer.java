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

	public void draw(Graphics2D g, double resizeRate) {
		if (!drawings.isEmpty()) {
			for (int x = 0; x < drawings.size(); x++) {
				Drawable d = drawings.get(x);
				g.drawImage(d.currentImage, (int)(d.x * resizeRate), (int)(d.y * resizeRate), (int)(d.width * resizeRate), (int)(d.height * resizeRate), null);
			}
		}
	}

	public void draw(Graphics2D g, int leftOffset, int topOffset, double resizeRate) {
		if (!drawings.isEmpty()) {
			for (int x = 0; x < drawings.size(); x++) {
				Drawable d = drawings.get(x);
				g.drawImage(d.currentImage, (int)(d.x * resizeRate) + leftOffset, (int)(d.y * resizeRate) + topOffset, (int)(d.width * resizeRate), (int)(d.height * resizeRate), null);
			}
		}
	}
}
