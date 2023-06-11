package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;

import model.Huesped;

public class HuespedDAO {
private Connection connection;
	
	public HuespedDAO(Connection connection) {
		this.connection = connection;
	}
	
	public void guardar(Huesped huesped) {
		try {
			String sql = "INSERT INTO huespedes (nombre, apellido, fechaNacimiento, nacionalidad, telefono, idReserva) VALUES (?, ?, ?, ?,?,?)";

			try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

				pstm.setString(1, huesped.getNombre());
				pstm.setString(2, huesped.getApellido());
				pstm.setDate(3, huesped.getFechaNacimiento());
				pstm.setString(4, huesped.getNacionalidad());
				pstm.setString(5, huesped.getTelefono());
				pstm.setInt(6, huesped.getIdReserva());

				pstm.execute();

				try (ResultSet rst = pstm.getGeneratedKeys()) {
					while (rst.next()) {
						huesped.setId(rst.getInt(1));
					}
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public List<Huesped> listarHuespedes() {
		List<Huesped> huespedes = new ArrayList<Huesped>();
		try {
			String sql = "SELECT idHuespedes, nombre, apellido, fechaNacimiento, nacionalidad, telefono, idReserva FROM huespedes";

			try (PreparedStatement pstm = connection.prepareStatement(sql)) {
				pstm.execute();

				try (ResultSet resultSet = pstm.getResultSet()) {
					while (resultSet.next()) {
						Huesped huesped = new Huesped(
								resultSet.getInt("idHuespedes"), 
								resultSet.getString("nombre"),
								resultSet.getString("apellido"), 
								resultSet.getDate("fechaNacimiento"),
								resultSet.getString("nacionalidad"), 
								resultSet.getString("telefono"),
								resultSet.getInt("idReserva"));

						huespedes.add(huesped);
					}
				}
			}
			return huespedes;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Huesped> buscarId(String id) {
		List<Huesped> huespedes = new ArrayList<Huesped>();
		try {

			String sql = "SELECT idHuespedes, nombre, apellido, fechaNacimiento, nacionalidad, telefono, idReserva FROM huespedes WHERE idHuespedes = ?";

			try (PreparedStatement pstm = connection.prepareStatement(sql)) {
				pstm.setString(1, id);
				pstm.execute();

				try (ResultSet resultSet = pstm.getResultSet()) {
					while (resultSet.next()) {
						Huesped huesped = new Huesped(
								resultSet.getInt("idHuespedes"), 
								resultSet.getString("nombre"),
								resultSet.getString("apellido"), 
								resultSet.getDate("fechaNacimiento"),
								resultSet.getString("nacionalidad"), 
								resultSet.getString("telefono"),
								resultSet.getInt("idReserva"));

						huespedes.add(huesped);
					}
				}
			}
			return huespedes;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void Actualizar(String nombre, String apellido, Date fechaN, String nacionalidad, String telefono, Integer idReserva, Integer id) {
		try (PreparedStatement stm = connection
				.prepareStatement("UPDATE huespedes SET nombre = ?, apellido = ?, fechaNacimiento = ?, nacionalidad = ?, telefono = ?, idReserva = ? WHERE idHuespedes = ?")) {
			stm.setString(1, nombre);
			stm.setString(2, apellido);
			stm.setDate(3, fechaN);
			stm.setString(4, nacionalidad);
			stm.setString(5, telefono);
			stm.setInt(6, idReserva);
			stm.setInt(7, id);
			stm.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public void Eliminar(Integer id) {
		try (PreparedStatement stm = connection.prepareStatement("DELETE FROM huespedes WHERE idHuespedes = ?")) {
			stm.setInt(1, id);
			stm.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
