package tk.cvrunmin.lanfasy.block;

import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import tk.cvrunmin.mcme.api.block.MEBlock;

public class BlockTextureless extends MEBlock{

	public BlockTextureless() {
		super(Material.rock);
		this.setRegisteredName("textureless");
		this.setUnlocalizedName("textureless");
		this.setCreativeTab(CreativeTabs.tabBlock);
	}

}
