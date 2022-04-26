package container;

import javax.swing.JFrame;

import model.Phase;

public class Container extends JFrame {

	private static final long serialVersionUID = 1L;

	public Container() {
		add(new Phase());
		setTitle("my cool game");
		setSize(1024, 728);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		this.setResizable(false);
		setVisible(true);
	}

	public static void main(String[] args) {
		new Container();
	}
}
