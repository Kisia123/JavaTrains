package com.pro.frames.train;

import com.pro.frames.TrainFrame;
import com.pro.frames.TrainStationFrame;

import javax.swing.*;
import java.awt.*;

public class Buttons extends JPanel{
    JButton trainsButton;
    JButton trainStationsButton;
    int height = 300;
    int width = 300;

    public Buttons() {
        setBackground(Color.LIGHT_GRAY);
        trainsButton = new JButton("Trains");
        trainsButton.setBackground(Color.ORANGE);
        trainStationsButton = new JButton("TrainStations");
        trainStationsButton.setBackground(Color.ORANGE);
        trainsButton.addActionListener(e -> new TrainFrame());
        trainStationsButton.addActionListener(e->new TrainStationFrame());
        setLayout(new FlowLayout(FlowLayout.CENTER));
        Dimension dimension = new Dimension(width,height);
        add(trainsButton).setPreferredSize(dimension);
        add(trainStationsButton).setPreferredSize(dimension);
    }
}
