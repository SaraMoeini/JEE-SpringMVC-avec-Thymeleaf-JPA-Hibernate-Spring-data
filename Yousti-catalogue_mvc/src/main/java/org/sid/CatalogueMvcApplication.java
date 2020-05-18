package org.sid;

import org.sid.dao.ProduitRepository;
import org.sid.entities.Produit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class CatalogueMvcApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(CatalogueMvcApplication.class, args);
		ProduitRepository produitRepository = ctx.getBean(ProduitRepository.class);
		produitRepository.save(new Produit("LX 4352", 670, 90));
		produitRepository.save(new Produit("Ord HP", 670, 90));
		produitRepository.save(new Produit("Imprimante Epson", 450, 12));
		produitRepository.save(new Produit("Thinkpad", 670, 10));
		
		produitRepository.findAll().forEach(p->System.out.println(p.getDesignation()));	//Expression lambda

	}

}
