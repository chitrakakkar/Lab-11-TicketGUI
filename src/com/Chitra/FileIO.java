package com.Chitra;

import sun.security.krb5.internal.Ticket;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Vector;

/**
 * Created by chitrakakkar on 4/8/16.
 */
public class FileIO {

    private static String filename = "ResolvedTicket.TXT";


    public void ReadingFile() {
        // vector implements a dynamic array. It is similar to ArrayList
        // but more synchronized and contains many legacy methods
        Vector<Tickets> ALlTickets = new Vector<Tickets>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String TicketData = reader.readLine();
            while (TicketData != null) {
                Tickets l = parseLakeDataIntoTicket(TicketData);
                ALlTickets.add(l);
                TicketData = reader.readLine();

            }
        } catch (IOException ioe) {
            System.out.println("Error reading file");
            return;


        }
    }
    static void WriteTickets(Vector<Tickets> tickets)
    {

        //Join name and list of times with commas eg.  "Como,45,56,32,56"

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename)))
        {

            for (Tickets ticket : tickets)
            {
                String output =ticket.TicketId.toString();
                output += ticket.getDescription();
                output += ticket.getSeverity();
                output+= "\n";
                writer.write(output);
            }
        }

        catch (IOException ioe) {
            System.out.println("Error writing data");
        }
    }

    static Tickets parseLakeDataIntoTicket(String TicketData)
    {
        String [] ticketData = TicketData.split(":");
        String TicketDesc = ticketData[1];
        int TicketSeverity =  Integer.parseInt(ticketData[2]);
        int TicketId = Integer.parseInt(ticketData[0]);
        Tickets ticketObjectFromFile = new Tickets(TicketId,TicketDesc,TicketSeverity);
        return ticketObjectFromFile;



    }



}


