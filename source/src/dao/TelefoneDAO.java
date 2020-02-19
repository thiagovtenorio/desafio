package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import model.Pessoa;
import model.Telefone;

public class TelefoneDAO {
EntityManagerFactory emf;
	
	@PersistenceContext
	EntityManager em;

	public TelefoneDAO() {
		emf = Persistence.createEntityManagerFactory("conexao");
		em = emf.createEntityManager();
	}
	
	public void salvar(Telefone telefone) {
		em.getTransaction().begin();
		em.merge(telefone);
		em.getTransaction().commit();
	}
	public List<Telefone> listarPorIdPessoa(long idPessoa){
		em.getTransaction().begin();
		 List<Telefone> list=this.em.createQuery("select telefone from telefone_pessoa telefone where idPessoa="+idPessoa+"", Telefone.class)
		.getResultList();
		em.getTransaction().commit();
		return list;

	}
}
