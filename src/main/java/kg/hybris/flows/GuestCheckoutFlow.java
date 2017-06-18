package kg.hybris.flows;

import kg.hybris.actions.*;
import kg.hybris.setup.HybrisBrowser;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by kaushik on 6/18/2017.
 */
public class GuestCheckoutFlow {

    @Autowired
    HybrisBrowser hybrisBrowser;

    public void performGuestCheckoutFlow()throws Exception
    {
        hybrisBrowser.executeAction(new HybrisStorefrontNavigationAction(hybrisBrowser,"yacceleratorstorefront/?site=apparel-uk"));
        Thread.sleep(2000);
        hybrisBrowser.executeAction(new ProductSearchHybrisUserAction(hybrisBrowser,"shirts"));
        Thread.sleep(2000);
        hybrisBrowser.executeAction(new AddtoCartFromProductSearchPageHybrisUserAction(hybrisBrowser,3));
        hybrisBrowser.executeAction(new NavigateToCartFromMiniCartLinkPageHybrisUserAction(hybrisBrowser,"yacceleratorstorefront/cart"));
        Thread.sleep(2000);
        hybrisBrowser.executeAction(new ClickCheckoutButtonOnCartPageHybrisUserAction(hybrisBrowser));
        Thread.sleep(5000);
    }
}
