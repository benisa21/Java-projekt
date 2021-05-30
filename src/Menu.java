import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Timer;

import javax.swing.JPanel;

public class Menu extends JPanel implements Runnable, MouseListener {

	private Thread paintThread;

//	private Polozaj celota;
	private Polozaj igraj;
//	private Polozaj opcije;
	private Polozaj izhod;
//	private Polozaj naslov;
	public IgralnoPolje igralnoPolje;
	
	private CardLayout cardLayout;
	private Zazeni zazeni;


	public Menu (CardLayout cardLayout, Zazeni zazeni) {
		super();
		this.zazeni = zazeni;
		ustvariOkna();
		paintThread = new Thread(this, "paintThread");
		this.cardLayout = cardLayout;
		addMouseListener(this);
		paintThread.start();
		this.setBackground(Color.getHSBColor(0.2f, 0.6f, 0.7f));
	}
	
	private void ustvariOkna() {
		int x = 500;
		int y = 100;
		int dolzina = 150;
		int visina = 100;
//		celota = new Polozaj( x, y,  1024, 768);
		igraj = new Polozaj( x, y+150,  dolzina, visina);
//		opcije = new Polozaj( x, y+200,  dolzina, visina);
		izhod = new Polozaj( x, y+250,  dolzina, visina);
//		naslov = new Polozaj( x, y+400,  dolzina, visina);
	}

	@Override
	public void paint(Graphics g){
		Graphics2D graphics = (Graphics2D)g;
		graphics.setBackground(Color.BLUE);

		graphics.setFont(new Font("Cursive", Font.BOLD|Font.ITALIC, 50));
		graphics.setColor(java.awt.Color.RED);
		
		double dolzina = (double)(getWidth())/(double)(zazeni.dolzina);
        double visina = (double)(getHeight())/(double)(zazeni.visina);

//		graphics.drawString("IGRAJ", igraj.getX() - igraj.dolzina/2, igraj.getY() + igraj.visina/2);
//		graphics.drawString("OPCIJE", opcije.getX() - opcije.dolzina/2, opcije.getY() + opcije.visina/2);
//		graphics.drawString("IZHOD", izhod.getX() - izhod.dolzina/2, izhod.getY() + izhod.visina/2);
		
		graphics.drawString("IGRAJ", (int)((igraj.getX() - igraj.dolzina/2)*dolzina), (int)((igraj.getY() + igraj.visina/2)*visina));
//		graphics.drawString("OPCIJE", (int)((opcije.getX() - opcije.dolzina/2)*dolzina), (int)((opcije.getY() + opcije.visina/2)*visina));
		graphics.drawString("IZHOD", (int)((izhod.getX() - izhod.dolzina/2)*dolzina), (int)((izhod.getY() + izhod.visina/2)*visina));
	}
	
	@Override
	public void mouseClicked(MouseEvent mouseEvent) {
		if(mouseEvent.getButton() == MouseEvent.BUTTON1){
			if(igraj.jeZnotraj(mouseEvent.getX(), mouseEvent.getY())){
				igralnoPolje = new IgralnoPolje(cardLayout, zazeni);
//				Stanje stanje = new Stanje(cardLayout, zazeni);
				JPanel kontejner = (JPanel) ((JPanel) mouseEvent.getSource()).getParent();
				kontejner.add(igralnoPolje, BorderLayout.CENTER);
//				kontejner.add(stanje, BorderLayout.NORTH);
			 	cardLayout.show(kontejner, BorderLayout.CENTER);
//			 	cardLayout.show(kontejner, BorderLayout.NORTH);
			 	kontejner.addKeyListener(igralnoPolje);
			 	kontejner.grabFocus();
			}
			else if(izhod.jeZnotraj(mouseEvent.getX(), mouseEvent.getY())){

					System.exit(0);
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
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
	public void run() {
		// TODO Auto-generated method stub
		
	}
}
