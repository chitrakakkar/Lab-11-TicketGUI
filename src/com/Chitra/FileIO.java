package com.Chitra;

import sun.security.krb5.internal.Ticket;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Vector;

/**
 * Created by chitrakakkar on 4/8/16.
 */
public class FileIO
{

    private static String Rfilename = "ResolvedTicket.TXT";
    private static String OFileName = " OpenTickets.TXT";

    // reading file method which has a vector to store all tickets
    //and returns all tickets vector
    static Vector ReadingFile()
    {
        // vector implements a dynamic array. It is similar to ArrayList
        // but more synchronized and contains many legacy methods
        Vector<Tickets> ALlTickets = new Vector<Tickets>();
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(OFileName));
            String TicketData = reader.readLine();
            while (TicketData != null) {
                Tickets T = parseTicketDataIntoTicket(TicketData);
                ALlTickets.add(T);
                TicketData = reader.readLine();

            }
            return ALlTickets;
        } catch (IOException ioe) {
            System.out.println("Error reading file");
            return new Vector<>();
        }
    }
    //writing open tickets
    static void WriteOpenTickets(Vector<Tickets>  tickets)
    {
        try
        {
            BufferedWriter writer1 = new BufferedWriter(new FileWriter(OFileName,false));
            for (Tickets ticket1 : tickets)
            {
                String output =ticket1.TicketId.toString()+":";
                output += ticket1.getDescription()+":";
                output += ticket1.getSeverity();
                output+= "\n";
                writer1.write(output);
            }
            writer1.close();

        }
        catch (IOException ioe)
        {
            System.out.println("Error writing data");
        }

    }
    //writing Resolved Tickets
    static void WriteResolvedTickets(Vector<Tickets> tickets)
    {

        //Join name and list of times with commas eg.  "Como,45,56,32,56"

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Rfilename,true)))
        {

            for (Tickets ticket : tickets)
            {
                String output =ticket.TicketId.toString()+":";
                output += ticket.getDescription()+":";
                output += ticket.getSeverity()+":";
                output += "Resolution : " + ticket.getResolution();
                output+= "\n";
                writer.write(output);
            }
            writer.close();
        }

        catch (IOException ioe) {
            System.out.println("Error writing data");
        }

    }
    // parsing the Ticket data after reading it from the file
    static Tickets parseTicketDataIntoTicket(String TicketData)
    {
        String [] ticketData = TicketData.split(":");
        String TicketDesc = ticketData[1];
        int TicketSeverity =  Integer.parseInt(ticketData[2]);
        int TicketId = Integer.parseInt(ticketData[0]);
        Tickets ticketObjectFromFile = new Tickets(TicketId,TicketDesc,TicketSeverity);
        return ticketObjectFromFile;
    }



}


