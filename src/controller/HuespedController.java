package controller;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

import dao.HuespedDAO;
import factory.ConnectionFactory;
import model.Huesped;

public class HuespedController {
	private HuespedDAO huespedDAO;
	 
	 public HuespedController() {
			Connection con = new ConnectionFactory().getConexion();
			this.huespedDAO = new HuespedDAO(con);
		}
	 
		public void guardar(Huesped huesped) {
			this.huespedDAO.guardar(huesped);
		}
		public List<Huesped> listarHuespedes() {
			return this.huespedDAO.listarHuespedes();
		}
		
		public List<Huesped> listarHuespedesId(String id) {
			return this.huespedDAO.buscarId(id);
		}
		
		public void actualizar(String nombre, String apellido, Date fechaN, String nacionalidad, String telefono, Integer idReserva, Integer id) {
			this.huespedDAO.Actualizar(nombre, apellido, fechaN, nacionalidad, telefono, idReserva, id);
		}
		
		public void Eliminar(Integer id) {
			this.huespedDAO.Eliminar(id);
		}
}
