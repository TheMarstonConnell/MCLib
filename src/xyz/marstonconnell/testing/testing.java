package xyz.marstonconnell.testing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import xyz.marstonconnell.graphics.engine.Drawable;
import xyz.marstonconnell.graphics.engine.DrawingLayer;
import xyz.marstonconnell.graphics.engine.LayerContainer;

public class testing {

	public static void main(String[] args) {
		
		addonframe af = new addonframe();
		LayerContainer lc = new LayerContainer();
		DrawingLayer dl = new DrawingLayer();
		
		Drawable draw = new Drawable(0, 0, 100, dl);
		draw.setImage("cool", testing.class);
		
		lc.insertLayer(0, dl);
		
		Timer t = new Timer(1000/60, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				if(af.upDown) {
					draw.moveDown(1);
				}
				af.lc = lc;
				
				
			}
		});
		
		t.start();
		
		
		
		
		
		
		
		
		
		
	}

}
