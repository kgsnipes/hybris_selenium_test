package kg.hybris.services;

import kg.hybris.actions.HybrisUserAction;
import kg.hybris.flows.HybrisFlow;
import kg.hybris.setup.HybrisBrowser;
import kg.hybris.utils.HybrisTestingConstants;
import kg.hybris.utils.HybrisTestingUtils;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.util.Date;

/**
 * Created by kaushik on 6/23/2017.
 */
public class ScreenshotReportingService implements ReportingService {

    public void saveScreenshot(HybrisBrowser browser, HybrisUserAction action)throws Exception
    {
        createFlowReportFolderIfNotExists(browser.getFlow(),browser.getFlow().getReportingFolder());
        String base64_image = ((TakesScreenshot)browser.getBrowser()).getScreenshotAs(OutputType.BASE64);
        byte[] bytes = Base64.decodeBase64( base64_image );
        FileUtils.writeByteArrayToFile( new File(browser.getFlow().getReportingFolder()+browser.getFlow().getId()+"\\"+"screenshots"+"\\"+action.getName()+"_"+ HybrisTestingUtils.getFormattedDateForFileName(new Date())+"."+ HybrisTestingConstants.IMAGE_FORMAT), bytes );

    }

    public void createFlowReportFolderIfNotExists(HybrisFlow flow, String reportingFolder)throws Exception
    {
        if(new File(reportingFolder).exists() )
        {
            if(!new File(reportingFolder+"\\"+flow.getId()).exists())
            {
                FileUtils.forceMkdir(new File(reportingFolder+"\\"+flow.getId()));
            }
            if(!new File(reportingFolder+"\\"+flow.getId()+"\\"+"screenshots").exists())
            {
                FileUtils.forceMkdir(new File(reportingFolder+"\\"+flow.getId()+"\\"+"screenshots"));
            }
        }
    }
}
