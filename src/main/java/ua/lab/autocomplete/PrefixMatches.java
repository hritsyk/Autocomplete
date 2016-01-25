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

import java.util.ArrayList;
import java.util.List;

import ua.lab.autocomplete.trie.Trie;

/**
 * @author I.Gritsyk
 *
 */
public class PrefixMatches {
	private Trie trie;

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

	/**
	 * check if the word exist in dictionary
	 * 
	 * @param word
	 * @return
	 */
	public boolean contains(String word) {

		return trie.contains(word);
	}

	/**
	 * delete word from dictionary
	 * 
	 * @param word
	 * @return
	 */
	public boolean delete(String word) {
		return trie.delete(word);
	}

	/**
	 * size of dictionary
	 * 
	 * @return
	 */
	public int size() {
		return trie.size();
	}

	/**
	 * return set of words different length, begins from min length to lenght+k
	 * 
	 * @param pref
	 * @param k
	 * @return
	 */
	public Iterable<String> wordsWithPrefix(String pref, int k) {
		List<String> result=new ArrayList<>();
		if(pref.length()<2){
			return null;
		}
		
		Iterable<String> source=trie.wordsWithPrefix(pref);
		for(String s: source){
			if(s.length()<=pref.length()+k){
				result.add(s);
			}else{
				return result;
			}
			
		}
		
		return result;
	}

	/**
	 * return set of words different length, begins from min length to
	 * lenght+k=3
	 * 
	 * @param pref
	 * @return
	 */
	public Iterable<String> wordsWithPrefix(String pref) {
		return wordsWithPrefix(pref, 3);
	}
	
	
	

}
