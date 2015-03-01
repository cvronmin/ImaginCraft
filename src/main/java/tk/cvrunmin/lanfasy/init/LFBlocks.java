package tk.cvrunmin.lanfasy.init;

import net.minecraft.block.Block;
import net.minecraftforge.fml.common.registry.GameRegistry;
import tk.cvrunmin.lanfasy.block.BlockDamaged;
import tk.cvrunmin.lanfasy.block.BlockDremic;
import tk.cvrunmin.lanfasy.block.BlockDremicOre;
import tk.cvrunmin.lanfasy.block.BlockLFFire;
import tk.cvrunmin.lanfasy.block.BlockLFPortal;
import tk.cvrunmin.lanfasy.block.BlockLightless;
import tk.cvrunmin.lanfasy.block.BlockTextureless;
import tk.cvrunmin.lanfasy.block.LFBlock;

public class LFBlocks {
	public final static LFBlock dremic = new BlockDremic();
	public final static LFBlock dremic_ore = new BlockDremicOre();
	public final static BlockLFPortal lfportal = new BlockLFPortal();
	public final static BlockLFFire lffire = new BlockLFFire();
	public final static LFBlock lightless = new BlockLightless();
	public final static LFBlock textureless = new BlockTextureless();
	public final static LFBlock damaged = new BlockDamaged();

    public static void initBlocks(){
    	registerBlock(dremic);
    	registerBlock(dremic_ore);
    	registerBlock(lfportal);
    	registerBlock(lffire);
    	registerBlock(lightless);
    	registerBlock(textureless);
    	registerBlock(damaged);
    }
	private static void registerBlock(LFBlock block)
	{
		GameRegistry.registerBlock(block, block.getRegisteredName());
	}
	private static void registerBlock(Block block, String registerName)
	{
		GameRegistry.registerBlock(block, registerName);
	}
}
