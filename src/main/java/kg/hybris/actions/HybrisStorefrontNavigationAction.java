package kg.hybris.actions;


/**
 * Created by kaushik on 6/18/2017.
 */

public class HybrisStorefrontNavigationAction extends AbstractHybrisUserAction implements  HybrisUserAction{

    private String URL;

    public HybrisStorefrontNavigationAction(String URL) {
        this.URL = URL;

    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }


    public void perform()throws Exception
    {

        getHybrisBrowser().getBrowser().get(getHybrisBrowser().getHOST()+getHybrisBrowser().getContextPath()+getURL());
    }
    public String getName() {
        return this.getClass().getSimpleName();
    }
}
