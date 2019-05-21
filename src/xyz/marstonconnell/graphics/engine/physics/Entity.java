package xyz.marstonconnell.graphics.engine.physics;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import xyz.marstonconnell.graphics.engine.drawing.AnimatedDrawable;
import xyz.marstonconnell.graphics.engine.drawing.Drawable;
import xyz.marstonconnell.graphics.engine.drawing.DrawingLayer;

public class Entity extends AnimatedDrawable {

	ActionListener action;

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
<<<<<<< HEAD
					if (action != null) {
						action.actionPerformed(new ActionEvent(this, 0, "collisionEvent"));
					}
=======
					action.actionPerformed(new ActionEvent(canCollideWith.get(i), 0, "collisionEvent"));

>>>>>>> b1ac4f943efd797a744b6a4914ea8c189e5c912c
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
<<<<<<< HEAD
					if (action != null) {
						action.actionPerformed(new ActionEvent(this, 0, "collisionEvent"));
					}
=======
					action.actionPerformed(new ActionEvent(canCollideWith.get(i), 0, "collisionEvent"));

>>>>>>> b1ac4f943efd797a744b6a4914ea8c189e5c912c
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
<<<<<<< HEAD
					if (action != null) {
						action.actionPerformed(new ActionEvent(this, 0, "collisionEvent"));
					}
=======
					action.actionPerformed(new ActionEvent(canCollideWith.get(i), 0, "collisionEvent"));
>>>>>>> b1ac4f943efd797a744b6a4914ea8c189e5c912c

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
<<<<<<< HEAD
					if (action != null) {
						action.actionPerformed(new ActionEvent(this, 0, "collisionEvent"));
					}
=======
					action.actionPerformed(new ActionEvent(canCollideWith.get(i), 0, "collisionEvent"));
>>>>>>> b1ac4f943efd797a744b6a4914ea8c189e5c912c
					return;
				}
		}
		super.moveDown(delta);
	}

	public void addActionListener(ActionListener action) {

		this.action = action;
	}

}
