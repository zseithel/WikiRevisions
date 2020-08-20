package edu.bsu.cs222;

import org.junit.Assert;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class WikiParserTester
{
    @Test
    public void testFindRevisions()
    {
        WikiConnection connection = new WikiConnection();
        InputStream stream = connection.createURL("https://en.wikipedia.org/w/api.php?action=query&format=json&prop=revisions&titles=DND&rvprop=timestamp|user&rvlimit=" + 5 + "&redirects");
        WikiParser wikiParserTester = new WikiParser();
        wikiParserTester.findRevisions(stream);
        List<String> result = wikiParserTester.getRevisionData();
        Assert.assertNotNull(result);
    }
    @Test
    public void testNoAnons()
    {
        WikiConnection connection = new WikiConnection();
        InputStream stream = connection.createURL("https://en.wikipedia.org/w/api.php?action=query&format=json&prop=revisions&titles=DND&rvprop=timestamp|user&rvlimit=" + 5 + "&redirects");
        WikiParser wikiParserTester = new WikiParser();
        wikiParserTester.findRevisions(stream);
        List<String> array = wikiParserTester.getRevisionData();
        boolean result = array.contains("");
        Assert.assertFalse(result);
    }

}
