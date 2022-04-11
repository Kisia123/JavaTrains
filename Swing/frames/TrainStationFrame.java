package com.pro.frames;

import com.pro.Main;
import com.pro.Train;
import com.pro.TrainStation;
import com.pro.frames.station.AddTrainStationFrame;
import com.pro.frames.station.ChangeStationValueFrame;
import com.pro.frames.station.ShowTrainsOnStation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class TrainStationFrame extends JFrame {
    DefaultTableModel model;
    int height = 200;
    int width = 700;
    public TrainStationFrame(){
        super("Train Stations");
        setSize(new Dimension(700, 500));
        setLocation(10, 10);
        String[] headers = new String[]{"NAME", "MAX CAPACITY","CURRENT LOAD"};
        model = new DefaultTableModel(0, headers.length){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTable table = new JTable(model);
        for(int i =0;i< headers.length;i++) {
            TableColumn tc = table.getColumnModel().getColumn(i);
            tc.setHeaderValue(headers[i]);
        }
        setRows();
        JScrollPane sP = new JScrollPane(table);
        add(sP);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

        JButton addTrainStationButton = new JButton("Add Train Station");
        addTrainStationButton.setBackground(Color.ORANGE);
        addTrainStationButton.addActionListener(e -> new AddTrainStationFrame(model));

        JButton showTrainsButton = new JButton("Show Trains");
        showTrainsButton.setBackground(Color.ORANGE);
        showTrainsButton.addActionListener(e -> {
            int row = table.getSelectedRow();
            if(row<0){
                JOptionPane.showMessageDialog(null,"You need to select train station first");
                return;
            }
            String name = (String)table.getValueAt(row,0);
            TrainStation ts = Main.controller.findTrainStationByName(name);
            JFrame frame =new ShowTrainsOnStation(name,model,row);
            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {

                    int i = ts.getLoad();
                    model.setValueAt(i, row, 2);
                    e.getWindow().dispose();

                }
            });
        });

        JButton removeTrainStationButton = new JButton("Delete");
        removeTrainStationButton.setBackground(Color.ORANGE);
        removeTrainStationButton.addActionListener(e->{
            int row = table.getSelectedRow();
            if(row<0){
                JOptionPane.showMessageDialog(null,"You need to select train station first");
                return;
            }
            Main.controller.trainStationsList.remove(row);
            model.removeRow(row);
        });

        JButton editButton = new JButton("Edit");
        editButton.setBackground(Color.ORANGE);
        editButton.addActionListener(e-> {

            new ChangeStationValueFrame(table);
        });

        JButton sortByMaxCapacity = new JButton("Sort By Max Capacity");
        sortByMaxCapacity.setBackground(Color.ORANGE);
        sortByMaxCapacity.addActionListener(e->{
            List<TrainStation> unsorted = new ArrayList<>(Main.controller.trainStationsList);
            unsorted.sort((o1,o2)-> o1.compareTo(o2));
            model.setRowCount(0);
            for(TrainStation ts : unsorted) {
                model.addRow(ts.printToTable());
            }
        });
        JButton sortByLoad = new JButton("Sort By Load");
        sortByLoad.setBackground(Color.ORANGE);
        sortByLoad.addActionListener(e->{
            List<TrainStation> unsorted = new ArrayList<>(Main.controller.trainStationsList);
            unsorted.sort((o1,o2)->o1.compareByLoad(o2));
            model.setRowCount(0);
            for(TrainStation ts : unsorted) {
                model.addRow(ts.printToTable());
            }
        });

        buttonPanel.add(sortByLoad);
        buttonPanel.add(sortByMaxCapacity);
        buttonPanel.add(editButton);
        buttonPanel.add(removeTrainStationButton);
        buttonPanel.add(addTrainStationButton);
        buttonPanel.add(showTrainsButton);

        add(buttonPanel).setPreferredSize(new Dimension(width,height));
        setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        setVisible(true);

    }
    void setRows() {
        for (TrainStation station : Main.controller.trainStationsList) {
            model.addRow(station.printToTable());

        }
    }
}
