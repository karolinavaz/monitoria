
public class Pessoa {
	@Override
	public String toString() {
		return "Nome: " + nome + ", Curso: " + curso + ", ";
	}

	private String nome;
	private String curso;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}
}
