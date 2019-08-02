package xyz.marstonconnell.graphics.engine.text;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import org.apache.commons.lang3.text.WordUtils;

import xyz.marstonconnell.graphics.engine.drawing.DrawingLayer;

public class TextDialog extends DrawingLayer {
	Font font;
	int index;
	String text;
	Timer timer;
	int x = 0;
	int y = 0;
	int width = 0;
	int height = 0;
	boolean complete = false;
	ActionListener action;
	Timer flicker;
	boolean showFlicker = false;
	String name;

	protected int length = 0;

	public TextDialog(String text, int index, int x, int y, int width, int height) {
		super();
		this.text = text;
		this.index = index;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		font = new Font("Arial", Font.PLAIN, 20);
		timer = new Timer(10, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				updateText();
			}
		});
		timer.start();

		flicker = new Timer(600, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				showFlicker = !showFlicker;
			}
		});
		flicker.start();
	}

	public TextDialog(String text, int index, int x, int y, int width, int height, String name) {
		this(text, index, x, y, width, height);
		this.name = name;
	}

	public void addActionListener(ActionListener action) {

		this.action = action;
	}

	private void updateText() {
		if (length < text.length()) {
			complete = false;
			length++;

		} else {
			complete = true;
			timer.stop();

			if (action != null)
				action.actionPerformed(new ActionEvent(this, 0, "textFinishedEvent"));
		}
	}

	@Override
	public void draw(Graphics2D g, int leftOffset, int topOffset, double resizeRate, int width, int height) {
		super.draw(g, resizeRate);
		g.setFont(font);

		String textToDraw = WordUtils.wrap(text.substring(0, length), width / g.getFontMetrics(font).charWidth('n'));

		g.setColor(Color.black);
		g.fillRect(x, y + topOffset, width, height);
		g.setColor(Color.white);

		String[] lines = textToDraw.split("\n");
		for (int i = 0; i < lines.length; i++) {
			g.drawString(lines[i], x + leftOffset, y + topOffset + g.getFontMetrics().getHeight() * (i + 1));

		}

		if (name != null) {
			int nameWidth = g.getFontMetrics(font).stringWidth(name);
			g.setColor(Color.black);
			g.fillRect(x, y + topOffset - g.getFontMetrics().getHeight() - 1, nameWidth + 20, g.getFontMetrics().getHeight());
			
			g.setColor(Color.white);
			g.drawString(name, x + 10, y + topOffset - 6);
		}

		if (complete) {
			if (showFlicker)
				g.fillRect(x + leftOffset + width - 40, y + topOffset + height - 30, 20, 10);
		}
	}
}
