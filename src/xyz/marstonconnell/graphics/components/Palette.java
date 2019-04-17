package xyz.marstonconnell.graphics.components;

import java.awt.Color;

/**
 * Holds three colors to be used when designing graphics. <br>
 * 
 * Default values are held in Tools.
 * @author Marston Connell
 * @see Tools
 * @since 4/20/2018
 */
public class Palette {
	public Color base;
	public Color secondary;
	public Color tertiary;
	public Color text;
	
	
	/**
	 * Creates a palette with three colors of choice.
	 * @author Marston Connell
	 * @param base
	 * @param secondary
	 * @param text
	 */
	public Palette(Color base, Color secondary, Color tertiary, Color text) {
		super();
		this.base = base;
		this.secondary = secondary;
		this.text = text;
		this.tertiary = tertiary;
	}

	
	
}
