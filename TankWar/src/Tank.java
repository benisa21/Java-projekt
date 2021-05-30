import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Tank extends Objekt {
	
	private boolean premik_v_levo;
	private boolean premik_v_desno;
	private int igralec;
	public int zivljenja;
	
	public static final int MAX_ST_ZIVLJENJ = 5;
	public Image slika;


	public Tank(String file_location, Polozaj polozaj, boolean premik_v_levo, boolean premik_v_desno, int igralec) {
		super(polozaj);
		//objektu tipa tank določimo položaj, možne premike in trenutnega igralca
		try {
			this.slika = ImageIO.read(new File(file_location));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}
		
		this.premik_v_levo = premik_v_levo;
		this.premik_v_desno = premik_v_desno;
		this.igralec = igralec;
		this.zivljenja = MAX_ST_ZIVLJENJ;
		
	}


	public int getIgralec() {
		return igralec;
	}



	public void setIgralec(int igralec) {
		this.igralec = igralec;
	}



	public boolean isPremik_v_levo() {
		return premik_v_levo;
	}


	public void setPremik_v_levo(boolean premik_v_levo) {
		this.premik_v_levo = premik_v_levo;
	}


	public boolean isPremik_v_desno() {
		return premik_v_desno;
	}


	public void setPremik_v_desno(boolean premik_v_desno) {
		this.premik_v_desno = premik_v_desno;
	}

	
	public Polozaj getPresecisce(Teren_hrib hrib) {
		//polozaj hriba je opisan z elipso
		int y = (int)(this.getPolozaj().getY() + this.getPolozaj().getVisina()/5);
		double a = hrib.getPolozaj().getDolzina()/2;
		double b = hrib.getPolozaj().getVisina()/2;
		
		int p = (int)(hrib.getPolozaj().getX() + a);
		int q = (int)(hrib.getPolozaj().getY() + b);
		// s pomočjo enačbe premaknjene elipse dobimo robne točke hriba
		// na tak način lahko preverjamo ali je krogla trčila ob rob hriba
		int x1 = (int)(Math.sqrt((1- ((y-q)*(y-q))/(b*b)) * (a*a))+p);
		int x2 = (int)(-(Math.sqrt((1- ((y-q)*(y-q))/(b*b)) * (a*a)))+p);
		Polozaj polozaj1 = new Polozaj(x1, y, 0, 0);
		Polozaj polozaj2 = new Polozaj(x2, y, 0, 0);
		
		if (this.getIgralec() == 1) {
			return polozaj2;
		}
		else {
			return polozaj1;
			}
		
	}
	
	public void premakni(int dolzina, Teren_hrib hrib) {
		// Tank se lahko premika od roba okna do roba hriba
		if (this.getPolozaj().getX() <= dolzina-this.getPolozaj().getDolzina() && this.getPolozaj().getX() >= 0) {
			if (this.getIgralec() == 1) {
				if (this.getPolozaj().getX() + this.getPolozaj().getDolzina() < getPresecisce(hrib).getX()) {

					if (this.premik_v_desno == true) {
						this.getPolozaj().setX(this.getPolozaj().getX()+1);
					}
						
					if (this.premik_v_levo == true) {
						this.getPolozaj().setX(this.getPolozaj().getX()-1);
					}
				}
				
				else {
					this.getPolozaj().setX(this.getPolozaj().getX()-1);
				}
			}
			else {
				if (this.getPolozaj().getX() > getPresecisce(hrib).getX()) {
					
					if (this.premik_v_desno == true) {
						this.getPolozaj().setX(this.getPolozaj().getX()+1);
					}
						
					if (this.premik_v_levo == true) {
						this.getPolozaj().setX(this.getPolozaj().getX()-1);
					}
				}
				else {
					this.getPolozaj().setX(this.getPolozaj().getX()+1);
				}
				
			}
				
		}
		else {
			if (this.getIgralec() == 1) {
				this.getPolozaj().setX(this.getPolozaj().getX()+1);
			}
			else {
				this.getPolozaj().setX(this.getPolozaj().getX()-1);
			}
		}

}
}

