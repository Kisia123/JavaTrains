package com.pro.frames;

import com.pro.Main;
import com.pro.Train;
import com.pro.frames.train.AddTrainFrame;
import com.pro.frames.train.ChangeTrainValueFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;

public class TrainFrame extends JFrame {
    DefaultTableModel model;
    int height = 200;
    int width = 700;

    public TrainFrame() {
        super("Trains");
        setSize(new Dimension(700, 500));
        setLocation(10, 10);
        String[] names = new String[]{"NAME", "SEATS", "STATE"};
        model = new DefaultTableModel(0, names.length){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTable table = new JTable(model);
        for(int i =0;i< names.length;i++) {
            TableColumn tc = table.getColumnModel().getColumn(i);
            tc.setHeaderValue(names[i]);
        }
        setRows();
        // table.setBounds(1,1,200,0);
        JScrollPane sP = new JScrollPane(table);
        add(sP);
        JButton addTrainButton = new JButton("Add Train");

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
       // Dimension dimension = new Dimension(width, height);

        addTrainButton.setBackground(Color.ORANGE);
        addTrainButton.addActionListener(e -> new AddTrainFrame(model));
        buttonPanel.add(addTrainButton);

        JButton removeTrainButton = new JButton("Delete");
        removeTrainButton.setBackground(Color.ORANGE);
        removeTrainButton.addActionListener(e->{
            int row = table.getSelectedRow();
            if(row<0){
                JOptionPane.showMessageDialog(null,"You need to select train first");
                return;
            }
            Main.controller.trainList.remove(row);
            model.removeRow(row);
        });

        JButton editButton = new JButton("Edit");
        editButton.setBackground(Color.ORANGE);
        editButton.addActionListener(e-> new ChangeTrainValueFrame(table));

        buttonPanel.add(editButton);
        buttonPanel.add(removeTrainButton);
        add(buttonPanel).setPreferredSize(new Dimension(width,height));


        setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        setVisible(true);
    }

    void setRows() {
        for (Train t : Main.controller.trainList) {
            model.addRow(t.printToTable());
        }
    }
}
