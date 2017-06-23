package kg.hybris.flows;

import kg.hybris.dto.*;
import kg.hybris.services.PDFScreenshotReportingService;
import kg.hybris.setup.HybrisBrowser;
import kg.hybris.utils.HybrisTestingUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by kaushik on 6/22/2017.
 */
public abstract class AbstractHybrisFlow implements HybrisFlow {

    private static final Logger LOG = Logger.getLogger(AbstractHybrisFlow.class);
    @Autowired
    @Qualifier("sampleShippingAddress")
    protected Address shippingAddress;

    @Autowired
    @Qualifier("samplePaymentInfo")
    protected Payment payment;

    @Value("${hybristesting.report.folder}")
    private String reportingFolder;

    @Autowired
    PDFScreenshotReportingService pDFScreenshotReportingService;


    private String name;
    private String id;

    private HybrisFlowResult flowResult;


    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public String getReportingFolder() {
        return reportingFolder;
    }

    public void setReportingFolder(String reportingFolder) {
        this.reportingFolder = reportingFolder;
    }

    public void setFlowResult(HybrisFlowResult flowResult) {
        this.flowResult = flowResult;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void preFlowActivities()  {
        if(StringUtils.isEmpty(getName()))
        {
            setName(this.getClass().getSimpleName());
            Date now=new Date();

            setId(this.getClass().getSimpleName()+"-"+ HybrisTestingUtils.getFormattedDateForFileName(now));
        }
        getFlowResult().setStatus(FlowStatus.RUNNING);
        flowResult.setFlowStartTime(new Date());
        flowResult.setFlowId(getId());

    }

    public void postFlowActivities()  {
        this.flowResult.setFlowEndTime(new Date());
        getFlowResult().setStatus(FlowStatus.COMPLETED);
        this.flowResult.setFlowEndTime(new Date());
        consolidateScreenshotsToPDF();

    }

    public void flowFailureActivites(Exception ex) {
        getFlowResult().setStatus(FlowStatus.FAILED);
        this.flowResult.setFlowEndTime(new Date());
        getFlowResult().setError(ex);
        getFlowResult().setErrorMessage(ex.getMessage());
        consolidateScreenshotsToPDF();
    }

    public void createHybrisFlowResult(String flowName)
    {
       this.flowResult=new HybrisFlowResult();
       // flowResult.setFlowName(flowName);
        flowResult.setActionResultList(new ArrayList<FlowActionResult>());
        flowResult.setStatus(FlowStatus.STARTED);

    }

    public HybrisFlowResult getFlowResult(){
        return this.flowResult;
    }

    public void performFlow(HybrisBrowser browser)throws Exception{
        browser.setFlow(this);
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private void consolidateScreenshotsToPDF()
    {
        try
        {
            pDFScreenshotReportingService.saveScreenshotsForPDF(HybrisTestingUtils.getFilesSortedByModifiedDate(new File(this.getReportingFolder()+"\\"+this.getId()+"\\"+"screenshots")),this.getReportingFolder()+"\\"+this.getId()+"\\"+"screenshots",this.getId() );
        }
        catch (Exception ex)
        {
            LOG.error("Encountered Exception",ex);
        }
        finally {

        }
    }
}
