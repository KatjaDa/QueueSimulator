package view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import simu.model.AsiakasTyyppi;

public class Visualisointi extends Canvas implements IVisualisointi{

	private GraphicsContext gc;
	
	int size = 20;
	
	double i = this.getWidth() - (2 * size);
	double j = 10;
	String kuvapolku;
	
	private Image image;
	
	public Visualisointi(int w, int h, String kuvapolku) {
		super(w, h);
		gc = this.getGraphicsContext2D();
		this.kuvapolku = kuvapolku;
		tyhjennaNaytto();
	}

	public void tyhjennaNaytto() {
		i = this.getWidth() - (2 * size);
		this.j = size;
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		image = new Image(Visualisointi.class.getResourceAsStream(this.kuvapolku),
				200, 260, true, true);
		gc.drawImage(image, 0, 0);
	}
	
	public void uusiAsiakas(AsiakasTyyppi asiakasTyyppi) {
		if (AsiakasTyyppi.X.equals(asiakasTyyppi)) {
			// Turkoosi
			gc.setFill(Color.rgb(3, 244, 252));
		} else {
			// Punainen
			gc.setFill(Color.rgb(253, 0, 0));
		}	
		gc.fillOval(i,j,size,size);
		
		i -= size;
		
		if (i <= 0) {
			i = this.getWidth() - (2 * size);
			j+=size;			
		}
		
		if (j >= this.getHeight()) {
			j = size;
		}
	}
	
}
