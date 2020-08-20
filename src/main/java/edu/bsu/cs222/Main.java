package edu.bsu.cs222;


import javafx.scene.control.TextArea;

import javax.swing.*;
import java.awt.*;
import java.io.InputStream;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    WikiConnection connection = new WikiConnection();
    WikiParser wikiParser = new WikiParser();
    ArrayList revisions;
    ArrayList<String> alphabetizedArray;
    RevisionSorter sorter;
    String query;

    public void pageRequest(String prompt, int numberOfRevisions, TextArea textArea) {
        String validPrompt = formatPrompt(prompt);
        InputStream stream = connection.createURL("https://en.wikipedia.org/w/api.php?action=query&format=json&prop=revisions&titles=" + validPrompt + "&rvprop=timestamp|user&rvlimit=" + numberOfRevisions + "&redirects");
        if (stream == null) {
            connectionResult("failure to connect to Wikipedia server. Please try again later.");
        }
        wikiParser.findRevisions(stream);
        revisions = wikiParser.getRevisionData();
        formatRevisions();
        textArea.setText(query);
    }

    public void formatRevisions() {
        this.sorter = new RevisionSorter(revisions);
        sorter.seperateNamesAndTimeStamps();
        sorter.formatTimeStamps();
        sorter.removeDuplicates();
        this.alphabetizedArray = sorter.alphabetizeNames();
        connectionResult();

    }

    public String connectionResult(String warning)
    {

        return warning;
    }

    public void connectionResult()
    {
            String results = "";
            HashMap<String, ArrayList<ZonedDateTime>> listOfTimeStamps = sorter.getSortedRevisions();
            for(String name: alphabetizedArray)
            {

                results = results.concat("User: " + name + "    TimeStamp(s): " + extractTimeStamps(listOfTimeStamps.get(name)) + "\n");
            }
            query = results;
    }

    public String extractTimeStamps(ArrayList<ZonedDateTime> dates)
    {
        String concatenatedDate = "";
        for(ZonedDateTime date: dates)
        {
            concatenatedDate = concatenatedDate.concat("  " + DateTimeFormatter.ofPattern("dd/MM/yyyy - hh:mm").format(date) + " ");
        }

        return concatenatedDate;
    }

    public String formatPrompt(String prompt)
    {
        int len = prompt.length();
        if(prompt.contains(" "))
        {
            ArrayList<Integer> replace = new ArrayList<Integer>();
            for (int i = 0; i < len; i++)
            {
                if(prompt.charAt(i) == ' ')
                {
                    replace.add(i);
                }
            }
            char[] chars = prompt.toCharArray();
            for(int index:replace)
            {
                chars[index] = '+';
            }
            prompt = new String(chars);
        }
        return prompt;
    }
}
