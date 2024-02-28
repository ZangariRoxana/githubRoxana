/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isp.lab10.racedemo;

import java.awt.*;
import javax.swing.*;

public class Semaphore {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Semaphore");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        SemaphorePanel semaphorePanel = new SemaphorePanel();
        
        frame.getContentPane().add(semaphorePanel);
        frame.pack();
        frame.setVisible(true);
        
        SemaphoreThread semaphoreThread = new SemaphoreThread(semaphorePanel);
        semaphoreThread.start();
    }
}

