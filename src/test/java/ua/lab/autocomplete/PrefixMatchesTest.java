/*
 *PrefixMatchesTest
 *
 *Ver.
 *
 *19 янв. 2016
 *
 *© I.Gritsyk 2016. 
 */
package ua.lab.autocomplete;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import ua.lab.autocomplete.PrefixMatches;
import ua.lab.autocomplete.trie.RWayTrie;
import ua.lab.autocomplete.trie.Trie;

/**
 * @author I.Gritsyk
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class PrefixMatchesTest {
	@Mock
	private Trie trie;
	@InjectMocks
	private PrefixMatches prefixMatches;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		trie = new RWayTrie();
		prefixMatches = new PrefixMatches(trie);
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * Test method for
	 * {@link ua.lab.autocomplete.PrefixMatches#add(java.lang.String[])}.
	 */
	@Test
	public void add_LessThen3Symbols_DidNotAdd() {
		String testWord = "ab";
		assertEquals("addition word less then 3 simbols", 0, prefixMatches.add(testWord));
	}

	/**
	 * Test method for
	 * {@link ua.lab.autocomplete.PrefixMatches#add(java.lang.String[])}.
	 */
	@Test
	public void add_MoreThen2Symbols_SuccessfulyAdded() {
		String word = "abc";
		assertEquals("addition word more then 2 simbols", 1, prefixMatches.add(word));
	}
	
	

	/**
	 * Test method for
	 * {@link ua.lab.autocomplete.PrefixMatches#contains(java.lang.String)}.
	 */
	@Test
	public void contains_WordPresentInTrie_TrueReturned() {
		when(trie.contains("abc")).thenReturn(true);
		assertEquals(true, prefixMatches.contains("abc"));
	}

	/**
	 * Test method for
	 * {@link ua.lab.autocomplete.PrefixMatches#contains(java.lang.String)}.
	 */
	@Test
	public void contains_WordIsNotInTrie_FalseReturned() {
		when(trie.contains("abc")).thenReturn(false);
		assertEquals(false, prefixMatches.contains("abc"));
	}

	/**
	 * Test method for
	 * {@link ua.lab.autocomplete.PrefixMatches#contains(java.lang.String)}.
	 */
	@Test
	public void contains_2SymbolWord_FalseReturned() {
		assertEquals(false, prefixMatches.contains("ab"));
	}

	/**
	 * Test method for
	 * {@link ua.lab.autocomplete.PrefixMatches#delete(java.lang.String)}.
	 */
	@Test
	public void delete_2SymbolWord_FalseReturned() {
		assertEquals(false, prefixMatches.delete("ab"));
	}
	
	/**
	 * Test method for
	 * {@link ua.lab.autocomplete.PrefixMatches#delete(java.lang.String)}.
	 */
	@Test
	public void delete_3orMoreSymbolWordIfWordExist_TrueReturned() {
		String testWord="abc";
		when(trie.delete(testWord)).thenReturn(true);
		assertEquals(true, prefixMatches.delete(testWord));
	}

	/**
	 * Test method for
	 * {@link ua.lab.autocomplete.PrefixMatches#delete(java.lang.String)}.
	 */
	@Test
	public void delete_3orMoreSymbolWordIfWordIsNotExist_falseReturned() {
		String testWord="abc";
		when(trie.delete(testWord)).thenReturn(false);
		assertEquals(false, prefixMatches.delete(testWord));
	}

	
	/**
	 * Test method for {@link ua.lab.autocomplete.PrefixMatches#size()}.
	 */
	@Test
	public void size_OneItemInStructure_1Returned() {
		int size=1;
		
		when(trie.size()).thenReturn(size);
		assertEquals(size, prefixMatches.size());
	}

	/**
	 * Test method for {@link ua.lab.autocomplete.PrefixMatches#size()}.
	 */
	@Test
	public void size_StructureIsEmpty_0Returned() {
		int size=0;
		
		when(trie.size()).thenReturn(size);
		assertEquals(size, prefixMatches.size());
	}

	/**
	 * Test method for
	 * {@link ua.lab.autocomplete.PrefixMatches#wordsWithPrefix(java.lang.String, int)}
	 * .
	 */
	@Test
	public void wordsWithPrefixString_ifWordExist_IteratorHasNextTrueReturned() {
		String testPref="abc";
		
		List<String> ls = new ArrayList<String>();
		ls.add("abc");
		ls.add("abcd");
		ls.add("abcde");

		when(trie.wordsWithPrefix(testPref)).thenReturn(ls);
		assertEquals(true, prefixMatches.wordsWithPrefix(testPref).iterator().hasNext());
	}

	/**
	 * Test method for
	 * {@link ua.lab.autocomplete.PrefixMatches#wordsWithPrefix(java.lang.String, int)}
	 * .
	 */
	@Test
	public void wordsWithPrefixString_ifWordIsNotExist_IteratorHasNextFalseReturned() {
		String testPref="ab";
		
		Iterable<String> testIterable = new Iterable<String>() {

			@Override
			public Iterator<String> iterator() {
				return new Iterator<String>() {

					@Override
					public boolean hasNext() {
						return false;
					}

					@Override
					public String next() {
						return null;
					}
				};
			}
		};
		when(trie.wordsWithPrefix(testPref)).thenReturn(testIterable);
		assertEquals(false, prefixMatches.wordsWithPrefix(testPref).iterator().hasNext());
	}


	/**
	 * Test method for
	 * {@link ua.lab.autocomplete.PrefixMatches#wordsWithPrefix(java.lang.String)}
	 * .
	 */
	@Test
	public void WordsWithPrefixStringInt_ifPrefLengthLessThan2_NullReturned() {

		when(trie.wordsWithPrefix("a")).thenReturn(null);
		assertEquals(null, prefixMatches.wordsWithPrefix("a", 1));

	}

}
