package objectLists;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.addressBean;
import helpers.jsonHelper;

public class addresses {
	private Connection _connection;
	private ArrayList<addressBean> _addresses;

	public addresses(Connection cn) {
		this._connection = cn;
		this._addresses = new ArrayList<addressBean>();
		getAddresses();
	}

	public ArrayList<addressBean> getAddresses() {
		String qry = "select * from address";

		if (this._addresses.size() > 0) 
			return this._addresses;
			
		this._addresses = new ArrayList<addressBean>();
		try {
			PreparedStatement myQry = this._connection.prepareStatement(qry);
			ResultSet rs = myQry.executeQuery();
			while(rs.next()) {  // rows
				this._addresses.add(buildAddress(rs));
			}
			
			rs.close();
			myQry.close();			
		} catch (SQLException e) {
			System.out.println("getActors exception");
			e.printStackTrace();
		}

		return this._addresses;
	}
	
	public int createAddress(String address, String postalcode, String state) {
		int count = -1;
		String qry = "INSERT INTO address (address, postalcode, state) VALUES (?, ?, ?)";
		try (PreparedStatement myQry = this._connection.prepareStatement(qry)){
			myQry.setString(1, address);
			myQry.setString(2, postalcode);
			myQry.setString(3, state);
			count = myQry.executeUpdate();
		
		}catch (SQLException e) {
			System.out.println("createAddress exception for statement");
			e.printStackTrace();
		}
		
		return count;
	}
	public int updateAddresses(String address, String postalcode, String state, int address_id) {
		int count = -1;
		String qry = "UPDATE address SET address = ?, postalcode = ?, state = ? WHERE address_id = " + address_id;
		try (PreparedStatement myQry = this._connection.prepareStatement(qry)){
			myQry.setString(1, address);
			myQry.setString(2, postalcode);
			myQry.setString(3, state);
			count = myQry.executeUpdate();
		
		}catch (SQLException e) {
			System.out.println("updateAddresses exception for statement");
			e.printStackTrace();
		}
		
		return count;
	}
	
	public int deleteAddress(int address_id) {
		int count = -1;
		actor_relations myAR = new actor_relations(_connection);
		actors myActor = new actors(_connection);
		myAR.deleteActorRelations(address_id);
		myActor.deleteActor(address_id);
		
		String qry = "DELETE FROM address WHERE address_id = ?";
		try (PreparedStatement myQry = this._connection.prepareStatement(qry)){
			myQry.setInt(1, address_id);
			count = myQry.executeUpdate();
		
		}catch (SQLException e) {
			System.out.println("deleteAddress exception for statement");
			e.printStackTrace();
		}
		
		return count;
	
	}
	

	public String toJson() {
		String beansContent = "";
		for (addressBean ab : this._addresses) {
			beansContent += ab.toJson() + ",";
		}
		beansContent = beansContent.substring(0, beansContent.lastIndexOf(","));

		return jsonHelper
				.toJsonArray("Addresses", beansContent);
	}
	
 	private addressBean buildAddress(ResultSet rs) {
 		addressBean ab = new addressBean();

		try {
			ab.setId(rs.getInt("address_id"));
			ab.setAddress(rs.getString("address"));
			ab.setPostalcode(rs.getString("postalcode"));
			ab.setState(rs.getString("state"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ab;
	}

}
