package xyz.marstonconnell.testing;

import java.awt.Color;
import java.awt.Polygon;

import xyz.marstonconnell.graphics.components.shapes.DrawableEllipse;
import xyz.marstonconnell.graphics.components.shapes.DrawableRectangle;
import xyz.marstonconnell.graphics.components.shapes.DrawableShape;
import xyz.marstonconnell.graphics.components.shapes.DrawableText;
import xyz.marstonconnell.graphics.engine.EngineFrame;
import xyz.marstonconnell.graphics.engine.drawing.DrawingLayer;

public class Shapes {
	public static void main(String[] args) {
		
		//setup drawing
		EngineFrame af = new EngineFrame(800, 800, "");
		DrawingLayer dl = new DrawingLayer();
		af.getLayerContainer().insertLayer(0, dl);

		//rectangle
		DrawableRectangle rect = new DrawableRectangle(50, 30, 50, 100, dl, Color.blue);
		
		//oval
		DrawableEllipse oval = new DrawableEllipse(250, 90, 30, 90, dl, true, Color.red, Color.red);
		
		//Anything other than rectangle or oval
		Polygon p = new Polygon(new int[] {0, 100, 150}, new int[] {0, 40, 80}, 3);
		DrawableShape shape = new DrawableShape(350, 270, dl, Color.green, p);
		
		//Strings
		DrawableText text = new DrawableText(100, 100, dl, Color.black, "WOW SHAPES!", 20);
	}
}
