package kg.hybris;

import kg.hybris.flows.GuestCheckoutFlow;
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
    @Autowired
    GuestCheckoutFlow guestCheckoutFlow;
    public static void main( String[] args )throws  Exception
    {
        System.out.println( "Run the test cases" );

        new App().guestCheckoutFlow.performGuestCheckoutFlow();
    }

}
