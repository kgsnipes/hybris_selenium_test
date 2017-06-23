package kg.hybris.flows;

import kg.hybris.dto.HybrisFlowResult;
import kg.hybris.setup.HybrisBrowser;

/**
 * Created by kaushik on 6/22/2017.
 */
public interface HybrisFlow {

    public String getName();
    public void performFlow(HybrisBrowser browser)throws Exception;
    public void preFlowActivities();
    public void postFlowActivities();
    public void flowFailureActivites(Exception ex);
    public void createHybrisFlowResult(String flowName);
    public HybrisFlowResult getFlowResult();

}
