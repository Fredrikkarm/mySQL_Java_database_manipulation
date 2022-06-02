package java_sql_part2;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import helpers.databaseHelper;
import helpers.jsonHelper;
import objectLists.actor_relations;
import objectLists.actors;
import objectLists.addresses;
import objectLists.directors;
import objectLists.genres;
import objectLists.moviegenres;
import objectLists.movies;
import objectLists.releasedates;

public class main_class {
	public static void main(String[] args) throws SQLException {
		Connection conn = databaseHelper.DbConnect("movies");

		//ShowAllTables(conn);

		// updateActors(conn, "morgan freeman", "Visby", 45);
		// createActors(conn, "Stellan Skarsgård", "Stockholm", 42, 4, 3);

		// updateAddress(conn, "villa villerkulla", "22222", "svarte", 1);
		// createAddress(conn, "mellangården 2", "987654", "skåne");

		// updateDirectors(conn, "Långstrump", "liechtenstein", 10);
		// createDirectors(conn, "steven spielberg", "lubech");

		// updateMovies(conn, "James Bond", 3, 6, 3);
		// createMovies(conn, "Double agent 007", 4, 2);

		// createGenre(conn, "Kidsshow");
		// updateGenres(conn, "barnprogram", 7);
		
		//deleteMovies(conn, "Avatar");

		//deleteGenre(conn, 6);
		
		//deleteActor(conn, 1);
		
		//deleteAddress(conn, 5);
		
		ShowAllTables(conn);

		conn.close();
	}

	private static void ShowAllTables(Connection conn) {

		actors myActors = new actors(conn);
		addresses myAddresses = new addresses(conn);
		movies myMovies = new movies(conn);
		genres myGenres = new genres(conn);
		directors myDirectors = new directors(conn);
		actor_relations myAR = new actor_relations(conn);
		moviegenres myMG = new moviegenres(conn);
		releasedates myRD = new releasedates(conn);

		ArrayList<String> document = new ArrayList<String>();
		document.add(myActors.toJson());
		document.add(myAddresses.toJson());
		document.add(myMovies.toJson());
		document.add(myGenres.toJson());
		document.add(myDirectors.toJson());
		document.add(myAR.toJson());
		document.add(myMG.toJson());
		document.add(myRD.toJson());

		String jsonDoc = jsonHelper.toJsonObjectFromStrings(document);
		System.out.println(jsonDoc);

	}

	private static void createActors(Connection conn, String name, String hometown, int age, int addressId, int actorMovieId) {
		actors myActors = new actors(conn);

		myActors.createActor(name, hometown, age, addressId, actorMovieId);
		System.out.println("createActor executed");
	}

	private static void updateActors(Connection conn, String name, String city, int age) {

		actors myActors = new actors(conn);

		int antal = myActors.updateActors(name, city, age);
		System.out.println("uppdaterat: " + antal);

		antal = myActors.updateActorsCity("s%", "växjö");
		System.out.println("uppdaterat : " + antal);
	}

	private static void createDirectors(Connection conn, String name, String city) {
		directors myDirectors = new directors(conn);
		myDirectors.createDirector(name, city);
	}

	private static void updateAddress(Connection conn, String address, String postalcode, String state,
			int address_id) {
		addresses myAddresses = new addresses(conn);

		int antal = myAddresses.updateAddresses(address, postalcode, state, address_id);
		System.out.println("uppdaterat: " + antal);

	}

	private static void createAddress(Connection conn, String address, String postalcode, String state) {
		addresses myAddresses = new addresses(conn);

		myAddresses.createAddress(address, postalcode, state);
	}

	private static void updateDirectors(Connection conn, String name, String city, int id) {
		directors myDirectors = new directors(conn);

		int antal = myDirectors.updateDirectors(name, city, id);
		System.out.println("updated: " + antal);
	}

	private static void updateMovies(Connection conn, String title, int director_id, int releasedate_id, int movie_id) {
		movies myMovies = new movies(conn);

		int antal = myMovies.updateMovies(title, director_id, releasedate_id, movie_id);
	}

	private static void createMovies(Connection conn, String title, int director_id, int releasedate_id) {
		movies myMovies = new movies(conn);

		myMovies.createMovies(title, director_id, releasedate_id);
	}

	private static void updateGenres(Connection conn, String genre, int genre_id) {
		genres myGenres = new genres(conn);

		myGenres.updateGenres(genre, genre_id);
	}

	private static void createGenre(Connection conn, String genre) {
		genres myGenres = new genres(conn);
		myGenres.createGenre(genre);
	}

	private static void updateActorRelations(Connection conn, int movie_id, int actor_id) {
		actor_relations myAR = new actor_relations(conn);
		myAR.updateActorRelations(movie_id, actor_id);
	}

	private static void createActorRelations(Connection conn, int movie_id, int actor_id) {
		actor_relations myAR = new actor_relations(conn);
		myAR.createActorRelations(movie_id, actor_id);
	}

	private static void updateMovieGenres(Connection conn, int movie_id, int genre_id) {
		moviegenres myMG = new moviegenres(conn);
		myMG.updateMovieGenres(movie_id, genre_id);
	}

	private static void createMovieGenres(Connection conn, int movie_id, int genre_id) {
		moviegenres myMG = new moviegenres(conn);
		myMG.createActorRelations(movie_id, genre_id);
	}

	private static void updateReleasedates(Connection conn, int releasedate_id, int releasedateYear) {
		releasedates myRD = new releasedates(conn);
		myRD.updateReleasedate(releasedate_id, releasedateYear);
	}

	private static void createReleasedates(Connection conn, int releasedateYear) {
		releasedates myRD = new releasedates(conn);
		myRD.createReleasedate(releasedateYear);
	}
	
	private static void deleteMovies(Connection conn, String title) {
		//not functional yet
		movies myMovies = new movies(conn);
		myMovies.deleteMovies(title);
	}
	
	private static void deleteGenre(Connection conn, int genre_id) {
		genres myGenres = new genres(conn);
		myGenres.deleteGenre(genre_id);
	}
	
	private static void deleteActorrelations(Connection conn, int actor_id) {
		actor_relations myAR = new actor_relations(conn);
		myAR.deleteActorRelations(actor_id);
	}
	
	private static void deleteActor(Connection conn, int address_id) {
		actors myActors = new actors(conn);
		myActors.deleteActor(address_id);
	}
	
	private static void deleteAddress(Connection conn, int address_id) {
		addresses myAddresses = new addresses(conn);
		myAddresses.deleteAddress(address_id);
	}
	
	private static void deleteReleasedate(Connection conn, int releasedate_id) {
		releasedates myRD = new releasedates(conn);
		myRD.deleteReleasedate(releasedate_id);
	}
	
	private static void deleteDirector(Connection conn, int director_id) {
		directors myDirectors = new directors(conn);
		myDirectors.deleteDirector(director_id);
	}

}
