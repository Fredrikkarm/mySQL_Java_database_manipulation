package beans;

import java.util.ArrayList;

import helpers.jsonHelper;
import helpers.keyvaluepair;

public class directorBean {

	private int _id;
	private String _name;
	private String _city;
	
	
	public int getId() {
		return _id;
	}
	public void setId(int id) {
		this._id = id;
	}
	public String getName() {
		return _name;
	}
	public void setName(String name) {
		this._name = name;
	}
	public String getCity() {
		return _city;
	}
	public void setCity(String city) {
		this._city = city;
	}
	
	public String toString() {
		String pattern = "Director ID = %s, Director Name = %s, City = %s";
		String returnString = String.format(pattern,Integer.toString(_id), this._name, this._city);	

		return returnString;
	}
	
	public String toJson() {
		ArrayList<keyvaluepair> dataList = new ArrayList<keyvaluepair>();
		dataList.add(new keyvaluepair("Id", Integer.toString(_id)));
		dataList.add(new keyvaluepair("Name", this._name));
		dataList.add(new keyvaluepair("City", this._city));
		
		return jsonHelper.toJsonObject(dataList);
	}
	
}
