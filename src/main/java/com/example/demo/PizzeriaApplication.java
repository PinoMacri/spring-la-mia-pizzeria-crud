package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PizzeriaApplication implements CommandLineRunner {

    @Autowired
    private PizzaService pizzaService;

    public static void main(String[] args) {
        SpringApplication.run(PizzeriaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        pizzaService.createAndSavePizza();
    	
        Pizza margherita = new Pizza("Margherita", "Pomodoro e Mozzarella","www.ciao.it",30.00f);
        pizzaService.save(margherita);
        
        Pizza napoli = new Pizza("Napoli", "Pomodoro Mozzarella e Alici","www.ciao.it",30.00f);
        pizzaService.save(napoli);
        
        Pizza capricciosa = new Pizza("Capricciosa", "Pomodoro, Mozzarella Uovo e Prosciutto","www.ciao.it",30.00f);
        pizzaService.save(capricciosa);
        
        Pizza calabrese = new Pizza("Calabrese", "Pomodoro, Mozzarella e Nduja","www.ciao.it",30.00f);
        pizzaService.save(calabrese);
        
        Pizza romana = new Pizza("Romana", "Pomodoro, Mozzarella Guanciale e Pecorino","www.ciao.it",30.00f);
        pizzaService.save(romana);

     
    }
}
