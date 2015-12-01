package redoLog;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

public class UpdateDB {
	private RedoLog log;
	private int popindex;

	/**
	 * Constructor to open the log file
	 * @param fileName
	 * @param popindex
	 * @throws IOException
	 */
	public UpdateDB(String fileName, int popindex) throws IOException {
		log = new RedoLog(fileName);
		this.popindex = popindex;
	}

	/**
	 * use the log file to update the backup database file
	 * @param backUp
	 */
	public void updateBackup(Relation backUp) {
		String[] oldtuple = null;
		String[] logtuple = null;
		byte[] oldtupleofr = null;
		byte[] oldtuplebyte = null;

		try (BufferedReader br = new BufferedReader(new FileReader(log.getfilename()))) {
			String line;
			while ((line = br.readLine()) != null) {
				if (!line.equals("<INIT>") && !line.equals("<START>") && !line.equals("<COMMIT>")) {
					logtuple = line.split(",");
					oldtuplebyte = backUp.get(Integer.parseInt(logtuple[1]));
					oldtuple = splitoftuple(oldtuplebyte);
					oldtuple[popindex] = logtuple[3];
					oldtupleofr = unsplitoftuple(oldtuple);
					backUp.put(oldtuple[0].hashCode(), oldtupleofr);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		backUp.saveContents();
	}

	/**
	 * split the string by ','
	 * @param tuple
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String[] splitoftuple(byte[] tuple) throws UnsupportedEncodingException {
		String str = new String(tuple, StandardCharsets.UTF_8);
		return str.split(","); // ignores commas inside quotation marks
	}

	/**
	 * combine the string with ',' between
	 * @param splitArray
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	protected static byte[] unsplitoftuple(String[] splitArray) throws UnsupportedEncodingException {
		String retString = null;
		for (String str : splitArray) {
			if (retString == null) {
				retString = str;
			} else {
				retString = retString + "," + str;
			}
		}
		return retString.getBytes("UTF-8");
	}
}
