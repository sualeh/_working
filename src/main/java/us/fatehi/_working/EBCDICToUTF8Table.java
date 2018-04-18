package us.fatehi._working;


import java.nio.charset.Charset;

public class EBCDICToUTF8Table
{

  public static void main(final String[] args)
  {
    final String[] EBCDIC_CODE_PAGES = { "IBM037", "IBM01148", "IBM1047" };
    for (final String element: EBCDIC_CODE_PAGES)
    {
      final Charset charset = Charset.forName(element);
      for (int i = 0; i < 256; i++)
      {
        // Convert EBCDIC character to a Java string
        final String chstr = new String(new byte[] { (byte) i }, charset);
        // Get character (code point) properties
        final int ch = chstr.codePointAt(0);
        final String name = Character.getName(ch);
        final boolean isISOControl = Character.isISOControl(ch);
        // Print properties
        System.out.println(String.format("%s %3d = %s = %s",
                                         charset.displayName(),
                                         i,
                                         isISOControl? "?": chstr,
                                         name));
      }
      System.out.println("\n\n");
    }
  }

}
