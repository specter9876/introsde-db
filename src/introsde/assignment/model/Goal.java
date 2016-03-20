package introsde.assignment.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Cacheable;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PersistenceException;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import introsde.assignment.dao.LifeCoachDao;

@Entity
@Cacheable(false)
@Table(name = "Goal")
@NamedQueries({ @NamedQuery(name = "Goal.findAll", query = "SELECT g FROM Goal g"),
	@NamedQuery(name = "Goal.findByIdUser", query = "SELECT g FROM Goal g where g.idUser = :idUser ORDER BY g.idGoal"),
    @NamedQuery(name = "Goal.findById", query = "SELECT g FROM Goal g where g.idGoal = :idGoal"),
	@NamedQuery(name = "Goal.findByIdUserType", query = "SELECT g FROM Goal g where g.idUser = :idUser and g.type = :type ORDER BY g.idGoal "),
	@NamedQuery(name = "Goal.findIsAchievedByIdUser", query = "SELECT g FROM Goal g where g.isAchieved = :isAchieved and g.idUser= :idUser ORDER BY g.idGoal"), })
@XmlRootElement

public class Goal implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "sqlite_goal")
	@TableGenerator(name = "sqlite_goal", table = "sqlite_sequence", pkColumnName = "name", valueColumnName = "seq", pkColumnValue = "Goal")
	private long idGoal;

    private int isAchieved;
	private String description;
    
	@Temporal(javax.persistence.TemporalType.DATE)
	private Date startAt;
   
    @Temporal(javax.persistence.TemporalType.DATE)
	private Date endAt;
   
    private String name;
    
    private String type;

    
    private double progress;
	
    private double initialValue;
   
	private double endValue;
	

	@ManyToOne
	@JoinColumn(name = "idUser", referencedColumnName = "idUser")
	private User idUser;

	
	public Goal() {
	}

	
	public long getIdGoal() {
		return idGoal;
	}

	public void setIdGoal(long idGoal) {
		this.idGoal = idGoal;
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
    public int getIsAchieved() {
		return isAchieved;
	}
    
	public void setIsAchieved(int isAchieved) {
		this.isAchieved = isAchieved;
	}

	public Date getStartAt() {
		return startAt;
	}

	public void setStartAt(Date startAt) {
		this.startAt = startAt;
	}
    
    
    
    
    public Date getEndAt() {
		return endAt;
	}
    
	public void setEndAt(Date endAt) {
		this.endAt = endAt;
	}
    
    

	public double getInitialValue() {
		return initialValue;
	}

	public void setInitialValue(double initialValue) {
		this.initialValue = initialValue;
	}


	public double getEndValue() {
		return endValue;
	}

	public void setEndValue(double endValue) {
		this.endValue = endValue;
	}

    public double getProgress (){
        return progress;
    }
    
	public void setProgress (double progress){
        this.progress=progress;
    }
    
    
    public String getType (){
        return type;
    }
    
	public void setType (String type){
        this.type=type;
    }
    
    //@XmlTransient
	public User getIdUser() {
		return idUser;
	}

	public void setIdUser(User idUser) {
		this.idUser = idUser;
	}

	///DB methods
	
	
	public static List<Goal> getAll() throws PersistenceException {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		List<Goal> list = em.createNamedQuery("Goal.findAll", Goal.class).getResultList();
		LifeCoachDao.instance.closeConnections(em);

		return list;
	}

	public static List<Goal> getByIdUser(long idUser) throws PersistenceException {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		List<Goal> result = em.createNamedQuery("Goal.findByIdUser", Goal.class).setParameter("idUser", User.getById(idUser))
				.getResultList();
		LifeCoachDao.instance.closeConnections(em);

		return result;
	}
    
    
    //
    
    
    public static Goal getById(long idGoal) throws PersistenceException {
        
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		Goal result = em.find(Goal.class,idGoal);
		LifeCoachDao.instance.closeConnections(em);
        
		return result;
	}
   
    
    
    //DB

	public static List<Goal> getByIdUserType(long idUser, String type) throws PersistenceException {
		EntityManager em =LifeCoachDao.instance.createEntityManager();
		List<Goal> result = em.createNamedQuery("Goal.findByUserAndType", Goal.class).setParameter("idUser", User.getById(idUser))
				.setParameter("type", type).getResultList();
		LifeCoachDao.instance.closeConnections(em);

		return result;
	}
    
    public static List<Goal> getAchieved(long idUser) throws PersistenceException {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		List<Goal> result = em.createNamedQuery("Goal.findIsAchievedByIdUser", Goal.class).setParameter("idUser", User.getById(idUser))
        .setParameter("isAchieved", 1).getResultList();
		LifeCoachDao.instance.closeConnections(em);
        
		return result;
	}
    
    public static List<Goal> getUnAchieved(long idUser) throws PersistenceException {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		List<Goal> result = em.createNamedQuery("Goal.findIsAchievedByIdUser", Goal.class).setParameter("idUser", User.getById(idUser))
        .setParameter("isAchieved", 0).getResultList();
		LifeCoachDao.instance.closeConnections(em);
        
		return result;
	}
    
    public static List<Goal> getByType (String type, long idUser) throws PersistenceException {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		List<Goal> result = em.createNamedQuery("Goal.findByIdUserType", Goal.class).setParameter("idUser", User.getById(idUser))
        .setParameter("type", type).getResultList();
		LifeCoachDao.instance.closeConnections(em);
        
		return result;
	}
    
    public static Goal update(Goal g) throws PersistenceException {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		g = em.merge(g);
		tx.commit();
		LifeCoachDao.instance.closeConnections(em);
        
		return g;
	}
    
    
    public static Goal create(Goal g) throws PersistenceException {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(g);
		tx.commit();
		LifeCoachDao.instance.closeConnections(em);
        
		return g;
	}
    
	public static void remove(Goal g) throws PersistenceException {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		g = em.merge(g);
		em.remove(g);
		tx.commit();
		LifeCoachDao.instance.closeConnections(em);
	}
    
	
	@Override
	public String toString() {
		return "Goal (" + getIdGoal() + ", name= "+getName()+", description=" + getDescription() + ", startAt=" + getStartAt() + ", initialValue=" + getInitialValue() + ", endValue=" + getEndValue() + ", progress=" + getProgress() + ", isAchieved= " + getIsAchieved() + ", type=" + getType() + ")";
	}
}
