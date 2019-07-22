package xyz.marstonconnell.graphics.engine.drawing;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import xyz.marstonconnell.graphics.engine.physics.Entity;

public class Particle extends Entity {
	private Color particleColor;
	private double lifeTime = 40;
	public double getLifeTime() {
		return lifeTime;
	}

	public void setLifeTime(double lifeTime) {
		this.lifeTime = lifeTime;
	}

	private double age = 0;
	boolean dead = false;
	int angle = 0;
	double speed = 10;

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public Particle(int x, int y, int size, Color color, DrawingLayer dl) {
		super(x, y, size, size, dl);
		this.particleColor = color;
	}

	public Color getParticleColor() {
		return particleColor;
	}

	public void setParticleColor(Color particleColor) {
		this.particleColor = particleColor;
	}

	public void update(List<Drawable> blackList) {
		double newSpeed = ((lifeTime - age) / lifeTime) * speed;
		System.out.println(newSpeed);
		
		List<Drawable> copy = new ArrayList<Drawable>(this.getDl().getDrawables());
		Iterator<Drawable> dit = copy.iterator();
		while(dit.hasNext()) {
			Drawable d = dit.next();
			if(blackList.contains(d) || d instanceof Particle) {
				dit.remove();
			}
		}
		
		this.moveRight((int) ((newSpeed * Math.cos(Math.toRadians(angle)))), copy);
		this.moveDown((int) ((newSpeed * Math.sin(Math.toRadians(angle)))), copy);

		if (age > lifeTime) {
			dead = true;
		} else {
			age++;
		}

	}

	public boolean isDead() {
		return dead;
	}

	public void setAngle(int i) {
		this.angle = i;
	}
}
