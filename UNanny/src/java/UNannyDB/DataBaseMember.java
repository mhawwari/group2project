package UNannyDB;

import HandleData.Gender;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import HandleData.Member;

import java.util.UUID;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataBaseMember extends DataBase {

    public boolean checkPassword(String Login, String Password) {
        String sqlStmt = null;
        boolean state = false;
        System.out.println("Login"+Login);
        System.out.println("pASSWORD"+Password);
        try {
            this.connect();
            sqlStmt = "SELECT usernama FROM `UnannyDB`.`Babysitter` "
                    + "WHERE usernama = '" + Login
                    + "' AND password = '" + Password + "'";
            ResultSet res = statement.executeQuery(sqlStmt);
             System.out.println("EXECUTED");
            state = (res.next()) ? true : false;
            if(!res.isClosed())
                res.close();
            this.close();
            return state;
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
            return state;
        }
    }

    public boolean checkValid(String Login) {
        String sqlStmt = null;
        boolean state = true;

        try {
            this.connect();
            sqlStmt = "SELECT UserName FROM `CS359Project`.`Member` "
                    + "WHERE UserName = '" + Login + "'";
            ResultSet res = statement.executeQuery(sqlStmt);
            state = (res.next()) ? false : true;
            if(!res.isClosed())
                res.close();
            this.close();
            return state;
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
            return state;
        }
    }
    
    public boolean setStudentStatus(String Login, String status){
        String sqlStmt = null;
        PreparedStatement pstmt = null;

        try {
            this.connect();
            connection.setAutoCommit(false);
            sqlStmt = "UPDATE `CS359Project`.`Member` SET ProfileStatus = ? WHERE UserName = '" + Login +"'";
            pstmt = connection.prepareStatement(sqlStmt);

            pstmt.setString(1, status);

            pstmt.executeUpdate();
            connection.commit();
            connection.setAutoCommit(true);
            pstmt.close();
            this.close();
            return true;
        }
        catch (SQLException ex) {
            Logger.getLogger(DataBaseMember.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public boolean insertStudentIntoBase(Member student){
        String sqlStmt = null;
        PreparedStatement pstmt = null;

        try {
            this.connect();
            connection.setAutoCommit(false);
            System.out.println("prin insert member");
           sqlStmt = "INSERT INTO `UnannyDB`.`Babysitter`(usernama,password,fname,lname, " +
                    "age,sex,telephone,email,university,department,city," +
                    "address,zip,min_price) " +
                    "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            pstmt = connection.prepareStatement(sqlStmt);
            
            pstmt.setString(1, student.getUserName());
            pstmt.setString(2, student.getPassWord());
            pstmt.setString(3, student.getFirstName());
            pstmt.setString(4, student.getLastName());
            pstmt.setInt(5, student.getAge());
            pstmt.setString(6,student.getGender());
            pstmt.setString(7, student.getPhone());
            pstmt.setString(8,student.getMail());
            pstmt.setString(9, student.getUniversity());
            pstmt.setString(10, student.getDepartment());
            pstmt.setString(11, student.getCity());
            pstmt.setString(12, student.getAddress());
            pstmt.setInt(13,student.getZip());
            pstmt.setInt(14,0);
            

            pstmt.executeUpdate();
            connection.commit();
            connection.setAutoCommit(true);
            System.out.println("after insert member");
            pstmt.close();
            this.close();

            return true;
        }
        catch (SQLException ex) {
            Logger.getLogger(DataBaseMember.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public Member getStudentFromBase(String Login){
        Member newStudent = new Member(Login);
        String sqlStmt = null;

        try {
            this.connect();
            sqlStmt = "SELECT UserName,FirstName,LastName,BirthDay, "
                    + "Country,University,Department,Picture,TotalRateScore, "
                    + "TotalReviews,ProfileStatus,MasterStudent,Gender "
                    + "FROM `CS359Project`.`Member` WHERE UserName = '"
                    + Login + "'";
            ResultSet res = statement.executeQuery(sqlStmt);
            if(res.next()){
                newStudent.setUserName(res.getString("UserName"));
                newStudent.setFirstName(res.getString("FirstName"));
                newStudent.setLastName(res.getString("LastName"));
                newStudent.setUniversity(res.getString("University"));
                newStudent.setDepartment(res.getString("Department"));
                newStudent.setPicture(res.getBlob("Picture"));
                newStudent.setTotalRateScore(res.getInt("TotalRateScore"));
                newStudent.setTotalReviews(res.getInt("TotalReviews"));
                newStudent.setProfileStatus(res.getString("ProfileStatus"));
                newStudent.setGender(res.getString("Gender"));
            }
            if(!res.isClosed())
                res.close();
            this.close();
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return newStudent;
    }

    public String[] getSuggestedMembers(String UserName){
        Vector<String> suggested = new Vector<String>();
        
        if(suggested.size()>0){
            String tmp[] = new String[suggested.size()];
            return suggested.toArray(tmp);
        } else {
            return null;
        }
    }

    
   

   
}
