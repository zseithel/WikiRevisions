package edu.bsu.cs222;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class WikiConnection
{

    public InputStream createURL(String prompt) {
        try {
            URL url = new URL(prompt);
            URLConnection connection = url.openConnection();
            connection.setRequestProperty("User-Agent", "WikiParser Tracker/0.1 (http://www.cs.bsu.edu/~pvg/courses/cs222Fa17; @bsu.edu)");
            return connection.getInputStream();
        }
        catch (IOException e)
        {
            return null;
        }
    }
}
