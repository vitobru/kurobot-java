package kurobot;

import org.javacord.api.listener.message.MessageCreateListener;
import org.javacord.api.entity.message.*;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;

public class Ping implements MessageCreateListener{
    @Override
    public void onMessageCreate(MessageCreateEvent event) {

        if (event.getMessageContent().equalsIgnoreCase(Constants.prefix+"ping")) {

            EmbedBuilder embed = new EmbedBuilder()
                    .setTitle("Pong!")
                    .setDescription("in Null ms") // placeholder
                    .setColor(Constants.PING_COLOR);

            double result_ms=0.0;
            long millisBefore = System.currentTimeMillis();
            Message message = event.getChannel().sendMessage(embed).join();
            long millisAfter = System.currentTimeMillis();
            result_ms=millisAfter-millisBefore;
            message.edit(embed.setDescription("in "+result_ms+"ms")).join();
            
        }
    }
}