package us.fatehi._working;


import java.time.Instant;
import java.time.ZoneId;
import java.time.zone.ZoneOffsetTransitionRule;
import java.util.List;

/**
 * http://www.javaworld.com/article/2078757/java-se/java-se-java-101-the-next-generation-it-s-time-for-a-change.html?page=7
 */
public class PrintTimeZone
{

  public static void main(final String[] args)
  {

    ZoneId zid = ZoneId.of("Europe/London");// ZoneId.systemDefault();

    System.out.printf("Zone Id = %s%n", zid);

    System.out.printf("Transition Rules = %n");
    List<ZoneOffsetTransitionRule> transitionRules = zid.getRules()
      .getTransitionRules();
    for (ZoneOffsetTransitionRule zoneOffsetTransitionRule: transitionRules)
    {
      System.out.printf("\t%s%n", zoneOffsetTransitionRule);
    }

    System.out.printf("DST in effect: %b%n",
                      zid.getRules().isDaylightSavings(Instant.now()));

  }

}
