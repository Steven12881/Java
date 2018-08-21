package hangmangame;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Hangman{

    //private static String[] words = {"terminator", "banana", "computer", "cow", "rain", "water" };
    //private static String[] words = { "program" };

    public static void main(String[] args) throws Exception {
	Scanner sc = new Scanner(System.in);
	int miss = 0;
	char guess = 'y';
	boolean isAlreadyFound = false;
	boolean isNotfound = true;

	FileReader fr = new FileReader(Paths.get("").toAbsolutePath().toString() + File.separator + "words.txt");
	BufferedReader br = new BufferedReader(fr);
	String s = br.readLine();
	while (s != null && guess == 'y') {
	    //System.out.println(s);
	    String[] words = s.split(" ");
	    //Random shuffling the list
	    ArrayList<Integer> list = new ArrayList<Integer>();
	    for (int i = 0; i < words.length; i++) {
		list.add(new Integer(i));
	    }
	    Collections.shuffle(list);

	    for (int windex = 0; windex < words.length && guess == 'y'; windex++) {
		String word = words[list.get(windex)];
		//System.out.println(word);
		//String word = words[(int) (Math.random() * words.length)];
		String token = new String(new char[word.length()]).replace("\0", "*");
		int numOfAsterisk = token.length();
		//System.out.println(token + " " + word);
		while (numOfAsterisk > 0) {
		    System.out.print("(Guess) Enter a letter in word " + token + " > ");
		    String input = sc.nextLine();
		    if (input != null && input.trim().length() > 0) {
			char ch = input.charAt(0);
			char[] newWord = word.toCharArray();
			char[] newToken = token.toCharArray();
			ArrayList<Integer> indexes = new ArrayList<Integer>();
			for (int i = 0; i < newWord.length; i++) {
			    if (ch == newWord[i]) {
				//System.out.println(ch + " : " + newWord[i]);
				isNotfound = false;
				indexes.add(i);
			    }
			    //System.out.println(isNotfound);
			}

			if (isNotfound) {
			    System.out.println(ch + " is not in the word");
			    miss++;
			} else {
			    //Checking to see if the user entered the same character before or not
			    for (int j = 0; j < indexes.size(); j++) {
				int index = indexes.get(j);
				if (newToken[index] == ch) {
				    isAlreadyFound = true;
				    break;
				} else {
				    if (newToken[index] == '*') {
					newToken[index] = ch;
				    }
				}
			    }

			    if (isAlreadyFound) {
				System.out.println(ch + " is already in the word");
			    }
			}

			numOfAsterisk = 0;
			token = "";
			for (int a = 0; a < newToken.length; a++) {
			    if (newToken[a] == '*')
				numOfAsterisk++;
			    token += newToken[a];
			}
			//System.out.println(token);
		    }
		    isNotfound = true;
		    isAlreadyFound = false;
		}
		if (miss > 0)
		    System.out.println("The word is " + word + ". You missed " + miss + " times");
		else
		    System.out.println("The word is " + word + ". You missed " + miss + " time");
		System.out.print("Do you want to guess another word? Enter y or n> ");
		String input = sc.nextLine();
		miss = 0;

		while (input == null || input.trim().length() == 0
			|| (input.trim().charAt(0) != 'y' && input.trim().charAt(0) != 'n')) {
		    System.out.print("Do you want to guess another word? Enter y or n> ");
		    input = sc.nextLine();
		}
		guess = input.trim().charAt(0);
	    }
	    s = br.readLine();
	}
	br.close();
	System.out.print("Game is over");
	sc.close();
    }

}