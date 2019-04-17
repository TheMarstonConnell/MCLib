package xyz.marstonconnell.graphics.components;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import xyz.marstonconnell.util.Tools;

/**
 * A button with one solid color using palettes from MiCLib.
 * @author Marston Connell
 * @since 4/22/2018
 *
 */
public class MaterialButton extends JButton{

	private static final long serialVersionUID = -3900769734070759279L;

	//Look & feel
	String text = "Button";
	Palette colors = Tools.DARK_PALETTE;

	/**
	 * A button with one solid color using palettes from MiCLib.
	 * @author Marston Connell
	 * @param text
	 * @param colors
	 */
	public MaterialButton(String text, Palette colors) {
		this.text = text;
		this.colors = colors;
		initButton();
	}
	/**
	 * A button with one solid color using palettes from MiCLib.
	 * @author Marston Connell
	 * @param errorIcon
	 */
	public MaterialButton(ImageIcon icon) {
		this.setIcon(icon);
		this.text = "";
		initButton();
	}
	/**
	 * A button with one solid color using palettes from MiCLib.
	 * @author Marston Connell
	 */
	public MaterialButton() {
		initButton();
	}
	/**
	 * Changes palette of button.
	 * @author Marston Connell
	 * @param colors
	 */
	public void setPalette(Palette colors) {
		this.colors = colors;
		setBackground(colors.secondary);
		setForeground(colors.tertiary);
	}

	/**
	 * Creates the button from the data given through constructors.
	 * @author Marston Connell
	 */
	public void initButton() {

		//removes default button look
		setBorderPainted(false);
		setFocusPainted(false);
		setHorizontalAlignment(CENTER);
		//sets text
		setText(text);
		//changes colors
		setForeground(colors.tertiary);
		setBackground(colors.secondary);
		
		//color changing on hover
		this.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent me) {
				setBackground(colors.text);
			}
			@Override
			public void mouseExited(MouseEvent me) {
				setBackground(colors.secondary);
			}

		});
	}

}
