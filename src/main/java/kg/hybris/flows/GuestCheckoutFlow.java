package kg.hybris.flows;

import kg.hybris.actions.*;
import kg.hybris.dto.Address;
import kg.hybris.dto.Payment;
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
        Address shippingAddress=new Address("GB","9323 town court south","","2052120608","kilo"+System.currentTimeMillis()+"@mailinator.com","NJ","Lawrence township","08648","mr","kaushik","ganguly");
        Payment payment=new Payment("001","test user","4111111111111111","4","2020","123");
        hybrisBrowser.getBrowser().manage().window().maximize();
        hybrisBrowser.executeAction(new HybrisStorefrontNavigationAction(hybrisBrowser,"yacceleratorstorefront/?site=apparel-uk"));
        Thread.sleep(2000);
        hybrisBrowser.executeAction(new ProductSearchHybrisUserAction(hybrisBrowser,"shirts"));
        Thread.sleep(2000);
        hybrisBrowser.executeAction(new AddtoCartFromProductSearchPageHybrisUserAction(hybrisBrowser,3));
        hybrisBrowser.executeAction(new NavigateToCartFromMiniCartLinkPageHybrisUserAction(hybrisBrowser,"yacceleratorstorefront/cart"));
        Thread.sleep(2000);
        hybrisBrowser.executeAction(new ClickCheckoutButtonOnCartPageHybrisUserAction(hybrisBrowser));
        Thread.sleep(2000);
        hybrisBrowser.executeAction(new AddGuestDetailsForCheckoutLoginUserAction(hybrisBrowser,shippingAddress.getEmail()));
        Thread.sleep(2000);
        hybrisBrowser.executeAction(new AddGuestShippingAddressForCheckoutUserAction(hybrisBrowser,shippingAddress));
        Thread.sleep(2000);
        hybrisBrowser.executeAction(new AddGuestShippingMethodCheckoutUserAction(hybrisBrowser,"standard-gross"));
        Thread.sleep(2000);
        hybrisBrowser.executeAction(new AddGuestPaymentDetailsForCheckoutUserAction(hybrisBrowser,payment));
        Thread.sleep(2000);
        hybrisBrowser.executeAction(new AddGuestReviewOrderForCheckoutUserAction(hybrisBrowser));
        Thread.sleep(10000);
    }
}
