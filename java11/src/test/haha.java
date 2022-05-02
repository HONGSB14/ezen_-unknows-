package test;

import java.util.Scanner;

public class haha {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("ÀÔ·Â°¹¼ö >");
		int l = scanner.nextInt();
		
		for(int i = 1; i<=l; i++) {
			for(int k=1; k<=l-i; k++) {
				System.out.print(" ");
			}
			for(int s=1; s<=i*2-1; s++) {
				System.out.print("*");
			}
			System.out.println();
		}
		
		
		
		
	}
}
