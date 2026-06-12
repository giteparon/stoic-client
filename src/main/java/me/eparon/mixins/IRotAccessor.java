package me.eparon.mixins;


import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.player.Input;
import net.minecraft.client.Camera;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.objectweb.asm.Opcodes;
import me.eparon.StoicClient;
import net.minecraft.world.entity.Entity;

@Mixin(Camera.class)
public interface IRotAccessor {
    @Invoker("setRotation")
    void rotateTo(float pitch, float yaw);
}