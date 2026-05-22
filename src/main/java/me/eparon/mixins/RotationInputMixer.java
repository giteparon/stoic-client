package me.eparon.mixins;


import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.player.Input;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.objectweb.asm.Opcodes;
import me.eparon.StoicClient;

@Mixin(LocalPlayer.class)
public class RotationInputMixer {
    LocalPlayer input = (LocalPlayer) (Object) this;

    @Redirect(method = "tick",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/client/player/LocalPlayer",
                    opcode = Opcodes.PUTFIELD))
    private void plswork(LocalPlayer instance) {
        //System.out.println(input.keyPresses);
        instance.yRotLast = 100;
    }
}