package us.fatehi._working;

// http://stackoverflow.com/questions/23047857/factorial-java-return-0
public class Factorial {

  public static void main(String[] args) {
    final Factorial fact = new Factorial();
    System.out.println(fact.computeFactorial(35));
  }

  int computeFactorial(int number) {
    if (number <= 1) {
      return 1;
    }

    int x = number * computeFactorial(number - 1);
    return x;
  }
}
