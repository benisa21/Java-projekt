import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;


public class Stanje extends JPanel{
	
	public Zazeni zazeni;
	public CardLayout cd;
	
	public List<JLabel>  zivljenja1; 
	public List<JLabel>  zivljenja2; 
	
	public Stanje (CardLayout cd, Zazeni zazeni) {
		super();
		this.zazeni = zazeni;
		this.cd = cd;
		
        setBackground(Color.PINK);
        setMinimumSize(new Dimension(getWidth(), 50));
        setPreferredSize(new Dimension(getWidth(), 50));
        
	}
	
	@ Override
	public void paint(Graphics g) {
		super.paint(g);
		
		Graphics2D liki = (Graphics2D)g;
//		System.out.println(zazeni.tank1.zivljenja + "," + zazeni.tank2.zivljenja);
		
		double dolzina = (double)(getWidth())/(double)(zazeni.dolzina);
        double visina = (double)(getHeight())/(double)(zazeni.visina);
        
//        System.out.println(dolzina);
		
		liki.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
		
		FontMetrics mere = liki.getFontMetrics();
		
		liki.drawString("Igralec 1", (int)(50*dolzina), (int)(400*visina));
		liki.drawString("Igralec 2", (int)(getWidth() - 160*dolzina), (int)(400*visina));
		
		for (int i = 1 ; i < zazeni.tank1.zivljenja + 1; i++) {
			liki.drawString("\u2665", (int)(150*dolzina) + (int)(i*20*dolzina), (int)(400*visina));
		}
		
		for (int i = 1 ; i < zazeni.tank2.zivljenja + 1; i++) {
			liki.drawString("\u2665", (int)(getWidth() - 190*dolzina) - (int)(i*20*dolzina), (int)(400*visina));
		}
		
	}

}
