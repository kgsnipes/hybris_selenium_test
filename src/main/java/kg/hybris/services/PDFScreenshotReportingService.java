package kg.hybris.services;

import kg.hybris.setup.DefaultHybrisBrowser;
import kg.hybris.utils.HybrisTestingUtils;
import org.apache.log4j.Logger;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by kaushik on 6/23/2017.
 */
public class PDFScreenshotReportingService implements ReportingService {

    private static final Logger LOG = Logger.getLogger(PDFScreenshotReportingService.class);

    public void saveScreenshotsForPDF(File[] screenshots, String path, String flowId)throws Exception
    {
        //Creating PDF document object
        PDDocument document =null;
        try
        {
            document=new PDDocument();
            for(File f:screenshots)
            {
                Dimension d= HybrisTestingUtils.getImageDimension(f.getAbsolutePath());
                PDPage page = new PDPage(new PDRectangle((float)d.getWidth(),(float)d.getHeight()));
                document.addPage(page);
                LOG.info(f.getAbsolutePath());
                PDImageXObject pdImage = PDImageXObject.createFromFile(f.getAbsolutePath(), document);
                //PDPageContentStream contentStream = new PDPageContentStream(document, page);
                PDPageContentStream contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true, true);
                contentStream.drawImage(pdImage,0,0);
                contentStream.close();

            }
            document.save(path+"\\"+"ScreenshotConsolidated_"+flowId+".pdf");

        }
        finally {
            if(document!=null)
            {
                //Closing the document
                document.close();
            }
        }




    }
}
