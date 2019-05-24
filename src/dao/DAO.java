package dao;

import java.sql.Connection;
import java.util.ArrayList;

public interface DAO <T> {
	
	Connection connect = null;
	
	public abstract T getById (int id);
	public abstract void create(T objet);
	public abstract void update(T objet);
	public abstract void delete(T objet);
	public abstract ArrayList<T> Afficher();
	
}
