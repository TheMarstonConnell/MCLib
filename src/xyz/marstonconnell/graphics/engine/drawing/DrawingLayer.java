package xyz.marstonconnell.graphics.engine.drawing;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DrawingLayer {
	List<Drawable> drawings;
	boolean particleLayer = false;

	public List<Drawable> getDrawables() {
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
				g.drawImage(d.currentImage, (int) (d.x * resizeRate), (int) (d.y * resizeRate),
						(int) (d.width * resizeRate), (int) (d.height * resizeRate), null);
			}
		}
	}

	public void draw(Graphics2D g, int leftOffset, int topOffset, double resizeRate, int width, int height) {
		if (!drawings.isEmpty()) {
			for (int x = 0; x < drawings.size(); x++) {
				Drawable d = drawings.get(x);

				if (d.x + d.width > 0 && d.x < width && d.y + d.height > 0 && d.y < height) {
					if (d instanceof DrawingLine) {
						DrawingLine dl = (DrawingLine) d;
						g.setColor(dl.getColor());
						g.drawLine((int) (dl.x1 * resizeRate) + leftOffset, (int) (dl.y1 * resizeRate) + topOffset,
								(int) (dl.x2 * resizeRate), (int) (dl.x2 * resizeRate));
						continue;
					}

					g.drawImage(d.currentImage, (int) (d.x * resizeRate) + leftOffset,
							(int) (d.y * resizeRate) + topOffset, (int) (d.width * resizeRate),
							(int) (d.height * resizeRate), null);
				}
			}
		}
	}

	public void updateParticles(Graphics2D g, int leftOffset, int topOffset, double resizeRate,
			List<Drawable> blackList) {
		if (!drawings.isEmpty()) {
			Iterator<Drawable> dit = drawings.iterator();
			while (dit.hasNext()) {
				Drawable d = dit.next();
				if (d instanceof Particle) {
					Particle p = (Particle) d;
					if (p.isDead()) {
						dit.remove();
					} else {
						p.update(blackList);
					}
					g.setColor(p.getParticleColor());
					g.fillRect((int) (p.x * resizeRate) + leftOffset, (int) (p.y * resizeRate) + topOffset,
							(int) (p.width * resizeRate), (int) (p.height * resizeRate));
				}
			}

		}

	}

	public boolean isParticleLayer() {
		// TODO Auto-generated method stub
		return particleLayer;
	}
}
