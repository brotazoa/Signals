package com.minemaarten.signals.item;

import java.util.List;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.minemaarten.signals.Signals;
import com.minemaarten.signals.client.CreativeTabSignals;
import com.minemaarten.signals.lib.SignalsUtils;

public class ItemSignals extends Item{
    public ItemSignals(String name){
        setUnlocalizedName(name);
        setRegistryName(name);
        GameRegistry.register(this);
        setCreativeTab(CreativeTabSignals.getInstance());
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List<String> curInfo, boolean extraInfo){
        super.addInformation(stack, player, curInfo, extraInfo);
        addTooltip(stack, player, curInfo);
    }

    public static void addTooltip(ItemStack stack, EntityPlayer player, List<String> curInfo){
        String info = "signals.tooltip." + stack.getItem().getUnlocalizedName();
        String translatedInfo = I18n.format(info);
        if(!translatedInfo.equals(info)) {
            if(Signals.proxy.isSneakingInGui()) {
                translatedInfo = TextFormatting.AQUA + translatedInfo;
                if(!Loader.isModLoaded("IGWMod")) translatedInfo += " \\n \\n" + I18n.format("signals.tooltip.assistIGW");
                curInfo.addAll(SignalsUtils.convertStringIntoList(translatedInfo, 60));
            } else {
                curInfo.add(TextFormatting.AQUA + I18n.format("signals.gui.tooltip.sneakForInfo"));
            }
        }
    }
}
