import java.util.ArrayList;
import java.util.List;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

public class SelecionadosDAO implements ArquivoDAO<Aluno, String>{

		public SelecionadosDAO() {

		}

		
		public void add(Aluno p) {
			Aluno b = p;
			try (BufferedWriter buffer_saida = new BufferedWriter(new FileWriter("selecionados.txt", true))) {
				String separadorDeLinha = System.getProperty("line.separator");
				buffer_saida.write(b.getNome() + separadorDeLinha);
				buffer_saida.write(b.getCurso() + separadorDeLinha);
				buffer_saida.write(b.getDisciplina() + separadorDeLinha);
				buffer_saida.write(b.getTurno() + separadorDeLinha);
				buffer_saida.write(b.getCargaHoraria() + separadorDeLinha);
				buffer_saida.flush();

			} catch (Exception e) {
				System.out.println("ERRO ao gravar selecionado" );
				e.printStackTrace();
			}
		}


		@Override
		public String get(String chave) {
			Aluno retorno = null;
			Aluno b = null;
			String idSTR;

			try (DataInputStream entrada = new DataInputStream(new FileInputStream("selecionados.txt"))) {
				while ((idSTR = entrada.readUTF()) != null) {
					b = new Aluno();
					b.setNome(entrada.readUTF());
					b.setCurso(entrada.readUTF());
					b.setDisciplina(entrada.readUTF());
					b.setTurno(entrada.readUTF());
					b.setCargaHoraria(entrada.readUTF());

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
		public List<Aluno> getAll() {
			List<Aluno> alunos = new ArrayList<Aluno>();
			Aluno b = null;
			String idSTR;

			try (DataInputStream entrada = new DataInputStream(new FileInputStream("selecionados.txt"))) {
				while ((idSTR = entrada.readUTF()) != null) {
					b = new Aluno();
					b.setNome(entrada.readUTF());
					b.setCurso(entrada.readUTF());
					b.setDisciplina(entrada.readUTF());
					b.setTurno(entrada.readUTF());
					b.setCargaHoraria(entrada.readUTF());
					alunos.add(b);
				}
			} catch(EOFException e) { 

			} catch (Exception e) {
				System.out.println("ERRO ao ler o arquivo");
				e.printStackTrace();
			}
			return (List<Aluno>) alunos;
		}

		@Override
		public void update(Aluno p) {
			List<Aluno> alunos = getAll();
			int index = alunos.indexOf(p);
			if (index != -1) {
				alunos.set(index,p);
			}
			saveToFile(alunos);
		}

		@Override
		public void delete(Aluno p) {
			List<Aluno> alunos = getAll();
			int index = alunos.indexOf(p);
			if (index != -1) {
				alunos.remove(index);
			}
			saveToFile(alunos);
		}

		private void saveToFile(List<Aluno> alunos) {
			try (DataOutputStream saida = 
				new DataOutputStream(new FileOutputStream("aluno.txt", false))) {
				for (Aluno b : alunos) {
					saida.writeUTF(b.getNome());
					saida.writeUTF(b.getCurso());
					saida.writeUTF(b.getDisciplina());
					saida.writeUTF(b.getTurno());
					saida.writeUTF(b.getCargaHoraria());
					saida.flush();
				}

			} catch (Exception e) {
				System.out.println("ERRO ao gravar o Bens duraveis no disco!");
				e.printStackTrace();
			}
		}

		@Override
		public void read(String nomeArquivo) throws IOException {
			// TODO Auto-generated method stub
			
		}



}
