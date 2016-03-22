package introsde.assignment.model;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import introsde.assignment.dao.LifeCoachDao;
import introsde.assignment.model.HealthMeasure;
//import coach.lds.util.Util; //criptare??

@Entity
@Cacheable(false)
@Table(name = "User")
@NamedQueries({ 
	@NamedQuery(name = "User.findAll", query = "SELECT user FROM User user ORDER BY user.idUser"),
	@NamedQuery(name = "User.findByUserNamePassword", query = "SELECT user FROM User user WHERE user.userName = :userName AND user.password = :password"), })
@XmlRootElement

public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "sqlite_user")
	@TableGenerator(name = "sqlite_user", table = "sqlite_sequence", pkColumnName = "name", valueColumnName = "seq", pkColumnValue = "User")
	private long idUser;

	private String firstName;
	private String lastName;
	private String userName;
	private String password;
	private String email;
	private String gender;

	@Temporal(javax.persistence.TemporalType.DATE)
	private Date birthDate;

	@OneToMany(mappedBy = "idUser", cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private List<HealthMeasure> healthMeasure;

	@OneToMany(mappedBy = "idUser", cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private List<Activity> activity;

	@OneToMany(mappedBy = "idUser", cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private List<Goal> goal;

	public User() {
	}

	public long getIdUser() {
		return idUser;
	}

	public void setIdUser(long idUser) {
		this.idUser = idUser;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}	
	
	@XmlTransient
    public List<HealthMeasure> getHealthMeasure() {
		return healthMeasure;
	}

	public void setHealthMeasure(List<HealthMeasure> healthMeasure) {
		this.healthMeasure = healthMeasure;
	}

	
	@XmlTransient
	public List<Goal> getGoal() {
		return goal;
	}

	public void setGoals(List<Goal> goal) {
		this.goal = goal;
	}
    
    
	@XmlTransient
    public List<Activity> getActivity() {
		return activity;
	}
    
	public void setActivity(List<Activity> activity) {
		this.activity= activity;
	}
    

	
	
	public static User create(User user) throws PersistenceException {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		em.persist(user);
		tx.commit();
		LifeCoachDao.instance.closeConnections(em);

		return user;
	}

	public static User getById(long idUser) throws PersistenceException {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		User user = em.find(User.class, idUser);
		LifeCoachDao.instance.closeConnections(em);

		return user;
	}

	public static List<User> getAll() throws PersistenceException {
        
       // System.out.println("getAll()");
		EntityManager em = LifeCoachDao.instance.createEntityManager();
       // System.out.println("getAll() flag1");
		List<User> list = em.createNamedQuery("User.findAll", User.class).getResultList();
       // System.out.println("getAll() flag2");
		LifeCoachDao.instance.closeConnections(em);
       // System.out.println("getAll() flag3");

		return list;
	}

	public static User getByUserNamePassword(String userName, String password) throws PersistenceException {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		
		User user = em.createNamedQuery("User.findByUserNamePassword", User.class).setParameter("userName", userName)
				.setParameter("password", password).getResultList();
		LifeCoachDao.instance.closeConnections(em);

		if (user==null) {
            System.out.println("not found on db");
			return null;
		}
        System.out.println("found on db");
		System.out.println("User: "+user);
		return user;
	}

	public static User update(User user) throws PersistenceException {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		user = em.merge(user);
		tx.commit();
		LifeCoachDao.instance.closeConnections(em);

		return user;
	}
///mettere eliminazione per id
	public static void remove(User user) throws PersistenceException {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		user = em.merge(user);
		em.remove(user);
		tx.commit();
		LifeCoachDao.instance.closeConnections(em);
	}

	public static User changePassword(User user, String unEncryptedPassword) throws PersistenceException {
		EntityManager em = LifeCoachDao.instance.createEntityManager();

		String password = unEncryptedPassword; //Util.hashPassword(plain); magari metterla codificata

		user.setPassword(password);

		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.merge(user);
		tx.commit();

		LifeCoachDao.instance.closeConnections(em);
		return user;
	}

	public boolean verifyPassword(String nEncryptedPassword, String userName) {
		
        String password = getPassword();
        String baseUserName= getUserName();
		if ((password.equals(nEncryptedPassword))&(userName.equals(baseUserName))){
			return true;
            
		}
        else{
            
			return false;
		}
	}
	
	@Override
	public String toString() {
		return ("idUser " + getIdUser() + ", firstName=" + getFirstName() + ", lastName=" + getLastName() +
				", birthDate=" + getBirthDate() + ", email=" + getEmail() + ", gender=" + getGender() +
				", username=" + getUserName() + ", password= " + getPassword() + "");
	}
}