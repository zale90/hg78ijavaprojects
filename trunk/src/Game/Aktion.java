package Game;

public class Aktion {
	private Information[] veraenderungen;
	private String name, konsolenausgabe;
	
	public Aktion(Information[] veraenderungen, String name, String ausgabe)
	{
		this.setVeraenderungen(veraenderungen);
		this.setName(name);
		setKonsolenausgabe(ausgabe);
	}

	public void setVeraenderungen(Information[] veraenderungen) {
		this.veraenderungen = veraenderungen;
	}

	public Information[] getVeraenderungen() {
		return veraenderungen;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setKonsolenausgabe(String konsolenausgabe) {
		this.konsolenausgabe = konsolenausgabe;
	}

	public String getKonsolenausgabe() {
		return konsolenausgabe;
	}
	
}
