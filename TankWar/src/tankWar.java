public class tankWar {
	// v razredu je definirano osnovno grafično ozadje igrice
	//preverjamo kolizijo med objekti
	
	
	//vsak tank na začetku igre ima pet življenj
	public static final int MAX_ST_ZIVLJENJ = 5;
	
	static final double delta = 0.03;
	//upoštevamo gravitacijo
	public double G = -10.0;
	
	public Tank tank1;
	public Tank tank2;
	public Krogla krogla;
	public Eksplozija eksplozija;
	
	public Teren_raven teren1;
	public Teren_raven teren2;
	
	public Teren_hrib hrib1;
	public Teren_hrib hrib2;
	
	public int dolzina;
	public int visina;
	public int igralec;
	
	public tankWar(int dolzina, int visina, int verzija, int igralec) {
		
		this.dolzina = dolzina;
		this.visina = visina;
		this.igralec = igralec;
		//Tankom je začetni položaj določen
		Polozaj polozajTank1 = new Polozaj(0, 2*visina/3 - 45 + 100, 70, 45);
		Polozaj polozajTank2 = new Polozaj(dolzina - 70, visina/2 - 45 + 100, 70, 45);
		
		tank1 = new Tank("Predstavitev/tank1.png", polozajTank1, false, false, 1);
		tank2 = new Tank("Predstavitev/tank2.png", polozajTank2, false, false, 2);
		//določimo položaj ravnega terena, ki ga predstavlja pravokotnik
		Polozaj teren1 = new Polozaj(0,2*visina/3+100, dolzina/2, visina/3 +1);
		Polozaj teren2 = new Polozaj(dolzina/2, visina/2+100, dolzina/2, visina/2 +1);
		//določimo položaj hriba, ki ga aproksimira premaknjena elipsa
		Polozaj hrib1 = new Polozaj(dolzina/8,3*visina/7+100, dolzina/2, 3*visina/2);
		Polozaj hrib2 = new Polozaj(dolzina/3,2*visina/7+100, dolzina/2, 3*visina/2);
		
		this.teren1 = new Teren_raven(teren1);
		this.teren2 = new Teren_raven(teren2);
		
		this.hrib1 = new Teren_hrib(hrib1, 0, 180);
		this.hrib2 = new Teren_hrib(hrib2, 0, 180);

	}
	
	public void ponastavi () {
		//stvari se ob novi izstrelitvi kroglice posodabljajo
		tank1.zivljenja = MAX_ST_ZIVLJENJ;
		tank2.zivljenja = MAX_ST_ZIVLJENJ;
		eksplozija = null;
		Polozaj polozajTank1 = new Polozaj(0, 2*visina/3 - 45 + 100, 70, 45);
		Polozaj polozajTank2 = new Polozaj(dolzina - 70, visina/2 - 45 + 100, 70, 45);
		tank1.polozaj = polozajTank1;
		tank2.polozaj = polozajTank2;
	}
	
	public boolean jeKonecIgre () {
		//če en izmed tankov izgubi vseh 5 življenj je igre konec
		if (tank1.zivljenja == 0 || tank2.zivljenja == 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void jeUstreljen () {
		//ob vsakem zadetku nasprotnika, istemu zmanjšamo število življenj za 1
		if (igralec == 1) {
			tank1.zivljenja -= 1;
		}
		else {
			tank2.zivljenja -= 1;
		}
	}
	
	public void novaRunda() {
		//igralca se zamenjata, naslednji igralec je na potezi
		if (igralec == 1) {
			igralec = 2;
		}
		
		else {
			igralec = 1;
		}
		krogla = null;
//		eksplozija = null;
		
	}
	
	public void premakniKroglo() {
		//kroglo premikamo v smeri vektorja, ki ga določi položaj miške
		//hitrost razdelimo na komponente x in y
		if (krogla != null) {
			krogla.x = krogla.x + (int)(krogla.hitrost_x * delta);
			krogla.y = krogla.y + (int)(krogla.hitrost_y * delta);
			// v y-smeri upoštevamo gravitacijo
			krogla.hitrost_y = (int)((double)krogla.hitrost_y - G);
		}
	}
	

	public void premakniTank() {
		if (igralec == 1) {
			tank1.premakni(dolzina, hrib1);
		}
		else {
			tank2.premakni(dolzina, hrib2);
		}
	}
	
	public void narediEksplozijo(Polozaj polozaj1) {
		eksplozija = new Eksplozija(polozaj1, "Predstavitev/eksplozija.png");
		
	}
	
	public void sePrekriva() {
		//za vsak objekt posebaj(raven teren, hrib, tank) preverimo ali ga krogla zadane
		//ob vsakem zadetku, se sproži eksplozija
		//in če ga krogli nato nastavimo hitrost na 0 - zato, da se tam ustavi
		
		
		if (krogla == null) {
			return;
		}

		else if (krogla.y + krogla.getPolozaj().visina >= teren1.getPolozaj().y) {
			Polozaj polozaj = new Polozaj (krogla.x + krogla.getPolozaj().dolzina - 25, krogla.y + krogla.getPolozaj().visina - 25, 50, 50);
			narediEksplozijo(polozaj);
			krogla.hitrost_x = 0;
			krogla.hitrost_y = 0;
			
			novaRunda();

		}
			
		else if (krogla.y + krogla.getPolozaj().visina >= teren2.getPolozaj().y && krogla.x >= teren2.getPolozaj().x) {
			Polozaj polozaj = new Polozaj (krogla.x + krogla.getPolozaj().dolzina - 25, krogla.y + krogla.getPolozaj().visina - 25, 50, 50);
			narediEksplozijo(polozaj);
			krogla.hitrost_x = 0;
			krogla.hitrost_y = 0;
			
			novaRunda();
			
		}
		
		
		else if (krogla.isTrkHrib(hrib1, igralec)) {
			Polozaj polozaj = new Polozaj (krogla.x + krogla.getPolozaj().dolzina - 25, krogla.y + krogla.getPolozaj().visina - 25, 50, 50);
			narediEksplozijo(polozaj);
			krogla.hitrost_x = 0;
			krogla.hitrost_y = 0;
			
			novaRunda();
		}
		
		else if (krogla.isTrkHrib(hrib2, igralec)) {
			Polozaj polozaj = new Polozaj (krogla.x + krogla.getPolozaj().dolzina - 25, krogla.y + krogla.getPolozaj().visina - 25, 50, 50);
			narediEksplozijo(polozaj);
			krogla.hitrost_x = 0;
			krogla.hitrost_y = 0;
			
			novaRunda();
		}
		
		else if (igralec == 1) {
		if (krogla.x + krogla.getPolozaj().dolzina >= tank2.getPolozaj().x && krogla.x + krogla.getPolozaj().dolzina <= tank2.getPolozaj().x + tank2.getPolozaj().dolzina
				&& krogla.y + krogla.getPolozaj().visina >= tank2.getPolozaj().y && krogla.y + krogla.getPolozaj().visina <= tank2.getPolozaj().y + tank2.getPolozaj().visina) {
			Polozaj polozaj = new Polozaj (krogla.x + krogla.getPolozaj().dolzina - 25, krogla.y + krogla.getPolozaj().visina - 25, 50, 50);
			narediEksplozijo(polozaj);
			krogla.hitrost_x = 0;
			krogla.hitrost_y = 0;
			
			novaRunda();
			jeUstreljen();

			

			}
		}
		else if (igralec == 2) {
			if (krogla.x <= tank1.getPolozaj().x + tank1.getPolozaj().dolzina && krogla.x >= tank1.getPolozaj().x
					&& krogla.y + krogla.getPolozaj().visina >= tank1.getPolozaj().y && krogla.y + krogla.getPolozaj().visina <= tank1.getPolozaj().y + tank1.getPolozaj().visina) {
				Polozaj polozaj = new Polozaj (krogla.x + krogla.getPolozaj().dolzina - 25, krogla.y + krogla.getPolozaj().visina - 25, 50, 50);
				narediEksplozijo(polozaj);
				krogla.hitrost_x = 0;
				krogla.hitrost_y = 0;
				novaRunda();
				jeUstreljen();
				
			}
			
		}
		
		
	}
	
	public void posodobi() {
		//poskrbimo, da se stvari posodobijo
		
		sePrekriva();
		
		premakniTank();
		
		premakniKroglo();

		
	
	}
	
}

