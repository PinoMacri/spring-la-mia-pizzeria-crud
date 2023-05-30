package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PizzaController {
	@Autowired
	private PizzaService pizzaService;

	@GetMapping("/pizze")
	public String index(Model model) {
		Optional<List<Pizza>> optionalPizze = Optional.of(pizzaService.findAll());

		if (optionalPizze.isPresent()) {
			model.addAttribute("pizze", optionalPizze.get());
		} else {
			model.addAttribute("message", "Non ci sono pizze");
		}

		return "index";
	}

	@GetMapping("/pizze/{id}")
	public String getPizza(Model model, @PathVariable("id") int id) {
		Pizza pizza = getPizzaById(id);
		model.addAttribute("pizza", pizza);
		return "show";
	}

	private Pizza getPizzaById(int id) {
		List<Pizza> pizze = pizzaService.findAll();
		Pizza singolaPizza = null;
		for (Pizza pizza : pizze)
			if (pizza.getId() == id)
				singolaPizza = pizza;
		return singolaPizza;
	}

	@PostMapping("/pizze/nome")
	public String getPizzaNome(@RequestParam(required = false) String nome, Model model) {
		List<Pizza> pizze = pizzaService.findByNome(nome);
		model.addAttribute("pizze", pizze);
		model.addAttribute("nome", nome);
		return "index";
	}

	@GetMapping("pizze/create")
	public String create() {
		return "create";
	}
	@PostMapping("/pizze/store")
	public String store(@ModelAttribute Pizza pizza) {
		System.err.println(pizza);
		pizzaService.save(pizza);
		return "redirect:/pizze";
	}
	@GetMapping("/pizze/delete/{id}")
	public String delete (@PathVariable int id) {
		Optional <Pizza> pizzaOpt = pizzaService.findById(id);
		Pizza pizza = pizzaOpt.get();
		pizzaService.delete(pizza);
		return "redirect:/pizze";
	}
	@GetMapping("/pizze/edit/{id}")
	public String edit ( Model model, @PathVariable int id) {
		Optional <Pizza> pizzaOpt = pizzaService.findById(id);
		Pizza pizza = pizzaOpt.get();
		model.addAttribute("pizza", pizza);
		return "edit";
	}
	@PostMapping("/pizze/update/{id}")
	public String update(@PathVariable int id, @ModelAttribute Pizza pizza) {
		pizzaService.save(pizza);
		
		return "redirect:/pizze";
	}
}
