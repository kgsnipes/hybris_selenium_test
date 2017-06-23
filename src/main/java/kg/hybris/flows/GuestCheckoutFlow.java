package kg.hybris.flows;

import kg.hybris.actions.*;
import kg.hybris.dto.Address;
import kg.hybris.dto.Payment;
import kg.hybris.setup.HybrisBrowser;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * Created by kaushik on 6/18/2017.
 */
public class GuestCheckoutFlow extends  AbstractHybrisFlow implements  HybrisFlow{

    private static final Logger LOG = Logger.getLogger(GuestCheckoutFlow.class);


    public void performFlow(HybrisBrowser hybrisBrowser)throws Exception
    {
        try
        {
            super.performFlow(hybrisBrowser);
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
        catch (Exception ex)
        {

        }
        finally {

        }

    }


}
