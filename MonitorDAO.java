import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MonitorDAO implements ArquivoDAO<Monitor, String>{

	public MonitorDAO() {

	}

	
	public void add(Monitor p) {
		Monitor b = p;
		try (BufferedWriter buffer_saida = new BufferedWriter(new FileWriter("relatorio.txt", true))) {
			String separadorDeLinha = System.getProperty("line.separator");
			buffer_saida.write(b.getNome() + separadorDeLinha);
			buffer_saida.write(b.getCurso() + separadorDeLinha);
			buffer_saida.write(b.getDisciplina() + separadorDeLinha);
			buffer_saida.write(b.getnAluno() + separadorDeLinha);
			buffer_saida.write(b.getAtendimento() + separadorDeLinha);
			buffer_saida.flush();

		} catch (Exception e) {
			System.out.println("ERRO ao gravar Aluno" );
			e.printStackTrace();
		}
	}

	@Override
	public String get(String chave) {
		Monitor retorno = null;
		Monitor b = null;
		String idSTR;

		try (DataInputStream entrada = new DataInputStream(new FileInputStream("relatorio.txt"))) {
			while ((idSTR = entrada.readUTF()) != null) {
				b = new Monitor();
				b.setNome(entrada.readUTF());
				b.setCurso(entrada.readUTF());
				b.setDisciplina(entrada.readUTF());
				b.setnAluno(entrada.readUTF());
				b.setAtendimento(entrada.readUTF());

				if (chave.equalsIgnoreCase(b.getNome())) {
					retorno = b;
					break;
				}
			}
		} catch(EOFException e) { 

		} catch (Exception e) {
			System.out.println("ERRO ao ler o arquivo");
			e.printStackTrace();
		}
		return null;
	}
	



	@Override
	public List<Monitor> getAll() {
		List<Monitor> monitor = new ArrayList<Monitor>();
		Monitor b = null;
		String idSTR;

		try (DataInputStream entrada = new DataInputStream(new FileInputStream("relatorio.txt"))) {
			while ((idSTR = entrada.readUTF()) != null) {
				b = new Monitor();
				b.setNome(entrada.readUTF());
				b.setCurso(entrada.readUTF());
				b.setDisciplina(entrada.readUTF());
				b.setnAluno(entrada.readUTF());
				b.setAtendimento(entrada.readUTF());
				monitor.add(b);
			}
		} catch(EOFException e) { 

		} catch (Exception e) {
			System.out.println("ERRO ao ler o arquivo");
			e.printStackTrace();
		}
		return (List<Monitor>) monitor;
	}

	@Override
	public void update(Monitor p) {
		List<Monitor> m = getAll();
		int index = m.indexOf(p);
		if (index != -1) {
			m.set(index,p);
		}
		saveToFile(m);
	}

	@Override
	public void delete(Monitor p) {
		List<Monitor> m = getAll();
		int index = m.indexOf(p);
		if (index != -1) {
			m.remove(index);
		}
		saveToFile(m);
	}

	private void saveToFile(List<Monitor> m) {
		try (DataOutputStream saida = 
			new DataOutputStream(new FileOutputStream("relatorio.txt", false))) {
			for (Monitor b : m) {
				saida.writeUTF(b.getNome());
				saida.writeUTF(b.getCurso());
				saida.writeUTF(b.getDisciplina());
				saida.writeUTF(b.getnAluno());
				saida.writeUTF(b.getAtendimento());
				saida.flush();
			}

		} catch (Exception e) {
			System.out.println("ERRO ao gravar Aluno no disco!");
			e.printStackTrace();
		}
	}

	@Override
	public void read(String nomeArquivo) throws IOException {
		// TODO Auto-generated method stub
		
	}


}
