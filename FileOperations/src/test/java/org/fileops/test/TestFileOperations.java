package org.fileops.test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.fileops.FileUtility;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class TestFileOperations {

	@Rule
	public TemporaryFolder tempFolder = new TemporaryFolder();
	
	@Test
	public void testGetTopWords() throws IOException {
		tempFolder.newFile("input.txt");
		String fileContent = "simple sample simple";
		Files.write(Paths.get("input.txt"), fileContent.getBytes());
		
		FileUtility fileUtility = new FileUtility();
		
		Object[] words = fileUtility.getTopWords("input.txt", 2);
		
		Assert.assertEquals("simple=2", words[0].toString());
		Assert.assertEquals("sample=1", words[1].toString());
	}
	
}
