package com.tp2.journal;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileJournal implements IJournal {

	@Override
	public void outPut_Msg(String message) {
		
		try {
			FileWriter writer = new FileWriter("logs.txt", true);
			BufferedWriter out = new BufferedWriter(writer);

			out.write(message + "\r");
			out.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
