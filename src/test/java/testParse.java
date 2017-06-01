import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;

import java.util.ArrayList;

/**
 * Created by luisgarcia on 5/31/17.
 */
public class testParse
{
   Parser parse;
   String[] myList;
   String [] myCategory;

    @Before
    public void initial()
    {
        parse = new Parser();
        myList = parse.getListFromFile("RawData.txt");
        myCategory = parse.extractItem(myList[0]);
    }


    @Test
    public void parseTestObjects()
    {
        //given
        int expected = 28;

        //when
        int actual = myList.length;

        //then
        Assert.assertEquals(expected, actual);

    }

    @Test
    public void parseFistItemOnList()
    {
        //given
        String expected = "naMe:Milk;price:3.23;type:Food;expiration:1/25/2016";

        //when
        String actual = myList[0];

        //then
        Assert.assertEquals(expected, actual);

    }

    @Test
    public void categoryFistTryTest()
    {
        //given
        int expected = 4;

        //when
        int actual = myCategory.length;

        //then
        Assert.assertEquals(expected, actual);

    }

    @Test
    public void extractAllTest()
    {
        //given
        ArrayList paco = parse.extractAll();
        int expected = 28;

        //when
        int actual = paco.size();

        //then
        Assert.assertEquals(expected, actual);

    }

    @Test
    public void extractAllRetrieveItemTest()
    {
        //given
        ArrayList paco = parse.extractAll();
        String expected = "Milk 3.23 Food 1/25/2016";

        //when
        String actual = paco.get(0).toString();

        //then
        Assert.assertEquals(expected, actual);

    }

    @Test
    public void cleanTest()
    {
        //given
        String expected = "milk";

        //when
        String  actual = parse.cleanItem("milk;");;

        //then
        Assert.assertEquals(expected, actual);

    }

    @Test
    public void countMilkTest()
    {
        //given
        int expected = 6;

        //when
        int  actual = parse.countMilk();

        //then
        Assert.assertEquals(expected, actual);

    }

    @Test
    public void countMilkPriceOneTest()
    {
        //given
        int expected = 5;

        //when
        int  actual = parse.countMilkPriceOne();

        //then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void countMilkPriceTwoTest()
    {
        //given
        int expected = 1;

        //when
        int  actual = parse.countMilkPriceTwo();

        //then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void countBreadAndPriceTest()
    {
        //given
        int expected = 6;

        //when
        int  actual = parse.countBreadAndPrice();

        //then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void countCookiesAndPriceTest()
    {
        //given
        int expected = 8;

        //when
        int  actual = parse.countCookiesAndPrice();

        //then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void countApplesTest()
    {
        //given
        int expected = 8;

        //when
        int  actual = parse.countApples();

        //then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void countApplesAndPriceOneTest()
    {
        //given
        int expected = 2;

        //when
        int  actual = parse.countApplesAndPriceOne();

        //then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void countApplesAndPriceTwoTest()
    {
        //given
        int expected = 2;

        //when
        int  actual = parse.countApplesAndPriceTwo();

        //then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void countErrorsTest()
    {
        //given
        int expected = 4;

        //when
        int  actual = parse.countErrors();

        //then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void printMilkTest()
    {
        //given
        String expected = "name:    Milk \t\t seen: 6 times\n" +
                "============= \t \t =============\n" +
                "Price: \t 3.23\t\t seen: 5 times\n" +
                "-------------\t\t -------------\n" +
                "Price: \t 1.23\t\t seen: 1 times\n";

        //when
        String actual =parse.printListMilk();

        //then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void printReceiptTest()
    {
        //given
        String expected = "name:    Milk \t\t seen: 6 times\n" +
                "============= \t \t =============\n" +
                "Price: \t 3.23\t\t seen: 5 times\n" +
                "-------------\t\t -------------\n" +
                "Price: \t 1.23\t\t seen: 1 times\n" +
                "name:   Bread \t\t seen: 6 times\n" +
                "============= \t \t =============\n" +
                "Price: \t 1.23\t\t seen: 6 times\n" +
                "-------------\t\t -------------\n" +
                "name: Cookies \t\t seen: 8 times\n" +
                "============= \t \t =============\n" +
                "Price: \t 2.25\t\t seen: 6 times\n" +
                "-------------\t\t -------------\n" +
                "name:  Apples \t\t seen: 4 times\n" +
                "============= \t \t =============\n" +
                "Price: \t 0.25\t\t seen: 2 times\n" +
                "-------------\t\t -------------\n" +
                "Price: \t 0.23\t\t seen: 2 times\n" +
                "Errors         \t \t seen: 4 times";

        //when
        String actual =parse.printReceipt();

        //then
        Assert.assertEquals(expected, actual);
    }


}
