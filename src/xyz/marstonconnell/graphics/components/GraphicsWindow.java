package xyz.marstonconnell.graphics.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.MatteBorder;

import xyz.marstonconnell.util.Tools;

/**
 * Creates a material style window building off of JFrame with the ability to be
 * drawn on. <br>
 * Uses Palettes from MiCLib.
 * 
 * @author Marston Connell
 * @see Palette
 * @see JFrame
 * @since 4/21/2018
 *
 */
public class GraphicsWindow extends JPanel {

	private static final long serialVersionUID = 7327794597682977618L;

	// holds graphics defaults
	private int fps;
	private BufferedImage buffer;
	private Timer clock;

	private int viewX = 0;
	private int viewY = 0;
	private int zoom = 100;

	// holds colors being used
	public Color backgroundColor = Color.BLACK;
	public Color penColor = Color.blue;

	// holds defaults
	public Palette colors = Tools.DEFAULT_PALETTE;
	public JFrame content = new JFrame();
	public String titleText = "Material Window";
	public int width = 400;
	public int height = 400;
	public boolean undecorated = false;
	public boolean resizeable = true;
	private JPanel toolBar = new JPanel(new BorderLayout());
	private int resWidth;
	private int resHeight;

	// Event Handlers
	private int x;
	private Timer minimizeTimer = null;
	private Point initialClick;
	private boolean maxed = false;
	private Point oldPos;

	public int keyPressed;
	private boolean devMode; // god mode, turns off close button
	private boolean buttonsShowing = true;
	private boolean draggable = true;

	// holds all parts of frame
	private JPanel bottomDrag;
	private JPanel topDrag;
	private JPanel leftDrag;
	private JPanel rightDrag;
	private JPanel topPane;
	public JLabel closeButton;
	private JLabel min;
	private JLabel max;
	private JLabel title;
	private JPanel buttons;

	public void hideTopBar() {
		buttons.setOpaque(false);
		toolBar.setOpaque(false);
		toolBar.setBackground(new Color(1f, 0f, 0f, .5f));
		toolBar.setBorder(new MatteBorder(0, 2, 0, 2, this.colors.secondary));
		content.setAlwaysOnTop(true);
		buttons.setBackground(new Color(1f, 0f, 0f, .5f));
	}

	// drawing to the screen//
	/**
	 * Draws image at specified location by size.
	 * 
	 * @author Marston Connell
	 * @param img
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public void drawImage(Image img, int x, int y, int width, int height) {
		Graphics g = getOffscreenGraphics();
		g.drawImage(img, x, y, width, height, null);
	}

	/**
	 * Draws image at specified location.
	 * 
	 * @author Marston Connell
	 * @param img
	 * @param x
	 * @param y
	 */
	public void drawImage(Image img, int x, int y) {
		Graphics g = getOffscreenGraphics();
		g.drawImage(img, x, y, this);
	}

	/**
	 * Draws a String of text on screen at specified location.
	 * 
	 * @author Marston Connell
	 * @param str
	 * @param x
	 * @param y
	 */
	public void drawString(String str, int x, int y) {
		Graphics g = getOffscreenGraphics();
		g.setColor(penColor);
		g.drawString(str, x, y);
	}

	/**
	 * Draws a polygon based around two arrays of x and y coordinates.
	 * 
	 * @author Marston Connell
	 * @param x
	 * @param y
	 * @param pointCount
	 */
	public void drawPolygon(int[] x, int[] y, int pointCount) {
		Graphics g = getOffscreenGraphics();
		g.setColor(penColor);
		g.drawPolygon(x, y, pointCount);
	}

	/**
	 * Fills a polygon shape based around two arrays of x and y coordinates.
	 * 
	 * @author Marston Connell
	 * @param x
	 * @param y
	 * @param pointCount
	 */
	public void fillPolygon(int[] x, int[] y, int pointCount) {
		Graphics g = getOffscreenGraphics();
		g.setColor(penColor);
		g.fillPolygon(x, y, pointCount);
	}

	/**
	 * Draws a rectangle at specified location with sizing.
	 * 
	 * @author Marston Connell
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public void drawRect(int x, int y, int width, int height) {
		Graphics g = getOffscreenGraphics();
		g.setColor(penColor);
		g.drawRect(x, y, width, height);
	}

	/**
	 * Fills rectangle at specified location with sizing.
	 * 
	 * @author Marston Connell
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public void fillRect(int x, int y, int width, int height) {
		Graphics g = getOffscreenGraphics();
		g.setColor(penColor);
		g.fillRect(x, y, width, height);
	}

	/**
	 * Draws the outline of an oval at location.
	 * 
	 * @author Marston Connell
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public void drawOval(int x, int y, int width, int height) {
		Graphics g = getOffscreenGraphics();
		g.setColor(penColor);
		g.drawOval(x, y, width, height);
	}

	/**
	 * Fills oval at location.
	 * 
	 * @author Marston Connell
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public void fillOval(int x, int y, int width, int height) {
		Graphics g = getOffscreenGraphics();
		g.setColor(penColor);
		g.fillOval(x, y, width, height);
	}

	/**
	 * Draws line from two points.
	 * 
	 * @author Marston Connell
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 */
	public void drawLine(int x1, int y1, int x2, int y2) {
		Graphics g = getOffscreenGraphics();
		g.setColor(penColor);
		g.drawLine(x1, y1, x2, y2);
	}

	/**
	 * Draws square at location given.
	 * 
	 * @author Marston Connell
	 * @param x
	 * @param y
	 * @param size
	 */
	public void drawSquare(int x, int y, int size) {
		Graphics g = getOffscreenGraphics();
		g.setColor(penColor);
		g.drawRect(x, y, size, size);
	}

	/**
	 * Fills square at location given.
	 * 
	 * @author Marston Connell
	 * @param x
	 * @param y
	 * @param size
	 */
	public void fillSquare(int x, int y, int size) {
		Graphics g = getOffscreenGraphics();
		g.setColor(penColor);
		g.fillRect(x, y, size, size);
	}

	/**
	 * Draws arc at location given with angles given.
	 * 
	 * @author Marston Connell
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param startAngle
	 * @param arcAngle
	 */
	public void drawArc(int x, int y, int width, int height, int startAngle, int arcAngle) {
		Graphics g = getOffscreenGraphics();
		g.setColor(penColor);
		g.drawArc(x, y, width, height, startAngle, arcAngle);
	}

	// drawing to the screen//

	// Basic graphic tools
	/**
	 * Sets the font of drawing strings.
	 * 
	 * @author Marston Connell
	 */
	public void setFont(Font f) {
		// Graphics g = getOffscreenGraphics();
		super.setFont(f);
	}

	/**
	 * Sets background color of main panel.
	 * 
	 * @author Marston Connell
	 * @param color
	 */
	public void setBackgroundColor(Color color) {
		
		this.setBackground(color);
		this.backgroundColor = color;
	}

	/**
	 * Sets color used to draw with for main panel.
	 * 
	 * @author Marston Connell
	 * @param color
	 */
	public void setColor(Color color) {
		this.penColor = color;
	}

	/**
	 * Fills screen with background color essentially clearing it.
	 * 
	 * @author Marston Connell
	 */
	public void clear() {
		Graphics g = getOffscreenGraphics();
		g.setColor(backgroundColor);
		g.fillRect(0, 0, resWidth, resHeight);
	}

	/**
	 * Sets the drawing panel to the buffered image.
	 * 
	 * @author Marston Connell
	 * @return Graphics
	 */
	private Graphics getOffscreenGraphics() {
		Graphics g = buffer.getGraphics();

		g.setPaintMode();
		return g;
	}

	/**
	 * Draws in the buffered image to the screen to fill the screen with what has
	 * been drawn.
	 * 
	 * @author Marston Connell
	 */
	public void paintComponent(Graphics g) {
		synchronized (this) {
			g.drawImage(buffer, viewX, viewY, (int) (this.getWidth() * (zoom * 0.01)),
					(int) (this.getHeight() * (zoom * 0.01)), this);
		}
	}

	// Basic graphics tools//
	/**
	 * Sets the resolution of the drawing canvas in the window. <br>
	 * WARNING: WILL REMOVE ALL DRAWING DONE PREVIOUSLY!
	 * 
	 * @author Marston Connell
	 * @param width
	 * @param height
	 */
	public void setResolution(int width, int height) {
		resWidth = width;
		resHeight = height;
		buffer = new BufferedImage(resWidth, resHeight, BufferedImage.TYPE_4BYTE_ABGR);
	}

	/**
	 * Enter a positive or negative integer to change the zoom percentage. <br>
	 * By default it is set to 100.
	 * 
	 * @author Marston Connell
	 * @param amount
	 */
	public void zoom(int amount) {
		if (amount > 0) {
			zoom += amount;
		} else {
			if (zoom > 100) {
				zoom += amount;
			}
		}

	}

	/**
	 * Enter a positive number to move right and negative to move left.
	 * 
	 * @author Marston Connell
	 * @param amount
	 */
	public void horScroll(int amount) {
		viewX += amount;
		if (amount > 0) {
			if (viewX >= 0) {
				viewX = 0;
			}
		} else {
			if (viewX + resWidth * (zoom * 0.01) <= width) {
				viewX = (int) (width - resWidth * (zoom * 0.01) + (width / 100));
			}
		}

	}

	/**
	 * Enter a positive number to move down and negative to move up.
	 * 
	 * @author Marston Connell
	 * @param amount
	 */
	public void vertScroll(int amount) {
		viewY += amount;
		if (amount > 0) {
			if (viewY >= 0) {
				viewY = 0;
			}
		} else {
			if (viewY + resHeight * (zoom * 0.01) <= height) {
				viewY = (int) (height - resHeight * (zoom * 0.01));
			}
		}

	}

	public void exportDrawing() {
		File outputfile = new File(Tools.inputBox("Enter image name", "Save Image", null) + ".png");
		try {
			ImageIO.write(buffer, "png", outputfile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Gets current frame rate
	 * 
	 * @author Marston Connell
	 * @return fps
	 */
	public int getFps() {
		return fps;
	}

	/**
	 * Sets current frame rate.
	 * 
	 * @author Marston Connell
	 * @param fps
	 */
	public void setFps(int fps) {
		this.fps = fps;
	}

	/**
	 * Gets color scheme being used.
	 * 
	 * @author Marston Connell
	 * @return Palette
	 */
	public Palette getPalette() {
		return colors;
	}

	/**
	 * Sets color scheme.
	 * 
	 * @author Marston Connell
	 * @param colors
	 */
	public void setPalette(Palette colors) {
		this.colors = colors;

		this.setBackgroundColor(colors.base);
		bottomDrag.setBackground(colors.secondary);
		topDrag.setBackground(colors.secondary);
		leftDrag.setBackground(colors.secondary);
		rightDrag.setBackground(colors.secondary);
		buttons.setBackground(colors.secondary);
		max.setForeground(colors.tertiary);
		min.setForeground(colors.tertiary);
		closeButton.setForeground(colors.tertiary);
		title.setForeground(colors.tertiary);
		toolBar.setBackground(colors.secondary);
	}

	public boolean isDraggable() {
		return draggable;
	}

	public void setDraggable(boolean draggable) {
		this.draggable = draggable;
	}

	/**
	 * Sets if window is resizeable.
	 * 
	 * @author Marston Connell
	 * @param resizeable
	 */
	public void setResizeable(boolean resizeable) {
		this.resizeable = resizeable;
	}

	/**
	 * Gets the text displayed as title.
	 * 
	 * @author Marston Connell
	 * @return Title text
	 */
	public String getTitle() {
		return titleText;
	}

	/**
	 * Sets the title being displayed.
	 * 
	 * @author Marston Connell
	 * @param titleText
	 */
	public void setTitle(String titleText) {
		this.titleText = titleText;
		this.title.setText(titleText);
	}

	/**
	 * Gets the last key that was pressed.
	 * 
	 * @author Marston Connell
	 * @return
	 */
	public int getKeyPressed() {
		return keyPressed;
	}

	/**
	 * Handles try/catch for Thread.sleep.
	 * 
	 * @author Marston Connell
	 * @param millis
	 */
	public void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void enableGodMode() {
		devMode = true;
	}

	public boolean areButtonsShowing() {
		return buttonsShowing;
	}

	public void setButtonsShowing(boolean buttonsShowing) {
		this.buttonsShowing = buttonsShowing;

		if (buttonsShowing) {
			min.setText("  _  ");
			max.setText("  O  ");
			closeButton.setText("  X  ");
		} else {
			min.setText("     ");
			max.setText("     ");
			closeButton.setText("     ");
		}
	}

	/**
	 * Creates a material window building off of JFrame. <br>
	 * 
	 * @author Marston Connell
	 * @param width
	 * @param height
	 */
	public GraphicsWindow(int width, int height) {
		this.width = width;
		this.height = height;
		initWindow();
	}

	/**
	 * Creates a material window building off of JFrame. <br>
	 * 
	 * @author Marston Connell
	 * @param width
	 * @param height
	 * @param title
	 */
	public GraphicsWindow(int width, int height, String title) {
		this.width = width;
		this.height = height;
		titleText = title;
		initWindow();
	}

	/**
	 * Creates a material window building off of JFrame. <br>
	 * Uses Palettes from MiCLib.
	 * 
	 * @author Marston Connell
	 * @param width
	 * @param height
	 * @param title
	 * @param colors
	 */
	public GraphicsWindow(int width, int height, String title, Palette colors) {
		this.width = width;
		this.height = height;
		titleText = title;
		this.colors = colors;
		initWindow();
	}

	/**
	 * Creates a material window building off of JFrame. <br>
	 * Uses Palettes from MiCLib. <br>
	 * <br>
	 * Undecorated will remove top toolbar with title and dragging feature.
	 * 
	 * @author Marston Connell
	 * @param width
	 * @param height
	 * @param title
	 * @param colors
	 * @param undecorated
	 */
	public GraphicsWindow(int width, int height, String title, Palette colors, boolean undecorated) {
		this.width = width;
		this.height = height;
		titleText = title;
		this.colors = colors;
		this.undecorated = undecorated;
		initWindow();
	}

	/**
	 * Creates a material window building off of JFrame using defaults. <br>
	 * 
	 * @author Marston Connell
	 */
	public GraphicsWindow() {
		initWindow();
	}

	/**
	 * Initializes Window with params given.
	 * 
	 * @author Marston Connell
	 */
	public void initWindow() {

		content.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void focusLost(FocusEvent arg0) {
				content.requestFocus();

			}

		});

		buffer = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
		fps = 60;
		resWidth = width;
		resHeight = height;

		// finalizes panel
		clear();
		clock = new Timer(1000 / fps, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (zoom <= 100) {
					viewX = 0;
					viewY = 0;
				}
				repaint();
			}

		});

		clock.start();

		content.setUndecorated(true);
		content.setSize(width, height);
		setBackgroundColor(colors.base);

		// Creates drag-able edges for resizing
		bottomDrag = new JPanel();
		bottomDrag.setPreferredSize(new Dimension(bottomDrag.getPreferredSize().width, 2));
		bottomDrag.setBackground(colors.secondary);
		bottomDrag.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent me) {
				if (resizeable == true)
					content.setCursor(new Cursor(Cursor.S_RESIZE_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent me) {
				if (resizeable == true)
					content.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				if (resizeable == true)
					initialClick = e.getPoint();
			}
		});
		bottomDrag.addMouseMotionListener(new MouseMotionAdapter() {

			@Override
			public void mouseDragged(MouseEvent e) {
				if (resizeable == true) {
					maxed = false;
					int yMoved = e.getPoint().y + initialClick.y;

					height = content.getHeight() + yMoved;
					content.setSize(width, height);
				}
			}
		});
		topDrag = new JPanel();
		topDrag.setPreferredSize(new Dimension(topDrag.getPreferredSize().width, 2));
		topDrag.setBackground(colors.secondary);
		topDrag.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent me) {
				if (resizeable == true)
					content.setCursor(new Cursor(Cursor.S_RESIZE_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent me) {
				if (resizeable == true)
					content.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				if (resizeable == true)
					initialClick = e.getPoint();
			}
		});
		topDrag.addMouseMotionListener(new MouseMotionAdapter() {

			@Override
			public void mouseDragged(MouseEvent e) {
				if (resizeable == true) {
					maxed = false;
					int yMoved = e.getPoint().y + initialClick.y;

					content.setLocation(content.getX(), content.getY() + yMoved);
					height = content.getHeight() - yMoved;
					content.setSize(width, height);
				}
			}
		});

		leftDrag = new JPanel();
		leftDrag.setPreferredSize(new Dimension(2, leftDrag.getPreferredSize().height));
		leftDrag.setBackground(colors.secondary);
		leftDrag.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent me) {
				if (resizeable == true)
					content.setCursor(new Cursor(Cursor.E_RESIZE_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent me) {
				if (resizeable == true)
					content.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				if (resizeable == true)
					initialClick = e.getPoint();
			}
		});
		leftDrag.addMouseMotionListener(new MouseMotionAdapter() {

			@Override
			public void mouseDragged(MouseEvent e) {
				if (resizeable == true) {
					maxed = false;
					int xMoved = e.getPoint().x + initialClick.x;
					width = content.getWidth() - xMoved;
					content.setLocation(content.getX() + xMoved, content.getY());
					content.setSize(width, height);
				}
			}
		});
		rightDrag = new JPanel();
		rightDrag.setPreferredSize(new Dimension(2, rightDrag.getPreferredSize().height));
		rightDrag.setBackground(colors.secondary);
		rightDrag.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent me) {
				if (resizeable == true)
					content.setCursor(new Cursor(Cursor.E_RESIZE_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent me) {
				if (resizeable == true)
					content.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				if (resizeable == true)
					initialClick = e.getPoint();
			}
		});
		rightDrag.addMouseMotionListener(new MouseMotionAdapter() {

			@Override
			public void mouseDragged(MouseEvent e) {
				if (resizeable == true) {
					maxed = false;
					int xMoved = e.getPoint().x + initialClick.x;

					width = content.getWidth() + xMoved;
					content.setSize(width, height);
				}
			}
		});

		// holds top items (toolBar & resize)
		topPane = new JPanel(new BorderLayout());

		// buttons
		closeButton = new JLabel("  X  ");
		closeButton.setForeground(colors.tertiary);
		min = new JLabel("  _  ");
		min.setForeground(colors.tertiary);
		max = new JLabel("  O  ");
		max.setForeground(colors.tertiary);
		buttons = new JPanel();
		buttons.setBackground(colors.secondary);

		// title
		title = new JLabel("   " + titleText);
		title.setForeground(colors.tertiary);

		// adds everything to screen
		buttons.add(min);
		buttons.add(max);
		buttons.add(closeButton);
		toolBar.add(buttons, BorderLayout.LINE_END);
		toolBar.add(title, BorderLayout.LINE_START);
		toolBar.setBackground(colors.secondary);

		// finalizes window
		if (!undecorated) {
			topPane.add(toolBar, BorderLayout.PAGE_END);
			topPane.add(topDrag, BorderLayout.PAGE_START);
			content.add(rightDrag, BorderLayout.LINE_END);
			content.add(leftDrag, BorderLayout.LINE_START);
			content.add(bottomDrag, BorderLayout.PAGE_END);
			content.add(topPane, BorderLayout.PAGE_START);
		}
		content.add(this);
		content.setVisible(true);
		content.setLocationRelativeTo(null);

		// click and drag functionality
		toolBar.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (draggable) {
					initialClick = e.getPoint();
					content.getComponentAt(initialClick);
				}
			}
		});

		// allows for draggable movement
		toolBar.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				if (draggable) {
					// get location of Window
					int thisX = content.getLocation().x;
					int thisY = content.getLocation().y;

					// Determine how much the mouse moved since the initial click
					int xMoved = (thisX + e.getX()) - (thisX + initialClick.x);
					int yMoved = (thisY + e.getY()) - (thisY + initialClick.y);

					// Move window to this position
					int X = thisX + xMoved;
					int Y = thisY + yMoved;
					content.setLocation(X, Y);
				}
			}
		});

		/* Button Clicking for close, maximize & minimize */
		closeButton.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {

				if (!devMode || buttonsShowing) {
					System.exit(0);
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});
		min.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (!devMode || buttonsShowing) {

					x = 0;
					minimizeTimer = new Timer(5, new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							if (height - x > 0) {
								content.setSize(width, height - x);
								x += 5;
							} else {
								minimizeTimer.stop();
								content.setSize(width, height);
								content.setState(Frame.ICONIFIED);

							}

						}

					});

					minimizeTimer.start();

				}
			}
		});
		max.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (!devMode || buttonsShowing) {

					if (maxed) {
						content.setSize(width, height);
						content.setLocation(oldPos);
						maxed = false;
					} else {
						oldPos = content.getLocation();
						content.setSize(Toolkit.getDefaultToolkit().getScreenSize());
						content.setLocation(0, 0);
						maxed = true;
					}
				}
			}
		});

		content.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				keyPressed = e.getKeyCode();
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				keyPressed = -1;
			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

		});

		clear();
	}

}
