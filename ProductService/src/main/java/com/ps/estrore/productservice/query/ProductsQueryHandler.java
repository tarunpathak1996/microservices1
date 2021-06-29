package com.ps.estrore.productservice.query;

import java.util.ArrayList;
import java.util.List;

import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ps.estrore.productservice.core.data.ProductEntity;
import com.ps.estrore.productservice.core.data.ProductRepository;
import com.ps.estrore.productservice.query.rest.model.ProductRestModel;

@Component
public class ProductsQueryHandler {

	private final ProductRepository productRepo;

	@Autowired
	public ProductsQueryHandler(ProductRepository productRepo) {
		this.productRepo = productRepo;
	}

	@QueryHandler
	public List<ProductRestModel> findProducts(FindProductsQuery query) {

		List<ProductRestModel> productsModelList = new ArrayList<ProductRestModel>();
		List<ProductEntity> products = productRepo.findAll();
		products.forEach(product ->{
			ProductRestModel productRestModel = new ProductRestModel();
			BeanUtils.copyProperties(product, productRestModel);
			productsModelList.add(productRestModel);
		});
		
		return productsModelList;
	}

}
