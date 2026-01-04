package com.roomate.dao;

import java.sql.*;
import java.util.*;
import com.roomate.model.Chore;
import com.roomate.util.DBConnection;

public class ChoreDao {

    //  Hardcoded roommates list
    private static final List<String> ROOMMATES = Arrays.asList("Bhuvneshwar", "Divyansh", "Arpit", "Sabareesh");

    //  Fetch all chores
    public static List<Chore> getAllChores() { //this is method that is called in the servlet, it returns a chores list.
        List<Chore> chores = new ArrayList<>(); //creates a list of the chore objects
        String sql = "SELECT * FROM chores";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Chore c = new Chore(); //makes each row of the db into a chore object.
                c.setChoreId(rs.getInt("chore_id"));
                c.setChoreName(rs.getString("chore_name"));
                c.setLastDoneBy(rs.getString("last_done_by"));
                c.setNextPerson(rs.getString("next_person"));
                chores.add(c);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return chores;
    }

    //  Fetch a specific chore by ID
    public static Chore getChoreById(int id) {
        String sql = "SELECT * FROM chores WHERE chore_id = ?";
        Chore c = null;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                c = new Chore();
                c.setChoreId(rs.getInt("chore_id"));
                c.setChoreName(rs.getString("chore_name"));
                c.setLastDoneBy(rs.getString("last_done_by"));
                c.setNextPerson(rs.getString("next_person"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return c;
    }

    //  Update chore as completed
    public static void markChoreDone(int id, String currentPerson, String nextPerson) {
        String sql = "UPDATE chores SET last_done_by = ?, next_person = ?, status = ? WHERE chore_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, currentPerson);
            ps.setString(2, nextPerson);
            ps.setBoolean(3, true);
            ps.setInt(4, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //  Get next roommate in round-robin order
    public static String getNextPersonAfter(String currentPerson) {
        int idx = ROOMMATES.indexOf(currentPerson);
        if (idx == -1 || idx == ROOMMATES.size() - 1) {
            return ROOMMATES.get(0);
        }
        return ROOMMATES.get(idx + 1);
    }
}
