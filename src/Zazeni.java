import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Zazeni extends tankWar {
	
//	static JFrame okno = new JFrame();
	public Okno okno;
	public Menu menu;
	
	
	public Zazeni(int dolzina, int visina, int verzija, int igralec) {
		super(dolzina, visina, verzija, igralec);
		
		okno = new Okno(this);
		okno.setVisible(true);
		
	}
	
	public void narisi() {
		okno.repaint();
	}
	
	public void igraj() throws InterruptedException {
		while (true) {
			if (jeKonecIgre()) {
				if (okno.menu.igralnoPolje.ustavi) {
					Thread.sleep(3000);
					okno.menu.igralnoPolje.setVisible(false);
					okno.menu.setVisible(true);
					ponastavi();
					okno.menu.igralnoPolje.ustavi = false;
				}
			}
			if (okno.menu.igralnoPolje != null) {
				if (okno.menu.igralnoPolje.povecujCas == true && okno.menu.igralnoPolje.cas <= 775) {
					okno.menu.igralnoPolje.cas += 5;
				}
				
				}
			posodobi();
//			System.out.println("�ivljenja: " +tank1.zivljenja + " : " + tank2.zivljenja);
			narisi();
			Thread.sleep(10);
		}
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int visina = args.length > 0? Integer.parseInt(args[0]): 700;
		int dolzina = args.length > 1? Integer.parseInt(args[1]): 1000;
		int verzija = args.length > 2? Integer.parseInt(args[2]): 1;
		
		Zazeni zazeni1 = new Zazeni(dolzina, visina, verzija, 1);

		try {
			zazeni1.igraj();
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		
	}
}
