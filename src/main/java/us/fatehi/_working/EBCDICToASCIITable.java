package us.fatehi._working;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.Normalizer;

public class EBCDICToASCIITable {

  public static void main(final String[] args) throws Exception {
    final String[] EBCDIC_CODE_PAGES = {"IBM037", "IBM01148", "IBM1047"};
    for (final String element : EBCDIC_CODE_PAGES) {
      final Charset charset = Charset.forName(element);
      for (int i = 0; i < 256; i++) {
        // Convert EBCDIC character to a Java string
        final byte ebcdicChar = (byte) i;
        final String chstr = new String(new byte[] {ebcdicChar}, charset);
        final char normchstr = normalizeChar(ebcdicChar, charset);

        // Get character (code point) properties
        final int ch = chstr.codePointAt(0);
        final int numUTF8Bytes = chstr.getBytes(StandardCharsets.UTF_8).length;
        final String unicodeCharacterName = Character.getName(ch);
        final boolean isISOControl = Character.isISOControl(chstr.codePointAt(0));
        final boolean isASCIIPrintable = ch > 31 && ch < 127;

        // Sanity checks
        final boolean alternateASCIIPrintable = numUTF8Bytes == 1 && !isISOControl;

        if (isASCIIPrintable && !alternateASCIIPrintable) {
          throw new Exception(
              String.format(
                  "%3d %d %b - We have not detected printable ASCII characters correctly",
                  ch, numUTF8Bytes, isISOControl));
        }

        // Print properties
        System.out.println(
            String.format(
                "original \"%s\"; converted \"%s\"; %s ASCII printable; EBCDIC code point %3d; Unicode code point %3d; code page %s; Unicode name %s;",
                isISOControl ? "?" : chstr,
                normchstr,
                isASCIIPrintable ? "   " : "not",
                i,
                ch,
                charset.displayName(),
                unicodeCharacterName));
      }
      System.out.println("\n\n");
    }
  }

  private static char normalizeChar(final byte ebcdicChar, final Charset charset) {
    // Decode the EBCDIC character (which is a byte), based on the
    // EBCDIC charset
    final String decodedString = new String(new byte[] {ebcdicChar}, charset);
    // Normalize by removing accents and diacriticals
    final char normalizedChar = Normalizer.normalize(decodedString, Normalizer.Form.NFKD).charAt(0);
    // Check for characters within the ASCII printable range
    if (normalizedChar > 31 && normalizedChar < 127) {
      return normalizedChar;
    } else {
      return '?';
    }
  }
}
