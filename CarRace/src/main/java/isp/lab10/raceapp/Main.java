package isp.lab10.raceapp;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Car Race");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        CarPanel carPanel = new CarPanel();

        frame.getContentPane().add(carPanel);
        frame.pack();
        frame.setSize(500,300);
        frame.setVisible(true);

        Car car1 = new Car("Red car", carPanel);
        Car car2 = new Car("Blue car", carPanel);
        Car car3 = new Car("Green car", carPanel);
        Car car4 = new Car("Yellow car", carPanel);


        JFrame frame1 = new JFrame("Semaphore");
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        isp.lab10.racedemo.SemaphorePanel semaphorePanel = new isp.lab10.racedemo.SemaphorePanel();

        frame1.getContentPane().add(semaphorePanel);
        frame1.pack();
        frame1.setVisible(true);

        PlaySound ps = new PlaySound();

        isp.lab10.racedemo.SemaphoreThread semaphoreThread = new isp.lab10.racedemo.SemaphoreThread(semaphorePanel);


        semaphoreThread.start();
        try {
            semaphoreThread.join();
            ps.playSound();
            car1.start();
            car2.start();
            car3.start();
            car4.start();


        }catch (InterruptedException e){
            e.printStackTrace();
        }
        try {
            car1.join();
            car2.join();
            car3.join();
            car4.join();
            ps.stopSound();


        }catch (InterruptedException e){
            e.printStackTrace();
        }

    }
}
