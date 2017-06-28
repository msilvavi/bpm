package com.redhat.systems.neptune.controller;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.logging.Logger;
import org.jboss.logging.Logger.Level;

import com.redhat.systems.neptune.model.catalogs.Product;

public class ProductController {

	private static final Logger logger = Logger.getLogger(ProductController.class);

	@Inject
	EntityManager em;

	@SuppressWarnings("unchecked")
	public List<Product> getProductList() {
		logger.log(Level.DEBUG, "Quering products");
		List<Product> products = null;
		Query q = em.createNamedQuery("Product.findProducts", Product.class);
		Object result = q.getResultList();
		if (result != null) {
			products = (List<Product>) result;
		}
		return products;
	}
}
