package kg.hybris.actions;

import kg.hybris.setup.HybrisBrowser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by kaushik on 6/18/2017.
 */
public class AddGuestDetailsForCheckoutLoginUserAction extends AbstractHybrisUserAction implements HybrisUserAction {


    private String emailAddress;

    public AddGuestDetailsForCheckoutLoginUserAction( String emailAddress) {
        this.emailAddress = emailAddress;
    }



    @Override
    public void perform()throws Exception
    {
        super.perform();
        WebDriver browser= getHybrisBrowser().getBrowser();
        WebElement guestCheckoutLoginForm=browser.findElement(By.id("guestForm"));
        if(guestCheckoutLoginForm!=null)
        {
            browser.findElement(By.className("guestEmail")) .sendKeys(this.emailAddress);
            browser.findElement(By.className("confirmGuestEmail")) .sendKeys(this.emailAddress);
            guestCheckoutLoginForm.submit();
            WebDriverWait wait = new WebDriverWait(browser, 5000);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("checkout-shipping")));

        }

    }

    public String getName() {
        return this.getClass().getSimpleName();
    }

}
