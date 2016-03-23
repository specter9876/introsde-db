//package introsde.document.soap;
package introsde.document.soap;
import introsde.assignment.model.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import java.util.List;
import java.util.ArrayList;

import javax.jws.WebService;

//Service Implementation

@WebService(endpointInterface = "introsde.document.soap.DB",
	serviceName="DBService")
public class DBImpl implements DB {
    
    
    ////FOOD//////////////////////////////////////////////////////
    
    //Method #1:
    @Override
    public List<Food> getAllFood(){
        
        List <Food> food=Food.getAll();
        return food;
    }
   
    //Method #2:
    @Override
    public Food getFoodById(Long id){
        
        Food food=Food.getByIdFood(id);
        return food;
    }
 
    //Method #3:
    @Override
    public List <Food> getFoodByCaloriesBound (double calories){
        
        List <Food> food=Food.getByCaloriesBound(calories);
        return food;
    }
    
    @Override
    public List <Food> getFoodByType (String type){
        
        List<Food> food=Food.getByType(type);
        return food;
        
    }

    //Method #3:
    @Override
    public Food createFood (Food food){
        
        return Food.create(food);
        
    }

    
    
    ///USER///////////////////////////////////////////////////
    
    //Method #4:
    @Override
    public List<User> getAllUser(){
        
        List <User> user=User.getAll();
        return user;
    }
    
    
    //Method #5:
    @Override
    public User getUserById(Long idUser){

        User user=User.getById(idUser);
        return user;
    }
    
    //Method #6:
    @Override
    public User getUserByUsernamePassword(String userName, String password){
       
        User user= User.getByUserNamePassword(userName, password);
        return user;
    }
    
    //Method #7:
    @Override
    public User createUser (User user){
        
        return User.create(user);
    }
    
    //Method #8:
    @Override
    public User updateUser (User user){
        
        return User.update(user);
    }
    
    //Method #9:
    @Override
    public boolean removeUser (User user){
        User.remove(user);
        return true;
    }

    //Method #10:
   @Override
	public User loginUser(String userName, String password){
        
        System.out.println("sting username: "+userName+", password:"+password);
        User user=User.getByUserNamePassword(userName,password);
        System.out.println("User: "+user);
        return user;
    }

    
    //Method #11:
    @Override
	public boolean updatePassword(long id, String firstPassword, String secondPassword){
        //changePasswordc: hangePassword(User user, String unEncryptedPassword
       // return User.updatePassword(id, firstPassword,secondPassword);
        return true;
    }
    
    @Override
    public boolean controlUsernamePassword(String userName, String password){
        
        User user=User.getByUserNamePassword(userName, password);
        if(user!=null){
            
            return true;
        }
        else{
            return false;
        }
    }
    
    ///ACTIVITY//////////////////////////////////////////////////////
    
    //Method #12:
    @Override
    public List<Activity> getAllActivity(){
        
        List<Activity> activity=Activity.getAll();
        return activity;
    }

    public List<Activity> getActivityByIdUser(Long idUser){
        
        List<Activity> activity=Activity.getByIdUser(idUser);
        return activity;
    }

    //Method #13:
    @Override
    public Activity getActivityById(Long idActivity){
        
        Activity activity= Activity.getById(idActivity);
        return activity;
    }
    
    //Method #14:
    @Override
    public List<Activity> getActivityByType( String type){
        
        List<Activity> activity= Activity.getByType(type);
        return activity;
    }
    
    //Method #15:
    @Override
    public List<Activity> getActivityByName(String name){
        
        List<Activity> activity=Activity.getByName(name);
       return activity;
    }
    
    //Method #16:
    @Override
    public List<Activity> getActivityByCalories( double calories){
        
        List<Activity> activity= Activity.getByCalories(calories);
        return activity;
    }
    
    
    //Method #17:
    @Override
    public Activity createActivity (Activity activity){
        Activity activity2= Activity.create(activity);
        return activity2;
    }
    
    ///GOAL//////////////////////////////////////////////////////
    
    //Method #18:
    @Override
    public List<Goal> getAllGoal(){
        //to do
        List <Goal> goal= Goal.getAll();
        return goal;
    }
    
    //Method #19:
    @Override
    public Goal getGoalById(Long idGoal){
        
        Goal goal= Goal.getById(idGoal);
        return goal;
    }
    
    //Method #20:
    @Override
    public List<Goal> getGoalByType(String type, long id ){
        
        ////modifacare va solo per user 1
        List <Goal> goal= Goal.getByIdUserType(id,type);
        return goal;
    }
    
    //Method #21:
    @Override
    public List<Goal> getGoalNotAchieved(long id){
        
        List<Goal> goal=Goal.getUnAchieved(id);
        return goal;
    }
    
    //Method #21:
    @Override
    public List<Goal> getGoalAchieved(long id){
        
        List<Goal> goal=Goal.getAchieved(id);
        return goal;
    }

    ///////////mancaaaaaa su database  query////////////////////////
    //Method #22:
    @Override
    public List<Goal> getGoalEndAt(Date endAt){
        
        //to do
        return null;
    }
    
    //Method #23:
    @Override
    public List<Goal> controlGoal(String type){
        //to do
        return null;
    }
    
    @Override
    public Goal createGoal(Goal goal){
        
        return Goal.create(goal);
    }
    
    @Override
    public Goal updateGoal(Goal goal){
        
        return Goal.update(goal);
        
    }
    

    
    
    ///HEALTHMEASURE///////////////////////////////////////////////////////
    
    
    //Method #24:
    @Override
    public List<HealthMeasure> getAllHealthMeasure(){
        
        List<HealthMeasure> healthMeasure= HealthMeasure.getAll();
        return healthMeasure;
    }
    
    //Method #25:
    @Override
    public List<HealthMeasure> getHealthMeasureByIdUser(Long idUser){
        
        List <HealthMeasure> healthMeasure=HealthMeasure.getByIdUser(idUser);
        return healthMeasure;
    }
    
    
    @Override
    public List<HealthMeasure> getHealthMeasureByIdUserType(Long idUser, String type){
        
        List <HealthMeasure> healthMeasure=HealthMeasure.getByIdUserType(idUser, type);
        return healthMeasure;
    }

  
    //Method #27:
    @Override
    public HealthMeasure createHealthMeasure(HealthMeasure healthMeasure){
        
        HealthMeasure m=HealthMeasure.create(healthMeasure);
        return  m;
    }
    
    //Method #27:
    @Override
    public HealthMeasure updateHealthMeasure(HealthMeasure healthMeasure){
        
       // HealthMeasure.update(healthMeasure);
        return  HealthMeasure.update(healthMeasure);
        
    }
    

}
