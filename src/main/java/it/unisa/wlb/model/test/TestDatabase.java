package it.unisa.wlb.model.test;

import java.sql.Date;
import java.util.List;
import java.util.Random;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GenerationType;
import javax.persistence.Persistence;
import javax.persistence.Query;
import it.unisa.wlb.model.bean.Admin;
import it.unisa.wlb.model.bean.Employee;
import it.unisa.wlb.model.bean.Floor;
import it.unisa.wlb.model.bean.Room;
import it.unisa.wlb.model.bean.RoomPK;
import it.unisa.wlb.model.bean.SmartWorkingPrenotation;
import it.unisa.wlb.model.bean.SmartWorkingPrenotationPK;
import it.unisa.wlb.model.bean.Workstation;
import it.unisa.wlb.model.bean.WorkstationPK;
import it.unisa.wlb.model.bean.WorkstationPrenotation;
import it.unisa.wlb.model.bean.WorkstationPrenotationPK;
import it.unisa.wlb.model.jpa.AdminJpa;

public class TestDatabase {

  public static void main(String[] args) {
    EntityManagerFactory factor =
        Persistence.createEntityManagerFactory("WorkLifeBalance");
        EntityManager entitymanager = factor.createEntityManager();
        entitymanager.getTransaction().begin();

    /*AdminJpa a=new AdminJpa();
    Admin admin=new Admin();
    admin.setEmail("porcoddio");
    admin.setName("DIOCANE");
    admin.setPassword("Gigitispezzounamano");
    admin.setSurname("VinnyGay");
    a.create(admin);*/
    AdminJpa a=new AdminJpa();
   List<Admin> lista=a.retrieveAll();
   SmartWorkingPrenotation sw=new SmartWorkingPrenotation();
   sw.setCalendarWeek(17);
   sw.setYear(2019);
   Query q=entitymanager.createQuery("SELECT employee FROM Employee employee WHERE employee.email=?1");
   q.setParameter(1, "a@gmail.com");
   Employee e=(Employee) q.getSingleResult();
   sw.setEmployee(e);
   SmartWorkingPrenotationPK pk=new SmartWorkingPrenotationPK();
   pk.setEmployeeEmail("a@gmail.com");
   sw.setId(pk);
   entitymanager.persist(sw);
   WorkstationPrenotation wp=new WorkstationPrenotation();
   wp.setEmployee(e);
   wp.setCalendarWeek(17);
   wp.setYear(1998);
   WorkstationPrenotationPK wpk=new WorkstationPrenotationPK();
   wpk.setEmailEmployee("a@gmail.com");
   wpk.setPrenotationDate(new Date(20180201110400L));
   wp.setId(wpk);
   q=entitymanager.createNamedQuery("Workstation.findAll");
   List<Workstation> worklist=q.getResultList();
   Workstation workstation=new Workstation();
   for(int i=0; i<worklist.size(); i++)
   {
     WorkstationPK workstationpk=worklist.get(i).getId();
     if(workstationpk.getFloor()==1 && workstationpk.getRoom()==1 && workstationpk.getWorkstation()==4)
     {
       workstation=worklist.get(i);
     }
   }
   wp.setWorkstation(workstation);
   entitymanager.persist(wp);
   entitymanager.getTransaction().commit();
  }

}
