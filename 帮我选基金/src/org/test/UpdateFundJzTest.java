package org.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;
import org.util.UpdateFundJz;

public class UpdateFundJzTest {
	
	
	@Test
	public void testsaveprejz() throws IOException{
		File file=new File("基金历史净值/770002-test.txt");
		String prejz=UpdateFundJz.saveprejz(file);
		
		Assert.assertEquals("2014-05-30	1.0360		1.0490	"+"\r\n", prejz);
	}
	@Test
	public void testupdate() throws IOException{
		
		UpdateFundJz.updatejz("519192");
		int strnum=0;
		boolean up;
		File file = new File("基金历史净值/519192.txt");
	    BufferedReader br = null;
		br = new BufferedReader(new FileReader(file));
		while(br.readLine()!=null){
				strnum++;
		}
		br.close();
		if((strnum-29)>0){
			up=true;
		}
		else
			up=false;
		Assert.assertTrue(up);
	}
}
