package com.bigxplosion.chunkhighlighter.render;

import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class RenderEvent {

	@SubscribeEvent
	public void renderWorldLast(RenderWorldLastEvent event) {
		RenderChunkOverlay.render(event.getPartialTicks());
	}
}
