package com.pro.frames.station;

import com.pro.Main;
import com.pro.TrainStation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;


public class AddTrainStationFrame extends JFrame {
    int height = 20;
    int width = 100;
    JPanel inputPanel;
    public AddTrainStationFrame(DefaultTableModel model){
        super("New TrainStation");
        Dimension dimension = new Dimension(width,height);
        setSize(400,400);
        inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3,2,40,40));
        inputPanel.setBorder(new EmptyBorder(50,50,50,50));
        add(inputPanel);
        JLabel label1 = new JLabel("Name:");
        JLabel label2 = new JLabel("Max capacity:");

        JTextField text1 = new JTextField();
        JTextField text2 = new JTextField();

        inputPanel.add(label1);
        inputPanel.add(text1);
        inputPanel.add(label2);
        inputPanel.add(text2);

        JButton addTrainStation = new JButton("Add");
        addTrainStation.setBackground(Color.ORANGE);
        addTrainStation.setPreferredSize(dimension);

        addTrainStation.addActionListener(e->{
            String name = text1.getText();
            String maxCap = text2.getText();

            if(name.isEmpty()){
                JOptionPane.showMessageDialog(null,"Enter name first");
                return;
            }
            if(maxCap.isEmpty()){
                maxCap = "100";
            }

            TrainStation s = new TrainStation(name,Integer.parseInt(maxCap));
            Main.controller.trainStationsList.add(s);
            model.addRow(s.printToTable());
            dispose();
        });

        add(addTrainStation);
        setLayout(new FlowLayout());
        setVisible(true);
    }
}
