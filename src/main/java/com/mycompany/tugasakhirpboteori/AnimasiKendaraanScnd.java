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
import java.awt.geom.AffineTransform;

/*
Dibuat Oleh Kelompok 6
- Agus Syuhada (2207125092)
- Raja Hafiza Ramanda Puta (2207135965)
- Aini Nurul Amri (2207135967)
- Muhammad Muttakin (2207125094)
- Rayhan Al Farassy (2207135776)
 */
public class AnimasiKendaraanScnd extends JFrame {

    private int backgroundX = 0;
    private final int backgroundY = 0;
    private int carX = 0;
    private int carY = 0;

    private int backgroundSpeed = 0;
    private final int maxBackgroundSpeed = 30;
    private final int acceleration = 1;
    private final int deceleration = 1;
    
    private boolean useAlternativeCar = false;

    private final Timer timer;

    public AnimasiKendaraanScnd() {
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
        int carWidth = 680;
        int carHeight = 610;

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
            case KeyEvent.VK_E:
                useAlternativeCar = !useAlternativeCar;
                break;
        }
        repaint();
    }

    private class BackgroundPanel extends JPanel {

        private final int carWidth = 480;
        private final int carHeight = 650;

        private final int frontWheelOffsetX = carWidth / 4;
        private final int frontWheelOffsetY = carHeight / 3;

        private final int rearWheelOffsetX = -carWidth / 4;
        private final int rearWheelOffsetY = carHeight / 3;

        private int frontWheelX;
        private int frontWheelY;

        private int rearWheelX;
        private int rearWheelY;

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
            ImageIcon carIcon;
            if (useAlternativeCar) {
                carIcon = new ImageIcon("C:\\Users\\ASUS\\Documents\\NetBeansProjects\\TugasAkhirPBOTeori\\src\\main\\java\\image\\cr2.png");
            } else {
                carIcon = new ImageIcon("C:\\Users\\ASUS\\Documents\\NetBeansProjects\\TugasAkhirPBOTeori\\src\\main\\java\\image\\cr.png");
            }

            int carCenterX = carX + (carWidth / 2) + 63;
            int carCenterY = carY + (carHeight / 1) + 30;

            frontWheelX = carCenterX + 100 + frontWheelOffsetX;
            frontWheelY = carCenterY - frontWheelOffsetY;

            rearWheelX = carCenterX + rearWheelOffsetX;
            rearWheelY = carCenterY - rearWheelOffsetY;

            g.drawImage(carIcon.getImage(), carX, carY, this);

            drawRotatedWheel(g, frontWheelX, frontWheelY, getWheelRotationAngle());

            drawRotatedWheel(g, rearWheelX, rearWheelY, getWheelRotationAngle());
        }

        private void drawRotatedWheel(Graphics g, int x, int y, double angle) {
            ImageIcon wheelIcon = new ImageIcon("C:\\Users\\ASUS\\Documents\\NetBeansProjects\\TugasAkhirPBOTeori\\src\\main\\java\\image\\whl.png");

            AffineTransform rotation = new AffineTransform();
            rotation.translate(x - wheelIcon.getIconWidth() / 2, y - wheelIcon.getIconHeight() / 2);
            rotation.rotate(angle, wheelIcon.getIconWidth() / 2, wheelIcon.getIconHeight() / 2);

            Graphics2D g2d = (Graphics2D) g;
            g2d.drawImage(wheelIcon.getImage(), rotation, this);
        }

        private double getWheelRotationAngle() {
            return Math.toRadians((backgroundX % 360) * 2);
        }

        private Image getImage() {
            ImageIcon icon = new ImageIcon("C:\\Users\\ASUS\\Documents\\NetBeansProjects\\TugasAkhirPBOTeori\\src\\main\\java\\image\\bg.png");
            return icon.getImage();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                AnimasiKendaraanScnd app = new AnimasiKendaraanScnd();
                app.setVisible(true);
            }
        });
    }
}
