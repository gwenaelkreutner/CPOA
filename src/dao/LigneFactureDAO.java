package dao;

import metier.LigneFacture;

public interface LigneFactureDAO extends DAO<LigneFacture>{

	LigneFacture getById(int id, int ligne);

}
