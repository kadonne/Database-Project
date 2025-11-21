# Workforce Management Database Systems

A comprehensive multi-database system implementing three interconnected relational databases for workforce management, career planning, recruiting services, and business operations. This project demonstrates advanced database design, complex SQL querying, normalization theory, and JDBC programming for real-world business scenarios.

## Project Overview

This project implements three independent yet interconnected database systems modeling real-world workforce and business operations:

* **LD (Labor Department) Database**: Central workforce management system providing career planning, job hunting assistance, recruiting services, and training recommendations
* **AZ (Auto Zone) Database**: Retail company database managing employees, multiple store locations, inventory, sales, and supplier relationships
* **GV (Manufacturing) Database**: Manufacturing company database managing employees across multiple factories, materials, products, contracts, and customer relationships

## Team Members

* **Ammar**: Lead developer - Database runner implementation, table population scripts, query design and implementation
* **Kenneth**: Database architect - SQL schema creation, query design assistance
* **Andy**: Application developer - Database runner development assistance

## Repository Structure

```
Database-Project/
├── DatabaseRunner.java                          # Main JDBC Application driver  
├── Populate_tables.java                         # Data population program 
├── create_databases_users.sql                   # Database and User creation SQL script
├── setup.ps1                                    # All-in-one script that can run all the other scripts and start up the JDBC Application Driver
├── repopulate.ps1                               # Script that runs the population program and logs on to each user to populate 
├── database_run.ps1                             # Runs the JDBC Application Driver 
├── postgresql-42.7.8.jar                        # POSTGRESQL jar file with the version used at the time 
├── data/                                        # Data organized into text files for easy addition
│   ├── person/                                    
│   ├── skill/                                     
│   ├── store/                                     
│   ├── factory/                                   
│   ├── company/                                   
│   ├── position/                                  
│   ├── job/                                       
│   ├── inventory/                                 
│   ├── sales/                                     
│   ├── supplier/                                  
│   ├── purchase/                                  
│   ├── industry/                                  
│   ├── course/                                    
│   └── takes/                                     
├── AZ/                                          # AZ database files
│   ├── make_tables.sql                          # Generate database schema
│   ├── clear.sql                                # Drop the relations 
│   └── populate.sql (generated)                 # SQL script that populates the databases 
├── GV/                                          # GV database files  
│   ├── make_tables.sql                           
│   ├── clear.sql                                  
│   └── populate.sql (generated)                               
└── LD/                                          #LD database files 
    ├── make_tables.sql                            
    ├── clear.sql                                  
    └── populate.sql (generated)                               
```

## Installation & Setup

### 1. Database Creation, Population, JDBC Run: All-In-One Script (ignore other steps if you choose this one)

**Run this setup script (Only works for Windows):**
```powershell
.\setup.ps1 user1 user2 user2 user_password
OR
.\setup.ps1 #(default arguments: ammar andy kenneth 12345)
```
This script runs everything all at once. Creates databases and users, constructs schemas, populates the databases, runs the JDBC program.

**NOTE: Edit this script and change -U postgres to your postgresql super username:**
```powershell
psql -U postgres --set user1=$user1 --set user2=$user2 --set user3=$user3 --set user_pass=$user_pass -f create_databases_users.sql
```

### 2. Database and User Creation:

**Run this SQL script to create the Databases and Users:**
```powershell
psql -U postgres --set user1=$user1 --set user2=$user2 --set user3=$user3 --set user_pass=$user_pass -f create_databases_users.sql
```

### 3. Database Population
**Run this to populate the databases: (repopulate.ps1)**
```powershell
javac Populate_tables.java
java Populate_tables

$env:PGPASSWORD = $user_pass;

psql -U $user1 -d $user1 -f AZ\clear.sql -f AZ\make_tables.sql -f AZ\populate.sql;
psql -U $user2 -d $user2 -f LD\clear.sql -f LD\make_tables.sql -f LD\populate.sql;
psql -U $user3 -d $user3 -f GV\clear.sql -f GV\make_tables.sql -f GV\populate.sql;
```
Where $user_pass can be set to whatever password you assigned to each user. I made it so it's the same for all users for simplicity sake.

### 4. JDBC Run
**Run this to start the JDBC program: (database_run.ps1)**
```powershell
$jar = Get-ChildItem -Path "." -Filter "postgresql*.jar" | Select-Object -First 1

javac -cp $jar.Name .\DatabaseRunner.java
java -cp ".;$($jar.Name)" DatabaseRunner $user1 $user2 $user3 $user_pass
```

## Database Architectures

### 1. LD Database (Labor Department)

**Purpose**: Nationwide workforce management, career planning, and skills matching system

**Core Entities:**

* `Person(per_id, last_name, first_name, address, zip_code, email, gender)`
* `Skill(sk_code, sk_title, sk_description, sk_level)`
  * Levels: beginner, intermediate, advanced
* `Position(pos_code, pos_title, description, pay_range_high, pay_range_low)`
* `Job(job_code, j_title, comp_id, pos_code, emp_mode, pay_rate, pay_type, cate_code)`
  * emp_mode: full-time, part-time
  * pay_type: wage, salary
* `Company(comp_id, industry_group, address, zip_code, website)`
* `GICS(ind_id, ind_title, ind_level, ind_description, parent_id)`
  * Global Industry Classification Standard hierarchy
* `Course(c_code, c_title, c_level, description, status, retail_price)`
  * status: active, expired

**Key Relationships:**

* `Works(per_id, job_code, start_year, start_month, start_day, end_year, end_month, end_day)`
  * Tracks complete employment history (current and past jobs)
* `Has_Skill(per_id, sk_code)`
  * Skills possessed by workers
* `Requires(pos_code, sk_code)`
  * Skills required for positions
* `Teaches(c_code, sk_code)`
  * Skills taught by courses
* `Sub_Ind(comp_id, ind_id)`
  * Company sub-industry classifications

### 2. AZ Database (Auto Zone)

**Purpose**: Multi-store retail operations with employee management, inventory tracking, and financial operations

**Core Entities:**

* `Store(store_id, address, zip_code, phone)`
* `Inventory(item_num, title, description, quantity, unit, avg_cost, old_date, min_level, shelf_code, sh_title)`
* `Sales(item_num, store_id, shelf_code, invoice_num, s_year, s_month, s_day, quantity, price, avg_cost, note)`
* `Purchase(pur_num, sup_id, complete_date, item_num, quantity, unit_cost, note)`
* `Supplier(sup_id, address, zip_code, phone)`
* `Account_Payable(sup_id, balance)`
* `Purchase_Payment_Record(pur_num, complete_date, sup_id, amount, trans_type)`
  * trans_type: credit, debit

**Shared Entities:**
* Person, Skill, Position, Job (adapted for store operations)

**Key Relationships:**

* `Stocking(item_num, pur_num, complete_date)`
  * Links inventory to purchase orders
* `Requires(pos_code, sk_code)`
  * Position skill requirements
* `Addi_Requires(job_code, sk_code)`
  * Job-specific additional skill requirements
* `Has_Skill(per_id, sk_code)`
  * Employee skills

### 3. GV Database (Manufacturing)

**Purpose**: Multi-factory manufacturing operations with material management, production tracking, and contract fulfillment

**Core Entities:**

* `Factory(fac_id, fac_name, address, zip_code, phone, manager)`
* `Material(m_code, m_name, quantity, unit, min_level)`
* `Product(p_code, p_name, description, quantity, unit, avg_cost)`
* `Contract(contract_id, cus_id, date, sale_amount, pay_schedule)`
* `LineItem(contract_id, p_code, quantity)`
* `Purchase(pur_num, sup_id, complete_date, item_num, quantity, unit_cost, note)`

**Shared Entities:**
* Person, Skill, Position, Job (adapted for factory operations)

**Key Relationships:**

* `Makes(fac_id, p_code, quantity)`
  * Factory production capabilities
* `Requires(pos_code, sk_code)`
  * Position skill requirements
* `Has_Skill(per_id, sk_code)`
  * Employee skills

## Key Features

### 27 Complex SQL Queries

The project implements comprehensive queries across all three databases:

**Basic Operations (Queries 1-8):**
1. List workers alphabetically by last name
2. List salary workers in descending order
3. Calculate average annual pay per store/factory
4. Display required skills for positions
5. List person's skills in readable format
6. Identify skill gaps for specific positions
7. Analyze sales by time period (AZ)
8. Calculate biggest profit items (AZ)

**Business Analytics (Queries 9-12):**<br>
9. Inventory below minimum levels (AZ)<br>
10. Customer sales totals (GV)<br>
11. Most purchased materials by quarter (GV)<br>
12. Top producing factory for best-selling product (GV)<br>

**Workforce Management (Queries 13-18):**<br>
13. Complete employment history for person<br>
14. Crisis response - find all workers who held specific positions<br>
15. Find unemployed workers with specific experience<br>
16. Industry salary statistics (avg, max, min)<br>
17. Identify biggest employers, industries, and industry groups<br>
18. Job distribution across industries

**Advanced Matching (Queries 19-25):**<br>
19. Course recommendations for skill gaps<br>
20. Best-paying positions based on skills<br>
21. List qualified candidates for positions<br>
22. "Missing-k" analysis (candidates missing k < 4 skills)<br>
23. Find best candidates for new positions<br>
24. Analyze missing skills frequency<br>
25. Track earning increases by industry group<br>

**Strategic Optimization (Queries 26-27):**<br>
26. Positions with most openings due to skill shortages<br>
27. Course sets addressing high-demand skill gaps<br>

### JDBC Applications

**Implemented Business Processes:**

**a) AZ Employee Hiring Process:**
1. Fetch worker information from LD database
2. Upload and validate transcripts
3. Derive skills from completed courses
4. Verify skill requirements match
5. Generate training plan for skill gaps

**b) GV Worker Transfer:**
* Inter-factory employee transfers
* Update job assignments
* Maintain referential integrity

**c) Complex Business Transaction:**
* Multi-table transaction with rollback
* Exchange Market (EM) deal: $100M sales, $75M purchases
* Atomic operation ensuring both contract and purchase complete

**d) Executive Exchange:**
* Coordinated CIO swap between AZ and GV
* Transaction-based approach with rollback capability
* Ensures both transfers succeed or neither occurs

**e) Inter-Database Synchronization:**
* Push/pull methods for job information collection
* LD collects data from AZ and GV
* Handles concurrent operations and data consistency

### Data Population

**Populate_tables.java** generates realistic test data:

* **5 People**: Varied names, addresses, emails, genders
* **20 Skills**: 
  * Programming: Java, C, C++, C#, Python, Ruby, Perl
  * Web: JavaScript, jQuery, XML, .NET, Visual Basic
  * Systems: Microsoft Windows, Network Configuration
  * Security: Penetration Testing, Encryption, Forensics
  * Specialized: Mobile Development, Bioinformatics
* **6 Stores/Factories**: Distributed across multiple states
* **10 Job Positions**: 
  * Software Support, Software Developer, PC Technician
  * JavaScript Engineer, Web Strategy Director
  * Cyber Security Specialist, IT Help Desk
  * Medical Data Entry, Systems Librarian, Program Manager
* **10 Active Jobs**: Varied employment modes and pay types
* **Inventory/Sales Data**: Auto parts with realistic pricing
* **Randomized Relationships**: Skills per person (1-10), skills per position (1-7)

## Requirements

### Software Dependencies

```
Java JDK 8 or higher
PostgreSQL 9.6+ or MySQL 5.7+
JDBC Driver (PostgreSQL or MySQL)
```

### Hardware Recommendations

```
Minimum 4GB RAM
500MB disk space for databases
Multi-core processor recommended for complex queries
```


## Usage Examples

### Query Execution Examples

**Example 1: List workers alphabetically (Query 1)**

```sql
SELECT per_id, last_name, first_name
FROM person
ORDER BY last_name ASC, first_name ASC;
```

**Example 2: Find skill gaps (Query 6)**

```sql
-- Skills required for position but not possessed by person
SELECT s.sk_code, s.sk_title, s.sk_level
FROM skill s
WHERE s.sk_code IN (
    SELECT r.sk_code
    FROM requires r
    WHERE r.pos_code = '15'
)
AND s.sk_code NOT IN (
    SELECT h.sk_code
    FROM has_skill h
    WHERE h.per_id = '7'
)
ORDER BY s.sk_title;
```

**Example 3: Inventory alerts (Query 9)**

```sql
SELECT item_num, title, quantity, min_level
FROM inventory
WHERE CAST(quantity AS INTEGER) < CAST(min_level AS INTEGER)
ORDER BY (CAST(min_level AS INTEGER) - CAST(quantity AS INTEGER)) DESC;
```

**Example 4: Top-paying qualified positions (Query 20)**

```sql
SELECT pos.pos_code, pos.pos_title, pos.pay_range_high
FROM position pos
WHERE NOT EXISTS (
    SELECT r.sk_code
    FROM requires r
    WHERE r.pos_code = pos.pos_code
    AND r.sk_code NOT IN (
        SELECT h.sk_code
        FROM has_skill h
        WHERE h.per_id = '7'
    )
)
ORDER BY pos.pay_range_high DESC
LIMIT 1;
```

### Java Application Examples

**Example: Execute Query with JDBC**

```java
public static void executeQuery(Connection conn, String query) {
    try {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnCount = rsmd.getColumnCount();
        
        // Print column headers
        for (int i = 1; i <= columnCount; i++) {
            System.out.printf("%-20s", rsmd.getColumnName(i));
        }
        System.out.println();
        
        // Print results
        while (rs.next()) {
            for (int i = 1; i <= columnCount; i++) {
                System.out.printf("%-20s", rs.getString(i));
            }
            System.out.println();
        }
        
        rs.close();
        stmt.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
```

**Example: Transaction with Rollback**

```java
public static void executeTransaction(Connection conn) {
    try {
        conn.setAutoCommit(false);
        
        Statement stmt = conn.createStatement();
        
        // Execute multiple related operations
        stmt.executeUpdate("INSERT INTO contract VALUES (...)");
        stmt.executeUpdate("INSERT INTO purchase VALUES (...)");
        
        // Commit if all succeed
        conn.commit();
        System.out.println("Transaction completed successfully");
        
    } catch (SQLException e) {
        try {
            conn.rollback();
            System.out.println("Transaction rolled back");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    } finally {
        try {
            conn.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
```

## Database Design Highlights

### Normalization

All schemas are designed in **3rd Normal Form (3NF)**:

* **1NF**: All attributes contain atomic values
* **2NF**: No partial dependencies on composite keys
* **3NF**: No transitive dependencies

**Key Functional Dependencies:**

**LD Database:**
```
per_id → last_name, first_name, address, zip_code, email, gender
sk_code → sk_title, sk_description, sk_level
pos_code → pos_title, description, pay_range_high, pay_range_low
job_code → j_title, comp_id, pos_code, emp_mode, pay_rate, pay_type, cate_code
comp_id → industry_group, address, zip_code, website
c_code → c_title, c_level, description, status, retail_price
```

**AZ Database:**
```
store_id → address, zip_code, phone
item_num → title, description, quantity, unit, avg_cost, min_level, shelf_code
sup_id → address, zip_code, phone, balance
pur_num, complete_date → sup_id, item_num, quantity, unit_cost
```

**GV Database:**
```
fac_id → fac_name, address, zip_code, phone, manager
m_code → m_name, quantity, unit, min_level
p_code → p_name, description, quantity, unit, avg_cost
contract_id → cus_id, date, sale_amount, pay_schedule
```

### Design Decisions

**1. Employment History Tracking (LD only):**
* `Works` relationship includes start and end dates
* AZ and GV only track current employment
* Enables career progression analysis in LD

**2. Skill Level Granularity:**
* Three-tier system: beginner, intermediate, advanced
* Allows precise matching and training recommendations
* Supports progression tracking

**3. Flexible Job Requirements:**
* Position-level requirements (`Requires`)
* Job-specific additional requirements (`Addi_Requires` in AZ)
* Accommodates varying job complexities

**4. Industry Classification:**
* GICS hierarchy implementation
* Supports sector, industry group, industry, sub-industry
* Enables industry-wide analytics

**5. Date Storage:**
* Separate year, month, day columns
* Simplifies date range queries
* Avoids date format inconsistencies

**6. Pay Type Flexibility:**
* Supports both wage (hourly) and salary (annual)
* 1920 hours standard for annual calculations (40 hrs/week × 48 weeks)
* Enables consistent compensation comparisons

## Query Complexity Analysis

### Simple Queries (2 points each)
* Basic SELECT with ORDER BY
* Single table filtering
* Straightforward aggregations

### Intermediate Queries (3 points each)
* Multi-table JOINs
* Subqueries
* GROUP BY with HAVING
* Date range filtering

### Advanced Queries (5 points each)
* Complex subqueries (nested, correlated)
* Set operations (UNION, INTERSECT, EXCEPT)
* Advanced aggregations
* Skill gap analysis with multiple levels

### Expert Queries (7 points each)
* Multi-level optimization problems
* Recursive queries (prerequisite chains)
* Complex set theory operations
* Multiple database coordination

## Testing Strategy

### Unit Testing

**Test each query with known data:**

```sql
-- Test Query 1: Workers alphabetically
-- Expected: 5 workers ordered by last name
-- Brown, Jones, Johnson, Smith, Williams

-- Test Query 6: Skill gaps
-- Given: Person 7, Position 15
-- Expected: Skills in position but not in person's skill set
```

### Integration Testing

**Test business processes:**

* Employee hiring workflow (all 5 steps)
* Worker transfer with foreign key updates
* Transaction rollback scenarios
* Multi-database synchronization

## Known Limitations

* No authentication/authorization system
* Single-user application (no concurrency control beyond transactions)
* Limited to three databases (LD, AZ, GV)
* No web interface (command-line only)
* Manual data entry required for initial population
* No automated backup/recovery system

## Future Enhancements

* [ ] Implement web-based UI with REST API
* [ ] Add user authentication and role-based access control
* [ ] Add data visualization dashboard
* [ ] Implement stored procedures for complex operations
* [ ] Add machine learning for job matching recommendations
* [ ] Integrate with real job posting APIs (Indeed, Monster)
* [ ] Implement automated data synchronization between databases
* [ ] Add comprehensive logging and audit trails
* [ ] Create mobile application interface

## Educational Objectives

This project demonstrates mastery of:

* **Database Design**: ER modeling, normalization to 3NF, schema design
* **SQL Programming**: Complex queries, joins, subqueries, aggregations, set operations
* **JDBC Programming**: Database connectivity, prepared statements, transaction management, result set processing
* **Business Logic**: Translating real-world requirements into database operations
* **Data Integrity**: Foreign keys, constraints, cascading operations
* **Performance**: Indexing strategies, query optimization
* **Testing**: Unit testing, integration testing, data validation

## Troubleshooting

### Common Issues

**Issue: Connection refused**
```
Solution: Verify database is running and connection parameters are correct
Check: lsof -i :5432 (PostgreSQL) or netstat -an | grep 3306 (MySQL)
```

**Issue: Foreign key constraint violations**
```
Solution: Ensure parent records exist before inserting child records
Check insertion order in Populate_tables.java
```

**Issue: Query returns no results**
```
Solution: Verify test data exists in tables
Run: SELECT COUNT(*) FROM table_name; for each table
```

**Issue: Transaction deadlock**
```
Solution: Review transaction order, minimize transaction duration
Consider using explicit locking if necessary
```

## Contributing

This is an academic project completed as part of a Database Systems course. For questions or clarifications, please contact the lead team member.

## License

Academic project for educational purposes. All code and documentation are for learning and demonstration purposes only.

## Acknowledgments

* Database Systems course instructor and teaching assistants
* U.S. Bureau of Labor Statistics for employment projection data
* MSCI for Global Industry Classification Standard (GICS)
* Online job platforms (NOLA.com, Monster, Indeed) for real-world job data examples
* PostgreSQL and MySQL communities for excellent documentation
