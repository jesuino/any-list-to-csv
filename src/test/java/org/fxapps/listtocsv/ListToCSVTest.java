package org.fxapps.listtocsv;

import static org.fxapps.listtocsv.ListToCSV.SEPARATOR;
import static org.fxapps.listtocsv.ListToCSV.toCSV;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

public class ListToCSVTest {

	String CSV = "\"William\",\"Siqueira\",\"27\"\n\"Luana\",\"Camara\",\"26\"\n\"Antonio\",\"Siqueira\",\"0\"";
	String CSV_HEADER = "\"name\",\"surname\",\"age\"\n\"William\",\"Siqueira\",\"27\"\n\"Luana\",\"Camara\",\"26\"\n\"Antonio\",\"Siqueira\",\"0\"";
	String CSV_HEADER_NO_QUOTE = "name,surname,age\nWilliam,Siqueira,27\nLuana,Camara,26\nAntonio,Siqueira,0";
	String CSV_HEADER_EXCLUDING = "\"surname\"\n\"Siqueira\"\n\"Camara\"\n\"Siqueira\"";
	String CSV_EXCLUDING_FIELDS = "\"Siqueira\"\n\"Camara\"\n\"Siqueira\"";
	String CSV_OTHER_SEPARATOR = "\"William\";\"Siqueira\";\"27\"\n\"Luana\";\"Camara\";\"26\"\n\"Antonio\";\"Siqueira\";\"0\"";
	String CSV_NO_QUOTES = "William,Siqueira,27\nLuana,Camara,26\nAntonio,Siqueira,0";

	@Test
	public void toCSVTest() {
		List<Person> l = Arrays.asList(new Person("William", "Siqueira", 27),
				new Person("Luana", "Camara", 26), new Person("Antonio",
						"Siqueira", 0));
		List<String> empty = Collections.emptyList();
		List<String> exclude = Arrays.asList("name", "age");
		String csv = toCSV(l);
		String csvHeader = toCSV(l, empty, SEPARATOR, true);
		String csvHeaderNoQuote = toCSV(l, empty, SEPARATOR, true, false);
		String csvHeaderExcluding = toCSV(l, exclude, SEPARATOR, true);
		String csvExcluding = toCSV(l, exclude);
		String csvOtherSeparator = toCSV(l, empty, ";");
		String csvNoQuotes = toCSV(l, empty, ",", false, false);
		assertEquals(CSV, csv);
		assertEquals(CSV_EXCLUDING_FIELDS, csvExcluding);
		assertEquals(CSV_OTHER_SEPARATOR, csvOtherSeparator);
		assertEquals(csvNoQuotes, CSV_NO_QUOTES);
		assertEquals(CSV_HEADER, csvHeader);
		assertEquals(CSV_HEADER_NO_QUOTE, csvHeaderNoQuote);
		assertEquals(CSV_HEADER_EXCLUDING, csvHeaderExcluding);
	}
	
}