/*
 *RWayTriePrevVersion
 *
 *Ver.
 *
 *18 янв. 2016
 *
 *© I.Gritsyk 2016. 
 */
package ua.lab.autocomplete.trie;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author I.Gritsyk
 *
 */
public class RWayTrie implements Trie {
	
	
	private static final int R = 26; // number of references in nods (lowercase
										// character ASCII)
	private static final int ASCII_LOW_CASE_A = 'a';

	private Node root;
	
	private int size; // number of words in trie

	private int modCount=0;
	// R-way trie node
	private static class Node {
		private Integer value;
		private Node[] next = new Node[R];
	}

	/**
	 * Initializes an empty string symbol table.
	 */
	public RWayTrie() {
	}

	/*
	 * @see ua.lab.autocomplete.Trie#add(java.lang.String, java.lang.Object)
	 */
	@Override
	public void add(String term, Integer weight) {
		if (!contains(term)){
			root = add(root, term, weight, 0);
			modCount++;
		}	
	}

	private Node add(Node x, String word, Integer weight, int d) {
		if (x == null)
			x = new Node();
		if (d == word.length()) {
			if (x.value == null) {
				size++;
			}
			x.value = weight;
			return x;
		}
		int c = word.charAt(d) - ASCII_LOW_CASE_A;
		x.next[c] = add(x.next[c], word, weight, d + 1);
		return x;
	}

	/*
	 * @see ua.lab.autocomplete.Trie#contains(java.lang.String)
	 */
	@Override
	public boolean contains(String word) {

		return get(word) != null;
	}

	/*
	 * @see ua.lab.autocomplete.Trie#delete(java.lang.String)
	 */
	@Override
	public boolean delete(String word) {
		int prevSize = size;
		root = delete(root, word, 0);
		modCount--;
		return prevSize != size;
	}

	private Node delete(Node x, String word, int d) {
		if (x == null)
			return null;
		if (d == word.length()) {
			if (x.value != null)
				size--;
			x.value = null;
		} else {
			int c = word.charAt(d) - ASCII_LOW_CASE_A;
			x.next[c] = delete(x.next[c], word, d + 1);
		}

		// remove subtrie rooted at x if it is completely empty
		if (x.value != null)
			return x;
		for (int i = 0; i < R; i++)
			if (x.next[i] != null)
				return x;
		return null;
	}

	/*
	 * @see ua.lab.autocomplete.Trie#words()
	 */
	@Override
	public Iterable<String> words() {
		return wordsWithPrefix("");
	}

	/*
	 * @see ua.lab.autocomplete.Trie#wordsWithPrefix(java.lang.String)
	 */
	@Override
	public Iterable<String> wordsWithPrefix(String pref) {
		if(pref.length()<2) return null;

		return new Iterable<String>() {

			@Override
			public Iterator<String> iterator() {
				return new TrieIterator(pref);
			}
		};
	}

	/*
	 * @see ua.lab.autocomplete.Trie#size()
	 */
	@Override
	public int size() {
		return size;
	}

	private Integer get(String key) {
		Node x = get(root, key, 0);
		if (x == null)
			return null;
		return x.value;
	}

	private Node get(Node x, String key, int d) {
		if (x == null)
			return null;
		if (d == key.length())
			return x;
		int i = key.charAt(d) - ASCII_LOW_CASE_A;
		return get(x.next[i], key, d + 1);
	}

	private char charByCode(int i) {
		return (char) (i + ASCII_LOW_CASE_A);
	}

	private boolean ifArrayNotEmpty(Node[] nodes, int from) {
		for (int i = from; i < nodes.length; i++) {
			if (nodes[i] != null) {
				return true;
			}
		}
		return false;
	}

	private class TrieIterator implements Iterator<String> {
		
		private String pref;
		private Node nodeLikePref;
		private String currentWord;
		private int expectedModCoun;

		private Deque<String> words = new LinkedList<>();
		private Deque<Node[]> path = new LinkedList<>();

		/**
		 * @param pref
		 */
		public TrieIterator(String pref) {
			expectedModCoun=modCount;
			this.pref = pref;
			nodeLikePref = get(root, pref, 0);

			if (nodeLikePref != null) {
				if (ifArrayNotEmpty(nodeLikePref.next, 0)) {
					path.offer(nodeLikePref.next);
					words.offer(pref);
				}

				if (nodeLikePref.value != null) {
					currentWord = pref;
				}
			}
		}

		@Override
		public boolean hasNext() {
			return !path.isEmpty() || currentWord != null;
		}

		@Override
		public String next() {
			if(expectedModCoun!=modCount) throw new ConcurrentModificationException();
			
			String word = null;
			if (currentWord != null) {
				word = currentWord;
				currentWord = null;
			} else {
				word = getWord();
			}

			return word;
		}

		private String getWord() {
			while (!path.isEmpty()) {
				Node[] lastNodes = path.poll();
				String lastWord = words.poll();

				for (int i = 0; i < R; i++) {

					if (lastNodes[i] != null) {
						if (ifArrayNotEmpty(lastNodes[i].next, 0)) {
							path.offer(lastNodes[i].next);
							words.offer(lastWord + charByCode(i));
						}
						
						Node tmp = lastNodes[i];
						lastNodes[i] = null;

						if (tmp.value != null) {
							if ((i != R - 1) && ifArrayNotEmpty(lastNodes, i)) {
								path.addFirst(lastNodes);
								words.addFirst(lastWord);
							}
							return lastWord + charByCode(i);
						}

					}

				}
			}
			return null;
		}

	}

}
