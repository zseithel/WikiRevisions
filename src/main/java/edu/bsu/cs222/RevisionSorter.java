package edu.bsu.cs222;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class RevisionSorter
{
    ArrayList<String> unsortedString;
    HashMap<String, ArrayList<ZonedDateTime>> sortedRevisions = new HashMap<>();
    ArrayList<ZonedDateTime> formattedTimeStamps = new ArrayList<>();
    ArrayList<String> names = new ArrayList<>();
    ArrayList<String> timeStamps = new ArrayList<>();
    TimeStamp timeStampFormatter  = new TimeStamp();


    public RevisionSorter(ArrayList<String> unsotredRevisions)
    {
        this.unsortedString = unsotredRevisions;
    }



    public void seperateNamesAndTimeStamps()
    {
        for (int iterator = 0; iterator < unsortedString.size(); iterator++)
        {
            if (iterator % 2 == 0)
            {
                names.add(unsortedString.get(iterator));
            }
            else
            {
                timeStamps.add(unsortedString.get(iterator));
            }
        }
    }



    public void formatTimeStamps()
    {
        for(String stamp: timeStamps)
        {
            formattedTimeStamps.add(timeStampFormatter.calculateActualTime(stamp));
        }
    }



    public void removeDuplicates()
    {
        for (int iterator = 0; iterator < names.size(); iterator++)
        {
            if(sortedRevisions.containsKey(names.get(iterator)))
            {
                ArrayList<ZonedDateTime> values = sortedRevisions.get(names.get(iterator));
                values.add(formattedTimeStamps.get(iterator));
                sortedRevisions.put(names.get(iterator),values);
            }
            else
            {
                ArrayList<ZonedDateTime> stamps = new ArrayList<>();
                stamps.add(formattedTimeStamps.get(iterator));
                sortedRevisions.put(names.get(iterator),stamps);
            }
        }
    }



    public ArrayList<String> alphabetizeNames()
    {
        NameSort nameSorter = new NameSort(names);
        names = nameSorter.alphabetizeNames();
        return names;
    }

    public HashMap<String, ArrayList<ZonedDateTime>> getSortedRevisions() {
        return sortedRevisions;
    }
}