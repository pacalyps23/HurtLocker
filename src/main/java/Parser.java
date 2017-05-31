import org.apache.commons.io.IOUtils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by luisgarcia on 5/31/17.
 */
public class Parser
{
    private String [] makeFoodList;
    private String [] getCategory;

    public String readRawDataToString(String file) throws Exception
    {
        ClassLoader classLoader = getClass().getClassLoader();
        String result = IOUtils.toString(classLoader.getResourceAsStream(file));
        return result;
    }



    public String[] getListFromFile(String file)
    {
        String delims = "[##]+";

        String rawDataString = null;
        try
        {
            rawDataString = readRawDataToString(file);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        makeFoodList = rawDataString.split(delims);

        return makeFoodList;
    }

    public String [] makeCategory()
    {
        for(String item : makeFoodList)
        {

        }
    }

}
