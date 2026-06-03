package me.eparon.mixins;


import net.minecraft.client.player.LocalPlayer;

import net.minecraft.world.entity.player.Input;
import net.minecraft.client.Camera;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.Inject;
import org.objectweb.asm.Opcodes;
import me.eparon.StoicClient;
import net.minecraft.world.entity.Entity;

@Mixin(Camera.class)
public abstract class RotationInputMixer {

    @Inject(
            method = "setup",
            at = @At("RETURN")
    )
    private void onSetupReturn(
            Level level, Entity entity,
            boolean detached, boolean invertedView,
            float partialTick,
            CallbackInfo ci
    ) {

        float yaw = StoicClient.yaw;
        float pitch = StoicClient.pitch;
        if(StoicClient.lockHead) {
            //((IRotAccessor) (Object) this).rotateTo(yaw, pitch);
        }
    }
}