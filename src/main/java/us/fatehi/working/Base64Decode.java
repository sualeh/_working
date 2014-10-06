package us.fatehi.working;


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
        System.out.println(String.format("Decoded to %d binary bytes",
                                         decodeBase64.length));
        System.out.println("Decoded byes:");
        for (int i = 0; i < decodeBase64.length; i++)
        {
          final byte b = decodeBase64[i];
          System.out.print(String.format("Byte #%03d: ", i));
          printByte(b);
          System.out.println();
        }
      }
    }
  }

  private static void printByte(final byte b)
  {
    final int intB = b;
    System.out.print((intB < 0? "-": " ")
                     + String.format("%03d=%s", Math.abs(intB), Integer
                       .toBinaryString(b & 255 | 256).substring(1)));
  }

}
