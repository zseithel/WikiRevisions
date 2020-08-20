package edu.bsu.cs222;

import org.junit.Assert;
import org.junit.Test;

import java.io.InputStream;

public class UrlConnectionTester
{
    @Test
    public void testUrlConnection()
    {
        WikiConnection connection = new WikiConnection();
        InputStream stream = connection.createURL("https://en.wikipedia.org/w/api.php?action=query&format=json&prop=revisions&titles=DND&rvprop=timestamp|user&rvlimit=" + 5 + "&redirects");
        Assert.assertNotNull(stream);
    }
}
