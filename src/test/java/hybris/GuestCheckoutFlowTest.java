package hybris;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import kg.hybris.App;
import kg.hybris.actions.*;
import kg.hybris.config.AppConfig;

import kg.hybris.flows.GuestCheckoutFlow;
import kg.hybris.setup.BrowserDisplayMode;
import kg.hybris.setup.HybrisBrowser;
import org.apache.log4j.Logger;
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

    private static final Logger LOG = Logger.getLogger(GuestCheckoutFlowTest.class);
    @Autowired
    HybrisBrowser hybrisBrowser;

    @Autowired
    GuestCheckoutFlow guestCheckoutFlow;

    @Test
    public void performGuestCheckoutFlowTestDesktop()throws Exception
    {
        hybrisBrowser.setBrowserDisplayMode(BrowserDisplayMode.DESKTOP);
        guestCheckoutFlow.performFlow(hybrisBrowser);
        Gson gson = new Gson();
        LOG.info( gson.toJson(guestCheckoutFlow.getFlowResult()));
        Thread.sleep(5000);

        assert (true);
    }

    @Test
    public void performGuestCheckoutFlowTestMobile()throws Exception
    {
        hybrisBrowser.setBrowserDisplayMode(BrowserDisplayMode.MOBILE);
        guestCheckoutFlow.performFlow(hybrisBrowser);
        Gson gson = new Gson();
        LOG.info( gson.toJson(guestCheckoutFlow.getFlowResult()));
        Thread.sleep(5000);
        assert (true);
    }

    @Test
    public void performGuestCheckoutFlowTestTablet()throws Exception
    {
        hybrisBrowser.setBrowserDisplayMode(BrowserDisplayMode.TABLET);
        guestCheckoutFlow.performFlow(hybrisBrowser);
        Gson gson = new Gson();
        LOG.info( gson.toJson(guestCheckoutFlow.getFlowResult()));
        Thread.sleep(5000);
        assert (true);
    }
}
