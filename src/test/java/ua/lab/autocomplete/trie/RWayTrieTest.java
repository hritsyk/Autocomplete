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
	public void add_WordWhichAlreadyExist_SameSizeReturned() throws Exception {

		trie.add("abc", 3);
		trie.add("abcd", 4);
		int actualSize = trie.size();
		trie.add("abcd", 4);
		int expectedSize = trie.size();

		assertEquals(expectedSize, actualSize);
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
	public void delete_ExistWordDeleted_TrueReturned() throws Exception {

		trie.add("abc", 3);
		trie.add("abcd", 4);

		boolean expResult = trie.delete("abc");

		assertTrue(expResult);
	}
	
	@Test
	public void delete_WordIsNotExistDeleted_FalseReturned() throws Exception {

		trie.add("abc", 3);
		trie.add("abcd", 4);

		boolean expResult = trie.delete("zxv");

		assertFalse(expResult);
	}

	@Test
	public void wordWithPrefix_TestIterator() throws Exception {

		trie.add("abc", 3);
		trie.add("abcd", 4);

		Iterator<String> iter = trie.wordsWithPrefix("ab").iterator();
		assertTrue(iter.hasNext());
		assertEquals("abc", iter.next());
		assertTrue(iter.hasNext());
		assertEquals("abcd", iter.next());
		assertFalse(iter.hasNext());
		assertEquals(null, iter.next());

	}
	
	@Test
	public void wordWithPrefix_WordIsNotExist_IteratorNextNullReturned() throws Exception {
		String testPref="zxv";
		
		trie.add("abc", 3);
		trie.add("abcd", 4);

		Iterator<String> iter = trie.wordsWithPrefix(testPref).iterator();
		assertEquals(null, iter.next());

	}
	
	@Test
	public void wordWithPrefix_PrefIsNotExist_IteratorHasNextFalseReturned() throws Exception {

		String testPref="zxy";
		
		trie.add("abc", 3);
		
		Iterator<String> iter = trie.wordsWithPrefix(testPref).iterator();
		assertFalse(iter.hasNext());
	}
	
	

	@Test
	public void size_OneWordIsExist_OneReturned() throws Exception {
		int expectedSize=1;
		
		trie.add("abcd", 4);

		assertEquals(expectedSize, trie.size());
	}

	@Test
	public void size_ZeroElement_ZeroReturned() throws Exception {
		int expectedSize=0;
		
		assertEquals(expectedSize, trie.size());
	}

}
