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

}
