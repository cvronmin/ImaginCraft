package tk.cvrunmin.lanfasy.entity.item;

import java.util.Iterator;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityAFCrystal extends Entity{
    /** Used to create the rotation animation when rendering the crystal. */
    public int innerRotation;
    public int health;

    public EntityAFCrystal(World worldIn)
    {
        super(worldIn);
        this.preventEntitySpawning = true;
        this.setSize(2.0F, 2.0F);
        this.health = 5;
        this.innerRotation = this.rand.nextInt(100000);
    }
    @SideOnly(Side.CLIENT)
    public EntityAFCrystal(World worldIn, double x, double y, double z)
    {
        this(worldIn);
        this.setPosition(x, y, z);
    }
    protected boolean canTriggerWalking()
    {
        return false;
    }

    protected void entityInit()
    {
        this.dataWatcher.addObject(8, Integer.valueOf(this.health));
    }
    public void onUpdate()
    {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        ++this.innerRotation;
        this.dataWatcher.updateObject(8, Integer.valueOf(this.health));
    }
	protected void readEntityFromNBT(NBTTagCompound tagCompund) {

	}
	protected void writeEntityToNBT(NBTTagCompound tagCompound) {

	}
    public boolean canBeCollidedWith()
    {
        return true;
    }
    public boolean attackEntityFrom(DamageSource source, float amount)
    {
        if (this.isEntityInvulnerable(source))
        {
            return false;
        }
        else
        {
            if (!this.isDead && !this.worldObj.isRemote)
            {
                this.health -= 1;

                if (this.health <= 0)
                {
                    this.setDead();

                    if (!this.worldObj.isRemote)
                    {
                        this.worldObj.createExplosion((Entity)null, this.posX, this.posY, this.posZ, 6.0F, true);
                    }
                }
            }

            return true;
        }
    }
    public final int getHealth()
    {
        return this.dataWatcher.getWatchableObjectInt(8);
    }

    public void setHealth(int par1)
    {
        this.dataWatcher.updateObject(8, Integer.valueOf(par1));
    }
    public final float getMaxHealth()
    {
        return 5f;
    }
}
