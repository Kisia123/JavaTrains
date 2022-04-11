package com.pro.frames;

import com.pro.frames.train.Buttons;

import javax.swing.*;

public class AdminFrame extends JFrame{
    public AdminFrame(){
        super("Admin Panel");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800,500);
        setLocation(10,10);
        JPanel buttons = new Buttons();
        add(buttons);
        setVisible(true);
    }
}
