package com.company;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;


public class InterfaceNewObj extends JPanel implements ActionListener {

    private JPanel encMarco, principal;
    private JLabel clase;
    private JButton addObj, delObj, addData;
    private JScrollPane scrol;
    private JTextField campX, campY;
    private ArrayList<JTextField> listX, listY;
    private GridBagConstraints label, editX, editY;
    static Algorithm algo;
    private int nCam = 1, arron = 0, cont=0;

    public InterfaceNewObj(Algorithm algorithm) {

        setBackground(new Color(201, 184, 167));
        setLayout(new BorderLayout());

        algo = algorithm;

        listX = new ArrayList<>();
        listY = new ArrayList<>();

        label = new GridBagConstraints();
        label.gridx = 0;
        label.weightx = 0.4;
        editX = new GridBagConstraints();
        editX.gridx = 1;
        editX.weightx = 0.3;
        editY = new GridBagConstraints();
        editY.gridx = 2;
        editY.weightx = 0.3;

        encMarco = new JPanel();
        encMarco.setBackground(new Color(201, 184, 167));
        encMarco.setLayout(new GridLayout(0,2));
        add(encMarco, BorderLayout.NORTH);

        principal = new JPanel();
        principal.setBackground(new Color(222, 212, 202));
        principal.setLayout(new GridBagLayout());

        scrol = new JScrollPane(principal);
        add(scrol, BorderLayout.CENTER);

        clase = new JLabel("New Objects " );
        clase.setSize(50, 20);
        clase.setFont(new Font("", Font.BOLD, 14));
        clase.setBackground(new Color(201, 184, 167));
        clase.setHorizontalAlignment(SwingConstants.CENTER);
        clase.setVerticalAlignment(SwingConstants.CENTER);
        encMarco.add(clase);

        addObj = new JButton("Add Object");
        addObj.setSize(50, 20);
        addObj.setBackground(new Color(80, 80, 80));
        addObj.setForeground(Color.white);
        addObj.setFocusable(false);
        addObj.addActionListener(this);
        encMarco.add(addObj);

        delObj = new JButton("Delete");
        delObj.setSize(50, 20);
        delObj.addActionListener(this);

        addData = new JButton("Add Data");
        addData.setSize(50, 20);
        addData.setBackground(new Color(255, 105, 97));
        addData.setFocusable(false);
        addData.addActionListener(this);
        add(addData, BorderLayout.SOUTH);

    }

    public void actionPerformed(ActionEvent actionEvent) {

        if(actionEvent.getSource() == addObj) {

            label.gridy = arron;
            principal.add(new JLabel("Object " + nCam), label);

            campX = new JTextField();
            campX.setPreferredSize(new Dimension(80,25));
            campY = new JTextField();
            campY.setPreferredSize(new Dimension(80,25));

            listX.add(campX);
            listY.add(campY);

            editX.gridy = arron;
            principal.add(campX, editX);
            editY.gridy = arron;
            principal.add(campY, editY);

            arron++;
            nCam++;
            cont++;


            validate();
            updateUI();
        }
        if(actionEvent.getSource() == addData) {

            principal.setEnabled(false);
            addObj.setEnabled(false);
            addData.setEnabled(false);
            addData.setText("DONE");
            addData.setBackground(new Color(119, 221, 119));

            algo.cont=cont;

            int numObj;
            numObj = nCam - 1;
            double dataClass[][] = new double[2][numObj];

            int xc = 0;
            for(JTextField x : listX) {
                x.setEnabled(false);
                dataClass[0][xc] = Double.parseDouble(x.getText());
                xc++;
            }
            int yc = 0;
            for(JTextField y : listY) {
                y.setEnabled(false);
                dataClass[1][yc] = Double.parseDouble(y.getText());
                yc++;
            }

            algo.objNew.add(dataClass);
        }
    }
}
