package mysql;

import dao.ClientDAO;
import dao.DAOFactory;
import dao.FactureDAO;
import dao.LigneFactureDAO;
import dao.ProduitDAO;
import dao.TvaDAO;

public class MySQLDAOFactory extends DAOFactory{
	
	@Override
	public ClientDAO getClientDAO() {
		return MySQLClientDAO.getInstance();
	}

	@Override
	public TvaDAO getTvaDAO() {
		return MySQLTvaDAO.getInstance();
		
	}

	@Override
	public FactureDAO getFactureDAO() {
		return MySQLFactureDAO.getInstance();
	}

	@Override
	public ProduitDAO getProduitDAO() {
		return MySQLProduitDAO.getInstance();
		
	}

	@Override
	public LigneFactureDAO getLigneFactureDAO() {
		return MySQLLigneFactureDAO.getInstance();
	}	
}
