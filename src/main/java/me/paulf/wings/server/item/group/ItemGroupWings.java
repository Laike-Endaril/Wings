package me.paulf.wings.server.item.group;

import me.paulf.wings.WingsMod;
import me.paulf.wings.server.item.WingsItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public final class ItemGroupWings extends CreativeTabs
{
    private ItemGroupWings()
    {
        super(WingsMod.ID);
    }

    public static ItemGroupWings instance()
    {
        return Holder.INSTANCE;
    }

    @Override
    public ItemStack createIcon()
    {
        return new ItemStack(WingsItems.ANGEL_WINGS);
    }

    private static final class Holder
    {
        private static final ItemGroupWings INSTANCE = new ItemGroupWings();
    }
}
