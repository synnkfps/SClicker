package me.synnk.listeners;

import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;
import me.synnk.Main;

public class KeyListener implements NativeKeyListener {
    private static String pressedKey;
    @Override
    public void nativeKeyPressed(NativeKeyEvent nativeEvent) {
        NativeKeyListener.super.nativeKeyPressed(nativeEvent);
        // test
        if (String.valueOf(nativeEvent.getKeyCode()).equals(pressedKey)) {
            Main.toggled.setSelected(!Main.toggled.isSelected());
        }

        if (Main.isWaiting) {
            pressedKey = String.valueOf(nativeEvent.getKeyCode());
        }
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent nativeEvent) {
        NativeKeyListener.super.nativeKeyReleased(nativeEvent);
        System.out.println("Released: " + nativeEvent.getKeyCode());
        if (String.valueOf(nativeEvent.getKeyCode()).equals(pressedKey) && Main.isWaiting) {
            Main.isWaiting = false;
            Main.keybind.setText(pressedKey);
            Main.toggled.requestFocus();
        }
    }
}
