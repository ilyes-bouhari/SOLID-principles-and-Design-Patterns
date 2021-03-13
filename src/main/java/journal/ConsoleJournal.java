package journal;

public class ConsoleJournal implements IJournal {

	@Override
	public void outPut_Msg(String message) {
		System.out.println(message);
	}
}
