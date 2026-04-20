package me.eparon.commands;

import me.eparon.StoicClient;
import me.eparon.screens.mainGui;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;

public class openGui {
    public static Component open() {
        StoicClient.LOGGER.info("Opening GUI");
        Minecraft.getInstance().setScreen(
                new mainGui(Component.empty()));
        StoicClient.LOGGER.info("opened screen");

        return Component.literal("Opening GUI");
    }
}
