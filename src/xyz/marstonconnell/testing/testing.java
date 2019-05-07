package xyz.marstonconnell.testing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import xyz.marstonconnell.graphics.engine.Drawable;
import xyz.marstonconnell.graphics.engine.DrawingLayer;
import xyz.marstonconnell.graphics.engine.LayerContainer;
import xyz.marstonconnell.graphics.engine.physics.Entity;
import xyz.marstonconnell.graphics.engine.physics.Projectile;

public class testing {

	public static void main(String[] args) {

		addonframe af = new addonframe();
		LayerContainer lc = new LayerContainer();
		DrawingLayer dl = new DrawingLayer();

		Entity draw = new Entity(0, 0, 100, dl);
		Entity draw2 = new Entity(300, 300, 200, dl); 
		
		
		draw.setImage("cool", testing.class);
		draw2.setImage("cool", testing.class);
		

		lc.insertLayer(0, dl);
				


		Timer t = new Timer(1000 / 60, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (af.upDown) {
					draw2.moveUp(1, dl.getDrawables());
				}
				if (af.downDown) {
					draw2.moveDown(1, dl.getDrawables());
				}
				if (af.rightDown) {
					draw2.moveRight(1, dl.getDrawables());
				}
				if (af.leftDown) {
					draw2.moveLeft(1, dl.getDrawables());
				}
				
				if (af.wDown) {
					draw.moveUp(1, dl.getDrawables());
				}
				if (af.sDown) {
					draw.moveDown(1, dl.getDrawables());
				}
				if (af.dDown) {
					draw.moveRight(1, dl.getDrawables());
				}
				if (af.aDown) {
					draw.moveLeft(1, dl.getDrawables());
				}
				
				af.lc = lc;
				
			}
		});

		t.start();

	}

}
