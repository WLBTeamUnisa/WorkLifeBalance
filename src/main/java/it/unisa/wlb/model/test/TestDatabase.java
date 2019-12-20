package it.unisa.wlb.model.test;

import java.util.Random;
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
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    String x="0123456789";
    Random random=new Random();
    String codice_prenotazione="";
    
    for(int i=0; i<20; i++)
    codice_prenotazione+=x.charAt(random.nextInt(10));
    
    //faccio una query ---------> SELECT codice FROM SmartWorkingPrenotation WHERE codice=codice_prenotazione
    
    //se la query restituisce un risultato, allora rifaccio i passaggi sopra
    
    //altrimenti inserisco nel database la prenotazione
    
    
    
    
    
    
    
    
    
    
    
    
    
  }

}
