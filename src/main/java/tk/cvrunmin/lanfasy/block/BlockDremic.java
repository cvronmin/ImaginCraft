package tk.cvrunmin.lanfasy.block;

import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BlockDremic extends LFBlock {

	public BlockDremic() {
		super(Material.rock);
		this.setUnlocalizedName("dremic");
		this.setRegisteredName("dremic");
        this.setCreativeTab(CreativeTabs.tabBlock);
	}

}
