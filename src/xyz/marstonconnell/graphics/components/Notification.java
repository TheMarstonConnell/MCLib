package xyz.marstonconnell.graphics.components;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import xyz.marstonconnell.util.Tools;

/**
 * Creates a slide-in & out notification with a custom title & text. <br>
 * Works with palettes from MiCLib.
 * @author Marston Connell
 * @see Palette
 * @since 4/20/2018
 */
public class Notification {
	int width = 400;
	int height = 100;
	String bodyText = "This is sample text, edit it using the provided method.";
	String titleText = "Sample Title";
	JFrame not;
	Timer out;
	Timer pause;
	Timer in;
	int millis = 5000;
	int x = 0;
	Palette colors = Tools.SUNSHINE_PALETTE;
	public JLabel title;
	public JLabel text;
	
	/**
	 * Creates notification with width and height.
	 * @author Marston Connell
	 * @param width
	 * @param height
	 */
	public Notification(int width, int height) {
		this.width = width;
		this.height = height;
	}
	/**
	 * Creates notification with width, height and text displayed.
	 * @author Marston Connell
	 * @param width
	 * @param height
	 * @param title
	 * @param text
	 */
	public Notification(int width, int height, String title, String text) {
		this.width = width;
		this.height = height;
		bodyText = text;
		titleText = title;
	}
	/**
	 * Creates notification with width, height, text displayed and time displayed.
	 * @author Marston Connell
	 * @param width
	 * @param height
	 * @param title
	 * @param text
	 * @param seconds
	 */
	public Notification(int width, int height, String title, String text, double seconds) {
		this.width = width;
		this.height = height;
		bodyText = text;
		titleText = title;
		millis = (int) (seconds * 1000);
	}
	/**
	 * Creates notification with width, height, text displayed, time displayed, and palette used.
	 * @author Marston Connell
	 * @param width
	 * @param height
	 * @param title
	 * @param text
	 * @param seconds
	 * @param colors
	 */
	public Notification(int width, int height, String title, String text, double seconds, Palette colors) {
		this.width = width;
		this.height = height;
		bodyText = text;
		titleText = title;
		millis = (int) (seconds * 1000);
		this.colors = colors;
	}
	/**
	 * Displays notification with data given.
	 * @author Marston Connell
	 */
	public void showNot() {
		//sets defaults for window
		not = new JFrame();
		not.setUndecorated(true);
		not.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width , 40);
		not.setSize(width, height);
		

		//content holder
		JPanel content = new JPanel(new BorderLayout());
		JPanel titleContent = new JPanel(new BorderLayout());
		JPanel accentBar = new JPanel();
		accentBar.setBackground(colors.secondary);
		content.setBackground(colors.base);
		titleContent.setBackground(colors.base);

		//text fields
		title = new JLabel(titleText);
		text = new JLabel("<html><center>" + bodyText + "</center></html>");
		title.setHorizontalAlignment(JLabel.CENTER);
		text.setHorizontalAlignment(JLabel.CENTER);
		titleContent.add(Tools.SPACER, BorderLayout.PAGE_START);
		titleContent.add(title, BorderLayout.PAGE_END);
		content.add(text, BorderLayout.CENTER);
		content.add(titleContent, BorderLayout.PAGE_START);
		not.add(accentBar, BorderLayout.LINE_START);
		title.setForeground(colors.text);
		text.setForeground(colors.text);
		titleContent.getComponent(0).setBackground(colors.base);
		
		//finalize
		not.add(content);
		not.setAlwaysOnTop(false);
		not.setVisible(true);


		//moving out timer
		out = new Timer(10, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(x < width) {
					not.setLocation(not.getLocation().x - 5, not.getLocation().y);
					x += 5;
				}
				else {
					out.stop();
					pause.start();

				}
			}
		});
		//pausing timer
		pause = new Timer(millis, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pause.stop();
				in.start();


			}
		});

		//moving in timer
		in = new Timer(10, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(x > 0) {
					not.setLocation(not.getLocation().x + 5, not.getLocation().y);
					x -= 5;
				}
				else {
					in.stop();
					not.dispose();
				}
			}
		});

		out.start();


		//closes notification


	}
}
