package com.dkmo.integrationnextjs.services;


import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


import com.dkmo.integrationnextjs.models.Carrinho;
import com.dkmo.integrationnextjs.models.ItensCarrinho;
import com.dkmo.integrationnextjs.models.Products;

import com.dkmo.integrationnextjs.models.UserRegister;
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
      UserRegister userRegister = registerRepository.findByEmail(emailUser);
      Products product = productsRepository.findByCodigo(codigo);
      Carrinho carrinho = userRegister.getCarrinho();
         carrinho.adicionarItens(product, quantity);
         shoppingRepository.save(carrinho);
      return "Produto adicionado ao carrinho com sucesso!";
   }
   @SuppressWarnings("null")
   public void remove(Long itemId){
      Optional<ItensCarrinho> item = itensCarrinhoRepository.findById(itemId);
      if(item.isPresent()){
      itensCarrinhoRepository.delete(item.get());
      }
   }
   
   public Carrinho getCarrinho(String email){
      
      UserRegister user = registerRepository.findByEmail(email);
      return user.getCarrinho();
   }
   @SuppressWarnings("null")
   public int edit(Long id,int quantity){
      Optional<ItensCarrinho> itens = itensCarrinhoRepository.findById(id);
      if(itens.isPresent()){
         itens.get().setQuantity(quantity);
         itensCarrinhoRepository.save(itens.get());
         return itens.get().getQuantity();
      }  
      throw new RuntimeException("Error to the alter attribute");
   }
}
