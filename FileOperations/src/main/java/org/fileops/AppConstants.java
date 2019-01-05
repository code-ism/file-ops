package org.fileops;

public final class AppConstants {

	public static final String PATTERN_WORD = "[^\\w-'+]";
	public static String MESSAGE_USAGE = "Usage : java FileUtility FILE [TOP] \nFILE > File in whcih search is to be performed \n"
			+ "TOP > No of Top entries to be returned. Optional, default is 10 if no value specified";
	public static String MESSAGE_DEFAULT_ARG = "No or invlaid argument specified for TOP, using defafult value.";
	public static String MESSAGE_INVALID_ARGUMENTS = "Invlaid arguments specified, please refer the usage below.";
	public static String MESSAGE_FILE_NOT_FOUND = "Specified file not foud";
	public static String MESSAGE_ERROR_FILE_OPERATION = "Some error occured during file operation";
	public static String MESSAGE_TOP = "Top ";
	public static String MESSAGE_WORDS_FROM_FILE = " word(s) from the file";
	public static String MESSAGE_PLACE_HOLDER = "----------------------------";
}
