package com.example.demo.util;

public class StringUtil {

	public static final String NUMERIC_CHARS = "0123456789";
	public static final String NON_ALPHANUMERIC_CHARATERS = "[^A-Za-z0-9]";

	public static String replaceSpecialAndSpace(String string) {
		string = string.replaceAll("[ÂÀÁÄÃ]", "A");
		string = string.replaceAll("[âãàáä]", "a");
		string = string.replaceAll("[ÊÈÉË]", "E");
		string = string.replaceAll("[êèéë]", "e");
		string = string.replaceAll("[ÎÍÌÏ]", "I");
		string = string.replaceAll("[îíìï]", "i");
		string = string.replaceAll("[ÔÕÒÓÖ]", "O");
		string = string.replaceAll("[ôõòóö]", "o");
		string = string.replaceAll("[ÛÙÚÜ]", "U");
		string = string.replaceAll("[ûúùü]", "u");
		string = string.replaceAll("Ç", "C");
		string = string.replaceAll("ç", "c");
		string = string.replaceAll("[ýÿ]", "y");
		string = string.replaceAll("Ý", "Y");
		string = string.replaceAll("ñ", "n");
		string = string.replaceAll("Ñ", "N");
		string = string.replaceAll("['<>\\|/]", "");
		string = string.replaceAll(" ", "_");
		return string;
	}

	public static String replaceSpecial(String string) {
		string = string.replaceAll("[ÂÀÁÄÃ]", "A");
		string = string.replaceAll("[âãàáä]", "a");
		string = string.replaceAll("[ÊÈÉË]", "E");
		string = string.replaceAll("[êèéë]", "e");
		string = string.replaceAll("[ÎÍÌÏ]", "I");
		string = string.replaceAll("[îíìï]", "i");
		string = string.replaceAll("[ÔÕÒÓÖ]", "O");
		string = string.replaceAll("[ôõòóö]", "o");
		string = string.replaceAll("[ÛÙÚÜ]", "U");
		string = string.replaceAll("[ûúùü]", "u");
		string = string.replaceAll("Ç", "C");
		string = string.replaceAll("ç", "c");
		string = string.replaceAll("[ýÿ]", "y");
		string = string.replaceAll("Ý", "Y");
		string = string.replaceAll("ñ", "n");
		string = string.replaceAll("Ñ", "N");
		string = string.replaceAll("['<>\\|/]", "");
		return string;
	}

	public static String replace(String source, String pattern, String replace) {
		int s = 0;
		int e = 0;
		int pl = pattern.length();
		StringBuilder result = new StringBuilder();
		while ((e = source.indexOf(pattern, s)) >= 0) {
			result.append(source.substring(s, e));
			result.append(replace);
			s = e + pl;
		}
		result.append(source.substring(s));
		return result.toString();
	}

	public static String overlay(String source, String overlay, int start, int end) {
		if (source == null)
			return null;
		if (overlay == null)
			overlay = "";
		int length = source.length();
		if (start < 0)
			start = 0;
		if (start > length)
			start = length;
		if (end < 0)
			end = 0;
		if (end > length)
			end = length;
		if (start > end) {
			int temp = start;
			start = end;
			end = temp;
		}
		return new StringBuilder(length + start - end + overlay.length() + 1).append(source.substring(0, start)).append(overlay)
				.append(source.substring(end)).toString();
	}

	public static String removeChars(String source, String charsToRemove) {
		StringBuilder sb = new StringBuilder(source.length());
		for (int i = 0; i < source.length(); i++) {
			char c = source.charAt(i);
			if (charsToRemove.indexOf(c) == -1)
				sb.append(c);
		}
		return sb.toString();
	}

	/**
	 * Este método elimina todos os caracteres não numéricos
	 */
	public static String removeNonNumericChars(String source) {
		return preserveChars(source, NUMERIC_CHARS);
	}
	
	public static String removeNonAlphanumericChars(String source) {
		source = source.replace("-", "");
		return source.replace(NON_ALPHANUMERIC_CHARATERS, "");
	}

	public static String preserveChars(String source, String charsToPreserve) {
		StringBuilder sb = new StringBuilder(source.length());
		for (int i = 0; i < source.length(); i++) {
			char c = source.charAt(i);
			if (charsToPreserve.indexOf(c) != -1)
				sb.append(c);
		}
		return sb.toString();
	}

	public static boolean containOnyTheseChars(String source, String chars) {
		for (int i = 0; i < source.length(); i++) {
			char c = source.charAt(i);
			if (chars.indexOf(c) == -1)
				return false;
		}
		return true;
	}

	public static boolean isNumeric(String source) {
		return isNumeric(source, false);
	}

	public static boolean isNumeric(String source, boolean signIsValid) {
		if (isEmpty(source))
			return false;
		if (signIsValid && source.charAt(0) == '-')
			return containOnyTheseChars(source.substring(1), NUMERIC_CHARS);
		else
			return containOnyTheseChars(source, NUMERIC_CHARS);
	}

	public static String fill(String source, int quantity) {
		StringBuilder sb = new StringBuilder(quantity);
		for (int i = 1; i <= quantity; i++) {
			sb.append(source);
		}
		return sb.toString();
	}

	public static String center(String source, String str, int length) {
		if (source.length() >= length)
			return source.substring(0, length);
		int l = (length - source.length()) / 2;
		int r = (length - source.length() - l);
		String left = fill(str, l).substring(0, l);
		String right = fill(str, r).substring(0, r);
		return new StringBuilder(length).append(left).append(source).append(right).toString();
	}

	public static String insertLeftString(String source, String leftString, int length) {
		String str = fill(leftString, length) + source;
		return str.substring(str.length() - length, str.length());
	}

	public static String insertRightString(String source, String rightString, int length) {
		return (source + fill(rightString, length)).substring(0, length);
	}

	public static String justify(String source, String str, int length, int leftCenterRight) {
		if (leftCenterRight == -3)
			return insertLeftString(source, str, length);
		else if (leftCenterRight == -2)
			return center(source, str, length);
		else
			return insertRightString(source, str, length);
	}

	public static String justify(String source, String str, int length, char leftCenterRight) {
		if (leftCenterRight == 'r')
			return insertLeftString(source, str, length);
		else if (leftCenterRight == 'c')
			return center(source, str, length);
		else
			return insertRightString(source, str, length);
	}

	public static String substringBetween(String str, String open, String close) {
		return substringBetween(str, open, close, 0);
	}

	public static String substringBetween(String str, String open, String close, int fromIndex) {
		if (str == null || open == null || close == null) {
			return null;
		}
		int start = str.indexOf(open, fromIndex);
		if (start != -1) {
			int end = str.indexOf(close, start + open.length());
			if (end != -1) {
				return str.substring(start + open.length(), end);
			}
		}
		return null;
	}

	public static String substringFlex(String source, int beginIndex, int endIndex) {
		if (source == null)
			return "";
		int length = source.length();
		if (beginIndex < 0 || beginIndex >= length)
			return "";
		if (endIndex > length)
			endIndex = length;
		if (endIndex < beginIndex)
			return "";
		return source.substring(beginIndex, endIndex);
	}

	public static String toFirstLetterUpperCase(String source) {
		int l = source.length();
		if (l > 0) {
			return source.substring(0, 1).toUpperCase() + (l > 1 ? source.substring(1) : "");
		} else {
			return source;
		}
	}

	public static String toFirstLetterUpperCase(String source, String regex) {
		if (regex == null) {
			return toFirstLetterUpperCase(source);
		} else {
			StringBuilder sb = new StringBuilder();
			String words[] = source.split(regex);
			for (int i = 0; i < words.length; i++) {
				if (i > 0) {
					sb.append(regex);
				}
				sb.append(toFirstLetterUpperCase(words[i]));
			}
			return sb.toString();
		}
	}

	public static String toFirstLetterLowerCase(String source) {
		int l = source.length();
		if (l > 0) {
			return source.substring(0, 1).toLowerCase() + (l > 1 ? source.substring(1) : "");
		} else {
			return source;
		}
	}

	public static String toFirstLetterLowerCase(String source, String regex) {
		if (regex == null) {
			return toFirstLetterLowerCase(source);
		} else {
			StringBuilder sb = new StringBuilder();
			String words[] = source.split(regex);
			for (int i = 0; i < words.length; i++) {
				if (i > 0) {
					sb.append(regex);
				}
				sb.append(toFirstLetterLowerCase(words[i]));
			}
			return sb.toString();
		}
	}

	public static boolean isNull(String str) {
		return (str == null);
	}

	public static boolean isEmpty(String str) {
		return !(str != null && str.length() > 0);
	}

	public static boolean isNotEmpty(String str) {
		return (str != null && str.length() > 0);
	}

	public static String removePrefixFromListValues(String listValues, char separator) {
		String values[] = listValues.split(",");
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < values.length; i++) {
			if (i > 0)
				sb.append(", ");
			int pos = values[i].indexOf(separator);
			if (pos != -1)
				sb.append(values[i].substring(pos + 1));
		}
		return sb.toString();
	}

	public static int countChar(String source, char charToCount) {
		int count = 0;
		int length = source.length();
		for (int i = 0; i < length; i++) {
			if (source.charAt(i) == charToCount)
				count++;
		}
		return count;
	}

	public static char lastChar(String source) {
		return (source.charAt(source.length() - 1));
	}

	public static final int multiplyDigits(String source, int[] multipliers) {
		int sum = 0;
		for (int i = 0; i < source.length(); i++) {
			sum += Integer.parseInt("" + source.charAt(i)) * multipliers[i];
		}
		return sum;
	}

	public static String concat(String separator, String... source) {
		StringBuilder sb = new StringBuilder();
		for (String str : source) {
			if (!StringUtil.isEmpty(str)) {
				if (sb.length() > 0)
					sb.append(separator);
				sb.append(str);
			}
		}
		return sb.toString();
	}

	public static String textAsSingleLine(String text) {
		return replace(text, "\n", " ");
	}

	public static String textAsSingleLine(String text, String replace) {
		return replace(text, "\n", replace);
	}

	public static boolean isMale(char letter) {
		switch (letter) {
		case 'É':
			return true;
		case 'é':
			return true;
		case 'O':
			return true;
		case 'o':
			return true;
		case 'U':
			return true;
		case 'u':
			return true;
		case 'N':
			return true;
		case 'n':
			return true;
		}
		return false;
	}

	public static boolean isFemale(char letter) {
		switch (letter) {
		case 'a':
			return true;
		case 'e':
			return true;
		}
		return false;
	}

	public static boolean containsOnlyOneCharacter(String source) {
		if (isEmpty(source))
			return false;
		
		for (int index = 0; index < source.length(); index++) {
			if (source.charAt(0) != source.charAt(index)) 
				return false;
		}
		
		return true;
	}

	public static String capitalize(String source) {
		boolean afterSpace = true;
		for (int index = 0; index < source.length(); index++) {
			String prefix = source.substring(0, index);
			String character = "";
			String suffix  = (source.length() > (index + 1) ? source.substring(index + 1) : "");
			
			if (afterSpace) {
				character = source.substring(index, index + 1).toUpperCase();
			} else {
				character = source.substring(index, index + 1).toLowerCase();
			}
			
			source = prefix + character + suffix;
			
			afterSpace = source.charAt(index) == ' ';
		}

		return source;
	}
	
	public String removeZeroLeft(String value) {
        String x = null;
        if(value.startsWith("0")){
            x = value.replaceAll("0", "").replaceAll("", "");
        } else {
            x = value; 
        }
        return x;
 }
	
}
