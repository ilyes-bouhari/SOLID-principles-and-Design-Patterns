package com.tp2.journal;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class CompositeJournal implements IJournal {

	private List<IJournal> _journals = new ArrayList<IJournal>();
	
	public void addJournal(IJournal journal) {
		_journals.add(journal);
	}
	
	private void clear() {
		_journals.clear();
	}

	@Override
	public void outPut_Msg(String message) {
		for (IJournal journal : _journals) {
			journal.outPut_Msg(message);
		}
		
		clear();
	}
}
