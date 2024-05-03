/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.mycompany.tugasakhirpboteori;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;

public class AnimasiKendaraanThrd extends JFrame {
    private ImageIcon backgroundIcon1, backgroundIcon2, carIcon, frontWheelIcon, rearWheelIcon;
    private JLabel backgroundLabel1, backgroundLabel2, carLabel, frontWheelLabel, rearWheelLabel;

    private int carX = 50;
    private int rotationAngle = 0;
    private int backgroundX1 = 0;
    private int backgroundX2 = 800;

    public AnimasiKendaraanThrd() {
        setTitle("Sliding Background");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        backgroundIcon1 = new ImageIcon("C:\\\\Users\\\\ASUS\\\\Documents\\\\NetBeansProjects\\\\TugasAkhirPBOTeori\\\\src\\\\main\\\\java\\\\image\\\\bg.png");
        backgroundIcon2 = new ImageIcon("C:\\\\Users\\\\ASUS\\\\Documents\\\\NetBeansProjects\\\\TugasAkhirPBOTeori\\\\src\\\\main\\\\java\\\\image\\\\bg.png");
        carIcon = new ImageIcon("C:\\\\Users\\\\ASUS\\\\Documents\\\\NetBeansProjects\\\\TugasAkhirPBOTeori\\\\src\\\\main\\\\java\\\\image\\\\car.png");
        frontWheelIcon = new ImageIcon("front_wheel.png");
        rearWheelIcon = new ImageIcon("rear_wheel.png");

        backgroundLabel1 = new JLabel(backgroundIcon1);
        backgroundLabel2 = new JLabel(backgroundIcon2);
        carLabel = new JLabel(carIcon);
        frontWheelLabel = new JLabel(frontWheelIcon);
        rearWheelLabel = new JLabel(rearWheelIcon);

        backgroundLabel1.setBounds(backgroundX1, 0, 800, 600);
        backgroundLabel2.setBounds(backgroundX2, 0, 800, 600);
        carLabel.setBounds(carX, 400, carIcon.getIconWidth(), carIcon.getIconHeight());
        frontWheelLabel.setBounds(carX + 25, 480, frontWheelIcon.getIconWidth(), frontWheelIcon.getIconHeight());
        rearWheelLabel.setBounds(carX + 135, 480, rearWheelIcon.getIconWidth(), rearWheelIcon.getIconHeight());

        add(backgroundLabel1);
        add(backgroundLabel2);
        add(carLabel);
        add(frontWheelLabel);
        add(rearWheelLabel);

        Timer timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                backgroundX1 -= 2;
                backgroundX2 -= 2;
                
                if (backgroundX1 + 800 <= 0) {
                    backgroundX1 = 800;
                }
                if (backgroundX2 + 800 <= 0) {
                    backgroundX2 = 800;
                }

                carX += 2;
                if (carX > 800) {
                    carX = -carIcon.getIconWidth();
                }

                backgroundLabel1.setBounds(backgroundX1, 0, 800, 600);
                backgroundLabel2.setBounds(backgroundX2, 0, 800, 600);
                carLabel.setBounds(carX, 400, carIcon.getIconWidth(), carIcon.getIconHeight());

                rotationAngle += 4;
                if (rotationAngle >= 360) {
                    rotationAngle = 0;
                }

                AffineTransform transform = new AffineTransform();
                transform.rotate(Math.toRadians(rotationAngle), frontWheelIcon.getIconWidth() / 2, frontWheelIcon.getIconHeight() / 2);
                frontWheelLabel.setIcon(new ImageIcon(frontWheelIcon.getImage().getScaledInstance(frontWheelIcon.getIconWidth(), frontWheelIcon.getIconHeight(), Image.SCALE_DEFAULT)));
                frontWheelLabel.setBounds(carX + 25, 480, frontWheelIcon.getIconWidth(), frontWheelIcon.getIconHeight());
                frontWheelLabel.setIcon(frontWheelIcon);
                frontWheelLabel.repaint();

                transform = new AffineTransform();
                transform.rotate(Math.toRadians(rotationAngle), rearWheelIcon.getIconWidth() / 2, rearWheelIcon.getIconHeight() / 2);
                rearWheelLabel.setIcon(new ImageIcon(rearWheelIcon.getImage().getScaledInstance(rearWheelIcon.getIconWidth(), rearWheelIcon.getIconHeight(), Image.SCALE_DEFAULT)));
                rearWheelLabel.setBounds(carX + 135, 480, rearWheelIcon.getIconWidth(), rearWheelIcon.getIconHeight());
                rearWheelLabel.setIcon(rearWheelIcon);
                rearWheelLabel.repaint();
            }
        });
        timer.start();

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AnimasiKendaraanThrd();
            }
        });
    }
}
