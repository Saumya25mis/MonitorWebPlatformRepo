/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boha.monitor.util;

import com.boha.monitor.data.Beneficiary;
import com.boha.monitor.data.Company;
import com.boha.monitor.data.Project;
import com.boha.monitor.data.ProjectSite;
import com.boha.monitor.data.ProjectSiteTask;
import com.boha.monitor.data.ProjectSiteTaskStatus;
import com.boha.monitor.data.Task;
import com.boha.monitor.data.TaskStatus;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.joda.time.DateTime;

/**
 *
 * @author aubreyM
 */
//@Stateless
//@TransactionManagement(TransactionManagementType.CONTAINER)
public class Generator {

    @PersistenceContext
    EntityManager em;

    public void generate(Integer companyID) {
        Company c = em.find(Company.class, companyID);
        generateClients(c);
    }

    private void generateClients(Company c) {
        double latitude = 0, longitude = 0;
        latitude = -26.1450825;
        longitude = 28.1527777;

        generateProjects(c, latitude, longitude);
    }

    private void generateProjects(Company c,
            double latitude, double longitude) {

        try {
            Project p = new Project();
            p.setCompany(c);
            p.setDateRegistered(new Date());
            p.setDescription("Detailed description of project. Includes location, project sites and type of project");
            p.setProjectName("RDP Housing Project " + random.nextInt(1000));
            em.persist(p);
            em.flush();
            generateProjectSites(c, p, latitude, longitude);
            System.out.println("----------------------------------- Project has been generated OK: " + p.getProjectName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        //
        try {
            Project p2 = new Project();
            p2.setCompany(c);
            p2.setDateRegistered(new Date());
            p2.setDescription("Detailed description of project. Includes location, project sites and type of project");
            p2.setProjectName("RDP Housing Project " + random.nextInt(1000));
            em.persist(p2);
            em.flush();
            generateProjectSites(c, p2, latitude, longitude);
            System.out.println("Project has been generated OK: " + p2.getProjectName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        //
        try {
            Project p3 = new Project();
            p3.setCompany(c);
            p3.setDateRegistered(new Date());
            p3.setDescription("Detailed description of project. Includes location, project sites and type of project");
            p3.setProjectName("RDP Housing Project " + random.nextInt(1000));
            em.persist(p3);
            em.flush();
            generateProjectSites(c, p3, latitude, longitude);
            System.out.println("Project has been generated OK: " + p3.getProjectName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        //
        try {
            Project p4 = new Project();
            p4.setCompany(c);
            p4.setDateRegistered(new Date());
            p4.setDescription("Detailed description of project. Includes location, project sites and type of project");
            p4.setProjectName("RDP Housing Project " + random.nextInt(1000));
            em.persist(p4);
            em.flush();
            generateProjectSites(c, p4, latitude, longitude);
            System.out.println("Project has been generated OK: " + p4.getProjectName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        //

    }

    private void generateProjectSites(Company c, Project p,
            double latitude, double longitude) {
        int count = random.nextInt(100);
        if (count < 50) {
            count = 50;
        }
        for (int i = 0; i < count; i++) {
            ProjectSite site = new ProjectSite();
            site.setProject(p);
            site.setStandErfNumber("Stand " + getIDNumber());
            Beneficiary b = new Beneficiary();
            b.setCompany(c);
            b.setProject(p);
            int xx = random.nextInt(10);

            if (xx > 4) {
                b.setFirstName(firstNames[random.nextInt(girls.length - 1)]);
            } else {
                b.setFirstName(firstNames[random.nextInt(firstNames.length - 1)]);
            }

            b.setLastName(lastNames[random.nextInt(lastNames.length - 1)]);
            b.setAmountAuthorized(100000.00);
            b.setAmountPaid(0.00);
            b.setiDNumber(getIDNumber());
            em.persist(b);
            em.flush();
            //
            site.setBeneficiary(b);
            Location loc = getRandomPoint(latitude, longitude);
            site.setLatitude(loc.latitude);
            site.setLongitude(loc.longitude);
            site.setAccuracy(1000f);
            site.setProjectSiteName(getSiteName(c, p));
            em.persist(site);
            em.flush();
            System.out.println("Site generated: " + site.getProjectSiteName()
                    + " bennie: " + b.getFirstName() + " " + b.getLastName());
            generateProjectSiteTasks(site);

        }
    }

    Random random = new Random(System.currentTimeMillis());

    private String getIDNumber() {
        StringBuilder sb = new StringBuilder();
        int year = random.nextInt(99);
        if (year < 80) {
            year = 80;
        }
        int mth = random.nextInt(12);
        if (mth < 1) {
            mth = 1;
        }
        int day = random.nextInt(28);
        if (day == 0) {
            day = 1;
        }

        sb.append(year);
        if (mth < 10) {
            sb.append("0").append(mth);
        } else {
            sb.append(mth);
        }
        if (day < 10) {
            sb.append("0").append(day);
        } else {
            sb.append(day);
        }

        int ss = random.nextInt(99);
        if (ss < 10) {
            sb.append("0").append(ss);
        } else {
            sb.append(ss);
        }
        int s2 = random.nextInt(99);
        if (s2 < 10) {
            sb.append("0").append(s2);
        } else {
            sb.append(s2);
        }
        int s3 = random.nextInt(99);
        if (s3 < 10) {
            sb.append("0").append(s3);
        } else {
            sb.append(s3);
        }

        return sb.toString();
    }

    private String getSiteName(Company c, Project p) {
        StringBuilder sb = new StringBuilder();
        sb.append(c.getCompanyID()).append(p.getProjectID());
        sb.append(random.nextInt(99));
        sb.append(random.nextInt(99));
        sb.append(random.nextInt(99));
        sb.append(random.nextInt(99));

        return sb.toString();
    }

    private void generateProjectSiteTasks(ProjectSite site) {
        Query q = em.createNamedQuery("Task.findByCompany", Task.class);
        q.setParameter("companyID", site.getProject().getCompany().getCompanyID());
        List<Task> tasks = q.getResultList();
        for (Task task : tasks) {
            ProjectSiteTask ss = new ProjectSiteTask();
            ss.setDateRegistered(new Date());
            ss.setProjectSite(site);
            ss.setTask(task);
            em.persist(ss);
            em.flush();
            System.out.println("***** site task added: " + task.getTaskName());
        }
    }
    public static final double COORD_PER_KM = 9500 / 1E6;

    private Location getRandomPoint(double lat, double lon) {

        int dn = random.nextInt(10);
        if (dn < 2) {
            dn = random.nextInt(10);
        }
        int de = random.nextInt(10);
        if (de < 2) {
            de = random.nextInt(10);
        }

        double dLat = dn * COORD_PER_KM;
        double dLon = de * COORD_PER_KM;

        int x = random.nextInt(100);
        if (x > 50) {
            lat = lat + dLat;
            lon = lon + dLon;
        } else {
            lat = lat - dLat;
            lon = lon - dLon;
        }
        if (x < 20) {
            lat = lat + dLat;
            lon = lon - dLon;
        }

        return new Location(lat, lon);
    }

    public void updateLocationForTestSites(Integer companyID) {
        Query q = em.createNamedQuery("ProjectSite.findByCompany", ProjectSite.class);
        q.setParameter("companyID", companyID);
        List<ProjectSite> list = q.getResultList();
        for (ProjectSite site : list) {
            if (site.getLatitude() != null) {
                Location loc = getRandomPoint(site.getLatitude(), site.getLongitude());
                site.setLatitude(loc.latitude);
                site.setLongitude(loc.longitude);
                em.merge(site);
                System.out.println("+++ Random update, latitude: " + site.getLatitude() + " longitude: " + site.getLongitude());
            }
        }
        em.flush();
    }

    public void generateStatus(Integer companyID) {
        Query q = em.createNamedQuery("ProjectSiteTask.findByCompany", ProjectSiteTask.class);
        q.setParameter("companyID", companyID);
        List<ProjectSiteTask> list = q.getResultList();
        for (ProjectSiteTask task : list) {
            generateTaskStatus(companyID, task);
        }
        em.flush();
    }

    private void generateTaskStatus(Integer companyID, ProjectSiteTask task) {

        List<Date> list = getDates();
        for (Date date : list) {
            ProjectSiteTaskStatus s = new ProjectSiteTaskStatus();
            s.setDateUpdated(new Date());
            s.setProjectSiteTask(task);
            s.setStatusDate(date);
            s.setTaskStatus(getRandomTaskStatus(companyID));
            em.persist(s);

        }

    }

    private List<Date> getDates() {
        DateTime dt = DateTime.now();
        List<Date> list = new ArrayList<>();
        int count = random.nextInt(14);
        if (count == 0) {
            count = 7;
        }
        for (int i = 0; i < count; i++) {
            DateTime s = dt.minusDays(i + 1);
            list.add(s.toDate());
        }
        System.out.println("###### generated dates: " + list.size());
        return list;
    }

    private TaskStatus getRandomTaskStatus(Integer companyID) {
        if (taskStatusList == null) {
            Query q = em.createNamedQuery("TaskStatus.findByCompany", TaskStatus.class);
            q.setParameter("companyID", companyID);
            taskStatusList = q.getResultList();
            System.out.println("TaskStatus found: " + taskStatusList.size());
        }

        try {
            int index = random.nextInt(taskStatusList.size() - 1);
            if (index == 0) {
                index = 1;
            }

            return taskStatusList.get(index);
        } catch (Exception e) {
            return taskStatusList.get(1);
        }

    }
    List<TaskStatus> taskStatusList;

    private class Location {

        double latitude, longitude;

        public Location(double lat, double lon) {
            latitude = lat;
            longitude = lon;
        }
    }
    public String[] firstNames = {
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
    public String[] lastNames = {
        "Molefe", "Maringa", "Sikhakhane", "Hanayani", "Moerane", "Sithole",
        "Botha", "Khuzwayo", "Ntini", "Mayeza", "Nkosi", "Chauke", "Baloyi",
        "Black", "Qubeka", "Maisela", "Kgongoane", "Machaka", "Shikwambane", "Mosheou",
        "Maseko", "Montwedi", "Sibiya", "Sono", "Samuels", "Gondwe", "Maluleke", "Mathebula",
        "Hlungwane", "Tsotetsi", "Marule", "Moreledi", "Ngwenya", "Chukudu", "Mbhazima", "Shilowa",
        "Mathe", "Mofua", "Mosiane", "Lwembula", "Kuse", "Dube", "Bala", "Ramakatsa", "Mantsoe",
        "Molantoa", "Gonyogo", "Nete", "Kalane", "Makitane", "Khampepe", "Mbele", "Nthako",
        "Nyapoza", "Mlambo", "Ranchebe", "Maketekete", "Phahlane", "Mothekhe", "Tolo", "Moholoholo",
        "Fuzile"

    };

    public String[] girls = {
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
