package com.example.AlAkeel.Controllers;


import com.example.AlAkeel.Entities.*;
import com.example.AlAkeel.Repos.RestaurantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("akeel/api/Owner")
@CrossOrigin

public class OwnerController {
    @Autowired
    private RestaurantRepo repo;


    @PostMapping("/signup")
    public String signUp(@RequestBody RestaurantOwner owner){
        repo.save(owner);

        return "SIGN UP SUCCESSFUL\nUSERNAME: "+ owner.getName() + "\nID: "+ owner.getUserID();
    }
//    @GetMapping("/login")
//    public String login(@RequestBody RestaurantOwner owner){
//        int id =  owner.getUserID();
//
//        TypedQuery<RestaurantOwner> query=em.createQuery(
//                "SELECT r from RestaurantOwner r where r.userID >: ID",RestaurantOwner.class);
//        query.setParameter("ID", id);
//
//        if(	query.getSingleResult().getPassword().equals(owner.getPassword())){
//            return "WELCOME USER, " +owner.getName();
//        }
//        return "LOGIN FAILED.TRY AGAIN"+owner.getName();
//    }
//
//
//
//
    @PostMapping("/addRest/{id}")
    public String addRestaurant(@PathVariable(value="id") String id, @RequestBody Restaurant rest){
        int ownerid = Integer.parseInt(id);
        RestaurantOwner owner = repo.findById(RestaurantOwner.class,ownerid );
        rest.setOwnerID(owner);
        String menu=""; int i=1;


        for(Meal meal:rest.getMeals()){
            menu+= i+"- "+meal.getName()+'\t'+meal.getPrice()+'\n';	i++;
             meal.setRestaurant(rest);
             //repo.update(meal);
        }

        repo.save(rest);
        repo.update(owner);	//TODO

        return rest.getName()+"'S MENU\n"+(menu);
    }


    @GetMapping("/getRest/{id}")
    public String getDetails (@PathVariable(value="id") String id){
        int restid = Integer.parseInt(id);
        Restaurant rest = repo.findById(Restaurant.class,restid);
        String list=""; int i=0;
        for(Meal meal : rest.getMeals()){
            list+= ++i+"- "+meal.getName()+'\t'+meal.getPrice()+'\n';
        }
        return rest.getName()+"'S MENU\n"+list;
    }

    private String getRestaurant(Restaurant rest){
        //Restaurant rest = em.find(Restaurant.class, id);
        String list=""; int i=0;
        for(Meal meal : rest.getMeals()){
            list+= ++i+"- "+meal.getName()+'\t'+meal.getPrice()+'\n';
        }
        return rest.getName()+"'S MENU\n"+list;

    }

    @GetMapping("/getAllRests/{id}")
    public String getRestaurants( @PathVariable("id") String id){
        int ownerid = Integer.parseInt(id);
        RestaurantOwner owner = repo.findById(RestaurantOwner.class,ownerid);
        String list="";int i=1;
        for(Restaurant rest: owner.getRests()){
            list+=(i++) +"- "+rest.getName()+'\n';
        }
        return list;

    }

    @PostMapping("/getReport/{id}")
    public String getReport ( @PathVariable("id") String id){
        int restid = Integer.parseInt(id);
        Restaurant rest = repo.findById(Restaurant.class, restid);
        double revenue,comp_orders,canceled_orders;
        revenue = comp_orders = canceled_orders =0;

        for(Order order:rest.getOrders()){
            if(order.getStatus().equals(orderStatus.DELIVERED) ){
                revenue+= order.getTotal();
                comp_orders++;}
            if(order.getStatus().equals(orderStatus.DELIVERED) )
                canceled_orders++;
        }
        return "REVENUE:\n"+revenue+"\nCOMPLETED ORDERS\n"+comp_orders+"\nCANCELLED ORDERS\n"+canceled_orders;
    }

    @DeleteMapping("/EditMenu/{ownerId}/{restID}/{mealNum}")
    public String deleteMeal(@PathVariable(value="restID") String restNum, @PathVariable(value="mealNum") String mealID,@PathVariable(value="ownerId") String ownerID){
        int restid = Integer.parseInt(restNum);
        int ownerid = Integer.parseInt(ownerID);
        int mealnum = Integer.parseInt(mealID);
        RestaurantOwner owner = repo.findById(RestaurantOwner.class, ownerid);
        List<Restaurant> restList = new ArrayList<Restaurant>(owner.getRests());
//
        Restaurant rest =restList.get(restid-1);
//        List<Meal> mealList = new ArrayList<Meal>(rest.getMeals());
         Meal meal = repo.findById(Meal.class,mealnum);
         repo.remove(meal);
         meal.getRestaurant().getMeals().remove(meal);
         meal.getOrders().remove(meal);
         repo.update(meal.getRestaurant());
         repo.update(meal.getOrders());
//         meal = null;
//        meals.setParameter("id",restNum);
//        Meal meal = meals.getResultList().get(mealnum);
//
//        System.out.println(meal.getName());
//        //rest.getMeals().remove(meal);
        return "d";
    }

    @PostMapping("/EditMenu/{ownerId}/{restNum}")
    public String addMeal( @PathVariable(value="ownerId") String ownerID, @PathVariable(value="restNum") String restNum, @RequestBody Meal newMeal){
        int restid = Integer.parseInt(restNum);
        int ownerid = Integer.parseInt(ownerID);
        RestaurantOwner owner = repo.findById(RestaurantOwner.class, ownerid);
        List<Restaurant> restList = new ArrayList<Restaurant>(owner.getRests());

        Restaurant rest =restList.get(restid-1);
        //rest.addMeal(newMeal);
        //em.merge(rest);
        newMeal.setRestaurant(rest);
        repo.save(newMeal);
        return getRestaurant(rest);
    }

    @PutMapping("/EditMenu/{ownerId}/{restID}/{mealNum}")
    public String updateMeal( @PathVariable(value="restID") String restNum, @PathVariable(value="mealNum") String mealID,@PathVariable(value="ownerId") String ownerID, @RequestBody Meal newMeal){
        int restid = Integer.parseInt(restNum);
        int ownerid = Integer.parseInt(ownerID);
        int mealnum = Integer.parseInt(mealID);
        RestaurantOwner owner = repo.findById(RestaurantOwner.class, ownerid);
        List<Restaurant> restList = new ArrayList<Restaurant>(owner.getRests());

        Restaurant rest =restList.get(restid-1);
        List<Meal> mealList = new ArrayList<Meal>(rest.getMeals());

        Meal meal = mealList.get(mealnum-1);
        meal.setName(newMeal.getName());
        meal.setPrice(newMeal.getPrice());
        repo.update(meal);
        return getRestaurant(rest);

    }
}
