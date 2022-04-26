package model;

import java.awt.Image;

import java.util.Random;

import javax.swing.ImageIcon;

public class Stars {

	private Image image;
	private int x, y;
	private int height, width;
	private boolean isVisible;

	// private static final int WIDTH = 938;
	private static int SPEED = 5;

	public Stars(int x, int y) {
		this.x = x;
		this.y = y;
		isVisible = true;
	}

	public void load() {
		ImageIcon reference = new ImageIcon("resources\\stars.png");
		image = reference.getImage();

		this.width = image.getWidth(null);
		this.height = image.getHeight(null);
	}

	public void update() {
		if (this.x < -600) {
			this.x = width;
			Random a = new Random();
			int m = a.nextInt(500);
			this.x = m + 1024;

			Random b = new Random();
			int n = b.nextInt(768);
			this.y = n;
		} else {
			this.x -= SPEED;
		}
	}

	public boolean isVisible() {
		return isVisible;
	}

	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}

	public static int getSPEED() {
		return SPEED;
	}

	public static void setSPEED(int sPEED) {
		SPEED = sPEED;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Image getImage() {
		return image;
	}

}
