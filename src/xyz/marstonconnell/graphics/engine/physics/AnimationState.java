package xyz.marstonconnell.graphics.engine.physics;

import java.awt.Image;

public class AnimationState {
	public AnimationState(Image[] state, double refreshTime) {
		this.setRefreshTime(refreshTime);
		this.setImages(state);
	}

	public Image[] getImages() {
		return images;
	}
	public void setImages(Image[] images) {
		this.images = images;
	}

	public double getRefreshTime() {
		return refreshTime;
	}

	public void setRefreshTime(double refreshTime) {
		this.refreshTime = refreshTime;
	}

	private double refreshTime;
	private Image[] images;

}
