package kg.hybris.actions;

import kg.hybris.dto.Payment;
import kg.hybris.setup.HybrisBrowser;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by kaushik on 6/18/2017.
 */
public class AddGuestReviewOrderForCheckoutUserAction extends AbstractHybrisUserAction implements HybrisUserAction {




    public AddGuestReviewOrderForCheckoutUserAction() {

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
            System.out.println(termsCheckBox.getText());
            System.out.println(termsCheckBox.getAttribute("innerHTML"));
            if (termsCheckBox!=null && !termsCheckBox.isSelected())
            {
                termsCheckBox.click();
                //JavascriptExecutor js = (JavascriptExecutor)browser;
              //  js.executeScript("$(document).find('#Terms1').attr('checked',true);");
                //termsCheckBox.sendKeys(Keys.SPACE);
                browser.findElement(By.id("placeOrder")).click();

                WebDriverWait waitForOrderConfirmationPage = new WebDriverWait(browser, 10000);
                waitForOrderConfirmationPage.until(ExpectedConditions.visibilityOfElementLocated(By.className("checkout-success__body")));
            }



        }

    }

    public String getName() {
        return this.getClass().getSimpleName();
    }
}
