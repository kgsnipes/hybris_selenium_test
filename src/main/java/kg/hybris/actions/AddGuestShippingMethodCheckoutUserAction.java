package kg.hybris.actions;

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

    public AddGuestShippingMethodCheckoutUserAction(String shippingMethod) {
        this.shippingMethod = shippingMethod;

    }



    public void perform()throws Exception
    {

        WebDriver browser= getHybrisBrowser().getBrowser();

        WebElement guestCheckoutShippingMethodForm=browser.findElement(By.id("selectDeliveryMethodForm"));
        if(guestCheckoutShippingMethodForm!=null)
        {
            new Select(browser.findElement(By.name("delivery_method"))).selectByValue(this.shippingMethod);

            browser.findElement(By.id("deliveryMethodSubmit")).click();
            getHybrisBrowser().getScreenshotReportingService().saveScreenshot(getHybrisBrowser(),this);
            WebDriverWait waitForShippingMethod = new WebDriverWait(browser, 5000);
            waitForShippingMethod.until(ExpectedConditions.visibilityOfElementLocated(By.id("silentOrderPostForm")));

        }

    }

    public String getName() {
        return this.getClass().getSimpleName();
    }
}
