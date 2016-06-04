package com.bigxplosion.chunkhighlighter.render;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


@SideOnly(Side.CLIENT)
public class RenderChunkOverlay {

	private static boolean overlay;

	public static void buttonPressed() {
		overlay = ! overlay;
	}

	public static void render(float partialTicks) {
		if (!overlay) {
			return;
		}

		GlStateManager.pushMatrix();
		Entity entity = Minecraft.getMinecraft().getRenderViewEntity();
		GlStateManager.translate(-(entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * partialTicks), -(entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * partialTicks), -(entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * partialTicks));
		GlStateManager.disableTexture2D();
		GlStateManager.enableBlend();
		GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GlStateManager.disableLighting();
		GlStateManager.glLineWidth(1.5F);
		GlStateManager.glBegin(GL11.GL_LINES);

		for (int chunkX = -4; chunkX < 5; chunkX++) {
			for (int chunkZ = -4; chunkZ < 5; chunkZ++) {
				double xBegin = (entity.chunkCoordX + chunkX) * 16;
				double xEnd = xBegin + 16;
				double zBegin = (entity.chunkCoordZ + chunkZ) * 16;
				double zEnd = zBegin + 16;
				double yBegin = MathHelper.floor_double(entity.posY - 64);
				double yEnd = yBegin + 128;

				if (yBegin < 0) {
					yBegin = 0;
					yEnd = 128;
				}
				if (yEnd > entity.worldObj.getHeight()) {
					yEnd = entity.worldObj.getHeight();
					yBegin = yEnd - 128;
				}

				GlStateManager.color(0.9F, 0.0F, 0.0F, (float) Math.pow(1.5F, -(chunkX * chunkX + chunkZ * chunkZ)));

				if (chunkX >= 0 && chunkZ >= 0) {
					GL11.glVertex3d(xEnd, yBegin, zEnd);
					GL11.glVertex3d(xEnd, yEnd, zEnd);
				}
				if (chunkX >= 0 && chunkZ <= 0) {
					GL11.glVertex3d(xEnd, yBegin, zBegin);
					GL11.glVertex3d(xEnd, yEnd, zBegin);
				}
				if (chunkX <= 0 && chunkZ >= 0) {
					GL11.glVertex3d(xBegin, yBegin, zEnd);
					GL11.glVertex3d(xBegin, yEnd, zEnd);
				}
				if (chunkX <=0 && chunkZ <= 0) {
					GL11.glVertex3d(xBegin, yBegin, zBegin);
					GL11.glVertex3d(xBegin, yEnd, zBegin);
				}

				if (chunkX == 0 && chunkZ == 0) {
					GlStateManager.color(0.9F, 0.9F, 0.0F);

					GL11.glVertex3d(xBegin + 8, yBegin, zBegin + 8);
					GL11.glVertex3d(xBegin + 8, yEnd, zBegin + 8);

					yBegin = MathHelper.floor_double(entity.posY - 16);
					yEnd = yBegin + 32;

					if (yBegin < 0) {
						yBegin = 0;
						yEnd = 32;
					}
					if (yEnd > entity.worldObj.getHeight()) {
						yEnd = entity.worldObj.getHeight();
						yBegin = yEnd - 32;
					}

					GlStateManager.color(0.0F, 0.9F, 0.0F, 0.4F);

					for (double height = (int) yBegin; height < yEnd; height++) {
						GL11.glVertex3d(xEnd, height, zBegin);
						GL11.glVertex3d(xEnd, height, zEnd);
						GL11.glVertex3d(xBegin, height, zBegin);
						GL11.glVertex3d(xBegin, height, zEnd);
						GL11.glVertex3d(xBegin, height, zEnd);
						GL11.glVertex3d(xEnd, height, zEnd);
						GL11.glVertex3d(xBegin, height, zBegin);
						GL11.glVertex3d(xEnd, height, zBegin);
					}

					for (double pos = 1; pos < 16; pos++) {
						GL11.glVertex3d(xBegin + pos, yBegin, zBegin);
						GL11.glVertex3d(xBegin + pos, yEnd, zBegin);
						GL11.glVertex3d(xBegin + pos, yBegin, zEnd);
						GL11.glVertex3d(xBegin + pos, yEnd, zEnd);
						GL11.glVertex3d(xBegin, yBegin, zBegin + pos);
						GL11.glVertex3d(xBegin, yEnd, zBegin + pos);
						GL11.glVertex3d(xEnd, yBegin, zBegin + pos);
						GL11.glVertex3d(xEnd, yEnd, zBegin + pos);
					}
				}
			}
		}

		GlStateManager.glEnd();
		GlStateManager.enableLighting();
		GlStateManager.disableBlend();
		GlStateManager.enableTexture2D();
		GlStateManager.popMatrix();
	}

}
