package me.eparon.mods;
import me.eparon.screens.CustomWidget;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.TrapDoorBlock;

public class AutoStasis {
    public static CustomWidget autoStasis = new CustomWidget(40, 80, 80, 15, "Auto Stasis", true, "On command, this account will go to the chosen stasis chamber and activate it");
    public static BlockPos stasisBlockPos;
    public static boolean waitingForHitTrapdoor;
    public static void gotoStasis(){

    }
    public static boolean getState(){
            return autoStasis.getState();
        }


}