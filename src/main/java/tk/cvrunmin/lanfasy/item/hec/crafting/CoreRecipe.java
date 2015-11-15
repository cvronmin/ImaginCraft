package tk.cvrunmin.lanfasy.item.hec.crafting;

import java.util.HashMap;
import java.util.Map.Entry;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import tk.cvrunmin.fansy.api.item.crafting.IFRecipe;

public class CoreRecipe implements IFRecipe{
	private HashMap<Integer, ItemStack> in;
	private ItemStack out;

    public CoreRecipe (HashMap<Integer, ItemStack> input, ItemStack output){
    	this.in = input;
    	this.out = output;
    }
	@Override
	public boolean matches(IInventory inventory) {
		for (Entry<Integer, ItemStack> entry : this.in.entrySet())
		{
			ItemStack stackAt = inventory.getStackInSlot(entry.getKey());

			if (!this.checkItemEquals(stackAt, entry.getValue()))
			{
				return false;
			}
		}

		return true;
	}
	private boolean checkItemEquals(ItemStack target, ItemStack input)
	{
		if (input == null && target != null || input != null && target == null)
		{
			return false;
		}
		return target == null && input == null || target.getItem() == input.getItem() && (target.getItemDamage() == OreDictionary.WILDCARD_VALUE || target.getItemDamage() == input.getItemDamage());
	}
	@Override
	public int getRecipeSize()
	{
		return this.in.size();
	}

	@Override
	public ItemStack getRecipeOutput()
	{
		return this.out.copy();
	}

	public HashMap<Integer, ItemStack> getRecipeInput()
	{
		return this.in;
	}

}
