package controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import dao.PessoaDAO;
import manager.PessoaManager;
import manager.TelefoneManager;
import model.Pessoa;
import model.Telefone;


@ManagedBean(name="pessoaMB")
@SessionScoped
public class PessoaMB {
	
	
	private Pessoa selectedPessoa;
	private Pessoa pessoa;
	private PessoaManager pessoaManager;
	private TelefoneManager telefoneManager;
	private Pessoa pessoaConsulta;
	private Telefone telefone;
	private List<Telefone> telefones;
	private PessoaDAO pessoaDAO;
	private List<Pessoa> lista;	
	private String operacao;
	private boolean isEditar;

	@PostConstruct
    public void initialize() {
		
		isEditar=false;
        lista = pessoaDAO.listarTodos();
        this.telefone = new Telefone();
        telefones = new ArrayList<Telefone>();
        telefoneManager=new TelefoneManager();
        pessoaManager=new PessoaManager();
        pessoaConsulta=new Pessoa();
    }
	
	public void pesquisar() {
		lista=pessoaManager.listarPorNome(pessoaConsulta.getNome());
	}
	
	

	public PessoaMB() {
		pessoaDAO = new PessoaDAO();
		pessoa = new Pessoa();
		lista = pessoaDAO.listarTodos();				
	}
	
	
	public void adicionarTelefone() {
		Telefone novoTelefone=new Telefone();
		
		if(pessoa!=null) {
			novoTelefone.setIdPessoa((int)pessoa.getId());
		}
		novoTelefone.setDdd(telefone.getDdd());
		novoTelefone.setNumero(telefone.getNumero());
		novoTelefone.setTipo(telefone.getTipo());
		
		telefones.add(novoTelefone);
		telefone=new Telefone();
	}
	
	public void cancelarOperacao() {
		this.operacao=null;
	}
	
	public boolean isDetalhar() {
		if(operacao!=null) {
			if(operacao.equals("detalhar")) return true;
		}
		return false;
	}
	
	public boolean isEditar() {
		if(operacao!=null)
			if(operacao.equals("editar")) {
				return true;
			}
		return false;
	}
	
	
	public void updateList() {
		lista = pessoaDAO.listarTodos();
	}
	public String getOperacao() {
		return operacao;
	}
	public void setOperacao(String operacao) {
		this.operacao = operacao;
	}
	
	public Pessoa getSelectedPessoa() {
		return selectedPessoa;
	}

	public void setSelectedPessoa(Pessoa selectedPessoa) {
		this.selectedPessoa = selectedPessoa;
	}
	
	public Pessoa getPessoa(){
		return this.pessoa;
	}
	
	public void setPessoa(Pessoa pessoa){
		this.pessoa = pessoa;
	}
	
	public List<Pessoa> getList() {
		return this.lista;
	}
	public Telefone getTelefone() {
		return telefone;
	}

	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}
	
	public List<Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}
	
	public Pessoa getPessoaConsulta() {
		return pessoaConsulta;
	}

	public void setPessoaConsulta(Pessoa pessoaConsulta) {
		this.pessoaConsulta = pessoaConsulta;
	}
	
	public String editar() {
		telefones=telefoneManager.listarPorIdPessoa(pessoa.getId());
		return "cadastrarUsuario.xhtml";
	}
	public String detalhar() {
		telefones=telefoneManager.listarPorIdPessoa(pessoa.getId());
		return "cadastrarUsuario.xhtml";
	}
	
	public void salvar() {
		
		if(operacao!=null) {
			if(operacao.equals("editar")) {
				pessoaManager.atualizar(pessoa, telefones);
				
				pessoa = new Pessoa();
				telefone=new Telefone();
				telefones=new ArrayList<Telefone>();
				lista = pessoaDAO.listarTodos();
			}
		}else{
			pessoaManager.salvar(pessoa, telefones);
			
			pessoa = new Pessoa();
			telefone=new Telefone();
			telefones=new ArrayList<Telefone>();
			lista = pessoaDAO.listarTodos();

			addMessage("Pessoa salva com sucesso!!");
		}
	}
	public String voltar() {
		return "consultarUsuario.xhtml";
	}
	
	public void remover() {
		pessoaDAO.excluir(selectedPessoa);
		
		lista = pessoaDAO.listarTodos();
	}
	
	public void addMessage(String summary) {
	        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);
	        FacesContext.getCurrentInstance().addMessage(null, message);
	}
}	
    
