
public class Zazeni extends tankWar {
	// razred Zazeni vsebuje main metodo in glavno while zanko za izvajanje igre
	
	public Okno okno;
	public Meni meni;
	
	
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
				if (okno.meni.igralnoPolje.ustavi) {
					Thread.sleep(3000); // nekaj casa pocakamo preden se igra resetira
					// zamenjamo panel igre s panelom menija 
					okno.meni.igralnoPolje.setVisible(false); 
					okno.meni.setVisible(true);
					ponastavi(); 
					okno.meni.igralnoPolje.ustavi = false;
				}
			}
			if (okno.meni.igralnoPolje != null) {
				if (okno.meni.igralnoPolje.povecujCas == true && okno.meni.igralnoPolje.cas <= 775) {
					okno.meni.igralnoPolje.cas += 5; // povecujemo stevec cas za dolocitev moci izstrelka
				}
				
				}
			posodobi();
			narisi();
			Thread.sleep(10);
		}
	}


	public static void main(String[] args) {
		// nastavimo zacetne mere okna
		int visina = 700;
		int dolzina = 1000;
		int verzija =  1;
		
		Zazeni zazeni1 = new Zazeni(dolzina, visina, verzija, 1);

		try {
			zazeni1.igraj();
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		
	}
}
