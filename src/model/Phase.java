package model;

import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Phase extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;

	private Image background;
	private Player player;
	private Timer timer;
	private List<BasicEnemy> enemy1;
	private List<Stars> stars;
	private boolean inGame;

	public Phase() {
		setFocusable(true);
		setDoubleBuffered(true);

		ImageIcon reference = new ImageIcon("resources\\blackBackground.jpg");
		background = reference.getImage();

		player = new Player();
		player.load();

		addKeyListener(new KeyboardAdapter());

		timer = new Timer(5, this);
		timer.start();
		enemyInitializer();
		starsInitializer();
		inGame = true;
	}

	public void enemyInitializer() {
		int coordinates[] = new int[40];
		enemy1 = new ArrayList<BasicEnemy>();

		for (int i = 0; i < coordinates.length; i++) {
			int x = (int) (Math.random() * 8000 + 1024);
			int y = (int) (Math.random() * 650 + 30);
			enemy1.add(new BasicEnemy(x, y));
		}
	}

	public void starsInitializer() {
		int coordinates[] = new int[10];
		stars = new ArrayList<Stars>();

		for (int i = 0; i < coordinates.length; i++) {
			int x = (int) (Math.random() * 1024);
			int y = (int) (Math.random() * 768);
			stars.add(new Stars(x, y));
		}
	}

	public void paint(Graphics g) {
		Graphics2D graphics = (Graphics2D) g;

		if (inGame) {
			graphics.drawImage(background, 0, 0, null);

			for (int u = 0; u < stars.size(); u++) {
				Stars q = stars.get(u);
				q.load();
				graphics.drawImage(q.getImage(), q.getX(), q.getY(), this);
			}

			graphics.drawImage(player.getImage(), player.getX(), player.getY(), this);

			List<Shot> shots = player.getShots();
			for (int i = 0; i < shots.size(); i++) {
				Shot m = shots.get(i);
				m.load();
				graphics.drawImage(m.getImage(), m.getX(), m.getY(), this);
			}

			for (int o = 0; o < enemy1.size(); o++) {
				BasicEnemy in = enemy1.get(o);
				in.load();
				graphics.drawImage(in.getImage(), in.getX(), in.getY(), this);
			}
	
		} else {
			ImageIcon gameOver = new ImageIcon("resources\\gameover.png");
			graphics.drawImage(gameOver.getImage(), 0, 0, null);
		}
		
		g.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		player.update();

		for (int u = 0; u < stars.size(); u++) {
			Stars on = stars.get(u);
			if (on.isVisible()) {
				on.update();
			} else {
				stars.remove(u);
			}
		}
 
		List<Shot> shots = player.getShots();
		for (int i = 0; i < shots.size(); i++) {
			Shot m = shots.get(i);
			if (m.isVisible()) {
				m.update();
				if (player.isTurbo()) {
					Shot.setSPEED(-1);
				}

				if (!player.isTurbo()) {
					Shot.setSPEED(20);
				}
			} else {
				shots.remove(i);
			}
		}

		for (int o = 0; o < enemy1.size(); o++) {
			BasicEnemy in = enemy1.get(o);
			if (in.isVisible()) {
				in.update();
			} else {
				enemy1.remove(o);
			}
		}
		
		checkCollisions();
		repaint();

	}

	public void checkCollisions() {
		Rectangle spaceshipForm = player.getBounds();
		Rectangle basicEnemyForm;
		Rectangle shotForm;

		for (int i = 0; i < enemy1.size(); i++) {
			BasicEnemy tempEnemy1 = enemy1.get(i);
			basicEnemyForm = tempEnemy1.getBounds();
			if (spaceshipForm.intersects(basicEnemyForm)) {
				if (player.isTurbo()) {
					tempEnemy1.setVisible(false);
				} else {
					player.setVisible(false);
					tempEnemy1.setVisible(false);
					inGame = false;
				}
			}
		}

		List<Shot> shots = player.getShots();
		for (int j = 0; j < shots.size(); j++) {
			Shot tempShot = shots.get(j);
			shotForm = tempShot.getBounds();
			for (int a = 0; a < enemy1.size(); a++) {
				BasicEnemy tempEnemy1 = enemy1.get(a);
				basicEnemyForm = tempEnemy1.getBounds();
				if (shotForm.intersects(basicEnemyForm)) {
					tempEnemy1.setVisible(false);
					tempShot.setVisible(false);
				}
			}
		}
	}

	private class KeyboardAdapter extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {
			player.keyPressed(e);
		}

		@Override
		public void keyReleased(KeyEvent e) {
			player.keyRelease(e);
		}
	}
}
