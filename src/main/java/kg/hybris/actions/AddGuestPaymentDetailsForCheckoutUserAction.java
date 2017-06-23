package kg.hybris.actions;

import kg.hybris.dto.Payment;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by kaushik on 6/18/2017.
 */

public class AddGuestPaymentDetailsForCheckoutUserAction extends AbstractHybrisUserAction implements HybrisUserAction {


    private Payment payment;

    public AddGuestPaymentDetailsForCheckoutUserAction(Payment payment) {
        this.payment = payment;

    }


    public void perform()throws Exception
    {

        WebDriver browser= getHybrisBrowser().getBrowser();

        WebElement guestCheckoutPaymentForm=browser.findElement(By.id("silentOrderPostForm"));
        if(guestCheckoutPaymentForm!=null)
        {
            new Select(browser.findElement(By.name("card_cardType"))).selectByValue(this.payment.getCardType());
            browser.findElement(By.name("card_nameOnCard")) .sendKeys(this.payment.getName());
            browser.findElement(By.name("card_accountNumber")) .sendKeys(this.payment.getCardNumber());
            new Select(browser.findElement(By.name("card_expirationMonth"))).selectByValue(this.payment.getMonth());
            new Select(browser.findElement(By.name("card_expirationYear"))).selectByValue(this.payment.getYear());
            browser.findElement(By.name("card_cvNumber")) .sendKeys(this.payment.getCvv());
            browser.findElement(By.className("submit_silentOrderPostForm")).click();
            getHybrisBrowser().getScreenshotReportingService().saveScreenshot(getHybrisBrowser(),this);
            WebDriverWait waitForOrderReviewPage = new WebDriverWait(browser, 20000);
            waitForOrderReviewPage.until(ExpectedConditions.visibilityOfElementLocated(By.id("placeOrderForm1")));

        }

    }

    public String getName() {
        return this.getClass().getSimpleName();
    }
}
