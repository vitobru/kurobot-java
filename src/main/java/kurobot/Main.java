package kurobot;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

//import javax.swing.*;

public class Main {
	public static List<Long> devs = new ArrayList<>();	
    public static String version = "0.1.0";
    public static String osname = System.getProperty("os.name");
    
	public static void main(String[] args) throws IOException {
		
		devs.add(261232036625252352L);
		devs.add(408372847652634624L);
		
        if (args.length != 1) {
            System.err.println("This bot formerly required exactly one argument, "
                    + "Ensure that a token is set in the Constants file.\n"
                    + "If the argument is a relative file path, it is relative to the working directory");
        }

        System.out.println("KuroBot v"+version);
        System.out.println("Created by lillie#1072");
        System.out.println("Running on "+osname);
        System.out.println("Initialising Listeners...");

        DiscordApiBuilder apiBuilder = new DiscordApiBuilder();

        // Token
        // TODO make this a lot easier via GUI at some point
        if (args.length != 0) {
	        Path tokenFile = Paths.get(args[0]);
	        if (Files.isRegularFile(tokenFile)) {
	            try (BufferedReader tokenFileReader = Files.newBufferedReader(tokenFile)) {
	                apiBuilder.setToken(tokenFileReader.readLine());
	            }	        
	        }
        } 
        else {
        	System.out.println("Running from Constants...");
            apiBuilder.setToken(Constants.token);
        }

        // Login
        DiscordApi api = apiBuilder
                .login().join();
        System.out.println("Bot has connected to Discord.");

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
