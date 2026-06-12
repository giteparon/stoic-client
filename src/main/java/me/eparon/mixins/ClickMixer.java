package me.eparon.mixins;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.Inject;
import me.eparon.StoicClient;

@Mixin(Minecraft.class)
public class ClickMixer {

    @Inject(method = "tick", at = @At("TAIL"))
    public void tick(CallbackInfo info) {
        Minecraft mc = Minecraft.getInstance();
        if(StoicClient.holdLeftClick){
            if(StoicClient.firstClick){
                mc.startAttack();
                StoicClient.firstClick = false;
            }
            else{
                mc.continueAttack(true);
            }
        }
    }
}