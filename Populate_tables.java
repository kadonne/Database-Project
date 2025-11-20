import java.util.ArrayList;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;

public class Populate_tables
{
	static ArrayList<Integer> numbers = new ArrayList<Integer>();
	static ArrayList<Integer> numbers2 = new ArrayList<Integer>();
	static ArrayList<Integer> numbers3 = new ArrayList<Integer>();
	static ArrayList<Integer> per_id = new ArrayList<Integer>();
	static ArrayList<Integer> sk_code = new ArrayList<Integer>();
	static ArrayList<Integer> store_id = new ArrayList<Integer>();
	static ArrayList<Integer> fac_id = new ArrayList<Integer>();
	static ArrayList<Integer> comp_id = new ArrayList<Integer>();
	static ArrayList<Integer> job_code_az = new ArrayList<Integer>();
	static ArrayList<Integer> job_code_gv = new ArrayList<Integer>();
	static ArrayList<Integer> job_code_ld = new ArrayList<Integer>();
	static ArrayList<Integer> cate_code = new ArrayList<Integer>();
	static ArrayList<Integer> pos_code = new ArrayList<Integer>();
	static ArrayList<Integer> sup_id = new ArrayList<Integer>();
	static ArrayList<Integer> pur_num_az = new ArrayList<Integer>();
	static ArrayList<Integer> pur_num_gv = new ArrayList<Integer>();
	static ArrayList<Integer> item_num = new ArrayList<Integer>();
	static ArrayList<Integer> invoice_num = new ArrayList<Integer>();
	static ArrayList<Integer> c_code = new ArrayList<Integer>();
	static ArrayList<String> shelf_code = new ArrayList<String>();
	static ArrayList<String> purchase_dates_az = new ArrayList<String>();
	static ArrayList<Integer> pos_code_gv = new ArrayList<Integer>();
	static ArrayList<Integer> pos_code_ld = new ArrayList<Integer>();
	
	// AZ workers (indices 0-9)
	static ArrayList<Integer> az_workers = new ArrayList<Integer>();
	// GV workers (indices 10-19)
	static ArrayList<Integer> gv_workers = new ArrayList<Integer>();
	// LD workers (indices 20-29)
	static ArrayList<Integer> ld_workers = new ArrayList<Integer>();
	
	static PrintWriter azWriter;
	static PrintWriter gvWriter;
	static PrintWriter ldWriter;
	
	static ArrayList<String> last_name = new ArrayList<String>();
	static ArrayList<String> first_name = new ArrayList<String>();
	static ArrayList<String> address = new ArrayList<String>();
	static ArrayList<String> zip_code = new ArrayList<String>();
	static ArrayList<String> email = new ArrayList<String>();
	static ArrayList<String> gender = new ArrayList<String>();

	//Skill - 40 skills
	static ArrayList<String> title = new ArrayList<String>();
	static ArrayList<String> sk_description = new ArrayList<String>();
	static ArrayList<String> sk_level = new ArrayList<String>();

	//Store (AZ)
	static ArrayList<String> s_address = new ArrayList<String>();
	static ArrayList<String> s_zip_code = new ArrayList<String>();
	static ArrayList<String> s_phone = new ArrayList<String>();

	//Factory (GV)
	static ArrayList<String> f_name = new ArrayList<String>();
	static ArrayList<String> f_address = new ArrayList<String>();
	static ArrayList<String> f_zip_code = new ArrayList<String>();
	static ArrayList<String> f_phone = new ArrayList<String>();

	//Company (LD)
	static ArrayList<Integer> industry_group = new ArrayList<Integer>();
	static ArrayList<String> c_address = new ArrayList<String>();
	static ArrayList<String> c_zip_code = new ArrayList<String>();

	//Position - 30 positions
	static ArrayList<String> p_title = new ArrayList<String>();
	static ArrayList<String> p_description = new ArrayList<String>();
	static ArrayList<Integer> pay_range_high = new ArrayList<Integer>();
	static ArrayList<Integer> pay_range_low = new ArrayList<Integer>();

	//Job
	static ArrayList<String> j_title = new ArrayList<String>();
	static ArrayList<String> emp_mode = new ArrayList<String>();
	static ArrayList<Integer> pay_rate = new ArrayList<Integer>();
	static ArrayList<String> pay_type = new ArrayList<String>();
	static ArrayList<Integer> company = new ArrayList<Integer>();

	//Inventory (AZ)
	static ArrayList<String> i_title = new ArrayList<String>();
	static ArrayList<String> i_description = new ArrayList<String>();
	static ArrayList<Integer> i_quantity = new ArrayList<Integer>();
	static ArrayList<Integer> i_avg_cost = new ArrayList<Integer>();
	static ArrayList<Integer> i_min_level = new ArrayList<Integer>();

	//Sales (AZ)
	static ArrayList<Integer> s_year = new ArrayList<Integer>();
	static ArrayList<Integer> s_month = new ArrayList<Integer>();
	static ArrayList<Integer> s_day = new ArrayList<Integer>();
	static ArrayList<Integer> s_quantity = new ArrayList<Integer>();
	static ArrayList<Integer> price = new ArrayList<Integer>();
	static ArrayList<Integer> avg_cost = new ArrayList<Integer>();

	//Supplier (AZ)
	static ArrayList<String> sup_address = new ArrayList<String>();
	static ArrayList<String> sup_zip = new ArrayList<String>();
	static ArrayList<String> sup_phone = new ArrayList<String>();
	static ArrayList<Integer> balance = new ArrayList<Integer>();

	//Purchase
	static ArrayList<Integer> pur_quantity = new ArrayList<Integer>();
	static ArrayList<Integer> unit_cost = new ArrayList<Integer>();

	//GICS (LD)
	static ArrayList<Integer> ind_id = new ArrayList<Integer>();
	static ArrayList<Integer> ind_title_num = new ArrayList<Integer>();
	static ArrayList<String> ind_desc = new ArrayList<String>();

	//Course - 30 courses (shared across all databases)
	static ArrayList<String> c_title = new ArrayList<String>();
	static ArrayList<String> c_level = new ArrayList<String>();
	static ArrayList<String> c_desc = new ArrayList<String>();
	static ArrayList<String> c_status = new ArrayList<String>();

	//Takes
	static ArrayList<String> offered_by = new ArrayList<String>();
	static ArrayList<String> format = new ArrayList<String>();
	static ArrayList<Integer> t_price = new ArrayList<Integer>();


	public static void main(String[] args)
	{
		try {
			// Load all data from files
			loadDataFromFiles();

			azWriter = new PrintWriter(new FileWriter("AZ/populate.sql"));
			gvWriter = new PrintWriter(new FileWriter("GV/populate.sql"));
			ldWriter = new PrintWriter(new FileWriter("LD/populate.sql"));

			// Initialize number pools
			for(int i=0;i<300;i++)
				numbers.add(i);
			for(int i=50;i<400;i++)
				numbers2.add(i);
			for(int i=100;i<700;i++)
				numbers3.add(i);

			// Create people and assign them to databases
			make_people();

			// Create skills (shared)
			make_skills();

			// Connect person skills (all people in all DBs)
			connect_person_skills();

			// LD specific
			make_GICS();
			make_companies();
			make_positions_ld();
			make_jobs_ld();
			require_pos_skills_ld();
			make_works_ld();
			make_sub_ind();

			// AZ specific
			make_stores();
			make_positions_az();
			make_jobs_az();
			make_works_az();
			require_pos_skills_az();
			make_inventory();
			make_sales();
			make_suppliers();
			make_account_payable();
			make_purchases_az();
			make_stocking();
			make_purchase_payments_az();
			make_addi_requires_az();

			// GV specific
			make_factories();
			make_positions_gv();
			make_jobs_gv();
			make_works_gv();
			require_pos_skills_gv();
			make_purchases_gv();


			// Courses and Takes (shared across all)
			make_course();
			make_teaches();
			make_takes();

			azWriter.close();
			gvWriter.close();
			ldWriter.close();

			System.out.println("SQL populate scripts generated successfully");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Method to load all data from text files
	public static void loadDataFromFiles() throws IOException
	{
		// Person data
		loadStringList("data/person/last_name.txt", last_name);
		loadStringList("data/person/first_name.txt", first_name);
		loadStringList("data/person/address.txt", address);
		loadStringList("data/person/zip_code.txt", zip_code);
		loadStringList("data/person/email.txt", email);
		loadStringList("data/person/gender.txt", gender);
		
		// Skill data
		loadStringList("data/skill/sk_title.txt", title);
		loadStringList("data/skill/sk_description.txt", sk_description);
		loadStringList("data/skill/sk_level.txt", sk_level);
		
		// Store data (AZ)
		loadStringList("data/store/s_address.txt", s_address);
		loadStringList("data/store/s_zip_code.txt", s_zip_code);
		loadStringList("data/store/s_phone.txt", s_phone);
		
		// Factory data (GV)
		loadStringList("data/factory/f_name.txt", f_name);
		loadStringList("data/factory/f_address.txt", f_address);
		loadStringList("data/factory/f_zip_code.txt", f_zip_code);
		loadStringList("data/factory/f_phone.txt", f_phone);
		
		// Company data (LD)
		loadStringList("data/company/c_address.txt", c_address);
		loadStringList("data/company/c_zip_code.txt", c_zip_code);
		
		// Position data
		loadStringList("data/position/p_title.txt", p_title);
		loadStringList("data/position/p_description.txt", p_description);
		loadIntList("data/position/pay_range_high.txt", pay_range_high);
		loadIntList("data/position/pay_range_low.txt", pay_range_low);
		
		// Job data
		loadStringList("data/job/j_title.txt", j_title);
		loadStringList("data/job/emp_mode.txt", emp_mode);
		loadIntList("data/job/pay_rate.txt", pay_rate);
		loadStringList("data/job/pay_type.txt", pay_type);
		loadIntList("data/job/company.txt", company);
		
		// Inventory data (AZ)
		loadStringList("data/inventory/i_title.txt", i_title);
		loadStringList("data/inventory/i_description.txt", i_description);
		loadIntList("data/inventory/i_quantity.txt", i_quantity);
		loadIntList("data/inventory/i_avg_cost.txt", i_avg_cost);
		loadIntList("data/inventory/i_min_level.txt", i_min_level);
		
		// Sales data (AZ)
		loadIntList("data/sales/s_year.txt", s_year);
		loadIntList("data/sales/s_month.txt", s_month);
		loadIntList("data/sales/s_day.txt", s_day);
		loadIntList("data/sales/s_quantity.txt", s_quantity);
		loadIntList("data/sales/price.txt", price);
		
		// Supplier data (AZ)
		loadStringList("data/supplier/sup_address.txt", sup_address);
		loadStringList("data/supplier/sup_zip.txt", sup_zip);
		loadStringList("data/supplier/sup_phone.txt", sup_phone);
		loadIntList("data/supplier/balance.txt", balance);
		
		// Purchase data
		loadIntList("data/purchase/pur_quantity.txt", pur_quantity);
		loadIntList("data/purchase/unit_cost.txt", unit_cost);
		
		// GICS data (LD)
		loadIntList("data/industry/ind_title_num.txt", ind_title_num);
		loadStringList("data/industry/ind_desc.txt", ind_desc);
		
		// Course data
		loadStringList("data/course/course_title.txt", c_title);
		loadStringList("data/course/c_level.txt", c_level);
		loadStringList("data/course/c_desc.txt", c_desc);
		loadStringList("data/course/c_status.txt", c_status);
		
		// Takes data
		loadStringList("data/takes/offered_by.txt", offered_by);
		loadStringList("data/takes/format.txt", format);
		loadIntList("data/takes/t_price.txt", t_price);

	}
	
	// Helper method to load string data from file
	private static void loadStringList(String filename, ArrayList<String> list) throws IOException
	{
		BufferedReader reader = new BufferedReader(new FileReader(filename));
		String line;
		while ((line = reader.readLine()) != null) {
			list.add(line);
		}
		reader.close();
	}
	
	// Helper method to load integer data from file
	private static void loadIntList(String filename, ArrayList<Integer> list) throws IOException
	{
		BufferedReader reader = new BufferedReader(new FileReader(filename));
		String line;
		while ((line = reader.readLine()) != null) {
			list.add(Integer.parseInt(line.trim()));
		}
		reader.close();
	}


	public static void make_people()
	{
		Collections.shuffle(numbers);
		int size_factor = last_name.size() / 3;
		for (int i=0;i<last_name.size();i++)
		{
			int id = numbers.get(i);
			per_id.add(id);
		
			// Assign workers: 0-9 to AZ, 10-19 to GV, 20-29 to LD
			if(i < size_factor) {
				az_workers.add(id);
				azWriter.printf("insert into person values ('%d', '%s', '%s', '%s', '%s', '%s', '%s');%n", 
					id, last_name.get(i), first_name.get(i), address.get(i), zip_code.get(i), email.get(i), gender.get(i));
			}
			else if(i < size_factor*2) {
				gv_workers.add(id);
				gvWriter.printf("insert into person values ('%d', '%s', '%s', '%s', '%s', '%s', '%s');%n", 
					id, last_name.get(i), first_name.get(i), address.get(i), zip_code.get(i), email.get(i), gender.get(i));
			}
			else {
				ld_workers.add(id);
				ldWriter.printf("insert into person values ('%d', '%s', '%s', '%s', '%s', '%s', '%s');%n", 
					id, last_name.get(i), first_name.get(i), address.get(i), zip_code.get(i), email.get(i), gender.get(i));
			}
		}
	}
	
	public static void make_skills()
	{
		Collections.shuffle(numbers);
		for(int i=0; i<title.size();i++)
		{
			int random = (int)(Math.random()*3);
			int id = numbers.get(i);
			sk_code.add(id);
			
			String sql = String.format("insert into skill values ('%d','%s','%s','%s');%n", 
				id, title.get(i), sk_description.get(i), sk_level.get(random));
			azWriter.print(sql);
			gvWriter.print(sql);
			ldWriter.print(sql);
		}
	}
	
	public static void connect_person_skills()
	{
		// AZ workers
		for (int i=0;i<az_workers.size();i++)
		{
			HashSet<String> usedPairs = new HashSet<String>();
			int random = (int)(Math.random()*10+1);
			int added = 0;
			int attempts = 0;
			
			while(added < random && attempts < 100)
			{
				int skillIdx = (int)(Math.random()*sk_code.size());
				String pair = az_workers.get(i) + "-" + sk_code.get(skillIdx);
				
				if(!usedPairs.contains(pair))
				{
					azWriter.printf("insert into has_skill values ('%d', '%d');%n", az_workers.get(i), sk_code.get(skillIdx));
					usedPairs.add(pair);
					added++;
				}
				attempts++;
			}
		}
		
		// GV workers
		for (int i=0;i<gv_workers.size();i++)
		{
			HashSet<String> usedPairs = new HashSet<String>();
			int random = (int)(Math.random()*10+1);
			int added = 0;
			int attempts = 0;
			
			while(added < random && attempts < 100)
			{
				int skillIdx = (int)(Math.random()*sk_code.size());
				String pair = gv_workers.get(i) + "-" + sk_code.get(skillIdx);
				
				if(!usedPairs.contains(pair))
				{
					gvWriter.printf("insert into has_skill values ('%d', '%d');%n", gv_workers.get(i), sk_code.get(skillIdx));
					usedPairs.add(pair);
					added++;
				}
				attempts++;
			}
		}
		
		// LD workers
		for (int i=0;i<ld_workers.size();i++)
		{
			HashSet<String> usedPairs = new HashSet<String>();
			int random = (int)(Math.random()*10+1);
			int added = 0;
			int attempts = 0;
			
			while(added < random && attempts < 100)
			{
				int skillIdx = (int)(Math.random()*sk_code.size());
				String pair = ld_workers.get(i) + "-" + sk_code.get(skillIdx);
				
				if(!usedPairs.contains(pair))
				{
					ldWriter.printf("insert into has_skill values ('%d', '%d');%n", ld_workers.get(i), sk_code.get(skillIdx));
					usedPairs.add(pair);
					added++;
				}
				attempts++;
			}
		}
	}

	// =============== AZ METHODS ===============
	
	public static void make_stores()
	{
		Collections.shuffle(numbers);
		for (int i=0;i<6;i++)
		{
			int id = numbers.get(i);
			store_id.add(id);
			azWriter.printf("insert into store values ('%d', '%s', '%s', '%s');%n", 
				id, s_address.get(i), s_zip_code.get(i), s_phone.get(i));
		}
	}

	public static void make_positions_az()
	{
		Collections.shuffle(numbers);
		for (int i=0;i<10;i++)
		{
			int id = numbers.get(i);
			pos_code.add(id);
			azWriter.printf("insert into position values ('%d', '%s', '%s', '%d', '%d');%n", 
				id, p_title.get(i), p_description.get(i), pay_range_high.get(i), pay_range_low.get(i));
		}
	}

	public static void make_jobs_az()
	{
		Collections.shuffle(numbers2);
		for(int i=0;i<10;i++)
			job_code_az.add(numbers2.get(i));
		Collections.shuffle(numbers2);
		ArrayList<Integer> cate_az = new ArrayList<Integer>();
		for(int i=0;i<10;i++)
			cate_az.add(numbers2.get(i));

		// AZ schema: job_code, j_title, store_id, fac_id, comp_id, pos_code, emp_mode, pay_rate, pay_type, cate_code
		// Set fac_id to NULL, use store_id
		for (int job=0;job<10;job++)
		{
			azWriter.printf("insert into job values ('%d', '%s', '%d', NULL, '%d', '%d', '%s', '%d', '%s', '%d');%n", 
				job_code_az.get(job), 
				j_title.get((int)(Math.random()*5)), 
				store_id.get((int)(Math.random()*6)),
				comp_id.get((int)Math.random()*comp_id.size()),
				pos_code.get((int)(Math.random()*10)),
				emp_mode.get((int)(Math.random()*2)),
				pay_rate.get(job), 
				pay_type.get(job), 
				cate_az.get(job));
		}
	}
	
	static void make_works_az()
	{
		HashSet<String> usedCombinations = new HashSet<String>();
		HashSet<Integer> personsWithCurrentJob = new HashSet<Integer>();  

	
		// 30 works records with end dates
		for(int i=0; i<30;i++)
		{
			int personId, jobId;
			String combination;
		
			do {
				personId = az_workers.get((int)(Math.random()*az_workers.size()));
				jobId = job_code_az.get((int)(Math.random()*job_code_az.size()));
				combination = personId + "-" + jobId;
			} while(usedCombinations.contains(combination));
		
			usedCombinations.add(combination);
		
			// Generate start date (between 2005-2011)
			int startYear = 2005 + (int)(Math.random()*7);
			int startMonth = (int)(Math.random()*12)+1;
			int startDay = (int)(Math.random()*28)+1;
		
			// Generate end date (between 2014-2019, after start date)
			int endYear = 2014 + (int)(Math.random()*6);
			int endMonth = (int)(Math.random()*12)+1;
			int endDay = (int)(Math.random()*28)+1;
		
			azWriter.printf("insert into works values ('%d', '%d', '%d-%02d-%02d', '%d-%02d-%02d');%n",
				personId, jobId,
				startYear, startMonth, startDay,
				endYear, endMonth, endDay);
		}
	
		// 30 works records with null end dates (current jobs)
		for(int i=0;i<30;i++)
		{
			int personId, jobId;
			String combination;
		
			do {
  	    	    personId = az_workers.get((int)(Math.random()*az_workers.size()));
  	    	    jobId = job_code_az.get((int)(Math.random()*job_code_az.size()));
  	    	    combination = personId + "-" + jobId;
  	    	} while(usedCombinations.contains(combination) || personsWithCurrentJob.contains(personId));
		
			usedCombinations.add(combination);
			personsWithCurrentJob.add(personId);  
		
			// Generate start date (between 2005-2011)
			int startYear = 2005 + (int)(Math.random()*7);
			int startMonth = (int)(Math.random()*12)+1;
			int startDay = (int)(Math.random()*28)+1;
		
			azWriter.printf("insert into works values ('%d', '%d', '%d-%02d-%02d', NULL);%n",
				personId, jobId,
				startYear, startMonth, startDay);
		}
	}

	public static void require_pos_skills_az()
	{
		for(int i=0;i<10;i++)
		{
			HashSet<String> usedPairs = new HashSet<String>();
			int random = (int)(Math.random()*7+1);
			int added = 0;
			int attempts = 0;
			
			while(added < random && attempts < 100)
			{
				int skillIdx = (int)(Math.random()*sk_code.size());
				String pair = sk_code.get(skillIdx) + "-" + pos_code.get(i);
				
				if(!usedPairs.contains(pair))
				{
					azWriter.printf("insert into requires values ('%d', '%d');%n", sk_code.get(skillIdx), pos_code.get(i));
					usedPairs.add(pair);
					added++;
				}
				attempts++;
			}
		}
	}

	static void make_inventory()
	{
		make_shelf_code();
		Collections.shuffle(numbers);
		for (int i=0;i<10;i++)
			item_num.add(numbers.get(i));
			
		for(int i=0;i<10;i++)
		{
			azWriter.printf("insert into inventory values (%d, '%s', '%s', %d, null, '%d', null, '%s', null, null);%n",
				item_num.get(i), i_title.get(i), i_description.get(i), i_quantity.get(i), i_avg_cost.get(i), i_min_level.get(i));
		}
	}
	
	static void make_shelf_code()
	{
		for(int i=60; i<70;i++)
			shelf_code.add(String.valueOf(i));
	}

	public static void make_sales()
	{
		fill_avg_cost();
		Collections.shuffle(numbers);
		for (int i=0;i<10;i++)
			invoice_num.add(numbers.get(i));
			
		for (int i=0;i<10;i++)
		{
			azWriter.printf("insert into sales values (%d, '%d', null, '%d',%d,%d,%d, %d, %d, %d, null);%n",
				item_num.get(i), store_id.get((int)(Math.random()*6)), invoice_num.get(i), 
				s_year.get(i), s_month.get(i), s_day.get(i), s_quantity.get(i), price.get(i), avg_cost.get(i));
		}
	}
	
	static void fill_avg_cost()
	{
		for(int i=0;i<10;i++)
			avg_cost.add(price.get(i)/s_quantity.get(i));
	}

	public static void make_suppliers()
	{
		Collections.shuffle(numbers);
		for(int i=0; i<5; i++)
		{
			azWriter.printf("insert into supplier values ('%d', '%s', '%s', '%s');%n",
				numbers.get(i), sup_address.get(i), sup_zip.get(i), sup_phone.get(i));
			sup_id.add(numbers.get(i));
		}
	}

	public static void make_account_payable()
	{
		for(int i=0; i<5; i++)
		{
			azWriter.printf("insert into account_payable values ('%d', '%d');%n",
				sup_id.get(i), balance.get(i));
		}
	}

	public static void make_purchases_az()
	{
		Collections.shuffle(numbers);
		for(int i=0; i<8; i++)
		{
			int id = numbers.get(i);
			int month = (int)(Math.random()*12)+1;
			int day = (int)(Math.random()*28)+1;
			String date = String.format("2024%02d%02d", month, day);

			pur_num_az.add(id);
			purchase_dates_az.add(date);

			azWriter.printf("insert into purchase values ('%d', '%d', '%s', '%d', '%d', '%d', null);%n",
				id, sup_id.get((int)(Math.random()*5)), 
				date,
				item_num.get((int)(Math.random()*10)), pur_quantity.get(i), unit_cost.get(i));
		}
	}

	public static void make_stocking()
	{
		HashSet<String> usedPairs = new HashSet<String>();
		for(int i=0; i<8; i++)
		{
			int itemIdx, purIdx;
			String pair;

			do {
				itemIdx = (int)(Math.random()*item_num.size());
				purIdx = (int)(Math.random()*pur_num_az.size());
				pair = item_num.get(itemIdx) + "-" + pur_num_az.get(purIdx);
			} while(usedPairs.contains(pair));

			usedPairs.add(pair);

			azWriter.printf("insert into stocking values ('%d', '%d', '%s');%n",
				item_num.get(itemIdx), pur_num_az.get(purIdx), purchase_dates_az.get(purIdx));
		}
	}

	public static void make_purchase_payments_az()
	{
		String[] trans_type = {"credit", "debit"};
		for(int i=0; i<pur_num_az.size(); i++)
		{
			int amount = (int)(Math.random()*50000) + 10000;

			azWriter.printf("insert into purchase_payment_record values ('%d', '%s', '%d', '%d', '%s');%n",
				pur_num_az.get(i), purchase_dates_az.get(i), sup_id.get((int)(Math.random()*5)), 
				amount, trans_type[(int)(Math.random()*2)]);
		}
	}

	public static void make_addi_requires_az()
	{
		for(int i=0; i<job_code_az.size(); i++)
		{
			HashSet<String> usedPairs = new HashSet<String>();
			int random = (int)(Math.random()*3+1);
			int added = 0;
			int attempts = 0;
			
			while(added < random && attempts < 100)
			{
				int skillIdx = (int)(Math.random()*sk_code.size());
				String pair = sk_code.get(skillIdx) + "-" + job_code_az.get(i);
				
				if(!usedPairs.contains(pair))
				{
					azWriter.printf("insert into addi_requires values ('%d', '%d');%n", 
						sk_code.get(skillIdx), job_code_az.get(i));
					usedPairs.add(pair);
					added++;
				}
				attempts++;
			}
		}
	}

	// =============== GV METHODS ===============

	public static void make_factories()
	{
		Collections.shuffle(numbers);
		for (int i=0;i<6;i++)
		{
			int id = numbers.get(i);
			fac_id.add(id);
			gvWriter.printf("insert into factory values ('%d', '%s', '%s', '%s', '%s', '%d');%n", 
				id, f_name.get(i), f_address.get(i), f_zip_code.get(i), f_phone.get(i), 
				gv_workers.get(i % gv_workers.size()));
		}
	}

	public static void make_positions_gv()
	{
		Collections.shuffle(numbers);
		for (int i=0;i<10;i++)
		{
			int id = numbers.get(i);
			pos_code_gv.add(id);
			gvWriter.printf("insert into position values ('%d', '%s', '%s', '%d', '%d');%n", 
				id, p_title.get(i+10), p_description.get(i+10), pay_range_high.get(i+10), pay_range_low.get(i+10));
		}
	}

	public static void make_jobs_gv()
	{
		Collections.shuffle(numbers2);
		for(int i=0;i<10;i++)
			job_code_gv.add(numbers2.get(i));
		Collections.shuffle(numbers2);
		ArrayList<Integer> cate_gv = new ArrayList<Integer>();
		for(int i=0;i<10;i++)
			cate_gv.add(numbers2.get(i));

		// GV schema: job_code, j_title, store_id, fac_id, comp_id, pos_code, emp_mode, pay_rate, pay_type, cate_code
		// Set store_id to NULL, use fac_id
		for (int job=0;job<10;job++)
		{
			gvWriter.printf("insert into job values ('%d', '%s', NULL, '%d', '%d', '%d', '%s', '%d', '%s', '%d');%n", 
				job_code_gv.get(job), 
				j_title.get((int)(Math.random()*5)), 
				fac_id.get((int)(Math.random()*6)),
				comp_id.get((int)Math.random()*comp_id.size()),
				pos_code_gv.get(job),
				emp_mode.get((int)(Math.random()*2)),
				pay_rate.get(job+10), 
				pay_type.get(job+10), 
				cate_gv.get(job));
		}
	}

	static void make_works_gv()
	{
		HashSet<String> usedCombinations = new HashSet<String>();
		HashSet<Integer> personsWithCurrentJob = new HashSet<Integer>(); 

	
		// 30 works records with end dates
		for(int i=0; i<30;i++)
		{
			int personId, jobId;
			String combination;
		
			do {
				personId = gv_workers.get((int)(Math.random()*gv_workers.size()));
				jobId = job_code_gv.get((int)(Math.random()*job_code_gv.size()));
				combination = personId + "-" + jobId;
			} while(usedCombinations.contains(combination));
		
			usedCombinations.add(combination);
		
			// Generate start date (between 2005-2011)
			int startYear = 2005 + (int)(Math.random()*7);
			int startMonth = (int)(Math.random()*12)+1;
			int startDay = (int)(Math.random()*28)+1;
		
			// Generate end date (between 2014-2019, after start date)
			int endYear = 2014 + (int)(Math.random()*6);
			int endMonth = (int)(Math.random()*12)+1;
			int endDay = (int)(Math.random()*28)+1;
		
			gvWriter.printf("insert into works values ('%d', '%d', '%d-%02d-%02d', '%d-%02d-%02d');%n",
				personId, jobId,
				startYear, startMonth, startDay,
				endYear, endMonth, endDay);
		}
	
		// 30 works records with null end dates (current jobs)
		for(int i=0;i<30;i++)
		{
			int personId, jobId;
			String combination;
		
		    do {
     	       personId = gv_workers.get((int)(Math.random()*gv_workers.size()));
     	       jobId = job_code_gv.get((int)(Math.random()*job_code_gv.size()));
     	       combination = personId + "-" + jobId;
        	} while(usedCombinations.contains(combination) || personsWithCurrentJob.contains(personId));  

		
			usedCombinations.add(combination);
			personsWithCurrentJob.add(personId);
		
			// Generate start date (between 2005-2011)
			int startYear = 2005 + (int)(Math.random()*7);
			int startMonth = (int)(Math.random()*12)+1;
			int startDay = (int)(Math.random()*28)+1;
		
			gvWriter.printf("insert into works values ('%d', '%d', '%d-%02d-%02d', NULL);%n",
				personId, jobId,
				startYear, startMonth, startDay);
		}
	}

	public static void require_pos_skills_gv()
	{
		for(int i=0;i<10;i++)
		{
			HashSet<String> usedPairs = new HashSet<String>();
			int random = (int)(Math.random()*7+1);
			int added = 0;
			int attempts = 0;
		
			while(added < random && attempts < 100)
			{
				int skillIdx = (int)(Math.random()*sk_code.size());
				String pair = sk_code.get(skillIdx) + "-" + pos_code_gv.get(i);
			
				if(!usedPairs.contains(pair))
				{
					gvWriter.printf("insert into requires values ('%d', '%d');%n", 
						sk_code.get(skillIdx), pos_code_gv.get(i));
					usedPairs.add(pair);
					added++;
				}
				attempts++;
			}
		}
	}

	public static void make_purchases_gv()
	{
		Collections.shuffle(numbers);
		for(int i=0; i<8; i++)
		{
			int id = numbers.get(i);
			pur_num_gv.add(id);
			gvWriter.printf("insert into purchase values ('%d', '%d', '2024%02d%02d', '%d', '%d', '%d', null);%n",
				id, (int)(Math.random()*5)+1, 
				(int)(Math.random()*12)+1, (int)(Math.random()*28)+1,
				100+i, pur_quantity.get(i), unit_cost.get(i));
		}
	}

	// =============== LD METHODS ===============
	
	static void make_GICS()
	{
		ind_id.add(1321);
		ind_id.add(2321);
		ind_id.add(3321);
		
		industry_group.addAll(ind_id);
		
		for (int i=0; i<3; i++)
		{
			String sql = String.format("insert into GICS values ('%d', '%d', '%d', '%s', '%d');%n",
				ind_id.get(i), ind_title_num.get(i), i, ind_desc.get(i), ind_id.get(0));
			ldWriter.print(sql);
		}
	}

	public static void make_companies()
	{
		Collections.shuffle(numbers);
		for (int i=0;i<6;i++)
		{
			int id = numbers.get(i);
			comp_id.add(id);
			ldWriter.printf("insert into company values ('%d', '%d', '%s', '%s', null);%n", 
				id, industry_group.get((int)(Math.random()*3)),
				c_address.get(i), c_zip_code.get(i));
		}
	}

	public static void make_positions_ld()
	{
		Collections.shuffle(numbers);
		for (int i=0;i<10;i++)
		{
			int id = numbers.get(i);
			pos_code_ld.add(id);
			ldWriter.printf("insert into position values ('%d', '%s', '%s', '%d', '%d');%n", 
				id, p_title.get(i+20), p_description.get(i+20), pay_range_high.get(i+20), pay_range_low.get(i+20));
		}
	}

	public static void make_jobs_ld()
	{
		Collections.shuffle(numbers2);
		for(int i=0;i<10;i++)
			job_code_ld.add(numbers2.get(i));
		Collections.shuffle(numbers2);
		ArrayList<Integer> cate_ld = new ArrayList<Integer>();
		for(int i=0;i<10;i++)
			cate_ld.add(numbers2.get(i));

		// LD schema: job_code, j_title, store_id, fac_id, comp_id, pos_code, emp_mode, pay_rate, pay_type, cate_code
		// Set both store_id and fac_id to NULL, use comp_id
		for (int job=0;job<10;job++)
		{
			ldWriter.printf("insert into job values ('%d', '%s', NULL, NULL, '%d', '%d', '%s', '%d', '%s', '%d');%n", 
				job_code_ld.get(job), 
				j_title.get((int)(Math.random()*5)), 
				comp_id.get((int)Math.random()*comp_id.size()),
				pos_code_ld.get(job),
				emp_mode.get((int)(Math.random()*2)),
				pay_rate.get(job+20), 
				pay_type.get(job+20), 
				cate_ld.get(job));
		}
	}

	public static void require_pos_skills_ld()
	{
		for(int i=0;i<10;i++)
		{
			HashSet<String> usedPairs = new HashSet<String>();
			int random = (int)(Math.random()*7+1);
			int added = 0;
			int attempts = 0;
		
			while(added < random && attempts < 100)
			{
				int skillIdx = (int)(Math.random()*sk_code.size());
				String pair = sk_code.get(skillIdx) + "-" + pos_code_ld.get(i);
			
				if(!usedPairs.contains(pair))
				{
					ldWriter.printf("insert into requires values ('%d', '%d');%n", 
						sk_code.get(skillIdx), pos_code_ld.get(i));
					usedPairs.add(pair);
					added++;
				}
				attempts++;
			}
		}
	}

	static void make_works_ld()
	{
		HashSet<String> usedCombinations = new HashSet<String>();
		HashSet<Integer> personsWithCurrentJob = new HashSet<Integer>();
	
		// 30 works records with end dates
		for(int i=0; i<30;i++)
		{
			int personId, jobId;
			String combination;
		
			do {
				personId = ld_workers.get((int)(Math.random()*ld_workers.size()));
				jobId = job_code_ld.get((int)(Math.random()*job_code_ld.size()));
				combination = personId + "-" + jobId;
			} while(usedCombinations.contains(combination));
		
			usedCombinations.add(combination);
		
			// Generate start date (between 2005-2011)
			int startYear = 2005 + (int)(Math.random()*7);
			int startMonth = (int)(Math.random()*12)+1;
			int startDay = (int)(Math.random()*28)+1;
		
			// Generate end date (between 2014-2019, after start date)
			int endYear = 2014 + (int)(Math.random()*6);
			int endMonth = (int)(Math.random()*12)+1;
			int endDay = (int)(Math.random()*28)+1;
		
			ldWriter.printf("insert into works values ('%d', '%d', '%d-%02d-%02d', '%d-%02d-%02d');%n",
				personId, jobId,
				startYear, startMonth, startDay,
				endYear, endMonth, endDay);
		}
	
		// 30 works records with null end dates (current jobs)
		for(int i=0;i<30;i++)
		{
			int personId, jobId;
			String combination;
		
		    
        	do {
           	 	personId = ld_workers.get((int)(Math.random()*ld_workers.size()));
           	 	jobId = job_code_ld.get((int)(Math.random()*job_code_ld.size()));
            	combination = personId + "-" + jobId;
        	} while(usedCombinations.contains(combination) || personsWithCurrentJob.contains(personId)); 

		
			usedCombinations.add(combination);
			personsWithCurrentJob.add(personId); 
		
			// Generate start date (between 2005-2011)
			int startYear = 2005 + (int)(Math.random()*7);
			int startMonth = (int)(Math.random()*12)+1;
			int startDay = (int)(Math.random()*28)+1;
		
			ldWriter.printf("insert into works values ('%d', '%d', '%d-%02d-%02d', NULL);%n",
				personId, jobId,
				startYear, startMonth, startDay);
		}
	}
	
	static void make_sub_ind()
	{
		int k=0;
		for(int i=0; i<3;i++)
		{
			for (int j=k; j<k+2; j++)
			{
				ldWriter.printf("insert into sub_ind values ('%d', '%d');%n",
					comp_id.get(j), ind_id.get(i));
			}
			k+=2;
		}
	}
	
	// =============== SHARED METHODS (Courses and Takes) ===============
	
	static void make_course()
	{
		Collections.shuffle(numbers3);
		ArrayList<Integer> course_codes = new ArrayList<Integer>();
		for (int i=0;i<30;i++)
		{
			course_codes.add(numbers3.get(i));
			c_code.add(numbers3.get(i));
		}
		
		for (int i=0; i<30;i++)
		{
			int rand = (int)(Math.random()*2);
			String sql = String.format("insert into course values ('%d', '%s', '%s', '%s', '%s', null);%n",
				course_codes.get(i), c_title.get(i), c_level.get(i), c_desc.get(i), c_status.get(rand));
			azWriter.print(sql);
			gvWriter.print(sql);
			ldWriter.print(sql);
		}
	}
	
	static void make_teaches()
	{
		for (int i=0; i<30;i++)
		{
			HashSet<String> usedPairs = new HashSet<String>();
			int added = 0;
			int attempts = 0;
			
			while(added < 2 && attempts < 100)
			{
				int skillIdx = (int)(Math.random()*sk_code.size());
				String pair = c_code.get(i) + "-" + sk_code.get(skillIdx);
				
				if(!usedPairs.contains(pair))
				{
					String sql = String.format("insert into teaches values ('%d', '%d');%n",
						c_code.get(i), sk_code.get(skillIdx));
					azWriter.print(sql);
					gvWriter.print(sql);
					ldWriter.print(sql);
					usedPairs.add(pair);
					added++;
				}
				attempts++;
			}
		}
	}
	
	static void make_takes()
	{
		// AZ workers taking courses
		make_takes_for_workers(azWriter, az_workers);
		
		// GV workers taking courses
		make_takes_for_workers(gvWriter, gv_workers);
		
		// LD workers taking courses
		make_takes_for_workers(ldWriter, ld_workers);
	}
	
	static void make_takes_for_workers(PrintWriter writer, ArrayList<Integer> workers)
	{
		HashSet<String> usedKeys = new HashSet<String>();
		int targetRecords = workers.size() * 3; // Each worker takes ~3 courses
		int inserted = 0;
		int attempts = 0;
		
		while(inserted < targetRecords && attempts < targetRecords * 10)
		{
			int personIdx = (int)(Math.random()*workers.size());
			int courseIdx = (int)(Math.random()*c_code.size());
			
			String key = workers.get(personIdx) + "-" + c_code.get(courseIdx);
			
			if(!usedKeys.contains(key))
			{
				int sec_no = (int)(Math.random()*5) + 1;
				int year = 2015 + (int)(Math.random()*10);
				int month = (int)(Math.random()*12) + 1;
				int day = (int)(Math.random()*28) + 1;
				String complete_date = String.format("%d-%02d-%02d", year, month, day);
				
				writer.printf("insert into takes values ('%d', '%d', '%d', '%s', '%d', '%s', '%s', '%d');%n",
					workers.get(personIdx), 
					c_code.get(courseIdx),
					sec_no,
					complete_date,
					year,
					offered_by.get((int)(Math.random()*offered_by.size())),
					format.get((int)(Math.random()*format.size())),
					t_price.get((int)(Math.random()*t_price.size())));
				
				usedKeys.add(key);
				inserted++;
			}
			attempts++;
		}
	}
}