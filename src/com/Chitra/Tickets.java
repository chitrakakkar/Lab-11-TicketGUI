package com.Chitra;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by chitrakakkar on 4/8/16.
 */
public class Tickets
{
    protected Integer TicketId;
    private static int staticTicketIDCounter =1;
    protected String description;
    protected int severity;

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    protected String resolution;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSeverity() {
        return severity;
    }

    public void setSeverity(int severity) {
        this.severity = severity;
    }

    Tickets(String desc,int ticketId, int Sev)
    {

        this.description = desc;
        this.severity = Sev;
        this.TicketId = staticTicketIDCounter;
        staticTicketIDCounter++;

    }

    @Override
    public String toString()
    {
        return (this.TicketId + "."+ this.description +": " +  this.severity);
    }


}
