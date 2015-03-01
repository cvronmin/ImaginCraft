package tk.cvrunmin.lanfasy.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.util.MathHelper;
import tk.cvrunmin.lanfasy.entity.LFEntityZombieBoss;

public class ModelZombieBoss extends ModelBase
{
  //fields
    ModelRenderer Left;
    ModelRenderer Right;
    ModelRenderer RightArm;
    ModelRenderer Body;
    ModelRenderer LeftArm;
    ModelRenderer Head;
    ModelRenderer head2;
    ModelRenderer head3;
    ModelRenderer l1;
    ModelRenderer l2;
    ModelRenderer l3;
    ModelRenderer l4;
    ModelRenderer[] tail;
  
  public ModelZombieBoss()
  {
    textureWidth = 64;
    textureHeight = 64;
    
      Left = new ModelRenderer(this, 0, 16);
      Left.addBox(-2F, 0F, -2F, 4, 12, 4);
      Left.setRotationPoint(2F, 12F, 0F);
      Left.setTextureSize(64, 64);
      Left.mirror = true;
      setRotation(Left, 0F, 0F, 0F);
      Right = new ModelRenderer(this, 0, 16);
      Right.addBox(-2F, 0F, -2F, 4, 12, 4);
      Right.setRotationPoint(-2F, 12F, 0F);
      Right.setTextureSize(64, 64);
      setRotation(Right, 0F, 0F, 0F);
      RightArm = new ModelRenderer(this, 40, 16);
      RightArm.addBox(-4F, -2F, -2F, 4, 12, 4);
      RightArm.setRotationPoint(-4F, 2F, 0F);
      RightArm.setTextureSize(64, 64);
      setRotation(RightArm, -1.570796F, 0F, 0F);
      Body = new ModelRenderer(this, 16, 16);
      Body.addBox(-4F, -4F, -2F, 8, 12, 4);
      Body.setRotationPoint(0F, 4F, 0F);
      Body.setTextureSize(64, 64);
      setRotation(Body, 0F, 0F, 0F);
      LeftArm = new ModelRenderer(this, 40, 16);
      LeftArm.addBox(0F, -2F, -2F, 4, 12, 4);
      LeftArm.setRotationPoint(4F, 2F, 0F);
      LeftArm.setTextureSize(64, 64);
      LeftArm.mirror = true;
      setRotation(LeftArm, -1.570796F, 0F, 0F);
      Head = new ModelRenderer(this, 0, 0);
      Head.addBox(-4F, -4F, -4F, 8, 8, 8);
      Head.setRotationPoint(0F, -4F, 0F);
      Head.setTextureSize(64, 64);
      setRotation(Head, 0F, 0F, 0F);
      head2 = new ModelRenderer(this, 32, 0);
      head2.addBox(-2F, -2F, -2F, 4, 4, 4);
      head2.setRotationPoint(-6F, -2F, -8F);
      head2.setTextureSize(64, 64);
      setRotation(head2, 0F, 0F, 0F);
      head3 = new ModelRenderer(this, 32, 0);
      head3.addBox(-2F, -2F, -2F, 4, 4, 4);
      head3.setRotationPoint(6F, -2F, -8F);
      head3.setTextureSize(64, 64);
      head3.mirror = true;
      setRotation(head3, 0F, 0F, 0F);
      tail = new ModelRenderer[4];
      tail[0] = new ModelRenderer(this, 0, 32);
      l1 = new ModelRenderer(this, 0, 32);
      tail[0].addBox(-2F, 0F, -2F, 4, 8, 4);
      tail[0].setRotationPoint(0F, 8F, 1F);
      tail[0].setTextureSize(64, 64);
      setRotation(tail[0], 2.356194F, 0F, 0F);
/*      l1.addBox(-2F, 0F, -2F, 4, 8, 4);
      l1.setRotationPoint(0F, 8F, 1F);
      l1.setTextureSize(64, 64);
      setRotation(l1, 2.356194F, 0F, 0F);*/
      tail[1] = new ModelRenderer(this, 16, 32);
      tail[1].addBox(-1.5F, 0F, -1.5F, 3, 8, 3);
      tail[1].setRotationPoint(0F, 3F, 6.5F);
      tail[1].setTextureSize(64, 64);
      setRotation(tail[1], 2.748893F, 0F, 0F);
/*      l2 = new ModelRenderer(this, 16, 32);
      l2.addBox(-1.5F, 0F, -1.5F, 3, 8, 3);
      l2.setRotationPoint(0F, 3F, 6.5F);
      l2.setTextureSize(64, 64);
      setRotation(l2, 2.748893F, 0F, 0F);*/
      tail[2] = new ModelRenderer(this, 28, 32);
      tail[2].addBox(-1F, 0F, -1F, 2, 8, 2);
      tail[2].setRotationPoint(0F, -4F, 9.5F);
      tail[2].setTextureSize(64, 64);
      setRotation(tail[2], 3.141593F, 0F, 0F);
/*      l3 = new ModelRenderer(this, 28, 32);
      l3.addBox(-1F, 0F, -1F, 2, 8, 2);
      l3.setRotationPoint(0F, -4F, 9.5F);
      l3.setTextureSize(64, 64);
      setRotation(l3, 3.141593F, 0F, 0F);*/
      tail[3] = new ModelRenderer(this, 36, 32);
      tail[3].addBox(-0.5F, 0F, -0.5F, 1, 8, 1);
      tail[3].setRotationPoint(0F, -11F, 10F);
      tail[3].setTextureSize(64, 64);
      setRotation(tail[3], -2.748893F, 0F, 0F);
/*      l4 = new ModelRenderer(this, 36, 32);
      l4.addBox(-0.5F, 0F, -0.5F, 1, 8, 1);
      l4.setRotationPoint(0F, -11F, 10F);
      l4.setTextureSize(64, 64);
      setRotation(l4, -2.748893F, 0F, 0F);*/
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    Left.render(f5);
    Right.render(f5);
    RightArm.render(f5);
    Body.render(f5);
    LeftArm.render(f5);
    Head.render(f5);
    head2.render(f5);
    head3.render(f5);
/*    l1.render(f5);
    l2.render(f5);
    l3.render(f5);
    l4.render(f5);*/
    ModelRenderer[] amodelrenderer = this.tail;
    int i = amodelrenderer.length;
    int j;
    ModelRenderer modelrenderer;

    for (j = 0; j < i; ++j)
    {
        modelrenderer = amodelrenderer[j];
        modelrenderer.render(f5);
    }
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity6)
  {
    super.setRotationAngles(f, f1, f2, f3, f4, f5, entity6);
    float f6 = MathHelper.sin(this.swingProgress * (float)Math.PI);
    float f7 = MathHelper.sin((1.0F - (1.0F - this.swingProgress) * (1.0F - this.swingProgress)) * (float)Math.PI);
    this.Right.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
    this.Left.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 1.4F * f1;
    this.Right.rotateAngleY = 0.0F;
    this.Left.rotateAngleY = 0.0F;
    this.RightArm.rotateAngleZ = 0.0F;
    this.LeftArm.rotateAngleZ = 0.0F;
    this.RightArm.rotateAngleY = -(0.1F - f6 * 0.6F);
    this.LeftArm.rotateAngleY = 0.1F - f6 * 0.6F;
    this.RightArm.rotateAngleX = -((float)Math.PI / 2F);
    this.LeftArm.rotateAngleX = -((float)Math.PI / 2F);
    this.RightArm.rotateAngleX -= f6 * 1.2F - f7 * 0.4F;
    this.LeftArm.rotateAngleX -= f6 * 1.2F - f7 * 0.4F;
    this.RightArm.rotateAngleZ += MathHelper.cos(f2 * 0.09F) * 0.05F + 0.05F;
    this.LeftArm.rotateAngleZ -= MathHelper.cos(f2 * 0.09F) * 0.05F + 0.05F;
    this.RightArm.rotateAngleX += MathHelper.sin(f2 * 0.067F) * 0.05F;
    this.LeftArm.rotateAngleX -= MathHelper.sin(f2 * 0.067F) * 0.05F;
    this.head2.rotateAngleZ = this.RightArm.rotateAngleZ;
    this.head3.rotateAngleZ = this.LeftArm.rotateAngleZ;
    this.head2.rotateAngleY = this.RightArm.rotateAngleY;
    this.head3.rotateAngleY = this.LeftArm.rotateAngleY;
    this.head2.rotateAngleX = this.RightArm.rotateAngleX;
    this.head3.rotateAngleX = this.LeftArm.rotateAngleX;

    //--------------
/*    this.l1.rotateAngleZ += MathHelper.cos(f2 * 0.09F) * 0.05F + 0.05F;
    this.l2.rotateAngleZ += MathHelper.cos(f2 * 0.09F) * 0.05F + 0.05F;
    this.l3.rotateAngleZ += MathHelper.cos(f2 * 0.09F) * 0.05F + 0.05F;
    this.l4.rotateAngleZ += MathHelper.cos(f2 * 0.09F) * 0.05F + 0.05F;
    this.l1.rotateAngleX += MathHelper.sin(f2 * 0.067F) * 0.05F;
    this.l2.rotateAngleX += MathHelper.sin(f2 * 0.067F) * 0.05F;
    this.l3.rotateAngleX += MathHelper.sin(f2 * 0.067F) * 0.05F;
    this.l4.rotateAngleX += MathHelper.sin(f2 * 0.067F) * 0.05F;*/
//    this.tail[0].rotateAngleX = MathHelper.sin(f2 * 0.067F) * 0.05F;
  }
}
