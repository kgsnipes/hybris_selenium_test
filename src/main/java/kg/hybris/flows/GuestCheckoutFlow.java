package kg.hybris.flows;

import kg.hybris.actions.*;
import kg.hybris.dto.Address;
import kg.hybris.dto.Payment;
import kg.hybris.setup.HybrisBrowser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * Created by kaushik on 6/18/2017.
 */
public class GuestCheckoutFlow {

    @Autowired
    HybrisBrowser hybrisBrowser;

    @Autowired
    @Qualifier("sampleShippingAddress")
    Address shippingAddress;

    @Autowired
    @Qualifier("samplePaymentInfo")
    Payment payment;

    public void performGuestCheckoutFlow()throws Exception
    {
        hybrisBrowser.executeAction(new HybrisStorefrontNavigationAction(hybrisBrowser,"/?site=apparel-uk"))
        .executeAction(new ProductSearchHybrisUserAction(hybrisBrowser,"shirts"))
        .executeAction(new AddtoCartFromProductSearchPageHybrisUserAction(hybrisBrowser,3))
        .executeAction(new NavigateToCartFromMiniCartLinkPageHybrisUserAction(hybrisBrowser,"/cart"))
        .executeAction(new ClickCheckoutButtonOnCartPageHybrisUserAction(hybrisBrowser))
        .executeAction(new AddGuestDetailsForCheckoutLoginUserAction(hybrisBrowser,shippingAddress.getEmail()))
        .executeAction(new AddGuestShippingAddressForCheckoutUserAction(hybrisBrowser,shippingAddress))
        .executeAction(new AddGuestShippingMethodCheckoutUserAction(hybrisBrowser,"standard-gross"))
        .executeAction(new AddGuestPaymentDetailsForCheckoutUserAction(hybrisBrowser,payment))
        .executeAction(new AddGuestReviewOrderForCheckoutUserAction(hybrisBrowser));
        Thread.sleep(10000);
    }
}
