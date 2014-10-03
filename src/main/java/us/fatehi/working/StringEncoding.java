package us.fatehi.working;


import java.nio.charset.Charset;

/**
 * See which charset encoding preserves the input bytes.
 */
public class StringEncoding
{

  private static final boolean DEBUG = false;

  public static void main(final String[] args)
    throws Exception
  {
    printAllCharsets();
    final Charset charset = Charset.forName("ISO-8859-1");
    System.out.println("Using charset: " + charset);

    final byte[] b = new byte[1024];
    for (int i = 0; i < 4; i++)
    {
      for (int j = 0; j < 256; j++)
      {
        b[i * 256 + j] = (byte) j;
      }
    }

    printByteArray(b);

    final String s = new String(b, charset);
    final byte[] c = s.getBytes(charset);
    compareByteArrays(b, c);

    printByteArray(c);

  }

  private static void compareByteArrays(final byte[] a1, final byte[] a2)
  {
    if (a1 == a2)
    {
      return;
    }
    if (a1 == null || a2 == null)
    {
      return;
    }

    final int length = a1.length;
    if (a2.length != length)
    {
      return;
    }

    for (int i = 0; i < length; i++)
    {
      if (a1[i] != a2[i])
      {
        System.out.print("Array contents do not match at location " + i + ": ");
        printByte(a1[i]);
        System.out.print(" != ");
        printByte(a2[i]);
        return;
      }
    }

    System.out.println("Array contents are the same");
    return;
  }

  private static void printAllCharsets()
  {
    if (!DEBUG)
    {
      return;
    }
    System.out.println("All charsets:\n");
    System.out.println(Charset.availableCharsets().keySet().toString()
      .replaceAll("\\[", "").replaceAll(", ", "\n").replaceAll("\\]", ""));
  }

  private static void printByte(final byte b)
  {
    System.out.print(String.format("%03d=%s",
                                   (int) b,
                                   Integer.toBinaryString(b & 255 | 256)
                                     .substring(1)));
  }

  private static void printByteArray(final byte[] b)
  {
    if (!DEBUG)
    {
      return;
    }

    if (b == null)
    {
      return;
    }

    for (int k = 0; k < b.length; k++)
    {
      printByte(b[k]);
      System.out.print(",");
      if ((k + 1) % 4 == 0)
      {
        System.out.println();
      }
    }
  }

}
