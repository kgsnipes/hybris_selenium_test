package kg.hybris.actions;

import kg.hybris.dto.Payment;
import kg.hybris.setup.HybrisBrowser;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by kaushik on 6/18/2017.
 */
public class AddGuestReviewOrderForCheckoutUserAction extends AbstractHybrisUserAction implements HybrisUserAction {


    private Payment payment;

    public AddGuestReviewOrderForCheckoutUserAction(HybrisBrowser browser, Payment payment) {
        this.payment = payment;
        this.setHybrisBrowser(browser);
    }



    @Override
    public void perform()throws Exception
    {
        super.perform();
        WebDriver browser= getHybrisBrowser().getBrowser();

        WebElement guestCheckoutOrderReview=browser.findElement(By.className("checkout-review"));
        if(guestCheckoutOrderReview!=null)
        {
            WebElement termsCheckBox=browser.findElement(By.id("Terms1"));
            if (termsCheckBox!=null)
            {

                JavascriptExecutor js = (JavascriptExecutor)browser;
                js.executeScript("$(document).find('#Terms1').attr('checked',true);");

                browser.findElement(By.id("placeOrder")).click();

                WebDriverWait waitForShippingMethod = new WebDriverWait(browser, 10000);
                waitForShippingMethod.until(ExpectedConditions.visibilityOfElementLocated(By.className("checkout-success__body")));
            }



        }

    }

    public String getName() {
        return this.getClass().getSimpleName();
    }
}
