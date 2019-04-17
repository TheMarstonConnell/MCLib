package xyz.marstonconnell.graphics;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * Extend this class with a redraw method to create graphics.
 * @author Marston Connell
 *
 */
public class GraphicsFrame extends JFrame {

	protected Graphics2D graphics;
	private Timer updateTick;
	public int fps = 60;
	public int width = 0;
	public int height = 0;
	public JPanel contentPane;
	private BufferStrategy strategy;

	/**
	 * Extend this class with a redraw method to create graphics.
	 * @author Marston Connell
	 *
	 */
	public GraphicsFrame() {
		this.setSize(0, 0);

		init();
	}

	/**
	 * Extend this class with a redraw method to create graphics.
	 * @author Marston Connell
	 * @param width
	 * @param height
	 */
	public GraphicsFrame(int width, int height) {
		this.setSize(width, height);
		this.width = width;
		this.height = height;
		init();
	}

	/**
	 * Extend this class with a redraw method to create graphics.
	 * @author Marston Connell
	 * @param width
	 * @param height
	 * @param title
	 */
	public GraphicsFrame(int width, int height, String title) {
		this(width, height);
		this.setTitle(title);

		init();
	}

	/**
	 * Extend this class with a redraw method to create graphics.
	 * @author Marston Connell
	 * @param width
	 * @param height
	 * @param title
	 * @param undecorated?
	 */
	public GraphicsFrame(int width, int height, String title, boolean undecorated) {
		this(width, height, title);
		this.setUndecorated(undecorated);
		init();
	}

	/**
	 * Extend this class with a redraw method to create graphics.
	 * @author Marston Connell
	 * @param width
	 * @param height
	 * @param title
	 * @param undecorated?
	 * @param fps
	 */
	public GraphicsFrame(int width, int height, String title, boolean undecorated, int fps) {
		this(width, height, title, undecorated);
		this.fps = fps;
		init();
	}

	private void init() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, width, height);
		contentPane = new JPanel();
		contentPane.setPreferredSize(new Dimension(width, height));
		this.add(contentPane);

		pack();
		revalidate();
		this.setVisible(true);

		createBufferStrategy(2);

		updateTick = new Timer(1000 / fps, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				redraw();
				showDrawing();
			}

		});

		updateTick.start();
		this.setLocationRelativeTo(null);

	}

	/**
	 * Override to draw graphics.
	 * <br><br>
	 * ex:
	 * 
	 * <br>public void redraw() { 
	 * <br>	super.redraw();
	 * <br>	this.graphics.setColor(Color.BLACK); 
	 * <br>	this.graphics.fillRect(30, 30, 30, 30);
	 * <br>}
	 * @author Marston Connell
	 */
	public void redraw() {
		strategy = getBufferStrategy();
		graphics = (Graphics2D) strategy.getDrawGraphics();
	}

	public void showDrawing() {
		graphics.dispose();
		strategy.show();
	}
}
