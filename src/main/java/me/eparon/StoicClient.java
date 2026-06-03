package me.eparon;
import me.eparon.mixins.*;
import me.eparon.mods.*;
import net.minecraft.world.entity.Entity;
import net.minecraft.resources.Identifier;
import me.eparon.commands.openGui;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import org.slf4j.Logger;
import net.minecraft.client.Minecraft;
import net.minecraft.world.level.Level;
import net.minecraft.client.Camera;
import org.slf4j.LoggerFactory;
import me.eparon.screens.mainGui;
import net.minecraft.util.thread.TaskScheduler;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.TrapDoorBlock;
import me.eparon.movement.*;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import java.util.ArrayList;
import net.minecraft.core.BlockPos;
import me.eparon.environment.blockUtils;


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
	public static float yaw = 0f;
	public static float pitch = 0f;
	public static boolean lockHead = false;
	public static boolean forcestop = false;
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

				((Minecraft)(Object)mc).startAttack();

				lockHead = true;

				forceInput = true;
				goForward = true;
				goRight = true;

				return 1;

			}));
		});
		CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
			dispatcher.register(Commands.literal("forcestop").executes(context -> {
				context.getSource().sendSuccess(() -> Component.literal("forcestop"), false);
				//openGui.open();
				ArrayList<Float> waypoints = new ArrayList<Float>();
				waypoints.add(0f);
				waypoints.add(100f);
				waypoints.add(0f);
				forcestop = true;
				pathFinding.waypointsArray.add(waypoints);

				lockHead = false;
				forceInput = false;
				goForward = false;
				goRight = false;
				goLeft = false;
				jump = false;
				run = false;
				return 1;

			}));
		});
		CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
			dispatcher.register(Commands.literal("stoic").executes(context -> {
				context.getSource().sendSuccess(() -> Component.literal("opening stoic gui"), false);
				openGui.open();
				return 1;

			}));
		});
		CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
			dispatcher.register(Commands.literal("srotatepreview").executes(context -> {
				context.getSource().sendSuccess(() -> Component.literal("rotating"), false);
				Thread lookThread = new Thread(() -> {
					forcestop = false;
					double[][] a = {{8.5, -58.5, 4.5},{10.5, -59.5, 8.5},{7.5, -58.5, 10.5},{5.5, -57.5, 7.5}};
					int counter = 0;
					int ncounter = 0;
					while(true && !forcestop) {
						if(!RotUtils.macroRotating){
							((Minecraft)(Object)mc).startAttack();

							RotUtils.smoothLookAt(a[counter][0], a[counter][1], a[counter][2]);
							ncounter = (int)(Math.random() * 4);
							while(ncounter == counter){
								ncounter = (int)(Math.random() * 4);
							}
							counter = ncounter;

						}

						sleepMs(1);

					}

				});

				lookThread.setName("(Rotating)");
				lookThread.start();

				return 1;

			}));
		});
		CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
			dispatcher.register(Commands.literal("findBlock")
					.then(Commands.argument("value", StringArgumentType.string())
							.executes(StoicClient::findBlock)));
		});

//		AttackBlockCallback.EVENT.register((player, level, hand, pos, direction) -> {
//			BlockState state = level.getBlockState(pos);
//			if (!player.isSpectator() && AutoStasis.waitingForHitTrapdoor && level.getBlock() instanceof TrapDoorBlock) {
//				AutoStasis.stasisBlockPos = pos;
//			}
//
//			return InteractionResult.PASS;
//		});

	}
	public static void sleepMs(long ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
	private static int findBlock(CommandContext<CommandSourceStack> context) {
		String value = StringArgumentType.getString(context, "value");
		BlockPos pos = blockUtils.findBlockInRadiusByString(value, 6);
		String response = "X: " + pos.getX() + " Y: " + pos.getY() + " Z: " + pos.getZ();
		RotUtils.smoothLookAt(pos.getX() + 0.5, pos.getY()+ 0.5, pos.getZ()+ 0.5);
		context.getSource().sendSuccess(() -> Component.literal(response), false);

		return 1;
	}



}