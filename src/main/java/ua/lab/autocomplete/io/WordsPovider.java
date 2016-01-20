/*
 *WordsPovider
 *
 *Ver.
 *
 *19 янв. 2016
 *
 *© I.Gritsyk 2016. 
 */
package ua.lab.autocomplete.io;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

/**
 * @author I.Gritsyk
 *
 */
public class WordsPovider {
	private String filename;
	private String encoding = "UTF8";

	/**
	 * constructs file with given file name
	 * 
	 * @param filename
	 */
	public WordsPovider(String filename) {
		this.filename = filename;
	}

	/**
	 *
	 * @return string with text from file
	 */
	public String[] getKeys() {

		String tmpString = "";
		StringBuilder simplyText = new StringBuilder(tmpString);

		try (InputStreamReader ins = new InputStreamReader(new FileInputStream(filename), encoding)) {
			BufferedReader br = new BufferedReader(ins);
			while ((tmpString = br.readLine()) != null) {
				simplyText.append(tmpString);
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		Pattern pattern = Pattern.compile("[^a-z]+");
		String[] words = pattern.split(simplyText.toString().toLowerCase());

		return words;
	}

}
