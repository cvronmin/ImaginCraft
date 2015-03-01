package tk.cvrunmin.lanfasy.item;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBucket;

public class ItemLFBucket extends ItemBucket{
	private Block fluid;
    private Block isFull;
    private String fluidName;
	public ItemLFBucket(Block fluid) {
        this(fluid,CreativeTabs.tabMisc);
		// TODO Auto-generated constructor stub
	}
    public ItemLFBucket(Block fluid,CreativeTabs tab){
    	super(fluid);
		this.fluid = fluid;
    	setContainerItem(Items.bucket);
    	setCreativeTab(tab);
    }
}
