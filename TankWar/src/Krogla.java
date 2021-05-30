
public class Krogla extends Objekt {
	
	public int x;
	public int y;
	public int hitrost_x;
	public int hitrost_y;
	public int pospesek_x;
	public int pospesek_y;
//	private List<Objekt> objekti;
	
	public Krogla (Polozaj polozaj, int hitrost_x, int hitrost_y,int pospesek_x, int pospesek_y) {
		super(polozaj);
		this.x = (int)polozaj.getX();
		this.y = (int)polozaj.getY();
		this.hitrost_x = hitrost_x;
		this.hitrost_y = hitrost_y;
		this.pospesek_x = pospesek_x;
		this.pospesek_y = pospesek_y;
	}
	

	
	public boolean isTrkHrib(Teren_hrib hrib, int igralec) {
		
		double a = hrib.getPolozaj().getDolzina()/2;
		double b = hrib.getPolozaj().getVisina()/2;
		
		int p = (int)(hrib.getPolozaj().getX() + a);
		int q = (int)(hrib.getPolozaj().getY() + b);
		
		if (igralec == 1) {
			if (((this.x + this.getPolozaj().dolzina - p)*(this.x  + this.getPolozaj().dolzina - p)/(a*a) + Math.pow((this.y + this.getPolozaj().visina - q)/b, 2.0)) <= 1) {
				return true;
			}
			else {
				return false;
			}
		}
		
		else {
			if (((this.x - p)*(this.x - p)/(a*a) + Math.pow((this.y + this.getPolozaj().visina - q)/b, 2.0)) <= 1) {
				return true;
			}
			else {
				return false;
			}
			
		}
		
	}
		
	
}
