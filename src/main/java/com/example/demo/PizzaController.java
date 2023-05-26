package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PizzaController {
	@Autowired
	private PizzaService pizzaService;

	@GetMapping("/pizze")
	public String Index(Model model) {
	    List<Pizza> pizze = pizzaService.findAll();
	    
	    if (!pizze.isEmpty()) {
	        model.addAttribute("pizze", pizze);
	    } else {
	        model.addAttribute("message", "Non ci sono pizze");
	    }
	    
	    return "index";
	}
}
