package tk.cvrunmin.lanfasy.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntityLockable;
import tk.cvrunmin.lanfasy.inventory.ContainerDimchanger;

public class TileEntityDimChanger extends TileEntityLockable implements IInventory{
    private ItemStack[] stacks = new ItemStack[10];
    protected String customName;
	@Override
	public String getCommandSenderName() {
		return this.hasCustomName() ? this.customName : "block.dimchanger";
	}
    public void setCustomName(String customName)
    {
        this.customName = customName;
    }
	@Override
	public boolean hasCustomName() {
		return customName != null;
	}
    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        NBTTagList nbttaglist = compound.getTagList("Items", 10);
        this.stacks = new ItemStack[this.getSizeInventory()];

        for (int i = 0; i < nbttaglist.tagCount(); ++i)
        {
            NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
            int j = nbttagcompound1.getByte("Slot") & 255;

            if (j >= 0 && j < this.stacks.length)
            {
                this.stacks[j] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }

        if (compound.hasKey("CustomName", 8))
        {
            this.customName = compound.getString("CustomName");
        }
    }

    public void writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < this.stacks.length; ++i)
        {
            if (this.stacks[i] != null)
            {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte)i);
                this.stacks[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }

        compound.setTag("Items", nbttaglist);

        if (this.hasCustomName())
        {
            compound.setString("CustomName", this.customName);
        }
    }
	@Override
	public Container createContainer(InventoryPlayer playerInventory,
			EntityPlayer playerIn) {
		return new ContainerDimchanger(playerInventory, this, this.worldObj);
	}

	@Override
	public String getGuiID() {
		return "lanfasy:dimchanger";
	}

	@Override
    public int getSizeInventory()
    {
        return 10;
    }

	@Override
	public ItemStack getStackInSlot(int index) {
		return this.stacks[index];
	}

	@Override
    public ItemStack decrStackSize(int index, int count)
    {
        if (this.stacks[index] != null)
        {
            ItemStack itemstack;

            if (this.stacks[index].stackSize <= count)
            {
                itemstack = this.stacks[index];
                this.stacks[index] = null;
                this.markDirty();
                return itemstack;
            }
            else
            {
                itemstack = this.stacks[index].splitStack(count);

                if (this.stacks[index].stackSize == 0)
                {
                    this.stacks[index] = null;
                }

                this.markDirty();
                return itemstack;
            }
        }
        else
        {
            return null;
        }
    }

	@Override
    public ItemStack getStackInSlotOnClosing(int index)
    {
        if (this.stacks[index] != null)
        {
            ItemStack itemstack = this.stacks[index];
            this.stacks[index] = null;
            return itemstack;
        }
        else
        {
            return null;
        }
    }

	@Override
    public void setInventorySlotContents(int index, ItemStack stack)
    {
        this.stacks[index] = stack;

        if (stack != null && stack.stackSize > this.getInventoryStackLimit())
        {
            stack.stackSize = this.getInventoryStackLimit();
        }

        this.markDirty();
    }

	@Override
	public int getInventoryStackLimit() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
    public boolean isUseableByPlayer(EntityPlayer player)
    {
        return this.worldObj.getTileEntity(this.pos) != this ? false : player.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;
    }

	@Override
	public void openInventory(EntityPlayer player) {}

	@Override
	public void closeInventory(EntityPlayer player) {}

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
	public void setField(int id, int value) {}

	@Override
	public int getFieldCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void clear() {
        for (int i = 0; i < this.stacks.length; ++i)
        {
            this.stacks[i] = null;
        }
	}

}
