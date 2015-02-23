package tk.cvrunmin.lanfasy.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import tk.cvrunmin.lanfasy.LFCommonProxy;
import tk.cvrunmin.lanfasy.Lanfasy;
import tk.cvrunmin.lanfasy.client.model.ModelLFSlime;
import tk.cvrunmin.lanfasy.client.renderer.RenderCreeperBoss;
import tk.cvrunmin.lanfasy.client.renderer.RenderFireArrow;
import tk.cvrunmin.lanfasy.client.renderer.RenderIrrows;
import tk.cvrunmin.lanfasy.client.renderer.RenderLFCreeper;
import tk.cvrunmin.lanfasy.client.renderer.RenderLFEnderman;
import tk.cvrunmin.lanfasy.client.renderer.RenderLFGhast;
import tk.cvrunmin.lanfasy.client.renderer.RenderLFPigZombie;
import tk.cvrunmin.lanfasy.client.renderer.RenderLFSkeleton;
import tk.cvrunmin.lanfasy.client.renderer.RenderLFSlime;
import tk.cvrunmin.lanfasy.client.renderer.RenderLFSpider;
import tk.cvrunmin.lanfasy.client.renderer.RenderLFZombie;
import tk.cvrunmin.lanfasy.client.renderer.RenderLightBolt;
import tk.cvrunmin.lanfasy.client.renderer.RenderSlimeBoss;
import tk.cvrunmin.lanfasy.client.renderer.RenderZombieBoss;
import tk.cvrunmin.lanfasy.entity.LFEntityCreeper;
import tk.cvrunmin.lanfasy.entity.LFEntityCreeperBoss;
import tk.cvrunmin.lanfasy.entity.LFEntityEnderman;
import tk.cvrunmin.lanfasy.entity.LFEntityGhast;
import tk.cvrunmin.lanfasy.entity.LFEntityPigZombie;
import tk.cvrunmin.lanfasy.entity.LFEntitySkeleton;
import tk.cvrunmin.lanfasy.entity.LFEntitySlime;
import tk.cvrunmin.lanfasy.entity.LFEntitySlimeBoss;
import tk.cvrunmin.lanfasy.entity.LFEntitySpider;
import tk.cvrunmin.lanfasy.entity.LFEntityZombie;
import tk.cvrunmin.lanfasy.entity.LFEntityZombieBoss;
import tk.cvrunmin.lanfasy.entity.effect.EntityLightBolt;
import tk.cvrunmin.lanfasy.entity.projectile.EntityFireArrow;
import tk.cvrunmin.lanfasy.entity.projectile.EntityIrrow;
import tk.cvrunmin.lanfasy.entity.projectile.EntitySafeIrrow;
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
		RenderingRegistry.registerEntityRenderingHandler(EntityFireArrow.class, new RenderFireArrow(Minecraft.getMinecraft().getRenderManager()));
		RenderingRegistry.registerEntityRenderingHandler(LFEntityCreeperBoss.class, new RenderCreeperBoss(Minecraft.getMinecraft().getRenderManager()));
		RenderingRegistry.registerEntityRenderingHandler(LFEntityZombieBoss.class, new RenderZombieBoss(Minecraft.getMinecraft().getRenderManager()));
		RenderingRegistry.registerEntityRenderingHandler(EntityLightBolt.class, new RenderLightBolt(Minecraft.getMinecraft().getRenderManager()));
		RenderingRegistry.registerEntityRenderingHandler(EntitySafeIrrow.class, new RenderIrrows(Minecraft.getMinecraft().getRenderManager()));
		RenderingRegistry.registerEntityRenderingHandler(EntityIrrow.class, new RenderIrrows(Minecraft.getMinecraft().getRenderManager()));
		
	}
	public void registerBlockRenderers(){
		this.blockRend(LFBlocks.dremic);
		this.blockRend(LFBlocks.dremic_ore);
		this.blockRend(LFBlocks.lfportal);
		this.blockRend(LFBlocks.lffire);
//		this.blockRend(LFBlocks.waterstill, "LFWater");
//		this.blockRend(LFBlocks.lavastill, "LFLava");
		this.blockRend(LFBlocks.textureless);
		this.blockRend(LFBlocks.lightless);
	}
	public void registerItemRenderers(){
		this.itemRend(LFItems.dremic_ingot);
		this.itemRend(LFItems.trigger);
		this.itemRend(LFItems.fire_arrow);
		this.itemRend(LFItems.fire_bow);
        this.itemRend(LFItems.fire_bow, 1, "fire_bow_pulling_0");
        this.itemRend(LFItems.fire_bow, 2, "fire_bow_pulling_1");
        this.itemRend(LFItems.fire_bow, 3, "fire_bow_pulling_2");
		ModelBakery.addVariantName(LFItems.fire_bow, "lanfasy:fire_bow", "lanfasy:fire_bow_pulling_0", "lanfasy:fire_bow_pulling_1", "lanfasy:fire_bow_pulling_2");
		this.itemRend(Lanfasy.lfwaterbucket, "lfwater_bucket");
		this.itemRend(Lanfasy.lflavabucket, "lflava_bucket");
	}
}
