package beans;

import java.util.ArrayList;

import helpers.jsonHelper;
import helpers.keyvaluepair;

public class movieBean {
	private int movie_id;
	private String title;
	private int director_id;
	private int releasedate_id;
	
	
	public int getMovie_id() {
		return movie_id;
	}
	public void setMovie_id(int movie_id) {
		this.movie_id = movie_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getDirector_id() {
		return director_id;
	}
	public void setDirector_id(int director_id) {
		this.director_id = director_id;
	}
	public int getReleasedate_id() {
		return releasedate_id;
	}
	public void setReleasedate_id(int releasedate_id) {
		this.releasedate_id = releasedate_id;
	}
	
	public String toString() {
		String pattern = "Movie ID = %s, Title = %s, Director ID = %s, Releasedate ID = %s";
		String returnString = String.format(pattern, Integer.toString(this.movie_id), this.title, Integer.toString(director_id), Integer.toString(releasedate_id));	

		return returnString;
	}
	
	public String toJson() {
		ArrayList<keyvaluepair> dataList = new ArrayList<keyvaluepair>();
		dataList.add(new keyvaluepair("Movie ID", Integer.toString(this.movie_id)));
		dataList.add(new keyvaluepair("Title", this.title));
		dataList.add(new keyvaluepair("Director ID", Integer.toString(this.director_id)));
		dataList.add(new keyvaluepair("Releasedate ID", Integer.toString(this.releasedate_id)));
		
		return jsonHelper.toJsonObject(dataList);
	}
}
