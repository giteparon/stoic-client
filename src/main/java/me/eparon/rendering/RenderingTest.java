package me.eparon.rendering;
import net.minecraft.client.Minecraft;
import org.joml.Matrix3x2fStack;
import me.eparon.screens.CustomWidget;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;
import me.eparon.mods.DisplayFPS;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElement;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import me.eparon.StoicClient;

public class RenderingTest implements ModInitializer {
	public float totalTickProgress = 0F;
    private final Minecraft minecraft = Minecraft.getInstance();
	@Override
	public void onInitialize() {
		// "A Practical Example: Rendering a Triangle Strip"
		// :::registration
		StoicClient.LOGGER.info("initialized shape");
		HudElementRegistry.addLast(Identifier.fromNamespaceAndPath(StoicClient.MOD_ID, "last_element"), hudLayer());
		// :::registration
	}

	// :::hudLayer
	private HudElement hudLayer() {
		return (graphics, deltaTracker) -> {
			// :::hudLayer
            if(DisplayFPS.getState()) {
                graphics.drawString(minecraft.font,
                        DisplayFPS.getFPS(),
                        10,
                        10,
                        0xFFFFFFFF,
                        true
                );
            }
		};
	}
	// :::hudLayer
	// :::hudLayer
}
