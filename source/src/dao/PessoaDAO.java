package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import model.Pessoa;

public class PessoaDAO {
	EntityManagerFactory emf;
	
	@PersistenceContext
	EntityManager em;

	public PessoaDAO() {
		emf = Persistence.createEntityManagerFactory("conexao");
		em = emf.createEntityManager();
		
	}
	public boolean login(String email, String senha) {
		 String senhaBanco=this.em.createQuery("select senha from Pessoa pessoa where email='"+email+"'", String.class).getSingleResult();
		 if(senha.equals(senhaBanco)) {
			 return true;
		 }
		 return false;
	}
	
	public void atualizar(Pessoa pessoa) {
		em.getTransaction().begin();
		em.merge(pessoa);
		em.getTransaction().commit();
	}
	
	
	public long salvar(Pessoa pessoa) {
		
		em.getTransaction().begin();
		em.persist(pessoa);
		em.flush();
		long idPessoa=pessoa.getId();
		em.getTransaction().commit();
		
		return idPessoa;
	}
	
	public void excluir(Pessoa pessoa) {
		em.getTransaction().begin();
		em.remove(pessoa);
		em.getTransaction().commit();
	}
	@SuppressWarnings("unchecked")
	public List<Pessoa> listarTodos() {
		return this.em.createQuery("select pessoa from Pessoa pessoa", Pessoa.class).getResultList();

	}
	@SuppressWarnings("unchecked")
	public List<Pessoa> listarPorNome(String nome) {
		return this.em.createQuery("select pessoa from Pessoa pessoa where nome like '"+nome+"'", Pessoa.class).getResultList();

	}

}