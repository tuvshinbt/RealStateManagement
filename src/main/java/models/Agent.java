package models;

import java.util.List;

import org.sql2o.Connection;

import utils.Sql2Object;

public class Agent extends User {

	public static List<Agent> getAgentList(){
		String queryString = "SELECT * FROM user WHERE ROLE = 3";

        try ( Connection con = Sql2Object.open() ) {
            return con.createQuery(queryString).executeAndFetch(Agent.class);
        }
	}
	
	public static Agent find(String id) {
        String queryString = "SELECT * FROM user WHERE ID = :id and Role = 3";

        try ( Connection con = Sql2Object.open() ) {
            return con.createQuery(queryString)
                    .addParameter("id", id)
                    .executeAndFetch(Agent.class).get(0);
        }
    }
	
}
