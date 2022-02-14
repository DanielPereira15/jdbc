package model;
import lombok.Getter;
import lombok.Setter;

public class Cliente {
	@Getter
	@Setter
	protected String dni;
	@Getter
	@Setter
	protected String nombre;
	@Getter
	@Setter
	protected String apellido;
	@Getter
	@Setter
	protected Direccion direccion;

	/**
	 * @param dni
	 * @param nombre
	 * @param apellido
	 * @param direccion
	 */
	public Cliente(String dni, String nombre, String apellido, Direccion direccion) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
		this.direccion = direccion;
	}
	public Cliente(String dni, String nombre, Direccion direccion) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = "";
		this.direccion = direccion;
	}

	/**
	 * Sobreescribimos el metodo toString para mostrar los datos de forma ordenada
	 * 
	 * @return cliente
	 */

	@Override
	public String toString() {
		String cliente = "El dni es: " + this.dni + ", el nombre es: " + this.nombre + " y los apellidos: "
				+ this.apellido + " y direccion: " + this.direccion;
		return cliente;
	}

}
