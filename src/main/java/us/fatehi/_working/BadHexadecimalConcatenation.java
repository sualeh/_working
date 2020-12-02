package us.fatehi._working;

/**
 * <a href= "https://find-sec-bugs.github.io/bugs.htm#BAD_HEXA_CONVERSION">Bad hexadecimal
 * concatenation</a>
 */
public class BadHexadecimalConcatenation {

  public static void main(final String[] args) throws Exception {
    final byte[] bytes1 = new byte[] {0x06, 0x79};
    System.out.println(buildString1(bytes1));
    System.out.println(buildString2(bytes1));

    final byte[] bytes2 = new byte[] {0x67, 0x09};
    System.out.println(buildString1(bytes2));
    System.out.println(buildString2(bytes2));

    System.out.println(Integer.toHexString(0x6709));
    System.out.println(Integer.toHexString(0x0679));
  }

  private static String buildString1(final byte[] bytes) {
    final StringBuilder stringBuilder = new StringBuilder();
    for (final byte b : bytes) {
      stringBuilder.append(Integer.toHexString(b & 0xFF));
    }
    return stringBuilder.toString();
  }

  private static String buildString2(final byte[] bytes) {
    final StringBuilder stringBuilder = new StringBuilder();
    for (final byte b : bytes) {
      stringBuilder.append(String.format("%02X", b));
    }
    return stringBuilder.toString();
  }
}
