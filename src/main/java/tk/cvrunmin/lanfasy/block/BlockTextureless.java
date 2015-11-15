package tk.cvrunmin.lanfasy.block;

import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import tk.cvrunmin.fansy.api.block.FBlock;

public class BlockTextureless extends FBlock{

	public BlockTextureless() {
		super(Material.rock);
		this.setRegisteredName("textureless");
		this.setUnlocalizedName("textureless");
		this.setCreativeTab(CreativeTabs.tabBlock);
	}

}
