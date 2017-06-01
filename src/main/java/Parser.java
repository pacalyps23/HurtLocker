import org.apache.commons.io.IOUtils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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

    public int countMilk()
    {
        List<Food> milkList = extractAll();
        long numberOfMilk = milkList
                .stream()
                .filter(p -> p.getName().matches("[M+]\\w{3}") && !p.getPrice().equals("error"))
                .count();
        return (int) numberOfMilk;
    }

    public int countMilkPriceOne()
    {
        List<Food> milkList = extractAll();
        long numberOfMilk = milkList
                .stream()
                .filter(p -> p.getName().matches("[M+]\\w{3}") && p.getPrice().equals("3.23"))
                .count();
        return (int) numberOfMilk;
    }

    public int countMilkPriceTwo()
    {
        List<Food> milkList = extractAll();
        long numberOfMilk = milkList
                .stream()
                .filter(p -> p.getName().matches("[Mm+]\\w{3}") && p.getPrice().equals("1.23"))
                .count();
        return (int) numberOfMilk;
    }

    public int countBreadAndPrice()
    {
        List<Food> breadList = extractAll();
        long numberOfBread = breadList
                .stream()
                .filter(p -> p.getName().matches("[Bb]\\w{4}") && p.getPrice().equals("1.23"))
                .count();
        return (int) numberOfBread;
    }

    public int countCookiesAndPrice()
    {
        List<Food> breadList = extractAll();
        long numberOfBread = breadList
                .stream()
                .filter(p -> p.getName().matches("[Cc+]\\w{6}") && p.getPrice().equals("2.25"))
                .count();
        return (int) numberOfBread;
    }

    public int countApples()
    {
        List<Food> breadList = extractAll();
        long numberOfBread = breadList
                .stream()
                .filter(p -> p.getName().matches("[Aa+]\\w{5}"))
                .count();
        return (int) numberOfBread;
    }

    public int countApplesAndPriceOne()
    {
        List<Food> breadList = extractAll();
        long numberOfBread = breadList
                .stream()
                .filter(p -> p.getName().matches("[Aa+]\\w{5}") && p.getPrice().equals("0.25"))
                .count();
        return (int) numberOfBread;
    }

    public int countApplesAndPriceTwo()
    {
        List<Food> breadList = extractAll();
        long numberOfBread = breadList
                .stream()
                .filter(p -> p.getName().matches("[Aa+]\\w{5}") && p.getPrice().equals("0.23"))
                .count();
        return (int) numberOfBread;
    }

    public int countErrors()
    {
        List<Food> breadList = extractAll();
        long numberOfBread = breadList
                .stream()
                .filter(p -> p.getName().equals("error") || p.getPrice().equals("error"))
                .count();
        return (int) numberOfBread;
    }

    private static String border = "============= \t \t =============";
    private static String border2 = "-------------\t\t -------------";
    public String printListMilk()
    {
        String output = String.format("name:    Milk \t\t seen: %d times\n", countMilk());
        output += border + "\n";
        output += String.format("Price: \t 3.23\t\t seen: %d times\n", countMilkPriceOne());
        output += border2 + "\n";
        output += String.format("Price: \t 1.23\t\t seen: %d times\n", countMilkPriceTwo());

        return output;
    }

    public String printListBread()
    {
        String output = String.format("name:   Bread \t\t seen: %d times\n", countBreadAndPrice());
        output += border + "\n";
        output += String.format("Price: \t 1.23\t\t seen: %d times\n", countBreadAndPrice());
        output += border2 + "\n";

        return output;
    }

    public String printListCookies()
    {
        String output = String.format("name: Cookies \t\t seen: %d times\n", countCookiesAndPrice());
        output += border + "\n";
        output += String.format("Price: \t 2.25\t\t seen: %d times\n", countBreadAndPrice());
        output += border2 + "\n";

        return output;
    }

    public String printListApples()
    {
        String output = String.format("name:  Apples \t\t seen: %d times\n", countApples());
        output += border + "\n";
        output += String.format("Price: \t 0.25\t\t seen: %d times\n", countApplesAndPriceOne());
        output += border2 + "\n";
        output += String.format("Price: \t 0.23\t\t seen: %d times\n", countApplesAndPriceTwo());

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

}
