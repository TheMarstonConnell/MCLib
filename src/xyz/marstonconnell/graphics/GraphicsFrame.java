package xyz.marstonconnell.graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * Extend this class with a redraw method to create graphics.
 * 
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
	private Long systemTime = 0L;
	public boolean DEBUG = false;

	/**
	 * Key Variables
	 */
	public boolean wDown = false;
	public boolean aDown = false;
	public boolean sDown = false;
	public boolean dDown = false;
	public boolean spaceDown = false;
	public boolean upDown = false;
	public boolean downDown = false;
	public boolean leftDown = false;
	public boolean rightDown = false;
	public boolean keysLocked = false;

	/**
	 * Key Variables
	 */

	public boolean isKeysLocked() {
		return keysLocked;
	}

	public void setKeysLocked(boolean keysLocked) {
		this.keysLocked = keysLocked;
	}

	/**
	 * Extend this class with a redraw method to create graphics.
	 * 
	 * @author Marston Connell
	 *
	 */
	public GraphicsFrame() {
		this.setSize(0, 0);

		init();

	}

	public int getInnerWidth() {
		return this.getWidth() - getInsets().left - getInsets().right;
	}

	public int getInnerHeight() {
		return this.getHeight() - getInsets().top - getInsets().bottom;
	}

	public void clearDrawings(Color c) {
		graphics.setColor(c);
		graphics.fillRect(0, 0, getWidth(), getHeight());
	}

	/**
	 * Extend this class with a redraw method to create graphics.
	 * 
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
	 * 
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
	 * 
	 * @author Marston Connell
	 * @param width
	 * @param height
	 * @param title
	 * @param        undecorated?
	 */
	public GraphicsFrame(int width, int height, String title, boolean undecorated) {
		this(width, height, title);
		this.setUndecorated(undecorated);
		init();
	}

	/**
	 * Extend this class with a redraw method to create graphics.
	 * 
	 * @author Marston Connell
	 * @param width
	 * @param height
	 * @param title
	 * @param        undecorated?
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

		this.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent arg0) {

			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_W) {
					wDown = false;
				} else if (arg0.getKeyCode() == KeyEvent.VK_A) {
					aDown = false;
				} else if (arg0.getKeyCode() == KeyEvent.VK_S) {
					sDown = false;
				} else if (arg0.getKeyCode() == KeyEvent.VK_D) {
					dDown = false;
				} else if (arg0.getKeyCode() == KeyEvent.VK_SPACE) {
					spaceDown = false;
				} else if (arg0.getKeyCode() == KeyEvent.VK_UP) {
					upDown = false;
				} else if (arg0.getKeyCode() == KeyEvent.VK_DOWN) {
					downDown = false;
				} else if (arg0.getKeyCode() == KeyEvent.VK_LEFT) {
					leftDown = false;
				} else if (arg0.getKeyCode() == KeyEvent.VK_RIGHT) {
					rightDown = false;
				}
			}

			@Override
			public void keyPressed(KeyEvent arg0) {
				if (!keysLocked) {
					if (arg0.getKeyCode() == KeyEvent.VK_W) {
						wDown = true;
					} else if (arg0.getKeyCode() == KeyEvent.VK_A) {
						aDown = true;
					} else if (arg0.getKeyCode() == KeyEvent.VK_S) {
						sDown = true;
					} else if (arg0.getKeyCode() == KeyEvent.VK_D) {
						dDown = true;
					} else if (arg0.getKeyCode() == KeyEvent.VK_SPACE) {
						spaceDown = true;
					} else if (arg0.getKeyCode() == KeyEvent.VK_UP) {
						upDown = true;
					} else if (arg0.getKeyCode() == KeyEvent.VK_DOWN) {
						downDown = true;
					} else if (arg0.getKeyCode() == KeyEvent.VK_LEFT) {
						leftDown = true;
					} else if (arg0.getKeyCode() == KeyEvent.VK_RIGHT) {
						rightDown = true;
					}
				}

			}
		});

	}

	/**
	 * Override to draw graphics. <br>
	 * <br>
	 * ex:
	 * 
	 * <br>
	 * public void redraw() { <br>
	 * super.redraw(); <br>
	 * this.graphics.setColor(Color.BLACK); <br>
	 * this.graphics.fillRect(30, 30, 30, 30); <br>
	 * }
	 * 
	 * @author Marston Connell
	 */
	public void redraw() {

		strategy = getBufferStrategy();
		graphics = (Graphics2D) strategy.getDrawGraphics();

		if (DEBUG) {
			Long time = System.currentTimeMillis();
			System.out.println("Frame redraw took: " + (time - systemTime) + "ms");
			systemTime = time;
		}
	}

	public void showDrawing() {
		graphics.dispose();
		strategy.show();
	}
}
