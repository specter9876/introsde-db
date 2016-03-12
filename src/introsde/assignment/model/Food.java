package introsde.assignment.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PersistenceException;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import introsde.assignment.dao.LifeCoachDao;

@Entity
@Table(name = "Food")
@NamedQueries({
		@NamedQuery(name = "Food.findAll", query = "SELECT food FROM Food food"),
		@NamedQuery(name = "Food.findByType", query = "SELECT food FROM Food food where food.type = :type"),
		@NamedQuery(name = "Food.findByCaloriesBound", query = "SELECT food FROM Food food where food.calories <= :calories") })

@XmlRootElement
public class Food implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "sqlite_food")
	@TableGenerator(name = "sqlite_food", table = "sqlite_sequence", pkColumnName = "name", valueColumnName = "seq", pkColumnValue = "Food")
	private long idFood;
	private String description;
	private String type;
	private double calories;
    private String name;
    

	public Food() {
	}

	public long getIdFood() {
		return idFood;
	}

	public void setIdFood(long idFood) {
		this.idFood = idFood;
	}
    
    public String getName() {
		return name;
	}
    
	public void setName(String name) {
		this.name = name;
    }
    
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getCalories() {
		return calories;
	}

	public void setCalories(double calories) {
		this.calories = calories;
	}

	//DB operation

	public static Food create(Food f) throws PersistenceException {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(f);
		tx.commit();
		LifeCoachDao.instance.closeConnections(em);

		return f;
	}

	public static Food getByIdFood(long idFood) throws PersistenceException {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		Food f = em.find(Food.class, idFood);
		LifeCoachDao.instance.closeConnections(em);

		return f;
	}

	public static List<Food> getAll() throws PersistenceException {
        //System.out.println("food flag1");
		EntityManager em = LifeCoachDao.instance.createEntityManager();
        //System.out.println("food flag2");
		List<Food> list = em.createNamedQuery("Food.findAll", Food.class)
				.getResultList();
        //System.out.println("food flag3");
		LifeCoachDao.instance.closeConnections(em);

		return list;
	}

	public static List<Food> getByType(String type) throws PersistenceException {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		List<Food> result = em.createNamedQuery("Food.findByType", Food.class)
				.setParameter("type", type).getResultList();
		LifeCoachDao.instance.closeConnections(em);

		return result;
	}

	

	public static List<Food> getByCaloriesBound(double calories)
			throws PersistenceException {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		List<Food> result = em
				.createNamedQuery("Food.findByCaloriesBound", Food.class)
				.setParameter("calories", calories).getResultList();
		LifeCoachDao.instance.closeConnections(em);

		return result;
	}

	public static Food update(Food f) throws PersistenceException {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		f = em.merge(f);
		tx.commit();
		LifeCoachDao.instance.closeConnections(em);

		return f;
	}

	public static void remove(Food f) throws PersistenceException {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		f = em.merge(f);
		em.remove(f);
		tx.commit();
		LifeCoachDao.instance.closeConnections(em);
	}
	
	
	@Override
	public String toString() {
		return "Food (" + getIdFood() +" name= " + getName() + ", Description=" + getDescription() + ", type=" + getType() + ", calories=" + getCalories() + ")";
	}
}
