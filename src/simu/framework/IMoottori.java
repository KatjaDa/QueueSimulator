package simu.framework;

public interface IMoottori { // UUSI
		
	// Kontrolleri käyttää tätä rajapintaa
	
	public void setSimulointiaika(double aika);
	public void setViive(long aika);
	public long getViive();
	public void setXParam(double xParam);
	public void setYParam(double yParam);
}
