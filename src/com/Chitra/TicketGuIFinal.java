package com.Chitra;

import com.sun.tools.internal.ws.processor.model.Model;
import sun.security.krb5.internal.Ticket;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.util.Vector;

/**
 * Created by chitrakakkar on 4/8/16.
 */
//This class takes care of the GuI and Its components
// has quite some listeners which perform some actions on button clicks

public class TicketGuIFinal extends JFrame
{
    private static final  int Pref_W = 500;
    private static final  int Pref_H = 500;
    // Stack over flow
    @Override
    public Dimension getPreferredSize()
    {
        //return super.getPreferredSize();
        return new Dimension(Pref_W,Pref_H);
    }

    private JPanel rootPanel;
    private JTextField ProblemTextField;
    private JComboBox <Integer>severityComboBox;
    private JList<Tickets> OpenTicketList;
    private JButton resolveButton;
    private JButton Save;
    private JLabel SeverityLabel;
    private JButton addToListButton;
    private JTextField TicketIdTextField;
    private JTextField EnterResolutionTextField;
    private JButton Quit;
    private Tickets T;
    protected DefaultListModel<Tickets> listModel;
    //protected DefaultListModel<Tickets> listModel2;

    Vector <Tickets> ResolvedTicketVector = new Vector<>();
    Vector<Tickets> OpenTicketVector = new Vector<>();
    TicketGuIFinal()
    {
        super("Ticket Manager");
        // reading from file
        OpenTicketVector = FileIO.ReadingFile();


//        listModel2 = new DefaultListModel<>(TicketVector);
        listModel = new DefaultListModel<>();
        //listModel2 = new DefaultListModel<>();
        //listModel = new DefaultListModel();
        // adding it to the list model
        for (Tickets t:OpenTicketVector
                ) {
            listModel.addElement(t);

        }

        OpenTicketList.setModel(listModel);

        configureSeverity();
        addListeners();
        setContentPane(rootPanel);
        pack();
        setVisible(true);

    }
    // Severity combo box design
    protected void configureSeverity()
    {
        severityComboBox.insertItemAt(0,0);

        for (int x = 1; x <= 5; x++) {

//            Integer tempNum = x;
//            String severity = Integer.toString(tempNum);
            severityComboBox.addItem(x);
        }
    }
    //add to the list button click action code
    protected void addListeners()
    {
        addToListButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {

                String ticket = ProblemTextField.getText();
                ticket = ticket.trim();
                int Id = (Integer.parseInt(TicketIdTextField.getText()));
                int sevLevel = Integer.parseInt(severityComboBox.getSelectedItem().toString());
                //int severity = (Integer)severityLevel.getSelectedItem();
                Tickets newTickets = new Tickets(Id,ticket,sevLevel);
                listModel.addElement(newTickets);
                ProblemTextField.setText("");
                TicketIdTextField.setText("");
                //severityComboBox.insertItemAt(0,0);
                //severityComboBox.removeItem(severityComboBox.getSelectedIndex());
                severityComboBox.setSelectedIndex(0);

            }
        });
        // Save button click action code
        Save.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                for (Object o:listModel.toArray()
                     )
                {
                    // checking if the ticket is already presnt in the file
                    if(!OpenTicketVector.contains(o))
                    OpenTicketVector.add((Tickets) o);
                }
                //FileIO.WriteResolvedTickets(ResolvedTicketVector);
                //FileIO.WriteOpenTickets(OpenTicketVector);

            }
        });
        //resolve button action
        resolveButton.addActionListener(new ActionListener()
        {
            // deleting data from list model-adding it to the file
            // deleting it from Open ticket vector too.

            @Override
            public void actionPerformed(ActionEvent e)
            {
                String r = EnterResolutionTextField.getText();
               // EnterResolutionTextField.grabFocus();
                Tickets elements = OpenTicketList.getSelectedValue();
                elements.setResolution(r);
                ResolvedTicketVector.add(elements);
                listModel.removeElement(elements);
                OpenTicketVector.remove(elements);

                EnterResolutionTextField.setText("");
//                JList OpenTicketList = new JList(listModel);
//                JList ResolvedTicketList = new JList(listModel2);
//                ResolvedTicketList.setModel(listModel2);
//                for (Object selectedValue:OpenTicketList.getSelectedValuesList()
//                     ) {
//                    listModel2.addElement((Tickets)selectedValue);
//                    listModel.removeElement(selectedValue);
//                }
            }
        });
        //quits the windows and writes into the file-> saving info.
        //Only if they don't already exist
        Quit.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {

                for (Object o:listModel.toArray()
                        ) {

                    if (!OpenTicketVector.contains(o)) {

                        OpenTicketVector.add((Tickets) o);
                       // ((Tickets) o).setTicketId(T.getTicketId());
                    }

                }
                if (JOptionPane.OK_OPTION == JOptionPane.showConfirmDialog(TicketGuIFinal.this, "Are you sure you want to save and exit?", "Exit?", JOptionPane.OK_CANCEL_OPTION)) {
                    //Save all of the data...

                    FileIO.WriteResolvedTickets(ResolvedTicketVector);
                    FileIO.WriteOpenTickets(OpenTicketVector);
                }

                    //FileIO.WriteOpenTickets(OpenTicketVector);}

                System.exit(0);

            }
        });
    }


}
