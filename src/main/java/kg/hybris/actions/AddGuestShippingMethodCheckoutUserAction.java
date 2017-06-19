package kg.hybris.actions;

import kg.hybris.dto.Address;
import kg.hybris.setup.HybrisBrowser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by kaushik on 6/18/2017.
 */
public class AddGuestShippingMethodCheckoutUserAction extends AbstractHybrisUserAction implements HybrisUserAction {


    private String shippingMethod;

    public AddGuestShippingMethodCheckoutUserAction(HybrisBrowser browser, String shippingMethod) {
        this.shippingMethod = shippingMethod;
        this.setHybrisBrowser(browser);
    }



    @Override
    public void perform()throws Exception
    {
        super.perform();
        WebDriver browser= getHybrisBrowser().getBrowser();

        WebElement guestCheckoutShippingMethodForm=browser.findElement(By.id("selectDeliveryMethodForm"));
        if(guestCheckoutShippingMethodForm!=null)
        {
            new Select(browser.findElement(By.name("delivery_method"))).selectByValue(this.shippingMethod);

            browser.findElement(By.id("deliveryMethodSubmit")).click();

            WebDriverWait waitForShippingMethod = new WebDriverWait(browser, 5000);
            waitForShippingMethod.until(ExpectedConditions.visibilityOfElementLocated(By.id("silentOrderPostForm")));

        }

    }

    public String getName() {
        return this.getClass().getSimpleName();
    }
}
