package xyz.marstonconnell.graphics.engine.physics;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import xyz.marstonconnell.graphics.engine.Drawable;
import xyz.marstonconnell.graphics.engine.DrawingLayer;

public class Projectile extends Entity {

	private int angle = 0;
	private int speed = 0;
	Entity owner;

	private ActionListener action;

	public Projectile(int x, int y, int size, DrawingLayer drawingLayer, Entity owner) {
		super(x, y, size, drawingLayer);
		this.owner = owner;

	}

	public Projectile(int x, int y, int width, int height, DrawingLayer drawingLayer, Entity owner) {
		super(x, y, width, height, drawingLayer);
		this.owner = owner;
	}

	public void setDirection(int angle) {
		this.angle = angle;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public void move(List<Drawable> canCollideWith) {
		int deltaX = (int) (speed * Math.cos(Math.toRadians(angle)));
		int deltaY = (int) (speed * Math.sin(Math.toRadians(angle)));
		;

		Rectangle proposed = new Rectangle(this.x + deltaX, this.y + deltaY, this.width, this.height);

		for (int i = 0; i < canCollideWith.size(); i++) {
			if (canCollideWith.get(i) instanceof Entity)

				if (proposed.intersects(canCollideWith.get(i)) && !canCollideWith.get(i).equals(this)
						&& !canCollideWith.get(i).equals(owner)) {
					if (action != null) {
						action.actionPerformed(new ActionEvent(this, 0, "collisionEvent"));
					}
					return;
				}
		}
		super.moveRight(deltaX);
		super.moveDown(deltaY);
	}

	public void addActionListener(ActionListener action) {

		this.action = action;
	}

}
