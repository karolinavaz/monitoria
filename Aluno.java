	import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Aluno extends Pessoa {
		private String disciplina;
		private String turno;
		private String cargaHoraria;

		public Aluno() {
			super();

		}
		public Aluno(String nome, String curso, String disciplina, String turno, String cargaHoraria) {
			super();
			this.disciplina=disciplina;
			this.cargaHoraria=cargaHoraria;
			this.turno = turno;
		}
		
		public String getDisciplina() {
			return disciplina;
		}
		public void setDisciplina(String disciplina) {
			this.disciplina = disciplina;
		}
		public String getTurno() {
			return turno;
		}
		public void setTurno(String turno) {
			this.turno = turno;
		}
		public String getCargaHoraria() {
			return cargaHoraria;
		}
		public void setCargaHoraria(String cargaHoraria) {
			this.cargaHoraria = cargaHoraria;
		}
	
		
		public void cadastrar(String n, String c, String na) {
			Coordenador co = new Coordenador(n,c,na);
			CoordenadorDAO cDAO = new CoordenadorDAO();
			cDAO.add(co);
		}
		
		public void Visualizar(String nomeArquivo) throws IOException {
			AlunoDAO editalDAO = new AlunoDAO();
			editalDAO.read(nomeArquivo);
			
		}
	
		public static void cadastrarAluno(String nome,String curso, String disciplina, String turno, String cargaH) {
			Aluno a = new Aluno();
			
			AlunoDAO adao = new AlunoDAO();
		
			a.setNome(nome);
			a.setCurso(curso);
			a.setDisciplina(disciplina);
			a.setTurno(turno);
			a.setCargaHoraria(cargaH);
			adao.add(a);
		

		}

		@Override
		public String toString() {
			return super.toString() + " Disciplina: " + disciplina + ", Turno: " + turno + ", Carga Horária: " + cargaHoraria + ", ";
		}
		public static void visualizarEdital() throws IOException {
			String curso = JOptionPane.showInputDialog(null, "Digite seu curso: ", "Edital", JOptionPane.OK_CANCEL_OPTION);
			CoordenadorDAO c = new CoordenadorDAO();

			String nomeArquivo = c.get(curso);

			Aluno a = new Aluno();
			a.Visualizar(nomeArquivo);
		}
		
		public static void resultado() throws IOException {

			Aluno a = new Aluno();
			a.Visualizar("selecionados.txt");
		}
		
//		public void pesquisaCurso(String curso) {
//			ArrayList<Aluno> astream = new ArrayList<Aluno>();
//			astream.stream().filter("ES").collect(collector); 
//		}
}
