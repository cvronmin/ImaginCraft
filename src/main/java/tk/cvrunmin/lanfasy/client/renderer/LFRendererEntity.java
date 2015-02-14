package tk.cvrunmin.lanfasy.client.renderer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import tk.cvrunmin.lanfasy.client.model.ModelLFSlime;
import tk.cvrunmin.lanfasy.entity.LFEntityCreeper;
import tk.cvrunmin.lanfasy.entity.LFEntityEnderman;
import tk.cvrunmin.lanfasy.entity.LFEntityGhast;
import tk.cvrunmin.lanfasy.entity.LFEntityPigZombie;
import tk.cvrunmin.lanfasy.entity.LFEntitySkeleton;
import tk.cvrunmin.lanfasy.entity.LFEntitySlime;
import tk.cvrunmin.lanfasy.entity.LFEntitySlimeBoss;
import tk.cvrunmin.lanfasy.entity.LFEntitySpider;
import tk.cvrunmin.lanfasy.entity.LFEntityZombie;

public class LFRendererEntity {
	static RenderManager manager = Minecraft.getMinecraft().getRenderManager();
	public static void registerEntityRenderers(){
		RenderingRegistry.registerEntityRenderingHandler(LFEntityCreeper.class, new RenderLFCreeper(manager));
		RenderingRegistry.registerEntityRenderingHandler(LFEntityEnderman.class, new RenderLFEnderman(manager));
		RenderingRegistry.registerEntityRenderingHandler(LFEntityGhast.class, new RenderLFGhast(manager));
		RenderingRegistry.registerEntityRenderingHandler(LFEntitySkeleton.class, new RenderLFSkeleton(manager));
		RenderingRegistry.registerEntityRenderingHandler(LFEntityPigZombie.class, new RenderLFPigZombie(manager));
		RenderingRegistry.registerEntityRenderingHandler(LFEntityZombie.class, new RenderLFZombie(manager));
		RenderingRegistry.registerEntityRenderingHandler(LFEntitySlime.class, new RenderLFSlime(manager, new ModelLFSlime(16), 0.25F));
		RenderingRegistry.registerEntityRenderingHandler(LFEntitySpider.class, new RenderLFSpider(manager));
		RenderingRegistry.registerEntityRenderingHandler(LFEntitySlimeBoss.class, new RenderSlimeBoss(manager, new ModelLFSlime(16), 0.25F));
	}
}
