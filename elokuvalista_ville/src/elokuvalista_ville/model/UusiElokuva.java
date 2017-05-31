package elokuvalista_ville.model;

public class UusiElokuva {
	
	private int id;
	private String nimi;
	private String genre;
	
	
	
	public UusiElokuva() {
		super();
	}



	public UusiElokuva(int id, String nimi, String genre) {
		super();
		this.id = id;
		this.nimi = nimi;
		this.genre = genre;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getNimi() {
		return nimi;
	}



	public void setNimi(String nimi) {
		this.nimi = nimi;
	}



	public String getGenre() {
		return genre;
	}



	public void setGenre(String genre) {
		this.genre = genre;
	}



	@Override
	public String toString() {
		return "UusiElokuva [id=" + id + ", nimi=" + nimi + ", genre=" + genre
				+ "]";
	}
	
	

}
