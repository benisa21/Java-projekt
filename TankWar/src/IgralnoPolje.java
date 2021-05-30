import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.*;
import javax.swing.event.MouseInputAdapter;

public class IgralnoPolje extends JPanel implements KeyListener, MouseMotionListener {
	// razred IgralnoPolje implementira grafiko igrice TankWar
	private CardLayout cl;
	public Zazeni zazeni;

	public Tank tank1;
	public Tank tank2;	
	public Tank trenutniTank;
	public Eksplozija eksplozija;
	
	public Krogla krogla;
	
	public int x_miske;
	public int y_miske;
	
	public int x_miske_premik;
	public int y_miske_premik;
	
	public long zac_cas;
	public long kon_cas;
	
	public int razdalja;
	public int cas;
	
	public double preizkus;
	
	public boolean povecujCas;
	
	public boolean ustavi;

	public IgralnoPolje(CardLayout cl, Zazeni zazeni) {
        super();
		
	this.cl = cl;
	this.zazeni = zazeni;

        tank1 = zazeni.tank1;
        tank2 = zazeni.tank2;
        eksplozija = zazeni.eksplozija;
        
	// ustvarimo nov objek tanka, ki predstavlja tank, ki je trenutno na potezi 
	if (zazeni.igralec == 1) {
        	trenutniTank = tank1; 
        }
        else {
        	trenutniTank = tank2;
        }
        
        setBackground(Color.pink);
        setFocusable(true);
        addMouseMotionListener(this);		
	addMouseListener(new MouseInputAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {			
		}

		public void mousePressed(MouseEvent e) {

			zazeni.eksplozija = null; // trenutna eksplozija se odstrani
			cas = 0;
			povecujCas = true; // s pomocjo stevca cas nastavimo moč izstrelitve krogle
			x_miske = e.getX();
			y_miske = e.getY();

			repaint();


		}

		public void mouseReleased(MouseEvent e) {

			povecujCas = false;
			// poiscemo razdaljo med tankom in klikom miske 
			razdalja = (int)(Math.sqrt(Math.pow((x_miske - (int)trenutniTank.getPolozaj().x), 2.0) + Math.pow((y_miske - (int)trenutniTank.getPolozaj().y), 2.0)));
			
			// izracunamo hitrost v x in y smeri na podlagi dolzine casa, ko smo drzali klik miske
			int x = (int)(((double)(x_miske - (int)trenutniTank.getPolozaj().x)/(double)razdalja)*(double)(cas));
			int y = (int)(((double)(y_miske - (int)trenutniTank.getPolozaj().y)/(double)razdalja)*(double)(cas));
			
			int x_tank = (int)trenutniTank.getPolozaj().x + (trenutniTank.getPolozaj().dolzina / 2);
			int y_tank = (int)trenutniTank.getPolozaj().y + (trenutniTank.getPolozaj().visina / 3);
			
			// izstrelimo kroglo, ki ji nastavimo zacetni polozaj ter zacetno hitrost v x in y smeri
			zazeni.krogla = new Krogla(new Polozaj(x_tank, y_tank-20, 15,15), x, y, 0, 0);
			zazeni.eksplozija = null;

			cas = 0; // ponastavimo stevec casa
			repaint();
		}


	});
		
		
		
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// sprotno pridobivamo polozaj miske na oknu, saj ta določa smer leta krogle
		x_miske_premik = e.getX();
		y_miske_premik = e.getY();
		
		repaint();
	}

	
	
	
	@Override
    public void paint(Graphics g) {
        super.paint(g); 
        Graphics2D liki = (Graphics2D)g; 

        liki.setColor(Color.BLACK);
        
	// s pomocjo faktorjev dolzina in visina nastavljamo dolzino in sirino elementov na igralnem polju v primeru razširitve oz zožitve okna
        double dolzina = (double)(getWidth())/(double)(zazeni.dolzina);
        double visina = (double)(getHeight())/(double)(zazeni.visina);
        
        liki.setFont(new Font(Font.SANS_SERIF, Font.BOLD|Font.ITALIC, 50));
     
        // ko enemu izmed tankov zmanjka zivljenj izpisemo spodnji napis in zakljucimo igro
        if (trenutniTank == zazeni.tank1) {
		if (zazeni.tank1.zivljenja == 0) {
			liki.drawString("IGRALEC 2 JE ZMAGOVALEC!", (int)(5*(getWidth()/32) * dolzina), (int)(getHeight()/4 * visina));
			ustavi = true;
		}
		}
			
	else {
		if (zazeni.tank2.zivljenja == 0) {
			liki.drawString("IGRALEC 1 JE ZMAGOVALEC!",  (int)(5*(getWidth()/32) * dolzina), (int)(getHeight()/4 * visina));
			ustavi = true;
		}
	}
        liki.setStroke(new BasicStroke(2.0f));
        
	// narisemo kazalec moči 
	liki.drawPolygon(new int[] {(int)(50*dolzina), (int)((50+775*0.2)*dolzina), (int)((50+775*0.2)*dolzina)}, new int[] {(int)(50*visina), (int)((50-775*0.02)*visina), (int)(50*visina)}, 3);
	liki.drawPolygon(new int[] {(int)((800)*dolzina), (int)(((800)+775*0.2)*dolzina), (int)(((800)+775*0.2)*dolzina)}, new int[] {(int)(50*visina), (int)((50-775*0.02)*visina), (int)(50*visina)}, 3);

	liki.setStroke(new BasicStroke(1.0f));

        liki.setColor(Color.BLACK);
        
        liki.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
		
	liki.drawString("Igralec 1", (int)(50*dolzina), (int)(20*visina));
	liki.drawString("Igralec 2", (int)(getWidth() - 160*dolzina), (int)(20*visina));
	
	// narisemo srca, ki predstavljajo stevilo zivljenj za posamezni tank
	for (int i = 1 ; i < zazeni.tank1.zivljenja + 1; i++) {
		liki.drawString("\u2665", (int)(150*dolzina) + (int)(i*20*dolzina), (int)(20*visina));
	}

	for (int i = 1 ; i < zazeni.tank2.zivljenja + 1; i++) {
		liki.drawString("\u2665", (int)(getWidth() - 190*dolzina) - (int)(i*20*dolzina), (int)(20*visina));
	}

	// narisemo tanka na zacetno pozicijo
	liki.drawImage(tank1.slika, (int)Math.round((tank1.getPolozaj().getX())*dolzina), (int)Math.round((tank1.getPolozaj().getY())*visina), (int)Math.round((tank1.getPolozaj().getDolzina())*dolzina),(int)Math.round((tank1.getPolozaj().getVisina())*visina), null);
	liki.drawImage(tank2.slika, (int)Math.round((tank2.getPolozaj().getX())*dolzina), (int)Math.round((tank2.getPolozaj().getY())*visina), (int)Math.round((tank2.getPolozaj().getDolzina())*dolzina),(int)Math.round((tank2.getPolozaj().getVisina())*visina), null);
	
	// narisemo ozadje (ravnino in hriba)
	liki.drawRect((int)Math.round(zazeni.teren1.getPolozaj().getX()*dolzina), (int)Math.round((zazeni.teren1.getPolozaj().getY())*visina), (int)Math.round(zazeni.teren1.getPolozaj().getDolzina()*dolzina), (int)Math.round(zazeni.teren1.getPolozaj().getVisina()*visina));
	liki.fillRect((int)Math.round(zazeni.teren1.getPolozaj().getX()*dolzina), (int)Math.round((zazeni.teren1.getPolozaj().getY())*visina), (int)Math.round(zazeni.teren1.getPolozaj().getDolzina()*dolzina), (int)Math.round(zazeni.teren1.getPolozaj().getVisina()*visina));

	liki.drawRect((int)Math.round(zazeni.teren2.getPolozaj().getX()*dolzina), (int)Math.round((zazeni.teren2.getPolozaj().getY())*visina), (int)Math.round(zazeni.teren2.getPolozaj().getDolzina()*dolzina), (int)Math.round(zazeni.teren2.getPolozaj().getVisina()*visina));
	liki.fillRect((int)Math.round(zazeni.teren2.getPolozaj().getX()*dolzina), (int)Math.round((zazeni.teren2.getPolozaj().getY())*visina), (int)Math.round(zazeni.teren2.getPolozaj().getDolzina()*dolzina), (int)Math.round(zazeni.teren2.getPolozaj().getVisina()*visina));

	liki.fillOval((int)Math.round((zazeni.hrib1.getPolozaj().getX())*dolzina), (int)Math.round((zazeni.hrib1.getPolozaj().getY())*visina), (int)Math.round((zazeni.hrib1.getPolozaj().getDolzina())*dolzina), (int)Math.round((zazeni.hrib1.getPolozaj().getVisina())*visina));
	liki.fillOval((int)Math.round((zazeni.hrib2.getPolozaj().getX())*dolzina), (int)Math.round((zazeni.hrib2.getPolozaj().getY())*visina), (int)Math.round((zazeni.hrib2.getPolozaj().getDolzina())*dolzina), (int)Math.round((zazeni.hrib2.getPolozaj().getVisina())*visina));

	// izrisemo eksplozijo na polozaj, kjer je krogla trčila z drugim objekom
	if (zazeni.eksplozija != null) {
	liki.drawImage(zazeni.eksplozija.slika, (int)Math.round((zazeni.eksplozija.getPolozaj().getX())*dolzina), (int)Math.round((zazeni.eksplozija.getPolozaj().getY())*visina), (int)Math.round((zazeni.eksplozija.getPolozaj().getDolzina())*dolzina),(int)Math.round((zazeni.eksplozija.getPolozaj().getVisina())*visina), null);
	}

	if (zazeni.igralec == 1) {
	trenutniTank = tank1;
    	}
    	else {
		trenutniTank = tank2;
    	}

	int x_tank = (int)trenutniTank.getPolozaj().x + (trenutniTank.getPolozaj().dolzina / 2);
	int y_tank = (int)trenutniTank.getPolozaj().y + (trenutniTank.getPolozaj().visina / 3);

	double x = (x_miske_premik - x_tank)*dolzina;
	double y = (y_miske_premik - y_tank)*visina;

	double h = Math.sqrt(y*y + x*x);
	double d = 25; // dolzina cevi, ki kaže smer izstrelka

	double x_koncna = (x_tank + (d*x)/h);
	double y_koncna = (y_tank + (d*y)/h);
	
	// v prej okvirjen trikotnik narisemo dejansko moč izstrelka 
	if (trenutniTank == tank1) {
		liki.fillPolygon(new int[] {(int)(50*dolzina), (int)((50+cas*0.2)*dolzina), (int)((50+cas*0.2)*dolzina)}, new int[] {(int)(50*visina), (int)((50-cas*0.02)*visina), (int)(50*visina)}, 3);

	}
	else {
		liki.fillPolygon(new int[] {(int)((800)*dolzina), (int)(((800)+cas*0.2)*dolzina), (int)(((800)+cas*0.2)*dolzina)}, new int[] {(int)(50*visina), (int)((50-cas*0.02)*visina), (int)(50*visina)}, 3);

	}


	liki.setStroke(new BasicStroke(4.0f));

	liki.setColor(new Color (78,82,66));
	// narisemo top tanka
	liki.drawLine((int)(x_tank*dolzina), (int)(y_tank*visina), (int)(x_koncna*dolzina), (int)(y_koncna*visina));

	    
	// narisemo kroglo
	liki.setColor(Color.RED);
	if (zazeni.krogla != null) {
		liki.fillOval((int)(zazeni.krogla.x*dolzina), (int)(zazeni.krogla.y*visina), (int)(15*dolzina), (int)(15*visina));
	}
		
		
		
    }

	@Override
	public void keyTyped(KeyEvent e) {

	}

	
	@Override
	// s puscicama levo in desno lahko premikamo tank
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode()== KeyEvent.VK_RIGHT) {

			trenutniTank.setPremik_v_desno(true);

		}
		else if (e.getKeyCode()==KeyEvent.VK_LEFT) {

			trenutniTank.setPremik_v_levo(true);

		}
		zazeni.eksplozija = null; // ce tank prestavimo eksplozija izgine
		repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode()== KeyEvent.VK_RIGHT) {

			trenutniTank.setPremik_v_desno(false);
		}
		else if (e.getKeyCode()==KeyEvent.VK_LEFT) {

			trenutniTank.setPremik_v_levo(false);
		}
		zazeni.eksplozija = null;
		repaint();
	}


}
