package org.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import org.junit.Assert;
import org.junit.Test;

public class DownloadFundTest {

	@Test
	public void testdownloadfundcode() throws IOException {

		int strnum = 0;
		File file = new File("所有基金代码.txt");
		BufferedReader br = null;
		br = new BufferedReader(new FileReader(file));
		while (br.readLine() != null) {
			strnum++;
		}
		br.close();
		Assert.assertEquals(2334, strnum);
	}

	@Test
	public void testdownloadfunjz() throws IOException {

		int strnum = 0;
		File file = new File("基金历史净值/000015.txt");
		BufferedReader br = null;
		br = new BufferedReader(new FileReader(file));
		while (br.readLine() != null) {
			strnum++;
		}
		br.close();
		Assert.assertEquals(272, strnum);
	}
}
