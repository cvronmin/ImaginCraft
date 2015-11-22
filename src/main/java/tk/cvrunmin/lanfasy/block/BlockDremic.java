package tk.cvrunmin.lanfasy.block;

import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import tk.cvrunmin.mcme.api.block.MEBlock;

public class BlockDremic extends MEBlock {

	public BlockDremic() {
		super(Material.rock);
		this.setUnlocalizedName("dremic");
		this.setRegisteredName("dremic");
        this.setCreativeTab(CreativeTabs.tabBlock);
	}

}
