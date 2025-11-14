package com.dkmo.integrationnextjs.services;


import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


import com.dkmo.integrationnextjs.models.Carrinho;

import com.dkmo.integrationnextjs.models.Products;

import com.dkmo.integrationnextjs.models.UserRegister;

import com.dkmo.integrationnextjs.repository.ProductsRepository;
import com.dkmo.integrationnextjs.repository.RegisterRepository;
import com.dkmo.integrationnextjs.repository.ShoppingRepository;



@Service
public class ShoppingService {
   
   
   @Autowired
   private ShoppingRepository shoppingRepository;
   @Autowired
   private ProductsRepository productsRepository;
   @Autowired
   private RegisterRepository registerRepository;

   public String addToCart(String codigoProduct, String emailUser, int quantity) {
      Long codigo = Long.parseLong(codigoProduct);
      UserRegister userRegister = registerRepository.findByEmail(emailUser);
      Products product = productsRepository.findByCodigo(codigo);
      Carrinho carrinho = userRegister.getCarrinho();
         carrinho.adicionarItens(product, quantity);
         shoppingRepository.save(carrinho);
      return "Produto adicionado ao carrinho com sucesso!";
   }
   
   @SuppressWarnings("null")
   public String removeCart(Long productId,String email){
      UserRegister user = registerRepository.findByEmail(email);
      Optional<Products> products = productsRepository.findById(productId);
      Optional<Carrinho> carrinho = shoppingRepository.findById(user.getCarrinho().getId());
      carrinho.get().removeItens(products.get());
      shoppingRepository.save(carrinho.get());
      return "apagado com sucesso";
      
   }
   public Carrinho getCarrinho(String email){
      
      UserRegister user = registerRepository.findByEmail(email);
      return user.getCarrinho();
   }
   // public int editCart(String id,int quantity){
   //    long idl = Long.parseLong(id);
   //    Optional<Carrinho> shopping = shoppingRepository.findById(idl);
   //    if(shopping.isPresent()){
   //       shopping.get().setQuantity(quantity);
   //       shoppingRepository.save(shopping.get());
   //       return shopping.get().getQuantity();
   //    }
   //    throw new RuntimeException("Erro ao atribuir novo valor");
      
   // }
}
