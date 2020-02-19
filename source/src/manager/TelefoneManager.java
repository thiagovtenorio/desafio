package manager;

import java.util.List;

import dao.TelefoneDAO;
import model.Telefone;

public class TelefoneManager {
	private TelefoneDAO telefoneDAO;
	
	public TelefoneManager(){
		telefoneDAO=new TelefoneDAO();
	}
	public List<Telefone> listarPorIdPessoa(long idPessoa){
		return telefoneDAO.listarPorIdPessoa(idPessoa);
	}
}
