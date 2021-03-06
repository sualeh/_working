package us.fatehi._working;

import java.util.Scanner;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

public class Base64Decode {

  public static void main(final String[] args) {
    System.out.println("Enter Base-64 encoded strings.");
    try (Scanner in = new Scanner(System.in); ) {
      while (true) {
        final String base64Encoded = in.next();
        final byte[] decodeBase64 = Base64.decodeBase64(base64Encoded);
        System.out.println("Base-64 encoded string: " + base64Encoded);
        System.out.println(String.format("Decoded to %d bytes", decodeBase64.length));
        System.out.println("Hex encoded: " + Hex.encodeHexString(decodeBase64).toUpperCase());
      }
    }
  }
}
