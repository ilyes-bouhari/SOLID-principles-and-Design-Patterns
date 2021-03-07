package package_1;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MetaJournal implements IJournal {

	private IJournal _journal;
	
	public MetaJournal(IJournal journal) {
		_journal = journal;
	}
	
	private String getDate() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		
		return formatter.format(date);
	}
	
	private String getClassName() {
		return _journal.getClass().getSimpleName();
	}
	
	@Override
	public void outPut_Msg(String message) {
		message = "[ " + getDate() + " ]" + " [ " + getClassName() + " ]" + " [ " + message + " ]";
		
		_journal.outPut_Msg(message);
	}
}
