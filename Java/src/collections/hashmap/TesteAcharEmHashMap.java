package collections.hashmap;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * Comparamos encontrar um número de objetos de Pessoa em um ArrayList e em um HashMap.
 * A classe Pessoa possui um objeto da classe PessoaPK. Esse objeto é a chave do objeto Pessoa no HashMap
 * @author Daniel
 *
 */
public class TesteAcharEmHashMap {

	private static int NUM_PESSOAS_TOTAL = 10000;
	private static int NUM_PESSOAS_PROCURADAS = 10;
	private static Random random = new Random();

	public static void main(String[] args) {

		List<Pessoa> listaPessoas = new ArrayList<>();

		System.out.println("Gerando List de Pessoa com "+NUM_PESSOAS_TOTAL+" objetos.");
		
		for (int i = 0; i < NUM_PESSOAS_TOTAL; i++) {
			Pessoa pessoa = samplePessoa();
			listaPessoas.add(pessoa);
		}

		int[] indexes = geraNIndexes(NUM_PESSOAS_PROCURADAS);
		
		System.out.println("Indexes das pessoas a serem procuradas:");
		for(int index : indexes) {
			System.out.print(index+", ");
		}

		System.out.println();
		
		procurarComList(listaPessoas, indexes);

		procurarComHashMap(listaPessoas, indexes);

	}

	private static void procurarComList(List<Pessoa> listaPessoas, int[] indexes) {
		int[] numEquals = new int[indexes.length];
		Pessoa.vezesEquals=0;

		Instant start = Instant.now();
		for (int i = 0; i < indexes.length; i++) {
			Pessoa pessoaProcurada = listaPessoas.get(indexes[i]);
			listaPessoas.contains(pessoaProcurada);
			numEquals[i] = Pessoa.vezesEquals;
			Pessoa.vezesEquals = 0;
		}
		Instant finish = Instant.now();

		System.out.println("Número de comparações com List: ");
		imprimeInformacoesComparacoes(numEquals);
		System.out.println("Tempo que levou em milisegundos: "+ Duration.between(start, finish).toMillis());

	}
	
	private static void procurarComHashMap(List<Pessoa> listaPessoas, int[] indexes) {
		int[] numEquals = new int[indexes.length];
		
		HashMap<PessoaPK,Pessoa> pessoas = new HashMap<>();
		PessoaPK.vezesEquals = 0;
		
		for(Pessoa pessoa: listaPessoas) {
			pessoas.put(pessoa.getPessoaPK(), pessoa);
		}
		
		PessoaPK.vezesEquals = 0;
		Pessoa.vezesEquals = 0;
		
		Instant start = Instant.now();
		for (int i = 0; i < indexes.length; i++) {
			Pessoa pessoaProcurada = listaPessoas.get(indexes[i]);
			pessoas.get(pessoaProcurada.getPessoaPK());
			numEquals[i] = PessoaPK.vezesEquals;
			PessoaPK.vezesEquals = 0;
		}
		Instant finish = Instant.now();
		
		System.out.println("Número de comparações com HashMap: ");
		imprimeInformacoesComparacoes(numEquals);
		System.out.println("Tempo que levou em milisegundos: "+ Duration.between(start, finish).toMillis());
		
	}

	private static void imprimeInformacoesComparacoes(int[] numEquals) {
		int soma = 0;
		for (int i = 0; i < numEquals.length; i++) {
			System.out.print(numEquals[i] + ", ");
			soma += numEquals[i];
		}
		System.out.println("Soma: " + soma);
	}

	private static int[] geraNIndexes(int nPessoas) {
		int[] indexes = new int[nPessoas];

		for (int i = 0; i < nPessoas; i++) {
			indexes[i] = random.nextInt(NUM_PESSOAS_TOTAL);
		}
		return indexes;
	}

	private static Pessoa samplePessoa() {
		String[] nomes = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N" };
		String[] cpfs = { "AA", "AB", "AC", "AD", "AE", "BA", "BB", "BC", "BD", "BE", "BF", "BG", "CC", "CR" };
		String[] rgs = { "FA", "FB", "FC", "FD", "FE", "FA", "FB", "JC", "JD", "JE", "JF", "JG", "JC", "JR" };

		int nomeIndex = random.nextInt(nomes.length);
		int cpfIndex = random.nextInt(cpfs.length);
		int rgIndex = random.nextInt(rgs.length);

		PessoaPK pessoaPK = new PessoaPK(nomes[nomeIndex], cpfs[cpfIndex], rgs[rgIndex]);
		return new Pessoa(pessoaPK);
	}

}
