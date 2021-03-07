package package_1;

import java.util.ArrayList;
import java.util.List;

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
