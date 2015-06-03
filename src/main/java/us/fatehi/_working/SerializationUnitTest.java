package us.fatehi._working;


import static org.junit.Assert.assertEquals;

import java.io.Serializable;

import org.apache.commons.lang3.SerializationUtils;
import org.junit.Test;

import uk.co.jemos.podam.api.PodamFactoryImpl;

public class SerializationUnitTest
{

  private static class Unserializable
    implements Serializable
  {
    private static final long serialVersionUID = 8233540218856893869L;

    private final UnserializableMember number;

    public Unserializable(final int number)
    {
      this.number = new UnserializableMember(number);
    }

    public int getNumber()
    {
      return number.getNumber();
    }

  }

  private static class UnserializableMember
  // implements Serializable
  {
    private final int number;

    public UnserializableMember(final int number)
    {
      this.number = number;
    }

    public int getNumber()
    {
      return number;
    }

  }

  @Test
  public void testSerializable()
    throws Exception
  {
    final Unserializable unserializable1 = new PodamFactoryImpl()
      .manufacturePojo(Unserializable.class);
    final Unserializable clonedUnserializable1 = SerializationUtils
      .roundtrip(unserializable1);

    System.out.println(unserializable1.getNumber());
    assertEquals(unserializable1.getNumber(), clonedUnserializable1.getNumber());
  }

}
