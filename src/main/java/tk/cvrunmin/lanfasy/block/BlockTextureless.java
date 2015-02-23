package tk.cvrunmin.lanfasy.block;

import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BlockTextureless extends LFBlock{

	public BlockTextureless() {
		super(Material.rock);
		this.setRegisteredName("textureless");
		this.setUnlocalizedName("textureless");
		this.setCreativeTab(CreativeTabs.tabBlock);
	}

}
