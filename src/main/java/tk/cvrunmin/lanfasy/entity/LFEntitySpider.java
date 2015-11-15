package tk.cvrunmin.lanfasy.entity;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNavigateClimber;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class LFEntitySpider extends EntityMob{
	public Object instance;
	public static int mobid = 1013;
	int entityID = mobid;

	public LFEntitySpider(World par1World) {
		super(par1World);
        this.setSize(1.4F, 0.9F);
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, this.aiAvoidExplodingCreepers);
        this.tasks.addTask(3, new EntityAILeapAtTarget(this, 0.4F));
        this.tasks.addTask(4, new LFEntitySpider.AISpiderAttack(EntityPlayer.class));
        this.tasks.addTask(4, new LFEntitySpider.AISpiderAttack(EntityIronGolem.class));
        this.tasks.addTask(5, new EntityAIWander(this, 0.8D));
        this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(6, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false, new Class[0]));
        this.targetTasks.addTask(2, new LFEntitySpider.AISpiderTarget(EntityPlayer.class));
        this.targetTasks.addTask(3, new LFEntitySpider.AISpiderTarget(EntityIronGolem.class));
	}
    protected PathNavigate func_175447_b(World worldIn)
    {
        return new PathNavigateClimber(this, worldIn);
    }

    protected void entityInit()
    {
        super.entityInit();
        this.dataWatcher.addObject(16, new Byte((byte)0));
    }

    public void onUpdate()
    {
        super.onUpdate();

        if (!this.worldObj.isRemote)
        {
            this.setBesideClimbableBlock(this.isCollidedHorizontally);
        }
    }
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(16.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.30000001192092896D);
    }
    protected String getLivingSound()
    {
        return "mob.spider.say";
    }

    protected String getHurtSound()
    {
        return "mob.spider.say";
    }

    protected String getDeathSound()
    {
        return "mob.spider.death";
    }

    protected void func_145780_a(int p_145780_1_, int p_145780_2_, int p_145780_3_, Block p_145780_4_)
    {
        this.playSound("mob.spider.step", 0.15F, 1.0F);
    }
    public boolean isOnLadder()
    {
        return this.isBesideClimbableBlock();
    }

    public void setInWeb() {}

    public EnumCreatureAttribute getCreatureAttribute()
    {
        return EnumCreatureAttribute.ARTHROPOD;
    }

    public boolean isPotionApplicable(PotionEffect p_70687_1_)
    {
        return p_70687_1_.getPotionID() == Potion.poison.id ? false : super.isPotionApplicable(p_70687_1_);
    }

    public boolean isBesideClimbableBlock()
    {
        return (this.dataWatcher.getWatchableObjectByte(16) & 1) != 0;
    }

    public void setBesideClimbableBlock(boolean p_70839_1_)
    {
        byte b0 = this.dataWatcher.getWatchableObjectByte(16);

        if (p_70839_1_)
        {
            b0 = (byte)(b0 | 1);
        }
        else
        {
            b0 &= -2;
        }

        this.dataWatcher.updateObject(16, Byte.valueOf(b0));
    }

    public IEntityLivingData onInitialSpawn(DifficultyInstance p_180482_1_, IEntityLivingData p_180482_2_)
    {
        Object p_180482_2_1 = super.onInitialSpawn(p_180482_1_, p_180482_2_);

        if (this.worldObj.rand.nextInt(100) == 0)
        {
            LFEntitySkeleton entityskeleton = new LFEntitySkeleton(this.worldObj);
            entityskeleton.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0F);
            entityskeleton.onInitialSpawn(p_180482_1_, (IEntityLivingData)null);
            this.worldObj.spawnEntityInWorld(entityskeleton);
            entityskeleton.mountEntity(this);
        }

        if (p_180482_2_1 == null)
        {
            p_180482_2_1 = new LFEntitySpider.GroupData();

            if (this.worldObj.getDifficulty() == EnumDifficulty.HARD && this.worldObj.rand.nextFloat() < 0.1F * p_180482_1_.getClampedAdditionalDifficulty())
            {
                ((LFEntitySpider.GroupData)p_180482_2_1).func_111104_a(this.worldObj.rand);
            }
        }

        if (p_180482_2_1 instanceof LFEntitySpider.GroupData)
        {
            int i = ((LFEntitySpider.GroupData)p_180482_2_1).field_111105_a;

            if (i > 0 && Potion.potionTypes[i] != null)
            {
                this.addPotionEffect(new PotionEffect(i, Integer.MAX_VALUE));
            }
        }

        return (IEntityLivingData)p_180482_2_1;
    }

    public float getEyeHeight()
    {
        return 0.65F;
    }
    protected Item getDropItem()
    {
//        return MLRItems.rString;
    	return null;
    }
    protected void dropFewItems(boolean par1, int par2)
    {
        super.dropFewItems(par1, par2);

        if (par1 && (this.rand.nextInt(3) == 0 || this.rand.nextInt(1 + par2) > 0))
        {
//            this.dropItem(MLRItems.rSpider_eye, 1);
        }
    }
    class AISpiderAttack extends EntityAIAttackOnCollide
    {
        public AISpiderAttack(Class p_i45819_2_)
        {
            super(LFEntitySpider.this, p_i45819_2_, 1.0D, true);
        }

        public boolean continueExecuting()
        {
            float f = this.attacker.getBrightness(1.0F);

            if (f >= 0.5F && this.attacker.getRNG().nextInt(100) == 0)
            {
                this.attacker.setAttackTarget((EntityLivingBase)null);
                return false;
            }
            else
            {
                return super.continueExecuting();
            }
        }

        protected double func_179512_a(EntityLivingBase p_179512_1_)
        {
            return (double)(4.0F + p_179512_1_.width);
        }
    }

    class AISpiderTarget extends EntityAINearestAttackableTarget
    {

        public AISpiderTarget(Class p_i45818_2_)
        {
            super(LFEntitySpider.this, p_i45818_2_, true);
        }

        public boolean shouldExecute()
        {
            float f = this.taskOwner.getBrightness(1.0F);
            return f >= 0.5F ? false : super.shouldExecute();
        }
    }
    public static class GroupData implements IEntityLivingData
    {
        public int field_111105_a;

        public void func_111104_a(Random p_111104_1_)
        {
            int i = p_111104_1_.nextInt(5);

            if (i <= 1)
            {
                this.field_111105_a = Potion.moveSpeed.id;
            }
            else if (i <= 2)
            {
                this.field_111105_a = Potion.damageBoost.id;
            }
            else if (i <= 3)
            {
                this.field_111105_a = Potion.regeneration.id;
            }
            else if (i <= 4)
            {
                this.field_111105_a = Potion.invisibility.id;
            }
        }
    }
}
