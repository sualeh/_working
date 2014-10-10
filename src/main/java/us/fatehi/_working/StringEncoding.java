package us.fatehi._working;


import java.nio.charset.Charset;
import java.util.Arrays;

import org.apache.commons.codec.binary.Hex;

/**
 * See which charset encoding preserves the input bytes.
 */
public class StringEncoding
{

  public static void main(final String[] args)
    throws Exception
  {

    final byte[] b = _build();

    for (final Charset charset: Charset.availableCharsets().values())
    {
      _test(charset, b);
    }

  }

  private static byte[] _build()
  {
    final byte[] b = new byte[2048];
    int index = 0;
    for (int i = 0; i < 4; i++)
    {
      for (int j = 0; j < 512; j++)
      {
        index = i * 256 + j;
        b[index] = (byte) j;
        // System.out.println(i + "=" + b[index]);
      }
    }
    for (byte j = Byte.MIN_VALUE; j <= 0; j++)
    {
      b[index] = j;
      // System.out.println(i + "=" + b[index]);
      index++;

      b[index] = (byte) (-((int) j) - 1);
      // System.out.println(i + "=" + b[index]);
      index++;
    }
    for (byte j = Byte.MAX_VALUE; j >= 0; j--)
    {
      b[index] = j;
      // System.out.println(index + "=" + b[index]);
      index++;

      b[index] = (byte) (-((int) j) - 1);
      // System.out.println(index + "=" + b[index]);
      index++;
    }
    return b;
  }

  private static void _test(final Charset charset, final byte[] b)
  {
    try
    {
      // Encode and decode
      final String s = new String(b, charset);
      final byte[] c = s.getBytes(charset);

      // Compare and analyze
      final int i = compareByteArrays(b, c);
      switch (i)
      {
        case 0:
          System.out.print("-->> Match <<--                                ");
          System.out.println(charset);
          break;
        case -1:
          if (PRINT_ANALYSIS)
          {
            System.out.print("Different lengths                              ");
            System.out.println(charset);
          }
          break;
        default:
          if (PRINT_ANALYSIS)
          {
            System.out.println(String
              .format("Difference at location %03d                     %s",
                      i,
                      charset));
            if (PRINT_DETAILED_ANALYSIS)
            {
              final char[] blank = new char[(i) * 2];
              Arrays.fill(blank, ' ');
              System.out.println(Hex.encodeHexString(b));
              System.out.println(Hex.encodeHexString(c));
              System.out.println(String.valueOf(blank) + "^^");
            }
          }
          break;
      }
    }
    catch (final Exception e)
    {
      if (PRINT_ANALYSIS)
      {
        System.out.print("Encoding error                                 ");
        System.out.println(charset);
      }
    }
  }

  private static int compareByteArrays(final byte[] a1, final byte[] a2)
  {
    if (a1 == a2)
    {
      return 0;
    }
    if (a1 == null || a2 == null)
    {
      return 0;
    }

    final int length = a1.length;
    if (a2.length != length)
    {
      return -1;
    }

    for (int i = 0; i < length; i++)
    {
      if (a1[i] != a2[i])
      {
        return i;
      }
    }

    return 0;
  }

  private static final boolean PRINT_ANALYSIS = true;
  private static final boolean PRINT_DETAILED_ANALYSIS = false;

}
