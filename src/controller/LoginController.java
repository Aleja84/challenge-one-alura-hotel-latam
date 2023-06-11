package controller;

import java.sql.Connection;

import dao.LoginDAO;
import factory.ConnectionFactory;
import model.Usuario;

public class LoginController {
private LoginDAO loginDAO;
	
	public LoginController() {

		Connection con = new ConnectionFactory().getConexion();
		this.loginDAO = new LoginDAO(con);
		
	}
	
	public boolean validar (Usuario usuario) {
		return loginDAO.validar(usuario);
	}

}
