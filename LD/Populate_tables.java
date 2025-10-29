import java.util.ArrayList;
import java.util.Collections;
public class Populate_tables
{
	static ArrayList<Integer> numbers = new ArrayList<Integer>();
	static ArrayList<Integer> numbers2 = new ArrayList<Integer>();
	static ArrayList<Integer> per_id = new ArrayList<Integer>();
	static ArrayList<Integer> sk_code = new ArrayList<Integer>();
	static ArrayList<Integer> comp_id = new ArrayList<Integer>();
	static ArrayList<Integer> job_code = new ArrayList<Integer>();
	static ArrayList<Integer> cate_code = new ArrayList<Integer>();
	static ArrayList<Integer> pos_code = new ArrayList<Integer>();
	//Person ==============================================================================================
	static String[] last_name = {"Smith","Johnson","Williams","Jones","Brown", "Kadic", "Fang", "Brisco", "Jameson", "Jenkins"};
	static String[] first_name = {"Alivia","Billie","Aries","Birdee","Cage", "Ammar", "Andy", "Kenneth", "Jame", "Leeroy"};
	static String[] address = {"497 8th St. Camp Hill, PA","9786 Albany St. Muskegon, MI","62 East Jones St. Lexington, NC",
				   "662 Madison Street Hampton, VA", "8811 Tailwater Dr. York, PA", null, null, null, null, null};
	static String[] zip_code = {"17011", "49441","27292","23666","17402", null, null, null, null, null};
	static String[] email = {"llaoulidia@piscosf.com","eabhishe@crashkiller.ovh","0hesimli@dorne.ml",
				 "3karar.alftlaw@ca11.cf","4die@harysetiabudi.site", null, null, null, null, null};
	static String[] gender = {"female", "female", "male", "female", "male", null, null ,null, null, null};
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

	//Company ==============================================================================================
	static ArrayList<Integer> industry_group = new ArrayList<Integer>();
	static String[] c_address = { "3258 Chapel Street, NY", "2743 Fairmont Avenue, CA",  "4365 Wright Court, KY", 
				      "2517 Lynn Avenue PA", "166 Progress Way TX", "2799 Huntz Lane LA"};
	static String[] c_zip_code = { "01702", "10001", "03101", "11968", "41862", "66666"};
	//Company ==============================================================================================

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

	//GICS ==============================================================================================
	static ArrayList<Integer> ind_id = industry_group;
	static String[] ind_title= {"ind_1","ind_2","ind_3"};
	static String[] ind_desc = {"this is ind_1", "this is ind_2", "this is ind_3"};
	//GICS ==============================================================================================

	//Course ==============================================================================================
	static ArrayList<Integer> c_code = new ArrayList<Integer>();
	static String[] c_title = { "General Networking", "IT Management","JAVA","JavaScript and jQuery",
					"Cybersecurity","Database Administration","Sales Management","Project Management Software",
					"Enterprise Resource Planning","C And C++" };
	static Integer[] c_level = {1,1,1,2,4,3,2,5,3,4};
	static String[] c_desc = { "Obtain a good understanding of how networks works",
				   "Based on learning how to teach someone how to lead a team to completing a task in a quick manner",
				   "Understanding the major factors  of JAVA","Learn the ends and out on JavaScript and jQuery",
				   "Learn How To Prevent Vulnerabilities by becoming a white hat hacker",
				   "Learn the final ends and outs of management and databases",
				   "Learn how make the most profit",
				   "Learning the minor things people usually miss and continue to improve your managent",
				   "Learn how to properly plan events",
				   "Learn the importance of C and C++ and how to code general databases with these two languages"};
	static String[] c_status = {"active","expired"};
	//Course ==============================================================================================
	public static void main(String[] args)
	{
		for(int i=0;i<20;i++)
			numbers.add(i);
		for(int i=30;i<50;i++)
			numbers2.add(i);
		make_people();
		make_skills();
		connect_person_skills();
		make_companies();
		make_positions();
		make_jobs();
		require_pos_skills();
		make_works();
		make_GICS();
		make_course();
		make_teaches();
		make_sub_ind();
	}

	public static void make_people()
	{
		Collections.shuffle(numbers);
		for (int i=0;i<10;i++)
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
		for (int i=0;i<per_id.size();i++)
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

	public static void make_companies()
	{


		industry_group.add(1321);
		industry_group.add(2321);
		industry_group.add(3321);

		Collections.shuffle(numbers);
		for (int i=0;i<6;i++)
		{
			System.out.printf("insert into company values ('%d', '%d', '%s', '%s', null);%n", 
			numbers.get(i), industry_group.get((int)(Math.random()*3)),
			c_address[i], c_zip_code[i] );
			comp_id.add(numbers.get(i));
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
			System.out.printf("insert into job values ('%d', '%s', '%d', '%d', '%s','%d','%s','%d');%n", 
				job_code.get(job),j_title[(int)(Math.random()*4)], comp_id.get((int)(Math.random()*5)),
				pos_code.get((int)(Math.random()*10)),
				emp_mode[(int)(Math.random()*2)],
				pay_rate[job], pay_type[job], cate_code.get(job));
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

	static void make_works()
	{
		for(int i=0; i<10;i++)
		{
			System.out.printf("insert into works values ('%d', '%d', '%d', '%d', '%d', '%d', '%d', '%d');%n",
			per_id.get((int)(Math.random()*per_id.size())), job_code.get((int)(Math.random()*10)),
			2014+(int)(Math.random()*6), (int)(Math.random()*13), (int)(Math.random()*29)+1,
			2005+(int)(Math.random()*7), (int)(Math.random()*13), (int)(Math.random()*29)+1);
		}
		for(int i=0;i<10;i++)
		{
			System.out.printf("insert into works values ('%d', '%d', %d, %d, %d, %d, %d, %d);%n",
			per_id.get((int)(Math.random()*per_id.size())), job_code.get((int)(Math.random()*10)),
			null,null,null,2005+(int)(Math.random()*7),(int)(Math.random()*13),(int)(Math.random()*29)+1);
		}
	}

	static void make_GICS()
	{
		for (int i=0; i<3; i++)
		{
			System.out.printf("insert into GICS values ('%d', '%s', '%d', '%s', '%d');%n",
			ind_id.get(i), ind_title[i], i, ind_desc[i], ind_id.get(0));
		}
	}
	
	static void make_course()
	{
		Collections.shuffle(numbers);
		for (int i=0;i<10;i++)
			c_code.add(numbers.get(i));
		for (int i=0; i<10;i++)
		{
			int rand = (int)(Math.random()*2);
			System.out.printf("insert into course values ('%d', '%s', '%d', '%s', '%s', null);%n",
			c_code.get(i), c_title[i], c_level[i], c_desc[i], c_status[rand]);
		}
	}
	
	static void make_teaches()
	{
		for (int i=0; i<10;i++)
		{
			Collections.shuffle(numbers);
			for(int skill=0; skill<2;skill++)
			{
				System.out.printf("insert into teaches values ('%d', '%d');%n",
				c_code.get(i), sk_code.get(numbers.get(skill)));
			}
		}
	}

	static void make_sub_ind()
	{
		int k=0;
		for(int i=0; i<3;i++)
		{
			for (int j=k; j<k+2; j++)
			{
				System.out.printf("insert into sub_ind values ('%d', '%d');%n",
				comp_id.get(j), ind_id.get(i));
			}
			k+=2;
		}
	}
}
