package subsetsum;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Class to read input file that contains prices of different grocery items
 * and store each price as an array list
 * @author Sean Kan
 *
 */

public class GroceriesFileReader
{
    /**
     * Reads csv file and return ArrayList as output
     * @param filePath     Location of the input file
     * @return an arrayList of price (Double)
     */
    public ArrayList<Double> readFile(String filePath)
    {
        ArrayList<Double> price = new ArrayList<Double>();
        try
        {
            Scanner reader = new Scanner(new File(filePath));
            while (reader.hasNextLine())
            {
                String input = reader.nextLine();
                String[] temp = input.split(",");
                price.add(Double.parseDouble(temp[1]));
            }
            reader.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        return price;
    }
}
