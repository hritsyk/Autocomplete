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
    public void add(String term, Integer weight);

    public boolean contains(String word);

    public boolean delete(String word);

    public Iterable<String> words();

    public Iterable<String> wordsWithPrefix(String pref);

    public int size();
}
