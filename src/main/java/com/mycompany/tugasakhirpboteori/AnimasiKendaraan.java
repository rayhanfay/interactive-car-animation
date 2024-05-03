/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.mycompany.tugasakhirpboteori;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/*
Dibuat Oleh Kelompok 6
- Agus Syuhada (2207125092)
- Raja Hafiza Ramanda Puta (2207135965)
- Aini Nurul Amri (2207135967)
- Muhammad Muttakin (2207125094)
- Rayhan Al Farassy (2207135776)
*/

public class AnimasiKendaraan extends JFrame {

    private int backgroundX = 0;
    private final int backgroundY = 0;
    private int carX = 0;
    private int carY = 0;

    private int backgroundSpeed = 0;
    private final int maxBackgroundSpeed = 30;
    private final int acceleration = 1;
    private final int deceleration = 1;

    private final Timer timer;

    public AnimasiKendaraan() {
        setTitle("Animasi Kendaraan - Oleh Kelompok 6");
        setSize(1280, 758);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        BackgroundPanel backgroundPanel = new BackgroundPanel();
        setContentPane(backgroundPanel);

        timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveBackground();
                backgroundPanel.repaint();
            }
        });

        timer.start();

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                // Implementasikan jika diperlukan
            }

            @Override
            public void keyPressed(KeyEvent e) {
                handleKeyPress(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                // Implementasikan jika diperlukan
            }
        });
        setFocusable(true);
        
        setLocationRelativeTo(null);
    }

    private void moveBackground() {
        if (carX > getWidth() / 8) {
            backgroundSpeed += acceleration;
            if (backgroundSpeed > maxBackgroundSpeed) {
                backgroundSpeed = maxBackgroundSpeed;
            }
        } else {
            backgroundSpeed -= deceleration;
            if (backgroundSpeed < 0) {
                backgroundSpeed = 0;
            }
        }
        backgroundX -= backgroundSpeed;

        if (backgroundX <= -1280) {
            backgroundX = 0;
        }
    }

    private void handleKeyPress(KeyEvent e) {
        int key = e.getKeyCode();
        int carWidth = 480;
        int carHeight = 650;

        switch (key) {
            case KeyEvent.VK_LEFT:
                if (carX > 0) {
                    carX -= 10;
                }
                break;
            case KeyEvent.VK_RIGHT:
                if (carX < getWidth() - carWidth) {
                    carX += 10;
                }
                break;
            case KeyEvent.VK_UP:
                if (carY > 0) {
                    carY -= 10;
                }
                break;
            case KeyEvent.VK_DOWN:
                if (carY < getHeight() - carHeight - 10) {
                    carY += 10;
                }
                break;
        }
        repaint();
    }

    private class BackgroundPanel extends JPanel {

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            drawBackground(g);
            drawCar(g);
        }

        private void drawBackground(Graphics g) {
            g.drawImage(getImage(), backgroundX, backgroundY, this);
            g.drawImage(getImage(), backgroundX + 1280, backgroundY, this);
        }

        private void drawCar(Graphics g) {
            ImageIcon carIcon = new ImageIcon("C:\\Users\\ASUS\\Documents\\NetBeansProjects\\TugasAkhirPBOTeori\\src\\main\\java\\image\\car.png");
            g.drawImage(carIcon.getImage(), carX, carY, this);
        }

        private Image getImage() {
            ImageIcon icon = new ImageIcon("C:\\Users\\ASUS\\Documents\\NetBeansProjects\\TugasAkhirPBOTeori\\src\\main\\java\\image\\background.png");
            return icon.getImage();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                AnimasiKendaraan app = new AnimasiKendaraan();
                app.setVisible(true);
            }
        });
    }
}
