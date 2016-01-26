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

import java.util.Iterator;

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
		if (pref.length() < 2)
			return null;

		int maxWordLength = pref.length() + k;

		Iterator<String> trieIterator = trie.wordsWithPrefix(pref).iterator();

		return new Iterable<String>() {
			String tmpWord = getNextFromTrie();

			@Override
			public Iterator<String> iterator() {

				return new Iterator<String>() {

					@Override
					public boolean hasNext() {

						return tmpWord != null;
					}

					@Override
					public String next() {
						String next = tmpWord;
						tmpWord = getNextFromTrie();
						return next;
					}
				};
			}

			private String getNextFromTrie() {
				String word = null;
				if (trieIterator.hasNext()) {
					word = trieIterator.next();
					word = word.length() <= maxWordLength ? word : null;
				}
				return word;
			}
		};

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
