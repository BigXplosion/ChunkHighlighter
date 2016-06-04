package com.bigxplosion.chunkhighlighter.key;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.Minecraft;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.bigxplosion.chunkhighlighter.render.RenderChunkOverlay;

@SideOnly(Side.CLIENT)
public class KeyInputEvent {

	@SubscribeEvent
	public void handleKeyInputEvent(InputEvent.KeyInputEvent event) {
		if (!KeyBindings.chunk.isPressed()) {
			return;
		}

		if (Minecraft.getMinecraft().currentScreen != null) {
			return;
		}

		if(!Keyboard.getEventKeyState()) {
			return;
		}

		RenderChunkOverlay.buttonPressed();
	}
}
