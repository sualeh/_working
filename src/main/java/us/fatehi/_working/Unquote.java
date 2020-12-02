package us.fatehi._working;

import java.util.regex.Pattern;

public class Unquote {
  public static void main(String[] args) {}

  protected static String unquotedName(final String name) {
    if (isBlank(name) && name.length() < 3) {
      return name;
    }

    String unquotedName = name;

    if (Pattern.matches("\\p{Punct}.*\\p{Punct}", name)) {
      final char firstChar = name.charAt(0);
      final char lastChar = name.charAt(name.length() - 1);
      if (firstChar == lastChar) {
        unquotedName = name.substring(1, name.length() - 1);
      }
    }
    return unquotedName;
  }

  private static boolean isBlank(String name) {
    return name == null || name.trim().isEmpty();
  }
}
