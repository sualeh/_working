package us.fatehi._working;


import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class EBCDICToUTF8Table
{

  public static void main(final String[] args)
    throws Exception
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
        final boolean isASCIIPrintable = ch > 31 && ch < 127;
        // Sanity checks
        final boolean alternateASCIIPrintable = numUTF8Bytes == 1
                                                && !isISOControl;
        if (isASCIIPrintable && !alternateASCIIPrintable)
        {
          throw new Exception(String
            .format("%3d %d %b - We have not detected printable ASCII characters correctly",
                    ch,
                    numUTF8Bytes,
                    isISOControl));
        }
        // Print properties
        System.out.println(String
          .format("\"%s\"; %s printable; EBCDIC code point %3d; Unicode code point %3d; code page %s; Unicode name %s;",
                  isISOControl? "?": chstr,
                  isASCIIPrintable? "   ": "not",
                  i,
                  ch,
                  charset.displayName(),
                  name));
      }
      System.out.println("\n\n");
    }
  }

}
