package tk.cvrunmin.lanfasy.block;

import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import tk.cvrunmin.fansy.api.block.FBlock;

public class BlockDremic extends FBlock {

	public BlockDremic() {
		super(Material.rock);
		this.setUnlocalizedName("dremic");
		this.setRegisteredName("dremic");
        this.setCreativeTab(CreativeTabs.tabBlock);
	}

}
