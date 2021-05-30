import java.awt.*;

import javax.swing.*;

public class Okno extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Meni meni;

	public Okno(Zazeni zazeni) {
		super();
		setBackground(Color.PINK);
		setSize(new Dimension(zazeni.dolzina, zazeni.visina));
		setMinimumSize(new Dimension(800, 600));
		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		
		CardLayout cl = new CardLayout();
		
		
		JPanel kontejner = new JPanel();	
		kontejner.setLayout(cl);
		meni = new Meni(cl, zazeni);
		kontejner.add(meni, "MENI");
		add(kontejner);
		kontejner.setVisible(true);

		
	}

}

