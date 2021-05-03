package collections.lists.searchSortingFirst;

import java.text.Collator;
import java.util.Comparator;

import collections.lists.Pessoa;

public class PessoaRgCpfNomeComparator implements Comparator<Pessoa> {
	
	public static int numComparations = 0;

	@Override
	public int compare(Pessoa pessoaAtual, Pessoa outraPessoa) {
		numComparations++;
		int comparaRg = Collator.getInstance().compare(pessoaAtual.getRg(), outraPessoa.getRg());
		if (comparaRg == 0) {
			int comparaCpf = Collator.getInstance().compare(pessoaAtual.getCpf(), outraPessoa.getCpf());
			if (comparaCpf == 0) {
				int comparaNome = Collator.getInstance().compare(pessoaAtual.getNome(), outraPessoa.getNome());
				if (comparaNome == 0) {
					return 0;
				} else {
					return comparaNome;
				}
			} else {
				return comparaCpf;
			}
		} else {
			return comparaRg;
		}
	}

}
