package beans;

import java.util.ArrayList;
import helpers.jsonHelper;
import helpers.keyvaluepair;

public class moviegenreBean {
	private int genreId;
	private int movieId;
	
	
	public int getGenreId() {
		return genreId;
	}
	public void setGenreId(int actorId) {
		this.genreId = actorId;
	}
	public int getMovieId() {
		return movieId;
	}
	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
	
	public String toString() {
		String pattern = "Genre ID = %s, Movie ID = %s";
		String returnString = String.format(pattern,Integer.toString(genreId), Integer.toString(movieId));	

		return returnString;
	}
	
	public String toJson() {
		ArrayList<keyvaluepair> dataList = new ArrayList<keyvaluepair>();
		dataList.add(new keyvaluepair("GenreId", Integer.toString(genreId)));
		dataList.add(new keyvaluepair("MovieId", Integer.toString(movieId)));
		return jsonHelper.toJsonObject(dataList);
	}
	
}
