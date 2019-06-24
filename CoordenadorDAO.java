import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
	
public class CoordenadorDAO implements ArquivoDAO<Coordenador, String>{

		public CoordenadorDAO() {

		}

		public void add(Coordenador p) {
			Coordenador b = p;
			try (BufferedWriter buffer_saida = new BufferedWriter(new FileWriter("coordenadores.txt", true))) {
				String separadorDeLinha = System.getProperty("line.separator");
				buffer_saida.write(b.getNome() + separadorDeLinha);
				buffer_saida.write(b.getCurso() + separadorDeLinha);
				buffer_saida.write(b.getNomeArquivo() + separadorDeLinha);
				buffer_saida.flush();

			} catch (Exception e) {
				System.out.println("ERRO ao gravar o Coordenador" );
				e.printStackTrace();
			}
		}


		@Override
		public String get(String chave) {
			Coordenador retorno = null;
			Coordenador b = null;

			try (BufferedReader buffer_entrada = new BufferedReader(new FileReader("coordenadores.txt"))) {
				String idSTR;

				while ((idSTR = buffer_entrada.readLine()) != null) {
					b = new Coordenador();
					b.setNome(idSTR);
					b.setCurso(buffer_entrada.readLine());
					b.setNomeArquivo(buffer_entrada.readLine());
					if (chave.equals(b.getCurso())) {
						return b.getNomeArquivo();
					}
				}
			} catch (Exception e) {
				System.out.println("ERRO ao ler Coordenador" );
				e.printStackTrace();
			}
			return null;
		}

		@Override
		public List<Coordenador> getAll() {
			List<Coordenador> coordenadores = new ArrayList<Coordenador>();
			Coordenador b = null;
			String idSTR;

			try (DataInputStream entrada = new DataInputStream(new FileInputStream("coordenador.txt"))) {
				while ((idSTR = entrada.readUTF()) != null) {
					b = new Coordenador();
					b.setNome(entrada.readUTF());
					b.setCurso(entrada.readUTF());
					b.setNomeArquivo(entrada.readUTF());
					
					coordenadores.add(b);
				}
			} catch(EOFException e) { 

			} catch (Exception e) {
				System.out.println("ERRO ao ler o arquivo");
				e.printStackTrace();
			}
			return (List<Coordenador>) coordenadores;
		}

		@Override
		public void update(Coordenador p) {
			List<Coordenador> coordenadores = getAll();
			int index = coordenadores.indexOf(p);
			if (index != -1) {
				coordenadores.set(index,p);
			}
			saveToFile(coordenadores);
		}

		@Override
		public void delete(Coordenador p) {
			List<Coordenador> coordenadores = getAll();
			int index = coordenadores.indexOf(p);
			if (index != -1) {
				coordenadores.remove(index);
			}
			saveToFile(coordenadores);
		}

		private void saveToFile(List<Coordenador> coordenadores) {
			try (DataOutputStream saida = 
				new DataOutputStream(new FileOutputStream("coordenador.txt", false))) {
				for (Coordenador b : coordenadores) {
					saida.writeUTF(b.getNome());
					saida.writeUTF(b.getCurso());
					saida.writeUTF(b.getNomeArquivo());
					saida.flush();
				}

			} catch (Exception e) {
				System.out.println("ERRO ao gravar Coordenadores no disco!");
				e.printStackTrace();
			}
		}



		@Override
		public void read(String nomeArquivo) throws IOException {
			// TODO Auto-generated method stub
			
		}

	
}
