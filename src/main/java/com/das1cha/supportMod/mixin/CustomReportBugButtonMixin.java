package com.das1cha.supportMod.mixin;

import com.das1cha.supportMod.SuppMod;
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
public class CustomReportBugButtonMixin extends Screen {

	protected CustomReportBugButtonMixin(Text title) {
		super(title);
	}	// Без наличия конструктора, не будет работать.

	@Inject(at = @At("HEAD"), method = "initWidgets()V")
	private void initWidgets(CallbackInfo c) {
		addDrawableChild(new ButtonWidget(width / 2 + 4, height / 4 + 72 + -16 , 98, 20, new TranslatableText(SuppMod.translateId), (button) -> {
			client.setScreen(new ConfirmChatLinkScreen((confirmed) -> {
				if (confirmed) {
					Util.getOperatingSystem().open(SuppMod.urlSupp);
				}

				client.setScreen(this);
			}, SuppMod.urlSupp, true));
		}));// упаковка, всего этого чуда, в метод не желательно, так как в лучшем случае ничего не произойдёт и кнопка не появится. В худшем случае приведёт к вылету
	}



	}
