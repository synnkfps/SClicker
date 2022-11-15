package me.synnk;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import me.synnk.listeners.KeyListener;
import me.synnk.listeners.MouseListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.util.Random;

import static com.github.kwhat.jnativehook.mouse.NativeMouseEvent.BUTTON1;

public class Main extends JFrame {

    static Robot robot;

    public static JCheckBox toggled = new JCheckBox("Enabled");
    public static JTextField keybind = new JTextField("R"); // button to enable the clicker

    public static Boolean clicking = false;

    public static Boolean isWaiting = false; // keybind
    JLabel labelMinCPS = new JLabel("Min CPS: ");
    JLabel labelMaxCPS = new JLabel("Max CPS: ");
    JTextField minCPS = new JTextField("8");
    JTextField maxCPS = new JTextField("12");


    public void setupFrame() {
        keybind.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                System.out.println("Focus Gained");
                isWaiting = true;
            }

            @Override
            public void focusLost(FocusEvent e) {
                System.out.println("Focus Lost");
            }
        });

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.ipadx=50;

        add(toggled, c);

        c.anchor=GridBagConstraints.WEST;
        c.gridy=1;
        add(labelMinCPS, c);

        c.gridy=1;
        c.gridx=1;
        add(minCPS, c);

        c.anchor=GridBagConstraints.WEST;
        c.gridx=0;
        c.gridy=2;
        add(labelMaxCPS, c);

        c.gridx=1;
        c.gridy=2;
        add(maxCPS, c);

        c.gridx=0;
        c.gridy=3;
        add(keybind, c);

        setSize(200, 270);
        setLocationRelativeTo(null);
        setType(Type.UTILITY);
        setTitle("SClicker b0.1");
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public Main() {
        setupFrame();
    }

    public static void main(String[] args) {
        try {
            robot = new Robot();
            GlobalScreen.registerNativeHook();
            GlobalScreen.addNativeKeyListener(new KeyListener());
            GlobalScreen.addNativeMouseListener(new MouseListener());
        } catch (NativeHookException | AWTException e) {
            e.printStackTrace();
        }
        new Main();
    }

    public static void sendClick() {
        System.out.println("Clicking");
        robot.mousePress(MouseEvent.BUTTON1_MASK);
        robot.delay(10);
        robot.mouseRelease(MouseEvent.BUTTON1_MASK);
    }
}