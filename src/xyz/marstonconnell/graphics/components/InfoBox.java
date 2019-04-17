package xyz.marstonconnell.graphics.components;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import xyz.marstonconnell.util.Tools;

public class InfoBox extends GraphicsWindow {
	JLabel textLabel;
	JLabel icon;
	MaterialButton ok;
	String text;
	String buttonText;
	String iconName;
	boolean hasIcon = false;
	Timer openAnim;
	private final static int width = 400;
	private final static int height = 300;
	private int x = 0;
	private int y = 0;
	Tools t;

	public InfoBox(String title, String text, String buttonText, Palette color) {
		super(width, height, title, color);
		this.text = "<html>" + text + "</html>";
		this.buttonText = buttonText;
//		initBox();
		playAnimation();


	}

	public InfoBox(String title, String text, String buttonText, String iconName, Palette color) {
		super(width, height, title, color);
		this.text = "<html>" + text + "</html>";
		this.buttonText = buttonText;
		this.iconName = iconName;
		hasIcon = true;
//		initBox();
		playAnimation();


	}

	public InfoBox(String text) {
		super(width, height, "", Tools.DEFAULT_PALETTE);
		this.text = "<html>" + text + "</html>";
		this.buttonText = "Ok";
//		initBox();
		playAnimation();

	}

	public InfoBox(String text, String title) {
		super(width, height, title, Tools.DEFAULT_PALETTE);
		this.text = "<html>" + text + "</html>";
		this.buttonText = "Ok";
//		initBox();
		playAnimation();

	}

	public InfoBox(String text, String title, String buttonText) {
		super(width, height, title, Tools.DEFAULT_PALETTE);
		this.text = "<html>" + text + "</html>";
		this.buttonText = buttonText;
//		initBox();
		playAnimation();
	}


	private void playAnimation() {
		t = new Tools();
		
		this.setButtonsShowing(false);
		this.hideTopBar();
		this.setDraggable(false);
		this.setResizeable(false);
		
		
		x = 0;
		y = 0;
		ActionListener g = new ActionListener() {

			int mod = 6;
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(x < width) {
					content.setSize(content.getWidth() + mod, content.getHeight());
					x += mod;
				}
				if(y < height) {
					content.setSize(content.getWidth(), content.getHeight() + mod);
					y += mod;
				}

				content.setLocationRelativeTo(null);
				content.setAlwaysOnTop(true);
				if(x >= width && y >= height) {
					openAnim.stop();
					initBox();
				}else {
//					if((x & 20) == 0)
//					Tools.playMidiNote(x / 3 + 10);
				}

			}

		};
		openAnim = new Timer(2, g);
		//		this.content.pack();


		content.setSize(1, 1);
		content.setLocationRelativeTo(null);

		openAnim.start();
	}

	private void initBox() {
		

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 4;

		this.setLayout(new GridBagLayout());
		textLabel = new JLabel(text, JLabel.CENTER);
		textLabel.setBorder(new EmptyBorder(10, 10, 10, 10));
		this.add(textLabel, c);

		if(hasIcon) {
			c.gridy = 0;
			icon = new JLabel(
					"<html><div align=left><img src=" + this.getClass().getClassLoader().getResource("assets/images/" + iconName + ".png").toString()
					+ " width=" + 48 + " height=" + 48 + "></img></div></html>", JLabel.CENTER);
			icon.setBorder(new EmptyBorder(10,10,10,10));
			this.add(icon, c);
		}

		c.gridx = 2;
		c.gridy = 2;
		c.gridwidth = 1;

		ok = new MaterialButton(buttonText, this.colors);
		// ok.setBorder(new EmptyBorder(15,15,15,15));
		this.add(ok, c);

		JPanel p = new JPanel();
		JPanel p2 = new JPanel();

		c.gridx = 1;
		c.gridy = 2;
		c.gridwidth = 1;

		this.add(p, c);
		c.gridx = 3;

		this.add(p2, c);

//		playAnimation();
		
		
		ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				content.dispose();

			}

		});


	}

}
