/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boha.monitor.util;

import com.boha.monitor.data.Beneficiary;
import com.boha.monitor.data.Client;
import com.boha.monitor.data.Company;
import com.boha.monitor.data.Project;
import com.boha.monitor.data.ProjectSite;
import com.boha.monitor.data.ProjectSiteTask;
import com.boha.monitor.data.Task;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author aubreyM
 */
public class Generator {

    public static void generate(EntityManager em, Integer companyID,
            double latitude, double longitude) {
        
        Company c = em.find(Company.class, companyID);
        generateClients(em, c, latitude, longitude);
    }

    private static void generateClients(EntityManager em, Company c,
            double latitude, double longitude) {
            
        Client cl = new Client();
        cl.setCompany(c);
        cl.setClientName("Company Client #1");
        cl.setDateRegistered(new Date());
        cl.setEmail("client1.info@gmail.com");
        cl.setAddress("206 Stanza Bopape Street, Regents Building, Pretoria");
        cl.setPostCode("0087");
        cl.setCellphone("082 355 8273");
        em.persist(cl);
        em.flush();
        generateProjects(em, c, cl, latitude, longitude);
        Client cm = new Client();
        cm.setCompany(c);
        cm.setClientName("Company Client #2");
        cm.setDateRegistered(new Date());
        cm.setEmail("client2.info@gmail.com");
        cm.setAddress("1340 Sehume Street, 10th Floor, Peace Building, Pretoria");
        cm.setPostCode("0087");
        cm.setCellphone("071 987 7765");
        em.persist(cm);
        em.flush();
        generateProjects(em, c, cm, latitude, longitude);
        Client ck = new Client();
        ck.setCompany(c);
        ck.setClientName("Company Client #3");
        ck.setDateRegistered(new Date());
        ck.setEmail("client3.info@gmail.com");
        ck.setAddress("Absa Court, 309 Stanza Bopape Street, Pretoria");
        ck.setPostCode("0034");
        ck.setCellphone("072 886 8000");
        em.persist(ck);
        em.flush();
        generateProjects(em, c, ck, latitude, longitude);
    }

    private static void generateProjects(EntityManager em, 
            Company c, Client client, 
            double latitude, double longitude) {

        Project p = new Project();
        p.setClient(client);
        p.setCompany(c);
        p.setDateRegistered(new Date());
        p.setDescription("Detailed description of project. Includes location, project sites and type of project");
        p.setProjectName("Project Name One");
        em.persist(p);
        em.flush();
        generateProjectSites(em, p, latitude, longitude);
        //
        Project p2 = new Project();
        p2.setClient(client);
        p2.setCompany(c);
        p2.setDateRegistered(new Date());
        p2.setDescription("Detailed description of project. Includes location, project sites and type of project");
        p2.setProjectName("Project Name Two");
        em.persist(p2);
        em.flush();
        generateProjectSites(em, p2, latitude, longitude);
        //
        Project p3 = new Project();
        p3.setClient(client);
        p3.setCompany(c);
        p3.setDateRegistered(new Date());
        p3.setDescription("Detailed description of project. Includes location, project sites and type of project");
        p3.setProjectName("Project Name Three");
        em.persist(p3);
        em.flush();
        generateProjectSites(em, p3, latitude, longitude);
        //
        Project p4 = new Project();
        p4.setClient(client);
        p4.setCompany(c);
        p4.setDateRegistered(new Date());
        p4.setDescription("Detailed description of project. Includes location, project sites and type of project");
        p4.setProjectName("Project Name Four");
        em.persist(p4);
        em.flush();
        generateProjectSites(em, p4, latitude, longitude);
        //

    }

    private static void generateProjectSites(EntityManager em, Project p,
            double latitude, double longitude) {
        int count = random.nextInt(20);
        if (count < 10) {
            count = 10;
        }
        for (int i = 0; i < count; i++) {
            ProjectSite site = new ProjectSite();
            site.setProject(p);
            site.setStandErfNumber("Stand #" + (System.currentTimeMillis() / 1001) + "-" + count);
            Beneficiary b = new Beneficiary();
            b.setFirstName(firstNames[random.nextInt(firstNames.length - 1)]);
            b.setLastName(lastNames[random.nextInt(lastNames.length - 1)]);
            b.setAmountAuthorized(100000.00);
            b.setAmountPaid(0.00);
            b.setiDNumber("" + (System.currentTimeMillis() / 100));
            em.persist(b);
            em.flush();
            site.setBeneficiary(b);
            site.setLatitude(getRandomPoint(latitude));
            site.setLongitude(getRandomPoint(longitude));
            site.setAccuracy(1000f);
            site.setProjectSiteName("Building Site #"+System.currentTimeMillis());
            em.persist(site);
            System.out.println(site.getProjectSiteName() + " generated");
            generateProjectSiteTasks(em, site);

        }
    }

    private static void generateProjectSiteTasks(EntityManager em, ProjectSite site) {
        Query q = em.createNamedQuery("Task.findByCompany", Task.class);
        q.setParameter("companyID", site.getProject().getCompany().getCompanyID());
        List<Task> tasks =q.getResultList();
        for (Task task : tasks) {
            ProjectSiteTask ss = new ProjectSiteTask();
            ss.setDateRegistered(new Date());
            ss.setProjectSite(site);
            ss.setTask(task);
            em.persist(ss);
            em.flush();
        }
    }
   
    private static double getRandomPoint(double seed) {
        double point = 0.00;
        int meters = random.nextInt(10000);
        if (meters < 500) {
            meters = 500 * random.nextInt(20);
        }

        int sw = random.nextInt(100);
        if (sw < 50) {
            point = seed + (meters * 1009);
        } else {
            point = seed - (meters * 1009);
        }

        return point;
    }
    private static Random random = new Random(System.currentTimeMillis());
    public static String[] firstNames = {
        "Benjamin", "Johnny", "Tom", "Sam", "Thomas", "Zeke", "John",
        "Tommy", "Peter", "Paul", "Forrest", "Bennie", "Mark", "MacDonald",
        "McLean", "Chris", "Frank", "Mark", "Ronald", "Ronnie", "Blake",
        "John", "Vincent", "Jack", "Bobby", "Malenga", "Sean", "Shane",
        "Mack", "Jonty", "Lance", "David", "Adam", "Luke",
        "Jean", "Peter", "Francois", "Stephen", "Geoffrey", "Omar", "Andre", "Robert", "Hunter",
        "William", "Harry", "Boyce", "Lee", "Lawrence", "Michael", "Noonan", "Caleb", "Jacob",
        "Samuel", "Andrew", "Bernard", "Jack", "Tommy", "Johannes", "Lance", "Jeremiah", "Jerry",
        "Trayvon", "Newton", "Sam", "Fred", "TJ", "Raymond", "Godfrey", "Charlie", "Mingus",
        "Ryan", "Steve", "Stephen", "Hunter", "Henry", "Jordan", "Andrew",
        "Brandon", "Louis", "Christopher", "Daniel", "Eli", "Liam", "Carter", "Dominic",
        "Parker", "Anthony", "Benjamin", "Lucas", "Connor", "Zachary",
        "Cameron", "Matthew", "Justin", "Nathan", "Sebastian", "Brody",
        "Alexander", "Alex", "Levi", "James", "Aiden", "Cooper", "Xavier", "Ryder",
        "Michael", "Tyler", "Ethan", "Jonathan", "Robert", "Roberto", "Gabriel",
        "Chase", "Logan", "Hudson", "Julian", "Aaron", "Severiano", "Owen"
    };
    public static String[] lastNames = {
        "Armstrong", "Maringa", "Scott", "Oosthuizen", "Els", "Schwartzel",
        "Botha", "Smythe", "Baker", "Watson", "Jobs", "Player", "Locke",
        "Black", "Charles", "Grainger", "Jones", "Brown", "Peterson", "Mickels",
        "Pollack", "Peyton", "Williams", "Zuckerberg", "Samuels", "Hernandez", "Johnson", "Gray",
        "Davidson", "Lombardi", "Smith", "Jackson", "Chauke", "Morris", "Peterson", "Paulson",
        "Remington", "Priest", "Church", "Charles", "Burmingham", "Naidoo", "Bala", "Renoir", "Switzer",
        "Dennison", "Johnson", "Jerram", "Adams", "Wilson", "Hepburn", "Giggs", "Stephens",
        "Dafoe", "Daggett", "Dahlberg", "Dangerfield", "Danziger", "Daniels", "Smith", "Smythe",
        "Calandrino", "Cadwell", "Callaghan", "California", "Villegas", "Camilleri",
        "Hackney", "Hackman", "Hackett", "Haagensen", "Hackworth", "Hacker",
        "Hachmeister", "Hack", "Duff", "Haigwood", "Wood", "Woods", "Mickelson",
        "Taglieri", "Tanaka", "Tailor", "Talarico", "Talbot", "Tafoya", "Tartaglia",
        "Gaffney", "Gagliardi", "Gaillard", "Galaska", "Dufner", "Gambetta",
        "Fabiani", "Factor", "Fahlstrom", "Fagin", "Faldo", "Fariello", "Packwood",
        "Pacino", "Paganelli", "Page", "Pagani", "Palinski", "Rafferty", "Rabinovitz",
        "Radcliffe", "Raindford", "Rainsford"

    };

    public static String[] girls = {
        "Mary", "Louise", "Brenda", "Samantha", "Ivanka", "Petra", "Maria",
        "Sue", "Thabitah", "Henrietta", "Fannie", "Bernande", "Linda", "Catherine",
        "Lee", "Christina", "Denise", "Yvonne", "Isabella", "Mia", "Blake",
        "Alexis", "Sofia", "Claire", "Melanie", "Sarah", "Brianna", "Jasmine",
        "Grace", "Hannah", "Elizabeth", "Natalie", "Allison", "Zoe",
        "Jean", "Julia", "Lucy", "Hailey", "Leah", "Andrea", "Kaylee", "Victoria", "Jocelyn",
        "Brooklyn", "Sophie", "Madison", "Taylor", "Alexandra", "Alexa", "Riley",
        "Aubree", "Naomi", "Kayla", "Michelle", "Bernande", "Arianne",
        "Anna", "Gabriella", "Madeline", "Maggie", "Evelyn", "Lily", "Bella", "Savannah",
        "Kimberley", "Charlotte",
        "Stella", "Leah", "Ella", "Serenity", "Katherine", "Reagan",
        "Godiva", "Caroline", "Alyssa", "Sarah", "Molly", "Morgan",
        "Chloe"
    };
}
