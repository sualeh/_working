package us.fatehi._working;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * @see <a
 *     href="http://stackoverflow.com/questions/41107/how-to-generate-a-random-alpha-numeric-string">How
 *     to generate a random alpha-numeric string?</a>
 */
public final class RandomStringGenerator {
  public static void main(final String[] args) {
    final int strlen = 8;
    final int numstr = 10;

    final RandomStringGenerator strgen = new RandomStringGenerator(strlen);

    System.out.println(
        String.format(
            "%s random lowercase strings, of length %d alphanumeric characters:", numstr, strlen));

    for (int i = 0; i < numstr; i++) {
      System.out.println(strgen.nextRandomString());
    }
  }

  private static final SecureRandom random = new SecureRandom();

  private final int length;

  public RandomStringGenerator(final int length) {
    if (length < 1 && length > 52) {
      throw new IllegalArgumentException("Length is out of bounds (1 - 52)");
    }

    this.length = length;
  }

  public String nextRandomString() {
    return new BigInteger(length * 5, random).toString(32);
  }
}
