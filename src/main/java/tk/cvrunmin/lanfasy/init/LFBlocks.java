package tk.cvrunmin.lanfasy.init;

import net.minecraftforge.fml.common.registry.GameRegistry;
import tk.cvrunmin.lanfasy.block.BlockDremic;
import tk.cvrunmin.lanfasy.block.BlockDremicOre;
import tk.cvrunmin.lanfasy.block.BlockLFFire;
import tk.cvrunmin.lanfasy.block.BlockLFPortal;
import tk.cvrunmin.lanfasy.block.LFBlock;

public class LFBlocks {
	public final static LFBlock dremic = new BlockDremic();
	public final static LFBlock dremic_ore = new BlockDremicOre();
	public final static BlockLFPortal lfportal = new BlockLFPortal();
	public final static BlockLFFire lffire = new BlockLFFire();

    public static void initBlocks(){
    	registerBlock(dremic);
    	registerBlock(dremic_ore);
    	registerBlock(lfportal);
    	registerBlock(lffire);
    }
	private static void registerBlock(LFBlock block)
	{
		GameRegistry.registerBlock(block, block.getRegisteredName());
	}
}
