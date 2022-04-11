package com.pro.frames.train;

import com.pro.Main;
import com.pro.Train;
import com.pro.TrainState;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class AddTrainFrame extends JFrame{
    int height = 20;
    int width = 100;
    JPanel inputPanel;
    public AddTrainFrame(DefaultTableModel model) {
        Dimension dimension = new Dimension(width,height);
        setSize(400,400);
        setLocation(150,40);
        inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3,2,40,40));
        inputPanel.setBorder(new EmptyBorder(50,50,50,50));
        add(inputPanel);

        JLabel label1 = new JLabel("Name:");
        JLabel label2 = new JLabel("Number Of Seats:");
        JLabel label3 = new JLabel("State:");
        JTextField text1 = new JTextField();
        JTextField text2 = new JTextField();
        JTextField text3 = new JTextField();

        inputPanel.add(label1);
        inputPanel.add(text1);
        inputPanel.add(label2);
        inputPanel.add(text2);
        inputPanel.add(label3);
        inputPanel.add(text3);
        JButton addTrain = new JButton("Add Train");
        addTrain.setBackground(Color.ORANGE);
        addTrain.setPreferredSize(dimension);

        addTrain.addActionListener(e->{
            String name = text1.getText();
            String seats = text2.getText();
            String state = text3.getText();
            if(name.isEmpty()){
              JOptionPane.showMessageDialog(null,"Enter name first");
                return;
            }
            if(seats.isEmpty()){
                seats = "100";
            }
            TrainState s = switch (state) {
                case "opozniony" -> TrainState.opozniony;
                case "nowy" -> TrainState.nowy;
                case "zepsuty" -> TrainState.zepsuty;
                default -> TrainState.zwykly;
            };

            Train t = new Train(name,Integer.parseInt(seats),s);
            Main.controller.trainList.add(t);
            model.addRow(t.printToTable());
            dispose();
        });
        add(addTrain);
        setLayout(new FlowLayout());
        setVisible(true);
    }
}
