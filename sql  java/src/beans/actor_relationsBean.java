package beans;

import java.util.ArrayList;

import helpers.jsonHelper;
import helpers.keyvaluepair;

public class actor_relationsBean {
	private int actorId;
	private int movieId;
	
	
	public int getActorId() {
		return actorId;
	}
	public void setActorId(int actorId) {
		this.actorId = actorId;
	}
	public int getMovieId() {
		return movieId;
	}
	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
	
	public String toString() {
		String pattern = "Actor ID = %s, Movie ID = %s";
		String returnString = String.format(pattern,Integer.toString(actorId), Integer.toString(movieId));	

		return returnString;
	}
	
	public String toJson() {
		ArrayList<keyvaluepair> dataList = new ArrayList<keyvaluepair>();
		dataList.add(new keyvaluepair("ActorId", Integer.toString(actorId)));
		dataList.add(new keyvaluepair("MovieId", Integer.toString(movieId)));
		return jsonHelper.toJsonObject(dataList);
	}
	
	
}
