package redoLog;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RedoLog {
	// start project 4 here.
	private final static Charset ENCODING = StandardCharsets.UTF_8;
	final String INIT = "<INIT>";
	final String COMMIT = "<COMMIT>";
	final String START = "<START>";
	final String STOP = "<STOP>";
	final String UPDATE = "<UPDATE>";
	List<String> logline;
	private String filename;

	/**
	 * 
	 * @param filename
	 * @throws IOException
	 */

	public RedoLog(String filename) throws IOException {
		this.filename = filename;
		logline = new ArrayList<String>();
		// readLargerTextFile(filename);
	}

	/**
	 * add "COMMIT" into log
	 */

	public void writeCommit() {
		logline.add(COMMIT);
	}

	/**
	 * add "START" into log
	 */

	public void writeStart() {
		logline.add(START);
	}

	/**
	 * add "STOP" into log
	 */
	public void writeStop() {
		logline.add(STOP);
	}

	/**
	 * add "INIT" into log
	 */

	public void writeInit() {
		logline.add(INIT);
	}

	/**
	 * 
	 * @param key
	 * @param name
	 * @param old_pop
	 * @param new_pop
	 */
	public void writeUpdate(int key, String name, double old_pop, double new_pop) {
		String temp = UPDATE + "," + key + "," + name + "," + old_pop + "," + new_pop;
		logline.add(temp);
	}

	// public void init(String logname) throws IOException{
	// String firstline = "<INIT>";
	// logline.add(firstline);
	// writeLargerTextFile(logname, logline);
	// }
	/**
	 * 
	 * @param logname
	 * @throws IOException
	 */
	public void readLargerTextFile(String logname) throws IOException {
		Path path = Paths.get(logname);
		try (Scanner scanner = new Scanner(path, ENCODING.name())) {
			while (scanner.hasNextLine()) {
				// process each line in some way
				logline.add(scanner.nextLine());
			}
		}
	}

	/**
	 * 
	 * @param fileName
	 * @param logLines
	 * @throws IOException
	 */
	public void writeLargerTextFile(String fileName, List<String> logLines) throws IOException {
		Path path = Paths.get(fileName);
		try (BufferedWriter writer = Files.newBufferedWriter(path, ENCODING)) {
			for (String line : logLines) {
				writer.write(line);
				writer.newLine();
			}
			writer.close();
		}
	}

	/**
	 * 
	 * @throws IOException
	 */
	public void savelog() throws IOException {
		writeLargerTextFile(this.filename, logline);
	}

	/**
	 * clear log
	 */
	public void clearlog() {
		logline.clear();
	}

	/**
	 *  print log 
	 */
	public void printlog() {
		for (String lgg : logline) {
			System.out.println(lgg);
		}
	}

	/**
	 * 
	 * @return filename
	 */
	public String getfilename() {
		return this.filename;
	}
}
