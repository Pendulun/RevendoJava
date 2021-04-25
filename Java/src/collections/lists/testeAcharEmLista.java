package collections.lists;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class testeAcharEmLista {

	private static final int NUM_PESSOAS = 10000;
	private static final int NUM_PESSOAS_PROCURADAS = 10;
	private static Random random;
	private static final int RANDOM_SEED = 74;
	
	
	public static void main(String[] args) {
		random = new Random(RANDOM_SEED);
		
		List<Pessoa> pessoas = new ArrayList<Pessoa>();
		
		for(int i = 0; i < NUM_PESSOAS; i++) {
			pessoas.add(samplePessoa());
		}

		procurarUmaPessoaEspecifica(pessoas);
		
		System.out.println("\n");
		
		procurarNPessoas(pessoas, NUM_PESSOAS_PROCURADAS);

	}
	


	private static void procurarNPessoas(List<Pessoa> pessoas, int nPessoas) {
		
		System.out.println("----N Pessoas----");
		int[] indexes = new int[nPessoas];
		int[] qtEqContains = new int[nPessoas];
		int[] qtEqFor = new int[nPessoas];
		
		indexes = geraNIndexes(nPessoas);
		
		for(int i=0; i<nPessoas; i++) {
			int indexPessoa = indexes[i];
			Pessoa pessoa = pessoas.get(indexPessoa);
			qtEqContains[i] = calculaGanho(indexPessoa,procurarPessoaViaContains(pessoas,pessoa));
			qtEqFor[i] = calculaGanho(indexPessoa, procurarPessoaViaFor(pessoas,pessoa));
		}
		
		System.out.println("Indexes objetos na lista: ");
		imprimeResultado(indexes);
		System.out.println("Comparações usando o contains: ");
		imprimeResultado(qtEqContains);
		System.out.println("Comparações usando o for: ");
		imprimeResultado(qtEqFor);
		
	}

	private static void imprimeResultado(int[] array) {
		imprimeArray(array);
		System.out.println("Comparações necessárias: "+somaArray(array));
		System.out.println("\n");
	}



	private static int calculaGanho(int indexPessoa, int comparacoes) {
		return indexPessoa - comparacoes;
	}



	private static int[] geraNIndexes(int nPessoas) {
		int[] indexes = new int[nPessoas];
		
		for(int i=0; i<nPessoas; i++) {
			indexes[i] = random.nextInt(NUM_PESSOAS);
		}
		return indexes;
	}



	private static void imprimeArray(int[] array) {
		for(int i=0; i<array.length; i++) {
			System.out.print(array[i]+", ");
		}
	}
	
	private static int somaArray(int[] array) {
		int soma = 0;
		for(int i=0; i<array.length; i++) {
			soma += array[i];
		}
		return soma;
	}


	private static int procurarPessoaViaFor(List<Pessoa> pessoas, Pessoa pessoaProc) {
		Pessoa.vezesEquals=0;
		for(Pessoa pessoa : pessoas) {
			if(pessoa.equals(pessoaProc)) {
				int vezesEq = Pessoa.vezesEquals;
				Pessoa.vezesEquals = 0;
				return vezesEq;
			}
		}
		return -1;
	}



	private static void procurarUmaPessoaEspecifica(List<Pessoa> pessoas) {
		
		System.out.println("----Uma pessoa específica----");
		int indexPessoa = random.nextInt(NUM_PESSOAS);
		System.out.println("IndexPessoa: "+indexPessoa);
		Pessoa pessoa = pessoas.get(indexPessoa);
		
		int compContains = procurarPessoaViaContains(pessoas,pessoa);
		int compIndexOf = procurarPessoaViaIndexOf(pessoas,pessoa);
		
		System.out.println("Comparações via Contains: "+compContains);
		
		System.out.println("Comparações via IndexOf: "+compIndexOf);
		
		if(compContains == compIndexOf) {
			System.out.println("Ambos são iguais, portanto, usam o mesmo algoritmo de procura");
		}
		
	}


	private static int procurarPessoaViaContains(List<Pessoa> pessoas, Pessoa pessoa) {
		pessoas.contains(pessoa);
		int vezesEquals = Pessoa.vezesEquals;
		Pessoa.vezesEquals = 0;
		return vezesEquals;
	}
	
	private static int procurarPessoaViaIndexOf(List<Pessoa> pessoas, Pessoa pessoa) {
		pessoas.indexOf(pessoa);
		int vezesEquals = Pessoa.vezesEquals;
		Pessoa.vezesEquals = 0;
		return vezesEquals;
	}


	private static Pessoa samplePessoa() {
		String[] nomes = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N"};
		String[] cpfs = {"AA", "AB", "AC", "AD", "AE", "BA", "BB", "BC", "BD", "BE", "BF", "BG", "CC", "CR"};
		String[] rgs = {"FA", "FB", "FC", "FD", "FE", "FA", "FB", "JC", "JD", "JE", "JF", "JG", "JC", "JR"};
		
		int nomeIndex = random.nextInt(nomes.length);
		int cpfIndex = random.nextInt(cpfs.length);
		int rgIndex = random.nextInt(rgs.length);
		
		return new Pessoa(nomes[nomeIndex], cpfs[cpfIndex], rgs[rgIndex]);
	}

}
