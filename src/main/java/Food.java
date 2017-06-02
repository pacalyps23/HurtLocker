import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by luisgarcia on 5/31/17.
 */
public class Food
{

    private String name;
    private String price;
    private String type;
    private String exp;

    public Food(String name, String price, String type, String exp)
    {
        this.name = name;
        this.price = price;
        this.type = type;
        this.exp = exp;

    }

    public Food(){};

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getPrice()
    {
        return price;
    }

    public void setPrice(String price)
    {
        this.price = price;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getExp()
    {
        return exp;
    }

    public String toString()
    {
        return this.name + " " + this.price + " " + this.type + " " + this.exp;
    }

}

