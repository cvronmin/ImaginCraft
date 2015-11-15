package tk.cvrunmin.lanfasy.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;

public class InventoryInori implements IInventory{
	private final ItemStack[] stackList;
	private final Container eventHandler;
	public InventoryInori(Container container){
		stackList = new ItemStack[11];
		eventHandler = container;
	}
	@Override
	public String getCommandSenderName() {
		// TODO Auto-generated method stub
		return "container.inori";
	}

	@Override
	public boolean hasCustomName() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
    public IChatComponent getDisplayName()
    {
        return (IChatComponent)(this.hasCustomName() ? new ChatComponentText(this.getCommandSenderName()) : new ChatComponentTranslation(this.getCommandSenderName(), new Object[0]));
    }

	@Override
	public int getSizeInventory()
	{
		return this.stackList.length;
	}

	@Override
	public ItemStack getStackInSlot(int par1)
	{
		return par1 >= this.getSizeInventory() ? null : this.stackList[par1];
	}
	@Override
    public ItemStack decrStackSize(int index, int count)
    {
        if (this.stackList[index] != null)
        {
            ItemStack itemstack;

            if (this.stackList[index].stackSize <= count)
            {
                itemstack = this.stackList[index];
                this.stackList[index] = null;
                this.eventHandler.onCraftMatrixChanged(this);
                return itemstack;
            }
            else
            {
                itemstack = this.stackList[index].splitStack(count);

                if (this.stackList[index].stackSize == 0)
                {
                    this.stackList[index] = null;
                }

                this.eventHandler.onCraftMatrixChanged(this);
                return itemstack;
            }
        }
        else
        {
            return null;
        }
    }

	@Override
	public ItemStack getStackInSlotOnClosing(int par1)
	{
		if (this.stackList[par1] != null)
		{
			final ItemStack var2 = this.stackList[par1];
			this.stackList[par1] = null;
			return var2;
		}
		else
		{
			return null;
		}
	}

	@Override
	public void setInventorySlotContents(int par1, ItemStack par2ItemStack)
	{
		this.stackList[par1] = par2ItemStack;
		this.eventHandler.onCraftMatrixChanged(this);
	}

	@Override
	public int getInventoryStackLimit() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public void markDirty() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void openInventory(EntityPlayer player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void closeInventory(EntityPlayer player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public int getField(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setField(int id, int value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getFieldCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void clear() {
        for (int i = 0; i < this.stackList.length; ++i)
        {
            this.stackList[i] = null;
        }
	}
}
