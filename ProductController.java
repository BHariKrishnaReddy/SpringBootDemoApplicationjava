package com.example.demo.product;

import java.net.URI;
import java.util.List;
import java.util.Locale;
import org.springframework.http.ResponseEntity;
import org.springframework.context.MessageSource;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.context.event.ApplicationReadyEvent;
//import org.springframework.context.event.EventListener;
//import org.springframework.core.io.ClassPathResource;
//import javax.sql.DataSource;
//import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController								//to create RESTful web services using Spring MVC.
public class ProductController {		   //Used to map web requests onto specific handler classes and/or handler methods
	
	@Autowired							   // enables you to inject the object dependency implicitly.
	private ProductDaoServices service;
	
	@Autowired
	private MessageSource messageSource;
	
//	@Autowired
//  private DataSource dataSource;
	
	public String regex ="[0-9]+";
	
	//Welcome Page
	@GetMapping("/")      // internalization
	public String pageinfo(@RequestHeader(name="Accept-Language",required = false) Locale locale) {
		return "Hey "+"\n"+ messageSource.getMessage("good.morning.message",null,  locale)  +"\n"+"To get All Products --> /productsall" +"\n"+ "To find a Product --> /productsfind/{id} " +"\n"+ "To add a Product --> /productsadd  " +"\n"+ "To delete a Product --> /productsdel/{id} " +"\n"+ "To update Product --> /productsupdate";
	}
	
	// To get All Products -->
	@GetMapping("/productsall")
	public List<Product> retrieveAllProducts(){
		return service.findAll();
	}
	
	//To find a Product
	@GetMapping("/productsfind/{id}")
	public Product retrieveProduct(@PathVariable int id) {
		
		Product product= service.findOne(id);
		if(product==null )
			throw new UserNotFoundException("Entered Value is not available - "+id+" .Please try Again with the appropriate Value.");
		return product;
	}
	
	//To add a Product
	@PostMapping("/productsadd")
	public ResponseEntity createProduct(@RequestBody Product product) {
		Product savedProduct=service.save(product);
		String x=product.getName();
		
		if (product.getId()>=0 && x.matches("[a-zA-Z]+") ) {
				URI location=ServletUriComponentsBuilder
						.fromCurrentRequest()
						.path("/{id}")
						.buildAndExpand(savedProduct.getId()).toUri();
						;
				return ResponseEntity.created(location).build();
				}else {
		throw new UserNotFoundException("Entered Value/Values are not Appropiate.Please try Again with the appropriate Value.");}
}
	
	//To delete a Product
	@DeleteMapping("/productsdel/{id}")
	public void deleteProduct(@PathVariable int id) {
		Product product= service.deleteById(id);
		if(product==null)
			throw new UserNotFoundException("id-"+id);
	}
	
	//To update Products
	@PutMapping("/productsupdate")
	public Product updateProduct(@RequestBody Product product) {
		
		return service.updateProduct(product);
	}
	
}
	
									// Ideas In implementation


////find by name
//@GetMapping("/p/{name}")
//public Product retrieveProduct(@PathVariable String name) {
//	Product product= service.findbyName(name);
//	if(product==null)
//		throw new UserNotFoundException("Enter id is not available-"+name);
//	return product;
//}


//	@EventListener(ApplicationReadyEvent.class)
//    public void loadData() {
//            ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator(false, false, "UTF-8", new ClassPathResource("data.sql"));
//        resourceDatabasePopulator.execute(dataSource);
//    }
