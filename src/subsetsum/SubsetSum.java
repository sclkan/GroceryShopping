package subsetsum;
import cs1c.SongEntry;
import java.util.ArrayList;

/**
 * Find the best combination of prices/songs given by budget/duration
 * @author Sean Kan
 *
 */
public class SubsetSum
{

    /**
     * Provides sum of given ArrayList
     *
     * @param list     ArrayList
     * @return sum of ArrayList as double
     */
    private static <T>double sum(ArrayList<T> list)
    {
        double sum = 0;
        for (Object i : list)
        {
            if(i instanceof Double)
                sum = (Double) i + sum;

            else if(i instanceof SongEntry)
                sum = ((SongEntry) i).getDuration() + sum;
        }
        return sum;
    }

    /**
     * Finds the max value of collection
     *
     * @param list     Collection of prices/songs which is an ArrayList of ArrayList
     * @return list with the largest sum
     */
    private static <T>ArrayList<T> max(ArrayList<ArrayList<T>> list)
    {
        double max = 0;
        int maxIndex = 0;

        for (int i = 0; i < list.size(); i++)
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
     * Finds grocery list closest to the given budget without going over
     *
     * @param input     ArrayList of prices of grocery items
     * @param budget    budget given by user
     * @return best combination of prices
     */
    public static ArrayList<Double> findSubset(ArrayList<Double> input, double budget)
    {
        if (budget >= sum(input))
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

    /**
     * Finds playlist that is closest to the given duration without going over
     * @param input     ArrayList of songs with genre, artist name, title, duration and song id
     * @param duration     duration given by user
     * @return best combination of songs
     */
    public static ArrayList<SongEntry> findSubsetOfSongs(ArrayList<SongEntry> input, double duration)
    {
        if (duration >= sum(input))
        {
            return input;
        }
        else
        {
            ArrayList<ArrayList<SongEntry>> col = new ArrayList<>();
            ArrayList<SongEntry> currentSubset = new ArrayList<>();
            col.add(currentSubset);

            for (int i = 0; i < input.size(); i++)
            {
                int colSize = col.size();
                for (int j = 0; j < colSize; j++)
                {
                    currentSubset.addAll(col.get(j));
                    double sum = 0;
                    if (sum(currentSubset) + input.get(i).getDuration() < duration)
                    {
                        ArrayList<SongEntry> copy = (ArrayList<SongEntry>) currentSubset.clone();
                        copy.add(input.get(i));
                        col.add(copy);
                    }

                    //Break and return set if there is an exact match
                    else if (sum(currentSubset) + input.get(i).getDuration() == duration)
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