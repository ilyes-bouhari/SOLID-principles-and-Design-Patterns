package package_1;

public class ConsoleJournal implements IJournal {

	@Override
	public void outPut_Msg(String message) {
		System.out.println(message);
	}
}
