package manager;

import java.util.List;

import dao.PessoaDAO;
import dao.TelefoneDAO;
import model.Pessoa;
import model.Telefone;

public class PessoaManager {
	private PessoaDAO pessoaDAO;
	private TelefoneDAO telefoneDAO;
	
	public PessoaManager(){
		pessoaDAO=new PessoaDAO();
		telefoneDAO=new TelefoneDAO();
	}
	
	public boolean login(String email, String senha) {
		return pessoaDAO.login(email, senha);
	}
	
	public void atualizar(Pessoa pessoa, List<Telefone> telefones) {
		pessoaDAO.atualizar(pessoa);
		for(Telefone telefone:telefones) {
			telefone.setIdPessoa((int)pessoa.getId());
			telefoneDAO.salvar(telefone);
		}
	}
	
	public void salvar(Pessoa pessoa, List<Telefone> telefones) {
		long idPessoa=pessoaDAO.salvar(pessoa);
		
		for(Telefone telefone:telefones) {
			telefone.setIdPessoa((int)idPessoa);
			telefoneDAO.salvar(telefone);
		}
	}
	public List<Pessoa> listarPorNome(String nome){
		return pessoaDAO.listarPorNome(nome);
	}
}
