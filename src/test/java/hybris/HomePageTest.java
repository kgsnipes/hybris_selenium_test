package hybris;

import kg.hybris.actions.HybrisStorefrontNavigationAction;
import kg.hybris.actions.HybrisUserAction;
import kg.hybris.actions.ProductSearchHybrisUserAction;
import kg.hybris.config.AppConfig;

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
public class HomePageTest {

    @Autowired
    HybrisBrowser hybrisBrowser;

    @Test
    public void loadHomepage()throws Exception
    {
        hybrisBrowser.executeAction(new HybrisStorefrontNavigationAction(hybrisBrowser,"yacceleratorstorefront/?site=apparel-uk"));
        hybrisBrowser.executeAction(new ProductSearchHybrisUserAction(hybrisBrowser,"shirts"));
        assert (true);
    }
}
