package listememoire;

import dao.ClientDAO;
import dao.DAOFactory;
import dao.FactureDAO;
import dao.LigneFactureDAO;
import dao.ProduitDAO;
import dao.TvaDAO;

public class ListeMemoireDAOFactory extends DAOFactory {

	@Override
	public ClientDAO getClientDAO() {
		return ListeMemoireClientDAO.getInstance();
	}

	@Override
	public TvaDAO getTvaDAO() {
		return ListeMemoireTvaDAO.getInstance();
	}

	@Override
	public FactureDAO getFactureDAO() {
		return ListeMemoireFactureDAO.getInstance();
	}

	@Override
	public ProduitDAO getProduitDAO() {
		// TODO Auto-generated method stub
		return ListeMemoireProduitDAO.getInstance();
	}

	@Override
	public LigneFactureDAO getLigneFactureDAO() {
		// TODO Auto-generated method stub
		return ListeMemoireLigneFactureDAO.getInstance();
	}

}
