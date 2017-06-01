import org.apache.commons.io.IOUtils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by luisgarcia on 5/31/17.
 */
public class Parser
{
    private String [] makeFoodList;

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

    public String[] extractItem(String cat)
    {
        String [] cat1;
        String [] cat2;
        String [] cat3;
        String [] cat4;

        String namePattern = "([Nn][aA][Mm][Ee][:*;*@*\\.*\\^*\\s])";
        String namePattern2 = "([Pp][Rr][Ii][Cc][Ee][:*;*@*\\.*\\^*\\s])";
        String namePattern3 = "([Tt][Yy][Pp][Ee][:*;*@*\\.*\\^*\\s])";
        String namePattern4 = "\\w{10}[:*;*@*\\.*\\^*\\s]";

        cat1 = cat.split(namePattern);
        String val = cat1[1];//Milk;price:3.23;type:Food;expiration:1/25/2016

        cat2 = val.split(namePattern2);
        String val2 = cat2[1];//3.23;type:Food;expiration:1/25/2016

        cat3 = val2.split(namePattern3);
        String val3 = cat3[1];//Food;expiration:1/25/2016

        cat4 = val3.split(namePattern4);
        String [] category = {cleanItem(cat2[0]), cleanItem(cat3[0]), cleanItem(cat4[0]), cat4[1]};

        return category;
    }

    public String cleanItem(String input)
    {
        if(input.equals(";"))
        {
            return "error";
        }
        String pattern = "[:*;*@**\\^*\\s]";
        String [] clean = input.split(pattern);
        return  clean[0];
    }


    public ArrayList<Food> extractAll()
    {
        ArrayList<Food> foodArray = new ArrayList();
        String [] oneItem;
        for(int i = 0; i < makeFoodList.length; i++)
        {
            oneItem = extractItem(makeFoodList[i]);
            foodArray.add(new Food(oneItem[0], oneItem[1], oneItem[2], oneItem[3]));

        }
        return foodArray;
    }



}
