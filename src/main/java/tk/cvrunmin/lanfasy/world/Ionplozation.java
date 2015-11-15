package tk.cvrunmin.lanfasy.world;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentProtection;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import tk.cvrunmin.lanfasy.entity.effect.EntityLightBolt;
import tk.cvrunmin.lanfasy.util.DamageSourceLF;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

public class Ionplozation extends Explosion{
    private final boolean isSmoking;
    private final Random explosionRNG;
    private final World worldObj;
    private final double explosionX;
    private final double explosionY;
    private final double explosionZ;
    private final Entity exploder;
    private final float explosionSize;
    private final List affectedBlockPositions;
    private final Map field_77288_k;
    private final Vec3 position;
    public Ionplozation(World worldIn, Entity p_i45754_2_, double p_i45754_3_, double p_i45754_5_, double p_i45754_7_, float p_i45754_9_, boolean p_i45754_10_, boolean p_i45754_11_)
    {
        super(worldIn, p_i45754_2_, p_i45754_3_, p_i45754_5_, p_i45754_7_, p_i45754_9_, p_i45754_10_, p_i45754_11_);
        this.explosionRNG = new Random();
        this.affectedBlockPositions = Lists.newArrayList();
        this.field_77288_k = Maps.newHashMap();
        this.worldObj = worldIn;
        this.exploder = p_i45754_2_;
        this.explosionSize = p_i45754_9_;
        this.explosionX = p_i45754_3_;
        this.explosionY = p_i45754_5_;
        this.explosionZ = p_i45754_7_;
        this.isSmoking = p_i45754_11_;
        this.position = new Vec3(explosionX, explosionY, explosionZ);
    }
    @Override
    public void doExplosionA()
    {
        HashSet hashset = Sets.newHashSet();
        int j;
        int k;

        for (int i = 0; i < 16; ++i)
        {
            for (j = 0; j < 16; ++j)
            {
                for (k = 0; k < 16; ++k)
                {
                    if (i == 0 || i == 15 || j == 0 || j == 15 || k == 0 || k == 15)
                    {
                        double d0 = (double)((float)i / 15.0F * 2.0F - 1.0F);
                        double d1 = (double)((float)j / 15.0F * 2.0F - 1.0F);
                        double d2 = (double)((float)k / 15.0F * 2.0F - 1.0F);
                        double d3 = Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
                        d0 /= d3;
                        d1 /= d3;
                        d2 /= d3;
                        float f = this.explosionSize * (0.7F + this.worldObj.rand.nextFloat() * 0.6F);
                        double d4 = this.explosionX;
                        double d6 = this.explosionY;
                        double d8 = this.explosionZ;

                        for (; f > 0.0F; f -= 0.22500001F)
                        {
                            BlockPos blockpos = new BlockPos(d4, d6, d8);
                            this.worldObj.getBlockState(blockpos);
                                hashset.add(blockpos);

                            d4 += d0 * 0.30000001192092896D;
                            d6 += d1 * 0.30000001192092896D;
                            d8 += d2 * 0.30000001192092896D;
                        }
                    }
                }
            }
        }

        this.affectedBlockPositions.addAll(hashset);
        float f3 = this.explosionSize * 2.0F;
        j = MathHelper.floor_double(this.explosionX - (double)f3 - 1.0D);
        k = MathHelper.floor_double(this.explosionX + (double)f3 + 1.0D);
        int j1 = MathHelper.floor_double(this.explosionY - (double)f3 - 1.0D);
        int l = MathHelper.floor_double(this.explosionY + (double)f3 + 1.0D);
        int k1 = MathHelper.floor_double(this.explosionZ - (double)f3 - 1.0D);
        int i1 = MathHelper.floor_double(this.explosionZ + (double)f3 + 1.0D);
        List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this.exploder, new AxisAlignedBB((double)j, (double)j1, (double)k1, (double)k, (double)l, (double)i1));
        net.minecraftforge.event.ForgeEventFactory.onExplosionDetonate(this.worldObj, this, list, f3);
        Vec3 vec3 = new Vec3(this.explosionX, this.explosionY, this.explosionZ);

        for (int l1 = 0; l1 < list.size(); ++l1)
        {
            Entity entity = (Entity)list.get(l1);

            if (!entity.isImmuneToExplosions())
            {
                double d12 = entity.getDistance(this.explosionX, this.explosionY, this.explosionZ) / (double)f3;

                if (d12 <= 1.0D)
                {
                    double d5 = entity.posX - this.explosionX;
                    double d7 = entity.posY + (double)entity.getEyeHeight() - this.explosionY;
                    double d9 = entity.posZ - this.explosionZ;
                    double d13 = (double)MathHelper.sqrt_double(d5 * d5 + d7 * d7 + d9 * d9);

                    if (d13 != 0.0D)
                    {
                        d5 /= d13;
                        d7 /= d13;
                        d9 /= d13;
                        double d14 = (double)this.worldObj.getBlockDensity(vec3, entity.getEntityBoundingBox());
                        double d10 = (1.0D - d12) * d14;
                        entity.attackEntityFrom(DamageSourceLF.setIonplozationSource(this), Float.MAX_VALUE);
                        double d11 = EnchantmentProtection.func_92092_a(entity, d10);
                        entity.motionX += d5 * d11;
                        entity.motionY += d7 * d11;
                        entity.motionZ += d9 * d11;

/*                        if (entity instanceof EntityPlayer)
                        {
                            this.field_77288_k.put((EntityPlayer)entity, new Vec3(d5 * d10, d7 * d10, d9 * d10));
                        }*/
                    }
                }
            }
        }
    }
    public void doExplosionB(boolean p_77279_1_)
    {
        this.worldObj.playSoundEffect(this.explosionX, this.explosionY, this.explosionZ, "random.explode", 4.0F, (1.0F + (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.2F) * 0.7F);
        Iterator iterator;
        BlockPos blockpos;

        if (this.isSmoking)
        {
            iterator = this.affectedBlockPositions.iterator();

            while (iterator.hasNext())
            {
                blockpos = (BlockPos)iterator.next();
                Block block = this.worldObj.getBlockState(blockpos).getBlock();

                if (p_77279_1_)
                {
                    double d0 = (double)((float)blockpos.getX() + this.worldObj.rand.nextFloat());
                    double d1 = (double)((float)blockpos.getY() + this.worldObj.rand.nextFloat());
                    double d2 = (double)((float)blockpos.getZ() + this.worldObj.rand.nextFloat());
                    double d3 = d0 - this.explosionX;
                    double d4 = d1 - this.explosionY;
                    double d5 = d2 - this.explosionZ;
                    double d6 = (double)MathHelper.sqrt_double(d3 * d3 + d4 * d4 + d5 * d5);
                    d3 /= d6;
                    d4 /= d6;
                    d5 /= d6;
                    double d7 = 0.5D / (d6 / (double)this.explosionSize + 0.1D);
                    d7 *= (double)(this.worldObj.rand.nextFloat() * this.worldObj.rand.nextFloat() + 0.3F);
                    d3 *= d7;
                    d4 *= d7;
                    d5 *= d7;
                }
                    worldObj.addWeatherEffect(new EntityLightBolt(worldObj, blockpos.getX(), blockpos.getY(), blockpos.getZ()));
                    block.onBlockExploded(this.worldObj, blockpos, this);
            }
        }
    }
    public void doExplosionC()
    {
        HashSet hashset = Sets.newHashSet();
        int j;
        int k;

        for (int i = 0; i < 16; ++i)
        {
            for (j = 0; j < 16; ++j)
            {
                for (k = 0; k < 16; ++k)
                {
                    if (i == 0 || i == 15 || j == 0 || j == 15 || k == 0 || k == 15)
                    {
                        double d0 = (double)((float)i / 15.0F * 2.0F - 1.0F);
                        double d1 = (double)((float)j / 15.0F * 2.0F - 1.0F);
                        double d2 = (double)((float)k / 15.0F * 2.0F - 1.0F);
                        double d3 = Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
                        d0 /= d3;
                        d1 /= d3;
                        d2 /= d3;
                        float f = this.explosionSize * (0.7F + this.worldObj.rand.nextFloat() * 0.6F);
                        double d4 = this.explosionX;
                        double d6 = this.explosionY;
                        double d8 = this.explosionZ;

                        for (; f > 0.0F; f -= 0.22500001F)
                        {
                            BlockPos blockpos = new BlockPos(d4, d6, d8);
                            this.worldObj.getBlockState(blockpos);
                                hashset.add(blockpos);

                            d4 += d0 * 0.30000001192092896D;
                            d6 += d1 * 0.30000001192092896D;
                            d8 += d2 * 0.30000001192092896D;
                        }
                    }
                }
            }
        }

        this.affectedBlockPositions.addAll(hashset);
        float f3 = this.explosionSize * 2.0F;
        j = MathHelper.floor_double(this.explosionX - (double)f3 - 1.0D);
        k = MathHelper.floor_double(this.explosionX + (double)f3 + 1.0D);
        int j1 = MathHelper.floor_double(this.explosionY - (double)f3 - 1.0D);
        int l = MathHelper.floor_double(this.explosionY + (double)f3 + 1.0D);
        int k1 = MathHelper.floor_double(this.explosionZ - (double)f3 - 1.0D);
        int i1 = MathHelper.floor_double(this.explosionZ + (double)f3 + 1.0D);
        List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this.exploder, new AxisAlignedBB((double)j, (double)j1, (double)k1, (double)k, (double)l, (double)i1));
        net.minecraftforge.event.ForgeEventFactory.onExplosionDetonate(this.worldObj, this, list, f3);
        Vec3 vec3 = new Vec3(this.explosionX, this.explosionY, this.explosionZ);

        for (int l1 = 0; l1 < list.size(); ++l1)
        {
            Entity entity = (Entity)list.get(l1);

            if (!entity.isImmuneToExplosions())
            {
                double d12 = entity.getDistance(this.explosionX, this.explosionY, this.explosionZ) / (double)f3;

                if (d12 <= 1.0D)
                {
                    double d5 = entity.posX - this.explosionX;
                    double d7 = entity.posY + (double)entity.getEyeHeight() - this.explosionY;
                    double d9 = entity.posZ - this.explosionZ;
                    double d13 = (double)MathHelper.sqrt_double(d5 * d5 + d7 * d7 + d9 * d9);

                    if (d13 != 0.0D)
                    {
                        d5 /= d13;
                        d7 /= d13;
                        d9 /= d13;
                        double d14 = (double)this.worldObj.getBlockDensity(vec3, entity.getEntityBoundingBox());
                        double d10 = (1.0D - d12) * d14;
                        entity.attackEntityFrom(DamageSourceLF.setIonplozationSource(this), Float.MAX_VALUE);
                        double d11 = EnchantmentProtection.func_92092_a(entity, d10);
                        entity.motionX += d5 * d11;
                        entity.motionY += d7 * d11;
                        entity.motionZ += d9 * d11;

/*                        if (entity instanceof EntityPlayer)
                        {
                            this.field_77288_k.put((EntityPlayer)entity, new Vec3(d5 * d10, d7 * d10, d9 * d10));
                        }*/
                    }
                }
            }
        }
    }
    public void doExplosionD(boolean p_77279_1_)
    {
        this.worldObj.playSoundEffect(this.explosionX, this.explosionY, this.explosionZ, "random.explode", 4.0F, (1.0F + (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.2F) * 0.7F);
        Iterator iterator;
        BlockPos blockpos;

        if (this.isSmoking)
        {
            iterator = this.affectedBlockPositions.iterator();

            while (iterator.hasNext())
            {
                blockpos = (BlockPos)iterator.next();
                Block block = this.worldObj.getBlockState(blockpos).getBlock();

                if (p_77279_1_)
                {
                    double d0 = (double)((float)blockpos.getX() + this.worldObj.rand.nextFloat());
                    double d1 = (double)((float)blockpos.getY() + this.worldObj.rand.nextFloat());
                    double d2 = (double)((float)blockpos.getZ() + this.worldObj.rand.nextFloat());
                    double d3 = d0 - this.explosionX;
                    double d4 = d1 - this.explosionY;
                    double d5 = d2 - this.explosionZ;
                    double d6 = (double)MathHelper.sqrt_double(d3 * d3 + d4 * d4 + d5 * d5);
                    d3 /= d6;
                    d4 /= d6;
                    d5 /= d6;
                    double d7 = 0.5D / (d6 / (double)this.explosionSize + 0.1D);
                    d7 *= (double)(this.worldObj.rand.nextFloat() * this.worldObj.rand.nextFloat() + 0.3F);
                    d3 *= d7;
                    d4 *= d7;
                    d5 *= d7;
                }
                    block.onBlockExploded(this.worldObj, blockpos, this);
            }
        }
    }
}
