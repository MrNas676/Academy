import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class addTeacher {
private static final String INSER_QUERY = "INSER INTO teacher(tid, tname, classes) VLAUES (?,?,?)";
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");
		String name = req.getParameter("name");
		String tid = req.getParameter("tid");
		String classes = req.getParameter("classes");

		
		try {
			Class.forName("jdbc:mysql://localhost:4306/academy");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost:4306/academy","root", "");
				PreparedStatement ps = con.prepareStatement(INSER_QUERY);){
			ps.setString(1, name);
			ps.setString(2, tid);
			ps.setString(3, classes);

			
			int count=ps.executeUpdate();
			if(count==0) {
				pw.println("Record not submitted");
			}else {
				pw.println("Record has been stored!");
			}
			
		}catch(SQLException se) {
			pw.println(se.getMessage());
			se.printStackTrace();
		}catch(Exception e) {
			pw.println(e.getMessage());
			e.printStackTrace();
		}

		
		pw.close();
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
}
