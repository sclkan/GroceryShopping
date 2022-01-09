package subsetsum;
import java.util.ArrayList;

/**
 * Find the best combination of prices given by budget
 * @author Sean Kan
 *
 */
public class SubsetSum
{
    /**
     *Provides sum of given ArrayList
     * @param list     ArrayList
     * @return sum of ArrayList (double)
     */
    private static double sum(ArrayList<Double> list)
    {
        double sum = 0;

        for(Double i:list)
            sum+=i;
        return sum;
    }

    /**
     * Finds the max value of collection
     * @param list     Collection of prices which is an ArrayList of ArrayList
     * @return list with the largest sum
     */
    private static ArrayList<Double> max(ArrayList<ArrayList<Double>> list)
    {
        double max=0;
        int maxIndex=0;

        for(int i = 0; i<list.size();i++)
        {
            if (sum(list.get(i)) > max)
            {
                max = sum(list.get(i));
                maxIndex = i;
            }
        }
        return list.get(maxIndex);

    }

    /**
     * Finds the most appropriate set closest to the budget without going over
     * @param input     ArrayList of prices of grocery items
     * @param budget     budget given by user
     * @return best matching combo
     */
    public static ArrayList<Double> findSubset(ArrayList<Double> input, double budget)
    {
        if (budget >= sum(input) )
        {
            return input;
        }
        else
        {
            ArrayList<ArrayList<Double>> col = new ArrayList<>();
            ArrayList<Double> currentSubset = new ArrayList<>();
            col.add(currentSubset);
            for (int i=0; i < input.size(); i++)
            {
                int colSize = col.size();

                for (int j=0; j<colSize; j++)
                {
                    //Define current set
                    currentSubset.addAll(col.get(j));

                    //Adds to collection if not an exact match
                    if (sum(currentSubset) + input.get(i) < budget)
                    {
                        ArrayList<Double> copy = (ArrayList<Double>) currentSubset.clone();
                        copy.add(input.get(i));
                        col.add(copy);
                    }

                    //Break and return set if there is an exact match
                    else if (sum(currentSubset) + input.get(i) == budget)
                    {
                        currentSubset.add(input.get(i));
                        return currentSubset;
                    }
                    currentSubset.clear();
                }
            }
            return max(col);
        }
    }
}
