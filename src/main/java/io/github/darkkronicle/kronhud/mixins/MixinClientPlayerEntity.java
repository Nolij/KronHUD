package io.github.darkkronicle.kronhud.mixins;

import io.github.darkkronicle.kronhud.KronHUD;
import io.github.darkkronicle.kronhud.gui.hud.ToggleSprintHud;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.option.KeyBinding;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ClientPlayerEntity.class)
public abstract class MixinClientPlayerEntity {

    /**
     * @author DragonEggBedrockBreaking
     * @license MPL-2.0
     * @param sprintKey the sprint key that the user has bound
     * @return whether or not the user should try to sprint
     */
    @Redirect(
            method = "tickMovement",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/option/KeyBinding;isPressed()Z"
            )
    )
    private boolean alwaysPressed(KeyBinding sprintKey) {
        ToggleSprintHud hud = (ToggleSprintHud) KronHUD.hudManager.get(ToggleSprintHud.ID);
        return hud.sprintToggled.getBooleanValue() || sprintKey.isPressed();
    }
}
