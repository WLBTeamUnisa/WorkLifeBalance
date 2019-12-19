package it.unisa.wlb.model.jpa;

import it.unisa.wlb.model.bean.Admin;

public class TestAdmin {

  public static void main(String[] args) 
  {
    Admin admin=new Admin();
    admin.setEmail("tiziocaio@wlb.it");
    admin.setName("tizio");
    admin.setPassword("VinnyStaSempASott");
    admin.setSurname("caio");
    AdminJpa jpa=new AdminJpa();
    Admin a=jpa.create(admin);
    System.out.println(jpa.retrieveAll());
    
  }

}
