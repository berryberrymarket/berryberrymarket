package berryberrymarket;

import java.io.IOException;

public class ConsoleUtil {
	 public static void clearConsole() {
	 try {
         if (System.getProperty("os.name").contains("Windows")) {
             new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
         } else {
             new ProcessBuilder("clear").inheritIO().start().waitFor();
         }
     } catch (IOException | InterruptedException ex) {
         ex.printStackTrace();
     }
 }
}