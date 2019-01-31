package com.adrian.util;

public class Log {
	
	public static final int NONE 	= 0;
	public static final int DEBUG 	= 1;
	public static final int INFO 	= 2;
	public static final int WARNING = 4;
	public static final int ERROR	= 8;
	
	private static int FLAGS = INFO | DEBUG | WARNING | ERROR;
	private static String log = "";

	/**
	 * Clear the FLAGS variable
	 */
	public static void clearFlags() {
		FLAGS = NONE;
	}
	
	/**
	 * Set the FLAGS variable
	 * @param flags The FLAG code
	 */
	public static void setFlags(int flags) {
		FLAGS = flags;
	}
	
	/**
	 * Add a FLAG into a FLAGS variable
	 * @param flag The FLAG code
	 */
	public static void addFlag(int flag) {
		if((FLAGS & flag) != flag) {
			FLAGS += flag;
		}
	}
	
	/**
	 * Removes the flag from the FLAGS variable
	 * @param flag The FLAG code
	 */
	public static void removeFlag(int flag) {
		if((FLAGS & flag) == flag) {
			FLAGS -= flag;
		}
	}

	/**
	 *  Save the log message and print it if DEBUG flag is active
	 * @param tag The TAG of the log message
	 * @param message The message
	 */
	public static void d(String tag, String message) {
		log += "D-["+tag+"] - "+message+"\n";
		if((FLAGS & DEBUG) == DEBUG) {
			System.out.println("D-["+tag+"] - "+message);			
		}
	}
	
	/**
	 *  Save the log message and print it if INFO flag is active
	 * @param tag The TAG of the log message
	 * @param message The message
	 */
	public static void i(String tag, String message) {
		log += "I-["+tag+"] - "+message+"\n";
		if((FLAGS & INFO) == INFO) {
			System.out.println("I-["+tag+"] - "+message);			
		}
	}
	
	/**
	 *  Save the log message and print it if WARNING flag is active
	 * @param tag The TAG of the log message
	 * @param message The message
	 */
	public static void w(String tag, String message) {
		log += "W-["+tag+"] - "+message+"\n";
		if((FLAGS & WARNING) == WARNING) {
			System.out.println("W-["+tag+"] - "+message);			
		}
	}
	
	/**
	 *  Save the log message and print it if ERROR flag is active
	 * @param tag The TAG of the log message
	 * @param message The message
	 */
	public static void e(String tag, String message) {
		log += "E-["+tag+"] - "+message+"\n";
		if((FLAGS & ERROR) == ERROR) {
			System.err.println("E-["+tag+"] - "+message);			
		}
	}
	
	/**
	 * Save the log into the 
 	 * @param path The path where save the log file (text file)
	 *
	public static void writeLog(String path) {
		Utilities.writeLines(new String[]{log}, path);
	}*/
	
}
