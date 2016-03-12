package introsde.assignment.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

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
import introsde.assignment.model.*;
//import coach.lds.util.Util; //criptare??

class Test {
public static void main(String[] args){

 /*System.out.println("=============FOOD================================================");
    
    System.out.println("=============getAll================");
    List<Food> food=Food.getAll();
    System.out.println("size of list: "+food.size());
    
    for(Food foodtemp:food){
        
        System.out.println(foodtemp);
    
    }
    
    System.out.println("=============getByIdFood================");
    Food food1=Food.getByIdFood(1L);
    System.out.println(food1);
    
    System.out.println("=============getByCaloriesBound================");
    List<Food> food2=Food.getByCaloriesBound(10000);
    System.out.println("size of list: "+food2.size());
    
    for(Food foodtemp:food2){
        
        System.out.println(foodtemp);
        
    }
    
    System.out.println("=============getByType================");
    List<Food> food3=Food.getByType("pasta");
    System.out.println("size of list: "+food3.size());
    
    for(Food foodtemp:food3){
        
        System.out.println(foodtemp);
        
    }
    
    System.out.println("=============createFood()================");
    Food f3=new Food();
    f3.setName("testname");
    f3.setDescription("test description");
    f3.setCalories(1234);
    f3.setType("testType");
    System.out.println(Food.create(f3));
    
    System.out.println("////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////");
  

System.out.println("===========ACTIVITY==================================================");
    System.out.println("=============getAll================");
    
    List<Activity> activity=Activity.getAll();
    for(Activity activitytemp:activity){
        
        System.out.println(activitytemp);
        
    }
    
    System.out.println("=============getByIdActivity================");
    
    Activity activity2=Activity.getById(2L);
    System.out.println(activity2);
    
    System.out.println("=============getByCalories================");
    
    List<Activity> activity3=Activity.getByCalories(6000);
    for(Activity activitytemp:activity3){
        
        System.out.println(activitytemp);
        
    }
    System.out.println("=============getByType================");
    List<Activity> activity4=Activity.getByType("step");
   
    for(Activity activitytemp:activity4){
        
        System.out.println(activitytemp);
        
    }
    System.out.println("=============getByNamee================");
    List<Activity> activity5=Activity.getByName("zzZZZzz");
    
    for(Activity activitytemp:activity5){
        
        System.out.println(activitytemp);
        
    }

    
   System.out.println("////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////");
    System.out.println("============GOAL=================================================================");
    
    System.out.println("=============getAll================");

    List<Goal> goal=Goal.getAll();
        
    for(Goal goaltemp:goal){
        
        System.out.println(goaltemp);
        
    }
    System.out.println("=============getById================");
    
    Goal goal2=Goal.getById(1L);
    System.out.println(goal2);
        
    System.out.println("=============getByIdUSer================");
    List<Goal> goal32=Goal.getByIdUser(1L);
        
    for(Goal goaltemp:goal32){
        
        System.out.println(goaltemp);
            
    }
    System.out.println("=============getIsAchieved================");
        
        List<Goal> goal322=Goal.getAchieved(1L);
        
        for(Goal goaltemp:goal322){
            
            System.out.println(goaltemp);
            
        }

        
        
    System.out.println("=============getIsNotAchieved================");
        
        
        List<Goal> goal3222=Goal.getUnAchieved(1L);
        
        for(Goal goaltemp:goal3222){
            
            System.out.println(goaltemp);
            
        }
    /*
   System.out.println("=============updateGoal================");
    Goal g=Goal.getById(3L);
    g.setIsAchieved(0);
    System.out.println(Goal.update(g));
    
    
    */System.out.println("////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////");
    System.out.println("============USER=================================================================");
    
    
      System.out.println("=============updateUser================");
    
    User user11=User.getById(1L);
    System.out.println(user11);
    user11.setUserName("cambiato");
    System.out.println(User.update(user11));
    /*
    
    System.out.println("=============createUser================");
    User u=new User();
    u.setUserName("username");
    u.setPassword("123abc");
    HealthMeasure h =new HealthMeasure();
    h.setType("test");
    h.setValue(999);
    h.setUser(u);
    List <HealthMeasure> lhm=new ArrayList<HealthMeasure>();
    lhm.add(h);
    u.setHealthMeasure(lhm);
    System.out.println(User.create(u));
    
    
    //System.out.println("TESTTTTTTTTTT");
    

    
     System.out.println("=============getAll================");
    List<User> user=User.getAll();
    
    for(User usertemp:user){
        
        System.out.println(usertemp);
        
    }*/
  /*   System.out.println("=============getById================");
     User user1=User.getById(1L);
     System.out.println(user1);
    
    List <HealthMeasure> temp= user1.getHealthMeasure();
   // HealthMeasure h =new HealthMeasure();
    for (HealthMeasure h:temp){
        
        System.out.println(h);
    }
    
     System.out.println("=============STEP2 add new health con user annesso================");
    
    HealthMeasure h1 =new HealthMeasure();
    h1.setType("test");
    h1.setValue(999);
    
    
    User user11=User.getById(1L);
    System.out.println(user11);
    
    h1.setUser(user11);
    System.out.println(HealthMeasure.create(h1));
    
    System.out.println("=============STEP3 look for user health list================");
    
    User user12=User.getById(1L);
    System.out.println(user12);
    
    List <HealthMeasure> temp22= user12.getHealthMeasure();
    // HealthMeasure h =new HealthMeasure();
    for (HealthMeasure h22:temp22){
        
        System.out.println(h22);
    }

    
    
   /* System.out.println("=============getByUserNamePassword================");
    
    User user12=User.getByUserNamePassword("jester99","isde");
    System.out.println(user12);
    
    
    System.out.println("////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////");
    
   // System.out.println("============HealthMeasure=================================================================");
    
   /*
    System.out.println("=============createforUSER1================");
   
    HealthMeasure hmm=new HealthMeasure();
    User u12=User.getById(1L);
    
    
    
    hmm.setType("bloodPressure");
    hmm.setValue(180);
    hmm.setUser(u12);
    
    HealthMeasure.create(hmm);
    
    //System.out.println(hmm);
   // System.out.println(""+HealthMeasure.create(hmm));
    
    
    System.out.println("=============getAll================");
    List<HealthMeasure> hm=HealthMeasure.getAll();
    
    for(HealthMeasure hmtemp:hm){
        
        System.out.println(hmtemp);
        
    }
   
   

    System.out.println("===============verifyPassword==============");
    boolean status=User.verifyPassword("kahune","ff1x");
    System.out.println(status);

    */
    
    }
}