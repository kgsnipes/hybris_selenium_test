package kg.hybris;

import kg.hybris.flows.GuestCheckoutFlow;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

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
