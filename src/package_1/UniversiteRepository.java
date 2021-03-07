package package_1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UniversiteRepository implements IRepository<Universite> {
	
	IDBConnection dbConnection;
	private CompositeJournal _journal;
	private String message;
	
	public UniversiteRepository(IDBConnection dbConnection) {
		this.dbConnection = dbConnection;
		this._journal = new CompositeJournal();
	}
	
	@Override
	public Universite GetById(int universityId) throws SQLException {
		
		Connection connect = dbConnection.getConnection();
		
		Statement statement = connect.createStatement();
				
		message = "LOG DB : debut recherche de id universite dans la base de donnee";
	    _journal.addJournal(new MetaJournal(new ConsoleJournal()));
	    _journal.addJournal(new MetaJournal(new FileJournal()));
	    _journal.outPut_Msg(message);
		
		String sql = "select * from universite where id_universite="+ universityId;

		ResultSet result = statement.executeQuery(sql);

		result.next();
		
		TypePackage typePackage = TypePackage.valueOf(result.getString(3));
		Universite university = new Universite (result.getInt(1), result.getString(2), typePackage);
			
		message = "LOG DB : universite recuperee";
	    _journal.addJournal(new MetaJournal(new ConsoleJournal()));
	    _journal.addJournal(new MetaJournal(new FileJournal()));
	    _journal.outPut_Msg(message);
	    
		connect.close();
		
		return university;		
	}

	@Override
	public boolean Exists(String field) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean Exists(int field) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void add(Universite model) throws SQLException {
		// TODO Auto-generated method stub	
	}
	
	public Etudiant setNbLivreMensuel_Autorise(Etudiant student) throws SQLException {
		
		TypePackage university_pack = this.GetById(student.getId_universite()).getPack();
		
		switch (university_pack) {
			case Standard: {
				student.setNbLivreMensuel_Autorise(10);
				break;
			}
			
			case Premium: {
				student.setNbLivreMensuel_Autorise(10 * 2);
				break;
			}
			
			default:
				break;
		}
		
		return student;
	}
}
