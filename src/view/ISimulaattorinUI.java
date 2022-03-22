package view;

public interface ISimulaattorinUI {
	
	// Kontrolleri tarvitsee syötteitä, jotka se välittää Moottorille
	public double getAika();
	public long getViive();
	public double getXParam();
	public double getYParam();
	
	// Kontrolleri tarvitsee syötteiksi myös X-asiakkaiden saapumisten parametrit ja Y-asiakkaiden saapumisten parametrit
	// Näille pitäisi tehdä metodit
	
	// Kontrolleri antaa käyttöliittymälle tuloksia, joita Moottori tuottaa 
	public void setLoppuaika(double aika);
	
	public void setAsiakasMaaraX(int asiakasMaaraX);
	public void setAsiakasMaaraY(int asiakasMaaraY);
	public void setAsiakasMaaraYht(int asiakasMaara);
	
	// Lisätty setPalvellutX ja setPalvellutY
	public void setPalvellutX(int asiakasMaaraX);
	public void setPalvellutY(int asiakasMaaraY);
	public void setPalvellut(int asiakasMaara);
	
	// Kontrolleri tarvitsee  
	public IVisualisointi getPP1Visualisointi();
	public IVisualisointi getPP2Visualisointi();
	public IVisualisointi getPP3Visualisointi();
	public IVisualisointi getPP4Visualisointi();
	
	// Päivittää progressBarin aikaa
	public void setProgressBarAika();
	
}
