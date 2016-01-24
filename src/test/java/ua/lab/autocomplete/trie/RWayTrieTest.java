/*
 *RWayTrieTest
 *
 *Ver.
 *
 *24 янв. 2016
 *
 *© I.Gritsyk 2016. 
 */
package ua.lab.autocomplete.trie;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

/**
 * @author I.Gritsyk
 *
 */
public class RWayTrieTest {

	private Trie trie;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		trie = new RWayTrie();
	}

	@Test
	public void add_TwoWords_Size2Returned() throws Exception {

		trie.add("abc", 3);
		trie.add("abcd", 4);

		int expectedSize = 2;
		int size = trie.size();

		assertEquals(expectedSize, size);
	}

	@Test
	public void contains_WordExist_TrueReturned() throws Exception {

		trie.add("abc", 3);
		trie.add("abcd", 4);

		boolean actualResult = trie.contains("abc");

		assertTrue(actualResult);
	}

	@Test
	public void contains_WordIsNotExist_FalseReturned() throws Exception {

		trie.add("abc", 3);
		trie.add("abcd", 4);

		boolean actualResult = trie.contains("zvb");

		assertFalse(actualResult);
	}

	@Test
	public void delete_1WordDeleted_LessSizeReturned() throws Exception {

		trie.add("abc", 3);
		trie.add("abcd", 4);
		int sizeBefor = trie.size();

		trie.delete("abc");

		int sizeAfter = trie.size();

		assertFalse(sizeAfter == sizeBefor);
	}
	
	 
	@Test
	public void wordWithPrefix_TestIterator() throws Exception {

		trie.add("abc", 3);
		trie.add("abcd", 4);

		Iterator<String> iter=trie.wordsWithPrefix("ab").iterator();
		assertTrue(iter.hasNext());
		assertEquals("abc", iter.next());
		assertTrue(iter.hasNext());
		assertEquals("abcd", iter.next());
		assertFalse(iter.hasNext());
		assertEquals(null, iter.next());

	}

}
