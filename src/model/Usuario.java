package model;

public class Usuario {
	private int id;
	private String usuario;
	private String clave;
	
	public Usuario (String usuario, String clave) {
		this.usuario = usuario;
		this.clave = clave;
	}
	
	public String getClave() {
		return clave;
	}
	
	public void setClave(String clave) {
		this.clave = clave;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getUsuario() {
		return usuario;
	}
}
