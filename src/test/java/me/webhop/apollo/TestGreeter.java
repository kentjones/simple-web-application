package me.webhop.apollo;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.containsString;

public class TestGreeter {
  private Logger logger = LoggerFactory.getLogger(TestGreeter.class);
  private Greeter greeter;

  @Before
  public void setup() {
    logger.info("initialize test");
    greeter = new Greeter();
  }

  @Test
  public void greetShouldIncludeTheOneBeingGreeted() {

    logger.info("greetShouldIncludeTheOneBeingGreeted");

    String someone = "World";

    assertThat(greeter.greet(someone), containsString(someone));

    logger.info(greeter.greet(someone));
  }

  @Test
  public void greetShouldIncludeGreetingPhrase() {
    logger.info("greetShouldIncludeGreetingPhrase");

    String someone = "Texas";

    assertThat(greeter.greet(someone).length(), is(greaterThan(someone.length())));

    logger.info(greeter.greet(someone));
  }
}
