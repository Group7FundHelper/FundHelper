package org.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;
import org.util.Function;

public class FunctionTest {

	@Test
	public void testcreatefile() {
		String filename = "test.txt";
		File file = Function.createfile(filename);
		Assert.assertNotNull(file);
	}

	@Test
	public void testlink() throws IOException {

		String pagecontent = "var apidata";
		String content = Function
				.link("http://fund.eastmoney.com/f10/F10DataApi.aspx?type=lsjz&code=512600&page=1&per=20&sdate=&edate=");

		Assert.assertEquals(pagecontent, content.substring(0, 11));
	}

	@Test
	public void testdetermine() {
		boolean findjz = Function.determine("暂无数据!");
		boolean findjz1 = Function.determine("11111");
		Assert.assertEquals(true, findjz1);
		Assert.assertEquals(false, findjz);
	}

	@Test
	public void testwriteinformation() throws IOException {

		File file = new File("所有基金代码.txt");
		BufferedReader br = null;
		br = new BufferedReader(new FileReader(file));
		String pagecontent = Function
				.link("http://fund.eastmoney.com/js/fundcode_search.js?v=20130718.js");
		Function.writeinformation(file, pagecontent, 1);
		String teststr = br.readLine();
		br.close();
		Assert.assertEquals("000001		华夏成长		混合型", teststr);

	}

}
