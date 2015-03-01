package tk.cvrunmin.lanfasy.entity.effect;

import java.util.List;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.effect.EntityWeatherEffect;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityLightBolt extends EntityWeatherEffect
{
    private int lightningState;
    public long boltVertex;
    private int boltLivingTime;

    public EntityLightBolt(World worldIn, double posX, double posY, double posZ)
    {
        super(worldIn);
        this.setLocationAndAngles(posX, posY, posZ, 0.0F, 0.0F);
        this.lightningState = 5;
        this.boltVertex = this.rand.nextLong();
        this.boltLivingTime = this.rand.nextInt(3) + 1;
    }
    @Override
    public void onUpdate()
    {
        super.onUpdate();

        if (this.lightningState == 5)
        {
            this.worldObj.playSoundEffect(this.posX, this.posY, this.posZ, "ambient.weather.thunder", 10000.0F, 0.8F + this.rand.nextFloat() * 0.2F);
            this.worldObj.playSoundEffect(this.posX, this.posY, this.posZ, "random.explode", 2.0F, 0.5F + this.rand.nextFloat() * 0.2F);
        }

        --this.lightningState;

        if (this.lightningState < 0)
        {
            if (this.boltLivingTime == 0)
            {
                this.setDead();
            }
            else if (this.lightningState < -this.rand.nextInt(10))
            {
                --this.boltLivingTime;
                this.lightningState = 1;
                this.boltVertex = this.rand.nextLong();
            }
        }

        if (this.lightningState >= 0)
        {
            if (this.worldObj.isRemote)
            {
                this.worldObj.setLastLightningBolt(2);
            }
            else
            {
                double d0 = 3.0D;
                List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, new AxisAlignedBB(this.posX - d0, this.posY - d0, this.posZ - d0, this.posX + d0, this.posY + 6.0D + d0, this.posZ + d0));
            }
        }
    }

    protected void entityInit() {}

    protected void readEntityFromNBT(NBTTagCompound tagCompund) {}

    protected void writeEntityToNBT(NBTTagCompound tagCompound) {}
}