package berryberrymarket;

import java.util.Scanner;

public class PtintTest_suhyeon {


		    public static void main(String[] args) throws InterruptedException {
		    	System.out.println("ㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱ");
		    	Scanner sc = new Scanner(System.in);
		    	String[] frames = {
		            """
		            o
		            |
		           / \\
		            """,
		            """
		            o
		           /|
		           / \\
		            """,
		            """
		            o
		           /|\\
		           / \\
		            """
		        };

		        while (true) {
		            for (String frame : frames) {
		                System.out.print("\033[H\033[2J"); // 콘솔 화면 지우기
		                System.out.flush();
		                System.out.println(frame);
		                Thread.sleep(10); // 0.5초 대기
		            }
		        }
		    }
}
