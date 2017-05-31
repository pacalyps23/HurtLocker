import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;

/**
 * Created by luisgarcia on 5/31/17.
 */
public class testParse
{
   Parser parse;
    @Before
    public void initial()
    {
        parse = new Parser();
    }


    @Test
    public void parseTestObjects()
    {
        //given
        String [] myList = parse.getListFromFile("RawData.txt");
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
        String [] myList = parse.getListFromFile("RawData.txt");
        String expected = "naMe:Milk;price:3.23;type:Food;expiration:1/25/2016";

        //when
        String actual = myList[0];

        //then
        Assert.assertEquals(expected, actual);

    }
}
