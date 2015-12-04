package tk.cvrunmin.lanfasy.client;

import net.minecraft.client.resources.model.ModelBakery;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import tk.cvrunmin.lanfasy.EventListenerLFClient;
import tk.cvrunmin.lanfasy.LFCommonProxy;
import tk.cvrunmin.lanfasy.Lanfasy;
import tk.cvrunmin.lanfasy.TickEventsLFClient;
import tk.cvrunmin.lanfasy.client.model.ModelLFSlime;
import tk.cvrunmin.lanfasy.client.renderer.RenderCreeperBoss;
import tk.cvrunmin.lanfasy.client.renderer.RenderFireArrow;
import tk.cvrunmin.lanfasy.client.renderer.RenderInoArrow;
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
import tk.cvrunmin.lanfasy.client.renderer.crystal.RenderGreenstal;
import tk.cvrunmin.lanfasy.client.renderer.crystal.RenderRedstal;
import tk.cvrunmin.lanfasy.client.renderer.cy.RenderHymnson;
import tk.cvrunmin.lanfasy.client.renderer.cy.RenderWaiWai;
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
import tk.cvrunmin.lanfasy.entity.cy.EntityHymnson;
import tk.cvrunmin.lanfasy.entity.cy.EntityWaiWai;
import tk.cvrunmin.lanfasy.entity.effect.EntityLightBolt;
import tk.cvrunmin.lanfasy.entity.item.EntityGreenstal;
import tk.cvrunmin.lanfasy.entity.item.EntityRedstal;
import tk.cvrunmin.lanfasy.entity.projectile.EntityFireArrow;
import tk.cvrunmin.lanfasy.entity.projectile.EntityInoArrow;
import tk.cvrunmin.lanfasy.entity.projectile.EntityIrrow;
import tk.cvrunmin.lanfasy.entity.projectile.EntitySafeIrrow;
import tk.cvrunmin.lanfasy.init.LFBlocks;
import tk.cvrunmin.lanfasy.init.LFHecItems;
import tk.cvrunmin.lanfasy.init.LFItems;

public class LFClientProxy extends LFCommonProxy{
	@Override
	public void init(FMLInitializationEvent event){
		this.registerRenderers();
		this.registerBlockRenderers();
		this.registerItemRenderers();
		TickEventsLFClient tickclient = new TickEventsLFClient();
		MinecraftForge.EVENT_BUS.register(tickclient);
		FMLCommonHandler.instance().bus().register(tickclient);
		EventListenerLFClient eventc = new EventListenerLFClient();
		MinecraftForge.EVENT_BUS.register(eventc);
		FMLCommonHandler.instance().bus().register(eventc);
	}
	public void registerRenderers(){
		RenderingRegistry.registerEntityRenderingHandler(LFEntityCreeper.class, new RenderLFCreeper());
		RenderingRegistry.registerEntityRenderingHandler(LFEntityEnderman.class, new RenderLFEnderman());
		RenderingRegistry.registerEntityRenderingHandler(LFEntityGhast.class, new RenderLFGhast());
		RenderingRegistry.registerEntityRenderingHandler(LFEntitySkeleton.class, new RenderLFSkeleton());
		RenderingRegistry.registerEntityRenderingHandler(LFEntityPigZombie.class, new RenderLFPigZombie());
		RenderingRegistry.registerEntityRenderingHandler(LFEntityZombie.class, new RenderLFZombie());
		RenderingRegistry.registerEntityRenderingHandler(LFEntitySlime.class, new RenderLFSlime(new ModelLFSlime(16), 0.25F));
		RenderingRegistry.registerEntityRenderingHandler(LFEntitySpider.class, new RenderLFSpider());
		RenderingRegistry.registerEntityRenderingHandler(LFEntitySlimeBoss.class, new RenderSlimeBoss(new ModelLFSlime(16), 0.25F));
		RenderingRegistry.registerEntityRenderingHandler(EntityFireArrow.class, new RenderFireArrow());
		RenderingRegistry.registerEntityRenderingHandler(LFEntityCreeperBoss.class, new RenderCreeperBoss());
		RenderingRegistry.registerEntityRenderingHandler(LFEntityZombieBoss.class, new RenderZombieBoss());
		RenderingRegistry.registerEntityRenderingHandler(EntityLightBolt.class, new RenderLightBolt());
		RenderingRegistry.registerEntityRenderingHandler(EntitySafeIrrow.class, new RenderIrrows());
		RenderingRegistry.registerEntityRenderingHandler(EntityIrrow.class, new RenderIrrows());
		RenderingRegistry.registerEntityRenderingHandler(EntityRedstal.class, new RenderRedstal());
		RenderingRegistry.registerEntityRenderingHandler(EntityGreenstal.class, new RenderGreenstal());
		RenderingRegistry.registerEntityRenderingHandler(EntityInoArrow.Fire.class, new RenderInoArrow.Fire());
		RenderingRegistry.registerEntityRenderingHandler(EntityInoArrow.TP.class, new RenderInoArrow.TP());
		RenderingRegistry.registerEntityRenderingHandler(EntityHymnson.class, new RenderHymnson());
		RenderingRegistry.registerEntityRenderingHandler(EntityWaiWai.class, new RenderWaiWai());
	}
	public void registerBlockRenderers(){
		this.blockRend(LFBlocks.dremic);
		this.blockRend(LFBlocks.dremic_ore);
		this.blockRend(LFBlocks.lfportal);
//		this.blockRend(LFBlocks.lffire);
//		this.blockRend(LFBlocks.waterstill, "LFWater");
//		this.blockRend(LFBlocks.lavastill, "LFLava");
		this.blockRend(LFBlocks.textureless);
		this.blockRend(LFBlocks.lightless);
		this.blockRend(LFBlocks.afb);
		this.blockRend(LFBlocks.dimchanger);
		this.blockRend(LFBlocks.slimeboss);
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
		
		this.itemRend(LFItems.ino_bow, 0, "ino_bow_0");
		this.itemRend(LFItems.ino_bow, 1, "ino_bow_1");
		this.itemRend(LFItems.ino_bow, 5, "ino_bow_0_pulling_0");
		this.itemRend(LFItems.ino_bow, 6, "ino_bow_1_pulling_0");
		this.itemRend(LFItems.ino_bow, 10, "ino_bow_0_pulling_1");
		this.itemRend(LFItems.ino_bow, 11, "ino_bow_1_pulling_1");
		this.itemRend(LFItems.ino_bow, 15, "ino_bow_0_pulling_2");
		this.itemRend(LFItems.ino_bow, 16, "ino_bow_1_pulling_2");
		ModelBakery.addVariantName(LFItems.ino_bow, "lanfasy:ino_bow_0", "lanfasy:ino_bow_0_pulling_0", "lanfasy:ino_bow_0_pulling_1", "lanfasy:ino_bow_0_pulling_2"
								  				  , "lanfasy:ino_bow_1", "lanfasy:ino_bow_1_pulling_0", "lanfasy:ino_bow_1_pulling_1", "lanfasy:ino_bow_1_pulling_2");
		this.itemRend(LFItems.ino_bow_ignite, 0, "ino_bow_0");
		this.itemRend(LFItems.ino_bow_ignite, 1, "ino_bow_0_pulling_0");
		this.itemRend(LFItems.ino_bow_ignite, 2, "ino_bow_0_pulling_1");
		this.itemRend(LFItems.ino_bow_ignite, 3, "ino_bow_0_pulling_2");
		ModelBakery.addVariantName(LFItems.ino_bow_ignite, "lanfasy:ino_bow_0", "lanfasy:ino_bow_0_pulling_0", "lanfasy:ino_bow_0_pulling_1", "lanfasy:ino_bow_0_pulling_2");
		this.itemRend(LFItems.ino_bow_chain , 0, "ino_bow_1");
		this.itemRend(LFItems.ino_bow_chain , 1, "ino_bow_1_pulling_0");
		this.itemRend(LFItems.ino_bow_chain , 2, "ino_bow_1_pulling_1");
		this.itemRend(LFItems.ino_bow_chain , 3, "ino_bow_1_pulling_2");
		ModelBakery.addVariantName(LFItems.ino_bow_chain , "lanfasy:ino_bow_1", "lanfasy:ino_bow_1_pulling_0", "lanfasy:ino_bow_1_pulling_1", "lanfasy:ino_bow_1_pulling_2");
		this.itemRend(Lanfasy.lfwaterbucket, "lfwater_bucket");
		this.itemRend(Lanfasy.lflavabucket, "lflava_bucket");
		this.registerHERenderers();
	}
	private void registerHERenderers(){
		this.itemRend(LFHecItems.him2d23);
		this.itemRend(LFHecItems.macrystal, 0, "macrystal_pure");
		this.itemRend(LFHecItems.macrystal, 1, "macrystal_prayer");
		this.itemRend(LFHecItems.macrystal, 2, "macrystal_txper");
		this.itemRend(LFHecItems.macrystal, 3, "macrystal_shere");
		this.itemRend(LFHecItems.macrystal, 4, "macrystal_ender");
		this.itemRend(LFHecItems.macrystal, 5, "macrystal_power");
		this.itemRend(LFHecItems.macrystal, 6, "macrystal_kart");
		this.itemRend(LFHecItems.macrystal, 7, "macrystal_dround");
		this.itemRend(LFHecItems.macrystal, 8, "macrystal_void");
		this.itemRend(LFHecItems.macrystal, 21, "macrystal_O");
		this.itemRend(LFHecItems.macrystal, 22, "macrystal_gsound");
		ModelBakery.addVariantName(LFHecItems.macrystal, "lanfasy:macrystal_pure", "macrystal_prayer", "macrystal_txper", "macrystal_shere", "macrystal_ender", "macrystal_power", "macrystal_kart", "macrystal_dround", "macrystal_void",
				"macrystal_O", "macrystal_gsound");
	}
}
