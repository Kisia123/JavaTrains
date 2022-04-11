package com.pro.frames.station;

import com.pro.Main;
import com.pro.Time;
import com.pro.Train;
import com.pro.TrainStation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class AddTrainOnStationFrame extends JFrame {
    JPanel inputPanel;
    JPanel boxPanel;

    public AddTrainOnStationFrame(DefaultTableModel model, String stationName) {
        super("Add Train");
        setSize(500, 200);
        setLocation(100, 50);
        boxPanel = new JPanel();
        add(boxPanel);

        inputPanel = new JPanel();
        add(inputPanel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

        JLabel label1 = new JLabel("Choose Train:");
        JComboBox list = new JComboBox(Main.controller.trainList.stream().map(train -> train.name).toArray());
        boxPanel.add(label1).setPreferredSize(new Dimension(200,30));
        list.setMaximumRowCount(4);
        list.setPreferredSize(new Dimension(150,30));
        boxPanel.add(list);

        JLabel label2 = new JLabel("Departure Time:");
        inputPanel.add(label2).setPreferredSize(new Dimension(200,30));
        JTextField text = new JTextField("11:12");
        inputPanel.add(text).setPreferredSize(new Dimension(150,30));

        add(buttonPanel).setPreferredSize(new Dimension(400,100));
        JButton addTrain = new JButton("Add");
        addTrain.setBackground(Color.ORANGE);
        addTrain.setPreferredSize(new Dimension(100,20));
        addTrain.addActionListener(e-> {
           Train t =  Main.controller.findTrainByName((String) list.getSelectedItem());
           String timeString = text.getText();
           if(timeString.isEmpty()){
               return;
           }
           Time time = new Time(timeString);

           TrainStation s = Main.controller.findTrainStationByName(stationName);
           s.addTrain(t,time);
           model.addRow(new Object[]{t.name,t.numberOfSeats,t.state,timeString});
        });
        setLayout(new BoxLayout(this.getContentPane(),BoxLayout.Y_AXIS));
        buttonPanel.add(addTrain);
        setVisible(true);
    }
}
