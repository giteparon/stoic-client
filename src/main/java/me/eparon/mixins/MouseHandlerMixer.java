package me.eparon.mixins;
import net.minecraft.client.Minecraft;
import net.minecraft.client.MouseHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.Inject;
import me.eparon.StoicClient;

@Mixin(MouseHandler.class)
public class MouseHandlerMixer {
    @Inject(method = "turnPlayer", at = @At("HEAD"), cancellable = true)
    private void onTurnPlayer(double d, CallbackInfo ci) {
        if (StoicClient.lockHead) {
            ci.cancel();
        }
    }
}