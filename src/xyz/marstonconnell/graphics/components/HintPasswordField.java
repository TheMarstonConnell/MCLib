package xyz.marstonconnell.graphics.components;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;

import javax.swing.JPasswordField;

/**
 * Adapted from JPasswordField and HintTextField
 * @author Marston Connell
 * @see HintTextField
 */
public class HintPasswordField extends JPasswordField{

	private static final long serialVersionUID = 8374170137636645832L;
	
	/**
	 * Creates a password field
	 * @param hint
	 */
	public HintPasswordField(String hint, int maxChars) {
		setColumns(maxChars);
        _hint = hint;
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        char[] password = this.getPassword();
        String.valueOf(password);
        if (String.valueOf(password).length() == 0) {
            int h = getHeight();
            ((Graphics2D)g).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            Insets ins = getInsets();
            FontMetrics fm = g.getFontMetrics();
            int c0 = getBackground().getRGB();
            int c1 = getForeground().getRGB();
            int m = 0xfefefefe;
            int c2 = ((c0 & m) >>> 1) + ((c1 & m) >>> 1);
            g.setColor(new Color(c2, true));
            g.drawString(_hint, ins.left, h / 2 + fm.getAscent() / 2 - 2);
        }
    }
    private final String _hint;
}
