package com.pro.frames.station;

import com.pro.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class EditTrainFrame extends JFrame {
    JPanel inputPanel;
    public EditTrainFrame(JTable table,TrainStation ts){
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
            String name = (String) table.getValueAt(row, 0);

            Train train = ts.findTrain(name);
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
            else if(column == 3){
                if(input.isEmpty()){
                    return;
                }
                Time oldTime = new Time(table.getValueAt(row, 3).toString());
                Time newTime = new Time(input);
                ts.findTimeAndReplace(newTime,oldTime,train);
            }
            table.setValueAt(input,row,column);
            dispose();
        });
        add(save);
        setLayout(new FlowLayout());
        setVisible(true);

    }
}
