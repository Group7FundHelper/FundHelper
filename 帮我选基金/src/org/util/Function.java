package org.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Function {

	public static File createfile(String filename) {

		File file = new File(filename);
		return file;
	}

	public static String link(String linkstr) throws IOException {

		URL url = new URL(linkstr);
		InputStream in = url.openStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));

		StringBuilder sb = new StringBuilder();

		String line = null;
		while ((line = reader.readLine()) != null) {
			sb.append(line + "\n");
		}

		in.close();

		String pagecontent = sb.toString();
		return pagecontent;
	}

	public static boolean determine(String pagecontent) {
		boolean flage;
		String fpage = "\\D+\\!";

		Pattern fpage_pat = Pattern.compile(fpage);
		Matcher fpage_mat = fpage_pat.matcher(pagecontent);

		if (fpage_mat.find()) {
			flage = false;
		} else {
			flage = true;
		}

		return flage;
	}

	public static void writeinformation(File file, String pagecontent,
			int type) throws IOException {

		OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(
				file), "UTF-8");
		BufferedWriter bw = new BufferedWriter(write);

		String data = null;
		if (type == 1) {
			data = "\\[(.*?)\\]";
		} else {
			data = "\"(.*?)\"";
		}

		Pattern data_pat = Pattern.compile(data);
		Matcher data_mat = data_pat.matcher(pagecontent);

		while (data_mat.find()) {
			String final_data = data_mat.group();

			final_data = final_data.replace("[[", "[");

			String[] strarray = final_data.split(",");
			if (type == 1) {

				final_data = strarray[0].substring(2, strarray[0].length() - 1)
						+ "\t\t"
						+ strarray[2].substring(1, strarray[2].length() - 1)
						+ "\t\t"
						+ strarray[3].substring(1, strarray[3].length() - 2);
			} else {
				final_data = strarray[0].substring(1, strarray[0].length())
						+ "\t\t" + strarray[1] + "\t\t" + strarray[type];
			}

			bw.write(final_data);

			bw.newLine();

		}
		bw.flush();
		bw.close();

	}

	protected static void writeinformation(String pagecontent, File file)
			throws IOException {

		OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(
				file, true), "UTF-8");
		BufferedWriter bw = new BufferedWriter(write);

		String rowdata = "<td+(.*?)>(.*?)</td>";
		Pattern rowdata_pat = Pattern.compile(rowdata);
		Matcher rowdata_mat = rowdata_pat.matcher(pagecontent);

		int row = 0;
		while (rowdata_mat.find()) {

			row = row + 1;
			if (rowdata_mat.group().length() == 19
					|| rowdata_mat.group().length() == 45) {
				if (row != 1) {
					bw.newLine();
				}
				String timestr = rowdata_mat.group().substring(4, 14);
				bw.write(timestr + "\t");
			} else if (rowdata_mat.group().length() == 32
					|| rowdata_mat.group().length() == 33) {

				String jzstr = rowdata_mat.group().substring(21, 27);
				bw.write(jzstr + "\t" + "\t");

			}

		}
		bw.newLine();
		bw.flush();
		bw.close();
	}

}
