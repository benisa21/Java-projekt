
public class Teren_hrib extends Objekt {
	// Razred Teren_hrib je podrazred razreda Objekt, ki pa kot argumente dodatno sprejme zacetni in koncni kot, ki opisujeta zacetek in 
	// konec kroznega izseka 
	private int zacetni_kot;
	private int koncni_kot;

	public Teren_hrib(Polozaj polozaj, int zacetni_kot, int koncni_kot) {
		super(polozaj);
		// TODO Auto-generated constructor stub
		this.koncni_kot = koncni_kot;
		this.zacetni_kot = zacetni_kot;
	}

	public int getZacetni_kot() {
		return zacetni_kot;
	}

	public int getKoncni_kot() {
		return koncni_kot;
	}

}
