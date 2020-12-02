package us.fatehi._working;

import java.util.Arrays;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

import com.ibm.as400.access.AS400PackedDecimal;

public class PackedDecimal {

  public static void main(final String[] args) throws DecoderException {
    // Treat an integer as a double with one place of decimal. The
    // decimal will be .0, and the last byte of packed decimal will be
    // 0f, which includes the sign. Drop the last byte, it is the same
    // value as the hex encoded string.

    final AS400PackedDecimal packedDecimal = new AS400PackedDecimal(5, 1);

    displayBytes(packedDecimal.toBytes(1501));
    displayBytes((byte[]) new Hex().decode("1501"));
  }

  private static void displayBytes(final byte[] bytes) {
    System.out.println(new String(Hex.encodeHex(bytes)) + ": " + Arrays.toString(bytes));
  }
}
