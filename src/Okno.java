import java.awt.*;

import javax.swing.*;

public class Okno extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Menu menu;

	public Okno(Zazeni zazeni) {
		super();
		
		setSize(new Dimension(zazeni.dolzina, zazeni.visina));
		setMinimumSize(new Dimension(800, 600));
		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		
		CardLayout cl = new CardLayout();
		
		
		JPanel kontejner = new JPanel();
//		
		kontejner.setLayout(cl);
////
		menu = new Menu(cl, zazeni);
		kontejner.add(menu, "MENI");
////
		add(kontejner);
		kontejner.setVisible(true);

//		add(new IgralnoPolje(cl, zazeni), BorderLayout.CENTER);
//		add(new Stanje(cl, zazeni),BorderLayout.NORTH);
//		pack();
		
	}

}
