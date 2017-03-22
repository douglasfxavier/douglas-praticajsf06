package br.edu.ifpb.pweb.turmas.bean;

import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.event.ActionEvent;

import br.pweb.turmas.dao.AlunoDAO;
import br.pweb.turmas.dao.TurmaDAO;
import br.pweb.turmas.model.Aluno;
import br.pweb.turmas.model.Turma;

@ManagedBean
@ViewScoped
public class TurmasBean {

	private String nomeTurma,matricula,nomeAluno; 
	private Date dataTurma;
	private long id;
	private Turma turma;
	private List<Turma> turmas;
	private List<Aluno> alunos;
	
	public String cadastrarTurma(){
		TurmaDAO turmadao = new TurmaDAO();
		Turma turma = new Turma();
		turma.setNome(getNomeTurma());
		turma.setDataCriacao(dataTurma);
		turmadao.beginTransaction();
		turmadao.insert(turma);
		turmadao.commit();
		removerBean();
		return null;
	}
	
	public String cadastrarAluno(){
		AlunoDAO alunodao = new AlunoDAO();
		Aluno aluno = new Aluno();
		TurmaDAO turmadao = new TurmaDAO();
		turma = turmadao.find(id);
		aluno.setTurma(turma);
		aluno.setNome(nomeAluno);
		aluno.setMatricula(matricula);
		turma.addAluno(aluno);
		alunodao.beginTransaction();
		alunodao.insert(aluno);
		alunodao.commit();
		turmadao.beginTransaction();
		turmadao.update(turma);
		turmadao.commit();
		removerBean();
		
		return "turma?faces-redirect=true&id="+this.turma.getId();
	}
	
	public String excluirTurma(Turma turma){
		TurmaDAO turmadao = new TurmaDAO();
		turmadao.beginTransaction();
		turmadao.delete(turma);
		turmadao.commit();
		removerBean();
		
		return null;
	}
	
	public String excluirAluno(Aluno aluno){
		TurmaDAO turmadao = new TurmaDAO();
		turma = turmadao.find(id);
		turma.getAlunos().remove(aluno);
		turmadao.beginTransaction();
		turmadao.update(turma);
		turmadao.commit();
		AlunoDAO alunodao = new AlunoDAO();
		alunodao.beginTransaction();
		alunodao.delete(aluno);
		alunodao.commit();
		removerBean();
		
		return "turma?faces-redirect=true&id="+this.turma.getId();
	}
	
	public List<Turma> getTurmas() {
		return turmas;
	}

	public void setTurmas(List<Turma> turmas) {
		this.turmas = turmas;
	}
	
	public void listarTurmas() {
		TurmaDAO tDao = new TurmaDAO();
		this.turmas = tDao.findAll();
	}
	
	public String listarAlunos(){
		TurmaDAO tDao = new TurmaDAO();
		turma = tDao.find(id);
		
		this.alunos = turma.getAlunos();
		
		return "turma";
	}
	
	public void atualizarTurmas(ActionEvent e) {
		this.listarTurmas();
	}
	
	public void atualizarAlunos(ActionEvent e) {
		this.listarAlunos();
	}
	
	
	public void carregarTurmas(){
		this.listarTurmas();
	}
	
	public void carregarAlunos(){
		this.listarAlunos();
	}

	public String getNomeTurma() {
		return nomeTurma;
	}

	public void setNomeTurma(String nomeTurma) {
		this.nomeTurma = nomeTurma;
	}

	public Date getDataTurma() {
		return dataTurma;
	}

	public void setDataTurma(Date dataTurma) {
		this.dataTurma = dataTurma;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getNomeAluno() {
		return nomeAluno;
	}

	public void setNomeAluno(String nomeAluno) {
		this.nomeAluno = nomeAluno;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	private void loadFlash(){
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.put("turma", turma);
		flash.put("turmas", turmas);
	}
	
	public void unloadFlash(){
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		this.setTurma((Turma) flash.get(turma));
		this.setTurmas((List<Turma>) flash.get(turmas));
	}
	
	public String selecionarTurma(Turma turma){
		this.turma = turma;
		loadFlash();
		return "turma?faces-redirect=true&id="+this.turma.getId();
	}
	
	public void removerBean(){
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("turmasBean");
    }
	
}

