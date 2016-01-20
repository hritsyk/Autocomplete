/*
 *Trie
 *
 *Ver.
 *
 *18 янв. 2016
 *
 *© I.Gritsyk 2016. 
 */
package ua.lab.autocomplete.trie;

/**
 * @author I.Gritsyk
 *
 */
public interface Trie {
	/**
	 * adds word to dictionary
	 * @param term
	 * @param weight
	 */
    public void add(String term, Integer weight);

    /**
     * check if the word exist in dictionary
     * @param word
     * @return
     */
    public boolean contains(String word);

    /**
     * delete word from dictionary
     * @param word
     * @return
     */
    public boolean delete(String word);

    /**
     * return all words from dictionary
     * @return
     */
    public Iterable<String> words();

    /**
     * return words from dictionary which begin from given prefix-pref
     * @param pref
     * @return
     */
    public Iterable<String> wordsWithPrefix(String pref);

    /**
     * return size of dictionary
     * @return
     */
    public int size();
}
