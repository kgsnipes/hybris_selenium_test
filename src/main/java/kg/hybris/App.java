package kg.hybris;

import kg.hybris.actions.AbstractHybrisUserAction;
import kg.hybris.flows.GuestCheckoutFlow;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
@Configuration

@ComponentScan(basePackages = { "kg.hybris.*" })
public class App 
{
    private static final Logger LOG = Logger.getLogger(App.class);
    @Autowired
    GuestCheckoutFlow guestCheckoutFlow;
    public static void main( String[] args )throws  Exception
    {

        LOG.info("PLEASE RUN THE TEST CASES");

    }

}
