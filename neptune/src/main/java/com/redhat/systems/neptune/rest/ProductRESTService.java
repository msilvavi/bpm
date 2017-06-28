package com.redhat.systems.neptune.rest;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jboss.logging.Logger;

import com.redhat.systems.neptune.controller.ProductController;
import com.redhat.systems.neptune.dto.ListResponse;
import com.redhat.systems.neptune.model.catalogs.Product;
import com.redhat.systems.neptune.util.Roles;

@RolesAllowed(Roles.REST_ALL)
@Path("/products")
public class ProductRESTService { 

	private static final Logger logger = Logger.getLogger(ProductRESTService.class);

	@Inject
	ProductController productController;

	@GET
	@Path("/list")
	@RolesAllowed(Roles.USERS_ADMIN)
	@Produces(MediaType.APPLICATION_JSON)
	public ListResponse listProducts() {
		logger.debug("Getting products from service");
		List<Product> products = productController.getProductList();
		ListResponse response = new ListResponse();
		response.setRecords(products);
		response.setQueryRecordCount(products.size());
		response.setTotalRecordCount(products.size());
		return response;
	}

}
