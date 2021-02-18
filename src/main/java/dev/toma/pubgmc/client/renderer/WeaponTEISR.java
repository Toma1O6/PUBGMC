package dev.toma.pubgmc.client.renderer;

import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.client.ClientEvents;
import dev.toma.pubgmc.client.models.ModelGun;
import dev.toma.pubgmc.common.capability.IPlayerData;
import dev.toma.pubgmc.common.items.guns.GunBase;
import dev.toma.pubgmc.client.models.weapons.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class WeaponTEISR extends TileEntityItemStackRenderer {
    //model
    public final ModelFlareGun flareGun = new ModelFlareGun();
    public final ModelP92 p92 = new ModelP92();
    public final ModelP1911 p1911 = new ModelP1911();
    public final ModelP18C p18c = new ModelP18C();
    public final ModelR45 r45 = new ModelR45();
    public final ModelR1895 r1895 = new ModelR1895();
    public final ModelScorpion scorpion = new ModelScorpion();
    public final ModelDeagle deagle = new ModelDeagle();
    public final ModelWin94 win94 = new ModelWin94();
    public final ModelSawedOff sawedOff = new ModelSawedOff();
    public final ModelS1897 s1897 = new ModelS1897();
    public final ModelS686 s686 = new ModelS686();
    public final ModelS12K s12k = new ModelS12K();
    public final ModelMicroUzi microuzi = new ModelMicroUzi();
    public final ModelUmp45 ump = new ModelUmp45();
    public final ModelTommyGun tommygun = new ModelTommyGun();
    public final ModelPP19Bizon bizon = new ModelPP19Bizon();
    public final ModelMP5K mp5k = new ModelMP5K();
    public final ModelVector vector = new ModelVector();
    public final ModelM16A4 m16a4 = new ModelM16A4();
    public final ModelM416 m416 = new ModelM416();
    public final ModelScarL scar = new ModelScarL();
    public final ModelG36C g36c = new ModelG36C();
    public final ModelQBZ qbz = new ModelQBZ();
    public final ModelAUG aug = new ModelAUG();
    public final ModelAKM akm = new ModelAKM();
    public final ModelBerylM762 m762 = new ModelBerylM762();
    public final ModelMK47Mutant mk47 = new ModelMK47Mutant();
    public final ModelGroza groza = new ModelGroza();
    public final ModelDP28 dp28 = new ModelDP28();
    public final ModelM249 m249 = new ModelM249();
    public final ModelVSS vss = new ModelVSS();
    public final ModelMini14 mini14 = new ModelMini14();
    public final ModelQBU qbu = new ModelQBU();
    public final ModelSKS sks = new ModelSKS();
    public final ModelSLR slr = new ModelSLR();
    public final ModelMK14 mk14 = new ModelMK14();
    public final ModelKar98K kar98k = new ModelKar98K();
    public final ModelM24 m24 = new ModelM24();
    public final ModelAWM awm = new ModelAWM();

    @Override
    public void renderByItem(ItemStack stack) {
        EntityPlayer player = Minecraft.getMinecraft().player;
        boolean flag0 = stack == player.getHeldItemMainhand();
        ModelGun gun = ((GunBase) stack.getItem()).getWeaponModel();
        this.bindTexture(gun.textureName());
        if(flag0 && Minecraft.getMinecraft().gameSettings.thirdPersonView == 0) this.applyRecoilAnimation(player);
        gun.render(stack);
    }

    private void applyRecoilAnimation(EntityPlayer player) {
        if(ClientEvents.recoilTicks > 0 && IPlayerData.PlayerData.get(player).isAiming()) {
            int i = 11 - ClientEvents.recoilTicks;
            GlStateManager.rotate(ClientEvents.recoilTicks / 9, 1f, 0f, 0f);
            GlStateManager.translate(0, 0.015 / i, 0.025 / i);
            return;
        }
        GlStateManager.rotate(ClientEvents.recoilTicks / 1.5F, 1, 0, 0);
        GlStateManager.translate(0, 0, 0.05 / (11 - ClientEvents.recoilTicks));
    }

    private double smallRandom(int i) {
        return Pubgmc.rng().nextDouble() / i - Pubgmc.rng().nextDouble() / i;
    }

    private void bindTexture(String name) {
        ResourceLocation rl = new ResourceLocation(Pubgmc.MOD_ID + ":textures/weapons/" + name + ".png");
        Minecraft.getMinecraft().getTextureManager().bindTexture(rl);
    }
}
