package tk.cvrunmin.lanfasy.item.hec.crafting;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import tk.cvrunmin.lanfasy.init.LFItems;
import tk.cvrunmin.lanfasy.inventory.InventoryInori;
import tk.cvrunmin.mcme.api.item.crafting.IMERecipe;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class HECT {
    private static final HECT instance = new HECT();
    /** A list of all the recipes added */
    private final List<IMERecipe> coreRecipes = Lists.newArrayList();
    public static HECT getInstance(){
    	return instance;
    }
    private HECT(){
    	this.addCoreRecipe(new ItemStack(LFItems.dremic_ingot), new ItemStack[]{this.createStack(LFItems.dremic_ingot),this.createStack(LFItems.dremic_ingot),this.createStack(LFItems.dremic_ingot), null});
    }
    public CoreRecipe addCoreRecipe(ItemStack output, ItemStack[] input){
    	HashMap<Integer, ItemStack> map = Maps.newHashMap();
    	for(int i = 0;i < input.length;++i){
    		map.put(i + 1, input[i]);
    	}
    	for(int i = 1;i < 11;++i){
    		if(!map.containsKey(i)){
    			map.put(i, null);
    		}
    	}
    	CoreRecipe recipe = new CoreRecipe(map, output);
    	this.coreRecipes.add(recipe);
		return recipe;
    }
    public ItemStack findCoreRecipe(InventoryInori matrix){
        Iterator iterator = this.coreRecipes.iterator();
        IMERecipe irecipe;

        do
        {
            if (!iterator.hasNext())
            {
                return null;
            }

            irecipe = (IMERecipe)iterator.next();
        }
        while (!irecipe.matches(matrix));
		return irecipe.getRecipeOutput();
    	
    }
    private ItemStack createStack(Item item){
    	ItemStack stack = new ItemStack(item);
    	return stack;
    }
    private ItemStack createStack(Block block){
    	ItemStack stack = new ItemStack(block);
    	return stack;
    }
}
