package us.fatehi._working;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Comparator;

public class StaticComparator {

  private static class NewObject implements Comparable<NewObject> {

    private final String value;

    public NewObject(final String value) {
      this.value = value;
    }

    @Override
    public int compareTo(final NewObject o) {
      return comparator.compare(this, o);
    }

    public String getValue() {
      return value;
    }
  }

  /** IMPORTANT: Do not return null in the comparing function! See commented lines below. */
  private static final Comparator<NewObject> comparator =
      Comparator.nullsFirst(
          Comparator.comparing(NewObject::getValue, String.CASE_INSENSITIVE_ORDER));

  public static void main(final String[] args) {
    final NewObject n1 = new NewObject(null);
    final NewObject n2 = new NewObject("Hello");
    final NewObject n3 = new NewObject("hello");
    final NewObject n4 = new NewObject("Goodbye");

    // No need to do explicit null checks if you use nullsFirst
    assertThat(comparator.compare(null, null), is(0));
    assertThat(comparator.compare(null, n1), is(-1));
    assertThat(comparator.compare(n1, null), is(1));

    assertThat(n1.compareTo(null), is(1));

    /*
    assertThat(n2.compareTo(n1), is(0));
    assertThat(n1.compareTo(n2), is(0));
    */

    assertThat(n2.compareTo(n3), is(0));
    assertThat(n3.compareTo(n2), is(0));

    assertThat(n2.compareTo(n4), is(1));
    assertThat(n4.compareTo(n2), is(-1));
  }
}
