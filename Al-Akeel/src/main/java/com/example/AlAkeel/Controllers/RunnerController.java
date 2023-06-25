package com.example.AlAkeel.Controllers;

import com.example.AlAkeel.Entities.Order;
import com.example.AlAkeel.Entities.Runner;
import com.example.AlAkeel.Entities.orderStatus;
import com.example.AlAkeel.Entities.runnerStatus;
import com.example.AlAkeel.Repos.RunnerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("akeel/api/Runner")
@CrossOrigin

public class RunnerController {
    @Autowired
    private RunnerRepo repo;

    @PostMapping("signup")
    public String signUp(@RequestBody Runner runner){
        repo.save(runner);
        String message = "Sign up was successful! Your ID is "+ runner.getUserID();
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
//        Runner runner = em.find(Runner.class, id);
//        if (runner.getPassword().equals(pass)){
//
//            return "Welcome back "+runner.getName();
//        }
//        else{
//            return "Could not find user, either your ID or password are wrong";
//        }
//    }

    @PutMapping("deliveredorder/{oid}/{rid}")
    public String deliveredOrder(@PathVariable("oid") int id,@PathVariable("rid") int runnerId){

        Runner runner = repo.find(Runner.class, runnerId);

        Order order =repo.find(Order.class, id);
        if(order.getId()==id && !order.getStatus().equals(orderStatus.DELIVERED)){
            order.setStatus(orderStatus.DELIVERED);
            runner.setStatus(String.valueOf(runnerStatus.AVAILABLE));
            runner.incrementTC(); repo.update(order);repo.update(runner);
            return "order status:"+order.getStatus()+"\n runner status:"+runner.getStatus();
        }
        else
        {
            return"invalid order";
        }





    }


    @GetMapping("trips/{id}")
    public int getTripsCount(@PathVariable("id") int id){
        Runner runner = repo.find(Runner.class, id);
        return runner.getTripsCount();

    }


}
