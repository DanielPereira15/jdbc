package model;

import java.util.List;
import java.util.Map;

public interface IRepositorioCliente extends IRepositorio<Cliente, Long> {
	Map<String, Cliente> getMapAll();

	Cliente getByDNI(String dni);

	List<Cliente> findByProvincia(String provincia);

}