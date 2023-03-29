package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Interface extends JFrame implements ActionListener {

    private JLabel title;
    private JPanel familias, fondo;
    private JButton addFamily, run, addNewObj;
    private JScrollPane scrol;
    private GridBagConstraints c;
    private JTextArea results;
    static Algorithm algorithm;
    private InterfaceNewObj objN;

    private int x = 5, y = 5, colum = 0, arron = 0, numClass;

    public Interface() {

        setTitle("Minimum Distance Classifier");
        setSize(810, 640);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        algorithm = new Algorithm();

        fondo = new JPanel();
        fondo.setSize(getSize());
        fondo.setLocation(0, 0);
        fondo.setLayout(null);
        fondo.setBackground(new Color(53, 53, 53));
        add(fondo);

        objN = new InterfaceNewObj(algorithm);
        objN.setVisible(false);
        objN.setBounds(50,50,250,520);
        fondo.add(objN);

        results = new JTextArea("");
        results.setEditable(false);
        results.setBounds(510, 50, 250, 520);
        results.setVisible(false);
        results.setBackground(new Color(222, 212, 202));
        fondo.add(results);

        title = new JLabel("Minimum Distance Classifier");
        title.setBounds(20, 10, 450, 20);
        title.setFont(new Font("", Font.BOLD, 22));
        title.setForeground(Color.WHITE);
        fondo.add(title);

        addFamily = new JButton("Add new Family");
        addFamily.setBounds(640, 10, 150, 20);
        addFamily.setBackground(new Color(73, 156, 84));
        addFamily.setFocusable(false);
        addFamily.setForeground(Color.WHITE);
        addFamily.addActionListener(this);
        fondo.add(addFamily);

        run = new JButton("Start");
        run.setBounds(365, 310, 80, 20);
        run.setBackground(new Color(53, 132, 228));
        run.addActionListener(this);
        run.setVisible(false);
        fondo.add(run);

        addNewObj = new JButton("Classify new Objects");
        addNewObj.setBounds(255, 580, 300, 20);
        addNewObj.setBackground(new Color(53, 132, 228));
        addNewObj.addActionListener(this);
        fondo.add(addNewObj);

        familias = new JPanel();
        familias.setBackground(new Color(80, 80, 80));
        familias.setLayout(new GridBagLayout());

        c = new GridBagConstraints();

        scrol = new JScrollPane(familias);
        scrol.setBounds(20, 50, 770, 520);
        fondo.add(scrol);
    }

    public void actionPerformed(ActionEvent actionEvent) {

        if(actionEvent.getSource() == addFamily){

            colum++;
            numClass++;

            if(colum == 1) {
                c.gridx = 0;
                c.gridy = arron;
            }
            if(colum > 1 && colum <= 3) {
                c.gridx = colum;
                c.gridy = arron;
            }

            familias.add((new ClassInterface(numClass, algorithm)), c);

            if(colum == 3) {
                colum = 0;
                arron++;
            }
            validate();
        }
        if(actionEvent.getSource() == run) {
            algorithm.runAlgorithm();
            results.setText(algorithm.outcome());
        }
        if(actionEvent.getSource() == addNewObj){

            scrol.setVisible(false);
            addFamily.setVisible(false);
            addNewObj.setVisible(false);
            objN.setVisible(true);
            results.setVisible(true);
            run.setVisible(true);
            fondo.validate();
            fondo.updateUI();

        }

    }
}
