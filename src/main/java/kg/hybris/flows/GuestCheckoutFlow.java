package kg.hybris.flows;

import kg.hybris.actions.*;
import kg.hybris.dto.Address;
import kg.hybris.dto.FlowStatus;
import kg.hybris.dto.Payment;
import kg.hybris.setup.HybrisBrowser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by kaushik on 6/18/2017.
 */
public class GuestCheckoutFlow extends  AbstractHybrisFlow implements  HybrisFlow{

    @Autowired
    @Qualifier("sampleShippingAddress")
    Address shippingAddress;

    @Autowired
    @Qualifier("samplePaymentInfo")
    Payment payment;

    public void performFlow(HybrisBrowser hybrisBrowser)throws Exception
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
