package kg.hybris.dto;

import java.util.Date;
import java.util.List;

/**
 * Created by kaushik on 6/22/2017.
 */
public class HybrisFlowResult {

    private String flowName;
    private Date flowStartTime;
    private Date flowEndTime;
    private FlowStatus status;
    private List<FlowActionResult> actionResultList;
    private Exception error;
    private String errorMessage;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Exception getError() {
        return error;
    }

    public void setError(Exception error) {
        this.error = error;
    }

    public String getFlowName() {
        return flowName;
    }

    public void setFlowName(String flowName) {
        this.flowName = flowName;
    }

    public Date getFlowStartTime() {
        return flowStartTime;
    }

    public void setFlowStartTime(Date flowStartTime) {
        this.flowStartTime = flowStartTime;
    }

    public Date getFlowEndTime() {
        return flowEndTime;
    }

    public void setFlowEndTime(Date flowEndTime) {
        this.flowEndTime = flowEndTime;
    }

    public FlowStatus getStatus() {
        return status;
    }

    public void setStatus(FlowStatus status) {
        this.status = status;
    }

    public List<FlowActionResult> getActionResultList() {
        return actionResultList;
    }

    public void setActionResultList(List<FlowActionResult> actionResultList) {
        this.actionResultList = actionResultList;
    }
}
