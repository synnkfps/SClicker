package me.synnk.listeners;

import com.github.kwhat.jnativehook.mouse.NativeMouseEvent;
import com.github.kwhat.jnativehook.mouse.NativeMouseListener;
import me.synnk.Main;

public class MouseListener implements NativeMouseListener {
    @Override
    public void nativeMousePressed(NativeMouseEvent event) {
        if (Main.toggled.isSelected()) {
            Main.clicking = true;
            Thread thread = new Thread(() -> {
                try {
                    while (Main.clicking) {
                        Main.sendClick();
                        Thread.sleep(1);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            thread.start();
        }
    }

    @Override
    public void nativeMouseReleased(NativeMouseEvent event) {
        if (Main.toggled.isSelected()) {
            if (Main.clicking) {
                Main.clicking = false;
                System.out.println("Stopped clicking...");
            }
        }
    }

    @Override
    public void nativeMouseClicked(NativeMouseEvent event) {
        // NO-OP
    }
}
