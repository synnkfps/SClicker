package me.synnk;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import me.synnk.listeners.KeyListener;
import me.synnk.listeners.MouseListener;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.util.Random;

import static com.github.kwhat.jnativehook.mouse.NativeMouseEvent.BUTTON1;

public class Main extends JFrame {

    public static Robot robot;
    public static Boolean focused = true;

    public static Boolean isWaiting = false; // keybind

    public static JCheckBox toggled = new JCheckBox("Enabled");
    JLabel keybindLabel = new JLabel("Key: ");
    public static JTextField keybind = new JTextField("R"); // button to enable the clicker

    JLabel labelMinCPS = new JLabel("Min CPS: ");
    JLabel labelMaxCPS = new JLabel("Max CPS: ");
    public static JTextField minCPS = new JTextField("8");
    public static JTextField maxCPS = new JTextField("12");

    public Main() {
        setupFrame();
        addInterface();
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

    public static void activate() {
        Main.toggled.setSelected(!Main.toggled.isSelected());
    }

    public void addInterface() {
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
        add(keybindLabel, c);

        c.gridx=1;
        c.gridy=3;
        add(keybind, c);
    }
    public void setupFrame() {
        this.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                isWaiting = false;
            }
            @Override
            public void focusLost(FocusEvent e) {
                focused = false;
            }
        });
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

        toggled.setFocusPainted(false);

        setSize(200, 270);
        setLocationRelativeTo(null);
        setType(Type.UTILITY);
        setTitle("SClicker b0.1");
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }
}