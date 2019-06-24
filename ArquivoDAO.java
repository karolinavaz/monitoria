import java.io.IOException;
import java.util.List;

public interface ArquivoDAO<T, K> {
		public String get(K chave);
		public void add(T p);
		public void update(T p);
		public void delete(T p);
		public List<T> getAll();
		void read(String nomeArquivo) throws IOException;

	}

