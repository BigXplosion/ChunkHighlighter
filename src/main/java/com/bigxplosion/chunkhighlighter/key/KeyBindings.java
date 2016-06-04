package com.bigxplosion.chunkhighlighter.key;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.settings.KeyBinding;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class KeyBindings {

	public static KeyBinding chunk = new KeyBinding("key.chunk.overlay", Keyboard.KEY_F9, "key.category.chunkhighlighter");
}
