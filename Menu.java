
import java.awt.List;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class Menu {
	public static ArrayList<Coordenador> coordenador =new ArrayList <> ( ) ;
	public static ArrayList<Aluno> aluno =new ArrayList <> ( ) ;
	public static ArrayList<Aluno> selecionados = new ArrayList<>();
	public static void main(String[] args) throws Exception {
		
		int op = Integer.parseInt(
				(JOptionPane.showInputDialog(null, "1 - Sou Aluno \n 2 - Sou Coordenador \n3 - Sou Monitor\n 4 - SAIR",
						"MENU", JOptionPane.OK_CANCEL_OPTION)));

		switch (op) {
		case 1:
			aluno();
			main(args);
			break;
		case 2:
			coordenador();
			main(args);
			break;
		case 3:
			monitor();
			main(args);
			break;
		case 4:
			break;
		default:
			main(args);
		}

	}

	public static void aluno() throws IOException, InterruptedException {
		Aluno a = new Aluno();
		int op = Integer.parseInt((JOptionPane.showInputDialog(null,
				"1 - Visualizar Edital \n 2 - Cadastrar \n 3 - Visualizar Resultados \n 4 - SAIR", "MENU",
				JOptionPane.OK_CANCEL_OPTION)));
		switch (op) {
		case 1:
			limparTela();
			a.visualizarEdital();
			aluno();
			break;
		case 2:
			limparTela();
			aluno.add(pegaDadosAluno());
			aluno();
			break;
		case 3:
			limparTela();
			Selecionados();
//			resultado(pegaDadosSele());
			break;
		case 4:
			break;
		default:
			aluno();
		}
	}

	public static void coordenador() throws IOException, InterruptedException {

		Coordenador c = new Coordenador();
		
		int op = Integer.parseInt((JOptionPane.showInputDialog(null,"1 - Cadastrar \n 2 - Vizualizar Candidatos \n  3 - Seleção \n 4 - SAIR", "MENU",
				JOptionPane.OK_CANCEL_OPTION)));
		switch (op) {

		case 1:
			coordenador.add(pegaDados());			
			coordenador();
			break;
		case 2:
			limparTela();
			Candidatos();
			coordenador();
			break;
		case 3:
			selecionados.add(pegaDadosSele());
			coordenador();
			break;
		case 4:
			break;
		default:
			coordenador();
		}
	}

	public static void limparTela() {
		for (int i = 0; i < 80; i++)
			System.out.print("\n");
	}

	public static void monitor() {
		Monitor m = new Monitor();
		int op = Integer.parseInt((JOptionPane.showInputDialog(null, "1 - Cadastrar Relatório \n 2 - SAIR", "MENU",
				JOptionPane.OK_CANCEL_OPTION)));
		switch (op) {
		case 1:
			limparTela();
			m.relatorioAluno();
			monitor();
			break;
		case 2:
			break;
		default:
			monitor();
		}
	}
	
	public static Coordenador pegaDados() {
		Coordenador c = new Coordenador();
		String nome = JOptionPane.showInputDialog(null, "Digite seu nome: ", "Cadastro", JOptionPane.OK_CANCEL_OPTION);
		String curso = JOptionPane.showInputDialog(null, "Digite seu curso: ", "Cadastro",
				JOptionPane.OK_CANCEL_OPTION);
		JOptionPane.showConfirmDialog(null, "Escolha o arquivo do edital (formato .txt): ");
		String nomeArquivo = escolherArquivos();
		c.setNome(nome);
		c.setCurso(curso);
		c.setNomeArquivo(nomeArquivo);
		c.cadastrarCoordenador(nome, curso, nomeArquivo);
		return c;
	}
	
	public static Aluno pegaDadosAluno() {
		Aluno a = new Aluno();
		String nome = JOptionPane.showInputDialog(null, "Digite seu nome: ", "Cadastro", JOptionPane.OK_CANCEL_OPTION);
		String curso = JOptionPane.showInputDialog(null, "Digite seu curso: ", "Cadastro",
				JOptionPane.OK_CANCEL_OPTION);
		String disciplina = JOptionPane.showInputDialog(null, "Digite nome da disciplina: ", "Cadastro",
				JOptionPane.OK_CANCEL_OPTION);
		String turno = "Turno: "+JOptionPane.showInputDialog(null, "Digite o turno: ", "Cadastro", JOptionPane.OK_CANCEL_OPTION);
		String cargaH = "Carga Horária: " + JOptionPane.showInputDialog(null, "Digite a carga horária: ", "Cadastro",
				JOptionPane.OK_CANCEL_OPTION) ;
		a.setNome(nome);
		a.setCurso(curso);
		a.setDisciplina(disciplina);
		a.setTurno(turno);
		a.setCargaHoraria(cargaH);
		a.cadastrarAluno(nome, curso, disciplina, turno,cargaH);
		return a;
	}
	
	public static String escolherArquivos() {
		File[] arquivos = null;
		JFileChooser fc = new JFileChooser();
		String caminhodoarquivo = null;
		fc.setDialogTitle("Escolha o(s) arquivo(s)...");
		fc.setDialogType(JFileChooser.OPEN_DIALOG);
		fc.setApproveButtonText("OK");
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fc.setMultiSelectionEnabled(true);
		int resultado = fc.showOpenDialog(fc);

		if (resultado == JFileChooser.APPROVE_OPTION) {
			caminhodoarquivo = fc.getSelectedFile().getAbsolutePath();
		}
		arquivos = fc.getSelectedFiles();
		System.out.println(caminhodoarquivo);
		return caminhodoarquivo;
	}
	
	public static Monitor pegaDadosSele() {
		String curso = JOptionPane.showInputDialog(null, "Você deseja selecionar candidatos de qual curso? ", "Pesquisa",
				JOptionPane.OK_CANCEL_OPTION);
		aluno.stream().filter(a -> a.getCurso().equals(curso)).forEach(a -> System.out.println(a.toString()));
		Monitor m = new Monitor();
		Coordenador c = new Coordenador();
		SelecionadosDAO sdao = new SelecionadosDAO();
		String nome = JOptionPane.showInputDialog(null, "Digite o nome do aluno: ", "Cadastro",
				JOptionPane.OK_CANCEL_OPTION);
		String curso11 = JOptionPane.showInputDialog(null, "Digite o curso: ", "Cadastro", JOptionPane.OK_CANCEL_OPTION);
		String disciplina = JOptionPane.showInputDialog(null, "Digite a disciplina: ", "Cadastro",
				JOptionPane.OK_CANCEL_OPTION);
		String turno = JOptionPane.showInputDialog(null, "Digite o turno: ", "Cadastro", JOptionPane.OK_CANCEL_OPTION);
		String cargaH = JOptionPane.showInputDialog(null, "Digite a carga horária: ", "Cadastro",
				JOptionPane.OK_CANCEL_OPTION);
		m.setNome(nome);
		m.setCurso(curso11);
		m.setDisciplina(disciplina);
		m.setTurno(turno);
		m.setCargaHoraria(cargaH);
		selecionados.add(m);
		c.selecao(nome,curso,disciplina,turno, cargaH);
		return m;
	}
	
	public static void Candidatos() {
		String curso = JOptionPane.showInputDialog(null, "Você deseja visualizar candidatos de qual curso? ", "Pesquisa",
				JOptionPane.OK_CANCEL_OPTION);
		aluno.stream().filter(a -> a.getCurso().equals(curso)).forEach(a -> System.out.println(a.toString()));
	}
	
	public static void Selecionados() {
		String curso = JOptionPane.showInputDialog(null, "Você deseja visualizar candidatos de qual curso? ", "Pesquisa",
				JOptionPane.OK_CANCEL_OPTION);
		selecionados.stream().filter(s -> s.getCurso().equals(curso)).forEach(s -> System.out.println(s.toString()));
	}

}
