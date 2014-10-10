package us.fatehi._working;


import java.util.regex.Pattern;

public class RegularExpressionMultipleExcludes
{

  private static String regex = ".*\\.(?!(MDRS_|MDRT_|MDOT_)).*\\..*";
  //                             .*\.(?!(MDRS_|MDRT_|MDOT_)).*

  public static boolean excludeMultiple(String text)
  {
    final boolean matches = Pattern.matches(regex, text);
    System.out.println(text + "=" + matches);
    return matches;
  }

  public static void main(String[] args)
  {
    System.out.println(regex);
    excludeMultiple("XX.MDRS_YY.ZZZZ");
    excludeMultiple("XX.MDRT_YY.ZZZZ");
    excludeMultiple("XX.MDOT_YY.ZZZZ");
    excludeMultiple("XX.VAP_YY.ZZZZ");
  }

}
