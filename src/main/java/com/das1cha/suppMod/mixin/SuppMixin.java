package com.das1cha.suppMod.mixin;

import net.minecraft.client.gui.screen.ConfirmChatLinkScreen;
import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Util;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameMenuScreen.class)
public class SuppMixin extends Screen {
	protected SuppMixin(Text title) {
		super(title);
	}

	@Inject(at = @At("HEAD"), method = "initWidgets()V")
	private void initWidgets(CallbackInfo c) {

		this.addDrawableChild(new ButtonWidget(this.width / 2 + 4, this.height / 4 + 72 + -16, 98, 20, new TranslatableText("menu.reportBugs"), (button) -> {
			this.client.setScreen(new ConfirmChatLinkScreen((confirmed) -> {
				if (confirmed) {
					Util.getOperatingSystem().open("https://discord.gg/k4dszY6yPh");
				}

				this.client.setScreen(this);
			}, "https://discord.gg/k4dszY6yPh", true));
		}));

	}
}
