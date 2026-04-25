package me.eparon.commands;

import me.eparon.StoicClient;
import me.eparon.screens.mainGui;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;

public class openGui {
    public static void open() {
        Minecraft client = Minecraft.getInstance();
        StoicClient.LOGGER.info("Opening GUI");
        client.schedule(() -> client.setScreen(new mainGui(Component.empty())));
        StoicClient.LOGGER.info("opened screen");


        //return Component.literal("Opening GUI");
    }
}
