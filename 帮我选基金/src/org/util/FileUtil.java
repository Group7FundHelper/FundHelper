/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Date;
import java.text.SimpleDateFormat;

import javax.swing.JOptionPane;

import org.ui.LoadUI;

public class FileUtil {

	public static String getFundInfo(String fundNum) {
		BufferedReader br = null;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(
					"所有基金代码.txt"), "UTF-8"));
			String s;
			while ((s = br.readLine()) != null) {
				String str[] = s.split(" |\t");
				if (str[0].equals(fundNum)) {
					return df.format(new Date()) + " :  " + str[0] + "  "
							+ str[2] + "  " + str[4] + "\n";
				}
			}
			br.close();
		} catch (FileNotFoundException ex) {
			
			Logger.getLogger(FileUtil.class.getName()).log(Level.SEVERE, null,
					ex);
		} catch (IOException ex) {
			Logger.getLogger(FileUtil.class.getName()).log(Level.SEVERE, null,
					ex);
		} finally {

		}
		return null;
	}

	public static Vector<Vector<String>> getFund() {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(
					"所有基金代码.txt"), "UTF-8"));
			String s;
			int i = 0;
			Vector<Vector<String>> ret = new Vector<Vector<String>>();
			while ((s = br.readLine()) != null) {
				i++;
				Vector<String> str1 = new Vector();
				String str[] = s.split(" |\t");
				str1.add(String.valueOf(i));
				str1.add(str[0]);
				str1.add(str[2]);
				str1.add(str[4]);
				ret.add(str1);
			}
			br.close();
			return ret;
		} catch (FileNotFoundException ex) {
			try {
				DownloadFund.downloadfundjz();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (IOException ex) {
			Logger.getLogger(FileUtil.class.getName()).log(Level.SEVERE, null,
					ex);
		} finally {

		}
		return null;
	}
	
	public static String getFileOfType(String type) {
		String filename = null; 
		if(type.equals("开放型")){
			filename = "Rank/"+"开放型";
		}else if(type.equals("货币型")){
			filename = "Rank/"+"货币型";
		}else if(type.equals("理财型")){
			filename = "Rank/"+"理财型";
		}
		return filename;	
	}

	public static String getFileOfTime(String filename,String time){
		if(filename.equals("Rank/理财型") && time.equals("近1月增长率")){
			filename = filename + "1个月排行.txt";
		}else if(filename.equals("Rank/理财型") && time.equals("近3月增长率")){
			filename = filename + "3个月排行.txt";
		}else if(time.equals("近6月增长率")){
			filename = filename + "6个月排行.txt";
		}else if(time.equals("近1年增长率")){
			filename = filename + "1年排行.txt";
		}else if(time.equals("近3年增长率")){
			filename = filename + "3年排行.txt";
		}
		return filename;
		
	}
	public static Vector<Vector<String>> getFundOfRank(String filename,String rank){
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(
					filename), "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "暂无数据！");
		}
		String s;
		int i = 0;
		Vector<Vector<String>> ret = new Vector<Vector<String>>();
		try {
			while ((s = br.readLine()) != null) {
				i++;
				Vector<String> str1 = new Vector();
				String str[] = s.split(" |\t");
				str1.add(String.valueOf(i));
				str1.add(str[0]);
				str1.add(str[2]);
				str1.add(str[4]);
				ret.add(str1);
				if(i==10 && rank.equals("前10")){
					break;
				}
				if(i==20 && rank.equals("前20")){
					break;
				}
				if(i==30 && rank.equals("前30")){
					break;
				}
				if(i==40 && rank.equals("前40")){
					break;
				}
				if(i==50 && rank.equals("前50")){
					break;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;		
	}
}
