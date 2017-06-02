package us.fatehi._working;


public class NegativeZero
{

  public static void main(final String[] args)
  {
    // Java (and the IEEE 754 floating-point standard) define two
    // representations of zero: negative zero and positive zero.

    final double a = 1.0;
    final double x = (a - a) / a; // positive zero ( 0.0)
    final double y = (a - a) / -a; // negative zero (-0.0)
    System.out.println(x);
    System.out.println(y);
  }

}
