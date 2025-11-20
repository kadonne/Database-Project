import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class ArrayToFilesWriter {
	
	// Person Data
	static String[] last_name = {"Smith","Johnson","Williams","Jones","Brown", "Davis", "Miller", "Wilson", "Moore", "Taylor",
				     "Kadic", "Fang", "Brisco", "Jameson", "Jenkins", "Anderson", "Thomas", "Jackson", "White", "Harris",
				     "Garcia","Martinez","Rodriguez","Lopez","Gonzalez","Sanchez","Clark","Ramirez","Lewis","Robinson"};
	static String[] first_name = {"Alivia","Billie","Aries","Birdee","Cage", "Emma", "Noah", "Olivia", "Liam", "Ava",
				      "Ammar", "Andy", "Kenneth", "Jame", "Leeroy", "Sophia", "Mason", "Isabella", "Lucas", "Mia",
				      "Sofia","Marcus","Elena","David","Ryan","Nathan","Charlotte","Logan","Amelia","Jacob"};
	static String[] address = {"497 8th St. Camp Hill, PA","9786 Albany St. Muskegon, MI","62 East Jones St. Lexington, NC",
				   "662 Madison Street Hampton, VA", "8811 Tailwater Dr. York, PA", 
				   "123 Main St. New York, NY", "456 Oak Ave. Boston, MA", "789 Pine Rd. Seattle, WA",
				   "321 Elm St. Denver, CO", "654 Maple Dr. Austin, TX",
				   "111 Factory Ln. Detroit, MI", "222 Steel St. Pittsburgh, PA", "333 Iron Ave. Cleveland, OH",
				   "444 Metal Rd. Buffalo, NY", "555 Coal St. Chicago, IL",
				   "666 Mill Ave. Milwaukee, WI", "777 Plant Rd. Indianapolis, IN", "888 Works St. Cincinnati, OH",
				   "999 Industry Ave. Columbus, OH", "101 Labor St. Toledo, OH",
				   "123 Oak Avenue, Seattle, WA","456 Pine Road, Austin, TX","789 Maple Drive, Boston, MA",
				   "321 Elm Street, Denver, CO","654 Cedar Lane, Phoenix, AZ","987 Birch Court, Portland, OR",
				   "147 Spruce Way, Miami, FL","258 Willow Path, Chicago, IL","369 Ash Boulevard, Dallas, TX",
				   "741 Cherry Circle, Atlanta, GA"};
	static String[] zip_code = {"17011", "49441","27292","23666","17402", 
				    "10001", "02101", "98101", "80201", "73301",
				    "48201", "15201", "44101", "14201", "60601",
				    "53201", "46201", "45201", "43201", "43601",
				    "98102","73302","02102","80202","85001","97201","33101","60602","75201","30301"};
	static String[] email = {"llaoulidia@piscosf.com","eabhishe@crashkiller.ovh","0hesimli@dorne.ml",
				 "3karar.alftlaw@ca11.cf","4die@harysetiabudi.site",
				 "emma.d@retail.com", "noah.m@store.com", "olivia.w@shop.com", "liam.m@sales.com", "ava.t@commerce.com",
				 "ammar.k@factory.com", "andy.f@plant.com", "kenneth.b@manufacturing.com", "jame.j@production.com", "leeroy.j@industry.com",
				 "sophia.a@foundry.com", "mason.t@steelworks.com", "isabella.j@metalworks.com", "lucas.w@forge.com", "mia.h@mill.com",
				 "sofia.g@techmail.com","marcus.m@devnet.io","elena.r@codebase.org","david.l@systech.com",
				 "ryan.g@webdev.net","nathan.s@datacore.io","charlotte.c@cloudtech.com","logan.r@appdev.net",
				 "amelia.l@innovation.io","jacob.r@digital.com"};
	static String[] gender = {"female", "female", "male", "female", "male", "female", "male", "female", "male", "female",
				  "male", "male", "male", "male", "male", "female", "male", "female", "male", "female",
				  "female","male","female","male","male","male","female","male","female","male"};

	// Skill - 40 skills
	static String[] title = {"Java","C","C++","C#","Python","Visual Basic",".NET Programming","JavaScript","JQuery","XML","Ruby",
				  "Perl", "VB Script","Microsoft Windows","Penetration-testing","Encryption","Mobile Development","Network Configuration",
				  "Forensics", "Bioinformatics",
				  "React","Angular","Vue.js","Node.js","Docker","Kubernetes","AWS","Azure","TypeScript","Go",
				  "Rust","Swift","Kotlin","Flutter","GraphQL","MongoDB","PostgreSQL","Redis","Elasticsearch","Machine Learning"};
	static String[] sk_description = { "Programming Language", "Low-level Programming Language","Programming Language","Scripting Language",
					   "Programming Language","Programming Language","Scripting Language","Scripting Language","Query Language",
					   "Scripting Language","Programming Language","Programming Language","Scripting Language","Operating System","Security","Security",
					   "Development","Networking","Security","Analyzing data",
					   "Frontend Framework","Frontend Framework","Frontend Framework","Backend Runtime","Containerization","Orchestration",
					   "Cloud Platform","Cloud Platform","Programming Language","Programming Language","Programming Language",
					   "Mobile Development","Mobile Development","Mobile Framework","API Query Language","NoSQL Database",
					   "Relational Database","In-Memory Database","Search Engine","AI/ML Technology"};
	static String[] sk_level = { "beginner", "intermediate", "advanced"};

	// Store (AZ)
	static String[] s_address = { "3258 Chapel Street, NY", "2743 Fairmont Avenue, CA", "4365 Wright Court, KY", 
				      "2517 Lynn Avenue PA", "166 Progress Way TX", "2799 Huntz Lane LA"};
	static String[] s_zip_code = { "01702", "10001", "03101", "11968", "41862", "19020"};
	static String[] s_phone = { "504-884-6576", "619-702-9198", "602-322-4702", "337-498-3531", "850-951-5364", "423-928-8970"};

	// Factory (GV)
	static String[] f_name = { "Mineral Furnace", "Furnace Fire","Fortune Furnace", "Steel Works", "Iron Foundry", "Metal Masters" };
	static String[] f_address = { "3258 Chapel Street, NY", "2743 Fairmont Avenue, CA", "4365 Wright Court, KY", 
				      "2517 Lynn Avenue PA", "166 Progress Way TX", "2799 Huntz Lane LA"};
	static String[] f_zip_code = { "01702", "10001", "03101", "11968", "41862", "66666"};
	static String[] f_phone = { "504-884-6576", "619-702-9198", "602-322-4702", "337-498-3531", "850-951-5364", "666-666-6666"};

	// Company (LD)
	static ArrayList<Integer> industry_group = new ArrayList<Integer>();
	static String[] c_address = { "3258 Chapel Street, NY", "2743 Fairmont Avenue, CA",  "4365 Wright Court, KY", 
				      "2517 Lynn Avenue PA", "166 Progress Way TX", "2799 Huntz Lane LA"};
	static String[] c_zip_code = { "01702", "10001", "03101", "11968", "41862", "66666"};

	// Position - 30 positions
	static String[] p_title = {"Software Support", "Software Developer","PC Technician","Javascript Engineer","Associate Director of Web Strategy", 
				   "Cyber Security Specialist","Enterprise IT Service Help Desk", "Medical Data Entry Genius", "Systems Librarian", 
				   "Deputy Program Manager","Full Stack Developer","DevOps Engineer","Data Scientist","Cloud Architect","Mobile Developer",
				   "UI/UX Designer","Database Administrator","Quality Assurance Engineer","Project Manager","Scrum Master",
				   "Business Analyst","Technical Writer","Network Engineer","Security Analyst","Machine Learning Engineer",
				   "Product Manager","Site Reliability Engineer","Frontend Developer","Backend Developer","Solutions Architect"};
	static String[] p_description = {"Intelligent, hard-working, creative customer focused support person",
					 "Experienced, self-motivated, and talented software engineer",
					 "Perform PC and 1st level network troubleshooting to isolate, diagnose and resolve common pc/network problems",
					 "Design front-end webpages",
					 "UI/ UX Rock Star that can not only create awesome themes, wireframes, and graphics - but code it into a responsive website",
					 "Monitor network security and regularly pen-test the server. Advise the IT-team of secure design",
					 "Sit at a desk and answer phone calls from customers",
					 "Entering medical data onto a database",
					 "Proficiency with standard computer hardware and desktop applications",
					 "Manages the connectivity of varius programs implemented",
					 "Build and maintain both client and server side applications",
					 "Automate deployment processes and manage infrastructure",
					 "Analyze complex data sets and create predictive models",
					 "Design scalable cloud infrastructure solutions",
					 "Develop native and cross-platform mobile applications",
					 "Create intuitive user interfaces and seamless user experiences",
					 "Maintain database systems and ensure data integrity",
					 "Test software to ensure quality and reliability",
					 "Plan and execute projects within scope and budget",
					 "Facilitate agile development processes and remove impediments",
					 "Bridge gap between business needs and technical solutions",
					 "Create clear technical documentation for products",
					 "Design and maintain network infrastructure",
					 "Monitor systems for security threats and vulnerabilities",
					 "Develop and deploy machine learning models",
					 "Define product vision and roadmap",
					 "Ensure system reliability and performance at scale",
					 "Build responsive user interfaces",
					 "Develop server-side logic and APIs",
					 "Design end-to-end technical solutions for clients"};
	static Integer[] pay_range_high={70000,96000,90000,75000,95000,130000, 60000, 70000, 90000, 100000,
					 110000,120000,135000,140000,105000,85000,95000,88000,115000,105000,
					 92000,78000,98000,108000,145000,125000,130000,100000,110000,135000};
	static Integer[] pay_range_low={50000,70000,60000,54000,80000,92000, 40000, 60000, 50000, 63000,
					75000,85000,95000,100000,70000,60000,70000,65000,80000,75000,
					68000,55000,72000,78000,105000,90000,95000,70000,80000,100000};

	// Job
	static String[] j_title = {"Front-end", "Back-end", "Software Engineer", "Information Technology", "Network management"};
	static String[] emp_mode={"full-time","part-time"};
	static Integer[] pay_rate={33,50, 90000,67,95000,130000,31,70000,90000,100000,
				   45,55,110000,75,125000,140000,38,85000,105000,115000,
				   42,52,95000,68,105000,135000,35,80000,95000,105000};
	static String[] pay_type={"wage","wage","salary","wage","salary","salary","wage","salary","salary","salary",
				  "wage","wage","salary","wage","salary","salary","wage","salary","salary","salary",
				  "wage","wage","salary","wage","salary","salary","wage","salary","salary","salary"};
	static Integer[] company = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30};

	// Inventory (AZ)
	static String[] i_title = {"Lucas Oil Products", "Bondo Professional","Bondo Professional", "Rust-Oleum","Flex Glue",
			 	   "REESE Towpower","Dralast Ratchet","Duralast Accessory Set","Bosch Iridium", "Duralast Ceramic"};
	static String[] i_description = { "Low Viscosity stabilizer", "Fast Dry Filler 1 Quart","Fast Dry Filler 14oz",
					  "Matte black truck bed turbo spray 24oz","Rubberized Waterproof Adhesive 6oz",
					  "Tactical Hitch Pin", "Combination Wrench Set 16 Piece", "17 piece","Spark plug 9602","Brake Pads D866"};
	static Integer[] i_quantity = { 50000, 400000, 800000, 4000000, 300000, 900000, 700000, 800000, 123421, 200000 };
	static Integer[] i_avg_cost = { 412, 1234, 532, 8343, 393, 29, 902, 213, 643, 265};
	static Integer[] i_min_level = {60000, 3210, 900000, 300000, 2300000, 1239539, 800000, 1230, 213, 250000};

	// Sales (AZ)
	static Integer[] s_year = {2018,2018,2017,2015,2019,2018,2016,2016,2017,2015};
	static Integer[] s_month = {05, 04, 05, 03, 02, 12, 9, 06, 01, 02};
	static Integer[] s_day = { 21,23,12,11,15,14,20,10,05,01};
	static Integer[] s_quantity = { 23, 12, 9, 100, 500, 2000, 300, 30, 30, 50};
	static Integer[] price = {2000, 3000, 5000, 31256, 35235, 124125, 909320, 125215, 2919, 100000};
	static Integer[] avg_cost = new Integer[10];

	// Supplier (AZ)
	static String[] sup_address = {"123 Industrial Blvd", "456 Warehouse Way", "789 Supply St", "321 Vendor Ave", "654 Trade Rd"};
	static String[] sup_zip = {"70001", "70002", "70003", "70004", "70005"};
	static String[] sup_phone = {"5551234567", "5552345678", "5553456789", "5554567890", "5555678901"};
	static Integer[] balance = {50000, 75000, 100000, 25000, 60000};

	// Purchase
	static Integer[] pur_quantity = {100, 200, 150, 300, 250, 180, 220, 190};
	static Integer[] unit_cost = {50, 75, 100, 45, 80, 60, 70, 55};

	// GICS (LD)
	static ArrayList<Integer> ind_id = new ArrayList<Integer>();
	static Integer[] ind_title_num = {1, 2, 3};
	static String[] ind_desc = {"Technology Services", "Financial Services", "Healthcare Services"};

	// Course - 30 courses (shared across all databases)
	static String[] course_title = { "General Networking", "IT Management","JAVA","JavaScript and jQuery",
					"Cybersecurity","Database Administration","Sales Management","Project Management Software",
					"Enterprise Resource Planning","C And C++",
					"Advanced React Development","Cloud Computing with AWS","Docker and Kubernetes",
					"Data Science Fundamentals","Machine Learning Basics","Mobile App Development",
					"Advanced Python Programming","DevOps Best Practices","Agile Methodologies",
					"UI/UX Design Principles","Microservices Architecture","GraphQL APIs",
					"Blockchain Fundamentals","AI Ethics","Full Stack Web Development",
					"Security Best Practices","NoSQL Databases","Test Driven Development",
					"CI/CD Pipeline Setup","System Design Interview Prep"};
	static String[] c_level = {"1","1","1","2","4","3","2","5","3","4",
				   "3","2","4","2","3","2","3","4","2","2",
				   "4","3","3","4","3","3","2","3","4","5"};
	static String[] c_desc = { "Obtain a good understanding of how networks works",
				   "Based on learning how to teach someone how to lead a team to completing a task in a quick manner",
				   "Understanding the major factors of JAVA","Learn the ends and out on JavaScript and jQuery",
				   "Learn How To Prevent Vulnerabilities by becoming a white hat hacker",
				   "Learn the final ins and outs of management and databases",
				   "Learn how make the most profit",
				   "Learning the minor things people usually miss and continue to improve your management",
				   "Learn how to properly plan events",
				   "Learn the importance of C and C++ and how to code general databases with these two languages",
				   "Master React hooks, context, and advanced patterns",
				   "Deploy scalable applications on Amazon Web Services",
				   "Container orchestration and microservices deployment",
				   "Statistical analysis and data visualization techniques",
				   "Introduction to supervised and unsupervised learning",
				   "Build native iOS and Android applications",
				   "Advanced features and best practices in Python",
				   "Automate infrastructure and deployment processes",
				   "Scrum, Kanban, and lean software development",
				   "Create user-centered designs and prototypes",
				   "Design distributed systems with microservices",
				   "Build efficient and flexible APIs with GraphQL",
				   "Understanding distributed ledger technology",
				   "Ethical considerations in artificial intelligence",
				   "Build complete web applications from scratch",
				   "Application security and threat mitigation",
				   "MongoDB, Cassandra, and document databases",
				   "Write testable code and automate testing",
				   "Continuous integration and deployment strategies",
				   "Learn to design scalable distributed systems"};
	static String[] c_status = {"active","expired"};

	// Takes
	static String[] offered_by = {"Coursera", "Udemy", "LinkedIn Learning", "edX", "Pluralsight", "Khan Academy"};
	static String[] format = {"online", "in-person", "hybrid"};
	static Integer[] t_price = {299, 499, 199, 399, 599, 149, 249, 449, 349, 799};

	public static void main(String[] args) {
		// Write all arrays to their respective files
		writeStringArray("../data/person/last_name.txt", last_name);
		writeStringArray("../data/person/first_name.txt", first_name);
		writeStringArray("../data/person/address.txt", address);
		writeStringArray("../data/person/zip_code.txt", zip_code);
		writeStringArray("../data/person/email.txt", email);
		writeStringArray("../data/person/gender.txt", gender);
		
		writeStringArray("../data/skill/sk_title.txt", title);
		writeStringArray("../data/skill/sk_description.txt", sk_description);
		writeStringArray("../data/skill/sk_level.txt", sk_level);
		
		writeStringArray("../data/store/s_address.txt", s_address);
		writeStringArray("../data/store/s_zip_code.txt", s_zip_code);
		writeStringArray("../data/store/s_phone.txt", s_phone);
		
		writeStringArray("../data/factory/f_name.txt", f_name);
		writeStringArray("../data/factory/f_address.txt", f_address);
		writeStringArray("../data/factory/f_zip_code.txt", f_zip_code);
		writeStringArray("../data/factory/f_phone.txt", f_phone);
		
		writeStringArray("../data/company/c_address.txt", c_address);
		writeStringArray("../data/company/c_zip_code.txt", c_zip_code);
		
		writeStringArray("../data/position/p_title.txt", p_title);
		writeStringArray("../data/position/p_description.txt", p_description);
		writeIntegerArray("../data/position/pay_range_high.txt", pay_range_high);
		writeIntegerArray("../data/position/pay_range_low.txt", pay_range_low);
		
		writeStringArray("../data/job/j_title.txt", j_title);
		writeStringArray("../data/job/emp_mode.txt", emp_mode);
		writeIntegerArray("../data/job/pay_rate.txt", pay_rate);
		writeStringArray("../data/job/pay_type.txt", pay_type);
		writeIntegerArray("../data/job/company.txt", company);
		
		writeStringArray("../data/inventory/i_title.txt", i_title);
		writeStringArray("../data/inventory/i_description.txt", i_description);
		writeIntegerArray("../data/inventory/i_quantity.txt", i_quantity);
		writeIntegerArray("../data/inventory/i_avg_cost.txt", i_avg_cost);
		writeIntegerArray("../data/inventory/i_min_level.txt", i_min_level);
		
		writeIntegerArray("../data/sales/s_year.txt", s_year);
		writeIntegerArray("../data/sales/s_month.txt", s_month);
		writeIntegerArray("../data/sales/s_day.txt", s_day);
		writeIntegerArray("../data/sales/s_quantity.txt", s_quantity);
		writeIntegerArray("../data/sales/price.txt", price);
		
		writeStringArray("../data/supplier/sup_address.txt", sup_address);
		writeStringArray("../data/supplier/sup_zip.txt", sup_zip);
		writeStringArray("../data/supplier/sup_phone.txt", sup_phone);
		writeIntegerArray("../data/supplier/balance.txt", balance);
		
		writeIntegerArray("../data/purchase/pur_quantity.txt", pur_quantity);
		writeIntegerArray("../data/purchase/unit_cost.txt", unit_cost);
		
		writeIntegerArray("../data/industry/ind_title_num.txt", ind_title_num);
		writeStringArray("../data/industry/ind_desc.txt", ind_desc);
		
		writeStringArray("../data/course/course_title.txt", course_title);
		writeStringArray("../data/course/c_level.txt", c_level);
		writeStringArray("../data/course/c_desc.txt", c_desc);
		writeStringArray("../data/course/c_status.txt", c_status);
		
		writeStringArray("../data/takes/offered_by.txt", offered_by);
		writeStringArray("../data/takes/format.txt", format);
		writeIntegerArray("../data/takes/t_price.txt", t_price);
		
		System.out.println("All files have been created successfully!");
	}
	
	private static void writeStringArray(String filename, String[] array) {
		try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
			for (String element : array) {
				writer.println(element);
			}
			System.out.println("Created: " + filename);
		} catch (IOException e) {
			System.err.println("Error writing to " + filename + ": " + e.getMessage());
		}
	}
	
	private static void writeIntegerArray(String filename, Integer[] array) {
		try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
			for (Integer element : array) {
				writer.println(element);
			}
			System.out.println("Created: " + filename);
		} catch (IOException e) {
			System.err.println("Error writing to " + filename + ": " + e.getMessage());
		}
	}
}