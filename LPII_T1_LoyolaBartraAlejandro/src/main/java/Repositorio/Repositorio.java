package Repositorio;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import modelo.Producto;

public class Repositorio {

	
	
	public static void main(String[] args) {
		//Repositorio repositorio = new Repositorio();
	}
	
	public Repositorio() {
		super();
	}
	
	//crear producto
	
	public void crearProducto(String nom, double pre, String des, String est, Date fab, Date ven) {
		Producto producto = new Producto();
		producto.setNombret1(nom);
		producto.setPreciot1(pre);
		producto.setDescripciont1(des);
		producto.setEstadot1(est);
		producto.setFechafabrit1(fab);
		producto.setFechavencimt1(ven);
		
		EntityManager manager = getEntityManager();
		
		try {
			manager.getTransaction().begin();
			manager.persist(producto);
			manager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			manager.getTransaction().rollback();
		}
	}
	
	// Editar producto
	
	public void editarProducto(int i, String nom, double pre, String des, String est, Date fab, Date ven) {
		EntityManager manager = getEntityManager();
		
		Producto producto = manager.find(Producto.class, i);
		producto.setNombret1(nom);
		producto.setPreciot1(pre);
		producto.setDescripciont1(des);
		producto.setEstadot1(est);
		producto.setFechafabrit1(fab);
		producto.setFechavencimt1(ven);
		
		try {
			manager.getTransaction().begin();
			manager.merge(producto);
			manager.getTransaction().commit();
		} catch(Exception e) {
			e.printStackTrace();
			manager.getTransaction().rollback();
		}
		
	}
	
	//Eliminar producto
	
	public void eliminarProducto(int i) {
		EntityManager manager = getEntityManager();
		
		Producto producto=manager.find(Producto.class, i);
		
		try {
			manager.getTransaction().begin();
	        manager.remove(producto);
	        manager.getTransaction().commit();
		}catch(Exception e) {
			e.printStackTrace();
			manager.getTransaction().rollback();
		}
	
	}
	
	// Listar productos
	public List<Producto> listarProductos() {
	    EntityManager manager = getEntityManager();
	    
	    TypedQuery<Producto> query = manager.createQuery("SELECT p FROM Producto p", Producto.class);
	    
	    List<Producto> productos = query.getResultList();
	    
	    return productos;
	}

	
	public EntityManager getEntityManager() {
		EntityManagerFactory factory=Persistence.createEntityManagerFactory("persistenceUnit");
		EntityManager manager = factory.createEntityManager();
		
		return manager;
	}
	
	
	
}
