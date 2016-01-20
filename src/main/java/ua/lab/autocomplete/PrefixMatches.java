/*
 *PrefixMatches
 *
 *Ver.
 *
 *18 янв. 2016
 *
 *© I.Gritsyk 2016. 
 */
package ua.lab.autocomplete;

import java.util.List;
import java.util.stream.Collectors;

import ua.lab.autocomplete.trie.Trie;

/**
 * @author I.Gritsyk
 *
 */
public class PrefixMatches {
	Trie trie;

	/**
	 * 
	 * @param trie
	 */
	public PrefixMatches(Trie trie) {
		this.trie = trie;
	}

	/**
	 * provides in-memory dictionary
	 * 
	 * @param string
	 *            or array of string
	 * @return
	 */
	public int add(String... strings) {
		int numberItemAdded = 0;
		for (String str : strings) {
			String[] arrstr = str.split(" ");
			for (String s : arrstr) {
				if (str.length() > 2) {
					trie.add(s, s.length());
					numberItemAdded++;
				}
			}
		}

		return numberItemAdded;
	}

	
	public boolean contains(String word) {

		return trie.contains(word);
	}

	public boolean delete(String word) {
		return trie.delete(word);
	}

	public int size() {
		return trie.size();
	}

	public Iterable<String> wordsWithPrefix(String pref, int k) {
		List<String> list = null;
		List<String> l = (List<String>) trie.wordsWithPrefix(pref);
		if (pref.length() >= 2 && k >= 1)
			list = l.stream().filter(s -> s.length() <= pref.length() + k).collect(Collectors.toList());
		return list;
	}

	public Iterable<String> wordsWithPrefix(String pref) {
		return wordsWithPrefix(pref, 3);
	}

}
