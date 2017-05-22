/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package postgresql1;
/**
 *
 * @author ashutosh_garg
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
public class PostgreSQLCreate {
   public static void main( String args[] ) {
      Connection c = null;
      Statement stmt = null;
      try {
         Class.forName("org.postgresql.Driver");
         c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/template1","postgres", "postgres");
         System.out.println("Opened database successfully");
         stmt = c.createStatement();
         String sql = "CREATE TABLE COMPANY1 " +"(ID INT PRIMARY KEY NOT NULL," +" NAME TEXT NOT NULL, " +"AGE INT,"+" ADDRESS CHAR(50), " +" SALARY  REAL)";
         stmt.executeUpdate(sql);
         stmt.close();
         c.close();
      } catch ( Exception e ) {
         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
         System.exit(0);
      }
      System.out.println("Table created successfully");
   }
}