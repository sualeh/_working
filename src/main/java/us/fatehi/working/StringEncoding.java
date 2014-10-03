package us.fatehi.working;


import java.nio.charset.Charset;

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
            System.out.print(String.format("Location %03d: ", i));
            printByte(b[i]);
            System.out.print(" != ");
            printByte(c[i]);
            System.out.print("   ");
            System.out.println(charset);
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

  private static void printByte(final byte b)
  {
    final int intB = b;
    System.out.print((intB < 0? "-": " ")
                     + String.format("%03d=%s", Math.abs(intB), Integer
                       .toBinaryString(b & 255 | 256).substring(1)));
  }

  private static final boolean PRINT_ANALYSIS = true;

}
