/**
 * @author ${Josiah Anderson} - janderson78@dmacc.edu
 * CIS175 - Fall 2021
 * ${2/2/2022}
 */
package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import model.guns;

public class dbHelper {

static EntityManagerFactory entManF = Persistence.createEntityManagerFactory("week3AssesmentAnderson");
	
	public void enterGun(guns gun) {
		EntityManager entM = entManF.createEntityManager();
		entM.getTransaction().begin();
		entM.persist(gun);
		entM.getTransaction().commit();
		entM.close();
		
	}
	
	public void deleteGun(guns delete) {
		
		EntityManager entM = entManF.createEntityManager();
		entM.getTransaction().begin();
		TypedQuery<guns>typedQuery = entM.createQuery("select gun from guns gun where gun.company = :selectCompany and gun.type = :selectType", guns.class);
		typedQuery.setParameter("selectCompany", delete.getCompany());
		typedQuery.setParameter("selectType", delete.getType());
		typedQuery.setMaxResults(1);
		guns res = typedQuery.getSingleResult();
		
		entM.remove(res);
		entM.getTransaction().commit();
		entM.close();
	}
	
	public List<guns> showGuns(){
		EntityManager entM = entManF.createEntityManager();
		@SuppressWarnings("unchecked")
		List<guns> items = entM.createQuery("SELECT i FROM guns i").getResultList();
		return items;
	}
	

	public void editGun(guns edit) {
		
		EntityManager entM = entManF.createEntityManager();
		entM.getTransaction().begin();
		entM.merge(edit);
		entM.getTransaction().commit();
		entM.close();
	}

	public List<guns> searchByCompany(String company) {
		
		EntityManager entM = entManF.createEntityManager();
		entM.getTransaction().begin();
		TypedQuery<guns>typedQuery = entM.createQuery("select gun from guns gun where gun.company = :selectCompany", guns.class);
		typedQuery.setParameter("selectCompany", company);
		
		List<guns>found = typedQuery.getResultList();
		entM.close();
		
		return found;
	}
	
	public guns searchId(int edit) {
		// Search id of guns
		EntityManager entM = entManF.createEntityManager();
		entM.getTransaction().begin();
		guns found = entM.find(guns.class, edit);
		entM.close();
		return found;
	}

	
	public void cleaner() {
		entManF.close();
	}
	
}
