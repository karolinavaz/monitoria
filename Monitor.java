import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Monitor extends Aluno{
	private String nAluno;
	private String atendimento;
	static Monitor monitor = new Monitor();
	public String getnAluno() {
		return nAluno;
	}

	public void setnAluno(String nAluno) {
		this.nAluno = nAluno;
	}

	public String getAtendimento() {
		return atendimento;
	}

	public void setAtendimento(String atendimento) {
		this.atendimento = atendimento;
	}

	public Monitor() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Monitor(String nome, String curso, String disciplina, String turno, String cargaHoraria) {
		super(nome, curso, disciplina, turno, cargaHoraria);
		// TODO Auto-generated constructor stub
	}
	
	public static void relatorioAluno() {
		
		ArrayList<Aluno> relatorioAluno =new ArrayList <Aluno> ( ) ;
		MonitorDAO adao = new MonitorDAO();
		String nome = JOptionPane.showInputDialog(null, "Digite seu nome: ", "Cadastro", JOptionPane.OK_CANCEL_OPTION);
		String curso = JOptionPane.showInputDialog(null, "Digite seu curso: ", "Cadastro",
				JOptionPane.OK_CANCEL_OPTION);
		String disciplina = JOptionPane.showInputDialog(null, "Digite nome da disciplina: ", "Cadastro",
				JOptionPane.OK_CANCEL_OPTION);
		String nAluno = JOptionPane.showInputDialog(null, "Digite o nome do aluno: ", "Cadastro", JOptionPane.OK_CANCEL_OPTION);
		String atendimento = JOptionPane.showInputDialog(null, "Atendimentos: ", "Cadastro",
				JOptionPane.OK_CANCEL_OPTION);
		monitor.setNome(nome);
		monitor.setCurso(curso);
		monitor.setDisciplina(disciplina);
		monitor.setnAluno(nAluno);
		monitor.setAtendimento(atendimento);
		adao.add(monitor);
		relatorioAluno.add(monitor);
	}

	
	public String toString1() {
		return super.toString();
	}
	
	@Override
	public String toString() {
		return super.toString() + "Nome Aluno: " + nAluno + "Atendimento: " + atendimento + "]";
	}
	
}
