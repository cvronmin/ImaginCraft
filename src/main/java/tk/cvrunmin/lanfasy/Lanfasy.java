package tk.cvrunmin.lanfasy;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import tk.cvrunmin.lanfasy.block.BlockLFFluid;
import tk.cvrunmin.lanfasy.client.gui.LFGuiHandler;
import tk.cvrunmin.lanfasy.client.renderer.LFRendererEntity;
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
import tk.cvrunmin.lanfasy.entity.projectile.EntityFireArrow;
import tk.cvrunmin.lanfasy.entity.projectile.EntityIrrow;
import tk.cvrunmin.lanfasy.entity.projectile.EntitySafeIrrow;
import tk.cvrunmin.lanfasy.init.LFBlocks;
import tk.cvrunmin.lanfasy.init.LFItems;
import tk.cvrunmin.lanfasy.item.ItemLFBucket;
import tk.cvrunmin.lanfasy.util.LFConfig;
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
	public static final LFConfig config = new LFConfig();
    @Instance("lanfasy")
    public static Lanfasy instance;
    public static Fluid lffluidwater;
    public static Fluid fluidwater;
    public static Fluid lffluidlava;
    public static Fluid fluidlava;
    public static Item lfwaterbucket;
    public static Item lflavabucket;
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
            this.registerEntity();
    		EntityList.entityEggs.remove(120);
        }
    @Mod.EventHandler
    public void init(FMLInitializationEvent event){
    	GameRegistry.registerWorldGenerator(new LFWorldGenMinable(), 5);
		MinecraftForge.EVENT_BUS.register(eventListener);
		FMLCommonHandler.instance().bus().register(eventListener);
		FMLCommonHandler.instance().bus().register(config);
        NetworkRegistry.INSTANCE.registerGuiHandler(this, new LFGuiHandler());
        if(side.isClient()){
        proxy.registerBlockRenderers();
        proxy.registerItemRenderers();
        proxy.registerRenderers(this);
        }
	    DimensionManager.registerProviderType(1010, WorldProviderLF.class, true);
	    DimensionManager.registerDimension(1010, 1010);
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
    	registerEntity(LFEntitySlimeBoss.class, "SlimeBoss", 1516, 534943, 830654);
    	registerEntity(LFEntityCreeperBoss.class, "CreeperBoss", 1517, 14155115,2557451);
    	registerEntity(LFEntityZombieBoss.class, "ZombieBoss", 1518, 35165813,6456132);
    	registerEntity(EntityFireArrow.class, "FireArrow", 1024);
    	registerEntity(EntityIrrow.class, "Irrow", 1025);
    	registerEntity(EntitySafeIrrow.class, "Irrow_safe", 1026);
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
