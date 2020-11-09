package me.paulf.wings.server.asm;

import net.minecraft.entity.Entity;
import net.minecraftforge.fml.common.eventhandler.Event;

public final class GetCameraEyeHeightEvent extends Event
{
    private final Entity entity;

    private final float delta;

    private float value;

    private GetCameraEyeHeightEvent(Entity entity, float delta)
    {
        this.entity = entity;
        this.delta = delta;
    }

    public static GetCameraEyeHeightEvent create(Entity entity, float delta)
    {
        GetCameraEyeHeightEvent ev = new GetCameraEyeHeightEvent(entity, delta);
        ev.setValue(entity.getEyeHeight());
        return ev;
    }

    public Entity getEntity()
    {
        return entity;
    }

    public float getDelta()
    {
        return delta;
    }

    public float getValue()
    {
        return value;
    }

    public void setValue(float value)
    {
        this.value = value;
    }
}
