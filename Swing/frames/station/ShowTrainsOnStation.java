package com.pro.frames.station;

import com.pro.Main;
import com.pro.Time;
import com.pro.Train;
import com.pro.TrainStation;
import com.pro.frames.train.ChangeTrainValueFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ShowTrainsOnStation extends JFrame {
    DefaultTableModel model;

    public ShowTrainsOnStation(String stationName, DefaultTableModel tsModel, int tsRow) {
        super("Trains on Station");
        setSize(new Dimension(700, 500));
        setLocation(10, 10);
        String[] names = new String[]{"NAME", "SEATS", "STATE", "DEPARTURE TIME"};
        model = new DefaultTableModel(0, names.length);
        JTable table = new JTable(model);
        for (int i = 0; i < names.length; i++) {
            TableColumn tc = table.getColumnModel().getColumn(i);
            tc.setHeaderValue(names[i]);
        }

        TrainStation ts = Main.controller.findTrainStationByName(stationName);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        JButton addTrainButton = new JButton("Add Train");
        addTrainButton.setBackground(Color.ORANGE);
        addTrainButton.addActionListener(e -> {
            new AddTrainOnStationFrame(model, stationName);
        });

        JButton removeTrainButton = new JButton("Delete");
        removeTrainButton.setBackground(Color.ORANGE);
        removeTrainButton.addActionListener(e -> {
            int row = table.getSelectedRow();
            if(row<0){
                JOptionPane.showMessageDialog(null,"You need to select train first");
                return;
            }
            String name = (String) table.getValueAt(row, 0);
            Time time = new Time(table.getValueAt(row, 3).toString());
            ts.removeTrain(name, time);
            model.removeRow(row);
        });

        JButton editButton = new JButton("Edit");
        editButton.setBackground(Color.ORANGE);
        editButton.addActionListener(e -> {
            new EditTrainFrame(table,ts);
        });

        buttonPanel.add(editButton);
        buttonPanel.add(removeTrainButton);


        buttonPanel.add(addTrainButton);
        JScrollPane sP = new JScrollPane(table);
        add(sP);
        add(buttonPanel);
        add(buttonPanel).setPreferredSize(new Dimension(700, 200));
        setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        setRows(stationName);
        setVisible(true);
    }

    void setRows(String name) {
        TrainStation s = Main.controller.findTrainStationByName(name);

        for (Train train : s.trainsOnStation.keySet()) {
            for (Time time : s.trainsOnStation.get(train)) {
                model.addRow(new Object[]{train.name, train.numberOfSeats, train.state, time});
            }
        }

    }


}
