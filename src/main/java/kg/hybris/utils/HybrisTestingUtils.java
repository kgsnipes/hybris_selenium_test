package kg.hybris.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;

/**
 * Created by kaushik on 6/23/2017.
 */
public class HybrisTestingUtils {

    public static String getFormattedDateForFileName(Date now)
    {
        return new SimpleDateFormat("yy_MM_dd_HH_mm_ss").format(now);
    }

    public static File[] getFilesSortedByModifiedDate(File directory)
    {
        File[] files = directory.listFiles();
          Arrays.sort(files, new Comparator<File>() {
            public int compare(File f1, File f2) {

                if(f1.lastModified()== f2.lastModified())
                    return 0;
                else if(f1.lastModified()< f2.lastModified())
                    return -1;
                else
                    return 1;
            }
        });
          return files;
    }

    public static Dimension getImageDimension(String filePath)throws Exception
    {
        BufferedImage bimg = ImageIO.read(new File(filePath));
        int width          = bimg.getWidth();
        int height         = bimg.getHeight();
        return new Dimension(width,height);
    }


}
