package introsde.assignment.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
import introsde.assignment.model.User;

@Entity
@Table(name = "HealthMeasure")
@NamedQueries({ @NamedQuery(name = "HealthMeasure.findAll", query = "SELECT m FROM HealthMeasure m ORDER BY m.date DESC"),
	@NamedQuery(name = "HealthMeasure.findByIdUser", query = "SELECT m FROM HealthMeasure m where m.idUser = :idUser ORDER BY m.date DESC"),
	@NamedQuery(name = "HealthMeasure.findByIdUserType", query = "SELECT m FROM HealthMeasure m where m.idUser = :idUser AND m.type = :type ORDER BY m.date DESC"), })

@XmlRootElement
public class HealthMeasure implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "sqlite_healthmeasure")
	@TableGenerator(name = "sqlite_healthmeasure", table = "sqlite_sequence", pkColumnName = "name", valueColumnName = "seq", pkColumnValue = "HealthMeasure")
	private long idHealthMeasure;

    private String type;
    
	private double value;
    
    @Temporal(javax.persistence.TemporalType.DATE)
	private Date date;
    

    
	@ManyToOne
	@JoinColumn(name = "idUser", referencedColumnName = "idUser")
	private User idUser;

	
	public HealthMeasure() {
	}

	
	public long getIdHealthMeasure() {
		return idHealthMeasure;
	}

	public void setIdHealthMeasure(long idHealthMeasure) {
		this.idHealthMeasure = idHealthMeasure;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

    //@XmlTransient
	public User getUser() {
		return idUser;
	}

	public void setUser(User idUser) {
		this.idUser = idUser;
	}

	
	public static HealthMeasure create(HealthMeasure m) throws PersistenceException {
        
        
        //System.out.println("flaggg: "+m);
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(m);
		tx.commit();
		LifeCoachDao.instance.closeConnections(em);

		return m;
	}

	public static HealthMeasure getById(long idHealthMeasure) throws PersistenceException {
		EntityManager em =LifeCoachDao.instance.createEntityManager();
		HealthMeasure m = em.find(HealthMeasure.class, idHealthMeasure);
		LifeCoachDao.instance.closeConnections(em);

		return m;
	}

	public static List<HealthMeasure> getAll() throws PersistenceException {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		List<HealthMeasure> list = em.createNamedQuery("HealthMeasure.findAll", HealthMeasure.class).getResultList();
		LifeCoachDao.instance.closeConnections(em);

		return list;
	}

	public static List<HealthMeasure> getByIdUser(long idUser) throws PersistenceException {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		List<HealthMeasure> result = em.createNamedQuery("HealthMeasure.findByIdUser", HealthMeasure.class)
				.setParameter("idUser", User.getById(idUser)).getResultList();
		LifeCoachDao.instance.closeConnections(em);

		return result;
	}

	public static List<HealthMeasure> getByIdUserType(long idUser, String type) throws PersistenceException {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		List<HealthMeasure> result = em.createNamedQuery("HealthMeasure.findByIdUserType", HealthMeasure.class)
				.setParameter("idUser", User.getById(idUser)).setParameter("type", type).getResultList();
		LifeCoachDao.instance.closeConnections(em);

		return result;
	}

	

	//fare query per stampare i piu recenti???
	
	public static HealthMeasure update(HealthMeasure m) throws PersistenceException {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		m = em.merge(m);
		tx.commit();
		LifeCoachDao.instance.closeConnections(em);

		return m;
	}

	public static void remove(HealthMeasure m) throws PersistenceException {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		m = em.merge(m);
		em.remove(m);
		tx.commit();
		LifeCoachDao.instance.closeConnections(em);
	}
	
	@Override
	public String toString() {
		return "HealthMeasure (" + getIdHealthMeasure() + ", date=" + getDate() + ", type=" + getType() + ", value=" +
				", IdUser=" + getUser().getIdUser() + ")";
	}
}
