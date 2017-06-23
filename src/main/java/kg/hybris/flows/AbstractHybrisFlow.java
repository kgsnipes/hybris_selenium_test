package kg.hybris.flows;

import kg.hybris.dto.FlowActionResult;
import kg.hybris.dto.FlowStatus;
import kg.hybris.dto.HybrisFlowResult;
import kg.hybris.setup.HybrisBrowser;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by kaushik on 6/22/2017.
 */
public abstract class AbstractHybrisFlow implements HybrisFlow {

    private String name;

    private HybrisFlowResult flowResult;

    public String getName() {
        if (StringUtils.isEmpty(this.name)){
        this.name=this.getClass().getSimpleName();
        }
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void preFlowActivities()  {
        if(StringUtils.isEmpty(getName()))
        {
            setName(this.getClass().getSimpleName()+"_"+System.currentTimeMillis());
        }
        getFlowResult().setStatus(FlowStatus.RUNNING);
        flowResult.setFlowStartTime(new Date());
    }

    public void postFlowActivities()  {
        this.flowResult.setFlowEndTime(new Date());
        getFlowResult().setStatus(FlowStatus.COMPLETED);
        this.flowResult.setFlowEndTime(new Date());
    }

    public void flowFailureActivites(Exception ex) {
        getFlowResult().setStatus(FlowStatus.FAILED);
        this.flowResult.setFlowEndTime(new Date());
        getFlowResult().setError(ex);
        getFlowResult().setErrorMessage(ex.getMessage());
    }

    public void createHybrisFlowResult(String flowName)
    {
       this.flowResult=new HybrisFlowResult();
        flowResult.setFlowName(flowName);
        flowResult.setActionResultList(new ArrayList<FlowActionResult>());
        flowResult.setStatus(FlowStatus.STARTED);

    }

    public HybrisFlowResult getFlowResult(){
        return this.flowResult;
    }

    public void performFlow(HybrisBrowser browser)throws Exception{
        browser.setFlow(this);
    }


}
