package tk.cvrunmin.lanfasy;

import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import tk.cvrunmin.lanfasy.client.gui.GuiBossBarOverlay;
import tk.cvrunmin.lanfasy.client.gui.GuiCHWOverlay;
import tk.cvrunmin.lanfasy.client.gui.LFGuiHandler;
import tk.cvrunmin.lanfasy.command.CommandDIMTeleport;
import tk.cvrunmin.lanfasy.command.CommandSpeed;
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
import tk.cvrunmin.lanfasy.entity.item.EntityGreenstal;
import tk.cvrunmin.lanfasy.entity.item.EntityRedstal;
import tk.cvrunmin.lanfasy.entity.projectile.EntityFireArrow;
import tk.cvrunmin.lanfasy.entity.projectile.EntityInoArrow;
import tk.cvrunmin.lanfasy.entity.projectile.EntityIrrow;
import tk.cvrunmin.lanfasy.entity.projectile.EntitySafeIrrow;
import tk.cvrunmin.lanfasy.event.EventMessBoss;
import tk.cvrunmin.lanfasy.init.LFBlocks;
import tk.cvrunmin.lanfasy.init.LFItems;
import tk.cvrunmin.lanfasy.tileentity.TileEntityAFPortal;
import tk.cvrunmin.lanfasy.tileentity.TileEntityRepairFurnace;
import tk.cvrunmin.lanfasy.util.LFConfig;
import tk.cvrunmin.lanfasy.world.WorldProviderAltifect;
import tk.cvrunmin.lanfasy.world.WorldProviderCrashHell;
import tk.cvrunmin.lanfasy.world.WorldProviderLF;
import tk.cvrunmin.lanfasy.world.gen.LFWorldGenMinable;

@Mod(modid=Lanfasy.MODID, name = Lanfasy.NAME, version = Lanfasy.VERSION, guiFactory = Lanfasy.FACTORY)
public class Lanfasy {
	public static final String MODID = "lanfasy";
	public static final String NAME = "Lanfasy";
	public static final String VERSION = "1.0";
	public static final String FACTORY = "tk.cvrunmin.lanfasy.util.LFGuiFactory";
    @SidedProxy(clientSide="tk.cvrunmin.lanfasy.client.LFClientProxy", serverSide="tk.cvrunmin.lanfasy.LFCommonProxy")
    public static LFCommonProxy proxy;
    public static Side side;
	public static final EventListenerLF eventListener = new EventListenerLF();
	public static final GuiCHWOverlay overlaychw = new GuiCHWOverlay(FMLClientHandler.instance().getClient());
	public static final GuiBossBarOverlay cybossBar = new GuiBossBarOverlay(FMLClientHandler.instance().getClient());
	public static final LFConfig config = new LFConfig();
    @Instance("lanfasy")
    public static Lanfasy instance;
    public static Fluid lffluidwater;
    public static Fluid fluidwater;
    public static Fluid lffluidlava;
    public static Fluid fluidlava;
    public static Item lfwaterbucket;
    public static Item lflavabucket;
    public static LFTabs tab = new LFTabs();
    @Mod.EventHandler
    public void serverLoad(FMLServerStartingEvent event) {
		event.registerServerCommand(new CommandDIMTeleport());
    }
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        side = event.getSide();
        LFConfig.startConfig(event);
/*            lffluidwater = new Fluid("lfwater").setViscosity(1000);
            lffluidlava = new Fluid("lflava").setViscosity(6000).setLuminosity(15);
            FluidRegistry.registerFluid(lffluidwater);
            FluidRegistry.registerFluid(lffluidlava);
    		fluidwater = FluidRegistry.getFluid("lfwater");
    		fluidlava = FluidRegistry.getFluid("lflava");
    		if (fluidwater.getBlock() == null)
    		{
    			LFBlocks.waterstill = new BlockLFFluid(fluidwater,Material.water);
    			((BlockLFFluid) LFBlocks.waterstill).setQuantaPerBlock(8);
    			LFBlocks.waterstill.setUnlocalizedName("lfwater");
    			fluidwater.setBlock(LFBlocks.waterstill);
    		}
    		else
    		{
    			LFBlocks.waterstill = fluidwater.getBlock();
    		}
    		if (fluidlava.getBlock() == null)
    		{
    			LFBlocks.lavastill = new BlockLFFluid(fluidlava, Material.lava);
    			((BlockLFFluid) LFBlocks.lavastill).setQuantaPerBlock(4);
    			LFBlocks.lavastill.setUnlocalizedName("lflava");
    			fluidlava.setBlock(LFBlocks.lavastill);
    		}
    		else
    		{
    			LFBlocks.lavastill = fluidlava.getBlock();
    		}    		
    	    lfwaterbucket = new ItemLFBucket(LFBlocks.waterstill);
    		lfwaterbucket.setUnlocalizedName("bucket_lfwater").setContainerItem(Items.bucket);
    		FluidContainerRegistry.registerFluidContainer(FluidRegistry.getFluidStack("lfwater", FluidContainerRegistry.BUCKET_VOLUME), new ItemStack(lfwaterbucket), new ItemStack(Items.bucket));
    		BucketHandlerLF.INSTANCE.buckets.put(LFBlocks.waterstill, lfwaterbucket);
    		
    	    lflavabucket = new ItemLFBucket(LFBlocks.lavastill);
    		lflavabucket.setUnlocalizedName("bucket_lflava").setContainerItem(Items.bucket);
    		FluidContainerRegistry.registerFluidContainer(FluidRegistry.getFluidStack("lflava", FluidContainerRegistry.BUCKET_VOLUME), new ItemStack(lflavabucket), new ItemStack(Items.bucket));
    		BucketHandlerLF.INSTANCE.buckets.put(LFBlocks.lavastill, lflavabucket);
    		
    		MinecraftForge.EVENT_BUS.register(BucketHandlerLF.INSTANCE);*/
            LFBlocks.initBlocks();
            LFItems.initItems();
//    		GameRegistry.registerItem(lfwaterbucket, "lfwater_bucket");
//    		GameRegistry.registerItem(lflavabucket, "lflava_bucket");
            this.registerEntitys();
//    		EntityList.entityEggs.remove(120);
        }
    @Mod.EventHandler
    public void init(FMLInitializationEvent event){
    	GameRegistry.registerWorldGenerator(new LFWorldGenMinable(), 5);
		MinecraftForge.EVENT_BUS.register(eventListener);
		FMLCommonHandler.instance().bus().register(eventListener);
		MinecraftForge.EVENT_BUS.register(overlaychw);
		MinecraftForge.EVENT_BUS.register(cybossBar);
		TickEventsLFServer tickserver = new TickEventsLFServer();
		MinecraftForge.EVENT_BUS.register(tickserver);
		FMLCommonHandler.instance().bus().register(tickserver);
		FMLCommonHandler.instance().bus().register(config);
		MinecraftForge.EVENT_BUS.register(new EventMessBoss());
        NetworkRegistry.INSTANCE.registerGuiHandler(this, new LFGuiHandler());
/*        if(side.isClient()){
        proxy.registerBlockRenderers();
        proxy.registerItemRenderers();
        proxy.registerRenderers(this);
        }*/
        proxy.init(event);
	    DimensionManager.registerProviderType(1010, WorldProviderLF.class, true);
	    DimensionManager.registerDimension(1010, 1010);
//	    DimensionManager.registerProviderType(-1011, WorldProviderCrashHell.class, true);
//	    DimensionManager.registerDimension(-1011, -1011);
	    DimensionManager.registerProviderType(512, WorldProviderAltifect.class, false);
	    DimensionManager.registerDimension(512, 512);
    }
    public void registerEntitys(){
    	int i = EntityRegistry.findGlobalUniqueEntityId();
    	registerEntity(LFEntityZombie.class, "LFZombie", i, 44975, 7969893);
    	i = EntityRegistry.findGlobalUniqueEntityId();
    	registerEntity(LFEntityCreeper.class, "LFCreeper", i , 894731, 0);
    	i = EntityRegistry.findGlobalUniqueEntityId();
    	registerEntity(LFEntitySkeleton.class, "LFSkeleton", i, 12698049, 4802889);
    	i = EntityRegistry.findGlobalUniqueEntityId();
    	registerEntity(LFEntitySpider.class, "LFSpider", i, 3419431, 11013646);
    	i = EntityRegistry.findGlobalUniqueEntityId();
    	registerEntity(LFEntitySlime.class, "LFSlime", i, 5349438, 8306542);
    	i = EntityRegistry.findGlobalUniqueEntityId();
    	registerEntity(LFEntityGhast.class, "LFGhast", i, 16382457, 12369084);
    	i = EntityRegistry.findGlobalUniqueEntityId();
    	registerEntity(LFEntityPigZombie.class, "LFZombiePigman", i, 15373203, 5009705);
    	i = EntityRegistry.findGlobalUniqueEntityId();
    	registerEntity(LFEntityEnderman.class, "LFEnderman", i, 1447446, 0);
    	i = EntityRegistry.findGlobalUniqueEntityId();
    	registerEntity(LFEntitySlimeBoss.class, "SlimeBoss", i, 534943, 830654);
    	i = EntityRegistry.findGlobalUniqueEntityId();
    	registerEntity(LFEntityCreeperBoss.class, "CreeperBoss", i, 14155115,2557451);
    	i = EntityRegistry.findGlobalUniqueEntityId();
    	registerEntity(LFEntityZombieBoss.class, "ZombieBoss", i, 35165813,6456132);
    	i = EntityRegistry.findGlobalUniqueEntityId();
    	registerEntity(EntityFireArrow.class, "FireArrow", i);
    	i = EntityRegistry.findGlobalUniqueEntityId();
    	registerEntity(EntityIrrow.class, "Irrow", i);
    	i = EntityRegistry.findGlobalUniqueEntityId();
    	registerEntity(EntitySafeIrrow.class, "Irrow_safe", i);
    	i = EntityRegistry.findGlobalUniqueEntityId();
    	registerEntity(EntityInoArrow.Fire.class, "FirePray", i);
    	i = EntityRegistry.findGlobalUniqueEntityId();
    	registerEntity(EntityInoArrow.TP.class, "TPPray", i);
    	i = EntityRegistry.findGlobalUniqueEntityId();
    	registerEntity(EntityRedstal.class, "Redstal", i);
    	i = EntityRegistry.findGlobalUniqueEntityId();
    	registerEntity(EntityGreenstal.class, "Greenstal", i);
    	i = EntityRegistry.findGlobalUniqueEntityId();
    	registerEntity(EntityHymnson.class, "Prayer", i, 0xFC1296, 0xAC2046);
        GameRegistry.registerTileEntity(TileEntityRepairFurnace.class, "TileEntityReFur");
        GameRegistry.registerTileEntity(TileEntityAFPortal.class, "TileEntityAFPortal");
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
