package kurobot;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.activity.ActivityType;
import org.javacord.api.entity.intent.Intent;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

//import javax.swing.*;

public class Main {
    public static String version = "0.2.13";
    public static String osname = System.getProperty("os.name");

	public static void main(String[] args) throws IOException {
		
        if (args.length != 1) {
            System.err.println("This bot formerly required exactly one argument, "
                    + "Ensure that a token is set in the Constants file.\n"
                    + "If the argument is a relative file path, it is relative to the working directory");
        }

        System.out.println("KuroBot v"+version);
        System.out.println("Created by kuirivito");
        System.out.println("Running on "+osname);
        System.out.println("Initialising Listeners...");

        DiscordApiBuilder apiBuilder = new DiscordApiBuilder();

        // Token
        // TODO make this a lot easier via GUI at some point
        // ^ this will not happen for a LONG time ^
        if (args.length != 0) { // just checks whether or not there's an argument passed on runtime
	        Path tokenFile = Paths.get(args[0]);
	        if (Files.isRegularFile(tokenFile)) {
	            try (BufferedReader tokenFileReader = Files.newBufferedReader(tokenFile)) {
	                apiBuilder.setToken(tokenFileReader.readLine());
	            } catch (Exception e) {

                    System.err.println("Error: Something is very wrong."); //maybe clarify this at some point but it's funnier left this way

                }       
	        }
        } 
        else { // this is where the program will initialise from 99% of the time.
        	System.out.println("Running from Constants...");
            Constants constants = new Constants(); 
            apiBuilder.setToken(constants.getToken());
        }

        // Login
        DiscordApi api = apiBuilder
                .addIntents(Intent.MESSAGE_CONTENT)
                .login().join();
        System.out.println("Bot has connected to Discord.");

        //Set Activity
        api.updateActivity(ActivityType.WATCHING, "Prisma Illya");

        // Listeners (Cogs)
        api.addListener(new Ping());
        api.addListener(new Fun());
        /*
        api.addListener(new NSFW());
        api.addListener(new Administration());
        api.addListener(new Music());
        */ 
        System.out.println("Listeners initialised.");
	}	

}
