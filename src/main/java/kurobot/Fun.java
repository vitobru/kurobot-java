/*
 * This will house the majority of Kuro's main functions
 * This is the first major priority for updating
 */
package kurobot;

import java.util.ArrayList;
import org.javacord.api.listener.message.MessageCreateListener;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.*;

public class Fun implements MessageCreateListener {

	@Override
    public void onMessageCreate(MessageCreateEvent event) {
		// TODO literally all of the fucking commands lmao

		if (event.getMessageContent().equalsIgnoreCase(Constants.prefix+"about")) {
			
			DiscordApi api = event.getApi();
			
			EmbedBuilder embed = new EmbedBuilder()
					.setFooter("KuroBot")
					.setTitle("KuroBot v"+Main.version)
					.setThumbnail(api.getYourself().getAvatar())
					.setDescription("The successor to the Discord bot written in Discord.py.")
					.addField("Send her your love!", ":heart:")
					.addField("Github", "https://github.com/vitobru/kurobot-java");
					
			event.getChannel().sendMessage(embed);
		}

		if (event.getMessageContent().equalsIgnoreCase(Constants.prefix+"prefix")) {
			event.getChannel().sendMessage("The current prefix is: "+Constants.prefix);
		}
		
		if (event.getMessageContent().equalsIgnoreCase(Constants.prefix+"exit")) {
			long authorID = event.getMessage().getAuthor().getId();
			Constants constants = new Constants();
			ArrayList<Long> devs = constants.getDevs();
			if (devs.contains(authorID)) {
				event.getChannel().sendMessage("Yes, master. I'll go to bed, now.");
				System.out.println("Bot has disconnected from Discord.");
				System.exit(1);
			}
			else {
				event.getChannel().sendMessage("You're not my master, you can't just shut me off!");
			}
		}
	}
}
