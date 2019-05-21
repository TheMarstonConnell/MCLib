package xyz.marstonconnell.testing;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import xyz.marstonconnell.graphics.engine.EngineFrame;
import xyz.marstonconnell.graphics.engine.drawing.Drawable;
import xyz.marstonconnell.graphics.engine.drawing.DrawingLayer;
import xyz.marstonconnell.graphics.engine.drawing.LayerContainer;
import xyz.marstonconnell.graphics.engine.physics.Entity;
import xyz.marstonconnell.graphics.engine.physics.Projectile;
import xyz.marstonconnell.graphics.engine.text.ChoiceDialog;
import xyz.marstonconnell.graphics.engine.text.TextDialog;

public class testing {

	static boolean inDiaglog = false;
	static int textDex;
	static Timer dialogWait;
	static boolean isWaiting = false;
	static ChoiceDialog td;
	static TextDialog td2;

	public static void main(String[] args) {

		EngineFrame af = new EngineFrame(800, 800, "");
		DrawingLayer dl = new DrawingLayer();

		Entity draw = new Entity(0, 0, 100, dl);
		Entity draw2 = new Entity(300, 300, 200, dl);

		draw.createState("still", 2, 0.5, testing.class);
		draw2.createState("still", 2, 0.8, testing.class);

		draw.createState("walk", 5, 0.2, testing.class);
		draw2.createState("walk", 5, 0.2, testing.class);

		draw.setState("still");
		draw2.setState("still");

		af.getLayerContainer().insertLayer(0, dl);

		dialogWait = new Timer(200, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				isWaiting = false;
				dialogWait.stop();
			}
		});

		Timer t = new Timer(1000 / 60, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (inDiaglog) {

					if (!isWaiting) {
						if (td != null) {
							if (af.upDown) {
								td.choiceUp();
								isWaiting = true;
								dialogWait.start();
							}
							if (af.downDown) {
								td.choiceDown();
								isWaiting = true;
								dialogWait.start();

							}
						}
					}
				}

				if (af.spaceDown) {
					if (inDiaglog) {
						if (!af.isKeysLocked()) {
							if (!isWaiting) {
								af.getLayerContainer().removeLayer(textDex);
								inDiaglog = !inDiaglog;
								isWaiting = true;
								dialogWait.start();

								if(td == null) {

								}else {

									int choice = td.getChoice();
									if(choice == 0) {
										startTextDialog(af, "Wow okay.");

									}else {
										startTextDialog(af, "You suck.");

									}
								}
								td = null;
								td2 = null;

								
							}
						}

					} else {
						if (Math.abs(draw.getCenterX() - draw2.getCenterX()) < (draw.getWidth() + draw2.getWidth() + 2)
								/ 2) {
							if (!af.isKeysLocked()) {
								if (!isWaiting) {
									startChoiceDialog(af, "I hate everything about who you are you stupid imbecile.");
									isWaiting = true;
								}
							}

						}
					}
				}

				if (!inDiaglog) {

					if (af.upDown) {
						draw2.moveUp(1, dl.getDrawables());
					}
					if (af.downDown) {
						draw2.moveDown(1, dl.getDrawables());
					}
					if (af.rightDown) {
						draw2.moveRight(1, dl.getDrawables());
					}
					if (af.leftDown) {
						draw2.moveLeft(1, dl.getDrawables());
					}

					if (af.upDown || af.downDown || af.rightDown || af.leftDown) {
						if (draw2.getState().equals("still")) {
							draw2.setState("walk");

						}
					} else {
						if (draw2.getState().equals("walk")) {
							draw2.setState("still");

						}
					}

					if (af.wDown) {
						draw.moveUp(1, dl.getDrawables());
					}
					if (af.sDown) {
						draw.moveDown(1, dl.getDrawables());
					}
					if (af.dDown) {
						draw.moveRight(1, dl.getDrawables());
					}
					if (af.aDown) {
						draw.moveLeft(1, dl.getDrawables());
					}

					if (af.aDown || af.wDown || af.dDown || af.sDown) {
						if (draw.getState().equals("still")) {
							draw.setState("walk");

						}
					} else {
						if (draw.getState().equals("walk")) {
							draw.setState("still");

						}
					}

				}

			}
		});

		t.start();

	}

	private static void startChoiceDialog(EngineFrame af, String text) {
		af.setKeysLocked(true);
		textDex = af.getLayerContainer().findHighestLayer() + 1;
		td = new ChoiceDialog(text, textDex, 10, af.height - af.height / 4, af.getWidth() - 20, af.getHeight() / 4,
				"NPC Man", new String[] { "I don't like you.", "epic." });
		td.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				af.setKeysLocked(false);
				dialogWait.start();
				System.out.println("Now Complete");
			}
		});
		af.getLayerContainer().insertLayer(textDex, td);
		inDiaglog = true;
	}

	private static void startTextDialog(EngineFrame af, String text) {
		af.setKeysLocked(true);
		textDex = af.getLayerContainer().findHighestLayer() + 1;
		td2 = new TextDialog(text, textDex, 10, af.height - af.height / 4, af.getWidth() - 20, af.getHeight() / 4,
				"NPC Man");
		td2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				af.setKeysLocked(false);
				dialogWait.start();
				System.out.println("Now Complete");
			}
		});
		af.getLayerContainer().insertLayer(textDex, td2);
		inDiaglog = true;
	}

}
