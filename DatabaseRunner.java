/**
* @author Ammar Kadic
* @version 1.3
* Spring 2019
* 
* This program connects to 3 different database oracle accounts and performs various tasks.
* Each database option has its own query options.
* Other features can be accessed by numbers 4 through 6.
* Note: Updated to use nextInt() and setInt() where appropriate.
**/
// package Database;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collections;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.Date;
import java.time.LocalDate;

public class DatabaseRunner 
{
	// Database driver and URL for the server
	// static final String driver = "oracle.jdbc.driver.OracleDriver";  
	static final String url = "jdbc:postgresql://localhost:5432/";
	static String user1 = ""; //AZ
	static String user2 = ""; // LD
	static String user3 = ""; // GV
	static String password = "";
	static Connection con;
	static PreparedStatement pstmt = null;
	static ResultSet rset = null;
	static Scanner in = new Scanner(System.in);
	static ArrayList<Integer> job_codes = new ArrayList<Integer>();
	static int per_id = 0;
	public static void main(String[] args)
	{

		 if (args.length < 4) 
		 {
            System.out.println("Usage: java DatabaseRunner <user1> <user2> <user3> <password>");
            return;
        }

		user1 = args[0];
		user2 = args[1];
		user3 = args[2];
		password = args[3];

		for(int i = 20; i<999;i++)
			job_codes.add(i);
		Collections.shuffle(job_codes);
		try
		{
			// Class.forName("oracle.jdbc.driver.OracleDriver");	
			Class.forName("org.postgresql.Driver");

			
			while(true)
			{
				System.out.println("Which Database would you like to connect to? \n(1)AZ (2)GV (3)LD\n"
						+ "\nBusiness Processes (7):\n"
						+ "(4) Hire new employee AZ \n(5) Transfer GV worker from one factory to another"
						+ " \n(6) Exchange CIO's for AZ and GV");
				int option = in.nextInt();
				if (option < 1 || option > 6)
				{
					System.out.println("\nGoodbye!");
					break;
				}
				switch (option)
				{
					case 1: connectToAZ();
					break;
					case 2: connectToGV();
					break;
					case 3: connectToLD();
					break;
					case 4: System.out.println("Executing (4)->7a)\n");
						AZhires();
					break;
					case 5: System.out.println("Executing (5)->7b)\n");
						transferGVworker();
					break;
					case 6: System.out.println("Executing (6)->7c)\n");
						transferCIOazgv();
					break;
				}
			} 
			in.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void connectToAZ()
	{
		try
		{
			System.out.println("Connecting to Database AZ");
			con = DriverManager.getConnection(url, user1, password);
			System.out.println("Successfully connected");

			
			while(true)
			{
				System.out.println("\n1) List the workers by names in the alphabetical order of last names.");
				System.out.println("\n2) List the staff (salary workers) by salary in descending order.");
				System.out.println("\n3) List the average annual pay (the salary or wage rates multiplying by 1920 hours) ");
				System.out.println("\nof each store/factory in descending order");
				System.out.println("\n4) List the required skills of a given pos_code in a readable format.");
				System.out.println("\n5) Given a person’s identifier, list this person’s skills in a readable format.");
				System.out.println("\n6) Given a person’s identifier, list a person’s missing skills for a specific pos_code in a readable format.");
				System.out.println("\n7) List the total number and the total sales ($) of every item in a given period of time ");
				System.out.println("(start date, end date) in AZ in the descending order of sales.");
				System.out.println("\n8) List the item_num, its title and the total profit that made the biggest profit for AZ in 2018.");
				System.out.println("\n9) Show the items for which the inventory is below the minimum level in AZ system. ");
				System.out.println("\nWhich query would you like to run? (1-9) (-1 to quit)");
						
				int option = in.nextInt();
				if (option < 1 || option > 9)
				{
					System.out.println("\nGoodbye AZ!");
					break;
				}
				switch (option)
				{
					case 1: System.out.println("Executing Query 1)\n");
							query1();
							break;
					case 2: System.out.println("Executing Query 2)\n");
							query2();
							break;
					case 3: System.out.println("Executing Query 3)\n");
							query3();
							break;
					case 4: System.out.println("Executing Query 4)\n");
							query4();
							break;
					case 5: System.out.println("Executing Query 5)\n");
							query5();
							break;
					case 6: System.out.println("Executing Query 6)\n");
							query6();
							break;
					case 7: System.out.println("Executing Query 7)\n");
							query7();
							break;
					case 8: System.out.println("Executing Query 8)\n");
							query8();
							break;
					case 9: System.out.println("Executing Query 9)\n");
							query9();
							break;
				}
			}
		} catch (SQLException e) {
			System.err.println(e);
		}
	}
	
	public static void connectToGV()
	{
		try
		{
			System.out.println("Connecting to Database GV");
			con = DriverManager.getConnection(url, user3, password);
			System.out.println("Successfully connected");
			while(true)
			{
				System.out.println("\n10) List the total sales in dollar to each customer of GV in 2018.");
				System.out.println("\n11) Show m_code, m_name of the material(s) that GV purchased the most (measured by quantity)");
				System.out.println(" in the fourth quarter of 2018.");
				System.out.println("\n12) Show the factory name that made the most total quantity of the product that was sold the most in 2018. ");
				System.out.println("\nWhich query would you like to run? (10-12) (-1 to quit)");
				int option = in.nextInt();
				if(option < 10 || option > 12)
				{
					System.out.println("\nGoodbye GV!");
					break;
				}
				switch (option)
				{
					case 10: System.out.println("Executing Query 10)\n");
							query10();
							break;
					case 11: System.out.println("Executing Query 11)\n");
							query11();
							break;
					case 12: System.out.println("Executing Query 12)\n");
							query12();
							break;
				}
			}
		} catch (SQLException e) {
			System.err.println(e);
		}
		
	}
	
	public static void connectToLD()
	{
		try
		{
			System.out.println("Connecting to Database LD");
			con = DriverManager.getConnection(url, user2, password);
			System.out.println("Successfully Connected");
			while(true)
			{
				System.out.println("\n13) Given a person's identifier, find all the jobs this person is currently holding and worked in the past.");
				System.out.println("\n14) In a local or national crisis, we need to find all the people who once held a position of the given pos_code. List");
				System.out.println("per_id, name, job title and the years the person worked in (starting year and ending year).");
				System.out.println("\n15) Find all the unemployed people who once held a job position of the given pos_code.");
				System.out.println("\n16) List the average, maximum and minimum annual pay (total salaries or wage rates multiplying by 1920 hours) of each");
				System.out.println("industry (listed in GICS) in the order of the industry names.");
				System.out.println("\n17) Find out the biggest employer, industry, and industry group in terms of number of employees. (Three queries)");
				System.out.println("\n18) Find out the job distribution among industries by showing the number of employees in each industry.");
				System.out.println("\n19) Given a person's identifier and a pos_code, find the courses (course id and title) that each alone teaches all the");
				System.out.println("missing skills for this person to be qualified for the specified position, assuming the skill gap of the worker and the");
				System.out.println("requirement of the position can be covered by one course.");
				System.out.println("\n20) Given a person's identifier, find the job position with the highest pay rate for this person according to his/her skill");
				System.out.println("possession.");
				System.out.println("\n21) Given a position code, list all the names along with the emails of the persons who are qualified for this position.");
				System.out.println("\n22) When a company cannot find any qualified person for a job position, a secondary solution is to find a person who is");
				System.out.println("almost qualified to the job position. Make a “missing-k” list that lists people who miss only k skills for a specified");
				System.out.println("pos_code; k < 4.");
				System.out.println("\n23) Suppose there is a new position that has nobody qualified. List the persons who miss the least number of skills that");
				System.out.println("are required by this pos_code and report the “least number”.");
				System.out.println("\n24) List each of the skill code and the number of people who misses the skill and are in the missing-k list for a given");
				System.out.println("position code in the ascending order of the people counts.");
				System.out.println("\n25) Find out the number of the workers whose earnings increased in a specific industry group (use attribute “industry");
				System.out.println("group” in table Company). [Hint: earning change = the sum of a person's current earnings - the pay of the person's");
				System.out.println("the last previous job.]");
				System.out.println("\n26) Find the position that have the most openings due to lack of qualified workers. If there are many openings of a");
				System.out.println("position but at the same time there are many qualified jobless people. Then training cannot help fill up this type of");
				System.out.println("job vacancies. What we want to find is the position that has the largest difference between vacancies (the unfilled");
				System.out.println("jobs) and the number of jobless people who are qualified for the position.");
				System.out.println("\n27) Find the course sets that teach every skill required by the position(s) found in Query #22. These courses should");
				System.out.println("effectively help most jobless people become qualified for the jobs with high demands. ");
				System.out.println("\nWhich query would you like to run? (13-27) (-1 to quit)");
				int option = in.nextInt();
				if (option < 13 || option > 27)
				{
					System.out.println("\nGoodbye LD!");
					break;
				}
				switch (option)
				{
					case 13: System.out.println("Executing Query 13)\n");
							query13();
							break;
					case 14: System.out.println("Executing Query 14)\n");
							query14();
							break;
					case 15: System.out.println("Executing Query 15)\n");
							query15();
							break;
					case 16: System.out.println("Executing Query 16)\n");
							query16();
							break;
					case 17: System.out.println("Executing Query 17)\n");
							query17();
							break;
					case 18: System.out.println("Executing Query 18)\n");
							query18();
							break;
					case 19: System.out.println("Executing Query 19)\n");
							query19();
							break;
					case 20: System.out.println("Executing Query 20)\n"); 
							query20();
							break;
					case 21: System.out.println("Executing Query 21)\n");
							query21();
							break;
					case 22: System.out.println("Executing Query 22)\n");
							query22();
							break;
					case 23: System.out.println("Executing Query 23)\n");
							query23();
							break;
					case 24: System.out.println("Executing Query 24)\n");
							query24();
							break;
					case 25: System.out.println("Executing Query 25)\n");
							query25();
							break;
					case 26: System.out.println("Executing Query 26)\n");
							query26();
							break;
					case 27: System.out.println("Executing Query 27)\n");
							query27();
							break;
				}
			}
		} catch(SQLException e) {
			System.err.println(e);
		}
	}
	
	/*List the workers by names in the alphabetical order of last names. */
	public static void query1() throws SQLException 
	{
		try
		{
			con.setAutoCommit(true);
			pstmt = con.prepareStatement("SELECT DISTINCT per_id, last_name, first_name "
									  +  "FROM person NATURAL JOIN job "
									  +  "ORDER BY last_name");
			System.out.println(pstmt.toString());
			rset = pstmt.executeQuery();
			System.out.printf("last_name%3s first_name%3s%n", "","");
			while (rset.next())
				System.out.printf("%-12s %s%n", rset.getString(2), rset.getString(3));
		} catch(SQLException e){
			System.err.println(e);
		}
	}
	
	/*List the staff (salary workers) by salary in descending order*/
	public static void query2() throws SQLException 
	{
		try
		{
			pstmt = con.prepareStatement("SELECT DISTINCT per_id, last_name, first_name, pay_rate " + 
								     	 "FROM person NATURAL JOIN job " + 
								     	 "WHERE pay_type = 'salary' " + 
									 	 "ORDER BY pay_rate DESC");
			rset = pstmt.executeQuery();
			System.out.printf("per_id%3s last_name%3s first_name%3s pay_rate%3s%n","", "","","");
			while (rset.next())
				System.out.printf("%-9s %-12s %-13s %s%n", rset.getString(1), rset.getString(2), 
								  rset.getString(3), rset.getString(4));

		} catch(SQLException e) {
			System.err.println(e);
		}
	}
	
	/* List the average annual pay (the salary or wage rates multiplying by 1920 hours) of each store/factory in 
	 * descending order*/
	public static void query3() throws SQLException
	{
		try
		{
			pstmt = con.prepareStatement("SELECT store_id, AVG( CASE\n" + 
									 	  "WHEN pay_type = 'wage' THEN pay_rate*1920\n" + 
									 	  "ELSE pay_rate\n" + 
									 	  "END ) AS average_pay\n" + 
									 	  "FROM job\n" + 
									 	  "GROUP BY store_id\n" + 
									 	  "ORDER BY store_id DESC");
			rset = pstmt.executeQuery();
			System.out.printf("store_id%3s average_pay%3s%n", "","");
			while (rset.next())
				System.out.printf("%-11s %-12.3f%n", rset.getString("store_id"), rset.getFloat(2));

		} catch(SQLException e) {
			System.err.println(e);
		}
	}
	
	/*List the required skills of a given pos_code in a readable format*/
	public static void query4() throws SQLException
	{
		try
		{
			pstmt = con.prepareStatement("SELECT DISTINCT pos_code FROM skill NATURAL JOIN requires");
			rset = pstmt.executeQuery();
			System.out.print("Available pos_code: ");
			while(rset.next())
				System.out.printf("%s ", rset.getString("pos_code"));
			System.out.println();
			System.out.println("Which position would you like?");
			int position = in.nextInt();
			pstmt = con.prepareStatement("SELECT sk_code, sk_title, sk_level\n" + 
										 "FROM skill NATURAL JOIN requires\n" + 
										 "WHERE pos_code = ?");
			pstmt.setInt(1, position);
			rset = pstmt.executeQuery();
			System.out.printf("sk_code%4s sk_title%12s sk_level%2s", "","","");
			System.out.println();
			while (rset.next())
				System.out.printf("%-11s %-20s %s%n", rset.getString("sk_code"), rset.getString("sk_title"), rset.getString("sk_level"));

		} catch(SQLException e) {
			System.err.println(e);
		}
	}
	/*Given a person's identifier, list this person's skills in a readable format. */
	public static void query5() throws SQLException
	{
		try
		{
			pstmt = con.prepareStatement("SELECT DISTINCT per_id FROM has_skill NATURAL JOIN requires");
			rset = pstmt.executeQuery();
			System.out.print("Available per_id: ");
			while(rset.next())
				System.out.printf("%s ", rset.getString("per_id"));
			System.out.println();
			System.out.println("Which person would you like to query?");
			int per_id = in.nextInt();
			pstmt = con.prepareStatement("SELECT sk_code, sk_title, sk_level\n" + 
									 	 "FROM has_skill NATURAL JOIN skill\n" + 
										 "WHERE per_id = ?");
			pstmt.setInt(1, per_id);
			rset = pstmt.executeQuery();
			System.out.printf("sk_code%4s sk_title%12s sk_level%2s", "","","");
			System.out.println();
			while (rset.next())
				System.out.printf("%-11s %-20s %s%n", rset.getString(1), rset.getString(2), rset.getString(3));

		} catch(SQLException e) {
			System.err.println(e);
		}
	}
	
	/*Given a person's identifier, list a person's missing skills for a specific pos_code in a readable format*/
	public static void query6() throws SQLException
	{
		try
		{
			pstmt = con.prepareStatement("SELECT DISTINCT per_id FROM has_skill"); 
			rset = pstmt.executeQuery();
			System.out.print("Available per_id: ");
			while(rset.next())
				System.out.printf("%s ", rset.getString("per_id"));
			System.out.println();
			System.out.println("Which person would you like to query?");
			int per_id = in.nextInt();

			pstmt = con.prepareStatement("SELECT DISTINCT pos_code FROM requires");
			rset = pstmt.executeQuery();
			System.out.print("Available pos_code: ");
			while(rset.next())
				System.out.printf("%s ", rset.getString("pos_code"));
			System.out.println();
			System.out.println("Which position would you like to query?");
			int position = in.nextInt();	
			System.out.println();
			
			pstmt = con.prepareStatement("SELECT sk_code, sk_title, sk_level\n" + 
										 "FROM skill NATURAL JOIN (( SELECT sk_code\n" + 
										 							"FROM requires\n" + 
										 							"WHERE pos_code = ?)\n" + 
										 							"EXCEPT\n" + 
										 							"(SELECT sk_code\n" + 
										 							"FROM has_skill \n" + 
									     							"WHERE per_id = ?))");
			pstmt.setInt(1, position);
			pstmt.setInt(2, per_id);
			rset = pstmt.executeQuery();
			System.out.printf("sk_code%4s sk_title%12s sk_level%2s", "","","");
			System.out.println();
			while (rset.next())
				System.out.printf("%-11s %-20s %s%n", rset.getString(1), rset.getString(2), rset.getString(3));

		} catch(SQLException e) {
			System.err.println(e);
		}
		
	}
	
	/*List the total number and the total sales ($) of every item in a given period of time (start date, end date) 
	 * in AZ in the descending order of sales. */
	public static void query7() throws SQLException
	{
		try
		{
			System.out.println("Available dates: ");
			pstmt = con.prepareStatement("SELECT s_year, s_month, s_day FROM sales ORDER BY s_year ASC");
			rset = pstmt.executeQuery();
			while(rset.next())
				System.out.printf("%s/%s/%s ", rset.getString(1), rset.getString(2), rset.getString(3));

			System.out.print("\nStart year? ");
			int s_year = in.nextInt();
			System.out.print(" start month? ");
			int s_month = in.nextInt();
			System.out.print(" start day? ");
			int s_day = in.nextInt();
			System.out.print(" end year ? ");
			int e_year = in.nextInt();
			System.out.print(" end month? ");
			int e_month = in.nextInt();
			System.out.print(" end day? ");
			int e_day = in.nextInt();

			pstmt = con.prepareStatement("SELECT item_num, SUM(quantity) AS num_of_items\n" + 
										 "FROM sales\n" + 
										 "WHERE s_year BETWEEN ? AND ? AND\n" + 
										 "s_month BETWEEN ? AND ? AND\n" + 
										 "s_day BETWEEN ? AND ?\n" +
										 "GROUP BY item_num\n" + 
										 "ORDER BY item_num DESC");
			pstmt.setInt(1, s_year);
			pstmt.setInt(2, e_year);
			pstmt.setInt(3, s_month);
			pstmt.setInt(4, e_month);
			pstmt.setInt(5, s_day);
			pstmt.setInt(6, e_day);
			rset = pstmt.executeQuery();
			System.out.printf("item_num%3s num_of_items","","");
			System.out.println();
			while (rset.next())
				System.out.printf("%-12s %s%n", rset.getString(1), rset.getString(2));
		} catch (SQLException e) {
			System.err.println(e);
		}
	}
	
	/*List the item_num, its title and the total profit that made the biggest profit for AZ in 2018. */
	public static void query8() throws SQLException
	{
		try
		{
			pstmt = con.prepareStatement("WITH total_profit (item_num, title, price, profit) AS (\n" + 
										"SELECT item_num, inventory.title, price, SUM(price)\n" + 
										"FROM sales JOIN inventory USING (item_num)\n" + 
										"WHERE s_year = 2018\n" + 
										"GROUP BY item_num, inventory.title, price),\n" + 
										"  biggest_profit(profit) AS (\n" + 
										"SELECT MAX(profit)\n" + 
										"FROM total_profit)\n" + 
										"SELECT item_num, title, price, profit\n" + 
										"FROM biggest_profit JOIN total_profit USING (profit)");
			rset = pstmt.executeQuery();
			System.out.printf("item_num%4s title%12s price%5s profit%n", "","","");
			while (rset.next())
				System.out.printf("%-12s %-17s %-10s %s%n", rset.getString(1), rset.getString(2), rset.getString(3), rset.getString(4));
		} catch (SQLException e) {
			System.err.println(e);
		}
	}
	
	/*Show the items for which the inventory is below the minimum level in AZ system. */
	public static void query9() throws SQLException
	{
		try
		{
			pstmt = con.prepareStatement ("SELECT item_num\n" + 
										  "FROM inventory\n" + 
										  "WHERE quantity<min_level");
			rset = pstmt.executeQuery();
			System.out.println("item_num");
			while(rset.next())
				System.out.printf("%s%n", rset.getString(1));
		} catch (SQLException e) {
			System.err.println(e);
		}
	}
	
	/*List the total sales in dollar to each customer of GV in 2018.*/
	public static void query10() throws SQLException
	{
		try
		{
			pstmt = con.prepareStatement(
					"SELECT cus_id, tot_sales\n" + 
					"FROM ( SELECT cus_id, SUM (sale_amount) AS tot_sales\n" + 
					"	FROM contract\n" + 
					"	WHERE c_y = 2018\n" + 
					"	GROUP BY cus_id)");
			rset = pstmt.executeQuery();
			System.out.printf("cus_id%6s tot_sales%n", "");
			while(rset.next())
				System.out.printf("%-12s %s%n", rset.getString(1), rset.getString(2));

		} catch (SQLException e) {
			System.err.println(e);
		}
	}
	
	/*Show m_code, m_name of the material(s) that GV purchased the most (measured by quantity) in the fourth 
	 * quarter of 2018*/
	public static void query11() throws SQLException
	{
		try
		{
			pstmt = con.prepareStatement(
					"WITH temp (m_code, sum_of_mats) AS (\n" + 
					"	SELECT m_code, SUM(quantity) AS sum_of_mats\n" + 
					"	FROM purchase NATURAL JOIN PurchaseLine \n" + 
					"	WHERE b_y = 2018 AND b_m BETWEEN 10 AND 12 AND b_d BETWEEN 1 AND 31\n" + 
					"	GROUP BY m_code),\n" + 
					"     temp2 (m_code, max_of_sums) AS (\n" + 
					"	SELECT m_code, sum_of_mats\n" + 
					"	FROM temp JOIN (SELECT MAX(sum_of_mats) AS sum_of_mats\n" + 
					"			FROM temp) USING (sum_of_mats))\n" + 
					"SELECT m_code, m_name\n" + 
					"FROM material NATURAL JOIN temp2");
			rset = pstmt.executeQuery();
			System.out.printf("m_code%3s m_name%n","");
			while(rset.next())
				System.out.printf("%-9s %s%n", rset.getString(1), rset.getString(2));
			
		} catch (SQLException e) {
			System.err.println(e);
		}
	}
	
	/*Show the factory name that made the most total quantity of the product that was sold the most in 2018. */
	public static void query12() throws SQLException
	{
		try
		{
			pstmt = con.prepareStatement(
					"WITH sold_sum_quantities (p_code, sum_quan) AS (\n" + 
					"	SELECT p_code, SUM(quantity)\n" + 
					"	FROM lineItem NATURAL JOIN contract\n" + 
					"	WHERE c_y = '2018'\n" + 
					"	GROUP BY p_code),\n" + 
					"     max_of_sold (p_code) AS (\n" + 
					"	SELECT p_code\n" + 
					"	FROM sold_sum_quantities JOIN (SELECT MAX(sum_quan) AS sum_quan\n" + 
					"					FROM sold_sum_quantities) USING (sum_quan)),\n" + 
					"     sum_made_sold (fac_name, p_code, sum_quan) AS (\n" + 
					"	SELECT fac_name, p_code, SUM(quantity) AS sum_quan\n" + 
					"	FROM factory NATURAL JOIN makes NATURAL JOIN max_of_sold \n" + 
					"	GROUP BY fac_name, p_code)\n" + 
					"SELECT fac_name\n" + 
					"FROM (SELECT MAX(sum_quan) AS sum_quan\n" + 
					"	FROM sum_made_sold) JOIN sum_made_sold USING (sum_quan)");
			rset = pstmt.executeQuery();
			System.out.println("fac_name");
			while(rset.next())
				System.out.printf("%s%n", rset.getString(1));
		} catch (SQLException e) {
			System.err.println(e);
		}
	}
	
	/*Given a person's identifier, find all the jobs this person is currently holding and worked in the past.*/
	public static void query13() throws SQLException
	{
		try
		{
			System.out.print("People who worked/work: ");
			pstmt = con.prepareStatement ("SELECT DISTINCT per_id FROM works");
			rset = pstmt.executeQuery();
			while(rset.next())
				System.out.printf("%s ", rset.getString(1));
			pstmt = con.prepareStatement ("(SELECT job_code\n" + 
										  " FROM works\n" + 
										  " WHERE s_y IS NOT NULL AND per_id = ?)\n" + 
										  "UNION\n" + 
										  "(SELECT job_code\n" + 
										  " FROM works\n" + 
										  " WHERE s_y IS NULL AND per_id = ?)");
			System.out.println("\nWhich per_id? ");
			int per_id = in.nextInt();
			pstmt.setInt(1, per_id);
			pstmt.setInt(2, per_id);
			rset = pstmt.executeQuery();
			System.out.println("job_code");
			while(rset.next())
				System.out.printf("%s%n", rset.getString(1));
		} catch (SQLException e) {
			System.err.println(e);
		}
	}
	
	/*. In a local or national crisis, we need to find all the people who once held a position of the given pos_code. 
	 * List per_id, name, job title and the years the person worked in (starting year and ending year). */
	public static void query14()
	{
		try
		{
			System.out.print("Currently open positions: ");
			pstmt = con.prepareStatement ("SELECT DISTINCT pos_code FROM works NATURAL JOIN job");
			rset = pstmt.executeQuery();
			while(rset.next())
				System.out.printf("%s ", rset.getString(1));
			pstmt = con.prepareStatement ("WITH temp (per_id, title, s_m, s_d, s_y, e_m, e_d, e_y) AS (\n" + 
										  "	  SELECT per_id, j_title, s_m, s_d, s_y, e_m, e_d, e_y\n" + 
										  "	  FROM works NATURAL JOIN job\n" + 
										  "	  WHERE pos_code = ?)\n" + 
										  "SELECT per_id, last_name, first_name, title, s_m, s_d, s_y, e_m, e_d, e_y\n" + 
										  "FROM person NATURAL JOIN temp");
			System.out.println("\nWhich pos_code? ");
			int pos_code = in.nextInt();
			pstmt.setInt(1, pos_code);
			rset = pstmt.executeQuery();
			System.out.println();
			while(rset.next())
				System.out.printf("%s %s %s %s %s/%s/%s %s/%s/%s %n",
														 rset.getString(1), rset.getString(2), rset.getString(3), rset.getString(4), 
														 rset.getString(5), rset.getString(6), rset.getString(7), rset.getString(8),
														 rset.getString(9), rset.getString(10));
		} catch (SQLException e) {
			System.err.println(e);
		}
	}
	public static void query15()
	{
		try
		{
			System.out.print("Currently open positions: ");
			pstmt = con.prepareStatement ("SELECT DISTINCT pos_code FROM job"); 
			rset = pstmt.executeQuery();
			while(rset.next())
				System.out.printf("%s ", rset.getString(1));
			pstmt = con.prepareStatement ("WITH unemployed_people (per_id) AS (\n" + 
										"	(SELECT per_id\n" + 
										"	 FROM works)\n" + 
										"	 EXCEPT\n" + 
										"	(SELECT per_id\n" + 
										"	 FROM works\n" + 
										"	 WHERE e_y IS NULL))\n" + 
										"SELECT per_id\n" + 
										"FROM unemployed_people NATURAL JOIN works NATURAL JOIN job\n" + 
										"WHERE pos_code = ?");
			System.out.println("\nWhich pos_code? ");
			int pos_code = in.nextInt();
			pstmt.setInt(1, pos_code);
			rset = pstmt.executeQuery();
			System.out.println();
			while(rset.next())
				System.out.printf("%s%n", rset.getString(1));
		} catch (SQLException e) {
			System.err.println(e);
		}
	}
	public static void query16()
	{
		try 
		{
			pstmt = con.prepareStatement (
					"WITH avg_job_pay (parent_id, avg_pay) AS (\n" + 
					"	SELECT parent_id, AVG(ann_pay) AS avg_pay\n" + 
					"	FROM sub_ind NATURAL JOIN company NATURAL JOIN GICS NATURAL JOIN (\n" + 
					"		SELECT comp_id, job_code, CASE WHEN pay_type = 'wage' THEN pay_rate*1920 ELSE pay_rate END AS ann_pay\n" + 
					"		FROM job NATURAL JOIN company)\n" + 
					"		GROUP BY parent_id),\n" + 
					"     min_job_pay (parent_id, min_pay) AS (\n" + 
					"	SELECT parent_id, MIN(ann_pay) AS min_ay\n" + 
					"	FROM sub_ind NATURAL JOIN company NATURAL JOIN GICS NATURAL JOIN (\n" + 
					"		SELECT comp_id, job_code, CASE WHEN pay_type = 'wage' THEN pay_rate*1920 ELSE pay_rate END AS ann_pay\n" + 
					"		FROM job NATURAL JOIN company)\n" + 
					"		GROUP BY parent_id)\n" + 
					"SELECT parent_id, avg_pay, max_pay, min_pay\n" + 
					"FROM avg_job_pay NATURAL JOIN max_job_pay NATURAL JOIN min_job_pay");
			rset = pstmt.executeQuery();
			System.out.printf("parent_id%3s avg_pay%3s max_pay%3s min_pay%3s%n", "","","","");
			while(rset.next())
				System.out.printf("%-12s %-10.3f %-10s %-10s%n", rset.getString(1), rset.getFloat(2), rset.getString(3), rset.getString(4));
		} catch (SQLException e) {
			System.err.println(e);
		}
		
	}
	public static void query17()
	{
		char option;
		System.out.println("Which query that is under 17? (a/b/c)");
		option = in.next().charAt(0);
		if (option=='a')
			query17a();
		else if (option=='b')
			query17b();
		else if (option=='c')
			query17c();
	}
	public static void query17a()
	{
		System.out.println("Running 17a");
		try
		{
			pstmt = con.prepareStatement (	
					"WITH biggest_employer (comp_id, emp_count) AS (\n" + 
					"	SELECT comp_id, COUNT (distinct per_id) AS emp_count\n" + 
					"	FROM job NATURAL JOIN works\n" + 
					"	WHERE works.e_y IS NULL\n" + 
					"	GROUP BY comp_id )\n" + 
					"SELECT comp_id, emp_count\n" + 
					"FROM ( 	SELECT MAX(emp_count) AS emp_count\n" + 
				    "			FROM biggest_employer ) JOIN biggest_employer USING (emp_count)");
			rset = pstmt.executeQuery();
			while (rset.next())
				System.out.printf("%s %s%n", rset.getString(1), rset.getString(2));
		} catch (SQLException e) {
			System.err.println(e);
		}	
	}
	public static void query17b()
	{
		System.out.println("Running 17b");
		try
		{
			pstmt = con.prepareStatement (	
					"WITH biggest_industry (parent_id, emp_count) AS (\n" + 
					"	SELECT parent_id, COUNT(DISTINCT per_id) AS emp_count\n" + 
					"	FROM works NATURAL JOIN job NATURAL JOIN sub_ind NATURAL JOIN company NATURAL JOIN GICS\n" + 
					"	WHERE works.e_y IS NULL\n" + 
					"	GROUP BY parent_id)\n" + 
					"SELECT parent_id, emp_count\n" + 
					"FROM (	SELECT MAX(emp_count) AS emp_count\n" + 
					"	    FROM biggest_industry) JOIN biggest_industry USING (emp_count)");
			rset = pstmt.executeQuery();
			while (rset.next())
				System.out.printf("%s %s%n", rset.getString(1), rset.getString(2));
		} catch (SQLException e) {
			System.err.println(e);
		}	
	}
	public static void query17c()
	{
		System.out.println("Running 17c");
		try
		{
			pstmt = con.prepareStatement (	
					"WITH biggest_industry_group (industry_group, emp_count) AS (\n" + 
					"	SELECT industry_group, COUNT(DISTINCT per_id) AS emp_count\n" + 
					"	FROM (job NATURAL JOIN works) NATURAL JOIN company\n" + 
					"	WHERE e_y IS NULL\n" + 
					"	GROUP BY industry_group )\n" + 
					"SELECT industry_group, emp_count\n" + 
					"FROM (	SELECT MAX(emp_count) AS emp_count\n" + 
					"		FROM biggest_industry_group ) JOIN biggest_industry_group USING (emp_count)");
			rset = pstmt.executeQuery();
			while (rset.next())
				System.out.printf("%s %s%n", rset.getString(1), rset.getString(2));
		} catch (SQLException e) {
			System.err.println(e);
		}	
	}
	public static void query18()
	{
		try
		{
			pstmt = con.prepareStatement (
					"SELECT parent_id, COUNT (DISTINCT per_id) AS num_of_employees\n" + 
					"FROM (sub_ind NATURAL JOIN company NATURAL JOIN GICS) NATURAL JOIN works\n" + 
					"WHERE e_y IS NULL\n" + 
					"GROUP BY parent_id");
			rset = pstmt.executeQuery();
			while (rset.next())
				System.out.printf("%s %s%n", rset.getString(1), rset.getString(1));
		} catch(SQLException e) {
			System.err.println(e);
		}

	}
	public static void query19()
	{
		try 
		{
			System.out.print("Current positions from requires: ");
			pstmt = con.prepareStatement ("SELECT DISTINCT pos_code FROM requires");
			rset = pstmt.executeQuery();
			while(rset.next())
				System.out.printf("%s ", rset.getString(1));
			System.out.println("\nWhich position would you like?");
			int pos_code = in.nextInt();

			System.out.print("\nCurrent people from has_skill: ");
			pstmt = con.prepareStatement ("SELECT DISTINCT per_id FROM has_skill");
			rset = pstmt.executeQuery();
			while(rset.next())
				System.out.printf("%s ", rset.getString(1));
			System.out.println("\nWhich person would you like?");
			int per_id = in.nextInt();
			pstmt = con.prepareStatement(
					"WITH missing_skills (sk_code) AS (\n" + 
					"      	SELECT sk_code\n" + 
					"	FROM requires\n" + 
					"      	WHERE pos_code = ? \n" + 
					"      	EXCEPT\n" + 
					"      	SELECT sk_code\n" + 
					"      	FROM has_skill\n" + 
					"      	WHERE per_id = ?)\n" + 
					"SELECT c_code, c_title\n" + 
					"FROM course \n" + 
					"WHERE NOT EXISTS ( \n" + 
					"	SELECT sk_code\n" + 
					"	FROM missing_skills\n" + 
					"	EXCEPT\n" + 
					"	SELECT sk_code\n" + 
					"	FROM teaches\n" + 
					"	WHERE course.c_code = teaches.c_code)");
			pstmt.setInt(1, pos_code);
			pstmt.setInt(2, per_id);
			rset = pstmt.executeQuery();
			while (rset.next())
				System.out.printf("%s %s%n", rset.getString(1), rset.getString(2));

		} catch (SQLException e) {
			System.err.println(e);
		}
		
	}
	public static void query20()
	{
		try
		{
			System.out.print("Current people from person: ");
			pstmt = con.prepareStatement ("SELECT DISTINCT per_id FROM person");
			rset = pstmt.executeQuery();
			while(rset.next())
				System.out.printf("%s ", rset.getString(1));
			System.out.println("\nWhich person would you like?");
			int per_id = in.nextInt();
			pstmt = con.prepareStatement (
					"WITH qual_pos (pos_code) AS (\n" + 
					"	SELECT P.pos_code\n" + 
					"	FROM requires P\n" + 
					"	WHERE NOT EXISTS ( \n" + 
					"		SELECT R.sk_code\n" + 
					"		FROM requires R\n" + 
					"		WHERE P.pos_code = R.pos_code\n" + 
					"		EXCEPT\n" + 
					"		SELECT HS.sk_code\n" + 
					"		FROM has_skill HS\n" + 
					"		WHERE HS.per_id = ?)),\n" + 
					"     qual_pos_pay (pos_code, pay_rate, pay_type) AS (\n" + 
					"	SELECT pos_code, pay_rate, pay_type\n" + 
					"	FROM (requires NATURAL JOIN qual_pos) NATURAL JOIN JOB)\n" + 
					"SELECT pos_code, (CASE \n" + 
					"			WHEN pay_type='wage' THEN pay_rate*1920\n" + 
					"			ELSE pay_rate\n" + 
					"			END) AS best_pay_rate\n" + 
					"FROM (SELECT MAX(pay_rate) AS pay_rate\n" + 
					"	FROM qual_pos_pay) JOIN qual_pos_pay USING (pay_rate)");

			pstmt.setInt(1, per_id);
			rset = pstmt.executeQuery();
			while (rset.next())
				System.out.printf("%s %s%n", rset.getString(1), rset.getString(2));

		} catch (SQLException e) {
			System.err.println(e);
		}
	}
	public static void query21()
	{
		try
		{
			pstmt = con.prepareStatement("SELECT DISTINCT pos_code FROM requires order by pos_code");
			rset = pstmt.executeQuery();
			System.out.printf("Available pos_code from requires: ");
			while (rset.next())
					System.out.printf("%s ", rset.getString(1));
			pstmt = con.prepareStatement(
					"WITH temp (per_id) AS (\n" + 
					"	SELECT per_id\n" + 
					"	FROM has_skill HS\n" + 
					"	WHERE NOT EXISTS (\n" + 
					"		SELECT sk_code\n" + 
					"		FROM requires\n" + 
					"		WHERE pos_code = ?\n" + 
					"		EXCEPT\n" + 
					"		SELECT sk_code\n" + 
					"		FROM has_skill S\n" + 
					"		WHERE HS.per_id = S.per_id))\n" + 
					"SELECT DISTINCT per_id, last_name, first_name, email\n" + 
					"FROM person natural join temp");
			System.out.println("\nWhich pos_code? ");
			int pos_code = in.nextInt();
			pstmt.setInt(1, pos_code);
			rset = pstmt.executeQuery();
			while (rset.next())
				System.out.printf("%s %s %s%n", rset.getString(2), rset.getString(3), rset.getString(4));
		} catch(SQLException e) {
			System.err.println(e);
		}
	}
	
	public static void query22()
	{
		try
		{
			pstmt = con.prepareStatement("SELECT DISTINCT pos_code FROM requires ORDER BY pos_code");
			rset = pstmt.executeQuery();
			System.out.print("Available pos_code from requires: ");
			while(rset.next())
				System.out.printf("%s ", rset.getString(1));
			pstmt = con.prepareStatement (
					"WITH person_skill_count (per_id, sk_count) AS (\n" + 
					"	SELECT per_id, COUNT(sk_code)\n" + 
					"	FROM has_skill NATURAL JOIN requires\n" + 
					"	WHERE pos_code = ?\n" + 
					"	GROUP BY per_id),\n" + 
					"    required_skill_count(k) AS (\n" + 
					"	SELECT COUNT(sk_code)\n" + 
					"	FROM requires\n" + 
					"	WHERE pos_code = ?)\n" + 
					"SELECT DISTINCT per_id, sk_count\n" + 
					"FROM person_skill_count, required_skill_count\n" + 
					"WHERE sk_count > k-4");
			System.out.println("\nWhich pos_code? ");
			int pos_code = in.nextInt();
			pstmt.setInt(1, pos_code);
			pstmt.setInt(2, pos_code);
			rset = pstmt.executeQuery();
			while(rset.next())
				System.out.printf("%s %s%n", rset.getString(1), rset.getString(2));
			
		} catch (SQLException e) {
			System.err.println(e);
			e.printStackTrace();
		}
	}
	public static void query23()
	{
		try
		{
			pstmt = con.prepareStatement("SELECT DISTINCT pos_code FROM requires ORDER BY pos_code");
			rset = pstmt.executeQuery();
			System.out.print("Available pos_code from requires: ");
			while(rset.next())
				System.out.printf("%s ", rset.getString(1));
			pstmt = con.prepareStatement (
					"WITH person_skill_count (per_id, sk_count) AS (\n" + 
					"	SELECT per_id, COUNT(sk_code) AS sk_count\n" + 
					"	FROM has_skill NATURAL JOIN requires\n" + 
					"	WHERE pos_code = ?\n" + 
					"	GROUP BY per_id),\n" + 
					"     required_skill_count (sk_count) AS (\n" + 
					"	SELECT COUNT(*)\n" + 
					"	FROM requires\n" + 
					"	WHERE pos_code = ?),\n" + 
					"     per_missing_skill_count (per_id, sk_count) AS (\n" + 
					"	SELECT per_id, (RSC.sk_count-PSC.sk_count) AS missing_skill_count\n" + 
					"	FROM person_skill_count PSC, required_skill_count RSC),\n" + 
					"     least_missing_skills (per_id, least_missing) AS (\n" + 
					"	SELECT per_id, sk_count\n" + 
					"	FROM (SELECT MIN(sk_count) AS sk_count\n" + 
					"	      FROM per_missing_skill_count) JOIN per_missing_skill_count USING (sk_count))\n" + 
					"SELECT per_id, last_name, first_name, least_missing\n" + 
					"FROM person NATURAL JOIN least_missing_skills");
			System.out.println("\nWhich pos_code? ");
			int pos_code = in.nextInt();
			pstmt.setInt(1, pos_code);
			pstmt.setInt(2, pos_code);
			rset = pstmt.executeQuery();
			while(rset.next())
				System.out.printf("%s %s %s %s%n", rset.getString(1), rset.getString(2), rset.getString(3), rset.getString(4));
		} catch(SQLException e) {
			System.err.println(e);
		}
		
	}
	public static void query24()
	{
		try
		{
			pstmt = con.prepareStatement("SELECT DISTINCT pos_code FROM requires ORDER BY pos_code");
			rset = pstmt.executeQuery();
			System.out.print("Available pos_code from requires: ");
			while(rset.next())
				System.out.printf("%s ", rset.getString(1));
			pstmt = con.prepareStatement (
					"WITH person_skill_count (per_id, sk_count) AS (\n" + 
					"	SELECT per_id, COUNT(sk_code)\n" + 
					"	FROM has_skill NATURAL JOIN requires\n" + 
					"	WHERE pos_code = ?\n" + 
					"	GROUP BY per_id),\n" + 
					"     required_skill_count (k) AS (\n" + 
					"	SELECT COUNT(*)\n" + 
					"	FROM requires\n" + 
					"	WHERE pos_code = ?),\n" + 
					"     missing_k_list (per_id, sk_count) AS (\n" + 
					"	SELECT per_id, sk_count\n" + 
					"	FROM person_skill_count, required_skill_count\n" + 
					"	WHERE sk_count > k-4),\n" + 
					"     req_skill_pos (pos_code, sk_code) AS (\n" + 
					"	SELECT pos_code, sk_code\n" + 
					"	FROM requires\n" + 
					"	WHERE pos_code = ?),\n" + 
					"     num_of_people (per_id, sk_code) AS (\n" + 
					"	SELECT per_id, sk_code\n" + 
					"	FROM missing_k_list, req_skill_pos\n" + 
					"	EXCEPT\n" + 
					"	SELECT per_id, sk_code\n" + 
					"	FROM has_skill)\n" + 
					"SELECT sk_code, COUNT(per_id) AS people_count\n" + 
					"FROM num_of_people\n" + 
					"GROUP BY sk_code\n" + 
					"ORDER BY COUNT(per_id)");
			System.out.println("\nWhich pos_code? ");
			int pos_code = in.nextInt();
			pstmt.setInt(1, pos_code);
			pstmt.setInt(2, pos_code);
			pstmt.setInt(3, pos_code);
			rset = pstmt.executeQuery();
			while(rset.next())
				System.out.printf("%s %s%n", rset.getString(1), rset.getString(2));

			
		} catch(SQLException e) {
			System.err.println(e);
		}
	}
	/*Find out the number of the workers whose earnings increased in a specific industry group 
	 * (use attribute "industry group" in table Company). 
	 * [Hint: earning change = the sum of a person's current earnings – the pay of the person's the last previous job.]*/
	public static void query25()
	{
		try
		{
			pstmt = con.prepareStatement(
					"WITH max_year (per_id, e_y) AS (\n" + 
					"	SELECT per_id, MAX(e_y)\n" + 
					"	FROM works\n" + 
					"	GROUP BY per_id),\n" + 
					"     max_month (per_id, e_y, e_m) AS (\n" + 
					"	SELECT per_id, e_y, MAX(e_m)\n" + 
					"	FROM max_year NATURAL JOIN works\n" + 
					"	GROUP BY per_id, e_y),\n" + 
					"     max_day (per_id, e_y, e_m, e_d) AS (\n" + 
					"	SELECT per_id, e_y, e_m, MAX(e_d)\n" + 
					"	FROM max_month NATURAL JOIN works\n" + 
					"	GROUP BY per_id, e_y, e_m),\n" + 
					"     prev_last_job_earnings (per_id, prev_pay_rate) AS (\n" + 
					"	SELECT per_id, CASE WHEN pay_type = 'wage' THEN pay_rate*1920 ELSE pay_rate END AS pay_rate\n" + 
					"	FROM job NATURAL JOIN works NATURAL JOIN max_day\n" + 
					"	ORDER BY per_id),\n" + 
					"     curr_job_earnings (per_id, pay_rate) AS (\n" + 
					"	SELECT per_id, SUM(CASE WHEN pay_type = 'wage' THEN pay_rate*1920 ELSE pay_rate END)\n" + 
					"	FROM job NATURAL JOIN works prev_last_job_earnings\n" + 
					"	GROUP BY per_id),\n" + 
					"     increased_earnings (per_id, earnings) AS (\n" + 
					"	SELECT per_id, pay_rate-prev_pay_rate\n" + 
					"	FROM job NATURAL JOIN works NATURAL JOIN prev_last_job_earnings\n" + 
					"	WHERE pay_Rate-prev_pay_rate > 0)\n" + 
					"SELECT industry_group, COUNT(DISTINCT per_id) AS num_of_people\n" + 
					"FROM company NATURAL JOIN job NATURAL JOIN works NATURAL JOIN increased_earnings \n" + 
					"GROUP BY industry_group");
			rset = pstmt.executeQuery();
			while(rset.next())
				System.out.printf("%s %s%n", rset.getString(1), rset.getString(2));
		} catch (SQLException e) {
			System.err.println(e);
		}
	}
	
	public static void query26()
	{
		try
		{
			pstmt = con.prepareStatement(
				"WITH num_qualified_ppl (pos_code, num_qual) AS (\n" + 
				"	SELECT DISTINCT R.pos_code, COUNT(DISTINCT per_id)\n" + 
				"	FROM requires R, has_skill S\n" + 
				"	WHERE NOT EXISTS (\n" + 
				"		SELECT sk_code \n" + 
				"		FROM requires RS\n" + 
				"		WHERE R.pos_code = RS.pos_code\n" + 
				"		EXCEPT\n" + 
				"		SELECT sk_code\n" + 
				"		FROM has_skill HS\n" + 
				"		WHERE S.per_id = HS.per_id)\n" + 
				"	GROUP BY R.pos_code),\n" + 
				"     max_qualified_ppl (pos_code) AS (\n" + 
				"	SELECT pos_code\n" + 
				"	FROM num_qualified_ppl\n" + 
				"	WHERE num_qual = (SELECT MAX(num_qual) FROM num_qualified_ppl)),\n" + 
				"     num_working_ppl (pos_code, num_qual) AS (\n" + 
				"	SELECT pos_code, COUNT(DISTINCT per_id)\n" + 
				"	FROM works NATURAL JOIN job WHERE e_y IS NULL\n" + 
				"	GROUP BY pos_code),\n" + 
				"     min_working_ppl (pos_code) AS (\n" + 
				"	SELECT pos_code\n" + 
				"	FROM num_working_ppl\n" + 
				"	WHERE num_qual = (SELECT MIN(num_qual) FROM num_working_ppl))\n" + 
				"SELECT pos_code\n" + 
				"FROM max_qualified_ppl NATURAL JOIN min_working_ppl");
			rset = pstmt.executeQuery();
			while(rset.next())
				System.out.printf("%s%n", rset.getString(1));
		} catch(SQLException e) {
			System.err.println(e);
		}
	}
	
	public static void query27()
	{
		try
		{
			System.out.println("Creating table course_set.");
			pstmt = con.prepareStatement(
					"CREATE TABLE course_set \n" + 
					"(\n" + 
					"	cset_id SERIAL PRIMARY KEY,\n" + 
					"	c_code1 INTEGER, c_code2 INTEGER, c_code3 INTEGER,\n" + 
					"	setsize INTEGER\n" + 
					")");
			pstmt.executeUpdate();
			System.out.println("course_set created.");
			
			System.out.println("Inserting into course_set.");
			pstmt = con.prepareStatement (
					"INSERT INTO course_set (c_code1, c_code2, c_code3, setsize)\n" + 
					"	SELECT C1.c_code, C2.c_code, C3.c_code, 3\n" + 
					"	FROM course C1, course C2, course C3\n" + 
					"	WHERE C1.c_code < C2.c_code AND C2.c_code < C3.c_code");
			pstmt.executeUpdate();
			System.out.println("Successfully inserted into course_set.");
			pstmt = con.prepareStatement(
				"WITH num_qualified_ppl (pos_code, num_qual) AS (\n" + 
				"	SELECT DISTINCT R.pos_code, COUNT(DISTINCT per_id)\n" + 
				"	FROM requires R, has_skill S\n" + 
				"	WHERE NOT EXISTS (\n" + 
				"		SELECT sk_code \n" + 
				"		FROM requires RS\n" + 
				"		WHERE R.pos_code = RS.pos_code\n" + 
				"		EXCEPT\n" + 
				"		SELECT sk_code\n" + 
				"		FROM has_skill HS\n" + 
				"		WHERE S.per_id = HS.per_id)\n" + 
				"	GROUP BY R.pos_code),\n" + 
				"     max_qualified_ppl (pos_code) AS (\n" + 
				"	SELECT pos_code\n" + 
				"	FROM num_qualified_ppl\n" + 
				"	WHERE num_qual = (SELECT MAX(num_qual) FROM num_qualified_ppl)),\n" + 
				"     num_working_ppl (pos_code, num_qual) AS (\n" + 
				"	SELECT pos_code, COUNT(DISTINCT per_id)\n" + 
				"	FROM works NATURAL JOIN job WHERE e_y IS NULL\n" + 
				"	GROUP BY pos_code),\n" + 
				"     min_working_ppl (pos_code) AS (\n" + 
				"	SELECT pos_code\n" + 
				"	FROM num_working_ppl\n" + 
				"	WHERE num_qual = (SELECT MIN(num_qual) FROM num_working_ppl)),\n" + 
				"     sk_codes (pos_code, sk_code) AS (\n" + 
				"	SELECT pos_code,sk_code\n" + 
				"	FROM max_qualified_ppl NATURAL JOIN min_working_ppl NATURAL JOIN requires),\n" + 
				"     course_set_skill (cset_id, c_code1, c_code2, c_code3, sk_code) AS (\n" + 
				"	SELECT cset_id, CSet.c_code1, CSet.c_code2, CSet.c_code3, sk_code\n" + 
				"	FROM course_set CSet JOIN teaches CS ON CSet.c_code1=CS.c_code\n" + 
				"	UNION \n" + 
				"	SELECT cset_id, CSet.c_code1, CSet.c_code2, CSet.c_code3, sk_code\n" + 
				"	FROM course_set CSet JOIN teaches CS ON CSet.c_code2=CS.c_code\n" + 
				"	UNION\n" + 
				"	SELECT cset_id, CSet.c_code1, CSet.c_code2, CSet.c_code3, sk_code\n" + 
				"	FROM course_set CSet JOIN teaches CS ON CSet.c_code3=CS.c_code),\n" + 
				"      cover_cset (cset_id) AS (\n" + 
				"	SELECT cset_id\n" + 
				"	FROM course_set CSet\n" + 
				"	WHERE NOT EXISTS(\n" + 
				"		SELECT sk_code\n" + 
				"		FROM sk_codes\n" + 
				"		EXCEPT\n" + 
				"		SELECT sk_code\n" + 
				"		FROM course_set_skill CSSk\n" + 
				"		WHERE CSSk.cset_id = CSet.cset_id))\n" + 
				"SELECT DISTINCT cset_id, c_code1, c_code2, c_code3\n" + 
				"FROM course_set_skill NATURAL JOIN cover_cset\n" + 
				"ORDER BY cset_id");
			rset = pstmt.executeQuery();
			System.out.printf("cset_id%3s course1%3s course2%3s course3%n","","","");
			while(rset.next())
				System.out.printf("%-10s %-10s %-10s %s%n", rset.getString(1), rset.getString(2),
									rset.getString(3), rset.getString(4));
				
			pstmt = con.prepareStatement(
					"DROP TABLE course_set");
			pstmt.executeUpdate();
			System.out.println("course_set dropped.");

		} catch (SQLException e) {
			System.err.println(e);
		}
	}
	
	public static void AZhires()
	{
		int store_id = 0;
		int per_id = 0;
		int c_code = 0;
		try
		{
 			System.out.println("Connecting to Database LD");
			con = DriverManager.getConnection(url, user2, password);
			System.out.println("Successfully Connected");
			pstmt = con.prepareStatement("SELECT per_id FROM (SELECT per_id, COUNT(sk_code) AS skill_count FROM has_skill\n"
					+ " 	GROUP BY per_id) WHERE per_id NOT IN (\n"
					+ "		SELECT per_id \n"
					+ "		FROM works \n"
					+ "		WHERE end_date IS NULL) \n"
					+ "ORDER BY skill_count DESC");
            ResultSet hiring_per_id = pstmt.executeQuery();
			System.out.println("\nAvailable per_id (NOTE: the first half of the list has 5-10 skills while the other half has 1-5):");
			while(hiring_per_id.next())
				System.out.printf("%d ", hiring_per_id.getInt(1));
		    System.out.println("\nWhich per_id? ");
		    per_id = in.nextInt();


			// Fetch person info from LD
            pstmt = con.prepareStatement("SELECT * FROM person WHERE per_id = ?");
            pstmt.setInt(1, per_id);
            ResultSet person_info = pstmt.executeQuery();

			// Fetch current skills from LD
		    pstmt = con.prepareStatement("SELECT * FROM has_skill WHERE per_id = ?");
		    pstmt.setInt(1, per_id);
            ResultSet person_skills = pstmt.executeQuery();

			System.out.println("Connecting to Database AZ");
			con = DriverManager.getConnection(url, user1, password);
			System.out.println("Successfully Connected");

			// Insert person into AZ
			System.out.printf("Inserting per_id: %d info into person.%n", per_id);
			while(person_info.next())
			{
				pstmt = con.prepareStatement(
						"INSERT INTO person values (?,?,?,?,?,?,?)");
				pstmt.setInt(1, person_info.getInt(1));
				pstmt.setString(2, person_info.getString(2));
				pstmt.setString(3, person_info.getString(3));
				pstmt.setString(4, person_info.getString(4));
				pstmt.setString(5, person_info.getString(5));
				pstmt.setString(6, person_info.getString(6));
				pstmt.setString(7, person_info.getString(7));
				pstmt.executeUpdate();
			}
			System.out.println("Successfully inserted.");

			// Insert self-claimed skills into AZ
			System.out.printf("Inserting per_id: %d current skills into has_skill.%n", per_id);
			while(person_skills.next())
			{
				pstmt = con.prepareStatement(
						"INSERT INTO has_skill values (?,?)");
				pstmt.setInt(1, person_skills.getInt(1));
				pstmt.setInt(2, person_skills.getInt(2));
				pstmt.executeUpdate();
			}
			System.out.println("Successfully inserted.");

			// STEP 2: Upload transcripts - input courses into Takes table
			System.out.println("\n=== Upload Transcripts ===");
			System.out.println("Enter course completions (enter -1 for c_code to finish):");

			while(true)
			{
				try
				{
					System.out.print("Course code (c_code): ");
					c_code = in.nextInt();
					if(c_code == -1) break;

					pstmt = con.prepareStatement(
							"INSERT INTO takes VALUES (?,?,?,?,?,?,?,?)");
					pstmt.setInt(1, per_id);
					pstmt.setInt(2, c_code);
					pstmt.setInt(3, 0);
					pstmt.setDate(4, null);
					pstmt.setInt(5, 0);
					pstmt.setString(6, null);
					pstmt.setString(7, null);
					pstmt.setBigDecimal(8, null);
					pstmt.executeUpdate();

					System.out.println("Course added successfully.\n");
				} catch (SQLException e) {
					if(e.getSQLState().equals("23503"))
						System.out.println("There is no course that teaches that skill."); 
					else if(e.getSQLState().equals("23505"))
						System.out.println("Cannot add a duplicate course.");
					else e.printStackTrace();
				}
			}

			// Show difference between transcript-derived skills vs self-claimed skills
			System.out.println("\n=== Analyzing Skills ===");
			System.out.println("Checking the difference between self-claimed skills and transcript skills...");

			pstmt = con.prepareStatement(
					"WITH transcript_skills AS (\n" + 
					"	SELECT DISTINCT sk_code\n" + 
					"	FROM takes NATURAL JOIN teaches\n" + 
					"	WHERE per_id = ?\n" + 
					"),\n" +
					"self_claimed_skills AS (\n" + 
					"	SELECT DISTINCT sk_code\n" + 
					"	FROM has_skill\n" + 
					"	WHERE per_id = ?\n" + 
					"),\n" +
					"verified_skills AS (\n" +
					"	SELECT sk_code FROM transcript_skills\n" +
					"	INTERSECT\n" +
					"	SELECT sk_code FROM self_claimed_skills\n" +
					"),\n" +
					"unverified_claims AS (\n" +
					"	SELECT sk_code FROM self_claimed_skills\n" +
					"	EXCEPT\n" +
					"	SELECT sk_code FROM transcript_skills\n" +
					")\n" +
					"SELECT \n" +
					"	(SELECT COUNT(*) FROM self_claimed_skills) as claimed_count,\n" +
					"	(SELECT COUNT(*) FROM transcript_skills) as transcript_count,\n" +
					"	(SELECT COUNT(*) FROM verified_skills) as verified_count,\n" +
					"	(SELECT COUNT(*) FROM unverified_claims) as unverified_count");
			pstmt.setInt(1, per_id);
			pstmt.setInt(2, per_id);
			ResultSet skill_analysis = pstmt.executeQuery();

			if(skill_analysis.next())
			{
				int claimed = skill_analysis.getInt("claimed_count");
				int transcript = skill_analysis.getInt("transcript_count");
				int verified = skill_analysis.getInt("verified_count");
				int unverified = skill_analysis.getInt("unverified_count");

				System.out.printf("Self-claimed skills: %d%n", claimed);
				System.out.printf("Skills from transcripts: %d%n", transcript);
				System.out.printf("Verified skills (overlap): %d%n", verified);
				System.out.printf("Unverified claims: %d%n", unverified);

				// If too many unverified claims, reject
				if(unverified > 5)
				{
					System.out.println("\nWARNING: This person has too many unverified skill claims.");
					System.out.println("Recommendation: Rescind job offer.");

					// Cleanup
					pstmt = con.prepareStatement("DELETE FROM person WHERE per_id = ?");
					pstmt.setInt(1, per_id);
					pstmt.executeUpdate();
					pstmt = con.prepareStatement("DELETE FROM takes WHERE per_id = ?");
					pstmt.setInt(1, per_id);
					pstmt.executeUpdate();
					pstmt = con.prepareStatement("DELETE FROM has_skill WHERE per_id = ?");
					pstmt.setInt(1, per_id);
					pstmt.executeUpdate();
					return;
				}
			}
			System.out.println("Skills verification passed.\n");

			// STEP 3: Populate Has_Skill with skills derived from transcripts
			System.out.println("=== Updating Skills Based on Transcripts ===");
			pstmt = con.prepareStatement(
					"SELECT DISTINCT sk_code "
					+ "FROM takes NATURAL JOIN teaches "
					+ "WHERE per_id = ? "
					+ "EXCEPT "
					+ "SELECT DISTINCT sk_code "
					+ "FROM has_skill "
					+ "WHERE per_id = ?");
			pstmt.setInt(1, per_id);
			pstmt.setInt(2, per_id);
			rset = pstmt.executeQuery();

			int added_skills = 0;
			System.out.printf("Adding skills derived from courses for person: %d%n", per_id);
			while(rset.next())
			{
				pstmt = con.prepareStatement(
						"INSERT INTO has_skill values (?,?)");
				pstmt.setInt(1, per_id);
				pstmt.setInt(2, rset.getInt(1));
				pstmt.executeUpdate();
				added_skills++;
			}
			System.out.printf("Added %d skills from transcripts.%n%n", added_skills);

			// STEP 4: Verify if person has required skills for position
			System.out.println("=== Job Application ===");
			System.out.print("Available job_codes and stores:\n");
			pstmt = con.prepareStatement(
					"SELECT job_code, store_id FROM job");
			rset = pstmt.executeQuery();
			System.out.printf("%njob_code%5s store_id%s%n","","");
			while(rset.next())
				System.out.printf("%-13d %d%n", rset.getInt(1), rset.getInt(2));
			System.out.println("\nWhich job_code? ");
			int job_code = in.nextInt();
			System.out.println("\nWhich store? ");
			store_id = in.nextInt();

			// Check for skill gaps
			pstmt = con.prepareStatement(
					"WITH skill_difference AS ("
				+	"SELECT sk_code FROM job NATURAL JOIN requires WHERE job_code = ? "
				+	"EXCEPT "
				+ 	"SELECT sk_code FROM has_skill WHERE per_id = ? )"
				+	"SELECT"
				+	"(SELECT COUNT(*) FROM skill_difference) AS skill_difference_count"
				);
			pstmt.setInt(1, job_code);
			pstmt.setInt(2, per_id);
			rset = pstmt.executeQuery();
			rset.next();

			if(rset.getInt("skill_difference_count") <= 2)
			{
				// No skill gap - hire the person
				System.out.println("\n✓ This person qualifies for the position.");
				pstmt = con.prepareStatement("SELECT * FROM job WHERE job_code = ?");
				pstmt.setInt(1, job_code);
				ResultSet job = pstmt.executeQuery();

				System.out.println("Hiring person...");
				pstmt = con.prepareStatement(
						"INSERT INTO works VALUES (?,?,?,?)" );
				pstmt.setInt(1, per_id);
				pstmt.setInt(2, job_code);
				pstmt.setDate(3, Date.valueOf(LocalDate.now()));
				pstmt.setNull(4, java.sql.Types.DATE);
				pstmt.executeUpdate();

				System.out.println("Successfully hired!\n");

				pstmt = con.prepareStatement(
						"SELECT * FROM takes where per_id = ? AND c_code = ?" );
				pstmt.setInt(1, per_id);
				pstmt.setInt(2, c_code);
				ResultSet takes_info = pstmt.executeQuery();

      	      	pstmt = con.prepareStatement("SELECT * FROM person WHERE per_id = ?");
      	      	pstmt.setInt(1, per_id);
      	      	person_info = pstmt.executeQuery();

		    	pstmt = con.prepareStatement("SELECT * FROM has_skill WHERE per_id = ?");
		    	pstmt.setInt(1, per_id);
      	      	person_skills = pstmt.executeQuery();
		    	pstmt = con.prepareStatement("SELECT * FROM works WHERE per_id = ?");
		    	pstmt.setInt(1, per_id);
      	      	ResultSet works_info = pstmt.executeQuery();
				pstmt = con.prepareStatement("SELECT position.* FROM position NATURAL JOIN job WHERE job_code = ?");
				pstmt.setInt(1, job_code);
				ResultSet position_info = pstmt.executeQuery();
				updateLD(person_info, person_skills, job, takes_info, works_info, position_info);
				return;
			}
			else
			{
				// STEP 5: Skill gap identified - propose training plan
				System.out.println("\n✗ This person doesn't qualify for the position");
				System.out.println("\n=== Training Plan Recommendation ===");

				pstmt = con.prepareStatement(
						"WITH per_skill (sk_code) AS (\n" + 
						"	SELECT DISTINCT sk_code FROM job NATURAL JOIN requires WHERE job_code = ?\n" + 
						"	EXCEPT\n" + 
						"	SELECT DISTINCT sk_code FROM has_skill WHERE per_id = ?\n" + 
						")\n" + 
						"SELECT DISTINCT c_code, c_title\n" + 
						"FROM course NATURAL JOIN teaches NATURAL JOIN per_skill");
				pstmt.setInt(1, job_code);
				pstmt.setInt(2, per_id);
				rset = pstmt.executeQuery();

				System.out.println("Recommended courses to fill skill gaps:");
				System.out.printf("%-10s %s%n", "c_code", "Course Title");
				System.out.println("----------------------------------------");
				while(rset.next())
					System.out.printf("%-10d %s%n", rset.getInt(1), rset.getString(2));

				// Cleanup - remove candidate from AZ database
				System.out.println("\nRemoving candidate from AZ database...");
				pstmt = con.prepareStatement("DELETE FROM person WHERE per_id = ?");
				pstmt.setInt(1, per_id);
				pstmt.executeUpdate();
				pstmt = con.prepareStatement("DELETE FROM takes WHERE per_id = ?");
				pstmt.setInt(1, per_id);
				pstmt.executeUpdate();
				pstmt = con.prepareStatement("DELETE FROM has_skill WHERE per_id = ?");
				pstmt.setInt(1, per_id);
				pstmt.executeUpdate();
				return;
			}
		} catch(SQLException e) {
			try
			{
				if(e.getSQLState().equals("23505"))
					System.out.println("Person already exists in the database, continuing..");
				pstmt = con.prepareStatement(
						"DELETE FROM person WHERE per_id = ?");
				pstmt.setInt(1, per_id);
				pstmt.executeUpdate();
				pstmt = con.prepareStatement(
						"DELETE FROM takes WHERE per_id = ?");
				pstmt.setInt(1, per_id);
				pstmt.executeUpdate();
				pstmt = con.prepareStatement(
						"DELETE FROM has_skill WHERE per_id = ?");
				pstmt.setInt(1, per_id);
				pstmt.executeUpdate();
				System.exit(1);
			} catch(SQLException r) {
				r.printStackTrace();
			}
	} 
}
public static void transferGVworker()
{
	try
	{
		ArrayList<Integer> list = new ArrayList<Integer>();
		System.out.println("Connecting to Database GV");
		con = DriverManager.getConnection(url, user3, password);
		System.out.println("Successfully Connected");
		pstmt = con.prepareStatement("SELECT DISTINCT fac_id FROM factory ");
		ResultSet rset = pstmt.executeQuery();
		while(rset.next())
		{
			list.add(rset.getInt(1));
			System.out.printf("%d ", rset.getInt(1));
		}
		System.out.print("Factories: " + list);
		System.out.println("\nWhich factory? ");
		int fac_id = in.nextInt();
		System.out.println("\nWhich factory to transfer to? ");
		int fac_id2 = in.nextInt();
		boolean bool = false;
		boolean bool2 = false;
		for (int i = 0; i<list.size(); i++)
		{
			if(list.get(i).equals(fac_id2))
				bool = true;
			if(list.get(i).equals(fac_id))
				bool2 = true;
		}
		if(!bool || !bool2)
		{
			System.out.println("Factory does not exist.");
			System.exit(1);
		}
		pstmt = con.prepareStatement(
				"SELECT DISTINCT per_id FROM job NATURAL JOIN works WHERE fac_id = ? AND end_date IS NULL");
		pstmt.setInt(1, fac_id);
		System.out.printf("Current working people for factory %d: ", fac_id);
		rset = pstmt.executeQuery();
		while(rset.next())
			System.out.printf("%d ", rset.getInt(1));
		System.out.println("\nWhich person? ");
		int per_id2 = in.nextInt();
		pstmt = con.prepareStatement(
				"SELECT * FROM job WHERE EXISTS (SELECT job_code FROM works WHERE per_id = ?) AND fac_id = ?");
		pstmt.setInt(1, per_id2);
		pstmt.setInt(2, fac_id);
		rset = pstmt.executeQuery();

		pstmt = con.prepareStatement(
				"DELETE FROM job WHERE per_id = ? AND fac_id = ?");
		pstmt.setInt(1, per_id2);
		pstmt.setInt(2, fac_id);
		pstmt.executeUpdate();
		int job_code = 0;
		while(rset.next())
		{
			job_code = rset.getInt(1);
			System.out.printf("Updating job at factory %d.%n", fac_id2);
				pstmt = con.prepareStatement(
						"INSERT INTO job VALUES (?,?,?,?,?,?,?,?,?,?)");
				pstmt.setInt(1, rset.getInt(1));      
				pstmt.setString(2, rset.getString(2)); 
				pstmt.setNull(3, java.sql.Types.INTEGER);
				pstmt.setInt(3, fac_id2);              
				pstmt.setInt(4, rset.getInt(4));       
				pstmt.setInt(5, rset.getInt(5));              
				pstmt.setString(6, rset.getString(6)); 
				pstmt.setInt(7, rset.getInt(7));       
				pstmt.setString(8, rset.getString(8)); 
				pstmt.setInt(9, rset.getInt(9));       
				pstmt.setInt(10, rset.getInt(10));     
				pstmt.executeUpdate();
			System.out.printf("Person %d now works at factory %d.%n", per_id2, fac_id2);
		}
		pstmt = con.prepareStatement(
				"SELECT * FROM takes where per_id = ?" );
		pstmt.setInt(1, per_id);
		ResultSet takes_info = pstmt.executeQuery();

   		pstmt = con.prepareStatement("SELECT * FROM person WHERE per_id = ?");
   		pstmt.setInt(1, per_id);
   		ResultSet person_info = pstmt.executeQuery();

 		pstmt = con.prepareStatement("SELECT * FROM has_skill WHERE per_id = ?");
 		pstmt.setInt(1, per_id);
 	    ResultSet person_skills = pstmt.executeQuery();
 		pstmt = con.prepareStatement("SELECT * FROM works WHERE per_id = ?");
 		pstmt.setInt(1, per_id);
    	ResultSet works_info = pstmt.executeQuery();
		pstmt = con.prepareStatement("SELECT * FROM job WHERE job_code = ?");
		pstmt.setInt(1, job_code);
		ResultSet job = pstmt.executeQuery();
		pstmt = con.prepareStatement("SELECT position.* FROM position NATURAL JOIN job WHERE job_code = ?");
		pstmt.setInt(1, job_code);
		ResultSet position_info = pstmt.executeQuery();
		updateLD(person_info, person_skills, job, takes_info, works_info, position_info);
		
	} catch(SQLException e) {
		e.printStackTrace();
	}
}

public static void transferCIOazgv()
{
	Connection con1 = null;
	Connection con2 = null;
	Connection con3 = null;
	try
	{
		System.out.println("Connecting to Database AZ...");
		con1 = DriverManager.getConnection(url, user1, password);
		System.out.println("Successfully Connected");
		con1.setAutoCommit(false);
		System.out.println("Fetching information of CIO in AZ...");
		pstmt = con1.prepareStatement(
				"WITH temp (per_id) AS "
				+ "(SELECT per_id FROM person NATURAL JOIN job WHERE j_title = 'CIO') "
				+ "SELECT * from temp natural join person");
		ResultSet cio_info_az = pstmt.executeQuery();
		System.out.println("Successfully fetched.");
		System.out.println("Removing AZ CIO info...");
		pstmt = con1.prepareStatement(
				"DELETE FROM person WHERE per_id = (SELECT per_id FROM job NATURAL JOIN person "
				+ "WHERE j_title = 'CIO')");
		pstmt.executeUpdate();
		System.out.println("Success.");
		System.out.println("Connecting to Database GV...");
		con2 = DriverManager.getConnection(url, user3, password);
		System.out.println("Successfully Connected");
		con2.setAutoCommit(false);

		System.out.println("Fetching information of CIO in GV...");
		pstmt = con2.prepareStatement(
				"WITH temp (per_id) AS "
				+ "(SELECT per_id FROM person NATURAL JOIN job WHERE j_title = 'CIO') "
				+ "SELECT * from temp natural join person");
		ResultSet cio_info_gv = pstmt.executeQuery();
		System.out.println("Successfully fetched.");
		System.out.println("Removing GV CIO info...");
		pstmt = con2.prepareStatement(
				"DELETE FROM person WHERE per_id = (SELECT per_id FROM job NATURAL JOIN person "
				+ "WHERE j_title = 'CIO')");
		pstmt.executeUpdate();
		System.out.println("Success.");
		String cio_az_str = "";
		int cio_az_int = 0;
		while(cio_info_az.next())
		{
			System.out.println("Inserting AZ CIO info into GV");
			pstmt = con2.prepareStatement(
					"INSERT INTO person VALUES (?,?,?,?,?,?,?)");
			cio_az_int = cio_info_az.getInt(1);
			cio_az_str = String.valueOf(cio_az_int);
			pstmt.setString(1, cio_az_str);
			pstmt.setString(2, cio_info_az.getString(2));
			pstmt.setString(3, cio_info_az.getString(3));
			pstmt.setString(4, cio_info_az.getString(4));
			pstmt.setString(5, cio_info_az.getString(5));
			pstmt.setString(6, cio_info_az.getString(6));
			pstmt.setString(7, cio_info_az.getString(7));
			pstmt.executeUpdate();
			System.out.println("Successfully inserted CIO into GV");
		}

		String cio_gv_str = "";
		int cio_gv_int = 0;
		while(cio_info_gv.next())
		{
			System.out.println("Inserting GV CIO info into AZ");
			pstmt = con1.prepareStatement(
					"INSERT INTO person VALUES (?,?,?,?,?,?,?)");
			cio_gv_str = cio_info_gv.getString(1);
			cio_gv_int = Integer.parseInt(cio_gv_str);
			pstmt.setInt(1, cio_gv_int);
			pstmt.setString(2, cio_info_gv.getString(2));
			pstmt.setString(3, cio_info_gv.getString(3));
			pstmt.setString(4, cio_info_gv.getString(4));
			pstmt.setString(5, cio_info_gv.getString(5));
			pstmt.setString(6, cio_info_gv.getString(6));
			pstmt.setString(7, cio_info_gv.getString(7));
			pstmt.executeUpdate();
			System.out.println("Successfully inserted CIO into AZ");
		}
		System.out.println("Fetching job info from previous CIO in AZ...");
		pstmt = con1.prepareStatement(
				"SELECT * FROM job WHERE j_title = 'CIO'");
		rset = pstmt.executeQuery();
		System.out.println("Successfully fetched.");
		System.out.println("Removing job info from previous CIO in AZ...");
		pstmt = con1.prepareStatement(
				"DELETE FROM job WHERE j_title = 'CIO'");
		pstmt.executeUpdate();
		System.out.println("Success.");

		System.out.println("Fetching job info from previous CIO in GV...");
		pstmt = con2.prepareStatement(
				"SELECT * FROM job WHERE j_title = 'CIO'");
		ResultSet rset2 = pstmt.executeQuery();
		System.out.println("Successfully fetched.");
		System.out.println("Removing job info from previous CIO in GV...");
		pstmt = con2.prepareStatement(
				"DELETE FROM job WHERE j_title = 'CIO'");
		pstmt.executeUpdate();
		System.out.println("Success.");
		while(rset.next())
		{
			System.out.println("Inserting AZ CIO job into GV");
			pstmt = con2.prepareStatement(
					"INSERT INTO job VALUES (?,?,?,?,?,?,?,?,?,?)");
			pstmt.setString(1, String.valueOf(rset.getInt(1)));
			pstmt.setString(2, rset.getString(2));
			pstmt.setString(3, rset.getString(3));
			pstmt.setString(4, cio_az_str);
			pstmt.setString(5, rset.getString(5));
			pstmt.setInt(6, rset.getInt(6));
			pstmt.setString(7, rset.getString(7));
			pstmt.setString(8, String.valueOf(rset.getInt(8)));
			pstmt.setString(9, rset.getString(9));
			pstmt.setString(10, "GV");
			pstmt.executeUpdate();
			System.out.println("Successfully inserted AZ job CIO into GV");
		}
		while(rset2.next())
		{
			System.out.println("Inserting GV CIO  job into AZ");
			pstmt = con1.prepareStatement(
					"INSERT INTO job VALUES (?,?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, Integer.parseInt(rset2.getString(1)));
			pstmt.setString(2, rset2.getString(2));
			pstmt.setInt(3, Integer.parseInt(rset2.getString(3)));
			pstmt.setInt(4, cio_gv_int);
			pstmt.setString(5, rset2.getString(5));
			pstmt.setInt(6, rset2.getInt(6));
			pstmt.setString(7, rset2.getString(7));
			pstmt.setInt(8, Integer.parseInt(rset2.getString(8)));
			pstmt.setString(9, rset2.getString(9));
			pstmt.setString(10, "AZ");
			pstmt.executeUpdate();
			System.out.println("Successfully inserted GV CIO job into AZ");
		}	

	} catch(SQLException e) {
		System.err.println(e);
		System.exit(1);	
	} finally {
		try
		{
			con1.setAutoCommit(true);
				con1.close();
				con2.setAutoCommit(true);
				con2.close();
		} catch (Exception s) {
			s.printStackTrace();
		}

	}
}

public static void transactionGV_EM()
{
	
}

public static void updateLD(ResultSet person_info, ResultSet person_skills, ResultSet job_info, ResultSet takes_info, ResultSet works_info, ResultSet position_info)
{
	try
	{
 		System.out.println("Connecting to Database LD");
		con = DriverManager.getConnection(url, user2, password);
		System.out.println("Successfully Connected");

		while(person_info.next())
		{
			pstmt = con.prepareStatement(
				"INSERT INTO person VALUES(?,?,?,?,?,?,?) ON CONFLICT (per_id) DO NOTHING");
			pstmt.setInt(1, person_info.getInt(1));
			pstmt.setString(2, person_info.getString(2));
			pstmt.setString(3, person_info.getString(3));
			pstmt.setString(4, person_info.getString(4));
			pstmt.setString(5, person_info.getString(5));
			pstmt.setString(6, person_info.getString(6));
			pstmt.setString(7, person_info.getString(7));
			pstmt.executeUpdate();
		}
		while(position_info.next())
		{
			pstmt = con.prepareStatement(
				"INSERT INTO position VALUES (?,?,?,?,?) ON CONFLICT (pos_code) DO NOTHING");
			pstmt.setInt(1, position_info.getInt(1));
			pstmt.setString(2, position_info.getString(2));
			pstmt.setString(3, position_info.getString(3));
			pstmt.setInt(4, position_info.getInt(4));
			pstmt.setInt(5, position_info.getInt(5));
			pstmt.executeUpdate();
		}
		while(person_skills.next())
		{
			pstmt = con.prepareStatement(
				"INSERT INTO has_skill VALUES(?,?) ON CONFLICT (per_id, sk_code) DO NOTHING");
			pstmt.setInt(1, person_skills.getInt(1));
			pstmt.setInt(2, person_skills.getInt(2));
			pstmt.executeUpdate();

		}
		while(job_info.next())
		{
			pstmt = con.prepareStatement(
				"INSERT INTO job VALUES(?,?,?,?,?,?,?,?,?,?) ON CONFLICT (job_code) DO NOTHING");
			pstmt.setInt(1, job_info.getInt(1));
			pstmt.setString(2, job_info.getString(2));
			pstmt.setInt(3, job_info.getInt(3));
			pstmt.setInt(4, job_info.getInt(4));
			pstmt.setInt(5, job_info.getInt(5));
			pstmt.setInt(6, job_info.getInt(6));
			pstmt.setString(7, job_info.getString(7));
			pstmt.setInt(8, job_info.getInt(8));
			pstmt.setString(9, job_info.getString(9));
			pstmt.setInt(10, job_info.getInt(10));
			pstmt.executeUpdate();
		}
		while(takes_info.next())
		{
			pstmt = con.prepareStatement(
				"INSERT INTO takes VALUES(?,?,?,?,?,?,?,?) ON CONFLICT (c_code) DO NOTHING");
			pstmt.setInt(1, takes_info.getInt(1));
			pstmt.setInt(2, takes_info.getInt(2));
			pstmt.setInt(3, takes_info.getInt(3));
			pstmt.setDate(4, takes_info.getDate(4));
			pstmt.setInt(5, takes_info.getInt(5));
			pstmt.setString(6, takes_info.getString(6));
			pstmt.setString(7, takes_info.getString(7));
			pstmt.setBigDecimal(8, takes_info.getBigDecimal(8));
			pstmt.executeUpdate();

		}
		while(works_info.next())
		{
			pstmt = con.prepareStatement(
				"INSERT INTO works VALUES(?,?,?,?) ON CONFLICT (job_code, per_id) DO NOTHING");
			pstmt.setInt(1, works_info.getInt(1));
			pstmt.setInt(2, works_info.getInt(2));
			pstmt.setDate(3, works_info.getDate(3));
			pstmt.setDate(4, works_info.getDate(4));
			pstmt.executeUpdate();

		}
	} catch(SQLException e) {
		System.err.println(e);
		e.printStackTrace();
	}
		
	}
}