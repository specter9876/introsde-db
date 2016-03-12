package introsde.assignment.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Cacheable;
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
@Cacheable(false)
@Table(name = "Activity")
@NamedQueries({//devo disambiguare per isMyACtivity
		@NamedQuery(name = "Activity.findAll", query = "SELECT a FROM Activity a ORDER BY a.idActivity"),
		@NamedQuery(name = "Activity.findByType", query = "SELECT a FROM Activity a where a.type = :type "),
        @NamedQuery(name = "Activity.findByName", query = "SELECT a FROM Activity a where a.name = :name "),
        @NamedQuery(name = "Activity.findByCalories", query = "SELECT activity FROM Activity activity where activity.calories >= :calories")})//and a.isMyActivity=: isMyACtivity

@XmlRootElement
public class Activity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "sqlite_activity")
	@TableGenerator(name = "sqlite_activity", table = "sqlite_sequence", pkColumnName = "name", valueColumnName = "seq", pkColumnValue = "Activity")
	private long idActivity;
    
	private String description;
	private String name;
	private String type;
	private double calories;
    private double duration;
    private int isMyActivity;
    

    public Activity() {
	}

	public long getIdActivity() {
		return idActivity;
	}

	public void setIdActivity(long idActivity) {
		this.idActivity = idActivity;
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

    public double getDuration() {
		return duration;
	}
    
	public void setDuration(double duration) {
		this.duration = duration;
	}
    public int getIsMyActivity() {
		return isMyActivity;
	}
    
	public void setIsMyActivity(int isMyActivity) {
		this.isMyActivity = isMyActivity;
	}

	

	public static Activity create(Activity a) throws PersistenceException {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(a);
		tx.commit();
		LifeCoachDao.instance.closeConnections(em);
		return a;
	}

	public static Activity getById(long activityId) throws PersistenceException {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		Activity a = em.find(Activity.class, activityId);
		LifeCoachDao.instance.closeConnections(em);
        //non va mi sa
		return a;
	}
    
    public static List<Activity> getByName(String name) throws PersistenceException {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		List<Activity> result = em
        .createNamedQuery("Activity.findByName", Activity.class)
        .setParameter("name", name).getResultList();
		LifeCoachDao.instance.closeConnections(em);
        return result;
	}

	public static List<Activity> getAll() throws PersistenceException {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		List<Activity> list = em.createNamedQuery("Activity.findAll",
				Activity.class).getResultList();
		LifeCoachDao.instance.closeConnections(em);

		return list;
	}

	public static List<Activity> getByType(String type)
			throws PersistenceException {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		List<Activity> result = em
				.createNamedQuery("Activity.findByType", Activity.class)
				.setParameter("type", type).getResultList();
		LifeCoachDao.instance.closeConnections(em);

		return result;
	}

	
	public static Activity update(Activity a) throws PersistenceException {
		EntityManager em =LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		a = em.merge(a);
		tx.commit();
		LifeCoachDao.instance.closeConnections(em);

		return a;
	}

	public static void remove(Activity a) throws PersistenceException {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		a = em.merge(a);
		em.remove(a);
		tx.commit();
		LifeCoachDao.instance.closeConnections(em);
	}
    
    public static List<Activity> getByCalories(double calories)
    throws PersistenceException {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		List<Activity> result = em
        .createNamedQuery("Activity.findByCalories", Activity.class)
        .setParameter("calories", calories).getResultList();
		LifeCoachDao.instance.closeConnections(em);
        
		return result;
	}
	
	@Override
	public String toString() {
		return ("Activity " + getIdActivity() + ", name=" + getName() +", type=" + getType() + ", calories=" + getCalories()+", description= "+getDescription() + ", duration= "+ getDuration() + "isMyActivity= "+ getIsMyActivity());
	}
}
