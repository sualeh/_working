package us.fatehi._working;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.Serializable;

import org.apache.commons.lang3.SerializationException;
import org.apache.commons.lang3.SerializationUtils;
import org.junit.jupiter.api.Test;

public class SerializationUnitTest {

  private static class Unserializable implements Serializable {
    private static final long serialVersionUID = 8233540218856893869L;

    private final UnserializableMember number;

    public Unserializable(final int number) {
      this.number = new UnserializableMember(number);
    }

    public int getNumber() {
      return number.getNumber();
    }
  }

  private static class UnserializableMember
  // implements Serializable
  {
    private final int number;

    public UnserializableMember(final int number) {
      this.number = number;
    }

    public int getNumber() {
      return number;
    }
  }

  @Test
  public void testSerializable() throws Exception {
    final Unserializable unserializable1 = new Unserializable(20);
    assertThat(unserializable1.getNumber(), is(20));

    assertThrows(SerializationException.class, () -> SerializationUtils.roundtrip(unserializable1));
  }
}
