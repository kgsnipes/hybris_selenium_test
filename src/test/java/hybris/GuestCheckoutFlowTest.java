package hybris;

import kg.hybris.actions.*;
import kg.hybris.config.AppConfig;

import kg.hybris.flows.GuestCheckoutFlow;
import kg.hybris.setup.HybrisBrowser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by kaushik on 6/18/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
public class GuestCheckoutFlowTest {

    @Autowired
    GuestCheckoutFlow guestCheckoutFlow;

    @Test
    public void performGuestCheckoutFlowTest()throws Exception
    {
        guestCheckoutFlow.performGuestCheckoutFlow();
        Thread.sleep(5000);

        throw new Exception("summa");
        //assert (true);
    }
}
