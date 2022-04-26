package model;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class BasicEnemy {

	private Image image;
	private int x, y;
	private int height, width;
	private boolean isVisible;

	//private static final int WIDTH = 938;
	private static int SPEED = 5;

	public BasicEnemy(int x, int y) {
		this.x = x;
		this.y = y;
		isVisible = true;
	}

	public void load() {
		ImageIcon reference = new ImageIcon("resources\\tentar.png");
		image = reference.getImage();

		this.width = image.getWidth(null);
		this.height = image.getHeight(null);
	}

	public void update() {
		this.x -= SPEED;

		/*if (this.x > WIDTH) {
			isVisible = false;
		}*/
	}
	
	public Rectangle getBounds() {
		return new Rectangle (x, y, width, height);
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
