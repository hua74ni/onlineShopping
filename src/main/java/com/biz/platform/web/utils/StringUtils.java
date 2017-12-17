package com.biz.platform.web.utils;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * TODO此处填写 class 信息
 *
 * @author
 */
public class StringUtils{

	private static final String FOLDER_SEPARATOR = "/";

	private static final char EXTENSION_SEPARATOR = '.';
	
	private static final String EMAIL_PATTERN = "^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,3})$";
	
	private static final Pattern KVP_PATTERN = Pattern.compile("([_.a-zA-Z0-9][-_.a-zA-Z0-9]*)[=](.*)"); //key value pair pattern.
	
	private static final Pattern COMMA_SPLIT_PATTERN  = Pattern.compile("\\s*[,]+\\s*");
	
	private static final Pattern INT_PATTERN = Pattern.compile("^\\d+$");
	
	private static final Pattern emailPattern = Pattern.compile(EMAIL_PATTERN);

	static final String[] SBC = { "，", "。", "；", "“", "”", "？", "！", "（", "）", "：", "——", "、" };

	static final String[] DBC = { ",", ".", ";", "\"", "\"", "?", "!", "(", ")", ":", "_", "," };
	
	private static final String NULL_STRING = "null";
	
	private static final String EMPTY_STRING = "";

	//---------------------------------------------------------------------
	// General convenience methods for working with Strings
	//---------------------------------------------------------------------

	/**
	 * Check that the given String is neither <code>null</code> nor of length 0.
	 * Note: Will return <code>true</code> for a String that purely consists of whitespace.
	 * <p><pre>
	 * StringUtils.hasLength(null) = false
	 * StringUtils.hasLength("") = false
	 * StringUtils.hasLength(" ") = true
	 * StringUtils.hasLength("Hello") = true
	 * </pre>
	 * @param str the String to check (may be <code>null</code>)
	 * @return <code>true</code> if the String is not null and has length
	 * @see #hasText(String)
	 */
	public static boolean hasLength(String str) {
		return (str != null && str.length() > 0);
	}

	/**
	 * Check whether the given String has actual text.
	 * More specifically, returns <code>true</code> if the string not <code>null<code>,
	 * its length is greater than 0, and it contains at least one non-whitespace character.
	 * <p><pre>
	 * StringUtils.hasText(null) = false
	 * StringUtils.hasText("") = false
	 * StringUtils.hasText(" ") = false
	 * StringUtils.hasText("12345") = true
	 * StringUtils.hasText(" 12345 ") = true
	 * </pre>
	 * @param str the String to check (may be <code>null</code>)
	 * @return <code>true</code> if the String is not <code>null</code>, its length is
	 * greater than 0, and is does not contain whitespace only
	 * @see Character#isWhitespace
	 */
	public static boolean hasText(String str) {
		if (!hasLength(str)) {
			return false;
		}
		int strLen = str.length();
		for (int i = 0; i < strLen; i++) {
			if (!Character.isWhitespace(str.charAt(i))) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Check whether the given String contains any whitespace characters.
	 * @param str the String to check (may be <code>null</code>)
	 * @return <code>true</code> if the String is not empty and
	 * contains at least 1 whitespace character
	 * @see Character#isWhitespace
	 */
	public static boolean containsWhitespace(String str) {
		if (!hasLength(str)) {
			return false;
		}
		int strLen = str.length();
		for (int i = 0; i < strLen; i++) {
			if (Character.isWhitespace(str.charAt(i))) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Trim leading and trailing whitespace from the given String.
	 * @param str the String to check
	 * @return the trimmed String
	 * @see Character#isWhitespace
	 */
	public static String trimWhitespace(String str) {
		if (!hasLength(str)) {
			return str;
		}
		StringBuffer buf = new StringBuffer(str);
		while (buf.length() > 0 && Character.isWhitespace(buf.charAt(0))) {
			buf.deleteCharAt(0);
		}
		while (buf.length() > 0 && Character.isWhitespace(buf.charAt(buf.length() - 1))) {
			buf.deleteCharAt(buf.length() - 1);
		}
		return buf.toString();
	}

	/**
	 * Trim leading whitespace from the given String.
	 * @param str the String to check
	 * @return the trimmed String
	 * @see Character#isWhitespace
	 */
	public static String trimLeadingWhitespace(String str) {
		if (!hasLength(str)) {
			return str;
		}
		StringBuffer buf = new StringBuffer(str);
		while (buf.length() > 0 && Character.isWhitespace(buf.charAt(0))) {
			buf.deleteCharAt(0);
		}
		return buf.toString();
	}

	/**
	 * Trim trailing whitespace from the given String.
	 * @param str the String to check
	 * @return the trimmed String
	 * @see Character#isWhitespace
	 */
	public static String trimTrailingWhitespace(String str) {
		if (!hasLength(str)) {
			return str;
		}
		StringBuffer buf = new StringBuffer(str);
		while (buf.length() > 0 && Character.isWhitespace(buf.charAt(buf.length() - 1))) {
			buf.deleteCharAt(buf.length() - 1);
		}
		return buf.toString();
	}

	/**
	 * Trim <i>all</i> whitespace from the given String:
	 * leading, trailing, and inbetween characters.
	 * @param str the String to check
	 * @return the trimmed String
	 * @see Character#isWhitespace
	 */
	public static String trimAllWhitespace(String str) {
		if (!hasLength(str)) {
			return str;
		}
		StringBuffer buf = new StringBuffer(str);
		int index = 0;
		while (buf.length() > index) {
			if (Character.isWhitespace(buf.charAt(index))) {
				buf.deleteCharAt(index);
			}
			else {
				index++;
			}
		}
		return buf.toString();
	}


	/**
	 * Test if the given String starts with the specified prefix,
	 * ignoring upper/lower case.
	 * @param str the String to check
	 * @param prefix the prefix to look for
	 * @see String#startsWith
	 */
	public static boolean startsWithIgnoreCase(String str, String prefix) {
		if (str == null || prefix == null) {
			return false;
		}
		if (str.startsWith(prefix)) {
			return true;
		}
		if (str.length() < prefix.length()) {
			return false;
		}
		String lcStr = str.substring(0, prefix.length()).toLowerCase();
		String lcPrefix = prefix.toLowerCase();
		return lcStr.equals(lcPrefix);
	}

	/**
	 * Test if the given String ends with the specified suffix,
	 * ignoring upper/lower case.
	 * @param str the String to check
	 * @param suffix the suffix to look for
	 * @see String#endsWith
	 */
	public static boolean endsWithIgnoreCase(String str, String suffix) {
		if (str == null || suffix == null) {
			return false;
		}
		if (str.endsWith(suffix)) {
			return true;
		}
		if (str.length() < suffix.length()) {
			return false;
		}

		String lcStr = str.substring(str.length() - suffix.length()).toLowerCase();
		String lcSuffix = suffix.toLowerCase();
		return lcStr.equals(lcSuffix);
	}

	/**
	 * Count the occurrences of the substring in string s.
	 * @param str string to search in. Return 0 if this is null.
	 * @param sub string to search for. Return 0 if this is null.
	 */
	public static int countOccurrencesOf(String str, String sub) {
		if (str == null || sub == null || str.length() == 0 || sub.length() == 0) {
			return 0;
		}
		int count = 0, pos = 0, idx = 0;
		while ((idx = str.indexOf(sub, pos)) != -1) {
			++count;
			pos = idx + sub.length();
		}
		return count;
	}

	/**
	 * Replace all occurences of a substring within a string with
	 * another string.
	 * @param inString String to examine
	 * @param oldPattern String to replace
	 * @param newPattern String to insert
	 * @return a String with the replacements
	 */
	public static String replace(String inString, String oldPattern, String newPattern) {
		if (inString == null) {
			return null;
		}
		if (oldPattern == null || newPattern == null) {
			return inString;
		}

		StringBuffer sbuf = new StringBuffer();
		// output StringBuffer we'll build up
		int pos = 0; // our position in the old string
		int index = inString.indexOf(oldPattern);
		// the index of an occurrence we've found, or -1
		int patLen = oldPattern.length();
		while (index >= 0) {
			sbuf.append(inString.substring(pos, index));
			sbuf.append(newPattern);
			pos = index + patLen;
			index = inString.indexOf(oldPattern, pos);
		}
		sbuf.append(inString.substring(pos));

		// remember to append any characters to the right of a match
		return sbuf.toString();
	}

	/**
	 * Delete all occurrences of the given substring.
	 * @param pattern the pattern to delete all occurrences of
	 */
	public static String delete(String inString, String pattern) {
		return replace(inString, pattern, "");
	}

	/**
	 * Delete any character in a given string.
	 * @param charsToDelete a set of characters to delete.
	 * E.g. "az\n" will delete 'a's, 'z's and new lines.
	 */
	public static String deleteAny(String inString, String charsToDelete) {
		if (inString == null || charsToDelete == null) {
			return inString;
		}
		StringBuffer out = new StringBuffer();
		for (int i = 0; i < inString.length(); i++) {
			char c = inString.charAt(i);
			if (charsToDelete.indexOf(c) == -1) {
				out.append(c);
			}
		}
		return out.toString();
	}


	//---------------------------------------------------------------------
	// Convenience methods for working with formatted Strings
	//---------------------------------------------------------------------

	/**
	 * Quote the given String with single quotes.
	 * @param str the input String (e.g. "myString")
	 * @return the quoted String (e.g. "'myString'"),
	 * or <code>null<code> if the input was <code>null</code>
	 */
	public static String quote(String str) {
		return (str != null ? "'" + str + "'" : null);
	}

	/**
	 * Turn the given Object into a String with single quotes
	 * if it is a String; keeping the Object as-is else.
	 * @param obj the input Object (e.g. "myString")
	 * @return the quoted String (e.g. "'myString'"),
	 * or the input object as-is if not a String
	 */
	public static Object quoteIfString(Object obj) {
		return (obj instanceof String ? quote((String) obj) : obj);
	}

	/**
	 * Unqualify a string qualified by a '.' dot character. For example,
	 * "this.name.is.qualified", returns "qualified".
	 * @param qualifiedName the qualified name
	 */
	public static String unqualify(String qualifiedName) {
		return unqualify(qualifiedName, '.');
	}

	/**
	 * Unqualify a string qualified by a separator character. For example,
	 * "this:name:is:qualified" returns "qualified" if using a ':' separator.
	 * @param qualifiedName the qualified name
	 * @param separator the separator
	 */
	public static String unqualify(String qualifiedName, char separator) {
		return qualifiedName.substring(qualifiedName.lastIndexOf(separator) + 1);
	}

	/**
	 * Capitalize a <code>String</code>, changing the first letter to
	 * upper case as per {@link Character#toUpperCase(char)}.
	 * No other letters are changed.
	 * @param str the String to capitalize, may be <code>null</code>
	 * @return the capitalized String, <code>null</code> if null
	 */
	public static String capitalize(String str) {
		return changeFirstCharacterCase(str, true);
	}

	/**
	 * Uncapitalize a <code>String</code>, changing the first letter to
	 * lower case as per {@link Character#toLowerCase(char)}.
	 * No other letters are changed.
	 * @param str the String to uncapitalize, may be <code>null</code>
	 * @return the uncapitalized String, <code>null</code> if null
	 */
	public static String uncapitalize(String str) {
		return changeFirstCharacterCase(str, false);
	}

	private static String changeFirstCharacterCase(String str, boolean capitalize) {
		if (str == null || str.length() == 0) {
			return str;
		}
		StringBuffer buf = new StringBuffer(str.length());
		if (capitalize) {
			buf.append(Character.toUpperCase(str.charAt(0)));
		}
		else {
			buf.append(Character.toLowerCase(str.charAt(0)));
		}
		buf.append(str.substring(1));
		return buf.toString();
	}

	/**
	 * Extract the filename from the given path,
	 * e.g. "mypath/myfile.txt" -> "myfile.txt".
	 * @param path the file path (may be <code>null</code>)
	 * @return the extracted filename, or <code>null</code> if none
	 */
	public static String getFilename(String path) {
		if (path == null) {
			return null;
		}
		int separatorIndex = path.lastIndexOf(FOLDER_SEPARATOR);
		return (separatorIndex != -1 ? path.substring(separatorIndex + 1) : path);
	}

	/**
	 * Extract the filename extension from the given path,
	 * e.g. "mypath/myfile.txt" -> "txt".
	 * @param path the file path (may be <code>null</code>)
	 * @return the extracted filename extension, or <code>null</code> if none
	 */
	public static String getFilenameExtension(String path) {
		if (path == null) {
			return null;
		}
		int sepIndex = path.lastIndexOf(EXTENSION_SEPARATOR);
		return (sepIndex != -1 ? path.substring(sepIndex + 1) : null);
	}

	/**
	 * Strip the filename extension from the given path,
	 * e.g. "mypath/myfile.txt" -> "mypath/myfile".
	 * @param path the file path (may be <code>null</code>)
	 * @return the path with stripped filename extension,
	 * or <code>null</code> if none
	 */
	public static String stripFilenameExtension(String path) {
		if (path == null) {
			return null;
		}
		int sepIndex = path.lastIndexOf(EXTENSION_SEPARATOR);
		return (sepIndex != -1 ? path.substring(0, sepIndex) : path);
	}

	/**
	 * Apply the given relative path to the given path,
	 * assuming standard Java folder separation (i.e. "/" separators);
	 * @param path the path to start from (usually a full file path)
	 * @param relativePath the relative path to apply
	 * (relative to the full file path above)
	 * @return the full file path that results from applying the relative path
	 */
	public static String applyRelativePath(String path, String relativePath) {
		int separatorIndex = path.lastIndexOf(FOLDER_SEPARATOR);
		if (separatorIndex != -1) {
			String newPath = path.substring(0, separatorIndex);
			if (!relativePath.startsWith(FOLDER_SEPARATOR)) {
				newPath += FOLDER_SEPARATOR;
			}
			return newPath + relativePath;
		}
		else {
			return relativePath;
		}
	}



	/**
	 * Split a String at the first occurrence of the delimiter.
	 * Does not include the delimiter in the result.
	 * @param toSplit the string to split
	 * @param delimiter to split the string up with
	 * @return a two element array with index 0 being before the delimiter, and
	 * index 1 being after the delimiter (neither element includes the delimiter);
	 * or <code>null</code> if the delimiter wasn't found in the given input String
	 */
	public static String[] split(String toSplit, String delimiter) {
		if (!hasLength(toSplit) || !hasLength(delimiter)) {
			return null;
		}
		int offset = toSplit.indexOf(delimiter);
		if (offset < 0) {
			return null;
		}
		String beforeDelimiter = toSplit.substring(0, offset);
		String afterDelimiter = toSplit.substring(offset + delimiter.length());
		return new String[] {beforeDelimiter, afterDelimiter};
	}



	/**
	 * Convert a String to int.
	 * @param intString A String contains an int value.
	 * @return int The int value parsed from the string as parameter, 0 is
	 * returned if cannot parse an int value from the given string.
	 */
	public static int toInt(String intString) {
		try {
			return Integer.parseInt(intString);
		} catch (NumberFormatException e) {
			return 0;
		}
	}

	/**
	 * Replace the certain part in target string with a new string
	 * @param oldStr String
	 * @param searchStr String
	 * @param replaceStr String
	 * @return result String
	 */
	public static String stringReplace(String oldStr, String searchStr,
			String replaceStr) {
		String outStr =  "";
		int  iPos = 0;
		int  iLen = searchStr.length();

		if(oldStr==null  || oldStr=="" || searchStr==null || replaceStr==null) {
			return oldStr;
		}

		iPos = oldStr.indexOf(searchStr);

		while(iPos!=-1) {
			outStr += oldStr.substring(0,iPos) + replaceStr;
			iPos +=  iLen;
			if (oldStr.length()  >= iPos) {
				oldStr = oldStr.substring(iPos);
				iPos = oldStr.indexOf(searchStr);
			}
		}
		outStr += oldStr;
		return outStr;
	}

	/**
	 * Localize the specified string
	 * @param s string to be localized
	 * @return result String
	 */
	public static String localString(String s) {
		if ( s == null ) return "";

		/*String s2=s;
		 try {
		 s2 = new String(s.getBytes("iso-8859-1"),"GB2312");
		 } catch(Exception  e) {}
		 finally {
		 return s2;
		 }*/
		return s;
	}

	public static String localStringReal(String s) {
		if ( s == null ) return "";

		String s2=s;
		try {
			s2 = new String(s.getBytes("iso-8859-1"),"GB2312");
		} 
		catch(Exception  e) {
		}
		return s2;
	}

	/**
	 * Convert string to iso string
	 * @param s string to be globalized
	 * @return result String
	 */
	public static String isoString(String s) {
		if ( s == null ) return "";

		/*String s2 = s;
		 try {
		 s2 = new String(s.getBytes("GB2312"),"iso-8859-1");
		 } catch(Exception  e) {
		 } finally {
		 return s2;
		 }*/
		return s;
	}

	public static String isoStringReal(String s){
		if ( s == null ) return "";

		String s2 = s;
		try {
			s2 = new String(s.getBytes("GB2312"),"iso-8859-1");
		} catch(Exception  e) {
		}
		return s2;
	}

	/**
	 * Encode string to sql friendly format
	 * @return result String
	 * @param inStr string to be encoded
	 */
	public static String sqlEncode(String inStr) {
		return stringReplace(nullToString(inStr), "'", "''");
	}

	/**
	 * Encode string to html friendly format
	 * @return result String
	 * @param inStr string to be encoded
	 */
	public static String htmlEncode(String inStr) {
		String outString = nullToString(inStr);
		outString =  stringReplace(outString, "&", "&amp;");
		outString =  stringReplace(outString, "<", "&lt;");
		outString =  stringReplace(outString, ">", "&gt;");
		outString =  stringReplace(outString, "\"", "&quot;");
		return outString;
	}

	public static String isoHtmlEncode(String inStr) {
		String outString = nullToString(inStr);
		outString =  stringReplace(outString, "&", "&amp;");
		outString =  stringReplace(outString, "<", "&lt;");
		outString =  stringReplace(outString, ">", "&gt;");
		outString =  stringReplace(outString, "\"", "&quot;");
		return isoString(outString);
	}

	public static String localHtmlEncode(String inStr) {
		String outString = nullToString(inStr);
		outString =  stringReplace(outString, "&", "&amp;");
		outString =  stringReplace(outString, "<", "&lt;");
		outString =  stringReplace(outString, ">", "&gt;");
		outString =  stringReplace(outString, "\"", "&quot;");
		return localString(outString);
	}

	/**
	 * Format specified number to a certain length
	 * @param iNumber the number to be converted
	 * @param iLength the wanted length
	 * @return result String
	 */
	public static String formatFixNumber(int iNumber, int iLength) {
		DecimalFormat decFormat;
		String sFormat = "";
		String sNumber = "";

		try {
			for (int i=0; i<iLength; i++) {
				sFormat  += "0";
			}
			decFormat =  new DecimalFormat(sFormat);
			sNumber  = decFormat.format(iNumber);
		} catch  (Exception e) {
			//System.out.println("Exception: " + e.getMessage());
		}
		return sNumber;
	}

	/**
	 * Convert a string to a non-null value
	 * @param inString string to be converted
	 * @return result String
	 */
	public static String nullToString(String inString) {
		return (inString == null ? "" : inString.trim());
	}

	/**
	 * Convert a string to a non-null value
	 * @param inObject to be converted
	 * @return result String
	 */
	public static String nullToString(Object inObject) {
		return (inObject == null ? "" : inObject.toString());
	}

	/**
	 * get the start day of the month.
	 * 1 - Sunday, 2- Monday,...,7 - Saturday
	 * @param iYear year
	 * @param iMonth month
	 * @return start day
	 */
	public static int getMonthStartDay(int iYear, int iMonth) {
		Calendar cDate = Calendar.getInstance();
		cDate.set(iYear, iMonth - 1, 1);
		return cDate.get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * get the day count of the month.
	 * 1 - 31, 2 -28/29,3 -  31,4 - 20 ...
	 * @param iYear year
	 * @param iMonth month
	 * @return the day count of the month
	 */
	public static int getDaysInMonth(int iYear, int iMonth) {
		Calendar cDate = Calendar.getInstance();
		cDate.set(iYear, iMonth, 1);
		cDate.add(Calendar.DATE, -1);
		return cDate.get(Calendar.DATE);
	}

	/**
	 * get the double format.
	 * @param iNumber
	 * @return String
	 */
	public static String formatDouble(String iNumber) {
		DecimalFormat decFormat;
		String sFormat = "#,##0.00";
		String sNumber = "";
		try {
			decFormat =  new DecimalFormat(sFormat);
			sNumber  = decFormat.format(Double.parseDouble(iNumber));
		} catch  (Exception e) {
			//System.out.println("Exception: " + e.getMessage());
		}
		return sNumber;
	}

	/**
	 * get the double format.
	 * @param iNumber
	 * @return String
	 */
	public static String formatMoney(String iNumber) {
		DecimalFormat decFormat;
		String sFormat = "###0.00";
		String sNumber = "";
		try {
			decFormat =  new DecimalFormat(sFormat);
			sNumber  = decFormat.format(Double.parseDouble(iNumber));
		} catch  (Exception e) {
			////System.out.println("Exception: " + e.getMessage());
		}
		return sNumber;
	}

	/**
	 * Convert one ANSI string into UNICODE code string, this
	 * function must used after JDK V1.1
	 * @param	s		 UNICODE	string that need to be converted
	 * @return	sRet UNICODE string converted from ANSI string
	 */
	public static String AnsiToUnicode(String s){

		if(s==null){
			return "";
		}
		char []c = s.toCharArray();
		byte []b = new byte[2*c.length];
		int	i = 0;
		int	l = 0;
		while(i<c.length) {
			int	iH = (int)c[i];
			int	iL = iH%256;
			iH = iH/256;
			if(iH>0) {
				b[l] = (byte)iH;
				l++;
			}
			b[l] = (byte)iL;
			l++;
			i++;
		}
		String sRet = "";
		try{
			sRet = new String(b,"GB2312");
		}catch(Exception e){
			//System.out.println("GB2312 to unicode failed:"+e.getMessage());
			return s.trim();
		}
		return sRet.trim();
	}



	public static String blankToNull(String input){
		if((input==null) || ("".equals(input.trim()))){
			return null;
		}else
			return input.trim();
	}

	


	
	/**
	 * 此方法将给出的字符串source使用delim划分为单词数组。
	 * @param source 需要进行划分的原字符串
	 * @param delim 单词的分隔字符串
	 * @return 划分以后的数组，如果source为null的时候返回以source为唯一元素的数组，
	 *         如果delim为null则使用逗号作为分隔字符串。
	 * @since  0.1
	 */
//	public static String[] split(String source, String delim) {
//		String[] wordLists;
//		if (source == null) {
//			wordLists = new String[1];
//			wordLists[0] = source;
//			return wordLists;
//		}
//		if (delim == null) {
//			delim = ",";
//		}
//		StringTokenizer st = new StringTokenizer(source, delim);
//		int total = st.countTokens();
//		wordLists = new String[total];
//		for (int i = 0; i < total; i++) {
//			wordLists[i] = st.nextToken();
//		}
//		return wordLists;
//	}

	/**
	 * 此方法将给出的字符串source使用delim划分为单词数组。
	 * @param source 需要进行划分的原字符串
	 * @param delim 单词的分隔字符
	 * @return 划分以后的数组，如果source为null的时候返回以source为唯一元素的数组。
	 * @since  0.2
	 */
	public static String[] split(String source, char delim) {
		return split(source, String.valueOf(delim));
	}

	/**
	 * 此方法将给出的字符串source使用逗号划分为单词数组。
	 * @param source 需要进行划分的原字符串
	 * @return 划分以后的数组，如果source为null的时候返回以source为唯一元素的数组。
	 * @since  0.1
	 */
	public static String[] split(String source) {
		return split(source, ",");
	}



	/**
	 * 将字符串中的变量使用values数组中的内容进行替换。
	 * 替换的过程是不进行嵌套的，即如果替换的内容中包含变量表达式时不会替换。
	 * @param prefix 变量前缀字符串
	 * @param source 带参数的原字符串
	 * @param values 替换用的字符串数组
	 * @return 替换后的字符串。
	 *         如果前缀为null则使用“%”作为前缀；
	 *         如果source或者values为null或者values的长度为0则返回source；
	 *         如果values的长度大于参数的个数，多余的值将被忽略；
	 *         如果values的长度小于参数的个数，则后面的所有参数都使用最后一个值进行替换。
	 * @since  0.2
	 */
	public static String getReplaceString(String prefix, String source,
			String[] values) {
		String result = source;
		if (source == null || values == null || values.length < 1) {
			return source;
		}
		if (prefix == null) {
			prefix = "%";
		}

		for (int i = 0; i < values.length; i++) {
			String argument = prefix + Integer.toString(i + 1);
			int index = result.indexOf(argument);
			if (index != -1) {
				String temp = result.substring(0, index);
				if (i < values.length) {
					temp += values[i];
				}
				else {
					temp += values[values.length - 1];
				}
				temp += result.substring(index + 2);
				result = temp;
			}
		}
		return result;
	}

	/**
	 * 将字符串中的变量（以“%”为前导后接数字）使用values数组中的内容进行替换。
	 * 替换的过程是不进行嵌套的，即如果替换的内容中包含变量表达式时不会替换。
	 * @param source 带参数的原字符串
	 * @param values 替换用的字符串数组
	 * @return 替换后的字符串
	 * @since  0.1
	 */
	public static String getReplaceString(String source, String[] values) {
		return getReplaceString("%", source, values);
	}

	/**
	 * 字符串数组中是否包含指定的字符串。
	 * @param strings 字符串数组
	 * @param string 字符串
	 * @param caseSensitive 是否大小写敏感
	 * @return 包含时返回true，否则返回false
	 * @since  0.4
	 */
	public static boolean contains(String[] strings, String string,
			boolean caseSensitive) {
		for (int i = 0; i < strings.length; i++) {
			if (caseSensitive == true) {
				if (strings[i].equals(string)) {
					return true;
				}
			}
			else {
				if (strings[i].equalsIgnoreCase(string)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 字符串数组中是否包含指定的字符串。大小写敏感。
	 * @param strings 字符串数组
	 * @param string 字符串
	 * @return 包含时返回true，否则返回false
	 * @since  0.4
	 */
	public static boolean contains(String[] strings, String string) {
		return contains(strings, string, true);
	}

	/**
	 * 不区分大小写判定字符串数组中是否包含指定的字符串。
	 * @param strings 字符串数组
	 * @param string 字符串
	 * @return 包含时返回true，否则返回false
	 * @since  0.4
	 */
	public static boolean containsIgnoreCase(String[] strings, String string) {
		return contains(strings, string, false);
	}

	/**
	 * 将字符串数组使用指定的分隔符合并成一个字符串。
	 * @param array 字符串数组
	 * @param delim 分隔符，为null的时候使用""作为分隔符（即没有分隔符）
	 * @return 合并后的字符串
	 * @since  0.4
	 */
	public static String combineStringArray(String[] array, String delim) {
		int length = array.length - 1;
		if (delim == null) {
			delim = "";
		}
		StringBuffer result = new StringBuffer(length * 8);
		for (int i = 0; i < length; i++) {
			result.append(array[i]);
			result.append(delim);
		}
		result.append(array[length]);
		return result.toString();
	}
	
	
	public static String upperCaseFirstChar(String s){
		if(s==null){
			return null;
		}else{
			return s.substring(0,1).toUpperCase()+s.substring(1);
		}
		
	}
	
	public static URL toURL(String urlspec)throws MalformedURLException{
		return toURL(urlspec,null);
	}
	
	/**
	 * 将字符串路径转化为URL对象
	 * @param urlspec
	 * @param relativePrefix
	 * @return
	 * @throws MalformedURLException
	 */
    public static URL toURL(String urlspec, String relativePrefix) throws MalformedURLException {
        urlspec = urlspec.trim();
        URL url;
        try {
            url = new URL(urlspec);
            if (url.getProtocol().equals("file"))
                url = makeURLFromFilespec(url.getFile(), relativePrefix);
        }
        catch (Exception e) {
            try {
                url = makeURLFromFilespec(urlspec, relativePrefix);
            }
            catch (IOException n) {
                throw new MalformedURLException(n.toString());
            }
        }
        return url;
    }
    
    private static URL makeURLFromFilespec(String filespec, String relativePrefix) throws IOException {
        File file = new File(filespec);
        if (relativePrefix != null && !file.isAbsolute())
            file = new File(relativePrefix, filespec);
        file = file.getCanonicalFile();
        return file.toURL();
    }
    

	public static boolean isWord(String str) {
		if (str == null) {
			return false;
		}

		char[] ch = str.toCharArray();

		for (int i = 0; i < str.length(); ++i) {
			if ((!Character.isLetterOrDigit(ch[i])) && (ch[i] != '_')) {
				return false;
			}
		}
		return true;
	}

	public static boolean isNumEx(String str) {
		if ((str == null) || (str.length() <= 0)) {
			return false;
		}

		char[] ch = str.toCharArray();

		int i = 0;
		for (int comcount = 0; i < str.length(); ++i) {
			if (!Character.isDigit(ch[i])) {
				if (ch[i] != '.') {
					return false;
				}
				if ((i == 0) || (i == str.length() - 1)) {
					return false;
				}
				if (++comcount > 1) {
					return false;
				}
			}
		}
		return true;
	}

	public static Object getStringNumber(String str, int index) {
		if (str == null) {
			return null;
		}

		char[] ch = str.toCharArray();

		String tempStr = "";
		Vector returnNumber = new Vector();

		for (int i = 0; i < str.length(); ++i) {
			if (Character.isDigit(ch[i])) {
				tempStr = tempStr + ch[i];
			} else {
				if (!tempStr.equals("")) {
					returnNumber.addElement(tempStr);
				}
				tempStr = "";
			}
		}

		if (!tempStr.equals("")) {
			returnNumber.addElement(tempStr);
		}

		if ((returnNumber.isEmpty()) || (index > returnNumber.size())) {
			return null;
		}

		if (index <= 0) {
			return returnNumber;
		}

		return returnNumber.elementAt(index - 1);
	}

	public static String[] sortByLength(String[] saSource, boolean bAsc) {
		if ((saSource == null) || (saSource.length <= 0)) {
			return null;
		}

		int iLength = saSource.length;
		String[] saDest = new String[iLength];

		for (int i = 0; i < iLength; ++i) {
			saDest[i] = saSource[i];
		}

		String sTemp = "";
		int j = 0;
		int k = 0;

		for (j = 0; j < iLength; ++j) {
			for (k = 0; k < iLength - j - 1; ++k) {
				if ((saDest[k].length() > saDest[(k + 1)].length()) && (bAsc)) {
					sTemp = saDest[k];
					saDest[k] = saDest[(k + 1)];
					saDest[(k + 1)] = sTemp;
				} else if ((saDest[k].length() < saDest[(k + 1)].length()) && (!bAsc)) {
					sTemp = saDest[k];
					saDest[k] = saDest[(k + 1)];
					saDest[(k + 1)] = sTemp;
				}
			}
		}
		return saDest;
	}

	public static String symbolSBCToDBC(String sSource) {
		if ((sSource == null) || (sSource.length() <= 0)) {
			return sSource;
		}

		int iLen = (SBC.length < DBC.length) ? SBC.length : DBC.length;
		for (int i = 0; i < iLen; ++i) {
			sSource = StringUtils.replace(sSource, SBC[i], DBC[i]);
		}
		return sSource;
	}

	public static String symbolDBCToSBC(String sSource) {
		if ((sSource == null) || (sSource.length() <= 0)) {
			return sSource;
		}

		int iLen = (SBC.length < DBC.length) ? SBC.length : DBC.length;
		for (int i = 0; i < iLen; ++i) {
			sSource = StringUtils.replace(sSource, DBC[i], SBC[i]);
		}
		return sSource;
	}

	public static boolean isEmailAddress(String str) {
		if (org.apache.commons.lang.StringUtils.isEmpty(str)) {
			return false;
		}

		return emailPattern.matcher(str).matches();
	}

	public static String quoteNullString(String s) {
		if (s == null) {
			return "Null";
		}
		if (s.trim().length() == 0) {
			return "Null";
		}
		return "'" + s.trim() + "'";
	}

	public static char getCharAtPosDefaultZero(String s, int pos) {
		if (s == null) {
			return '0';
		}

		if (s.length() <= pos) {
			return '0';
		}
		return s.charAt(pos);
	}

	public static String setCharAtPosAppendZero(String s, int pos, char c) {
		if (s == null) {
			s = "";
		}

		while (pos > s.length() - 1)
			s = s + '0';
		String preString;
		if (pos == 0) {
			preString = "";
		} else
			preString = s.substring(0, pos);
		String afterString;
		if (pos == s.length() - 1) {
			afterString = "";
		} else {
			afterString = s.substring(pos + 1);
		}

		return preString + c + afterString;
	}

	public static String fillBlank(String s, int n, boolean isLeft) {
		if (n < 0) {
			return s;
		}

		if (org.apache.commons.lang.StringUtils.isEmpty(s)) {
			return org.apache.commons.lang.StringUtils.rightPad("", n, " ");
		}

		if (s.length() >= n) {
			return s;
		}
		if (isLeft) {
			return org.apache.commons.lang.StringUtils.leftPad(s, n, " ");
		}

		return org.apache.commons.lang.StringUtils.rightPad(s, n, " ");
	}

	public static int compareVersion(String version1, String version2) {
		StringTokenizer st1 = new StringTokenizer(version1, ".");
		StringTokenizer st2 = new StringTokenizer(version2, ".");

		ArrayList al1 = new ArrayList();
		ArrayList al2 = new ArrayList();

		while (st1.hasMoreTokens()) {
			al1.add(st1.nextToken());
		}
		while (st2.hasMoreTokens()) {
			al2.add(st2.nextToken());
		}

		int size1 = al1.size();
		int size2 = al2.size();

		for (int i = 0; (i < size1) && (i < size2); ++i) {
			int v1 = Integer.parseInt((String) al1.get(i));
			int v2 = Integer.parseInt((String) al2.get(i));

			if (v1 > v2) {
				return 1;
			}
			if (v1 < v2) {
				return -1;
			}
		}

		if (size1 > size2) {
			return 1;
		}
		if (size1 < size2) {
			return -1;
		}
		return 0;
	}




	public static boolean isNotNullAndBlank(String str) {
		return !isNullOrBlank(str);
	}

	public static boolean isNullOrBlank(String str) {
		return (isNull(str)) || (str.equals("")) || (str.equals("null"));
	}

	public static boolean isNull(String str) {
		return (str == null) || (str.trim().length() == 0);
	}

	public static boolean isNotNull(String str) {
		if ((str == null) || (str.trim().length() == 0)) {
			return false;
		}

		return !str.trim().equalsIgnoreCase("null");
	}

	public static String ifNullToBlank(String str) {
		if ((isNotNull(str)) && (!str.trim().equals("null"))) {
			return str.trim();
		}

		return "";
	}

	public static String ifNullToBlank(Object obj) {
		String ret = "";
		String s = String.valueOf(obj);
		if ((s == null) || ("".equals(s)) || ("null".equals(s)) || ("NULL".equals(s))) {
			ret = "";
		} else {
			ret = s;
		}
		return ret;
	}

	public static boolean hasWildcards(String input) {
		return (org.apache.commons.lang.StringUtils.contains(input, "*")) || (org.apache.commons.lang.StringUtils.contains(input, "?"));
	}

	public static boolean isWildcardMatchOne(String keyword, String[] wildcardMatcher, boolean caseSensitive) {
		if (null == wildcardMatcher) {
			return true;
		}

		for (int i = 0; i < wildcardMatcher.length; ++i) {
			String t_WildCardMatcher = wildcardMatcher[i];

			if (isWildcardMatch(keyword, t_WildCardMatcher, caseSensitive)) {
				return true;
			}
		}

		return false;
	}

	public static boolean isWildcardMatchAll(String r_Keyword, String[] r_WildcardMatcher, boolean r_CaseSensitive) {
		if (null == r_WildcardMatcher) {
			return true;
		}

		for (int i = 0; i < r_WildcardMatcher.length; ++i) {
			String t_WildCardMatcher = r_WildcardMatcher[i];

			if (!isWildcardMatch(r_Keyword, t_WildCardMatcher, r_CaseSensitive)) {
				return false;
			}
		}

		return true;
	}

	public static boolean isWildcardMatch(String keyword, String wildcardMatcher) {
		return isWildcardMatch(keyword, wildcardMatcher, true);
	}

	public static boolean isWildcardMatch(String keyword, String wildcardMatcher, boolean caseSensitive) {
		if ((keyword == null) && (wildcardMatcher == null)) {
			return true;
		}
		if ((keyword == null) || (wildcardMatcher == null)) {
			return false;
		}
		if (!caseSensitive) {
			keyword = keyword.toLowerCase();
			wildcardMatcher = wildcardMatcher.toLowerCase();
		}
		String[] t_SplitValues = splitOnTokens(wildcardMatcher);
		boolean t_Chars = false;
		int t_Index = 0;
		int t_WildIndex = 0;
		Stack t_BackStack = new Stack();
		do {
			if (t_BackStack.size() > 0) {
				int[] array = (int[]) (int[]) t_BackStack.pop();
				t_WildIndex = array[0];
				t_Index = array[1];
				t_Chars = true;
			}

			while (t_WildIndex < t_SplitValues.length) {
				if (t_SplitValues[t_WildIndex].equals("?")) {
					++t_Index;
					t_Chars = false;
				} else if (t_SplitValues[t_WildIndex].equals("*")) {
					t_Chars = true;
					if (t_WildIndex == t_SplitValues.length - 1) {
						t_Index = keyword.length();
					}

				} else {
					if (t_Chars) {
						t_Index = keyword.indexOf(t_SplitValues[t_WildIndex], t_Index);
						if (t_Index == -1) {
							break;
						}

						int repeat = keyword.indexOf(t_SplitValues[t_WildIndex], t_Index + 1);
						if (repeat >= 0) {
							t_BackStack.push(new int[] { t_WildIndex, repeat });
						}

					} else {
						if (!keyword.startsWith(t_SplitValues[t_WildIndex], t_Index)) {
							break;
						}

					}

					t_Index += t_SplitValues[t_WildIndex].length();
					t_Chars = false;
				}

				++t_WildIndex;
			}

			if ((t_WildIndex == t_SplitValues.length) && (t_Index == keyword.length())) {
				return true;
			}
		} while (t_BackStack.size() > 0);

		return false;
	}

	private static String[] splitOnTokens(String text) {
		if ((text.indexOf("?") == -1) && (text.indexOf("*") == -1)) {
			return new String[] { text };
		}

		char[] array = text.toCharArray();
		ArrayList<String> list = new ArrayList<String>();
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < array.length; ++i) {
			if ((array[i] == '?') || (array[i] == '*')) {
				if (buffer.length() != 0) {
					list.add(buffer.toString());
					buffer.setLength(0);
				}
				if (array[i] == '?') {
					list.add("?");
				} else if ((list.size() == 0) || ((i > 0) && (!list.get(list.size() - 1).equals("*"))))
					list.add("*");
			} else {
				buffer.append(array[i]);
			}
		}
		if (buffer.length() != 0) {
			list.add(buffer.toString());
		}

		return (String[]) (String[]) list.toArray(new String[0]);
	}

	public static boolean isIn(String r_Source, String[] r_Target, boolean r_CaseSensitive) {
		for (int i = 0; i < r_Target.length; ++i) {
			String t_Value = r_Target[i];
			if (r_CaseSensitive) {
				if (org.apache.commons.lang.StringUtils.equals(r_Source, t_Value)) {
					return true;
				}
			} else if (org.apache.commons.lang.StringUtils.equalsIgnoreCase(r_Source, t_Value)) {
				return true;
			}

		}

		return false;
	}

	public static boolean isIn(String r_Source, Collection r_Target) {
		for (Iterator t_Iterator = r_Target.iterator(); t_Iterator.hasNext();) {
			String t_Value = (String) t_Iterator.next();
			if (org.apache.commons.lang.StringUtils.equalsIgnoreCase(r_Source, t_Value)) {
				return true;
			}
		}

		return false;
	}

	public static String targetEndStyle(String name) {
		return "</" + name + ">";
	}

	public static String valueToSetStyle(String value) {
		if (value == null) {
			value = "";
		}
		return "=\"" + value + "\"";
	}

	public static boolean equal(String s1, String s2) {
		if (s1 == s2) {
			return true;
		}

		if (s1 == null) {
			s1 = "";
		}

		if (s2 == null) {
			s2 = "";
		}

		s1 = s1.trim();
		s2 = s2.trim();

		return s1.equals(s2);
	}

	public static String concat(Object[] args) {
		StringBuffer buf = new StringBuffer();
		for (Object arg : args) {
			buf.append(arg);
		}
		return buf.toString();
	}

	public static String format(String s, Object[] params) {
		String message = s;
		if (message == null) {
			return "";
		}
		if ((params != null) && (params.length > 0)) {
			message = new MessageFormat(message).format(params);
		}
		return message;
	}


	public static String substring(String str, int start){
	    if (str == null) {
	      return null;
	    }
	    if (start < 0) {
	      start = str.length() + start;
	    }
	    if (start < 0) {
	      start = 0;
	    }
	    if (start > str.length()) {
	      return "";
	    }
	    return str.substring(start);
	}

	public static String substringBefore(String str, String separator){
	    if ((isNull(str)) || (separator == null)) {
	      return str;
	    }
	    if (separator.length() == 0) {
	      return "";
	    }
	    int pos = str.indexOf(separator);
	    if (pos == -1) {
	      return str;
	    }
	    return str.substring(0, pos);
	}

	public static String substringBeforeLast(String str, String separator){
	    if ((isNull(str)) || (isNull(separator))) {
	      return str;
	    }
	    int pos = str.lastIndexOf(separator);
	    if (pos == -1) {
	      return str;
	    }
	    return str.substring(0, pos);
	}
	
	
	public static String substringAfter(String str, String separator){
	    if (isNull(str)) {
	      return str;
	    }
	    if (separator == null) {
	      return "";
	    }
	    int pos = str.indexOf(separator);
	    if (pos == -1) {
	      return "";
	    }
	    return str.substring(pos + separator.length());
	}

	public static String substringAfterLast(String str, String separator){
	    if (isNull(str)) {
	      return str;
	    }
	    if (isNull(separator)) {
	      return "";
	    }
	    int pos = str.lastIndexOf(separator);
	    if ((pos == -1) || (pos == str.length() - separator.length())) {
	      return "";
	    }
	    return str.substring(pos + separator.length());
	}

	public static String substringBetween(String str, String tag){
	    return substringBetween(str, tag, tag);
	}

	public static String substringBetween(String str, String open, String close){
	    if ((str == null) || (open == null) || (close == null)) {
	      return null;
	    }
	    int start = str.indexOf(open);
	    if (start != -1) {
	    	int end = str.indexOf(close, start + open.length());
	    	if (end != -1) {
	    		return str.substring(start + open.length(), end);
	    	}
	    }
	    return null;
	}
	
	public static Map<String, String> parseQueryString(String qs)
	{
	    if( qs == null || qs.length() == 0 )
            return new HashMap<String, String>();
	    return parseKeyValuePair(qs, "\\&");
	}
	
	/**
	 * parse key-value pair.
	 * 
	 * @param str string.
	 * @param itemSeparator item separator.
	 * @return key-value map;
	 */
	private static Map<String, String> parseKeyValuePair(String str, String itemSeparator)
	{
		String[] tmp = str.split(itemSeparator);
		Map<String, String> map = new HashMap<String, String>(tmp.length);
		for(int i=0;i<tmp.length;i++)
		{
			Matcher matcher = KVP_PATTERN.matcher(tmp[i]);
			if( matcher.matches() == false )
				continue;
			map.put(matcher.group(1), matcher.group(2));
		}
		return map;
	}
	
	
	public static String toQueryString(Map<String, String> ps) {
		StringBuilder buf = new StringBuilder();
		if (ps != null && ps.size() > 0) {
			for (Map.Entry<String, String> entry : new TreeMap<String, String>(ps).entrySet()) {
				String key = entry.getKey();
				String value = entry.getValue();
				if (key != null && key.length() > 0
						&& value != null && value.length() > 0) {
					if (buf.length() > 0) {
						buf.append("&");
					}
					buf.append(key);
					buf.append("=");
					buf.append(value);
				}
			}
		}
		return buf.toString();
	}
	
	
    public static boolean isContains(String values, String value) {
        if (values == null || values.length() == 0) {
            return false;
        }
        return isContains(COMMA_SPLIT_PATTERN.split(values), value);
    }
    
    /**
     * 
     * @param values
     * @param value
     * @return contains
     */
    public static boolean isContains(String[] values, String value) {
        if (value != null && value.length() > 0 && values != null && values.length > 0) {
            for (String v : values) {
                if (value.equals(v)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    
    public static boolean isEquals(String s1, String s2) {
        if (s1 == null && s2 == null)
            return true;
        if (s1 == null || s2 == null)
            return false;
        return s1.equals(s2);
    }
    
    
    /**
     * is integer string.
     * 
     * @param str
     * @return is integer
     */
    public static boolean isInteger(String str) {
    	if (str == null || str.length() == 0)
    		return false;
        return INT_PATTERN.matcher(str).matches();
    }
    
    public static int parseInteger(String str) {
    	if (! isInteger(str))
    		return 0;
        return Integer.parseInt(str);
    }

	/**
	 * Tokenize the given String into a String array via a StringTokenizer.
	 * Trims tokens and omits empty tokens.
	 * <p>The given delimiters string is supposed to consist of any number of
	 * delimiter characters. Each of those characters can be used to separate
	 * tokens. A delimiter is always a single character; for multi-character
	 * delimiters, consider using <code>delimitedListToStringArray</code>
	 * @param str the String to tokenize
	 * @param delimiters the delimiter characters, assembled as String
	 * (each of those characters is individually considered as delimiter).
	 * @return an array of the tokens
	 * @see StringTokenizer
	 * @see String#trim()
	 * @see #delimitedListToStringArray
	 */
	public static String[] tokenizeToStringArray(String str, String delimiters) {
		return tokenizeToStringArray(str, delimiters, true, true);
	}

	/**
	 * Tokenize the given String into a String array via a StringTokenizer.
	 * <p>The given delimiters string is supposed to consist of any number of
	 * delimiter characters. Each of those characters can be used to separate
	 * tokens. A delimiter is always a single character; for multi-character
	 * delimiters, consider using <code>delimitedListToStringArray</code>
	 * @param str the String to tokenize
	 * @param delimiters the delimiter characters, assembled as String
	 * (each of those characters is individually considered as delimiter)
	 * @param trimTokens trim the tokens via String's <code>trim</code>
	 * @param ignoreEmptyTokens omit empty tokens from the result array
	 * (only applies to tokens that are empty after trimming; StringTokenizer
	 * will not consider subsequent delimiters as token in the first place).
	 * @return an array of the tokens (<code>null</code> if the input String
	 * was <code>null</code>)
	 * @see StringTokenizer
	 * @see String#trim()
	 */
	public static String[] tokenizeToStringArray(String str, String delimiters, boolean trimTokens, boolean ignoreEmptyTokens) {

		if (str == null) {
			return null;
		}
		StringTokenizer st = new StringTokenizer(str, delimiters);
		List<String> tokens = new ArrayList<String>();
		while (st.hasMoreTokens()) {
			String token = st.nextToken();
			if (trimTokens) {
				token = token.trim();
			}
			if (!ignoreEmptyTokens || token.length() > 0) {
				tokens.add(token);
			}
		}
		return toStringArray(tokens);
	}
	
	public static String[] toStringArray(Collection collection) {
		if (collection == null) {
			return null;
		}
		return (String[]) collection.toArray(new String[collection.size()]);
	}
    
	public static String arrayToDelimitedString(Object[] arr, String delim) {
		if (arr == null || arr.length == 0){
			return "";
		}
		if (arr.length == 1) {
			return nullSafeToString(arr[0]);
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < arr.length; i++) {
			if (i > 0) {
				sb.append(delim);
			}
			sb.append(arr[i]);
		}
		return sb.toString();
	}
	
	public static String arrayToCommaDelimitedString(Object[] arr) {
		return arrayToDelimitedString(arr, ",");
	}

	

	/**
	 * Convert a {@link Collection} to a delimited {@code String} (e.g. CSV).
	 * <p>Useful for {@code toString()} implementations.
	 * @param coll the {@code Collection} to convert
	 * @param delim the delimiter to use (typically a ",")
	 * @param prefix the {@code String} to start each element with
	 * @param suffix the {@code String} to end each element with
	 * @return the delimited {@code String}
	 */
	public static String collectionToDelimitedString(Collection<?> coll, String delim, String prefix, String suffix) {
		if (CollectionUtils.isEmpty(coll)) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		Iterator<?> it = coll.iterator();
		while (it.hasNext()) {
			sb.append(prefix).append(it.next()).append(suffix);
			if (it.hasNext()) {
				sb.append(delim);
			}
		}
		return sb.toString();
	}

	/**
	 * Convert a {@code Collection} into a delimited {@code String} (e.g. CSV).
	 * <p>Useful for {@code toString()} implementations.
	 * @param coll the {@code Collection} to convert
	 * @param delim the delimiter to use (typically a ",")
	 * @return the delimited {@code String}
	 */
	public static String collectionToDelimitedString(Collection<?> coll, String delim) {
		return collectionToDelimitedString(coll, delim, "", "");
	}

	/**
	 * Convert a {@code Collection} into a delimited {@code String} (e.g., CSV).
	 * <p>Useful for {@code toString()} implementations.
	 * @param coll the {@code Collection} to convert
	 * @return the delimited {@code String}
	 */
	public static String collectionToCommaDelimitedString(Collection<?> coll) {
		return collectionToDelimitedString(coll, ",");
	}

	
	public static String nullSafeToString(Object obj) {
		if (obj == null) {
			return NULL_STRING;
		}
		if (obj instanceof String) {
			return (String) obj;
		}
		if (obj instanceof Object[]) {
			return nullSafeToString((Object[]) obj);
		}
		if (obj instanceof boolean[]) {
			return nullSafeToString((boolean[]) obj);
		}
		if (obj instanceof byte[]) {
			return nullSafeToString((byte[]) obj);
		}
		if (obj instanceof char[]) {
			return nullSafeToString((char[]) obj);
		}
		if (obj instanceof double[]) {
			return nullSafeToString((double[]) obj);
		}
		if (obj instanceof float[]) {
			return nullSafeToString((float[]) obj);
		}
		if (obj instanceof int[]) {
			return nullSafeToString((int[]) obj);
		}
		if (obj instanceof long[]) {
			return nullSafeToString((long[]) obj);
		}
		if (obj instanceof short[]) {
			return nullSafeToString((short[]) obj);
		}
		String str = obj.toString();
		return (str != null ? str : EMPTY_STRING);
	}
	
	public static boolean nullSafeEquals(Object o1, Object o2) {
		if (o1 == o2) {
			return true;
		}
		if (o1 == null || o2 == null) {
			return false;
		}
		if (o1.equals(o2)) {
			return true;
		}
		if (o1.getClass().isArray() && o2.getClass().isArray()) {
			if (o1 instanceof Object[] && o2 instanceof Object[]) {
				return Arrays.equals((Object[]) o1, (Object[]) o2);
			}
			if (o1 instanceof boolean[] && o2 instanceof boolean[]) {
				return Arrays.equals((boolean[]) o1, (boolean[]) o2);
			}
			if (o1 instanceof byte[] && o2 instanceof byte[]) {
				return Arrays.equals((byte[]) o1, (byte[]) o2);
			}
			if (o1 instanceof char[] && o2 instanceof char[]) {
				return Arrays.equals((char[]) o1, (char[]) o2);
			}
			if (o1 instanceof double[] && o2 instanceof double[]) {
				return Arrays.equals((double[]) o1, (double[]) o2);
			}
			if (o1 instanceof float[] && o2 instanceof float[]) {
				return Arrays.equals((float[]) o1, (float[]) o2);
			}
			if (o1 instanceof int[] && o2 instanceof int[]) {
				return Arrays.equals((int[]) o1, (int[]) o2);
			}
			if (o1 instanceof long[] && o2 instanceof long[]) {
				return Arrays.equals((long[]) o1, (long[]) o2);
			}
			if (o1 instanceof short[] && o2 instanceof short[]) {
				return Arrays.equals((short[]) o1, (short[]) o2);
			}
		}
		return false;
	}
} 