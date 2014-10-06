package us.fatehi.working;


import java.math.BigInteger;
import java.util.Scanner;

import org.apache.commons.codec.binary.Base64;

public class Base64Decode
{

  public static void main(final String[] args)
  {
    System.out.println("Enter Base-64 encoded strings.");
    try (Scanner in = new Scanner(System.in);)
    {

      while (true)
      {
        final String base64Encoded = in.next();
        final byte[] decodeBase64 = Base64.decodeBase64(base64Encoded);
        System.out.println("Base-64 encoded string: " + base64Encoded);
        System.out.println(String.format("Decoded to %d bytes",
                                         decodeBase64.length));
        System.out.println(String.format("Hex encoded: %040x",
                                         new BigInteger(1, decodeBase64)));
      }
    }
  }

}
