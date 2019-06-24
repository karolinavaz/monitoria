import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class Coordenador extends Pessoa {
	private String nomeArquivo;

	public String getNomeArquivo() {
		return nomeArquivo;
	}

	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}

	public Coordenador() {
		super();

	}

	public Coordenador(String nome, String curso, String nomeArquivo) {
		super();
		this.nomeArquivo = nomeArquivo;
	}

	public void cadastrar(String n, String c, String na) {
		Coordenador co = new Coordenador(n, c, na);
		CoordenadorDAO cDAO = new CoordenadorDAO();
		cDAO.add(co);
	}

	public static void cadastrarCoordenador(String nome, String curso,String nomeArquivo) {
		Coordenador c = new Coordenador();
		CoordenadorDAO cdao = new CoordenadorDAO();
		c.setNome(nome);
		c.setCurso(curso);
		c.setNomeArquivo(nomeArquivo);
		cdao.add(c);

	}



	public static void vizualizarCandidatos() throws IOException {
		Aluno a = new Aluno();
		String curso = JOptionPane.showInputDialog(null, "Você deseja visualizar candidatos de qual curso? ", "Edital", JOptionPane.OK_CANCEL_OPTION);
		a.Visualizar("aluno.txt");
	}

	public static void selecao(String nome,String curso,String disciplina,String turno,String cargaH) {
		Monitor m = new Monitor();
		SelecionadosDAO sdao = new SelecionadosDAO();
		m.setNome(nome);
		m.setCurso(curso);
		m.setDisciplina(disciplina);
		m.setTurno(turno);
		m.setCargaHoraria(cargaH);
		sdao.add(m);
//		selecionados.add(m);
	}
	
	@Override
	public String toString() {
		return "Coordenador [nomeArquivo=" + nomeArquivo + "]";
	}

}
