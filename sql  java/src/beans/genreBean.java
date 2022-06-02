package beans;

import java.util.ArrayList;

import helpers.jsonHelper;
import helpers.keyvaluepair;

public class genreBean {
	private int _id;
	private String _genre;
	
	public int getId() {
		return _id;
	}
	
	public void setId(int genreId) {
		this._id = genreId;
	}
	
	public String getGenre() {
		return _genre;
	}
	
	public void setGenre(String genre) {
		this._genre = genre;
	}
	
	public String toString() {
		String pattern = "Genre ID = %s, Genre = %s";
		String returnString = String.format(pattern,Integer.toString(_id), this._genre);	

		return returnString;
	}
	
	public String toJson() {
		ArrayList<keyvaluepair> dataList = new ArrayList<keyvaluepair>();
		dataList.add(new keyvaluepair("Id", Integer.toString(_id)));
		dataList.add(new keyvaluepair("Genre", this._genre));
		return jsonHelper.toJsonObject(dataList);
	}
}
