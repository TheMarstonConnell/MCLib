package xyz.marstonconnell.graphics.engine.text;

import java.awt.Color;
import java.awt.Graphics2D;

import org.apache.commons.lang3.text.WordUtils;

public class ChoiceDialog extends TextDialog {

	int choice = 0;
	String[] choices;

	public ChoiceDialog(String text, int index, int x, int y, int width, int height, String[] choices) {
		super(text, index, x, y, width, height);
		this.choices = choices;
	}

	public ChoiceDialog(String text, int index, int x, int y, int width, int height, String name, String[] choices) {
		this(text, index, x, y, width, height, choices);
		this.name = name;
	}

	@Override
	public void draw(Graphics2D g, int leftOffset, int topOffset, double resizeRate) {
		super.draw(g, resizeRate);
		g.setFont(font);

		String textToDraw = WordUtils.wrap(text.substring(0, length), width / g.getFontMetrics(font).charWidth('n'));

		g.setColor(Color.black);
		g.fillRect((int)(x * resizeRate), (int)(y * resizeRate) + topOffset, (int)(width * resizeRate), (int)(height * resizeRate));
		g.setColor(Color.white);

		String[] lines = textToDraw.split("\n");
		for (int i = 0; i < lines.length; i++) {
			g.drawString(lines[i], (int)(x * resizeRate) + leftOffset, (int)(y * resizeRate) + topOffset + (int)(g.getFontMetrics().getHeight() * (i + 1) * resizeRate));

		}
		
		//TODO Finish

		if (name != null) {
			int nameWidth = g.getFontMetrics(font).stringWidth(name);
			g.setColor(Color.black);
			g.fillRect(x, y + topOffset - g.getFontMetrics().getHeight() - 1, nameWidth + 20,
					g.getFontMetrics().getHeight());

			g.setColor(Color.white);
			g.drawString(name, x + 10, y + topOffset - 6);
		}

		if (complete) {
			for (int j = 0; j < choices.length; j++) {

				String add = "  ";
				if (choice == j) {
					add = "> ";

				}

				g.drawString(add + choices[j], x + leftOffset, y + topOffset + g.getFontMetrics().getHeight() * (j + 1)
						+ g.getFontMetrics().getHeight() * (lines.length + 1));
			}

		}
	}

	public int getChoice() {
		return choice;
	}
	
	public void choiceUp() {
		if (choice < choices.length - 1) {
			choice++;
		} else {
			choice = 0;
		}
	}
	
	public void choiceDown() {
		if (choice > 0) {
			choice--;
		} else {
			choice = choices.length - 1;
		}
	}

}
