package xyz.marstonconnell.testing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import xyz.marstonconnell.graphics.engine.Drawable;
import xyz.marstonconnell.graphics.engine.DrawingLayer;
import xyz.marstonconnell.graphics.engine.EngineFrame;
import xyz.marstonconnell.graphics.engine.LayerContainer;
import xyz.marstonconnell.graphics.engine.physics.Entity;
import xyz.marstonconnell.graphics.engine.physics.Projectile;

public class testing {

	public static void main(String[] args) {

		EngineFrame af = new EngineFrame(800, 800, "");
		DrawingLayer dl = new DrawingLayer();

		Entity draw = new Entity(0, 0, 100, dl);
		Entity draw2 = new Entity(300, 300, 200, dl);

		draw.createState("still", 2, 0.5, testing.class);
		draw2.createState("still", 2, 0.8, testing.class);

		draw.createState("walk", 5, 0.2, testing.class);
		draw2.createState("walk", 5, 0.2, testing.class);
		
		draw.setState("still");
		draw2.setState("still");

		af.getLayerContainer().insertLayer(0, dl);

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
				
				if (af.upDown || af.downDown || af.rightDown || af.leftDown) {
					if(draw2.getState().equals("still")) {
						draw2.setState("walk");
						
					}
				}else {
					if(draw2.getState().equals("walk")) {
						draw2.setState("still");
						
					}
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
				
				if (af.aDown|| af.wDown || af.dDown || af.sDown) {
					if(draw.getState().equals("still")) {
						draw.setState("walk");
						
					}
				}else {
					if(draw.getState().equals("walk")) {
						draw.setState("still");
						
					}
				}

			}
		});

		t.start();

	}

}
