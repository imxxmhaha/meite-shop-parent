package com.mayikt.product.service.impl;

import java.util.List;

import com.mayikt.api.product.service.ProductSearchService;
import com.mayikt.common.base.BaseApiService;
import com.mayikt.common.base.BaseResponse;
import com.mayikt.product.output.dto.ProductDto;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.web.bind.annotation.RestController;

import com.mayikt.product.es.entity.ProductEntity;
import com.mayikt.product.es.reposiory.ProductReposiory;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@RestController
public class ProductSearchServiceImpl extends BaseApiService<List<ProductDto>> implements ProductSearchService {
	@Autowired
	private ProductReposiory productReposiory;

	@Override
	public BaseResponse<List<ProductDto>> search(String name) {
		//int i = 1/0;
		// 1.拼接查询条件
		BoolQueryBuilder builder = QueryBuilders.boolQuery();
		// 2.模糊查询name字段
		builder.must(QueryBuilders.fuzzyQuery("name", name));
		Pageable pageable = new QPageRequest(0, 5);
		// 3.调用ES接口查询
		Page<ProductEntity> page = productReposiory.search(builder, pageable);
		// 4.获取集合数据
		List<ProductEntity> content = page.getContent();
		// 5.将entity转换dto
		MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
		List<ProductDto> mapAsList = mapperFactory.getMapperFacade().mapAsList(content, ProductDto.class);
		return setResultSuccess(mapAsList);
	}
}
