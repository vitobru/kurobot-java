package kurobot;

import org.javacord.api.listener.message.MessageCreateListener;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.*;

public class Fun implements MessageCreateListener {
	@Override
    public void onMessageCreate(MessageCreateEvent event) {
		// TODO literally all of the fucking commands lmao
		
		if (event.getMessageContent().equals(Constants.prefix+"about")) {
			
			DiscordApi api = event.getApi();
			
			EmbedBuilder embed = new EmbedBuilder()
					.setFooter("KuroBot")
					.setTitle("KuroBot v"+Main.version)
					.setThumbnail(api.getYourself().getAvatar())
					.setDescription("The successor to the Discord bot written in Discord.py.")
					.addField("Send her your love!", ":heart:")
					.addField("Github", "https://placeholder.address/");
					
			event.getChannel().sendMessage(embed);
		}
		
		if (event.getMessageContent().equals(Constants.prefix+"exit")) {
			long authorID = event.getMessage().getAuthor().getId();
			if (Main.devs.contains(authorID)) {
				System.exit(1);
			}
			else {
				event.getChannel().sendMessage("You're not my master, you can't just shut me off!");
			}
		}
	}
}
