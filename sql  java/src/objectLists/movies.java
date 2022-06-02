package objectLists;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import beans.movieBean;
import helpers.jsonHelper;

public class movies {
	private Connection _connection;
	private ArrayList<movieBean> _movies;
	
	public movies(Connection cn) {
		this._connection = cn;
		this._movies = new ArrayList<movieBean>();
		getMovies();
	}
	
	public ArrayList<movieBean> getMovies() {
		String qry = "select * from movies";

		if (this._movies.size() > 0) 
			return this._movies;
			
		this._movies = new ArrayList<movieBean>();
		try (PreparedStatement myQry = this._connection.prepareStatement(qry)) {
			runQuery(myQry);
		} catch (SQLException e) {
			System.out.println("getMovies exception for statement");
			e.printStackTrace();
		}
		
		return this._movies;
	}
	public int updateMovies(String title, int director_id, int releasedate_id, int movie_id) {
		int count = -1;
		String qry = "UPDATE movies SET title = ?, director_id = ?, releasedate_id = ? WHERE movie_id = ?";
		try (PreparedStatement myQry = this._connection.prepareStatement(qry)){
			myQry.setString(1, title);
			myQry.setInt(2, director_id);
			myQry.setInt(3, releasedate_id);
			count = myQry.executeUpdate();
		
		}catch (SQLException e) {
			System.out.println("updateMovies exception for statement");
			e.printStackTrace();
		}
		
		return count;
	}
	public int createMovies(String title, int director_id, int releasedate_id) {
		int count = -1;
		String qry = "INSERT INTO movies (title, director_id, releasedate_id) VALUES (?,?,?)";
		try (PreparedStatement myQry = this._connection.prepareStatement(qry)){
			myQry.setString(1, title);
			myQry.setInt(2, director_id);
			myQry.setInt(3, releasedate_id);
			count = myQry.executeUpdate();
		
		}catch (SQLException e) {
			System.out.println("createMovies exception for statement");
			e.printStackTrace();
		}
		return count;
	}
	public int deleteMovies(String title) {
		int del = 0;
		String qry = "DELETE FROM movies where title = ?";
		try(PreparedStatement myQry = this._connection.prepareStatement(qry)){
			myQry.setString(1, title);
			myQry.executeUpdate();
		}catch(SQLException e) {
			System.out.println("deleteMovies Exception");
			e.printStackTrace();
		}
		return del;
	}
	
	public String toJson() {
		String beansContent = "";
		for (movieBean mb : this._movies) {
			beansContent += mb.toJson() + ",";
		}
		beansContent = beansContent.substring(0, beansContent.lastIndexOf(","));
		
		return jsonHelper
			.toJsonArray("Movies", beansContent);
	}

 	private movieBean buildMovie(ResultSet rs) {
		movieBean mb = new movieBean();

		try {
			mb.setMovie_id(rs.getInt("movie_id"));
			mb.setTitle(rs.getString("title"));
			mb.setDirector_id(rs.getInt("director_id"));
			mb.setReleasedate_id(rs.getInt("releasedate_id"));
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return mb;
	}
	
 	private void buildMovies(ResultSet rs) throws SQLException {
 		while(rs.next()) {  // rows
			this._movies.add(buildMovie(rs));
		}
 	}
 	
 	private void runQuery(PreparedStatement query) {
		try (ResultSet rs = query.executeQuery()) {
			buildMovies(rs);
		} catch (SQLException e) {
			System.out.println("error in runQuery exception for result set");
			e.printStackTrace();
		}

 	}
}
