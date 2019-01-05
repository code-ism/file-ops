package org.fileops;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileUtility {

	public static void main(String[] args) {

		FileUtility fileUtility = new FileUtility();
		Object[] wordsArray = null;
		String inputFie = null;
		int top = -1;
		
		try {
			top = Integer.parseInt(args[1]);
		}catch(ArrayIndexOutOfBoundsException | NumberFormatException e) {
			System.out.println(AppConstants.MESSAGE_DEFAULT_ARG);
			top = 10;
		}
		
		try {
			inputFie = args[0];
			wordsArray = fileUtility.getTopWords(inputFie,top);
			if(wordsArray.length < top) {
				top = wordsArray.length;
			}
			System.out.println(AppConstants.MESSAGE_TOP + top + AppConstants.MESSAGE_WORDS_FROM_FILE);
			System.out.println(AppConstants.MESSAGE_PLACE_HOLDER);
			for (int i = 0; i < top; i++) {
				System.out.println(wordsArray[i]);
			}
		}catch (FileNotFoundException e) {
			System.out.println(AppConstants.MESSAGE_FILE_NOT_FOUND);
		}catch(ArrayIndexOutOfBoundsException e) {
			System.out.println(AppConstants.MESSAGE_INVALID_ARGUMENTS);
			System.out.println(AppConstants.MESSAGE_USAGE);
		} catch (IOException e) {
			System.out.println(AppConstants.MESSAGE_ERROR_FILE_OPERATION);
		}
	}

	/*
	 * This method returns the top occurrence of words in specified file.
	 */
	public Object[] getTopWords(String filePath,int top) throws FileNotFoundException, IOException {
		
		Map <String, Integer> mapOfWords = new HashMap <String, Integer>();
		String[] listOfWords = null;
		int occurance = -1;
		
		FileInputStream inputStream = null;
		Scanner scanner = null;
		try {
			inputStream = new FileInputStream(filePath);
			scanner = new Scanner(inputStream);
		
			String line = null;
			while(scanner.hasNextLine()) {
				line = scanner.nextLine();
				listOfWords = line.toLowerCase().split(AppConstants.PATTERN_WORD);
				for (int i = 0; i < listOfWords.length; i++) {
					if(!listOfWords[i].isEmpty()){
						if(mapOfWords.get(listOfWords[i])!= null) {
							occurance = mapOfWords.get(listOfWords[i]);
							mapOfWords.put(listOfWords[i], occurance+1);
						}else {
							mapOfWords.put(listOfWords[i], 1);
						}
					}
				}
			}
		}catch (FileNotFoundException fileException) {
			throw fileException;
		}finally {
			if(inputStream != null) {
				inputStream.close();
			}
			if(scanner != null) {
				scanner.close();
			}
		}
		
		Object[] wordsArray = mapOfWords.entrySet().toArray();
		Arrays.sort(wordsArray, new Comparator<Object>() {
			@SuppressWarnings("unchecked")
			public int compare(Object o1, Object o2) {
				return ((Map.Entry<String, Integer>)o2).getValue()
						.compareTo(((Map.Entry<String, Integer>)o1).getValue());
			}
		});
		
		return Arrays.copyOfRange(wordsArray, 0, top);
	}
}
