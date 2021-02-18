/**
* @author Ammar Kadic
* @version 1.2
* Spring 2019
* 
* This program connects to 3 different database oracle accounts and performs various tasks.
* Each database option has its own query options.
* Other features can be accessed by numbers 4 through 6.
* Note: this program isn't runnable anymore due to the expiration of the oracle accounts.
**/
package Database;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collections;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import java.sql.ResultSet;
import java.sql.DriverManager;

public class DatabaseRunner 
{
	// Database driver and URL for the server
	static final String driver = "oracle.jdbc.driver.OracleDriver";  
	static final String url = "jdbc:oracle:thin:@dbsvcs.cs.uno.edu:1521:orcl";
	static Connection con;
	static PreparedStatement pstmt = null;
	static ResultSet rset = null;
	static Scanner in = new Scanner(System.in);
	static ArrayList<Integer> job_codes = new ArrayList<Integer>();
	public static void main(String[] args)
	{
		for(int i = 20; i<999;i++)
			job_codes.add(i);
		Collections.shuffle(job_codes);
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");	
			
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
			con = DriverManager.getConnection(url, "akadic", "CMT4xtCw");
			System.out.println(con);
			System.out.println("Successfully connected");
			
			while(true)
			{
				System.out.println("\nWhich query would you like to run? (1-9)");
						
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
					case 8: System.out.println("Executing Query 8)\n"); // ask Dr. Tu about unit and avg_cost
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
			con = DriverManager.getConnection(url, "kabrisc1", "KVfkx7kh");
			System.out.println("Successfully connected");
			while(true)
			{
				System.out.println("\nWhich query would you like to run? (10-12)");
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
					case 11: System.out.println("Executing Query 10)\n");
							query11();
							break;
					case 12: System.out.println("Executing Query 10)\n");
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
			con = DriverManager.getConnection(url, "afang", "WfvbW3pc");
			System.out.println("Successfully Connected");
			while(true)
			{
				System.out.println("\nWhich query would you like to run? (13-27)");
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
	/*Given a person’s identifier, list this person’s skills in a readable format. */
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
	
	/*Given a person’s identifier, list a person’s missing skills for a specific pos_code in a readable format*/
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
										 							"MINUS\n" + 
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
	
	/*Given a person’s identifier, find all the jobs this person is currently holding and worked in the past.*/
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
										"	 MINUS\n" + 
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
					"		SELECT comp_id, job_code, CASE WHEN pay_type ='wage' THEN pay_rate*1920 ELSE pay_rate END AS ann_pay\n" + 
					"		FROM job NATURAL JOIN company)\n" + 
					"	GROUP BY parent_id),\n" + 
					"     max_job_pay (parent_id, max_pay) AS (\n" + 
					"	SELECT parent_id, MAX(ann_pay) AS max_pay\n" + 
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
					"      	MINUS\n" + 
					"      	SELECT sk_code\n" + 
					"      	FROM has_skill\n" + 
					"      	WHERE per_id = ?)\n" + 
					"SELECT c_code, c_title\n" + 
					"FROM course \n" + 
					"WHERE NOT EXISTS ( \n" + 
					"	SELECT sk_code\n" + 
					"	FROM missing_skills\n" + 
					"	MINUS\n" + 
					"	SELECT sk_code\n" + 
					"	FROM teaches\n" + 
					"	WHERE course.c_code = teaches.c_code)");
			pstmt.setInt(1,pos_code);
			pstmt.setInt(2,per_id);
			rset = pstmt.executeQuery();
			while (rset.next())
				System.out.printf("%s %s%n", rset.getString(1), rset.getString(2));

		} catch (SQLException e) {
			System.err.println(e);
		}
		
	}
	public static void query20()	// afang WfvbW3pc	kabrisct1 KVfkx7kh
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
					"		MINUS\n" + 
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

			pstmt.setInt(1,per_id);
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
					"		MINUS\n" + 
					"		SELECT sk_code\n" + 
					"		FROM has_skill S\n" + 
					"		WHERE HS.per_id = S.per_id))\n" + 
					"SELECT DISTINCT per_id, last_name, first_name, email\n" + 
					"FROM person natural join temp");
			System.out.println("\nWhich pos_code? ");
			int pos_code = in.nextInt();
			pstmt.setInt(1,pos_code);
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
			pstmt.setInt(1,pos_code);
			pstmt.setInt(2,pos_code);
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
			pstmt.setInt(1,pos_code);
			pstmt.setInt(2,pos_code);
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
					"	MINUS\n" + 
					"	SELECT per_id, sk_code\n" + 
					"	FROM has_skill)\n" + 
					"SELECT sk_code, COUNT(per_id) AS people_count\n" + 
					"FROM num_of_people\n" + 
					"GROUP BY sk_code\n" + 
					"ORDER BY COUNT(per_id)");
			System.out.println("\nWhich pos_code? ");
			int pos_code = in.nextInt();
			pstmt.setInt(1,pos_code);
			pstmt.setInt(2,pos_code);
			pstmt.setInt(3,pos_code);
			rset = pstmt.executeQuery();
			while(rset.next())
				System.out.printf("%s %s%n", rset.getString(1), rset.getString(2));

			
		} catch(SQLException e) {
			System.err.println(e);
		}
	}
	/*Find out the number of the workers whose earnings increased in a specific industry group 
	 * (use attribute “industry group” in table Company). 
	 * [Hint: earning change = the sum of a person’s current earnings – the pay of the person’s the last previous job.]*/
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
				"		MINUS\n" + 
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
					"	cset_id NUMBER(6,0) PRIMARY KEY,\n" + 
					"	c_code1 NUMBER(6,0), c_code2 NUMBER(6,0), c_code3 NUMBER(6,0),\n" + 
					"	setsize NUMBER(2,0)\n" + 
					")");
			pstmt.executeUpdate();
			System.out.println("course_set created.");
			System.out.println("Creating sequence course_seq.");
			pstmt = con.prepareStatement(
					"CREATE SEQUENCE course_seq\n" + 
					"START WITH 1\n" + 
					"INCREMENT BY 1\n" + 
					"MAXVALUE 999999\n" + 
					"NOCYCLE");
			pstmt.executeUpdate();
			System.out.println("course_seq created.");
			System.out.println("Inserting into course_set.");
			pstmt = con.prepareStatement (
					"INSERT INTO course_set \n" + 
					"	SELECT course_seq.NEXTVAL, C1.c_code, C2.c_code, C3.c_code, 3\n" + 
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
				"		MINUS\n" + 
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
				"		MINUS\n" + 
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
					"DROP SEQUENCE course_seq");
			pstmt.executeUpdate();
			System.out.println("course_seq dropped.");
			pstmt = con.prepareStatement(
					"DROP TABLE course_set");
			pstmt.executeUpdate();
			System.out.println("course_set dropped.");

		} catch (SQLException e) {
			System.err.println(e);
		}
	}// akadic CMT4xtCw afang WfvbW3pc	kabrisc1 KVfkx7kh
	
	public static void AZhires()
	{
		int per_id = 0;
		int store_id = 0;
		try
		{
			System.out.println("Connecting to Database LD");
			con = DriverManager.getConnection(url, "afang", "WfvbW3pc");
			System.out.println("Successfully Connected");
			System.out.print("Available per_id from LD that want to be hired: ");
			pstmt = con.prepareStatement(""
					+ "SELECT per_id FROM person MINUS( "
					+ "		SELECT per_id "
					+ "		FROM works "
					+ "		WHERE e_y IS NULL) "
					+ "ORDER BY per_id");
			rset = pstmt.executeQuery();
			while(rset.next())
				System.out.printf("%s ", rset.getString(1));
			System.out.println("\nWhich per_id? ");
			per_id = in.nextInt();
			
			pstmt = con.prepareStatement("SELECT * FROM person WHERE per_id = ?");
			pstmt.setInt(1,per_id);
			ResultSet person_info = pstmt.executeQuery();

			pstmt = con.prepareStatement("SELECT * FROM has_skill WHERE per_id = ?");
			pstmt.setInt(1,per_id);
			ResultSet person_skills = pstmt.executeQuery();

			pstmt = con.prepareStatement("SELECT per_id, c_code FROM takes WHERE per_id = ?");
			pstmt.setInt(1, per_id);
			ResultSet person_takes = pstmt.executeQuery();

			System.out.println("Connecting to Database AZ");
			con = DriverManager.getConnection(url, "akadic", "CMT4xtCw");
			System.out.println("Successfully Connected");
			
			System.out.printf("Inserting per_id: %s info into person.%n", per_id);
			while(person_info.next())
			{
				pstmt = con.prepareStatement(
						"INSERT INTO person values (?,?,?,?,?,?,?)");
				pstmt.setString(1,person_info.getString(1));
				pstmt.setString(2,person_info.getString(2));
				pstmt.setString(3,person_info.getString(3));
				pstmt.setString(4,person_info.getString(4));
				pstmt.setString(5,person_info.getString(5));
				pstmt.setString(6,person_info.getString(6));
				pstmt.setString(7,person_info.getString(7));
				pstmt.executeUpdate();
			}
			System.out.println("Successfully inserted.");
			System.out.printf("Inserting per_id: %s current skills into has_skill.%n", per_id);
			while(person_skills.next())
			{
				pstmt = con.prepareStatement(
						"INSERT INTO has_skill values (?,?)");
				pstmt.setString(1,person_skills.getString(1));
				pstmt.setString(2,person_skills.getString(2));
				pstmt.executeUpdate();
			}
			System.out.println("Successfully inserted.");
			System.out.printf("Inserting per_id: %s taken courses into takes.%n", per_id);
			while(person_takes.next())
			{
				pstmt = con.prepareStatement(
						"INSERT INTO takes VALUES (?,?)");
				pstmt.setString(1,person_takes.getString(1));
				pstmt.setString(2,person_takes.getString(2));
				pstmt.executeUpdate();
			}
			System.out.println("Successfully inserted.");

			System.out.println("Checking the difference between self-claimed skills from transcript..");
			pstmt = con.prepareStatement(
					"WITH self_claimed_skills (per_id, sk_code) AS (\n" + 
					"	SELECT per_id, sk_code\n" + 
					"	FROM has_skill\n" + 
					"	WHERE per_id = ?\n" + 
					"	INTERSECT\n" + 
					"	SELECT per_id, sk_code \n" + 
					"	FROM takes NATURAL JOIN teaches\n" + 
					"	WHERE per_id = ?),\n" + 
					"    per_skill_count (p_sk_count) AS (\n" + 
					"	SELECT COUNT(sk_code)\n" + 
					"	FROM self_claimed_skills),\n" + 
					"    teaches_skill_count (t_sk_count) AS (\n" + 
					"	SELECT COUNT(DISTINCT sk_code)\n" + 
					"	FROM takes NATURAL JOIN TEACHES)\n" + 
					"SELECT t_sk_count-p_sk_count AS difference\n" + 
					"FROM per_skill_count,teaches_skill_count");
			pstmt.setInt(1, per_id);
			pstmt.setInt(2, per_id);
			ResultSet rset1 = pstmt.executeQuery();
			rset1.next();
			if(rset1.getInt(1) > 5)
			{
				System.out.println("This person hasn't passed enough courses and will not be hired.");
				System.out.println("Requirement: at least 5 skills acquired from courses.");
				pstmt = con.prepareStatement(
						"DELETE FROM person WHERE per_id = ?");
				pstmt.setInt(1,per_id);
				pstmt.executeUpdate();
				pstmt = con.prepareStatement(
						"DELETE FROM takes WHERE per_id = ?");
				pstmt.setInt(1,per_id);
				pstmt.executeUpdate();
				pstmt = con.prepareStatement(
						"DELETE FROM has_skill WHERE per_id = ?");
				pstmt.setInt(1,per_id);
				pstmt.executeUpdate();
				return;
			}
			System.out.println("This person has passed enough courses to be hired.");
			
			pstmt = con.prepareStatement(
					  "SELECT DISTINCT sk_code "
					+ "FROM takes NATURAL JOIN teaches "
					+ "WHERE per_id = ? "
					+ "MINUS "
					+ "SELECT DISTINCT sk_code "
					+ "FROM has_skill "
					+ "WHERE per_id = ?");
			pstmt.setInt(1,per_id);
			pstmt.setInt(2,per_id);
			rset = pstmt.executeQuery();
			System.out.printf("Inserting acquired skills into has_skill for person: %d%n", per_id);
			while(rset.next())
			{
				pstmt = con.prepareStatement(
						"INSERT INTO has_skill values (?,?)");
				pstmt.setInt(1,per_id);
				pstmt.setString(2,rset.getString(1));
				pstmt.executeUpdate();
			}
			System.out.println("Successfully inserted.");
			
			System.out.print("Available job_codes and stores:\n");
			pstmt = con.prepareStatement(
					"SELECT job_code, store_id FROM job");
			rset = pstmt.executeQuery();
			System.out.printf("%njob_code%5s store_id%s%n","","");
			while(rset.next())
				System.out.printf("%-13s %s%n", rset.getString(1), rset.getString(2));
			System.out.println("\nWhich job_code? ");
			int job_code = in.nextInt();
			System.out.println("\nWhich store? ");
			store_id = in.nextInt();
			
			pstmt = con.prepareStatement(
					"SELECT sk_code FROM job NATURAL JOIN requires WHERE job_code = ? "
				+	"MINUS "
				+ 	"SELECT sk_code FROM has_skill WHERE per_id = ?");
			pstmt.setInt(1,job_code);
			pstmt.setInt(2,per_id);
			rset = pstmt.executeQuery();
			if(!rset.next())
			{
				System.out.println("This person qualifies for the position.");
				pstmt = con.prepareStatement ( 
						"SELECT * FROM job WHERE job_code = ?");
				pstmt.setInt(1,job_code);
				rset = pstmt.executeQuery();
				while(rset.next())
				{	
					System.out.println("Hiring person...");
					pstmt = con.prepareStatement(
							"INSERT INTO job VALUES (?,?,?,?,?,?,?,?,?,?)");
					pstmt.setInt(1, job_codes.get(0));
					job_codes.remove(0);
					pstmt.setString(2, rset.getString(2));
					pstmt.setInt(3, store_id);
					pstmt.setInt(4, per_id);
					pstmt.setString(5, rset.getString(5));
					pstmt.setString(6, rset.getString(6));
					pstmt.setString(7, rset.getString(7));
					pstmt.setString(8, rset.getString(8));
					pstmt.setString(9, rset.getString(9));
					pstmt.setString(10, rset.getString(10));
					pstmt.executeUpdate();
				}
				System.out.println("Successfully hired\n");
				pullFromLD();
				return;
			}
			else
			{
				System.out.println("This person doesn't qualify for the position");
				pstmt = con.prepareStatement(
						"WITH per_skill (sk_code) AS (\n" + 
						"	SELECT DISTINCT sk_code FROM job NATURAL JOIN requires WHERE job_code = ?\n" + 
						"	MINUS\n" + 
						"	SELECT DISTINCT sk_code FROM has_skill WHERE per_id = ?\n" + 
						")\n" + 
						"SELECT DISTINCT c_code\n" + 
						"FROM teaches NATURAL JOIN per_skill");
				pstmt.setInt(1,job_code);
				pstmt.setInt(2,per_id);
				rset = pstmt.executeQuery();
				System.out.println("Recommended training plan: c_code: ");
				while(rset.next())
					System.out.printf("%s%n", rset.getString(1));
				
				pstmt = con.prepareStatement(
						"DELETE FROM person WHERE per_id = ?");
				pstmt.setInt(1,per_id);
				pstmt.executeUpdate();
				pstmt = con.prepareStatement(
						"DELETE FROM takes WHERE per_id = ?");
				pstmt.setInt(1,per_id);
				pstmt.executeUpdate();
				pstmt = con.prepareStatement(
						"DELETE FROM has_skill WHERE per_id = ?");
				pstmt.setInt(1,per_id);
				pstmt.executeUpdate();
				return;
			}
		} catch(SQLException e) {
			System.err.println(e);
			e.printStackTrace();
			try
			{
				pstmt = con.prepareStatement(
						"DELETE FROM person WHERE per_id = ?");
				pstmt.setInt(1,per_id);
				pstmt.executeUpdate();
				pstmt = con.prepareStatement(
						"DELETE FROM takes WHERE per_id = ?");
				pstmt.setInt(1,per_id);
				pstmt.executeUpdate();
				pstmt = con.prepareStatement(
						"DELETE FROM has_skill WHERE per_id = ?");
				pstmt.setInt(1,per_id);
				pstmt.executeUpdate();
				System.exit(1);
			} catch(SQLException r) {
				System.err.println(r);
			}
		} catch (Exception r) {
			System.err.println(r);
			System.out.println("Something went wrong");
			
			try
			{
				pstmt = con.prepareStatement(
						"DELETE FROM person WHERE per_id = ?");
				pstmt.setInt(1,per_id);
				pstmt.executeUpdate();
				pstmt = con.prepareStatement(
						"DELETE FROM takes WHERE per_id = ?");
				pstmt.setInt(1,per_id);
				pstmt.executeUpdate();
				pstmt = con.prepareStatement(
						"DELETE FROM has_skill WHERE per_id = ?");
				pstmt.setInt(1,per_id);
				pstmt.executeUpdate();
				System.exit(1);
			} catch(SQLException e) {
				System.err.println(e);
			}
		}
	}
	public static void transferGVworker()
	{
		try
		{
			System.out.println("Connecting to Database LD");
			con = DriverManager.getConnection(url, "kabrisc1", "KVfkx7kh");
			System.out.println("Successfully Connected");
			System.out.print("Factories: ");
			pstmt = con.prepareStatement(
					"SELECT DISTINCT fac_id FROM factory ");
			rset = pstmt.executeQuery();
			ArrayList<Integer> list = new ArrayList<Integer>();
			while(rset.next())
			{
				list.add(rset.getInt(1));
				System.out.printf("%s ", rset.getString(1));
			}
			System.out.println(list);
			System.out.println("\nWhich factory? ");
			int fac_id = in.nextInt();
			System.out.println("\nWhich factory to transfer to? ");
			int fac_id2 = in.nextInt();
			boolean bool = false;
			boolean bool2 = false;
			for (int i = 0; i<list.size(); i++)
			{
				if(list.get(i) == fac_id2)
					bool = true;
				if(list.get(i) == fac_id)
					bool2 = true;
			}
			if(!bool || !bool2)
			{
				System.out.println("Factory does not exist.");
				System.exit(1);
			}
			pstmt = con.prepareStatement(
					"SELECT per_id FROM job WHERE fac_id = ?");
			pstmt.setInt(1, fac_id);
			System.out.printf("Current working people for factory %s: ", fac_id);
			rset = pstmt.executeQuery();
			while(rset.next())
				System.out.printf("%s ", rset.getString(1));
			System.out.println("\nWhich person? ");
			int per_id2 = in.nextInt();
			pstmt = con.prepareStatement(
					"SELECT * FROM job WHERE per_id = ? AND fac_id = ?");
			pstmt.setInt(1,per_id2);
			pstmt.setInt(2,fac_id);
			rset = pstmt.executeQuery();

			pstmt = con.prepareStatement(
					"DELETE FROM job WHERE per_id = ? AND fac_id = ?");
			pstmt.setInt(1, per_id2);
			pstmt.setInt(2, fac_id);
			pstmt.executeUpdate();
			while(rset.next())
			{
				System.out.printf("Updating job at factory %d.%n", fac_id2);
					pstmt = con.prepareStatement(
							"INSERT INTO job VALUES (?,?,?,?,?,?,?,?,?,?)");
					pstmt.setString(1, rset.getString(1));
					pstmt.setString(2, rset.getString(2));
					pstmt.setInt(3, fac_id2);
					pstmt.setInt(4, per_id2);
					pstmt.setString(5, rset.getString(5));
					pstmt.setString(6, rset.getString(6));
					pstmt.setString(7, rset.getString(7));
					pstmt.setString(8, rset.getString(8));
					pstmt.setString(9, rset.getString(9));
					pstmt.setString(10, rset.getString(10));
					pstmt.executeUpdate();
				System.out.printf("Person %d now works at factory %d.%n", per_id2, fac_id2);
			}
			
			pullFromLD();
			
		} catch(SQLException e) {
			System.err.println(e);
		}// akadic CMT4xtCw afang WfvbW3pc	kabrisc1 KVfkx7kh
	}
	
	public static void transferCIOazgv()
	{
		Connection con1 = null;
		Connection con2 = null;
		Connection con3 = null;
		try
		{
			System.out.println("Connecting to Database AZ...");
			con1 = DriverManager.getConnection(url, "akadic", "CMT4xtCw");
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
			con2 = DriverManager.getConnection(url, "kabrisc1", "KVfkx7kh");
			System.out.println("Successfully Connected");
			con2.setAutoCommit(false);

			System.out.println("Fetching information of CIO in GV...");
			pstmt = con2.prepareStatement(
					"WITH temp (per_id) AS "
					+ "(SELECT per_id FROM person NATURAL JOIN job WHERE j_title = 'CIO') "
					+ "SELECT * from temp natural join person");
			ResultSet cio_info_gv = pstmt.executeQuery();
			System.out.println("Successfully fetched.");
			System.out.println("Removing AZ CIO info...");
			pstmt = con2.prepareStatement(
					"DELETE FROM person WHERE per_id = (SELECT per_id FROM job NATURAL JOIN person "
					+ "WHERE j_title = 'CIO')");
			pstmt.executeUpdate();
			System.out.println("Success.");
			int cio_az = 0;
			while(cio_info_az.next())
			{
				System.out.println("Inserting AZ CIO info into GV");
				pstmt = con2.prepareStatement(
						"INSERT INTO person VALUES (?,?,?,?,?,?,?)");
				cio_az = cio_info_az.getInt(1);
				pstmt.setString(1, cio_info_az.getString(1));
				pstmt.setString(2, cio_info_az.getString(2));
				pstmt.setString(3, cio_info_az.getString(3));
				pstmt.setString(4, cio_info_az.getString(4));
				pstmt.setString(5, cio_info_az.getString(5));
				pstmt.setString(6, cio_info_az.getString(6));
				pstmt.setString(7, cio_info_az.getString(7));
				pstmt.executeUpdate();
				System.out.println("Successfully inserted CIO into GV");
			}

			int cio_gv = 0;
			while(cio_info_gv.next())
			{
				System.out.println("Inserting GV CIO info into AZ");
				pstmt = con1.prepareStatement(
						"INSERT INTO person VALUES (?,?,?,?,?,?,?)");
				cio_gv = cio_info_gv.getInt(1);
				pstmt.setString(1, cio_info_gv.getString(1));
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
				pstmt.setString(1, rset.getString(1));
				pstmt.setString(2, rset.getString(2));
				pstmt.setString(3, rset.getString(3));
				pstmt.setInt(4, cio_az);
				pstmt.setString(5, rset.getString(5));
				pstmt.setString(6, rset.getString(6));
				pstmt.setString(7, rset.getString(7));
				pstmt.setString(8, rset.getString(8));
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
				pstmt.setString(1, rset2.getString(1));
				pstmt.setString(2, rset2.getString(2));
				pstmt.setString(3, rset2.getString(3));
				pstmt.setInt(4, cio_gv);
				pstmt.setString(5, rset2.getString(5));
				pstmt.setString(6, rset2.getString(6));
				pstmt.setString(7, rset2.getString(7));
				pstmt.setString(8, rset2.getString(8));
				pstmt.setString(9, rset2.getString(9));
				pstmt.setString(10, "AZ");
				pstmt.executeUpdate();
				System.out.println("Successfully inserted GV CIO job into AZ");
			}	

			pullFromLD();

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
	}// akadic CMT4xtCw afang WfvbW3pc	kabrisc1 KVfkx7kh
	
	public static void transactionGV_EM()
	{
		
	}

	public static void pullFromLD()
	{
		try
		{
			
			System.out.println("Connecting to Database AZ");
			con = DriverManager.getConnection(url, "akadic", "CMT4xtCw");
			System.out.println("Successfully Connected");
			pstmt = con.prepareStatement(
					"SELECT * FROM job");
			ResultSet job_AZ = pstmt.executeQuery();

			System.out.println("Connecting to Database GV");
			con = DriverManager.getConnection(url, "kabrisc1", "KVfkx7kh");
			System.out.println("Successfully Connected");
			pstmt = con.prepareStatement(
					"SELECT * FROM job");
			ResultSet job_GV = pstmt.executeQuery();

			System.out.println("Connecting to Database LD");
			con = DriverManager.getConnection(url, "afang", "WfvbW3pc");
			System.out.println("Successfully Connected");
			pstmt = con.prepareStatement(
					"SELECT job_code FROM job WHERE comp_id = '7'");
			ResultSet az_job_code = pstmt.executeQuery();

			System.out.println("DELETING FROM WORKS IN LD FOR AZ...");
			while(az_job_code.next())
			{
				pstmt = con.prepareStatement(
						"DELETE FROM works WHERE job_code = ?");
				pstmt.setString(1, az_job_code.getString(1));
				pstmt.executeUpdate();
			}
			System.out.println("SUCCESS");

			pstmt = con.prepareStatement(
					"SELECT job_code FROM job WHERE comp_id = '8'");
			ResultSet gv_job_code = pstmt.executeQuery();

			System.out.println("DELETING FROM WORKS IN LD FOR GV...");
			while(gv_job_code.next())
			{
				pstmt = con.prepareStatement(
						"DELETE FROM works WHERE job_code = ?");
				pstmt.setString(1,gv_job_code.getString(1));
				pstmt.executeUpdate();
			}
			System.out.println("Success");

			System.out.println("DELETING FROM JOB IN LD FOR AZ...");
			pstmt = con.prepareStatement(
					"SELECT job_code FROM job WHERE comp_id = '7'");
			ResultSet temp = pstmt.executeQuery();
			while(temp.next())
			{
				pstmt = con.prepareStatement(
						"DELETE FROM job WHERE job_code = ?");
				pstmt.setString(1,temp.getString(1));
				pstmt.executeUpdate();
			}
			System.out.println("Success");
			
			pstmt = con.prepareStatement(
					"SELECT job_code FROM job WHERE comp_id = '8'");
			temp = pstmt.executeQuery();
			System.out.println("DELETING FROM JOB IN LD FOR GV...");
			while(temp.next())
			{
				pstmt = con.prepareStatement(
						"DELETE FROM job WHERE job_code = ?");
				pstmt.setString(1,temp.getString(1));
				pstmt.executeUpdate();
			}
			System.out.println("Success");

			System.out.println("INSERTING INTO JOB IN LD FOR AZ...");
			System.out.println("INSERTING INTO WORKS IN LD FOR AZ...");
			while(job_AZ.next())
			{
				pstmt = con.prepareStatement(
						"INSERT INTO job VALUES (?,?,?,?,?,?,?,'0')");
				pstmt.setString(1, job_AZ.getString(1));
				pstmt.setString(2, job_AZ.getString(2));
				pstmt.setString(3, "7");
				pstmt.setString(4, job_AZ.getString(5));
				pstmt.setString(5, job_AZ.getString(6));
				pstmt.setString(6, job_AZ.getString(7));
				pstmt.setString(7, job_AZ.getString(8));
				pstmt.executeUpdate();

				pstmt = con.prepareStatement(
						"INSERT INTO works VALUES (?,?,null,null,null,null,null,null)");
				pstmt.setString(1,job_AZ.getString(4));
				pstmt.setString(2,job_AZ.getString(1));
				pstmt.executeUpdate();
			}
			System.out.println("Success");
			System.out.println("Success");

			

			System.out.println("INSERTING INTO JOB IN LD FOR GV...");
			System.out.println("INSERTING INTO WORKS IN LD FOR GV...");
			while(job_GV.next())
			{
				pstmt = con.prepareStatement(
						"INSERT INTO job VALUES (?,?,?,?,?,?,?,?)");
				pstmt.setString(1, job_GV.getString(1));
				pstmt.setString(2, job_GV.getString(2));
				pstmt.setString(3, "8");
				pstmt.setString(4, job_GV.getString(5));
				pstmt.setString(5, job_GV.getString(6));
				pstmt.setString(6, job_GV.getString(7));
				pstmt.setString(7, job_GV.getString(8));
				pstmt.setString(8, job_GV.getString(9));
				pstmt.executeUpdate();
				
				pstmt = con.prepareStatement(
						"INSERT INTO works VALUES (?,?, null,null,null,null,null,null)");
				pstmt.setString(1,job_GV.getString(4));
				pstmt.setString(2,job_GV.getString(1));
				pstmt.executeUpdate();
			}
			System.out.println("Success");
			System.out.println("Success");
			con.close();
			
		} catch(SQLException e) {
			System.err.println(e);
			e.printStackTrace();
		}
		
	}
}






