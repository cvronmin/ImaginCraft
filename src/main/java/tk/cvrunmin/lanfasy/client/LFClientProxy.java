package tk.cvrunmin.lanfasy.client;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import tk.cvrunmin.lanfasy.LFCommonProxy;
import tk.cvrunmin.lanfasy.Lanfasy;
import tk.cvrunmin.lanfasy.client.model.ModelLFSlime;
import tk.cvrunmin.lanfasy.client.renderer.RenderLFCreeper;
import tk.cvrunmin.lanfasy.client.renderer.RenderLFEnderman;
import tk.cvrunmin.lanfasy.client.renderer.RenderLFGhast;
import tk.cvrunmin.lanfasy.client.renderer.RenderLFPigZombie;
import tk.cvrunmin.lanfasy.client.renderer.RenderLFSkeleton;
import tk.cvrunmin.lanfasy.client.renderer.RenderLFSlime;
import tk.cvrunmin.lanfasy.client.renderer.RenderLFSpider;
import tk.cvrunmin.lanfasy.client.renderer.RenderLFZombie;
import tk.cvrunmin.lanfasy.client.renderer.RenderSlimeBoss;
import tk.cvrunmin.lanfasy.entity.LFEntityCreeper;
import tk.cvrunmin.lanfasy.entity.LFEntityEnderman;
import tk.cvrunmin.lanfasy.entity.LFEntityGhast;
import tk.cvrunmin.lanfasy.entity.LFEntityPigZombie;
import tk.cvrunmin.lanfasy.entity.LFEntitySkeleton;
import tk.cvrunmin.lanfasy.entity.LFEntitySlime;
import tk.cvrunmin.lanfasy.entity.LFEntitySlimeBoss;
import tk.cvrunmin.lanfasy.entity.LFEntitySpider;
import tk.cvrunmin.lanfasy.entity.LFEntityZombie;
import tk.cvrunmin.lanfasy.init.LFBlocks;
import tk.cvrunmin.lanfasy.init.LFItems;

public class LFClientProxy extends LFCommonProxy{
	public void registerRenderers(Lanfasy main){
		RenderingRegistry.registerEntityRenderingHandler(LFEntityCreeper.class, new RenderLFCreeper(Minecraft.getMinecraft().getRenderManager()));
		RenderingRegistry.registerEntityRenderingHandler(LFEntityEnderman.class, new RenderLFEnderman(Minecraft.getMinecraft().getRenderManager()));
		RenderingRegistry.registerEntityRenderingHandler(LFEntityGhast.class, new RenderLFGhast(Minecraft.getMinecraft().getRenderManager()));
		RenderingRegistry.registerEntityRenderingHandler(LFEntitySkeleton.class, new RenderLFSkeleton(Minecraft.getMinecraft().getRenderManager()));
		RenderingRegistry.registerEntityRenderingHandler(LFEntityPigZombie.class, new RenderLFPigZombie(Minecraft.getMinecraft().getRenderManager()));
		RenderingRegistry.registerEntityRenderingHandler(LFEntityZombie.class, new RenderLFZombie(Minecraft.getMinecraft().getRenderManager()));
		RenderingRegistry.registerEntityRenderingHandler(LFEntitySlime.class, new RenderLFSlime(Minecraft.getMinecraft().getRenderManager(), new ModelLFSlime(16), 0.25F));
		RenderingRegistry.registerEntityRenderingHandler(LFEntitySpider.class, new RenderLFSpider(Minecraft.getMinecraft().getRenderManager()));
		RenderingRegistry.registerEntityRenderingHandler(LFEntitySlimeBoss.class, new RenderSlimeBoss(Minecraft.getMinecraft().getRenderManager(), new ModelLFSlime(16), 0.25F));
	}
	public void registerBlockRenderers(){
		this.blockRend(LFBlocks.dremic);
		this.blockRend(LFBlocks.dremic_ore);
		this.blockRend(LFBlocks.lfportal);
		this.blockRend(LFBlocks.lffire);
	}
	public void registerItemRenderers(){
		this.itemRend(LFItems.dremic_ingot);
		this.itemRend(LFItems.trigger);
	}
}
