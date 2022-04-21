package me.webhop.spec

import me.webhop.apollo.Greeter
import spock.lang.Specification

import org.slf4j.Logger;
import org.slf4j.LoggerFactory
import spock.lang.Subject;

class TestGreeterSpec extends Specification {

    Logger logger = LoggerFactory.getLogger(TestGreeterSpec.class)

    @Subject
    Greeter greeter = new Greeter()

    def "Spock say Hello world!"(){

        when: "greeting the world"

        String someone = "World"
        logger.info("greeter should Include TheOneBeingGreeted");

        then: "our greeter will say hello world"
            greeter.greet(someone).contains("World")
            logger.info(greeter.greet(someone));
    }
    def "Greeter is bigger then Texas"(){

        when: "greeting texas"
            String someone = "Texas"
            logger.info("greeting someone from texas");

        then: "greeter phrase is bigger than texas"
           greeter.greet(someone).length() > someone.length()
           logger.info(greeter.greet(someone));
    }
}
