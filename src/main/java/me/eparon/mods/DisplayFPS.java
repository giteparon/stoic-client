package me.eparon.mods;
import me.eparon.screens.CustomWidget;
import net.minecraft.client.Minecraft;
public class DisplayFPS{
    public static CustomWidget displayFPS = new CustomWidget(40, 80, 80, 15, "Display FPS", true, "Display FPS in your screen");
    public static String getFPS(){
        int s = Minecraft.getInstance().getFps();
        String fps = Integer.toString(s);
        return fps;
    }
    public static boolean getState(){
        return displayFPS.getState();
    }

}