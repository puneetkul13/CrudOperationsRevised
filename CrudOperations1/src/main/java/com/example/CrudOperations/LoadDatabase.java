package com.example.CrudOperations;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoadDatabase {
	public LoadDatabase() {
		
	}
	@GetMapping("/guestbook")
	 public ArrayList<String> getGuest() throws SQLException {
		Connection connection;
		connection = (Connection)DriverManager.getConnection("jdbc:postgressql://34.30.79.157:niveustraining:us-central1:my-database", "punit.agarwal@niveussolution.com", "9453121824");
		String sql = "SELECT * FROM guestbook";
		Statement statement = connection.createStatement();
		ResultSet result = statement.executeQuery(sql);
		ArrayList<String> guests = new ArrayList<String>();
		while (result.next()){
		    String guestName = result.getString("guestName");
		    String content = result.getString("content");
		    String output = guestName + " "+content;
		    guests.add(output);}
		
	    return guests;
	  }
	@PostMapping("/guestbook/{guestName}/{content}")
	public void insertGuest(@PathVariable String guestName, @PathVariable String content) throws SQLException {
		Connection connection;
		connection = (Connection)DriverManager.getConnection("jdbc:postgressql://34.30.79.157:niveustraining:us-central1:my-database", "punit.agarwal@niveussolution.com", "9453121824");
		String sql = "INSERT INTO guestbook (guestName, content) VALUES (?, ?)";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, guestName);
		preparedStatement.setString(2,  content);
		preparedStatement.executeUpdate();
	}
	@PutMapping("/guestbook/{guestNameOld}/{ContentOld}/{guestNameNew}")
	public void updateGuest(@PathVariable String guestNameOld, @PathVariable String ContentOld, @PathVariable String guestNameNew) throws SQLException {
		Connection connection;
		connection = (Connection)DriverManager.getConnection("jdbc:postgressql://34.30.79.157:niveustraining:us-central1:my-database", "punit.agarwal@niveussolution.com", "9453121824");
		String sql = "UPDATE guestbook SET guestName=?, content=? WHERE guestName = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, guestNameOld);
		statement.setString(2, ContentOld);
		statement.setString(3, guestNameNew);
		statement.executeUpdate();
	}
	@DeleteMapping("/guestbook/{guestName}")
	public void deleteGuest(@PathVariable String guestName) throws SQLException {
		Connection connection;
		connection = (Connection)DriverManager.getConnection("jdbc:postgressql://34.30.79.157:niveustraining:us-central1:my-database", "punit.agarwal@niveussolution.com", "9453121824");
		String sql = "DELETE FROM guestbook WHERE guestName = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, guestName);
		statement.executeUpdate();
	}
	

}
