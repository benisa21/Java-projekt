public class Polozaj {
	// Razred Polozaj sprejme štiri argumente (x in y koordinato ter dolzino in visino) 
	public float x;
	public float y;
	public int dolzina;
	public int visina;
	
	public Polozaj(float x, float y, int dolzina, int visina) {
		super();
		this.x = x;
		this.y = y;
		this.dolzina = dolzina;
		this.visina = visina;
	}
	
	public Polozaj(Polozaj polozaj) {
		this.x = polozaj.x;
		this.y = polozaj.y;
		this.dolzina = polozaj.dolzina;
		this.visina = polozaj.visina;
		
	}
	public float getX() {
		return x;
	}
	public float getY() {
		return y;
	}
	

	public void setX(float x) {
		this.x = x;
	}

	public void setY(float y) {
		this.y = y;
	}

	public void setDolzina(int dolzina) {
		this.dolzina = dolzina;
	}

	public void setVisina(int visina) {
		this.visina = visina;
	}

	public int getDolzina() {
		return dolzina;
	}

	public int getVisina() {
		return visina;
	}
	
	// funkcija jeZnotraj pove ali je podana točka v trenutnem objektu oz se prekriva
	public boolean jeZnotraj(int x, int y){
		return (x <= this.getX() + this.dolzina/2  && x >= this.getX() - this.dolzina/2		&&
				y <= this.getY() + this.visina/2 && y >= this.getY() - this.visina/2);
	}
	
	// funkcija jeZunaj pove ali je neka podana točka zunaj našega objekta
	public boolean jeZunaj(int x, int y){
		return (!jeZnotraj(x, y));
	}
}
