package collections.hashmap;

public class PessoaPK {

	private final String nome;
	private final String cpf;
	private final String rg;
	public static int vezesEquals=0;
	
	public PessoaPK(String nome, String cpf, String rg) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.rg = rg;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((rg == null) ? 0 : rg.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		vezesEquals++;
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PessoaPK other = (PessoaPK) obj;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (rg == null) {
			if (other.rg != null)
				return false;
		} else if (!rg.equals(other.rg))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "PessoaPK [nome=" + nome + ", cpf=" + cpf + ", rg=" + rg + "]";
	}

	public String getNome() {
		return nome;
	}
	public String getCpf() {
		return cpf;
	}
	public String getRg() {
		return rg;
	}
	
}
