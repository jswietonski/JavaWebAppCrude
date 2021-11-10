/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Trwalosc;

import org.hibernate.Session;
import org.hibernate.HibernateException;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Transaction;
/**
 *
 * @author wojciechskumajto
 */
public class MotorQuery {
    private Session session = null;
    private List<Motorcycles> motorList = null;
    private Query q = null;
    String text = null;
    
    public String getMotorList(boolean OrderByName) {
    try {
            org.hibernate.Transaction tx = session.beginTransaction();
            if (OrderByName) {
                q = session.createQuery("from Motorcycles order by Brand"); // ??? znak 
            } else {
                q = session.createQuery("from Motorcycles");
            }
            motorList = (List<Motorcycles>) q.list();
            text = getListaHTML(motorList);
            session.close();
            tx.commit();
        } catch (HibernateException e) {
          }
    return text;
    }     
    
    
    private String getListaHTML(List<Motorcycles> lista) {
        String wiersz;
        wiersz = ("<table><tr>");
        wiersz = wiersz.concat(
        "<td><b>MODEL_ID</b></td>" +
        "<td><b>NAME</b></td>" + 
        "<td><b>BRAND</b></td>" +
        "<td><b>ENGINE_CAPACITY</b></td>" +
        "<td><b>POWER</b></td>" +
        "<td><b>NO_TRACES</b></td>" +
        "<td><b>REQUIRE_DRIVING_LICENSE</b></td>"
        );   
        
        wiersz = wiersz.concat("</tr>");
        for (Motorcycles moto : lista) {
            wiersz = wiersz.concat("<tr>");
            wiersz = wiersz.concat("<td>" + moto.getModelId() + "</td>");
            wiersz = wiersz.concat("<td>" + moto.getName() + "</td>");
            wiersz = wiersz.concat("<td>" + moto.getBrand() + "</td>");
            wiersz = wiersz.concat("<td>" + moto.getEngineCapacity() + "</td>");
            wiersz = wiersz.concat("<td>" + moto.getPower() + "</td>");
            wiersz = wiersz.concat("<td>" + moto.getNoTraces() + "</td>");
            wiersz = wiersz.concat("<td>" + moto.getRequireDrivingLicense() + "</td>");
            wiersz = wiersz.concat("</tr>");
        }
        
        wiersz = wiersz.concat("</table>");
        return wiersz;
    }   
    
    public void insertMotor(Motorcycles motor) {
        Transaction transaction = null;
         try {
            transaction = session.beginTransaction();
            session.save(motor);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    
     public void deleteUser(int id) {

        Transaction transaction = null;
        try {
            
            transaction = session.beginTransaction();
            Motorcycles motor = (Motorcycles) session.get(Motorcycles.class, id);
            if (motor != null) {
                session.delete(motor);
                System.out.println("Motor is deleted");
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
     
     
     public void updateMotor(Motorcycles motor) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(motor);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
   
    
    public MotorQuery() {
        this.session = HibernateUtil.getSessionFactory().getCurrentSession();
    }
    
}
