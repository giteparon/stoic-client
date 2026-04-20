package me.eparon;

import me.eparon.commands.openGui;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StoicClient implements ModInitializer {
	public static final String MOD_ID = "stoic-client";
	public static final String MOD_NAME = "[Stoic] - ";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info(MOD_NAME + "Initializing Stoic");
		CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
			dispatcher.register(Commands.literal("stoic").executes(context -> {
				context.getSource().sendSuccess(() -> Component.literal("Called /stoic"), false);
				context.getSource().sendSuccess(() -> openGui.open(), false);
				return 1;
			}));
		});

	}


}