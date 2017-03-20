package br.edu.ifpb.pweb.turmas.bean;

import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
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
	private long turma_id;
	private Turma turma;
	private List<Turma> turmas;
	private List<Aluno> alunos;
	
	public String inserirTurma(){
		TurmaDAO turmadao = new TurmaDAO();
		Turma turma = new Turma();
		turma.setNome(getNomeTurma());
		turma.setDataCriacao(dataTurma);
		turmadao.beginTransaction();
		turmadao.insert(turma);
		turmadao.commit();
		return null;
	}
	
	public String inserirAluno(){
		AlunoDAO alunodao = new AlunoDAO();
		Aluno aluno = new Aluno();
		aluno.setTurma(turma);
		aluno.setNome(nomeAluno);
		aluno.setMatricula(matricula);
		alunodao.beginTransaction();
		alunodao.insert(aluno);
		alunodao.commit();
		return null;
	}
	
	public String excluirTurma(){
		return null;
	}
	
	public String excluirAluno(){
		return null;
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
		turma = tDao.find(turma_id);
		
		return "turma";
	}
	
	public void atualizarTurmas(ActionEvent e) {
		this.listarTurmas();
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
}
