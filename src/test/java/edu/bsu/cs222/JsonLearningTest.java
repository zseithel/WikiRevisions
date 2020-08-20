package edu.bsu.cs222;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.Assert;
import org.junit.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Map;

public class JsonLearningTest
{
    private ArrayList<String> revisionData = new ArrayList<String>();

    @Test
    public void countRevisions()
    {
        JsonParser parser = new JsonParser();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("sample.json");
        Reader reader = new InputStreamReader(inputStream);
        JsonElement rootElement = parser.parse(reader);
        Assert.assertNotNull(rootElement);
        JsonObject rootObject = rootElement.getAsJsonObject();
        JsonObject pages = rootObject.getAsJsonObject("query").getAsJsonObject("pages");
        JsonArray array = null;
        for(Map.Entry<String,JsonElement> entry: pages.entrySet())
        {
            JsonObject entryObject = entry.getValue().getAsJsonObject();
            array = entryObject.getAsJsonArray("revisions");
        }
        Assert.assertEquals(5,array.size());
        for (JsonElement listOfRevisions : array)
        {
            for(Map.Entry<String, JsonElement> oneRevision: listOfRevisions.getAsJsonObject().entrySet())
            {
                String testObject = oneRevision.getValue().getAsString();
                if(testObject.equals(""))
                {

                }
                else
                {
                    revisionData.add(testObject);
                }
            }
        }
    }
    public ArrayList getRevivisonData()
    {
        return revisionData;
    }

}
