import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

public class Meni extends JPanel implements Runnable, MouseListener {

	private Thread paintThread;
	private Polozaj igraj;
	private Polozaj izhod;
	public IgralnoPolje igralnoPolje;
	private CardLayout cardLayout;
	private Zazeni zazeni;


	public Meni (CardLayout cardLayout, Zazeni zazeni) {
		//Ustvarimo začetni meni, ki se prikaže ob zagonu igrice
		super();
		this.setBackground(Color.PINK);
		this.zazeni = zazeni;
		ustvariOkna();
		paintThread = new Thread(this, "paintThread");
		this.cardLayout = cardLayout;
		addMouseListener(this);
		paintThread.start();
	}
	
	private void ustvariOkna() {
		int x = 500;
		int y = 100;
		int dolzina = 150;
		int visina = 100;
		//ustvarimo dva manjša okna, na katera uporabnik lahko klikne
		igraj = new Polozaj(x, y+150,  dolzina, visina);
		izhod = new Polozaj(x, y+250,  dolzina, visina);

	}

	@Override
	public void paint(Graphics g){
		Graphics2D graphics = (Graphics2D)g;
		graphics.setBackground(Color.BLUE);

		graphics.setFont(new Font("Cursive", Font.BOLD|Font.ITALIC, 50));
		graphics.setColor(java.awt.Color.BLACK);
		
		double dolzina = (double)(getWidth())/(double)(zazeni.dolzina);
        double visina = (double)(getHeight())/(double)(zazeni.visina);
		

		graphics.drawString("IGRAJ", (int)((igraj.getX() - igraj.dolzina/2)*dolzina), (int)((igraj.getY() + igraj.visina/2)*visina));
		graphics.drawString("IZHOD", (int)((izhod.getX() - izhod.dolzina/2)*dolzina), (int)((izhod.getY() + izhod.visina/2)*visina));
	}
	
	@Override
	public void mouseClicked(MouseEvent mouseEvent) {
		//uporabniku omogočimo, da s klikom miške zažene oziroma zapre igrico, v odvisnosti od pozicije miške
		double dolzina = (double)(getWidth())/(double)(zazeni.dolzina);
        double visina = (double)(getHeight())/(double)(zazeni.visina);
		if(mouseEvent.getButton() == MouseEvent.BUTTON1){
			if(igraj.jeZnotraj((int) (mouseEvent.getX()*dolzina), (int) (mouseEvent.getY()*visina))){
				igralnoPolje = new IgralnoPolje(cardLayout, zazeni);
				JPanel kontejner = (JPanel) ((JPanel) mouseEvent.getSource()).getParent();
				//v kontejner zraven menija dodamo še igralno polje
				kontejner.add(igralnoPolje, BorderLayout.CENTER);
				//omogočimo prikaz izbranega panela
			 	cardLayout.show(kontejner, BorderLayout.CENTER);
			 	//omogočimo uporabo MouseEvent tudi v igralnem polju
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

