package com.Chitra;

import com.sun.tools.internal.ws.processor.model.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

/**
 * Created by chitrakakkar on 4/8/16.
 */
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
    protected DefaultListModel<Tickets> listModel;
    //protected DefaultListModel<Tickets> listModel2;

    Vector <Tickets> TicketVector;
    TicketGuIFinal()
    {
        super("Ticket Manager");
        TicketVector = FileIO.ReadingFile();

//        listModel2 = new DefaultListModel<>(TicketVector);
        //listModel = new DefaultListModel<>();
        //listModel2 = new DefaultListModel<>();
        OpenTicketList.setModel(listModel);

        configureSeverity();
        addListeners();
        setContentPane(rootPanel);
        pack();
        setVisible(true);
        listModel = new DefaultListModel<>(TicketVector);



    }
    protected void configureSeverity()
    {
        severityComboBox.insertItemAt(0,0);

        for (int x = 1; x <= 5; x++) {

//            Integer tempNum = x;
//            String severity = Integer.toString(tempNum);
            severityComboBox.addItem(x);
        }
    }
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
        Save.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                listModel.removeElement(OpenTicketList.getSelectedValue());
                //write all resolved tickets into a file

                    //Save all of the data...
                FileIO.WriteResolvedTickets(TicketVector);
            }
        });
        resolveButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                EnterResolutionTextField.grabFocus();
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
        Quit.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (JOptionPane.OK_OPTION == JOptionPane.showConfirmDialog(TicketGuIFinal.this, "Are you sure you want to save and exit?", "Exit?", JOptionPane.OK_CANCEL_OPTION)) {
                    //Save all of the data...
                    FileIO.WriteOpenTickets(TicketVector);}

                System.exit(0);
                //write all open tickets into a file

            }
        });
    }


}
