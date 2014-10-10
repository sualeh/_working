package us.fatehi._working;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Currency;
import java.util.List;

public class Currencies
{

  public static void main(final String[] args)
  {
    // Sort currencies by display name
    final List<Currency> currencies = new ArrayList<>(Currency.getAvailableCurrencies());
    Collections.sort(currencies, new Comparator<Currency>()
    {

      @Override
      public int compare(final Currency o1, final Currency o2)
      {
        return o1.getDisplayName().compareTo(o2.getDisplayName());
      }
    });

    // Print currencies table
    System.out
      .println("NUMERIC_CODE\tCURRENCY_CODE\tCURRENCY\tFRACTION_DIGITS");
    for (final Currency currency: currencies)
    {
      System.out.println(String.format("%03d\t%s\t%s\t%d",
                                       currency.getNumericCode(),
                                       currency.getCurrencyCode(),
                                       currency.getDisplayName(),
                                       currency.getDefaultFractionDigits()));
    }

  }

}
