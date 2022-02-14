package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RepositorioJDBC implements IRepositorioCliente {

	Connection conn = null;
	Cliente cliente;
	List<Cliente> listClientes;
	Map<String, Cliente> mapClientes;
	List<Cliente> listClientesNombres;
	List<Cliente> listClientesProvincia;

	public RepositorioJDBC() throws SQLException {
		// mejor envolver la excepción SQLException
		conn = ConexionBD.getConexion();
	}

	@Override
	public long count() {
		Statement sentencia;
		long numDatos = 0;
		try {
			sentencia = conn.createStatement();
			numDatos = 0;
			String query = "SELECT count(*) FROM Cliente";
			ResultSet rs = sentencia.executeQuery(query);
			if (rs.next()) {
				numDatos = rs.getInt(1);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return numDatos;

	}

	@Override
	public void deleteById(Long id) {
		String query = "DELETE FROM Cliente where idCliente='" + id + "';";
		PreparedStatement sentencia;
		try {

			sentencia = conn.prepareStatement(query);
			sentencia.executeUpdate();
			System.out.println("Cliente borrado con exito");
		} catch (SQLException e) {
			e.getMessage();
		}
	}

	@Override
	public void deleteAll() {
		String query = "DELETE FROM Cliente;";
		PreparedStatement sentencia;
		try {

			sentencia = conn.prepareStatement(query);
			sentencia.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean existsById(Long id) {
		String query = "Select * FROM Cliente where idCliente='" + id + "';";
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

	@Override
	public Cliente getById(Long id) {
		Cliente clEncontrado = null;
		String query = "Select * FROM Cliente where idCliente='" + id + "';";
		PreparedStatement sentencia;
		try {
			sentencia = conn.prepareStatement(query);
			ResultSet rs = sentencia.executeQuery(query);
			if (clEncontrado == null) {
				System.out.println("No existe cliente con id: " + id);
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

	@Override
	public List<Cliente> findAll() {
		Cliente clEncontrado = null;
		listClientes = new ArrayList<Cliente>();
		String query = "Select * FROM Cliente;";
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

	@Override
	public <S extends Cliente> S save(S entity) {
		String dni = entity.getDni();
		String nombre = entity.getNombre();
		String apellido = entity.getApellido();
		String calle = entity.getDireccion().getCalle();
		String municipio = entity.getDireccion().getMunicipio();
		String provincia = entity.getDireccion().getProvincia();
		String query = "Insert into Cliente values(?,?,?,?,?,?);";
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

	@Override
	public Map<String, Cliente> getMapAll() {
		Cliente clEncontrado = null;
		mapClientes = new HashMap<String, Cliente>();
		String query = "Select * FROM Cliente;";
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

	@Override
	public Cliente getByDNI(String dni) {
		Cliente clEncontrado2 = null;
		String query = "Select * FROM Cliente where dni='" + dni + "';";
		PreparedStatement sentencia;
		try {
			sentencia = conn.prepareStatement(query);
			ResultSet rs = sentencia.executeQuery(query);
			if (clEncontrado2 == null) {
				System.out.println("No existe cliente con dni: " + dni);
			}
			while (rs.next()) {
				clEncontrado2 = new Cliente(rs.getString("dni"), rs.getString("nombre"), rs.getString("apellido"),
						new Direccion(rs.getString("calle"), rs.getString("municipio"), rs.getString("provincia")));
			}
		} catch (SQLException e) {
			e.getMessage();
		}
		return clEncontrado2;
	}

	@Override
	public List<Cliente> findByProvincia(String provincia) {
		Cliente clEncontrado = null;
		listClientesProvincia = new ArrayList<Cliente>();
		String query = "Select * FROM Cliente where provincia= '" + provincia + "';";
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

}
