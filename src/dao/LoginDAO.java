package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Usuario;

public class LoginDAO {
    private Connection connection;
	
	public LoginDAO (Connection connection) {
		this.connection = connection;
	}
	
	public boolean validar(Usuario usuario) {
		
		String query = "SELECT usuario, clave FROM usuarios where usuario = ? and clave = ?";
		
		try (PreparedStatement pstm = connection.prepareStatement(query)){
			pstm.setString(1, usuario.getUsuario().toLowerCase());
			pstm.setString(2, usuario.getClave().toLowerCase());
			
			pstm.execute();
			
			try (ResultSet resultSet = pstm.getResultSet()) {
				while(resultSet.next()) { 
					return true;
				}
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		return false;
	}
}
