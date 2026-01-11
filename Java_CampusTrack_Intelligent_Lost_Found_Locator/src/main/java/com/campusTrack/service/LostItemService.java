package com.campusTrack.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.campusTrack.dao.LostItemDao;
import com.campusTrack.entity.LostItem;

@Service
public class LostItemService {

	@Autowired
	private LostItemDao lostItemDao;
	
	@Autowired
	private LostFoundUsersService lostFoundUsersService;
	
	public String generateLostItemId() {
	    String newId = "";
	    String id = lostItemDao.getLastId();

	    if (id == null) {
	        newId = "L100001";
	    } else {
	        int num = Integer.parseInt(id.substring(1)) + 1;
	        newId = "L" + num;
	    }

	    return newId;
	}
	


	
}
