package com.dkmo.integrationnextjs.services;


import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


import com.dkmo.integrationnextjs.models.Cart;
import com.dkmo.integrationnextjs.models.CartItens;
import com.dkmo.integrationnextjs.models.Products;

import com.dkmo.integrationnextjs.models.Register;
import com.dkmo.integrationnextjs.repository.ItensCarrinhoRepository;
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
   @Autowired
   private ItensCarrinhoRepository itensCarrinhoRepository;

   public String addToCart(String codigoProduct, String emailUser, int quantity) {
      Long codigo = Long.parseLong(codigoProduct);
      Register userRegister = registerRepository.findByEmail(emailUser);
      Products product = productsRepository.findByCodigo(codigo);
      Cart carrinho = userRegister.getCarrinho();
         carrinho.adicionarItens(product, quantity);
         shoppingRepository.save(carrinho);
      return "Produto adicionado ao carrinho com sucesso!";
   }
   @SuppressWarnings("null")
   public void remove(Long itemId){
      Optional<CartItens> item = itensCarrinhoRepository.findById(itemId);
      if(item.isPresent()){
      itensCarrinhoRepository.delete(item.get());
      }
   }
   
   public Cart getCarrinho(String email){
      
      Register user = registerRepository.findByEmail(email);
      return user.getCarrinho();
   }
   @SuppressWarnings("null")
   public int edit(Long id,int quantity){
      Optional<CartItens> itens = itensCarrinhoRepository.findById(id);
      if(itens.isPresent()){
         itens.get().setQuantity(quantity);
         itensCarrinhoRepository.save(itens.get());
         return itens.get().getQuantity();
      }  
      throw new RuntimeException("Error to the alter attribute");
   }
}
