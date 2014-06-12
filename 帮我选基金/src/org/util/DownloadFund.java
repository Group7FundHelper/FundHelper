package org.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

public class DownloadFund {

	public static void downloadfundcode() throws IOException {

		String filename = "所有基金代码.txt";
		File file = Function.createfile(filename);
		String linkstr = "http://fund.eastmoney.com/js/fundcode_search.js?v=20130718.js";
		String pagecontent = Function.link(linkstr);
		Function.writeinformation(file, pagecontent, 1);
	}

	public static void downloadfundranking() throws IOException {

		String filename1 = "Rank/开放型6个月排行.txt";
		String filename2 = "Rank/开放型1年排行.txt";
		String filename3 = "Rank/开放型3年排行.txt";
		String filename4 = "Rank/货币型6个月排行.txt";
		String filename5 = "Rank/货币型1年排行.txt";
		String filename6 = "Rank/货币型3年排行.txt";
		String filename7 = "Rank/理财型6个月排行.txt";
		String filename8 = "Rank/理财型3个月排行.txt";
		String filename9 = "Rank/理财型1个月排行.txt";

		File file1 = Function.createfile(filename1);
		File file2 = Function.createfile(filename2);
		File file3 = Function.createfile(filename3);
		File file4 = Function.createfile(filename4);
		File file5 = Function.createfile(filename5);
		File file6 = Function.createfile(filename6);
		File file7 = Function.createfile(filename7);
		File file8 = Function.createfile(filename8);
		File file9 = Function.createfile(filename9);

		String linkstr1 = "http://fund.eastmoney.com/data/rankhandler.aspx?op=ph&dt=kf&ft=all&rs=&gs=0&sc=6yzf&st=desc";
		String linkstr2 = "http://fund.eastmoney.com/data/rankhandler.aspx?op=ph&dt=kf&ft=all&rs=&gs=0&sc=1nzf&st=desc";
		String linkstr3 = "http://fund.eastmoney.com/data/rankhandler.aspx?op=ph&dt=kf&ft=all&rs=&gs=0&sc=3nzf&st=desc";
		String linkstr4 = "http://fund.eastmoney.com/data/rankhandler.aspx?op=ph&dt=hb&ft=&rs=&gs=0&sc=6yzf&st=desc&pi=1&pn=50&mg=&dx=1";
		String linkstr5 = "http://fund.eastmoney.com/data/rankhandler.aspx?op=ph&dt=hb&ft=&rs=&gs=0&sc=1nzf&st=desc&pi=1&pn=50&mg=&dx=1";
		String linkstr6 = "http://fund.eastmoney.com/data/rankhandler.aspx?op=ph&dt=hb&ft=&rs=&gs=0&sc=3nzf&st=desc&pi=1&pn=50&mg=&dx=1";
		String linkstr7 = "http://fund.eastmoney.com/data/rankhandler.aspx?op=ph&dt=lc&ft=&rs=&gs=0&sc=6yzf&st=desc&pi=1&pn=50&dx=1&fbq=";
		String linkstr8 = "http://fund.eastmoney.com/data/rankhandler.aspx?op=ph&dt=lc&ft=&rs=&gs=0&sc=3yzf&st=desc&pi=1&pn=50&dx=1&fbq=";
		String linkstr9 = "http://fund.eastmoney.com/data/rankhandler.aspx?op=ph&dt=lc&ft=&rs=&gs=0&sc=1yzf&st=desc&pi=1&pn=50&dx=1&fbq=";

		String pagecontent1 = Function.link(linkstr1);
		String pagecontent2 = Function.link(linkstr2);
		String pagecontent3 = Function.link(linkstr3);
		String pagecontent4 = Function.link(linkstr4);
		String pagecontent5 = Function.link(linkstr5);
		String pagecontent6 = Function.link(linkstr6);
		String pagecontent7 = Function.link(linkstr7);
		String pagecontent8 = Function.link(linkstr8);
		String pagecontent9 = Function.link(linkstr9);

		Function.writeinformation(file1, pagecontent1, 10);
		Function.writeinformation(file2, pagecontent2, 11);
		Function.writeinformation(file3, pagecontent3, 13);
		Function.writeinformation(file4, pagecontent4, 9);
		Function.writeinformation(file5, pagecontent5, 10);
		Function.writeinformation(file6, pagecontent6, 12);
		Function.writeinformation(file7, pagecontent7, 9);
		Function.writeinformation(file8, pagecontent8, 8);
		Function.writeinformation(file9, pagecontent9, 7);
	}

	public static void downloadfundjz(String code) throws IOException {

		boolean flage;
		int page = 1;
		String filename = "基金历史净值/" + code + ".txt";
		File file = Function.createfile(filename);

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

				Function.writeinformation(pagecontent, file);
				page++;
			}
		} while (flage);

	}

	public static void downloadfundjz() throws IOException {

		int page = 1;
		File file = new File("所有基金代码.txt");
		BufferedReader br = null;
		br = new BufferedReader(new FileReader(file));
		String s = null;

		while ((s = br.readLine()) != null) {
			boolean flage = true;
			String code = s.substring(0, 6);
			System.out.print(code);

			String filename = "基金历史净值/" + code + ".txt";
			File Jzfile = Function.createfile(filename);

			FileWriter clear = new FileWriter(Jzfile);
			clear.write("");
			clear.close();

			while (flage) {
				String pag = String.valueOf(page);
				String linkstr = "http://fund.eastmoney.com/f10/F10DataApi.aspx?type=lsjz&code="
						+ code + "&page=" + pag + "&per=20&sdate=&edate=";
				String pagecontent = Function.link(linkstr);
				flage = Function.determine(pagecontent);
				if (flage) {
					Function.writeinformation(pagecontent, Jzfile);
					page++;
				}
			}
			System.out.print("下载成功" + '\n');
			page = 1;
		}
		br.close();

	}
}