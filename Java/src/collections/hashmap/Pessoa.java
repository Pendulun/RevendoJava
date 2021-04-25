package collections.hashmap;

public class Pessoa {
	
	private String id;
	private PessoaPK pessoaPK;
	private int idade;
	private int alturaCm;
	private int diaNascimento;
	private int mesNascimento;
	private int anoNascimento;
	public static int vezesEquals = 0;
	
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pessoaPK == null) ? 0 : pessoaPK.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		if (pessoaPK == null) {
			if (other.pessoaPK != null)
				return false;
		} else if (!pessoaPK.equals(other.pessoaPK))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Pessoa [pessoaPK=" + pessoaPK + "]";
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public int getIdade() {
		return idade;
	}
	public void setIdade(int idade) {
		this.idade = idade;
	}
	public int getAlturaCm() {
		return alturaCm;
	}
	public void setAlturaCm(int alturaCm) {
		this.alturaCm = alturaCm;
	}
	public int getDiaNascimento() {
		return diaNascimento;
	}
	public void setDiaNascimento(int diaNascimento) {
		this.diaNascimento = diaNascimento;
	}
	public int getMesNascimento() {
		return mesNascimento;
	}
	public void setMesNascimento(int mesNascimento) {
		this.mesNascimento = mesNascimento;
	}
	public int getAnoNascimento() {
		return anoNascimento;
	}
	public void setAnoNascimento(int anoNascimento) {
		this.anoNascimento = anoNascimento;
	}
	
	
}
