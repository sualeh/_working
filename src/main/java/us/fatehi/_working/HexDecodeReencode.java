package us.fatehi._working;

import java.io.UnsupportedEncodingException;
import java.util.Scanner;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

public class HexDecodeReencode {

  public static void main(final String[] args) throws UnsupportedEncodingException {
    System.out.println("Enter hex encoded strings.");
    try (Scanner in = new Scanner(System.in); ) {
      while (true) {
        try {
          final String hexEncoded = in.next();
          System.out.println("Hex encoded string: " + hexEncoded);

          final byte[] decodeHex = Hex.decodeHex(hexEncoded.toCharArray());
          final String bytesAsString = new String(decodeHex, "ISO-8859-1");
          System.out.println(
              String.format("Decoded to %d bytes: ~%s~", decodeHex.length, bytesAsString));

          final String hexReencoded = Hex.encodeHexString(decodeHex);
          System.out.println("Hex re-encoded: " + hexReencoded.toUpperCase());
        } catch (final DecoderException e) {
          e.printStackTrace();
        }
      }
    }
  }
}
