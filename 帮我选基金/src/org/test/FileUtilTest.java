package org.test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import org.util.FileUtil;
import org.junit.Assert;
import org.junit.Test;

public class FileUtilTest {

	@Test
	public void testgetFundInfo() {
		String fundNum = "000001";
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String fundInfo = df.format(new Date())+" :  000001  华夏成长  混合型\n";
		String s = FileUtil.getFundInfo(fundNum);
		Assert.assertEquals(s, fundInfo);
	}
	@Test
	public void testgetFund() {
		Vector<Vector<String>> fund = FileUtil.getFund();
		Assert.assertEquals(2334, fund.size());
	}
	@Test
	public void testgetFileOfType() {
		String filename = "Rank/开放型";
		String type = "开放型";
		String file = FileUtil.getFileOfType(type);
		Assert.assertEquals(filename, file);
	}
	@Test
	public void testgetFileOfTime() {
		String filename = "Rank/开放型1年排行.txt";
		String time = "近1年增长率";
		String file = FileUtil.getFileOfTime("Rank/开放型", time);
		Assert.assertEquals(filename, file);
	}
	@Test
	public void testgetFundOfRank() {
		String filename = "Rank/开放型1年排行.txt";
		String rank = "前10";
		Vector<Vector<String>> fund = FileUtil.getFundOfRank(filename, rank);
		Assert.assertEquals(10, fund.size());
	}
}
