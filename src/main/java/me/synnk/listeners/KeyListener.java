package me.synnk.listeners;

import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;
import me.synnk.Main;

public class KeyListener implements NativeKeyListener {
    private static String currentKeybind;
    @Override
    public void nativeKeyPressed(NativeKeyEvent e) {
        if (String.valueOf(e.getKeyCode()).equals(currentKeybind)) {
            Main.activate();
        }

        if (Main.isWaiting) {
            currentKeybind = String.valueOf(e.getKeyCode());
        }
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent e) {
        if (Main.isWaiting && String.valueOf(e.getKeyCode()).equals(currentKeybind)) {
            Main.keybind.setText(currentKeybind);
            Main.toggled.requestFocus();
            Main.isWaiting = false;
        }
    }
}

