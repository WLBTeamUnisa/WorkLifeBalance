package it.unisa.wlb.jpa;

import static org.junit.jupiter.api.Assertions.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unisa.wlb.model.bean.Admin;
import it.unisa.wlb.model.bean.Employee;
import it.unisa.wlb.model.bean.Project;
import it.unisa.wlb.model.jpa.ProjectJpa;

class ProjectJpaTest {

    private static final EntityManagerFactory factor = Persistence.createEntityManagerFactory("WorkLifeBalance");
    private EntityManager entityManager;
    private ProjectJpa projectJpa;
    private Project project;
    private Project project2;
    private Employee manager;
    private Admin admin;
    private Employee employee;

    @BeforeEach
    void setUp() throws Exception {
        projectJpa = new ProjectJpa();

        admin = new Admin();
        admin.setEmail("v.verdi1@wlbadmin.it");
        admin.setName("Vincenzo");
        admin.setSurname("Verdi");
        admin.setPassword("Ciao1234.");

        manager = new Employee();
        manager.setEmail("m.rossi1@wlb.it");
        manager.setName("Mario");
        manager.setSurname("Rossi");
        manager.setPassword("Ciao1234.");
        manager.setStatus(1);

        project = new Project();
        project.setAdmin(admin);
        project.setName("ProgettoIS");
        project.setScope("Università");
        project.setDescription("Progetto realizzato a scopo universitario");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        project.setStartDate(formatter.parse("2019-11-19"));
        project.setEndDate(formatter.parse("2020-01-15"));
        project.setEmployee(manager);

        project2 = new Project();
        project2.setAdmin(admin);
        project2.setName("ProgettoPOO");
        project2.setScope("Università");
        project2.setDescription("Progetto realizzato a scopo universitario");
        project2.setStartDate(formatter.parse("2018-12-19"));
        project2.setEndDate(formatter.parse("2019-01-16"));
        project2.setEmployee(manager);

        employee = new Employee();
        employee.setEmail("g.gialli1@wlb.it");
        employee.setPassword("Ciao1234.");
        employee.setName("Giovanni");
        employee.setSurname("Gialli");
        employee.setStatus(0);
        ArrayList<Employee> list = new ArrayList<Employee>();
        List<Employee> employeesList = list;
        list.add(employee);
        project.setEmployees(employeesList);
        project2.setEmployees(employeesList);

        try {
            entityManager = factor.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(employee);
            entityManager.persist(admin);
            entityManager.persist(manager);
            entityManager.persist(project);
            entityManager.persist(project2);
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }

    @AfterEach
    void tearDown() throws Exception {
        try {
            entityManager = factor.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.merge(project));
            entityManager.remove(entityManager.merge(project2));
            entityManager.remove(entityManager.merge(manager));
            entityManager.remove(entityManager.merge(employee));
            entityManager.remove(entityManager.merge(admin));
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }

    /**
     * Tests the creation of a project
     * 
     * @throws Exception
     */

    @Test
    void createTest() throws Exception {
        Project aProject = new Project();
        aProject.setAdmin(admin);
        aProject.setName("Research");
        aProject.setScope("Università");
        aProject.setDescription("Progetto realizzato a scopo universitario");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        aProject.setStartDate(formatter.parse("2019-10-20"));
        aProject.setEndDate(formatter.parse("2020-02-10"));
        aProject.setEmployee(manager);

        Project created = projectJpa.create(aProject);
        assertEquals("Research", created.getName());

        try {
            entityManager = factor.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.merge(aProject));
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }

    /**
     * Tests an update done to a project
     */

    @Test
    void updateTest() {
        project.setName("Project");
        Project modifyied = projectJpa.update(project);
        assertEquals("Project", modifyied.getName());
    }

    /**
     * Tests the removal of a project
     * 
     * @throws Exception
     */

    @Test
    void removeTest() throws Exception {
        Project aProject = new Project();
        aProject.setAdmin(admin);
        aProject.setName("Research");
        aProject.setScope("Università");
        aProject.setDescription("Progetto realizzato a scopo universitario");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        aProject.setStartDate(formatter.parse("2019-10-20"));
        aProject.setEndDate(formatter.parse("2020-02-10"));
        aProject.setEmployee(manager);

        try {
            entityManager = factor.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(aProject);
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }

        projectJpa.remove(aProject);
        assertThrows(NoResultException.class, () -> {
            projectJpa.retrieveByName("Research");
        });
    }

    /**
     * Tests the retrieve of all projects
     */

    @Test
    void retrieveAllTest() {
        boolean check1 = false;
        boolean check2 = false;
        List<Project> projectList = projectJpa.retrieveAll();
        for (Project aProject : projectList) {
            if (aProject.getName().equals("ProgettoIS"))
                check1 = true;
            if (aProject.getName().equals("ProgettoPOO"))
                check2 = true;
        }
        assertTrue(check1);
        assertTrue(check2);
    }

    /**
     * Tests the retrieve of all projects by a manager's email
     */

    @Test
    void retrieveByManagerTest() {
        boolean check = true;
        List<Project> projectList = projectJpa.retrieveByManager("m.rossi1@wlb.it");
        for (Project aProject : projectList) {
            if (!aProject.getEmployee().getEmail().equals("m.rossi1@wlb.it")) {
                check = false;
            }
        }
        assertTrue(check);
    }

    /**
     * Tests a research of all projects which name is like to a given name
     */

    @Test
    void searchByNameTest() {
        boolean check = true;
        List<Project> projectList = projectJpa.searchByName("Prog");
        for (Project aProject : projectList) {
            if (!aProject.getName().contains("Prog")) {
                check = false;
            }
        }
        assertTrue(check);
    }

    /**
     * Tests the retrieve of all projects by name
     */

    @Test
    void retrieveByNameTest() {
        Project retrieved = projectJpa.retrieveByName("ProgettoIS");
        assertEquals("ProgettoIS", retrieved.getName());
    }

}
