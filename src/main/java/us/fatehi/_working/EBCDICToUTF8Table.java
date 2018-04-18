package us.fatehi._working;


import java.nio.charset.Charset;

public class EBCDICToUTF8Table
{

  public static void main(final String[] args)
  {
    final String[] EBCDIC_CODE_PAGES = { "IBM037", "IBM01148", "IBM1047" };
    for (int codePageIndex = 0; codePageIndex < EBCDIC_CODE_PAGES.length; codePageIndex++)
    {
      Charset charset = Charset.forName(EBCDIC_CODE_PAGES[codePageIndex]);
      for (int i = 0; i < 256; i++)
      {
        // Convert EBCDIC character to a Java string
        final String convertedEbcdicChar = new String(new byte[] { (byte) i },
                                                      charset);
        // Get character (code point) properties
        final int ch = convertedEbcdicChar.codePointAt(0);
        final String name = Character.getName(ch);
        final boolean isISOControl = Character.isISOControl(ch);
        // Print properties
        System.out.println(String.format("%s %3d = %s = %s",
                                         charset.displayName(),
                                         i,
                                         isISOControl? "?": convertedEbcdicChar,
                                         name));
      }
      System.out.println("\n\n");
    }
  }

}
