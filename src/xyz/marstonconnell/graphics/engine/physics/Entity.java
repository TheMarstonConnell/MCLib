package xyz.marstonconnell.graphics.engine.physics;

import java.awt.Rectangle;
import java.util.List;

import xyz.marstonconnell.graphics.engine.AnimatedDrawable;
import xyz.marstonconnell.graphics.engine.Drawable;
import xyz.marstonconnell.graphics.engine.DrawingLayer;

public class Entity extends AnimatedDrawable {

	
	
	public Entity(int x, int y, int width, int height, DrawingLayer drawingLayer) {
		super(x, y, width, height, drawingLayer);
	}

	public Entity(int x, int y, int size, DrawingLayer drawingLayer) {
		this(x, y, size, size, drawingLayer);
	}

	public void moveLeft(int delta, List<Drawable> canCollideWith) {
		Rectangle proposed = new Rectangle(this.x - delta, this.y, this.width, this.height);

		for (int i = 0; i < canCollideWith.size(); i++) {
			if (canCollideWith.get(i) instanceof Entity)

				if (proposed.intersects(canCollideWith.get(i)) && !canCollideWith.get(i).equals(this)) {

					return;
				}
		}
		super.moveLeft(delta);
	}

	public void moveRight(int delta, List<Drawable> canCollideWith) {
		Rectangle proposed = new Rectangle(this.x + delta, this.y, this.width, this.height);

		for (int i = 0; i < canCollideWith.size(); i++) {
			if (canCollideWith.get(i) instanceof Entity)

				if (proposed.intersects(canCollideWith.get(i)) && !canCollideWith.get(i).equals(this)) {

					return;
				}
		}
		super.moveRight(delta);
	}

	public void moveUp(int delta, List<Drawable> canCollideWith) {
		Rectangle proposed = new Rectangle(this.x, this.y - delta, this.width, this.height);

		for (int i = 0; i < canCollideWith.size(); i++) {
			if (canCollideWith.get(i) instanceof Entity)

				if (proposed.intersects(canCollideWith.get(i)) && !canCollideWith.get(i).equals(this)) {
					return;
				}
		}
		super.moveUp(delta);
	}

	public void moveDown(int delta, List<Drawable> canCollideWith) {
		Rectangle proposed = new Rectangle(this.x, this.y + delta, this.width, this.height);

		for (int i = 0; i < canCollideWith.size(); i++) {
			if (canCollideWith.get(i) instanceof Entity)
				if (proposed.intersects(canCollideWith.get(i)) && !canCollideWith.get(i).equals(this)) {

					return;
				}
		}
		super.moveDown(delta);
	}

}
