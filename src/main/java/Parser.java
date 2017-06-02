import org.apache.commons.io.IOUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

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

        String rawDataString = "";
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

    public int countItem(String itemName)
    {
        List<Food> itemList = extractAll();
        long numberOfItem = itemList
                .stream()
                .filter(p -> p.getName().equalsIgnoreCase(itemName))
                .filter(p -> !p.getPrice().equals("error"))
                .count();
        return (int) numberOfItem;
    }


    public int countItem(String itemName, String price)
    {
        List<Food> itemList = extractAll();
        long numberOfItem = itemList
                .stream()
                .filter(p -> p.getName().equalsIgnoreCase(itemName))
                .filter(p -> p.getPrice().equals(price))
                .count();
        return (int) numberOfItem;
    }

    public int countCookies(String itemName)
    {
        List<Food> itemList = extractAll();
        long numberOfItem = itemList
                .stream()
                .filter(p -> p.getName().matches(regexCookie(itemName)))
                .count();
        return (int) numberOfItem;
    }

    public int countErrors()
    {
        List<Food> itemList = extractAll();
        long numberOfItem = itemList
                .stream()
                .filter(p -> p.getName().equalsIgnoreCase("error")
                        || p.getPrice().equalsIgnoreCase("error"))
                .count();
        return (int) numberOfItem;
    }

    private String regexCookie(String itemName)
    {
        return "[Cc]\\w{6}";
    }


    private static String border = "============= \t \t =============";
    private static String border2 = "-------------\t\t -------------";
    public String printListMilk()
    {
        String output = String.format("name:    Milk \t\t seen: %d times\n", countItem("milk"));
        output += border + "\n";
        output += String.format("Price: \t 3.23\t\t seen: %d times\n", countItem("milk", "3.23"));
        output += border2 + "\n";
        output += String.format("Price: \t 1.23\t\t seen: %d times\n", countItem("milk", "1.23"));

        return output;
    }

    public String printListBread()
    {
        String output = String.format("name:   Bread \t\t seen: %d times\n", countItem("bread"));
        output += border + "\n";
        output += String.format("Price: \t 1.23\t\t seen: %d times\n", countItem("bread", "1.23"));
        output += border2 + "\n";

        return output;
    }

    public String printListCookies()
    {
        String output = String.format("name: Cookies \t\t seen: %d times\n", countCookies("cookies"));
        output += border + "\n";
        output += String.format("Price: \t 2.25\t\t seen: %d times\n", countCookies("cookies"));
        output += border2 + "\n";

        return output;
    }

    public String printListApples()
    {
        String output = String.format("name:  Apples \t\t seen: %d times\n", countItem("apples"));
        output += border + "\n";
        output += String.format("Price: \t 0.25\t\t seen: %d times\n", countItem("apples", "0.25"));
        output += border2 + "\n";
        output += String.format("Price: \t 0.23\t\t seen: %d times\n", countItem("apples", "0.23"));

        return output;
    }

    public String printErrors()
    {
        String output = String.format("Errors         \t \t seen: %d times", countErrors());
        return output;
    }

    public String printReceipt()
    {
        return printListMilk() + printListBread() +
        printListCookies() + printListApples() + printErrors();
    }

    public void printToFile(String file)
    {
        PrintWriter out = null;
        try
        {
            out = new PrintWriter(new FileWriter(file));
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        out.println(printReceipt());
        out.close();
    }

}
