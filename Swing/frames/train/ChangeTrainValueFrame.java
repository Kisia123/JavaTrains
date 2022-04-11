package com.pro.frames.train;

import com.pro.Main;
import com.pro.Train;
import com.pro.TrainState;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ChangeTrainValueFrame extends JFrame{
    JPanel inputPanel;
    public ChangeTrainValueFrame(JTable table){
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
                JOptionPane.showMessageDialog(null,"You need to select train first");
                dispose();
                return;
            }
            Train train = Main.controller.trainList.get(row);
            String input = text.getText();
            if(column == 0){
                if(input.isEmpty()){
                    return;
                }
                train.name = input;
            }
            else if(column == 1){
                if(input.isEmpty()){
                    return;
                }
                train.numberOfSeats = Integer.parseInt(input);
            }
            else if(column == 2){
                TrainState s;
                switch (input) {
                    case "opozniony" -> s = TrainState.opozniony;
                    case "nowy" -> s = TrainState.nowy;
                    case "zepsuty" -> s = TrainState.zepsuty;
                    case "zwykly" -> s = TrainState.zwykly;
                    default -> {
                        s = train.state;
                        input = train.state.toString();
                    }
                }
                train.state=s;
            }
            table.setValueAt(input,row,column);
            dispose();
        });
        add(save);
        setLayout(new FlowLayout());
        setVisible(true);

    }
}
