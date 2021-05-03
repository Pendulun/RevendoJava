package collections.lists.searchSortingFirst;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import collections.lists.Pessoa;

public class TesteProcuraOrdenandoPrimeiro {

	private static final int NUM_PESSOAS = 100000;
	private static final int NUM_PESSOAS_PROCURADAS = 10;
	private static Random random;
	private static final int RANDOM_SEED = 74;
	private static final PessoaRgCpfNomeComparator comparePessoaByRgCpfNome = new PessoaRgCpfNomeComparator();

	public static void main(String[] args) {

		random = new Random(RANDOM_SEED);

		List<Pessoa> pessoas = createListOfPessoa();
		int[] indexes = generateNIndexes(NUM_PESSOAS_PROCURADAS);

		searchWithoutOrdering(pessoas, indexes);
		searchOrderingFirst(pessoas, indexes);

	}

	private static List<Pessoa> createListOfPessoa() {
		List<Pessoa> pessoas = new ArrayList<Pessoa>();
		System.out.println("Creating List of " + NUM_PESSOAS + " Pessoas");

		for (int i = 0; i < NUM_PESSOAS; i++) {
			pessoas.add(samplePessoa());
		}
		return pessoas;
	}

	private static void searchOrderingFirst(List<Pessoa> pessoas, int[] indexes) {
		System.out.println("----Searching ordering first----");

		sortListOfPessoa(pessoas);

		searchOnePessoa(pessoas, indexes);

		System.out.println("\n");

		searchNPessoas(pessoas, indexes);

		searchNPessoasBinary(pessoas, indexes);

	}

	private static void sortListOfPessoa(List<Pessoa> pessoas) {
		Collections.sort(pessoas, comparePessoaByRgCpfNome);

		System.out.println("Comparations needed in Collections.sort: " + PessoaRgCpfNomeComparator.numComparations);

		PessoaRgCpfNomeComparator.numComparations = 0;

	}

	private static void searchNPessoasBinary(List<Pessoa> pessoas, int[] indexes) {
		System.out.println("----Searching " + indexes.length + " Pessoas with Binary Search----");
		int[] qtEqBinary = new int[indexes.length];

		for (int i = 0; i < indexes.length; i++) {
			int indexPessoa = indexes[i];
			Pessoa pessoa = pessoas.get(indexPessoa);
			qtEqBinary[i] = searchPessoaViaBinarySearch(pessoas, pessoa);
		}

		System.out.println("Comparations using Collections.BinarySearch: ");
		printResults(qtEqBinary);
	}

	private static void searchWithoutOrdering(List<Pessoa> pessoas, int[] indexes) {
		System.out.println("----Searching without ordering first----");
		searchOnePessoa(pessoas, indexes);

		System.out.println("\n");

		searchNPessoas(pessoas, indexes);

	}

	private static void searchNPessoas(List<Pessoa> pessoas, int[] indexes) {

		System.out.println("----Searching " + indexes.length + " Pessoas----");
		int[] qtEqContains = new int[indexes.length];

		for (int i = 0; i < indexes.length; i++) {
			int indexPessoa = indexes[i];
			Pessoa pessoa = pessoas.get(indexPessoa);
			qtEqContains[i] = searchPessoaViaContains(pessoas, pessoa);
		}

		System.out.println("Comparations using contains: ");
		printResults(qtEqContains);

	}

	private static int searchPessoaViaBinarySearch(List<Pessoa> pessoas, Pessoa pessoa) {
		PessoaRgCpfNomeComparator.numComparations = 0;
		Collections.binarySearch(pessoas, pessoa, comparePessoaByRgCpfNome);
		int vezesEquals = PessoaRgCpfNomeComparator.numComparations;
		PessoaRgCpfNomeComparator.numComparations = 0;
		return vezesEquals;
	}

	private static void printResults(int[] array) {
		printArrayElements(array);
		System.out.println("Sum of comparations needed: " + sumArrayElements(array));
		System.out.println("\n");
	}

	private static int[] generateNIndexes(int nPessoas) {
		int[] indexes = new int[nPessoas];

		for (int i = 0; i < nPessoas; i++) {
			indexes[i] = random.nextInt(NUM_PESSOAS);
		}

		System.out.println("Pessoa indexes to be searched on the List");
		printResults(indexes);

		return indexes;
	}

	private static void printArrayElements(int[] array) {
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + ", ");
		}
	}

	private static int sumArrayElements(int[] array) {
		int soma = 0;
		for (int i = 0; i < array.length; i++) {
			soma += array[i];
		}
		return soma;
	}

	private static void searchOnePessoa(List<Pessoa> pessoas, int[] indexes) {

		System.out.println("----Just one Pessoa searched----");
		int indexPessoa = indexes[0];
		System.out.println("Pessoa index in List: " + indexPessoa);
		Pessoa pessoa = pessoas.get(indexPessoa);

		int compContains = searchPessoaViaContains(pessoas, pessoa);
		int compIndexOf = searchPessoaViaIndexOf(pessoas, pessoa);

		System.out.println("Comparations using Contains: " + compContains);
		System.out.println("Comparations using IndexOf: " + compIndexOf);

		if (compContains == compIndexOf) {
			System.out.println("Both are equal, therefore, use the same search algorithm!");
		}

	}

	private static int searchPessoaViaContains(List<Pessoa> pessoas, Pessoa pessoa) {
		Pessoa.vezesEquals = 0;
		pessoas.contains(pessoa);
		int vezesEquals = Pessoa.vezesEquals;
		Pessoa.vezesEquals = 0;
		return vezesEquals;
	}

	private static int searchPessoaViaIndexOf(List<Pessoa> pessoas, Pessoa pessoa) {
		pessoas.indexOf(pessoa);
		int vezesEquals = Pessoa.vezesEquals;
		Pessoa.vezesEquals = 0;
		return vezesEquals;
	}

	private static Pessoa samplePessoa() {
		String[] nomes = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N" };
		String[] cpfs = { "AA", "AB", "AC", "AD", "AE", "BA", "BB", "BC", "BD", "BE", "BF", "BG", "CC", "CR" };
		String[] rgs = { "FA", "FB", "FC", "FD", "FE", "FA", "FB", "JC", "JD", "JE", "JF", "JG", "JC", "JR" };

		int nomeIndex = random.nextInt(nomes.length);
		int cpfIndex = random.nextInt(cpfs.length);
		int rgIndex = random.nextInt(rgs.length);

		return new Pessoa(nomes[nomeIndex], cpfs[cpfIndex], rgs[rgIndex]);
	}

}
