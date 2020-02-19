package test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Telefone;

public class TesteTelefone {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("conexao");
		
		EntityManager em = emf.createEntityManager();
		
		Telefone tel=new Telefone();
		tel.setDdd(81);
		tel.setIdPessoa(1);
		
		em.getTransaction().begin();
		em.persist(tel);
		em.getTransaction().commit();
	}
}
