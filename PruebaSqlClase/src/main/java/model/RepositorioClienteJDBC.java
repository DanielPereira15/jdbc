package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class RepositorioClienteJDBC {

	Connection conn = null;
	Cliente cliente;
	List<Cliente> listClientes;
	Map<String, Cliente> mapClientes;
	List<Cliente> listClientesNombres;
	List<Cliente> listClientesProvincia;
	Map<String, Cliente> mapClientesProvincia;

	public RepositorioClienteJDBC() throws SQLException {
		// mejor envolver la excepción SQLException
		conn = ConexionBD.getConexion();
	}

//	public long count() throws Exception {
//		Statement sentencia = conn.createStatement();
//		long numDatos = 0;
//		String query = "SELECT count(*) FROM Clientes";
//		ResultSet rs = sentencia.executeQuery(query);
//		if (rs.next()) {
//			numDatos = rs.getInt(1);
//		}
//
//		return numDatos;
//	}

	public void deleteById(String id) {
		// TODO Auto-generated method stub
		String query = "DELETE FROM Clientes where dni='" + id + "';";
		PreparedStatement sentencia;
		try {

			sentencia = conn.prepareStatement(query);
			sentencia.executeUpdate();
			System.out.println("Cliente borrado con exito");
		} catch (SQLException e) {
			e.getMessage();
		}
	}

	public void deleteAll() {
		// TODO Auto-generated method stub
		String query = "DELETE FROM Clientes;";
		PreparedStatement sentencia;
		try {

			sentencia = conn.prepareStatement(query);
			sentencia.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Esta intentando introducir un Dni ya existente.");
			e.printStackTrace();
		}
	}

	public boolean existsById(String id) {
		String query = "Select * FROM Clientes where dni='" + id + "';";
		boolean existe = false;
		PreparedStatement sentencia;
		try {
			sentencia = conn.prepareStatement(query);
			System.out.println("Cliente encontrado con exito");
			existe = true;
		} catch (SQLException e) {
			e.getMessage();
		}
		return existe;
	}

	public Cliente getById(String id) {
		Cliente clEncontrado = null;
		String query = "Select * FROM Clientes where dni='" + id + "';";
		PreparedStatement sentencia;
		try {
			sentencia = conn.prepareStatement(query);
			ResultSet rs = sentencia.executeQuery(query);
			if (clEncontrado == null) {
				System.out.println("No existe cliente con dni: " + id);
			}
			while (rs.next()) {
				clEncontrado = new Cliente(rs.getString("dni"), rs.getString("nombre"), rs.getString("apellido"),
						new Direccion(rs.getString("calle"), rs.getString("municipio"), rs.getString("provincia")));
			}
		} catch (SQLException e) {
			e.getMessage();
		}
		return clEncontrado;
	}

	public List<Cliente> findAll() {
		Cliente clEncontrado = null;
		listClientes = new ArrayList<Cliente>();
		String query = "Select * FROM Clientes;";
		PreparedStatement sentencia;
		try {
			sentencia = conn.prepareStatement(query);
			ResultSet rs = sentencia.executeQuery(query);

			while (rs.next()) {
				clEncontrado = new Cliente(rs.getString("dni"), rs.getString("nombre"), rs.getString("apellido"),
						new Direccion(rs.getString("calle"), rs.getString("municipio"), rs.getString("provincia")));
				listClientes.add(clEncontrado);
			}
		} catch (SQLException e) {
			e.getMessage();
		}
		return listClientes;
	}

	public <S extends Cliente> S save(S entity) {
		// TODO Auto-generated method stub
		String dni = entity.getDni();
		String nombre = entity.getNombre();
		String apellido = entity.getApellido();
		String calle = entity.getDireccion().getCalle();
		String municipio = entity.getDireccion().getMunicipio();
		String provincia = entity.getDireccion().getProvincia();
		String query = "Insert into Clientes values(?,?,?,?,?,?);";
		PreparedStatement sentencia;
		try {

			sentencia = conn.prepareStatement(query);
			sentencia.setString(1, dni);
			sentencia.setString(2, nombre);
			sentencia.setString(3, apellido);
			sentencia.setString(4, calle);
			sentencia.setString(5, municipio);
			sentencia.setString(6, provincia);

			sentencia.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Esta intentando introducir un Dni ya existente.");
			e.printStackTrace();
		}

		return null;
	}

	public Map<String, Cliente> getMapAll() {
		Cliente clEncontrado = null;
		mapClientes = new HashMap<String, Cliente>();
		String query = "Select * FROM Clientes;";
		PreparedStatement sentencia;
		try {
			sentencia = conn.prepareStatement(query);
			ResultSet rs = sentencia.executeQuery(query);

			while (rs.next()) {
				clEncontrado = new Cliente(rs.getString("dni"), rs.getString("nombre"), rs.getString("apellido"),
						new Direccion(rs.getString("calle"), rs.getString("municipio"), rs.getString("provincia")));
				mapClientes.put(clEncontrado.getDni(), clEncontrado);
			}
		} catch (SQLException e) {
			e.getMessage();
		}

		return mapClientes;
	}

	public List<Cliente> findByNombre(String nombre) {
		Cliente clEncontrado = null;
		listClientesNombres = new ArrayList<Cliente>();
		String query = "Select * FROM Clientes where nombre= '" + nombre + "';";
		PreparedStatement sentencia;
		try {
			sentencia = conn.prepareStatement(query);
			ResultSet rs = sentencia.executeQuery(query);

			while (rs.next()) {
				clEncontrado = new Cliente(rs.getString("dni"), rs.getString("nombre"), rs.getString("apellido"),
						new Direccion(rs.getString("calle"), rs.getString("municipio"), rs.getString("provincia")));
				listClientesNombres.add(clEncontrado);
			}
		} catch (SQLException e) {
			e.getMessage();
		}
		return listClientesNombres;
	}

	public List<Cliente> findByProvincia(String provincia) {
		Cliente clEncontrado = null;
		listClientesProvincia = new ArrayList<Cliente>();
		String query = "Select * FROM Clientes where provincia= '" + provincia + "';";
		PreparedStatement sentencia;
		try {
			sentencia = conn.prepareStatement(query);
			ResultSet rs = sentencia.executeQuery(query);

			while (rs.next()) {
				clEncontrado = new Cliente(rs.getString("dni"), rs.getString("nombre"), rs.getString("apellido"),
						new Direccion(rs.getString("calle"), rs.getString("municipio"), rs.getString("provincia")));
				listClientesProvincia.add(clEncontrado);
			}
			if (listClientesProvincia.size() < 1) {
				System.out.println("No se ha encontrado ningun Cliente con la provincia: " + provincia);
			}
		} catch (SQLException e) {
			e.getMessage();
		}
		return listClientesProvincia;
	}

	public Map<String, Cliente> findByProvinciaUsingMap(String provincia) {
		Cliente clEncontrado = null;
		mapClientesProvincia = new HashMap<String, Cliente>();
		String query = "Select * FROM Clientes where provincia= '" + provincia + "';";
		PreparedStatement sentencia;
		try {
			sentencia = conn.prepareStatement(query);
			ResultSet rs = sentencia.executeQuery(query);

			while (rs.next()) {
				clEncontrado = new Cliente(rs.getString("dni"), rs.getString("nombre"), rs.getString("apellido"),
						new Direccion(rs.getString("calle"), rs.getString("municipio"), rs.getString("provincia")));
				mapClientesProvincia.put(clEncontrado.getDni(), clEncontrado);
			}
			if (mapClientesProvincia.size() < 1) {
				System.out.println("No se ha encontrado ningun Cliente con la provincia: " + provincia);
			}
		} catch (SQLException e) {
			e.getMessage();
		}

		return mapClientesProvincia;
	}
}