package com.example.AlAkeel.Controllers;

import com.example.AlAkeel.Entities.Customer;
import com.example.AlAkeel.Entities.Meal;
import com.example.AlAkeel.Entities.Order;
import com.example.AlAkeel.Entities.Restaurant;
import com.example.AlAkeel.Repos.CustomerRepo;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("akeel/api/Customer")
@CrossOrigin
public class CustomerController {
    @Autowired
    private CustomerRepo repo;


    @PostMapping("signup")
    public String signUp(@RequestBody Customer customer){
        repo.save(customer);
        String message = "Sign up was successful! Your ID is "+ customer.getUserID();
        return message;
    }

//    @PostMapping("login")
//    public String logIn(InputStream input){
//
//        // Create a JsonReader object to read the JSON input from the InputStream
//        JsonReader reader = Json.createReader(new InputStreamReader(input));
//
//        // Parse the JSON input to a JsonObject
//        JsonObject jsonInput = reader.readObject();
//        int id = Integer.parseInt(jsonInput.getString("id"));
//        String pass = jsonInput.getString("password");
//
//        Customer customer = em.find(Customer.class, id);
//        if (customer.getPassword().equals(pass)){
//            return "Welcome back "+customer.getName();
//        }
//        else{
//            return "Could not find user, either your ID or password are wrong";
//        }
//    }

    @PostMapping("/add_order/{customer}/{rest}")
    public String addOrder(@RequestBody int[] arr,@PathVariable(value = "customer") String customerID, @PathVariable(value = "rest")String restID ) {
        Order order = new Order();
        int cID = Integer.parseInt(customerID);
        int rID = Integer.parseInt(restID);
        order.setDateTime(new Date());
        order.setCustomer(repo.find(Customer.class,cID)); order.setRestaurant(repo.find(Restaurant.class,rID));
        float total=0;
        for(int i:arr){
            Meal meal = repo.find(Meal.class,i); order.addMeal(meal);  total += meal.getPrice();
        }
        order.setPrice(total);
        repo.save(order); //repo.update(order.getCustomer()); repo.update(order.getRestaurant());
        return "order";
    }
//    @PostMapping("/add_order")
//    public Order addOrder(InputStream input){
//       JsonReader reader = Json.createReader(input);
//
//        // Parse the JSON input to a JsonObject
//        JsonObject jsonInput = reader.readObject();
//       // int customerID = Integer.parseInt(jsonInput.getString("customer"));
//        //Customer customer = repo.find(Customer.class, customerID);
//        Order order =  new Order();
//        ///order.setCustomer(customer);
////        int restID = Integer.parseInt(jsonInput.getString("restaurant"));
////        Restaurant rest = repo.find(Restaurant.class, restID);
////        order.setRestaurant(rest);
//        float price = Float.parseFloat(jsonInput.getString("price"));
//        order.setPrice(price);
//        //? should i make sure customer id exists in DB
//        // Create a JsonReader object to read the JSON input from the InputStream
////        JsonReader reader = Json.createReader(new InputStreamReader(input));
////        // Parse the JSON input to a JsonObject
////        JsonObject jsonInput = reader.readObject();
////        int customerID = Integer.parseInt(jsonInput.getString("customerID"));
////        Customer customer = em.find(Customer.class, customerID);
////        Order order =  new Order();
////        order.setCustomer(customer);
//
//        order.setDateTime(new Date());
//        System.out.println(order.getMeals());
//      //  order.setCustomer(repo.find(Customer.class,order.customer));
//        //int restID = Integer.parseInt(jsonInput.getString("restaurantID"));
//        //Restaurant rest = em.find(Restaurant.class, restID);
//        //order.setRestaurant(rest);
//
////        JsonArray meals = jsonInput.getJsonArray("meals");
////        int len = meals.size();
////        List list =  new ArrayList<Meal>();
////        for (int i = 0; i < len; i++){
////            int x = Integer.parseInt(meals.getString(i));
////            Meal meal = rest.getMeals().get(x-1);
////            //  list.add(meal);
////            order.addMealItem(meal);
////        }
//        //System.out.println(order.getCustomer());
//       repo.save(order);
//        return order;
//
//    }

    @GetMapping("/list_resturants")
    public List<String> listResturants(){
        Query query=repo.createQuery("SELECT r.name from Restaurant r ");
        List<String> names =query.getResultList();
        return names;

    }




}
