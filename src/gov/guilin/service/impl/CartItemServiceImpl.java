/*
 * Copyright 2014 guilin. All rights reserved.
 * Support: guilin
 * License: guilin
 */
package gov.guilin.service.impl;

import gov.guilin.dao.CartItemDao;
import gov.guilin.entity.CartItem;
import gov.guilin.service.CartItemService;

import javax.annotation.Resource;


import org.springframework.stereotype.Service;

/**
 * Service - 购物车项
 * 
 * @author guilin
 * @version
 */
@Service("cartItemServiceImpl")
public class CartItemServiceImpl extends BaseServiceImpl<CartItem, Long> implements CartItemService {

	@Resource(name = "cartItemDaoImpl")
	public void setBaseDao(CartItemDao cartItemDao) {
		super.setBaseDao(cartItemDao);
	}

}