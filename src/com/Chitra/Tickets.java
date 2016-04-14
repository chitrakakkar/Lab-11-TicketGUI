package com.Chitra;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by chitrakakkar on 4/8/16.
 * This class has a ticket and all its attributes/properties
 * getter and setters-We can add more attributes to it like Date resolved and date Opened-
 * Version -2 would have date resolved and open date.
 */
public class Tickets
{
    protected Integer TicketId =0;
    private static int staticTicketIDCounter =1;
    protected String description;

    public Integer getTicketId() {
        return TicketId;
    }

    public void setTicketId(Integer ticketId) {
        TicketId = ticketId;
    }

    protected int severity;
    protected String resolution;

    public String getResolution()
    {
        return resolution;
    }

    public void setResolution(String resolution)
    {
        this.resolution = resolution;
    }



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

    Tickets(int ticketId, String desc,int Sev)
    {
//            this.TicketId = staticTicketIDCounter;
//            staticTicketIDCounter++;
        this.TicketId = ticketId;
            this.description = desc;
            this.severity = Sev;
    }
    @Override
    public String toString()
    {
        return (this.TicketId + "."+ this.description +": " +  this.severity);
    }


}
