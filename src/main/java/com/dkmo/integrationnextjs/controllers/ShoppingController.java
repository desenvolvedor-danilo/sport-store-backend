package com.dkmo.integrationnextjs.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dkmo.integrationnextjs.models.Carrinho;
import com.dkmo.integrationnextjs.services.ShoppingService;

@RestController
@RequestMapping("/shopping")
@CrossOrigin(origins = "*")
public class ShoppingController {
    @Autowired
    private ShoppingService shoppingService;
    @PostMapping("/add-to-cart")
    public String addToCart(@RequestParam(name="codigo-product")String codigoProduct,@RequestParam(name = "email")String email, @RequestParam(name = "quantity")int quantity){
        return shoppingService.addToCart(codigoProduct,email,quantity);
    }
   
   @DeleteMapping("/remove")
   public void remove(@RequestParam("codigo") long codigo){
   shoppingService.remove(codigo);
   }

//    @DeleteMapping("/remove")
//    public String removeCart(@RequestParam("codigo") long codigo, @RequestParam("email")String email){
//    return shoppingService.removeCart(codigo,email);
//    }
   @GetMapping("/findall")
   public Carrinho getAllShopping(@RequestParam("email")String email){
return shoppingService.getCarrinho(email);
 }
@PutMapping("/edit")
   public int editCart(@RequestParam(name = "id")long id, @RequestParam(name = "quantity")int quantity){
    return shoppingService.edit(id, quantity);

  }
}