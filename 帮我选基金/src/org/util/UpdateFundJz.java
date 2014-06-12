package org.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UpdateFundJz {

	private static String pretimestr;

	public static String saveprejz(File file) throws IOException {

		BufferedReader br = new BufferedReader(new FileReader(file));

		String firstline = br.readLine();
		if (firstline != null) {
			pretimestr = firstline.substring(0, 10);
			System.out.print("上次更新时间:" + pretimestr + '\n');
		}

		StringBuilder sb = new StringBuilder();
		sb.append(firstline + "\r\n");
		String nextline = null;

		while ((nextline = br.readLine()) != null) {
			sb.append(nextline + "\r\n");
		}

		br.close();

		String precontent = sb.toString();

		return precontent;

	}

	private static boolean writeinformation(File file, String pagecontent,
			String precontent) throws IOException {

		boolean findtimestr = false;
		FileOutputStream fw = new FileOutputStream(file, true);

		String rowdata = "<td+(.*?)>(.*?)</td>";
		Pattern rowdata_pat = Pattern.compile(rowdata);
		Matcher rowdata_mat = rowdata_pat.matcher(pagecontent);

		int row = 0;
		byte[] newline = "\r\n".getBytes();
		ok: while (rowdata_mat.find()) {

			row = row + 1;
			if (rowdata_mat.group().length() == 19
					|| rowdata_mat.group().length() == 45) {
				if (row != 1) {
					fw.write(newline);
				}
				String timestr = rowdata_mat.group().substring(4, 14);
				if (pretimestr.equals(timestr))

				{
					fw.write(precontent.getBytes());
					findtimestr = true;
					break ok;
				}
				fw.write((timestr + "\t").getBytes());
			} else if (rowdata_mat.group().length() == 32
					|| rowdata_mat.group().length() == 33) {

				String jzstr = rowdata_mat.group().substring(21, 27);
				fw.write((jzstr + "\t" + "\t").getBytes());

			}

		}
		fw.write(newline);
		fw.flush();
		fw.close();

		return findtimestr;
	}

	public static void updatejz(String code) throws IOException {

		int page = 1;
		boolean flage;

		File file = new File("基金历史净值/" + code + ".txt");
		String txtcontent = saveprejz(file);

		FileWriter clear = new FileWriter(file);
		clear.write("");
		clear.close();

		do {
			String pag = String.valueOf(page);
			String linkstr = "http://fund.eastmoney.com/f10/F10DataApi.aspx?type=lsjz&code="
					+ code + "&page=" + pag + "&per=20&sdate=&edate=";
			String pagecontent = Function.link(linkstr);

			flage = Function.determine(pagecontent);
			if (flage) {

				if (writeinformation(file, pagecontent, txtcontent)) {
					break;
				}
				page++;
			}
		} while (flage);

	}

	public static void updatejz() throws IOException {

		File file = new File("所有基金代码.txt");
		BufferedReader br = null;
		br = new BufferedReader(new FileReader(file));
		String code = null;

		while ((code = br.readLine().substring(0, 6)) != null) {
			int page = 1;
			boolean flage;
			File jzfile = new File("基金历史净值/" + code + ".txt");
			System.out.print(code);
			String precontent = saveprejz(jzfile);

			FileWriter clear = new FileWriter(jzfile);
			clear.write("");
			clear.close();

			do {
				String pag = String.valueOf(page);
				String linkstr = "http://fund.eastmoney.com/f10/F10DataApi.aspx?type=lsjz&code="
						+ code + "&page=" + pag + "&per=20&sdate=&edate=";
				String pagecontent = Function.link(linkstr);

				flage = Function.determine(pagecontent);
				if (flage) {

					if (writeinformation(jzfile, pagecontent, precontent)) {
						break;
					}
					page++;
				}
			} while (flage);
		}
		br.close();

	}

}
