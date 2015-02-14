package tk.cvrunmin.lanfasy;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.item.Item;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import tk.cvrunmin.lanfasy.client.gui.LFGuiHandler;
import tk.cvrunmin.lanfasy.client.renderer.LFRendererEntity;
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
import tk.cvrunmin.lanfasy.util.LFConfig;
import tk.cvrunmin.lanfasy.world.WorldProviderLF;

@Mod(modid=Lanfasy.MODID, name = Lanfasy.NAME, version = Lanfasy.VERSION, guiFactory = Lanfasy.FACTORY)
public class Lanfasy {
	public static final String MODID = "lanfasy";
	public static final String NAME = "Lanfasy";
	public static final String VERSION = "1.0";
	public static final String FACTORY = "tk.cvrunmin.lanfasy.util.LFGuiFactory";
    @SidedProxy(clientSide="tk.cvrunmin.lanfasy.client.LFClientProxy", serverSide="tk.cvrunmin.lanfasy.LFCommonProxy")
    public static LFCommonProxy proxy;
	public static final EventListenerLF eventListener = new EventListenerLF();
	public static final LFConfig config = new LFConfig();
	public static final LFRendererEntity renderEntity = new LFRendererEntity();
    @Instance("lanfasy")
    public static Lanfasy instance;
    public static Fluid mlfluidformat;
    public static Fluid fluidformat;
    public static Fluid mlfluidwater;
    public static Fluid fluidwater;
    public static Fluid mlfluidlava;
    public static Fluid fluidlava;
    public static Item formatbucket;
    public static Item mwaterbucket;
    public static Item mlavabucket;
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        LFConfig.startConfig(event);
        LFBlocks.initBlocks();
        LFItems.initItems();
        this.registerEntity();
    }
    @Mod.EventHandler
    public void init(FMLInitializationEvent event){
		FMLCommonHandler.instance().bus().register(eventListener);
		FMLCommonHandler.instance().bus().register(config);
        NetworkRegistry.INSTANCE.registerGuiHandler(this, new LFGuiHandler());
        proxy.registerBlockRenderers();
        proxy.registerItemRenderers();
        proxy.registerRenderers(this);
//	    DimensionManager.registerProviderType(1010, WorldProviderLF.class, false);
//	    DimensionManager.registerDimension(1010, 1010);
    }
    public void registerEntity(){
    	registerEntity(LFEntityZombie.class, "LFZombie", 1010, 44975, 7969893);
    	registerEntity(LFEntityCreeper.class, "LFCreeper", 1011 , 894731, 0);
    	registerEntity(LFEntitySkeleton.class, "LFSkeleton", 1012, 12698049, 4802889);
    	registerEntity(LFEntitySpider.class, "LFSpider", 1013, 3419431, 11013646);
    	registerEntity(LFEntitySlime.class, "LFSlime", 1014, 5349438, 8306542);
    	registerEntity(LFEntityGhast.class, "LFGhast", 1015, 16382457, 12369084);
    	registerEntity(LFEntityPigZombie.class, "LFZombiePigman", 1016, 15373203, 5009705);
    	registerEntity(LFEntityEnderman.class, "LFEnderman", 1017, 1447446, 0);
    	registerEntity(LFEntitySlimeBoss.class, "slimeBoss", 1516, 534943, 830654);
    }
    public void registerEntity(Class<? extends Entity> entityClass, String registerName, int id){
		EntityRegistry.registerGlobalEntityID(entityClass, registerName, id);
		EntityRegistry.registerModEntity(entityClass, registerName, id, instance, 64, 1, true);	
    }
    public void registerEntity(Class<? extends Entity> entityClass, String registerName, int id, int back, int fore){
		EntityRegistry.registerGlobalEntityID(entityClass, registerName, id, back, fore);
		EntityRegistry.registerModEntity(entityClass, registerName, id, instance, 64, 1, true);	
    }
}
