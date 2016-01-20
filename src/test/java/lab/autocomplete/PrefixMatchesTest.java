/*
 *PrefixMatchesTest
 *
 *Ver.
 *
 *19 янв. 2016
 *
 *© I.Gritsyk 2016. 
 */
package lab.autocomplete;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
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
		trie=new RWayTrie();
		prefixMatches = new PrefixMatches(trie);
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * Test method for {@link ua.lab.autocomplete.PrefixMatches#add(java.lang.String[])}.
	 */
	@Test
	public void add_LessThen3Symbols_DidNotAdd() {
		String word="ab";
		assertEquals("addition word less then 3 simbols",0, prefixMatches.add(word));
	}

	/**
	 * Test method for {@link ua.lab.autocomplete.PrefixMatches#add(java.lang.String[])}.
	 */
	@Test
	public void add_MoreThen2Symbols_SuccessfulyAdded() {
		String word="abc";
		assertEquals("addition word more then 2 simbols",1, prefixMatches.add(word));
	}
	
	/**
	 * Test method for {@link ua.lab.autocomplete.PrefixMatches#contains(java.lang.String)}.
	 */
	@Test
	public void contains_WordPresentInTrie_TrueReturned() {
		when(trie.contains("abc")).thenReturn(true);
		assertEquals(true, prefixMatches.contains("abc"));
	}

	/**
	 * Test method for {@link ua.lab.autocomplete.PrefixMatches#contains(java.lang.String)}.
	 */
	@Test
	public void contains_WordIsNotInTrie_FalseReturned() {
		when(trie.contains("abc")).thenReturn(false);
		assertEquals(false, prefixMatches.contains("abc"));
	}
	
	/**
	 * Test method for {@link ua.lab.autocomplete.PrefixMatches#contains(java.lang.String)}.
	 */
	@Test
	public void contains_2SymbolWord_FalseReturned() {
		assertEquals(false, prefixMatches.contains("ab"));
	}
	
	/**
	 * Test method for {@link ua.lab.autocomplete.PrefixMatches#delete(java.lang.String)}.
	 */
	@Test
	public void delete_2SymbolWord_FalseReturned() {
		assertEquals(false, prefixMatches.contains("ab"));
	}

	/**
	 * Test method for {@link ua.lab.autocomplete.PrefixMatches#size()}.
	 */
	@Test
	public void size_OneItemInStructure_1Returned() {
		when(trie.size()).thenReturn(1);
		assertEquals(1, prefixMatches.size());
	}
	
	/**
	 * Test method for {@link ua.lab.autocomplete.PrefixMatches#size()}.
	 */
	@Test
	public void size_StructureIsEmpty_0Returned() {
		when(trie.size()).thenReturn(0);
		assertEquals(0, prefixMatches.size());
	}

	/**
	 * Test method for {@link ua.lab.autocomplete.PrefixMatches#wordsWithPrefix(java.lang.String, int)}.
	 */
	@Test
	public void WordsWithPrefixString_ifWordExist_NotEmptyIterableReturned() {
		List<String> ls=new ArrayList<String>();
		ls.add("abc");
		ls.add("abcd");
		ls.add("abcde");
		when(trie.wordsWithPrefix("abc")).thenReturn(ls);
		assertEquals(ls, prefixMatches.wordsWithPrefix("abc"));
	}

	/**
	 * Test method for {@link ua.lab.autocomplete.PrefixMatches#wordsWithPrefix(java.lang.String)}.
	 */
	@Test
	public void WordsWithPrefixString_ifWordExist_EmptyIterableReturned() {
		
		List<String> ls=new ArrayList<String>();
		when(trie.wordsWithPrefix("abc")).thenReturn(ls);
		assertEquals(ls, prefixMatches.wordsWithPrefix("abc"));
		
	}
	/**
	 * Test method for {@link ua.lab.autocomplete.PrefixMatches#wordsWithPrefix(java.lang.String)}.
	 */
	@Test
	public void WordsWithPrefixStringInt_ifWordExist_IterableWithKPlus1WordsReturned() {
		
		List<String> ls=new ArrayList<String>();
		ls.add("abc");
		ls.add("abcd");
		ls.add("abcv");
		when(trie.wordsWithPrefix("abc")).thenReturn(ls);
		assertEquals(ls, prefixMatches.wordsWithPrefix("abc",1));
		
	}
	

}
