package me.eparon.mixins;


import net.minecraft.client.player.KeyboardInput;
import net.minecraft.world.entity.player.Input;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.objectweb.asm.Opcodes;
import me.eparon.StoicClient;

@Mixin(KeyboardInput.class)
public class KeyboardInputMixer {
    KeyboardInput input = (KeyboardInput) (Object) this;

    @Redirect(method = "tick",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/client/player/KeyboardInput;keyPresses:Lnet/minecraft/world/entity/player/Input;",
                    opcode = Opcodes.PUTFIELD))
    private void Semirdiken3000(KeyboardInput instance, Input originalInput) {
        //System.out.println(input.keyPresses);
        instance.keyPresses = new Input(
                (originalInput.forward() && !StoicClient.forceInput) || StoicClient.shouldForward,
                (originalInput.backward() && !StoicClient.forceInput) || StoicClient.shouldBackward,
                (originalInput.left() && !StoicClient.forceInput) || StoicClient.shouldLeft,
                (originalInput.right() && !StoicClient.forceInput) || StoicClient.shouldRight,
                (originalInput.jump() && !StoicClient.forceInput) || StoicClient.shouldJump,
                (originalInput.shift() && !StoicClient.forceInput) || StoicClient.shouldCrouch,
                (originalInput.sprint() && !StoicClient.forceInput) || StoicClient.shouldRunning
        );
    }
}