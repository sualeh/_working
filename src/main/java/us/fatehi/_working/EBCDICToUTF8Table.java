package us.fatehi._working;


import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

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
        final int numUTF8Bytes = chstr.getBytes(StandardCharsets.UTF_8).length;
        final String name = Character.getName(ch);
        final boolean isISOControl = Character.isISOControl(ch);
        final boolean isASCIIPrintable = numUTF8Bytes == 1 && !isISOControl;
        // Print properties
        System.out.println(String.format("%s %3d = %s %s = %s",
                                         charset.displayName(),
                                         i,
                                         isISOControl? "?": chstr,
                                         !isASCIIPrintable? "?": chstr,
                                         name));
      }
      System.out.println("\n\n");
    }
  }

}
