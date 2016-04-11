package com.Chitra;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by chitrakakkar on 4/8/16.
 */
public class TicketGuIFinal extends JFrame
{
    private static final  int Pref_W = 500;
    private static final  int Pref_H = 800;
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
    private JButton quitButton;
    private JLabel SeverityLabel;
    private JButton addToListButton;
    private JTextField TicketIdTextField;
    private JList ResolvedTicketList;
    private JTextField EnterResolutionTextField;
    protected DefaultListModel<Tickets> listModel;
    protected DefaultListModel<Tickets> listModel2;
    TicketGuIFinal()
    {
        super("Ticket Manager");
        listModel = new DefaultListModel<Tickets>();
        listModel2 = new DefaultListModel<Tickets>();
        OpenTicketList.setModel(listModel);

        configureSeverity();
        addListeners();
        setContentPane(rootPanel);
        pack();
        setVisible(true);

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
    protected void addListeners() {


        addToListButton.addActionListener(new ActionListener() {
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
        quitButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        resolveButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {


                JList OpenTicketList = new JList(listModel);
                JList ResolvedTicketList = new JList(listModel2);
                ResolvedTicketList.setModel(listModel2);
                for (Object selectedValue:OpenTicketList.getSelectedValuesList()
                     ) {
                    listModel2.addElement((Tickets)selectedValue);
                    listModel.removeElement(selectedValue);

                }

             // listModel.remove(OpenTicketList.getSelectedIndex());listModel2.addElement(ResolvedTickets);

            }
        });
    }


}
