package collections.lists;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class testeAcharEmLista {

	private static int NUM_PESSOAS = 10000;
	private static Random random = new Random();
	
	
	public static void main(String[] args) {
		
		List<Pessoa> pessoas = new ArrayList<Pessoa>();
		
		for(int i = 0; i < NUM_PESSOAS; i++) {
			pessoas.add(samplePessoa());
		}

		procurarUmaPessoaEspecifica(pessoas);
		
		System.out.println("\n");
		
		procurarNPessoas(pessoas, 10);

	}
	


	private static void procurarNPessoas(List<Pessoa> pessoas, int nPessoas) {
		
		System.out.println("----N Pessoas----");
		int[] indexes = new int[nPessoas];
		int[] qtEqContains = new int[nPessoas];
		//int[] qtEqIndexOf = new int[nPessoas];
		int[] qtEqForExcluindo = new int[nPessoas];
		
		indexes = geraNIndexes(nPessoas);
		
		for(int i=0; i<nPessoas; i++) {
			int indexPessoa = indexes[i];
			Pessoa pessoa = pessoas.get(indexPessoa);
			qtEqContains[i] = calculaGanho(indexPessoa,procurarPessoaViaContains(pessoas,pessoa));
		//	qtEqIndexOf[i] = calculaGanho(indexPessoa, procurarPessoaViaIndexOf(pessoas,pessoa));
				
		}
		
		for(int i=0; i<nPessoas; i++) {
			int indexPessoa = indexes[i];
			Pessoa pessoa = pessoas.get(indexPessoa);
			qtEqForExcluindo[i] = calculaGanho(indexPessoa, procurarPessoaViaForExcluindo(pessoas,pessoa));
				
		}
		
		
		System.out.println("Indexes objetos na lista: ");
		imprimeResultado(indexes);
		System.out.println("Compara��es usando o contains: ");
		imprimeResultado(qtEqContains);
		//System.out.println("qtEqIndexOf: ");
		//imprimeResultado(qtEqIndexOf);
		System.out.println("Compara��es usando o for com remove: ");
		imprimeResultado(qtEqForExcluindo);
		
	}

	private static void imprimeResultado(int[] array) {
		imprimeArray(array);
		System.out.println("Compara��es necess�rias: "+somaArray(array));
		System.out.println("\n");
	}



	private static int calculaGanho(int indexPessoa, int procurarPessoaViaContains) {
		return indexPessoa - procurarPessoaViaContains;
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


	private static int procurarPessoaViaForExcluindo(List<Pessoa> pessoas, Pessoa pessoaProc) {
		for(Pessoa pessoa : pessoas) {
			if(pessoa.equals(pessoaProc)) {
				pessoas.remove(pessoaProc);
				int vezesEq = Pessoa.vezesEquals;
				Pessoa.vezesEquals = 0;
				return vezesEq;
			}
		}
		return -1;
	}



	private static void procurarUmaPessoaEspecifica(List<Pessoa> pessoas) {
		
		System.out.println("----Uma pessoa espec�fica----");
		int indexPessoa = random.nextInt(NUM_PESSOAS);
		System.out.println("IndexPessoa: "+indexPessoa);
		Pessoa pessoa = pessoas.get(indexPessoa);
		
		int compContains = procurarPessoaViaContains(pessoas,pessoa);
		int compIndexOf = procurarPessoaViaIndexOf(pessoas,pessoa);
		
		System.out.println("Compara��es via Contains: "+compContains);
		
		System.out.println("Compara��es via IndexOf: "+compIndexOf);
		
		if(compContains == compIndexOf) {
			System.out.println("Ambos s�o iguais, portanto, usam o mesmo algoritmo de procura");
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