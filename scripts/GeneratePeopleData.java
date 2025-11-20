import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class GeneratePeopleData {
    
    static String[] lastNames = {
        "Smith", "Johnson", "Williams", "Jones", "Brown", "Davis", "Miller", "Wilson", 
        "Moore", "Taylor", "Anderson", "Thomas", "Jackson", "White", "Harris", "Martin",
        "Thompson", "Garcia", "Martinez", "Robinson", "Clark", "Rodriguez", "Lewis", "Lee",
        "Walker", "Hall", "Allen", "Young", "King", "Wright", "Lopez", "Hill", "Scott",
        "Green", "Adams", "Baker", "Nelson", "Carter", "Mitchell", "Perez", "Roberts",
        "Turner", "Phillips", "Campbell", "Parker", "Evans", "Edwards", "Collins", "Stewart",
        "Sanchez", "Morris", "Rogers", "Reed", "Cook", "Morgan", "Bell", "Murphy", "Bailey",
        "Rivera", "Cooper", "Richardson", "Cox", "Howard", "Ward", "Torres", "Peterson",
        "Gray", "Ramirez", "James", "Watson", "Brooks", "Kelly", "Sanders", "Price",
        "Bennett", "Wood", "Barnes", "Ross", "Henderson", "Coleman", "Jenkins", "Perry",
        "Powell", "Long", "Patterson", "Hughes", "Flores", "Washington", "Butler", "Simmons",
        "Foster", "Gonzales", "Bryant", "Alexander", "Russell", "Griffin", "Diaz", "Hayes"
    };
    
    static String[] firstNames = {
        "Alivia", "Emma", "Olivia", "Ava", "Sophia", "Isabella", "Mia", "Charlotte", "Amelia",
        "Harper", "Evelyn", "Abigail", "Emily", "Elizabeth", "Sofia", "Avery", "Ella", "Scarlett",
        "Grace", "Chloe", "Victoria", "Riley", "Aria", "Lily", "Aubrey", "Zoey", "Penelope",
        "Lillian", "Addison", "Layla", "Natalie", "Camila", "Hannah", "Brooklyn", "Zoe", "Nora",
        "Billie", "Birdee", "Elena", "Stella", "Luna", "Hazel", "Aurora", "Savannah", "Audrey",
        "Noah", "Liam", "Mason", "Jacob", "William", "Ethan", "James", "Alexander", "Michael",
        "Benjamin", "Elijah", "Daniel", "Aiden", "Logan", "Matthew", "Lucas", "Jackson", "David",
        "Oliver", "Jayden", "Joseph", "Gabriel", "Samuel", "Carter", "Anthony", "John", "Dylan",
        "Luke", "Henry", "Andrew", "Isaac", "Christopher", "Joshua", "Wyatt", "Sebastian", "Owen",
        "Aries", "Cage", "Ammar", "Andy", "Kenneth", "Jame", "Leeroy", "Marcus", "Ryan", "Nathan",
        "Caleb", "Nathan", "Ryan", "Jack", "Hunter", "Levi", "Christian", "Julian", "Landon", "Grayson"
    };
    
    static String[] streetNames = {
        "Main", "Oak", "Pine", "Maple", "Cedar", "Elm", "Washington", "Lake", "Hill", "Park",
        "River", "Forest", "Lincoln", "Spring", "Church", "Madison", "First", "Second", "Third",
        "Sunset", "Highland", "Market", "Broadway", "Mill", "Water", "School", "Center", "College",
        "Grove", "Franklin", "Walnut", "Cherry", "Willow", "Birch", "Ash", "Valley", "Ridge",
        "Summit", "Adams", "Jefferson", "Wilson", "Jackson", "State", "Union", "Liberty", "Railroad",
        "Industrial", "Factory", "Steel", "Iron", "Coal", "Metal", "Works", "Plant", "Production"
    };
    
    static String[] streetTypes = {"St", "Ave", "Rd", "Dr", "Ln", "Ct", "Blvd", "Way", "Path", "Circle"};
    
    static String[] cities = {
        "New York", "Los Angeles", "Chicago", "Houston", "Phoenix", "Philadelphia", "San Antonio",
        "San Diego", "Dallas", "San Jose", "Austin", "Jacksonville", "Fort Worth", "Columbus",
        "Indianapolis", "Charlotte", "San Francisco", "Seattle", "Denver", "Washington", "Boston",
        "El Paso", "Nashville", "Detroit", "Oklahoma City", "Portland", "Las Vegas", "Memphis",
        "Louisville", "Baltimore", "Milwaukee", "Albuquerque", "Tucson", "Fresno", "Sacramento",
        "Kansas City", "Long Beach", "Mesa", "Atlanta", "Colorado Springs", "Virginia Beach",
        "Raleigh", "Omaha", "Miami", "Oakland", "Minneapolis", "Tulsa", "Wichita", "New Orleans",
        "Arlington", "Cleveland", "Tampa", "Bakersfield", "Aurora", "Anaheim", "Honolulu",
        "Santa Ana", "Riverside", "Corpus Christi", "Lexington", "Henderson", "Stockton", "Saint Paul",
        "Cincinnati", "St. Louis", "Pittsburgh", "Greensboro", "Lincoln", "Anchorage", "Plano",
        "Orlando", "Irvine", "Newark", "Durham", "Chula Vista", "Toledo", "Fort Wayne", "St. Petersburg",
        "Laredo", "Jersey City", "Chandler", "Madison", "Lubbock", "Scottsdale", "Reno", "Buffalo",
        "Gilbert", "Glendale", "North Las Vegas", "Winston-Salem", "Chesapeake", "Norfolk", "Fremont",
        "Garland", "Irving", "Hialeah", "Richmond", "Boise", "Spokane", "Baton Rouge"
    };
    
    static String[] states = {
        "NY", "CA", "IL", "TX", "AZ", "PA", "FL", "OH", "NC", "MI", "GA", "VA", "WA",
        "MA", "IN", "TN", "MO", "MD", "WI", "MN", "CO", "AL", "SC", "LA", "KY", "OR",
        "OK", "CT", "UT", "IA", "NV", "AR", "MS", "KS", "NM", "NE", "WV", "ID", "HI",
        "NH", "ME", "RI", "MT", "DE", "SD", "ND", "AK", "VT", "WY"
    };
    
    static String[] emailDomains = {
        "gmail.com", "yahoo.com", "outlook.com", "hotmail.com", "aol.com", "icloud.com",
        "mail.com", "protonmail.com", "zoho.com", "retail.com", "store.com", "shop.com",
        "sales.com", "commerce.com", "factory.com", "plant.com", "manufacturing.com",
        "production.com", "industry.com", "techmail.com", "devnet.io", "codebase.org",
        "systech.com", "webdev.net", "datacore.io", "cloudtech.com", "appdev.net",
        "innovation.io", "digital.com", "enterprise.com"
    };
    
    static Random random = new Random();
    
    public static void main(String[] args) {
        try {
            generateLastNames();
            generateFirstNames();
            generateAddresses();
            generateZipCodes();
            generateEmails();
            generateGenders();
            
            System.out.println("Successfully generated data for 300 people!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    static void generateLastNames() throws IOException {
        PrintWriter writer = new PrintWriter(new FileWriter("data/person/last_name.txt"));
        for (int i = 0; i < 300; i++) {
            writer.println(lastNames[random.nextInt(lastNames.length)]);
        }
        writer.close();
    }
    
    static void generateFirstNames() throws IOException {
        PrintWriter writer = new PrintWriter(new FileWriter("data/person/first_name.txt"));
        for (int i = 0; i < 300; i++) {
            writer.println(firstNames[random.nextInt(firstNames.length)]);
        }
        writer.close();
    }
    
    static void generateAddresses() throws IOException {
        PrintWriter writer = new PrintWriter(new FileWriter("data/person/address.txt"));
        for (int i = 0; i < 300; i++) {
            int streetNum = random.nextInt(9999) + 1;
            String streetName = streetNames[random.nextInt(streetNames.length)];
            String streetType = streetTypes[random.nextInt(streetTypes.length)];
            String city = cities[random.nextInt(cities.length)];
            String state = states[random.nextInt(states.length)];
            
            writer.println(streetNum + " " + streetName + " " + streetType + ", " + city + ", " + state);
        }
        writer.close();
    }
    
    static void generateZipCodes() throws IOException {
        PrintWriter writer = new PrintWriter(new FileWriter("data/person/zip_code.txt"));
        for (int i = 0; i < 300; i++) {
            int zipCode = random.nextInt(90000) + 10000; // Generate 5-digit zip codes
            writer.println(zipCode);
        }
        writer.close();
    }
    
    static void generateEmails() throws IOException {
        PrintWriter writer = new PrintWriter(new FileWriter("data/person/email.txt"));
        for (int i = 0; i < 300; i++) {
            String firstName = firstNames[random.nextInt(firstNames.length)].toLowerCase();
            String lastName = lastNames[random.nextInt(lastNames.length)].toLowerCase();
            String domain = emailDomains[random.nextInt(emailDomains.length)];
            int randomNum = random.nextInt(1000);
            
            // Various email formats
            String email;
            int format = random.nextInt(5);
            switch(format) {
                case 0: email = firstName + "." + lastName + "@" + domain; break;
                case 1: email = firstName + lastName + randomNum + "@" + domain; break;
                case 2: email = firstName.charAt(0) + lastName + "@" + domain; break;
                case 3: email = firstName + randomNum + "@" + domain; break;
                default: email = firstName + "." + lastName.charAt(0) + randomNum + "@" + domain;
            }
            
            writer.println(email);
        }
        writer.close();
    }
    
    static void generateGenders() throws IOException {
        PrintWriter writer = new PrintWriter(new FileWriter("data/person/gender.txt"));
        for (int i = 0; i < 300; i++) {
            writer.println(random.nextBoolean() ? "male" : "female");
        }
        writer.close();
    }
}