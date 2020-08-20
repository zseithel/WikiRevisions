package edu.bsu.cs222;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class TimeStamp
{

    public String timeStamp;

    public ZonedDateTime calculateActualTime(String timeStamp)
    {
        this.timeStamp = timeStamp;
        Instant instantTime = Instant.parse(timeStamp);
        return instantTime.atZone(ZoneId.of("UTC-5"));
    }


}
