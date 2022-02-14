package model;

import java.sql.SQLException;

public class MainPruebas {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Cliente c1 = new Cliente("47552195H", "Daniel", "Pereira",
				new Direccion("Calle joaquin Blume", "Mostoles", "Madrid"));
		Cliente c2 = new Cliente("54835882C", "Alessandro", "Mariscal",
				new Direccion("Calle ray es bobo", "Madrid", "Madrid"));

		RepositorioJDBC repo;
		try {
			repo = new RepositorioJDBC();
			try {
				 System.out.println(repo.count());
				 repo.deleteAll();
//				System.out.println(repo.save(c2));
//				System.out.println(repo.save(c1));

				// repo.deleteById("54835883C");
				// repo.deleteAll();
//				System.out.println(repo.count());
//				System.out.println(repo.existsById("54835882C"));
				// System.out.println(repo.getById("54835882C"));
//				System.out.println(repo.findAll());
//				System.out.println(repo.getMapAll());
//				System.out.println(repo.findByNombre("Alessandro"));
//				System.out.println(repo.findByProvincia("Madrid"));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
