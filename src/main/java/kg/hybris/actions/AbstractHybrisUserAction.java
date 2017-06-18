package kg.hybris.actions;

import kg.hybris.setup.HybrisBrowser;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by kaushik on 6/18/2017.
 */

public abstract class AbstractHybrisUserAction implements HybrisUserAction {


    private HybrisBrowser hybrisBrowser;

    public HybrisBrowser getHybrisBrowser() {
        return hybrisBrowser;
    }

    public void setHybrisBrowser(HybrisBrowser hybrisBrowser) {
        this.hybrisBrowser = hybrisBrowser;
    }

    public void perform() throws Exception {
        System.out.println("Performing "+getName()+" .");
    }

    public String getName() {
        return this.getClass().getSimpleName();
    }
}
