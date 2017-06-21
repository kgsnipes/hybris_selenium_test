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
        hybrisBrowser.executeAction(new HybrisStorefrontNavigationAction("/?site=apparel-uk"))
        .executeAction(new ProductSearchHybrisUserAction("shirts"))
        .executeAction(new AddtoCartFromProductSearchPageHybrisUserAction(3))
        .executeAction(new NavigateToCartFromMiniCartLinkPageHybrisUserAction("/cart"))
        .executeAction(new ClickCheckoutButtonOnCartPageHybrisUserAction())
        .executeAction(new AddGuestDetailsForCheckoutLoginUserAction(shippingAddress.getEmail()))
        .executeAction(new AddGuestShippingAddressForCheckoutUserAction(shippingAddress))
        .executeAction(new AddGuestShippingMethodCheckoutUserAction("standard-gross"))
        .executeAction(new AddGuestPaymentDetailsForCheckoutUserAction(payment))
        .executeAction(new AddGuestReviewOrderForCheckoutUserAction());
        Thread.sleep(10000);
    }
}
