package com.pro.frames.station;

import com.pro.Main;
import com.pro.TrainStation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ChangeStationValueFrame extends JFrame {
    JPanel inputPanel;
    public ChangeStationValueFrame(JTable table){
        setSize(400,400);
        setLocation(10,10);
        inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3,2,40,40));
        inputPanel.setBorder(new EmptyBorder(50,50,50,50));
        add(inputPanel);

        JLabel label = new JLabel("Correct info:");
        JTextField text = new JTextField();
        inputPanel.add(label);
        inputPanel.add(text);

        JButton save = new JButton("Save");
        save.setBackground(Color.ORANGE);
        save.setPreferredSize(new Dimension(100,20));
        save.addActionListener(e->{
            int row = table.getSelectedRow();
            int column = table.getSelectedColumn();
            if(row<0||column<0){
                JOptionPane.showMessageDialog(null,"You need to select train station first");
                dispose();
                return;
            }
            TrainStation trainStation = Main.controller.trainStationsList.get(row);
            String input = text.getText();
            if(column == 0){
                if(input.isEmpty()){
                    return;
                }
                trainStation.name = input;
            }
            else if(column == 1){
                if(input.isEmpty()){
                    return;
                }
                trainStation.maxCapacity = Integer.parseInt(input);
            }

            table.setValueAt(input,row,column);
            dispose();
        });
        add(save);
        setLayout(new FlowLayout());
        setVisible(true);

    }
}
