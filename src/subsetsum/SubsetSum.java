package subsetsum;
import java.util.ArrayList;

public class SubsetSum
{
    private static double sum(ArrayList<Double> list)
    {
        double sum = 0;
        for(Double i:list)
            sum+=i;
        return sum;
    }

//    private static ArrayList<Double> max(ArrayList<ArrayList<Double>> list)
//    {
//
//    }

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
                    currentSubset.addAll(col.get(j));

                    if (sum(currentSubset) + input.get(i) < budget)
                    {
                        ArrayList<Double> copy = (ArrayList<Double>) currentSubset.clone();
                        copy.add(input.get(i));
                        col.add(copy);
                    }
                    else if (sum(currentSubset) + input.get(i) == budget)
                    {
                        currentSubset.add(input.get(i));
                        return currentSubset;
                    }
                    currentSubset.clear();
                }
            }


            return currentSubset;

        }

    }
}
