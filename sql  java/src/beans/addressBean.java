package beans;

import java.util.ArrayList;

import helpers.jsonHelper;
import helpers.keyvaluepair;

public class addressBean {
	private int _id;
	private String _address;
	private String _postalcode;
	private String _state;
	
	public int getId() {
		return _id;
	}
	
	public void setId(int id) {
		this._id = id;
	}
	
	public String getAddress() {
		return _address;
	}

	public void setAddress(String address) {
		this._address = address;
	}

	public String getPostalcode() {
		return _postalcode;
	}

	public void setPostalcode(String postalcode) {
		this._postalcode = postalcode;
	}

	public String getState() {
		return _state;
	}

	public void setState(String state) {
		this._state = state;
	}

	public String toString() {
		String pattern = "Address ID = %s, Address = %s, Postalcode = %s, State = %s";
		String returnString = String.format(pattern,Integer.toString(_id), this._address, this._postalcode, this._state);	

		return returnString;
	}
	
	public String toJson() {
		ArrayList<keyvaluepair> dataList = new ArrayList<keyvaluepair>();
		dataList.add(new keyvaluepair("Id", Integer.toString(_id)));
		dataList.add(new keyvaluepair("Address", this._address));
		dataList.add(new keyvaluepair("Postalcode", this._postalcode));
		dataList.add(new keyvaluepair("State", this._state));
		
		return jsonHelper.toJsonObject(dataList);
	}
}
