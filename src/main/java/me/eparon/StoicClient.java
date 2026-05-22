package me.eparon;
import me.eparon.mixins.KeyboardInputMixer;
import net.minecraft.resources.Identifier;
import me.eparon.commands.openGui;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import org.slf4j.Logger;
import net.minecraft.client.Minecraft;
import org.slf4j.LoggerFactory;
import me.eparon.screens.mainGui;
import net.minecraft.util.thread.TaskScheduler;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import me.eparon.movement.PlayerMovementInput;
public class StoicClient implements ModInitializer {
	public static final String MOD_ID = "stoic-client";
	public static final String MOD_NAME = "[Stoic] - ";
	public static boolean shouldForward = false;
	public static boolean shouldBackward = false;
	public static boolean shouldLeft = false;
	public static boolean shouldRight = false;
	public static boolean shouldJump = false;
	public static boolean shouldCrouch = false;
	public static boolean shouldRunning = false;
	//-----------------------------what to actually change-----------------------------
	public static boolean goForward = false;
	public static boolean goBackward = false;
	public static boolean goLeft = false;
	public static boolean goRight = false;
	public static boolean jump = false;
	public static boolean crouch = false;
	public static boolean run= false;
	public static boolean forceInput = false;
//----------------------------------------------------------------------------
	public void makeForward(boolean bool){this.shouldForward = bool;}
	public void makeBackward(boolean bool){this.shouldBackward = bool;}
	public void makeLeft(boolean bool){this.shouldLeft = bool;}
	public void makeRight(boolean bool){this.shouldRight = bool;}
	public void makeJump(boolean bool){this.shouldJump = bool;}
	public void makeShift(boolean bool){this.shouldCrouch = bool;}
	public void makeSprint(boolean bool){this.shouldRunning = bool;}

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		Minecraft mc = Minecraft.getInstance();
		LOGGER.info(MOD_NAME + "Initializing Stoic");
		//------------------------- macrothread ----------------
		Thread botThread = new Thread(() -> {
			while (true) {
				makeForward(goForward);
				makeBackward(goBackward);
				makeLeft(goLeft);
				makeRight(goRight);
				makeSprint(run);
				makeShift(crouch);
				makeJump(jump);
				sleepMs(100);
				makeJump(false);

			}
		});

		botThread.setName("(Bot)");
		botThread.start();
		CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
			dispatcher.register(Commands.literal("stoic").executes(context -> {
				context.getSource().sendSuccess(() -> Component.literal("Called /stoic"), false);
				openGui.open();

				return 1;

			}));
		});
		CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
			dispatcher.register(Commands.literal("macro").executes(context -> {
				context.getSource().sendSuccess(() -> Component.literal("macroing"), false);
				//openGui.open();
				forceInput = true;
				goForward = true;
				jump = true;
				run = true;
				return 1;

			}));
		});
		CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
			dispatcher.register(Commands.literal("forcestop").executes(context -> {
				context.getSource().sendSuccess(() -> Component.literal("forcestop"), false);
				//openGui.open();
				forceInput = false;
				goForward = false;
				jump = false;
				run = false;
				return 1;

			}));
		});



	}
	public static void sleepMs(long ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}


}