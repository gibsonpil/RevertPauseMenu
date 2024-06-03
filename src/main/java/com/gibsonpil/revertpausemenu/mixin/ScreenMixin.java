package com.gibsonpil.revertpausemenu.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Screen.class)
public class ScreenMixin {
    @Shadow
    protected MinecraftClient client;

    @Inject(method="renderBackground", at = @At("HEAD"), cancellable = true)
    public void renderBackground(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        if(client.world != null) {
            ((Screen)(Object)this).renderInGameBackground(context);
            ci.cancel();
        }
    }
}
