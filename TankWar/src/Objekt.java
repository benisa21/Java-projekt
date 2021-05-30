public class Objekt {
	// Razred Objekt opisuje vse elemente na igralnem polju (Tank, Eksplozijo, Kroglo, Teren). Shrani jim polozaj (x in y koordinata ter velikost)
	protected Polozaj polozaj;
	
	
	public Objekt(Polozaj polozaj) {
		super();
		this.polozaj = polozaj;
	}


	public Polozaj getPolozaj() {
		return polozaj;
	}


	public void setPolozaj(Polozaj polozaj) {
		this.polozaj = polozaj;
	}


}
