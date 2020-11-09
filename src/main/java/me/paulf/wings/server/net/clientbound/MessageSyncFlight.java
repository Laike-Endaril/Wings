package me.paulf.wings.server.net.clientbound;

import me.paulf.wings.server.flight.Flight;
import me.paulf.wings.server.flight.FlightDefault;
import me.paulf.wings.server.flight.Flights;
import me.paulf.wings.server.net.Message;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public final class MessageSyncFlight extends Message
{
    private final Flight flight;
    private int playerId;

    public MessageSyncFlight()
    {
        this(0, new FlightDefault());
    }

    public MessageSyncFlight(EntityPlayer player, Flight flight)
    {
        this(player.getEntityId(), flight);
    }

    private MessageSyncFlight(int playerId, Flight flight)
    {
        this.playerId = playerId;
        this.flight = flight;
    }

    @Override
    protected void serialize(PacketBuffer buf)
    {
        buf.writeVarInt(playerId);
        flight.serialize(buf);
    }

    @Override
    protected void deserialize(PacketBuffer buf)
    {
        playerId = buf.readVarInt();
        flight.deserialize(buf);
    }

    @Override
    protected void process(MessageContext ctx)
    {
        Flights.ifPlayer(FMLClientHandler.instance().getWorldClient().getEntityByID(playerId),
                (player, flight) -> flight.clone(this.flight)
        );
    }
}
