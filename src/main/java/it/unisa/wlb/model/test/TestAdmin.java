package it.unisa.wlb.model.test;

import it.unisa.wlb.model.bean.Admin;
import it.unisa.wlb.model.jpa.AdminJpa;

public class TestAdmin {

  public static void main(String[] args) {
    Admin admin=new Admin();
    admin.setEmail("tiziocaio@wlb.it");
    admin.setName("tizio");
    admin.setPassword("aaaaaaaaaaaa");
    admin.setSurname("caio");
    AdminJpa jpa=new AdminJpa();
    Admin a=jpa.create(admin);
    System.out.println(jpa.retrieveAll());
  }

}
