package model;

public class Direccion {

	public String calle;

	public String municipio;

	public String provincia;

	/**
	 * Constructor con todos los campos obligatorios
	 * 
	 * @param calle
	 * @param municipio
	 * @param provincia
	 */
	public Direccion(String calle, String municipio, String provincia) {
		super();
		this.calle = calle;
		this.municipio = municipio;
		this.provincia = provincia;
	}

	/**
	 * @return the calle
	 */
	public String getCalle() {
		return calle;
	}

	/**
	 * @param calle the calle to set
	 */
	public void setCalle(String calle) {
		this.calle = calle;
	}

	/**
	 * @return the municipio
	 */
	public String getMunicipio() {
		return municipio;
	}

	/**
	 * @param municipio the municipio to set
	 */
	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	/**
	 * @return the provincia
	 */
	public String getProvincia() {
		return provincia;
	}

	/**
	 * @param provincia the provincia to set
	 */
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	/**
	 * sobreescribimos el toString para mostrar los datos ordenados
	 * 
	 * @return los datos de direccion
	 */
	@Override
	public String toString() {
		return "Calle: " + this.calle + ", municipio: " + this.municipio + " y provincia: " + this.provincia;
	}

}
