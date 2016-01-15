package org.fxapps.listtocsv;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;
import static java.util.stream.Collectors.*;
import java.util.stream.Stream;

public class ListToCSV {

	public static String SEPARATOR = ",";
	private static boolean QUOTE = true;
	private static boolean HEADER = false;

	public static <T extends Object> String toCSV(List<T> l) {
		return toCSV(l, Collections.emptyList(), SEPARATOR, HEADER, QUOTE);
	}

	public static <T extends Object> String toCSV(List<T> l, List<String> ignore) {
		return toCSV(l, ignore, SEPARATOR, HEADER, QUOTE);
	}

	public static <T extends Object> String toCSV(List<T> l,
			List<String> ignore, String separator) {
		return toCSV(l, ignore, separator, HEADER, QUOTE);
	}

	public static <T extends Object> String toCSV(List<T> l,
			List<String> ignore, String separator, boolean header) {
		return toCSV(l, ignore, separator, header, QUOTE);
	}

	public static <T extends Object> String toCSV(List<T> l,
			List<String> ignore, String sep, boolean header, boolean quote) {
		if (l == null || l.size() == 0) {
			return "";
		}
		String lineSeparator = System.getProperty("line.separator");
		String headerStr = "";
		if (header) {
			headerStr = filter(l.get(0), ignore).stream().map(f -> {
				if (quote) {
					f = "\"" + f + "\"";
				}
				return f;
			}).collect(joining(sep));
			headerStr += lineSeparator;
		}
		String CSV = l.stream().map(e -> {
			List<String> fields = filter(e, ignore);
			String line = "";
			for (String f : fields) {
				String v = invokeGet(e, f);
				if (quote) {
					v = sep + "\"" + v + "\"";
				} else {
					v = sep + v;
				}
				line += v;
			}
			line = line.substring(1, line.length());
			return lineSeparator + line;
		}).collect(joining());
		return headerStr + CSV.substring(1, CSV.length());
	}

	private static <T> List<String> filter(T c, List<String> ignore) {
		return Stream.of(c.getClass().getDeclaredFields()).map(Field::getName)
				.filter(f -> !ignore.contains(f)).collect(toList());
	}

	private static <T> String invokeGet(T c, String field) {
		String mName = "get" + Character.toUpperCase(field.charAt(0))
				+ field.substring(1);
		try {
			Method m = c.getClass().getMethod(mName);
			return String.valueOf(m.invoke(c));
		} catch (Exception e) {
			throw new Error(e);
		}
	}

}