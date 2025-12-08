package com.campusTrack.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.campusTrack.dao.FoundItemDao;

@Service
public class FoundItemService {

	@Autowired
	private FoundItemDao foundItemDao;
	
	public String generateFoundItemId() {
	    String newId = "";
	    String id = foundItemDao.getLastId();

	    if (id == null) {
	        newId = "L100001";
	    } else {
	        int num = Integer.parseInt(id.substring(1)) + 1;
	        newId = "L" + num;
	    }

	    return newId;
	}
	
}
