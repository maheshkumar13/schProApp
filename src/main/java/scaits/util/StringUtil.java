package scaits.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.Vector;

/**
 * @author Administrator
 * 
 */
public class StringUtil {
	public static String htmlSpecialChars(final String src) {
		return htmlSpecialChars(new StringBuffer(src)).toString();
	}

	/**
	 * Convert html special charactors.
	 * 
	 * @param src
	 *            Source.
	 * @return Changed String.
	 */
	public static StringBuffer htmlSpecialChars(final StringBuffer src) {
		if (src == null) {
			return null;
		}
		int srcLength = src.length();

		for (int i = 0; i < srcLength; i++) {
			/*
			 * if (i > 0 && src.charAt(i - 1) == ' ' && src.charAt(i) == ' ') {
			 * src.replace(i - 1, i, "&nbsp;"); src.replace(i + 5, i + 6,
			 * "&nbsp;"); srcLength += 10; i = i + 10; }
			 */
		}
		return src;
	}

	public static String getHtmlOutCode(String str, String defaultValue) {
		if (str == null) {
			return defaultValue;
		} else {
			return str;
		}
	}

	public static String getToString(Object object) {
		StringBuffer buffer = new StringBuffer("");
		Method[] methods = object.getClass().getDeclaredMethods();
		for (int i = 0; i < methods.length; i++) {
			try {
				String methodName = methods[i].getName();
				if (methodName.startsWith("get") && !methodName.equals("getToString")) {
					String result = "";
					try {
						result = (methods[i].invoke(object)).toString();
					} catch (Exception e) {
					}
					if (result == null) {
						result = "";
					}
					buffer.append(methodName + "=" + result + "\n");
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return buffer.toString();
	}

	public static String leftTrim(String bigString, String ch) {
		if (bigString == null) {
			return null;
		}
		if (bigString.length() == 0) {
			return "";
		}

		while (bigString.substring(0, 1).equals(ch)) {
			bigString = bigString.substring(1, bigString.length());
		}

		return bigString.toString();
	}

	public static String trim(String bigString, int limit) {
		if (bigString == null) {
			return null;
		}
		int newLimit = (limit < bigString.length() ? limit : bigString.length());
		if (newLimit < limit) {
			return bigString;
		}

		StringBuffer trimmedString = new StringBuffer(bigString.substring(0, newLimit));
		trimmedString.append("...");
		return trimmedString.toString();
	}

	public static String getIds(Vector ids) {
		if (ids.size() == 0) {
			return " ";
		}
		StringBuffer idString = new StringBuffer();
		idString.append(ids.get(0));
		for (int i = 1; i < ids.size(); i++) {
			idString.append("," + ids.get(i));
		}
		return idString.toString();
	}

	public static String getNumberTag(int inNumber) {
		String tag = "";
		if (inNumber == 1 || (inNumber > 20 && inNumber % 10 == 1)) {
			tag = "st";
		} else if (inNumber == 2 || (inNumber > 20 && inNumber % 10 == 2)) {
			tag = "nd";
		} else if (inNumber == 3 || (inNumber > 20 && inNumber % 10 == 3)) {
			tag = "rd";
		} else {
			tag = "th";
		}
		return tag;
	}

	public static final String getMD5Code(String str) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] byt = str.getBytes();
			md.update(byt);
			byte[] digBytes = md.digest();
			StringBuffer hexStr = new StringBuffer();
			for (int i = 0; i < digBytes.length; i++) {
				byte tempbyte = digBytes[i];
				String s;
				if (tempbyte >= 0 && tempbyte <= 0xF) {
					s = "0" + Integer.toHexString(0xF & tempbyte);
				} else {
					s = Integer.toHexString(0xFF & tempbyte);
				}
				hexStr.append(s);
			}
			return hexStr.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String splitForHtml(String bigString, int limit) {
		if (bigString == null) {
			return null;
		}
		if (bigString.length() <= limit) {
			return bigString;
		}
		int chunks = (int) Math.ceil(bigString.length() / limit);
		StringBuffer newString = new StringBuffer("");
		for (int i = 0; i <= chunks; i++) {
			try {
				int start = i * limit;
				int end = i * limit + limit;
				if (end > bigString.length()) {
					end = bigString.length();
				}
				newString.append(bigString.substring(start, end));
				newString.append("<br>");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return newString.toString();
	}

	public static String splitForHtml(String bigString, String delimiter, int limit) {
		if (bigString == null) {
			return null;
		}
		StringTokenizer tokenizer = new StringTokenizer(bigString, delimiter);
		StringBuffer newString = new StringBuffer("");
		while (tokenizer.hasMoreTokens()) {
			newString.append(splitForHtml(tokenizer.nextToken(), limit));
			newString.append("<br>");
		}
		return newString.toString();
	}

	public static String getStringFromArray(String[] values) {
		if (values == null || values.length == 0) {
			return "";
		}
		return getStringFromArray(values, ",");
	}

	public static String getStringFromArray(String[] values, String delimiter) {
		if (values == null || values.length == 0) {
			return "";
		}
		StringBuffer sb = new StringBuffer("");
		String comma = "";
		for (int i = 0; i < values.length; i++) {
			sb.append(comma);
			sb.append(values[i]);
			comma = delimiter;
		}
		return sb.toString();
	}

	public static String[] getArrayFromString(String values) {
		if (values == null || values.trim().length() == 0) {
			return new String[0];
		}
		return getArrayFromString(values, ",");
	}

	public static String[] getArrayFromString(String values, String delimiter) {
		if (values == null || values.trim().length() == 0) {
			return new String[0];
		}
		StringTokenizer tokenizer = new StringTokenizer(values, delimiter);
		String[] stringArray = new String[tokenizer.countTokens()];
		for (int i = 0; i < stringArray.length; i++) {
			stringArray[i] = tokenizer.nextToken();
		}
		return stringArray;
	}

	public static Vector getVectorFromString(String values) {
		if (values == null || values.trim().length() == 0) {
			return new Vector();
		}
		return getVectorFromString(values, ",");
	}

	public static Vector getVectorFromString(String values, String delimiter) {
		if (values == null || values.trim().length() == 0) {
			return new Vector();
		}
		StringTokenizer tokenizer = new StringTokenizer(values, delimiter);
		Vector stringVector = new Vector();
		while (tokenizer.hasMoreElements()) {
			stringVector.add(tokenizer.nextToken());
		}
		return stringVector;
	}

	public static String getNameFromEmail(String email) {
		StringTokenizer st = new StringTokenizer(email, ".@");
		String[] names = new String[st.countTokens()];
		int i = 0;
		while (st.hasMoreTokens()) {
			names[i] = st.nextToken();
			i++;
		}
		StringBuffer sb = new StringBuffer("");
		sb.append(names[0].substring(0, 1).toUpperCase());
		sb.append(names[0].substring(1, names[0].length()));
		sb.append(" ");
		sb.append(names[1].substring(0, 1).toUpperCase());
		sb.append(names[1].substring(1, names[1].length()));
		return sb.toString();
	}

	public static String replaceCharacter(String str) {
		if (str == null) {
			return "";
		}
		return str.replace(' ', '-').toLowerCase();
	}

	public static String replaceWord(String original, String find, String replacement) {
		int i = original.indexOf(find);
		if (i < 0) {
			return original;
		}
		do {
			String partBefore = original.substring(0, i);
			String partAfter = original.substring(i + find.length());
			original = partBefore + replacement + partAfter;
			i = original.indexOf(find);
		} while (i > 0);
		return original;
	}

	public static String replaceTagAndBody(String original, String find, String replacement) {
		if (original.indexOf("<" + find + "/>") != -1) {
			original = replaceWord(original, "<" + find + "/>", "");
		}
		int i = original.indexOf("<" + find);
		if (i < 0) {
			return original;
		}
		do {
			String partBefore = original.substring(0, i);
			int j = original.indexOf("</" + find, i);
			String partAfter = original.substring(j + ("</" + find + ">").length());
			original = partBefore + replacement + partAfter;
			i = original.indexOf("<" + find);
		} while (i > 0);
		return original;
	}

	public static String removeTag(String original, String find) {
		int i = original.indexOf("<" + find);
		if (i < 0) {
			return original;
		}
		do {
			String partBefore = original.substring(0, i);
			int j = original.indexOf(">", i);
			int k = original.indexOf("</" + find.trim(), i);
			if (original.indexOf("/>", i) > 0 && (original.indexOf("/>", i) < k || k == -1)) {
				k = original.indexOf("/>", i);
				String partAfter = original.substring(k + ("/>").length());
				original = partBefore + partAfter;
			} else {
				String inside = original.substring(j + 1, k);
				String partAfter = original.substring(k + ("</" + find.trim() + ">").length());
				original = partBefore + inside + partAfter;
			}
			i = original.indexOf("<" + find);
		} while (i > 0);
		return original;
	}

	public static String removeTag(String original, String find, String replacement) {
		if (original.indexOf("<" + find) < 0) {
			return original;
		}
		String clean = removeTag(original, find);
		return replacement + clean;
	}

	public static String removeTagAttributes(String original, String find) {
		int i = original.indexOf("<" + find);
		int lastIndex = 0;
		if (i < 0) {
			return original;
		}
		do {
			String partBefore = original.substring(lastIndex, i);
			int j = original.indexOf(">", i);
			int k = original.indexOf("</" + find.trim(), i);
			String inside = original.substring(j + 1, k);
			String partAfter = original.substring(k + ("</" + find.trim() + ">").length());
			original = partBefore + "<" + find.trim() + ">" + inside + "</" + find.trim() + ">" + partAfter;
			lastIndex = i;
			i = original.indexOf("<" + find, i);
		} while (i > 0);
		return original;
	}

	public static String getUsefulMathText(String original) {
		if (original != null) {
			if (original.indexOf("m:annotation") > 0 || original.indexOf("m:semantics") > 0 || original.indexOf("span") > 0
					|| original.indexOf("<p ") > 0) {
				/*
				 * original = StringUtil.replaceWord(original,
				 * "<m:math xmlns:m=\"http://www.w3.org/1998/Math/MathML\" style=\"background-color:#\">"
				 * , ""); original = StringUtil.replaceWord(original,
				 * "<m:math xmlns='http://www.w3.org/1998/Math/MathML'>", "");
				 * original = StringUtil.replaceWord(original, "<m:math>", "");
				 * original = StringUtil.replaceWord(original,
				 * "<m:math style='background-color:#'>", ""); original =
				 * StringUtil.replaceWord(original, "</m:math>", "");
				 */

				original = replaceWord(original, "<m:semantics>", "", true);
				original = replaceWord(original, "</m:semantics>", "", true);
				original = removeTagAndBody(original, "m:annotation", true);
				// original = removeTag(original, "span");
				return original;
			} else {
				return original;
			}
		}
		return null;
	}

	/*
	 * Returns 6 chars alphanumeric code (promoCode) if true else 6 chars
	 * numeric code(Activation code)
	 */
	public static String getRandomString(boolean alphanumeric, int maxChars) {
		String[] codeArray = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M",
				"N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };
		int maxIndex = 0;
		if (alphanumeric) {
			maxIndex = 35;
		} else {
			maxIndex = 9;
		}
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < maxChars; i++) {
			Random rand = new Random();
			int num = rand.nextInt(maxIndex);
			buffer.append(codeArray[num]);
		}

		return buffer.toString();
	}

	/**
	 * Change to sentence case - ie first character in caps, the rest in lower.
	 * 
	 * @param word
	 *            The word to be manipulated
	 * @return The altered word
	 */
	public static String toSentenceCase(String word) {

		if (word == null || word.length() == 0) {
			return "";
		}
		word = word.trim();
		if (word.length() == 1) {
			return String.valueOf(Character.toUpperCase(word.charAt(0)));
		}
		return Character.toUpperCase(word.charAt(0)) + word.substring(1).toLowerCase();
	}

	/**
	 * Change to sentence case - ie first character in caps, the rest in lower.
	 * 
	 * @param word
	 *            The word to be manipulated
	 * @return The altered word
	 */
	public static String toSentenceCaseChange(String sentence) {

		if (sentence == null || sentence.length() == 0) {
			return "";
		}
		String tempWord = "";
		String[] strArr = sentence.split(",");
		for (int i = 0; i < strArr.length; i++) {
			if (strArr[i] != "" && strArr[i].trim().length() > 0) {
				if (i == 0) {
					tempWord = Character.toUpperCase(strArr[i].charAt(0)) + strArr[i].substring(1).toLowerCase();
				} else {
					tempWord = tempWord + ", " + Character.toUpperCase(strArr[i].trim().charAt(0)) + strArr[i].trim().substring(1).toLowerCase();
				}

				// System.out.println(tempWord + " " + i);
			} else {
				continue;
			}
		}
		String tempWord1 = "";
		strArr = tempWord.split(" ");
		for (int i = 0; i < strArr.length; i++) {
			if (strArr[i].trim().length() == 0) {
				continue;
			}
			tempWord1 = tempWord1 + " " + Character.toUpperCase(strArr[i].charAt(0)) + strArr[i].substring(1).toLowerCase();
			// System.out.println(tempWord1 + " " + i);
		}

		// System.out.println(tempWord1);
		return tempWord1;
	}

	public static List<Long> getNumericTokens(String str) {
		if (str == null) {
			return null;
		}
		StringTokenizer tokenizer = new StringTokenizer(str, " \t\r\n,;:");
		Set<Long> idSet = new HashSet<Long>();
		long start = 0, end = 0;
		while (tokenizer.hasMoreElements()) {
			String id = tokenizer.nextToken();
			if (id.indexOf('-') != -1) {
				StringTokenizer tokenizer1 = new StringTokenizer(str, " \t\r\n,;:-");
				if (tokenizer1.countTokens() == 2) {
					start = Long.parseLong((String) tokenizer1.nextElement());
					end = Long.parseLong((String) tokenizer1.nextElement());
				}
				for (long i = start; i <= end; i++) {
					idSet.add(i);
				}
			} else {
				idSet.add(Long.parseLong(id));
			}
		}

		List<Long> idList = new ArrayList<Long>();
		idList.addAll(idSet);
		return idList;

	}

	public static String formatScratchCardNumber(String num) {
		StringBuffer sb = new StringBuffer();
		sb.append(num.substring(0, 4));
		sb.append('-');
		sb.append(num.substring(4, 8));
		sb.append('-');
		sb.append(num.substring(8, 12));
		sb.append('-');
		sb.append(num.substring(12, 16));
		return sb.toString();
	}

	public static String formatConfirmationCode(String confirmationCode) {
		StringBuffer sb = new StringBuffer();
		sb.append(confirmationCode.substring(0, 5));
		sb.append('-');
		sb.append(confirmationCode.substring(5, 9));
		sb.append('-');
		sb.append(confirmationCode.substring(9, 13));
		sb.append('-');
		sb.append(confirmationCode.substring(13, 18));
		return sb.toString();
	}

	public static List<String> tokenizeCSVtoList(String csvString) {
		if (csvString != null && csvString.length() > 0) {
			List<String> output = new ArrayList<String>();
			StringTokenizer tokenizer = new StringTokenizer(csvString, ",");
			while (tokenizer.hasMoreElements()) {
				String string = (String) tokenizer.nextElement();
				output.add(string.trim());
			}
			return output;
		}
		return null;
	}

	public static void main(String[] args) {
		System.out.println(getTimeDuration("9", "11.1"));
		// try {
		// System.out.println(removeMathAttributes(getFileContent("E:/projects/sample.xhtml")));
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
	}

	public static void main1(String[] args) throws ParseException {
		String line = "216.79.174.100******** Wed May 13 00:00:00 IST 2009 || http://www.Kensium.com/?[?] TimeTaken=157";
		File file = new File("F:/test/urls.txt");
		FileInputStream fis;
		try {
			fis = new FileInputStream(file);
			BufferedReader d = new BufferedReader(new InputStreamReader(fis));
			StringBuilder sb = new StringBuilder();
			String str;
			int errors = 0;
			StringBuffer sb1 = new StringBuffer();
			while ((line = d.readLine()) != null) {
				try {
					int beginIndex = line.indexOf("Wed May 13");
					String substr1 = line.substring(beginIndex, beginIndex + 28);
					beginIndex = line.indexOf("TimeTaken=");
					String substr2 = line.substring(beginIndex + 10, line.length());
					beginIndex = line.indexOf("||");
					int endIndex = line.indexOf("[");
					String substr3 = line.substring(beginIndex + 3, endIndex);
					long endTime = DU.parse(substr1, "EEE MMM dd HH:mm:ss z yyyy").getTime();
					long startTime = endTime - Integer.parseInt(substr2);
					System.out.println(substr3 + "," + DU.format(new Date(startTime), "EEE MMM dd HH:mm:ss z yyyy") + "," + substr1 + "," + substr2);
				} catch (Exception e) {
					// System.out.println(e.getMessage());
					sb1.append(line);
					errors++;
				}
			}
			fis.close();
			// System.out.println(errors);
			// System.out.println(sb1.toString());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static String removeTagAndAttributes(String content, String tag, boolean multiple) {
		if (content == null || content.trim().length() == 0) {
			return content;
		}
		if (!content.contains("<" + tag)) {
			return content;
		}
		StringBuffer newContent = new StringBuffer("");
		int tagStartIndex = content.indexOf("<" + tag);
		String partBefore = content.substring(0, content.indexOf("<" + tag));
		String tagAttribs = content.substring(content.indexOf("<" + tag), content.indexOf(">", content.indexOf("<" + tag)) + 1);
		String partAfter = content.substring(content.indexOf(tagAttribs) + tagAttribs.length());
		partAfter = partAfter.replaceFirst("</" + tag + ">", "");
		// System.out.println(tagAttribs);
		newContent.append(partBefore).append(partAfter);
		// System.out.println(partBefore);
		// System.out.println(partAfter);
		if (multiple) {
			return removeTagAndAttributes(newContent.toString(), tag, multiple);
		}
		return newContent.toString();
	}

	public static String removeTagAndBody(String content, String tag, boolean multiple) {
		if (content == null || content.trim().length() == 0) {
			return content;
		}
		if (!content.contains("<" + tag)) {
			return content;
		}
		StringBuffer newContent = new StringBuffer("");
		int tagStartIndex = content.indexOf("<" + tag);
		String partBefore = content.substring(0, content.indexOf("<" + tag));
		String tagAttribs = content.substring(content.indexOf("<" + tag), content.indexOf(">", content.indexOf("<" + tag)) + 1);
		String partAfter = content.substring(content.indexOf(tagAttribs) + tagAttribs.length());
		String partAfterTag = partAfter.substring(partAfter.indexOf("</" + tag));
		partAfterTag = partAfterTag.replaceFirst("</" + tag + ">", "");
		// System.out.println(tagAttribs);
		newContent.append(partBefore).append(partAfterTag);
		// System.out.println(partBefore);
		// System.out.println(partAfter);
		if (multiple) {
			return removeTagAndBody(newContent.toString(), tag, multiple);
		}
		return newContent.toString();
	}

	public static String removeAttributes(String content, String tag, boolean multiple) {
		if (content == null || content.trim().length() == 0) {
			return content;
		}
		if (content.indexOf("<" + tag + " ") == -1) {
			return content;
		}
		StringBuffer newContent = new StringBuffer("");
		int tagStartIndex = 0;
		if (content.indexOf("<" + tag + " ") != -1) {
			tagStartIndex = content.indexOf("<" + tag + " ");
			String partBefore = content.substring(0, content.indexOf("<" + tag + " "));
			String tagAttribs = content.substring(content.indexOf("<" + tag + " "), content.indexOf(">", content.indexOf("<" + tag + " ")) + 1);
			// if(requiredAttribsList.contains(tagAttribs)
			String partAfter = content.substring(content.indexOf(tagAttribs) + tagAttribs.length());
			// System.out.println(tagAttribs);
			newContent.append(partBefore).append("<" + tag + ">").append(multiple ? removeAttributes(partAfter, tag, multiple) : partAfter);
		}
		// System.out.println(partBefore);
		// System.out.println(partAfter);
		return newContent.toString();
	}

	public static String replaceWord(String content, String find, String replacement, boolean multiple) {
		int i = content.indexOf(find);
		if (i < 0) {
			return content;
		}
		String partBefore = content.substring(0, i);
		String partAfter = content.substring(i + find.length());
		content = partBefore + replacement + partAfter;
		i = content.indexOf(find);
		if (multiple) {
			return replaceWord(content, find, replacement, multiple);
		}
		return content;
	}

	public static String removeAttributes(String content, String tag, String attribute, boolean multiple) {
		if (content == null || content.trim().length() == 0) {
			return content;
		}
		if (content.indexOf("<" + tag) == -1) {
			return content;
		}
		StringBuffer newContent = new StringBuffer("");
		int tagStartIndex = 0;
		if (content.indexOf("<" + tag) != -1) {
			tagStartIndex = content.indexOf("<" + tag);
			String partBefore = content.substring(0, content.indexOf("<" + tag));
			String tagWithAttribs = content.substring(content.indexOf("<" + tag), content.indexOf(">", content.indexOf("<" + tag)) + 1);
			if (tagWithAttribs.toLowerCase().contains(attribute.toLowerCase())) {
				tagWithAttribs = removeSpecificAttribute(tagWithAttribs, attribute);
			}
			String partAfter = content.substring(content.indexOf(tagWithAttribs) + tagWithAttribs.length());
			// System.out.println(tagAttribs);
			newContent.append(partBefore).append(tagWithAttribs).append(multiple ? removeAttributes(partAfter, tag, multiple) : partAfter);
		}
		// System.out.println(partBefore);
		// System.out.println(partAfter);
		return newContent.toString();
	}

	public static String removeSpecificAttribute(String content, String attrib) {
		// String content = "<img src='one.jpg' width='' id=12345678/>";
		int attribStartIndex = getAttribIndex(content, attrib, 0);
		int attribEndIndex = attribStartIndex;
		boolean quoteExists = false;
		String attribValue1 = content.substring(attribStartIndex + 2);
		// String attribValue2 = attribValue1.trim().substring(0,1);
		String attribValue2 = attribValue1.trim().substring(attribValue1.indexOf("=") + 1);
		String attribValue3 = attribValue2.trim().substring(0, 1);
		if (attribValue3.equals("'") || attribValue3.equals("\"")) {
			quoteExists = true;
		}
		if (quoteExists) {
			attribEndIndex = content.indexOf(attribValue3, attribStartIndex + 4);
		} else {
			attribEndIndex = content.indexOf(" ", attribStartIndex + 4);
			if (attribEndIndex <= -1) {
				attribEndIndex = content.indexOf("\t", attribStartIndex + 4);
			}
			if (attribEndIndex <= -1) {
				attribEndIndex = content.indexOf("\n", attribStartIndex + 4);
			}
			if (attribEndIndex <= -1) {
				attribEndIndex = content.indexOf("/", attribStartIndex + 4);
				attribEndIndex--;
			}
			if (attribEndIndex <= -1) {
				attribEndIndex = content.indexOf(">", attribStartIndex + 4);
				attribEndIndex--;
			}
		}
		String attribWithValue = content.substring(attribStartIndex, attribEndIndex + 1);
		// System.out.println(attribWithValue);
		String newContent = content.replaceAll(attribWithValue, "");
		// System.out.println(newContent);
		return newContent;
	}

	public static int getAttribIndex(String content, String attrib, int startIndex) {
		int attribStartIndex = content.indexOf(attrib, startIndex);
		String attribBeforeValue = content.substring(attribStartIndex - 1, attribStartIndex);
		boolean matches = attribBeforeValue.toLowerCase().matches("[a-z]");
		if (matches) {
			return getAttribIndex(content, attrib, attribStartIndex + 1);
		} else {
			return attribStartIndex;
		}
	}

	public static String removeAllTreeTagAttributes(String content, String tag) {
		if (content.indexOf(tag) <= -1) {
			return content;
		}
		content = StringUtil.removeAttributes(content, tag, true);
		Set<String> childTags = getUniqueChildTags(content, tag, 0);
		for (String childTag : childTags) {
			if (childTag.equals("menclose")) {
				System.out.println("Not removing attributes for menclose tag");
			} else {
				content = removeAttributes(content, childTag, true);
			}
		}
		return content;
	}

	public static Set<String> getUniqueChildTags(String content, String tag, int startIndex) {
		Set<String> childTags = new HashSet<String>();

		int tagEndIndex = content.lastIndexOf("</" + tag + ">");
		if (tagEndIndex == -1) {
			return childTags;
		}

		String partBefore = content.substring(0, tagEndIndex);

		int nextTagStartIndex = partBefore.lastIndexOf("</");
		if (nextTagStartIndex == -1) {
			return childTags;
		}
		int nextTagEndIndex = partBefore.indexOf(">", nextTagStartIndex);

		String nextTag = partBefore.substring(nextTagStartIndex + 2, nextTagEndIndex);
		if (nextTag.startsWith("m")) {
			childTags.add(nextTag);
		}
		childTags.addAll(getUniqueChildTags(partBefore, nextTag, 0));
		return childTags;
	}

	public static String removeMathAttributes(String content) {
		return removeAllTreeTagAttributes(content, "math");
	}

	public static String getFileContent(String filePath) throws IOException {
		StringBuffer fileContent = new StringBuffer("");

		RandomAccessFile raf = new RandomAccessFile(filePath, "r");
		String line = null;
		while ((line = raf.readLine()) != null) {
			fileContent.append(line).append("\n");
		}
		raf.close();
		return fileContent.toString();
	}

	public static String getNumberText(int input) {
		String[] numbers = { "Zero", "One", "Two" };
		return numbers[input];
	}

	public static String sentenceCaseChange(String sentence) {
		if (sentence == null || sentence.length() == 0) {
			return "";
		}
		String tempWord = "";
		String[] strArr = sentence.split("_");
		for (int i = 0; i < strArr.length; i++) {
			if (strArr[i] != "" && strArr[i].trim().length() > 0) {
				if (i == 0) {
					tempWord = Character.toUpperCase(strArr[i].charAt(0)) + strArr[i].substring(1).toLowerCase();
				} else {
					tempWord = tempWord + " " + Character.toUpperCase(strArr[i].charAt(0)) + strArr[i].substring(1).toLowerCase();
				}

			} else {
				continue;
			}
		}
		return tempWord;
	}

	public static String getTimeDuration(String startTime, String endTime) {
		startTime = getCorrectTimeFormat(startTime);
		endTime = getCorrectTimeFormat(endTime);
		String result = "00";
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

			Date time1 = sdf.parse(startTime);
			Date time2 = sdf.parse(endTime);

			float l1 = time1.getTime();
			float l2 = time2.getTime();
			int difference = (int) (l2 - l1) / (1000 * 60);
			int hour = 0;
			if (60 <= difference) {
				hour = difference / 60;
				difference = difference % 60;
			}
			if (difference == 0) {
				result = hour + "Hrs";
				return result;
			} else {
				result = hour + ":" + (difference > 9 ? difference : "0" + difference) + "Hrs";
				return result;
			}
		} catch (ParseException e) {
			e.printStackTrace();
			return result;
		}
	}

	private static String getCorrectTimeFormat(String inTime) {
		StringTokenizer tokenizer = new StringTokenizer(inTime, ".:,");
		StringBuffer outTime = new StringBuffer("");
		int tcnt = tokenizer.countTokens();
		outTime.append(tokenizer.nextToken());
		while (tokenizer.hasMoreTokens()) {
			outTime.append(":").append(tokenizer.nextToken());
		}
		if (tcnt == 1) {
			outTime.append(":").append("00");
		}
		return outTime.toString();
	}
}
