/*
 *Main
 *
 *Ver.
 *
 *19 янв. 2016
 *
 *© I.Gritsyk 2016. 
 */
package ua.lab.autocomplete;

import ua.lab.autocomplete.io.WordsPovider;
import ua.lab.autocomplete.trie.RWayTrie;
import ua.lab.autocomplete.trie.Trie;

/**
 * @author I.Gritsyk
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		WordsPovider textProvider = new WordsPovider("words-333333.txt");
		String[] dictionary = textProvider.getKeys();
		Trie trie = new RWayTrie();
		PrefixMatches prefixMatches = new PrefixMatches(trie);
		prefixMatches.add(dictionary);

		for(String s: prefixMatches.wordsWithPrefix("ab",3)){
			System.out.println(s);
		}
		
	}

}
