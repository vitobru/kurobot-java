/**
 * Constants used to help facilitate the operation of Kuro!
 * Keep these up-to-date and do not ever push the token to github, I will come after your family.
 */

package kurobot;

import java.awt.Color;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Constants {
    
	public static final Color PING_COLOR = new Color(156, 77, 201);
	
	public static String prefix = "$";
	
	private String token; //your token would go here normally

	private ArrayList<Long> devs = new ArrayList<Long>();

	public Constants() {

		Path tokenPath = Paths.get("token");
		if (Files.isRegularFile(tokenPath)) {
			try (BufferedReader tokenFileReader = Files.newBufferedReader(tokenPath)) {
				token = tokenFileReader.readLine();
			} catch (Exception e) {
				// Ideally if it cannot find a real path or the token is completely missing, this should throw. In practice, i'm not sure?
				System.out.println("Fatal error: "+e);
				System.out.println("Either the Token path is incorrect or token is missing.");
			}
		}

		devs.add(261232036625252352L);
		devs.add(408372847652634624L);

	}

	public String getToken() {
		return token;
	}

	public ArrayList<Long> getDevs() {
		return devs;
	}

}
