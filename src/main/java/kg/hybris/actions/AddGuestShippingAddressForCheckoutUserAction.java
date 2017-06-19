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
public class AddGuestShippingAddressForCheckoutUserAction extends AbstractHybrisUserAction implements HybrisUserAction {


    private Address address;

    public AddGuestShippingAddressForCheckoutUserAction(HybrisBrowser browser, Address address) {
        this.address = address;
        this.setHybrisBrowser(browser);
    }



    @Override
    public void perform()throws Exception
    {
        super.perform();
        WebDriver browser= getHybrisBrowser().getBrowser();
        WebElement guestCheckoutShippingAddressFormDiv=browser.findElement(By.className("checkout-shipping"));
        //WebElement guestCheckoutShippingAddressForm=browser.findElement(By.name("addressForm"));
        if(guestCheckoutShippingAddressFormDiv!=null)
        {
            new Select(browser.findElement(By.name("countryIso"))).selectByValue(this.address.getCountry());
            WebDriverWait wait = new WebDriverWait(browser, 5000);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("titleCode")));
            new Select(browser.findElement(By.name("titleCode"))).selectByValue(this.address.getTitle());
            browser.findElement(By.name("firstName")) .sendKeys(this.address.getFirstName());
            browser.findElement(By.name("lastName")) .sendKeys(this.address.getLastName());
            browser.findElement(By.name("line1")) .sendKeys(this.address.getLine1());
            browser.findElement(By.name("line2")) .sendKeys(this.address.getLine2());
            browser.findElement(By.name("townCity")) .sendKeys(this.address.getCity());
            browser.findElement(By.name("postcode")) .sendKeys(this.address.getZip());
            browser.findElement(By.name("phone")) .sendKeys(this.address.getPhone());
            browser.findElement(By.id("addressSubmit")).click();
            //guestCheckoutShippingAddressForm.submit();
            WebDriverWait waitForShippingMethod = new WebDriverWait(browser, 5000);
            waitForShippingMethod.until(ExpectedConditions.visibilityOfElementLocated(By.id("selectDeliveryMethodForm")));

        }

    }

    public String getName() {
        return this.getClass().getSimpleName();
    }
}
