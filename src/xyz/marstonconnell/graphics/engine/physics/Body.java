package xyz.marstonconnell.graphics.engine.physics;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.List;

import xyz.marstonconnell.graphics.engine.drawing.AnimatedDrawable;
import xyz.marstonconnell.graphics.engine.drawing.DrawingLayer;

public class Body extends Entity {
	float velX;

	public float getGravityCo() {
		return gravityCo;
	}

	public void setGravityCo(float gravityCo) {
		this.gravityCo = gravityCo;
	}

	public float getBounceCo() {
		return bounceCo;
	}

	public void setBounceCo(float bounceCo) {
		this.bounceCo = bounceCo;
	}

	public float getTermX() {
		return termX;
	}

	public void setTermX(float termX) {
		this.termX = termX;
	}

	private float velY;
	float gravityCo;
	private float xPos;
	private float yPos;
	float bounceCo = 0.0f;
	float termX = 6f;

	public boolean checkRestingOn(AnimatedDrawable floor) {
		Rectangle newBounds = new Rectangle(this.x, floor.y + 1, this.width, this.height);
		
		return (
//				this.y + this.height > floor.y && 
				newBounds.intersects(floor));

	}

	public Body(int x, int y, int size, DrawingLayer drawingLayer, float gravityCo) {
		this(x, y, size, size, drawingLayer, gravityCo);
		
	}

	public Body(int x, int y, int width, int height, DrawingLayer drawingLayer, float gravityCo) {
		super(x, y, width, height, drawingLayer);
		this.gravityCo = gravityCo;
		velX = 0f;
		setVelY(0f);
		this.setxPos(x);
		this.setyPos(y);
	}

	public void physicsTick(boolean canFall, boolean willCollide) {

		// System.out.println(velX);
		if (canFall) {
			setVelY(getVelY() + gravityCo);

		} else {
			setVelY(getVelY() * -bounceCo);
		}

		setyPos(getyPos() + getVelY());
//		this.y = (int) getyPos();

		if (velX == 0) {

		} else if (velX >= 1) {
			velX = velX - 1;

		} else if (velX <= 1) {
			velX = velX + 1;
		}

		if (velX > termX) {
			velX = termX;
		} else if (velX < -termX) {
			velX = -termX;
		}
		
		

		if(!willCollide) {
		this.xPos = this.xPos + velX;
		}

//		this.x = (int) xPos;
	}

	public float getyPos() {
		return yPos;
	}

	public void setyPos(float yPos) {
		this.yPos = yPos;
//		this.y = (int) this.yPos;
	}

	public float getVelY() {
		return velY;
	}

	public void setVelY(float velY) {
		this.velY = velY;
	}

	public float getxPos() {
		return xPos;
	}

	public void setxPos(float xPos) {
		this.xPos = xPos;
//		this.x = (int) this.xPos;
	}

	public void setVelX(float f) {
		this.velX = f;
	}

	public float getVelX() {
		return velX;
	}

}
