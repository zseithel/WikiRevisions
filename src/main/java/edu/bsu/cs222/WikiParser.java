package edu.bsu.cs222;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Map;


public class WikiParser
{
    JsonArray jsonArray = null;
    JsonObject pageDetails;
    ArrayList<String> revisionData = new ArrayList<>();


    public void findRevisions(InputStream stream)
    {
        /*YOU Are ALmost BAck to BEfore the fuCKery*/
        revisionData.clear();
        JsonParser parser = new JsonParser();
        Reader reader = new InputStreamReader(stream);
        JsonElement rootElement = parser.parse(reader);
        JsonObject rootObject = rootElement.getAsJsonObject();
        JsonObject pages = rootObject.getAsJsonObject("query").getAsJsonObject("pages");
        for(Map.Entry<String,JsonElement> entry: pages.entrySet())
        {
            pageDetails = entry.getValue().getAsJsonObject();
        }
        if (pageDetails.has("revisions")) {
            jsonArray = pageDetails.getAsJsonArray("revisions");
            for (JsonElement listOfRevisions : jsonArray) {
                for (Map.Entry<String, JsonElement> oneRevision : listOfRevisions.getAsJsonObject().entrySet()) {
                    String testObject = oneRevision.getValue().getAsString();
                    if (testObject.equals("")) {
                        //yes there is a warning here however this was intentional, and the warning even says that it might be so.
                    } else {
                        revisionData.add(testObject);
                    }
                }
            }
        }
        else
        {
            revisionData.add("That page doesn't seem to have any revisions. This is likely because the page you requested doesn't exist. Did you spell it correctly?");
            revisionData.add("1970-01-01T00:00:00Z");
        }
    }
    public ArrayList getRevisionData()
    {
        return revisionData;
    }
}
