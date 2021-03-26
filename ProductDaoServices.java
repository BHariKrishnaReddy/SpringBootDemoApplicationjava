package com.example.demo.product;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class ProductDaoServices {
	
	private static List<Product> products=new ArrayList<>();
	private static int productsCount=3;
	
	static {
		products.add(new Product(1,"MobilePhone",20000));
		products.add(new Product(2,"Laptops",70000));		// adding Products using add function of arrayList
		products.add(new Product(3,"Smartwatchs",20000));
	}
	
	// To get all products
	public List<Product> findAll(){
		return products;
	}
	
	//To Add a product
	public Product save(Product product) {
		if(product.getId()==null) {
			product.setId(productsCount++);
			if(product.getName()==null) {
				System.out.print(product+"---------------------");
				throw new UserNotFoundException("Entered Value is not Valid - "+product.getName()+" .Please try Again with the appropriate Value.");
			}
			
		}
		products.add(product);
		return product;
	}
	
	//To get a Particular Product 
	public Product findOne(int id) {
		for(Product product:products) {
			if(product.getId()==id) 
				return product;
		}
		return null;
	}
	
	//To delete a Particular Product
	public Product deleteById(int id) {
		Iterator<Product> iterator=products.iterator();
		while(iterator.hasNext()) {
			Product product=iterator.next();
			if(product.getId()==id) {
				iterator.remove();
				return product;
		    }
		}
		return null;
	}
	
	//To update Product
	public Product updateProduct(Product product) {
		products.add(product);
		return product;
	}
}

									//Ideas in Implementation


//To get by name
//public Product findbyName(String name) {
//	for(Product product:products) {
//		if(product.getName() ==name) 
//			return product;
//	}
//	return null;
//}
//
