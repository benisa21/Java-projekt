public class tankWar {
	// razred TankWar predstavlja logiko igre TankWar.  Tu so shranjeni začetni položaji elementov igre.
	
	public static final int MAX_ST_ZIVLJENJ = 5;
	
	static final double delta = 0.03; // faktor za reguliranje hitrosti
	public double G = -10.0; // pospešek
	
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
		
		// zacetna polozaja obeh tankov
		Polozaj polozajTank1 = new Polozaj(0, 2*visina/3 - 45 + 100, 70, 45);
		Polozaj polozajTank2 = new Polozaj(dolzina - 70, visina/2 - 45 + 100, 70, 45);
		
		// ustvarimo tanka
		tank1 = new Tank("Predstavitev/tank1.png", polozajTank1, false, false, 1);
		tank2 = new Tank("Predstavitev/tank2.png", polozajTank2, false, false, 2);
		
		// polozaji terenov oz podlage pod tankoma 
		Polozaj teren1 = new Polozaj(0,2*visina/3+100, dolzina/2, visina/3 +1);
		Polozaj teren2 = new Polozaj(dolzina/2, visina/2+100, dolzina/2, visina/2 +1);
		
		Polozaj hrib1 = new Polozaj(dolzina/8,3*visina/7+100, dolzina/2, 3*visina/2);
		Polozaj hrib2 = new Polozaj(dolzina/3,2*visina/7+100, dolzina/2, 3*visina/2);
		
		// ustvarimo teren
		this.teren1 = new Teren_raven(teren1);
		this.teren2 = new Teren_raven(teren2);
		
		this.hrib1 = new Teren_hrib(hrib1, 0, 180);
		this.hrib2 = new Teren_hrib(hrib2, 0, 180);

	}

	public void ponastavi () {
		// funkcija ponastavi nam ponastavi potrebne vrednosti, kot so zivljenja, polozaj tankov in prikaz eksplozija
		tank1.zivljenja = MAX_ST_ZIVLJENJ;
		tank2.zivljenja = MAX_ST_ZIVLJENJ;
		eksplozija = null;
		Polozaj polozajTank1 = new Polozaj(0, 2*visina/3 - 45 + 100, 70, 45);
		Polozaj polozajTank2 = new Polozaj(dolzina - 70, visina/2 - 45 + 100, 70, 45);
		tank1.polozaj = polozajTank1;
		tank2.polozaj = polozajTank2;
	}
	
	public boolean jeKonecIgre () {
		// funkcija jeKonecIgre je zadlozena za pregled ali je kateri od tankov ze zmagal
		if (tank1.zivljenja == 0 || tank2.zivljenja == 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void jeUstreljen () {
		// funkcija jeUstreljen zmanjsa števli življenj ustreznemu igralcu
		if (igralec == 1) {
			tank1.zivljenja -= 1;
		}
		else {
			tank2.zivljenja -= 1;
		}
	}
	
	public void novaRunda() {
		// funkcija novaRunda zamenja trenutnega igralca 
		if (igralec == 1) {
			igralec = 2;
		}
		
		else {
			igralec = 1;
		}
		krogla = null; // kroglo izničimo 
		
	}
	
	public void premakniKroglo() {
		// funkcija premakniKroglo skrbi za premik krogle na ustrezno lokacijo, glede na smer in hitrost strela
		if (krogla != null) {
			krogla.x = krogla.x + (int)(krogla.hitrost_x * delta);
			krogla.y = krogla.y + (int)(krogla.hitrost_y * delta);
			
			krogla.hitrost_y = (int)((double)krogla.hitrost_y - G);
		}
	}
	

	public void premakniTank() {
		// funkcija premakniTank skrbi za ustrezen premik trenutnega tanka
		if (igralec == 1) {
			tank1.premakni(dolzina, hrib1);
		}
		else {
			tank2.premakni(dolzina, hrib2);
		}
	}
	
	public void narediEksplozijo(Polozaj polozaj1) {
		// funkcija narediEksplozijo naredi novo eksplozijo na podanem položaju 
		eksplozija = new Eksplozija(polozaj1, "Predstavitev/eksplozija.png");
//		System.out.println(eksplozija.slika);
		
	}
	
	public void sePrekriva() {
		// funkcija sePrekriva kliče funkcijo novaRunda in narediEksplozijo, če se krogla v nekem trenuku prekriva z drugim objektom
		if (krogla == null) {
			return;
		}
		
		// krogla se prekriva s terenom 1
		else if (krogla.y + krogla.getPolozaj().visina >= teren1.getPolozaj().y) {
			Polozaj polozaj = new Polozaj (krogla.x + krogla.getPolozaj().dolzina - 25, krogla.y + krogla.getPolozaj().visina - 25, 50, 50);
			narediEksplozijo(polozaj);
			krogla.hitrost_x = 0;
			krogla.hitrost_y = 0;
			
			novaRunda();

		}
		
		// krogla se prekriva s terenom 2
		else if (krogla.y + krogla.getPolozaj().visina >= teren2.getPolozaj().y && krogla.x >= teren2.getPolozaj().x) {
			Polozaj polozaj = new Polozaj (krogla.x + krogla.getPolozaj().dolzina - 25, krogla.y + krogla.getPolozaj().visina - 25, 50, 50);
			narediEksplozijo(polozaj);
			krogla.hitrost_x = 0;
			krogla.hitrost_y = 0;
			
			novaRunda();
			
		}
		
		// krogla se prekriva s hribom 1
		else if (krogla.isTrkHrib(hrib1, igralec)) {
			Polozaj polozaj = new Polozaj (krogla.x + krogla.getPolozaj().dolzina - 25, krogla.y + krogla.getPolozaj().visina - 25, 50, 50);
			narediEksplozijo(polozaj);
			krogla.hitrost_x = 0;
			krogla.hitrost_y = 0;
			
			novaRunda();
		}
		
		// krogla se prekriva s hribom 2
		else if (krogla.isTrkHrib(hrib2, igralec)) {
			Polozaj polozaj = new Polozaj (krogla.x + krogla.getPolozaj().dolzina - 25, krogla.y + krogla.getPolozaj().visina - 25, 50, 50);
			narediEksplozijo(polozaj);
			krogla.hitrost_x = 0;
			krogla.hitrost_y = 0;
			
			novaRunda();
		}
		
		// krogla se prekriva s nasprotnim tankov
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
				jeUstreljen(); // zmanjsamo stevilo življenj
				
			}
			
		}
		
		
	}
	
	public void posodobi() {
		// funkcija posodobi kliče funkcije sePrekriva, premakniTank in premakniKroglo in s tem posodobi celotno igralno polje
		sePrekriva();
		
		premakniTank();
		
		premakniKroglo();

		
	
	}
	
}

