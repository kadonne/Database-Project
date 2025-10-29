import java.util.ArrayList;
import java.util.Collections;
public class Populate_tables
{
	static ArrayList<Integer> numbers = new ArrayList<Integer>();
	static ArrayList<Integer> numbers2 = new ArrayList<Integer>();
	static ArrayList<Integer> per_id = new ArrayList<Integer>();
	static ArrayList<Integer> sk_code = new ArrayList<Integer>();
	static ArrayList<Integer> store_id = new ArrayList<Integer>();
	static ArrayList<Integer> job_code = new ArrayList<Integer>();
	static ArrayList<Integer> cate_code = new ArrayList<Integer>();
	static ArrayList<Integer> pos_code = new ArrayList<Integer>();
	//Person ==============================================================================================
	static String[] last_name = {"Smith","Johnson","Williams","Jones","Brown"};
	static String[] first_name = {"Alivia","Billie","Aries","Birdee","Cage"};
	static String[] address = {"497 8th St. Camp Hill, PA","9786 Albany St. Muskegon, MI","62 East Jones St. Lexington, NC",
				   "662 Madison Street Hampton, VA", "8811 Tailwater Dr. York, PA"};
	static String[] zip_code = {"17011", "49441","27292","23666","17402"};
	static String[] email = {"llaoulidia@piscosf.com","eabhishe@crashkiller.ovh","0hesimli@dorne.ml",
				 "3karar.alftlaw@ca11.cf","4die@harysetiabudi.site"};
	static String[] gender = {"female", "female", "male", "female", "male"};
	//Person ==============================================================================================

	//Skill ==============================================================================================	
	static String[] title = {"Java","C","C++","C#","Python","Visual Basic",".NET Programming","JavaScript","JQuery","XML","Ruby",
				  "Perl", "VB Script","Microsoft Windows","Penetration-testing","Encryption","Mobile Development","Network Configuration",
				  "Forensics", "Bioinformatics"};
	static String[] sk_description = { "Programming Language", "Low-level Programming Language","Programming Language","Scripting Language",
					   "Programming Language","Programming Language","Scripting Language","Scripting Language","Query Language",
					   "Scripting Language","Programming Language","Programming Language","Scripting Language","Operating System","Security","Security",
					   "Development","Networking","Security","Analyzing data" };

	static String[] sk_level = { "beginner", "intermediate", "advanced"};
	//Skill ==============================================================================================

	//Store ==============================================================================================
	static String[] s_address = { "3258 Chapel Street, NY", "2743 Fairmont Avenue, CA", "4365 Wright Court, KY", "2517 Lynn Avenue PA", "166 Progress Way TX", "2799 Huntz Lane LA"};
	static String[] s_zip_code = { "01702", "10001", "03101", "11968", "41862", "19020"};
	static String[] s_phone = { "504-884-6576", "619-702-9198", "602-322-4702", "337-498-3531", "850-951-5364", "423-928-8970"};
	//Store ==============================================================================================

	//Position ==============================================================================================
	static String[] p_title = {"Software Support", "Software Developer","PC Technician","Javascript Engineer","Associate Director of Web Strategy", "Cyber Security Specialist",
				   "Enterprise IT Service Help Desk", "Medical Data Entry Genius", "Systems Librarian", "Deputy Program Manager"};
	static String[] p_description = {"Intelligent, hard-working, creative customer focused support person",
					 "Experienced, self-motivated, and talented software engineer",
					 "Perform PC and 1st level network troubleshooting to isolate, diagnose and resolve common pc/network problems",
					 "Design front-end webpages",
					 "UI/ UX Rock Star that can not only create awesome themes, wireframes, and graphics - but code it into a responsive website",
					 "Monitor network security and regularly pen-test the server. Advise the IT-team of secure design",
					 "Sit at a desk and answer phone calls from customers",
					 "Entering medical data onto a database",
					 "Proficiency with standard computer hardware and desktop applications",
					 "Manages the connectivity of varius programs implemented" };

	static Integer[] pay_range_high={70000,96000,90000,75000,95000,130000, 60000, 70000, 90000, 100000};
	static Integer[] pay_range_low={50000,70000,60000,54000,80000,92000, 40000, 60000, 50000, 63000};
	//Position ==============================================================================================

	//Job ==============================================================================================
	static String[] j_title = {"Front-end", "Back-end", "Software Engineer", "Information Technology", "Network management"};
	static String[] emp_mode={"full-time","part-time"};
	static Integer[] pay_rate={33,50, 90000,67,95000,130000,31,70000,90000,100000};
	static String[] pay_type={"wage","wage","salary","wage","salary","salary","wage","salary","salary","salary"};
	static String[] company = {"WebApps", "Whetstone Education","Odyssey House Louisiana","University of New Orleans","DXC","Oschner"};
	//Job ==============================================================================================

	//Sales ==============================================================================================
	static ArrayList<Integer> invoice_num = new ArrayList<Integer>();
	static ArrayList<Integer> item_num = new ArrayList<Integer>();
	static Integer[] s_year = {2018,2018,2017,2015,2019,2018,2016,2016,2017,2015};
	static Integer[] s_month = {05, 04, 05, 03, 02, 12, 9, 06, 01, 02};
	static Integer[] s_day = { 21,23,12,11,15,14,20,10,05,01};
	static Integer[] s_quantity = { 23, 12, 9, 100, 500, 2000, 300, 30, 30, 50};
	static Integer[] price = {2000, 3000, 5000, 31256, 35235, 124125, 909320, 125215, 2919, 100000};
	static Integer[] avg_cost = new Integer[10];
	static String[] note = new String[10];
	//Sales ==============================================================================================

	//Inventory ==============================================================================================
	static ArrayList<Integer> shelf_code = new ArrayList<Integer>();
	static String[] i_title = {"Lucas Oil Products", "Bondo Professional","Bondo Professional", "Rust-Oleum","Flex Glue",
			 	   "REESE Towpower","Dralast Ratchet","Duralast Accessory Set","Bosch Iridium", "Duralast Ceramic"};
	static String[] i_description = { "Low Viscosity stabilizer", "Fast Dry Filler 1 Quart","Fast Dry Filler 14oz",
					  "Matte black truck bed turbo spray 24oz","Rubberized Waterproof Adhesive 6oz",
					  "Tactical Hitch Pin", "Combination Wrench Set 16 Piece", "17 piece","Spark plug 9602","Brake Pads D866"};
	static Integer[] i_quantity = { 50000, 400000, 800000, 4000000, 300000, 900000, 700000, 800000, 123421, 200000 };
	static Integer[] i_avg_cost = { 412, 1234, 532, 8343, 393, 29, 902, 213, 643, 265};
	static Integer[] i_min_level = {60000, 3210, 900000, 300000, 2300000, 1239539, 800000, 1230, 213, 250000};
	//Inventory ==============================================================================================

	public static void main(String[] args)
	{
		for(int i=0;i<20;i++)
			numbers.add(i);
		for(int i=30;i<50;i++)
			numbers2.add(i);
		make_people();
		make_skills();
		connect_person_skills();
		make_stores();
		make_positions();
		make_jobs();
		require_pos_skills();
		make_sales();
		make_inventory();
	}

	public static void make_people()
	{
		Collections.shuffle(numbers);
		for (int i=0;i<5;i++)
		{
			System.out.printf("insert into person values ('%d', '%s', '%s', '%s', '%s', '%s', '%s');%n", 
			numbers.get(i), last_name[i], first_name[i], address[i], zip_code[i], email[i], gender[i]);
			per_id.add(numbers.get(i));
		}
	}
	public static void make_skills()
	{
		Collections.shuffle(numbers);
		for(int i=0; i<20;i++)
		{
			int random = (int)(Math.random()*3);
			System.out.printf("insert into skill values ('%d','%s','%s','%s');%n", numbers.get(i), title[i], sk_description[i],sk_level[random]);
			sk_code.add(numbers.get(i));
		}

	}
	
	public static void connect_person_skills()
	{
		for (int i=0;i<5;i++)
		{
			Collections.shuffle(numbers);
			int random = (int)(Math.random()*10+1);
			//System.out.printf("/* %d */%n",random);
			for (int j=0;j<random;j++)
			{
				System.out.printf("insert into has_skill values ('%d', '%d');%n", per_id.get(i), sk_code.get(numbers.get(j)));

			}
		}
		
	}

	public static void make_stores()
	{
		Collections.shuffle(numbers);
		for (int i=0;i<6;i++)
		{
			System.out.printf("insert into store values ('%d', '%s', '%s', '%s');%n", numbers.get(i), s_address[i], s_zip_code[i], s_phone[i]);
			store_id.add(numbers.get(i));
		}
	}

	public static void make_positions()
	{
		Collections.shuffle(numbers);
		for (int i=0;i<10;i++)
		{
			System.out.printf("insert into position values ('%d', '%s', '%s', '%d', '%d');%n", numbers.get(i), p_title[i], p_description[i], 
													  pay_range_high[i], pay_range_low[i]);
			pos_code.add(numbers.get(i));
		}
	}

	public static void make_jobs()
	{
		Collections.shuffle(numbers);
		Collections.shuffle(numbers);
		Collections.shuffle(numbers);
		for(int i=0;i<10;i++)
			job_code.add(numbers.get(i));
		Collections.shuffle(numbers);
		for(int i=0;i<10;i++)
			cate_code.add(numbers.get(i));

		ArrayList<Integer> num_position = new ArrayList<Integer>();

		for(int i=0;i<10;i++)
			num_position.add(i);

		Collections.shuffle(num_position);
		Collections.shuffle(num_position);

		for (int job=0;job<10;job++)
		{
			System.out.printf("insert into job values ('%d', '%s', '%d', '%d', '%d', '%s','%d','%s','%d','%s');%n", 
				job_code.get(job),j_title[(int)(Math.random()*4)], store_id.get((int)(Math.random()*5)),
				pos_code.get((int)(Math.random()*10)),
				per_id.get((int)(Math.random()*5)),emp_mode[(int)(Math.random()*2)],
				pay_rate[job], pay_type[job], cate_code.get(job), "Auto Zone");
		}

	}

	public static void require_pos_skills()
	{
		for(int i=0;i<10;i++)
		{
			Collections.shuffle(numbers);
			int random = (int)(Math.random()*7+1);
			for(int j=0;j<random;j++)
			{
				System.out.printf("insert into requires values ('%d', '%d');%n", sk_code.get(numbers.get(j)), pos_code.get(i));
			}
		}
	}

	public static void make_sales()
	{
		fill_avg_cost();

		Collections.shuffle(numbers);
		for (int i=0;i<10;i++)
			invoice_num.add(numbers.get(i));
		Collections.shuffle(numbers);
		for (int i=0;i<10;i++)
			item_num.add(numbers.get(i));
			
		for (int i=0;i<10;i++)
		{
			//int random_store = (int)(Ma
			System.out.printf("insert into sales values (%d, '%s', null, '%s',%d,%d,%d, %d, %d, %d, null);%n",
			item_num.get(i),store_id.get((int)(Math.random()*6)),invoice_num.get(i),s_year[i],s_month[i], s_day[i],
			s_quantity[i],price[i], avg_cost[i]);
		}
	}
	
	static void fill_avg_cost()
	{
		for(int i=0;i<10;i++)
			avg_cost[i] = price[i]/s_quantity[i];
	}

	static void make_inventory()
	{
		make_shelf_code();

		Collections.shuffle(numbers);
		Collections.shuffle(numbers);
		for(int i=0;i<10;i++)
			System.out.printf("insert into inventory values (%d, '%s', '%s', %d, null, '%d', null, %d, null, null);%n",
			item_num.get(i),i_title[i], i_description[i], i_quantity[i], i_avg_cost[i],
			i_min_level[i] );
	}
	static void make_shelf_code()
	{
		for(int i=60; i<70;i++)
			shelf_code.add(i);
	}
}
