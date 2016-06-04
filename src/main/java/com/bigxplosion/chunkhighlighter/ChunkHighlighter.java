package com.bigxplosion.chunkhighlighter;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;

import com.bigxplosion.chunkhighlighter.key.KeyBindings;
import com.bigxplosion.chunkhighlighter.key.KeyInputEvent;
import com.bigxplosion.chunkhighlighter.render.RenderEvent;

@Mod(modid = ChunkHighlighter.MOD_ID, name = ChunkHighlighter.MOD_NAME, version = ChunkHighlighter.MOD_VERSION)
public class ChunkHighlighter {

	public static final String MOD_ID = "ChunkHighlighter";
	public static final String MOD_NAME = "Chunk Highlighter";
	public static final String MOD_VERSION = "@VERSION@";

	@Mod.Instance(MOD_ID)
	public static ChunkHighlighter INSTANCE;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		if (event.getSide() == Side.CLIENT) {
			ClientRegistry.registerKeyBinding(KeyBindings.chunk);
			MinecraftForge.EVENT_BUS.register(new KeyInputEvent());
			MinecraftForge.EVENT_BUS.register(new RenderEvent());
		}
	}
}
