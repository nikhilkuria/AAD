package com.sc.percolation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Test;

public class PercolationTest {

	
	@Test
	public void TestPercolation() throws FileNotFoundException, IOException{
		Percolation percolation = new Percolation(4);
		File nodesFile = new File("C:/Temp/sc/git/AAD/Perlocation/src/com/sc/percolation/nodes.txt");
		
		try(BufferedReader nodeFileReader = new BufferedReader(new FileReader(nodesFile))){
			for (String line; (line = nodeFileReader.readLine()) != null; ) {
				int xOrdinate = Integer.valueOf(line.split(",")[0]);
				int yOrdinate = Integer.valueOf(line.split(",")[1]);
				System.out.println(xOrdinate+" "+yOrdinate);
				percolation.open(xOrdinate, yOrdinate);
				System.out.println(percolation.percolates());
			}
		}
		
	}
	
}
