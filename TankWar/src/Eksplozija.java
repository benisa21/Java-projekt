
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Eksplozija extends Objekt {
	// Razred opisuje eksplozijo, ki shrani polozaj (x in y koordinata ter velikost) in pa lokacijo datoteke slike
	public Image slika;
	public Eksplozija(Polozaj polozaj, String lokacija) {
		super(polozaj);
		// TODO Auto-generated constructor stub
		try {
			this.slika = ImageIO.read(new File(lokacija));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}
	}
	
}
