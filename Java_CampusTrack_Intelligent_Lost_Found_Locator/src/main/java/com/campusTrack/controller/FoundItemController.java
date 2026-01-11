package com.campusTrack.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


import com.campusTrack.dao.FoundItemDao;
import com.campusTrack.dao.LostItemDao;
import com.campusTrack.entity.FoundItem;
import com.campusTrack.entity.FoundItemDTO;
import com.campusTrack.entity.LostItem;
import com.campusTrack.service.FoundItemService;
import com.campusTrack.service.LostFoundUsersService;

@RestController
@RequestMapping("/lostfound/")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class FoundItemController {
	
	@Autowired
	private FoundItemDao foundItemDao;
	
	@Autowired
	private LostFoundUsersService service;
	
	@Autowired
	private FoundItemService foundService;
	
	@Autowired
	private LostItemDao lostItemDao;
	
	
	 @PostMapping("/found")
	    public void saveFoundItem(@RequestBody FoundItem foundItem ) {
		 
		 if (foundItem.getUsername() == null || foundItem.getUsername().trim().isEmpty()) {
		        throw new RuntimeException("Username is missing");
		    }
	        foundItemDao.saveFoundItem(foundItem);   
	    }
	
	    @GetMapping("/found")
	    public List<FoundItem> getAllFoundItems() {
	        return foundItemDao.getAllFoundItems();
	    }

	    @GetMapping("/found/{id}")
	    public FoundItem getFoundItemById(@PathVariable String id) {
	        return foundItemDao.getFoundItemById(id);
	    }

	    @DeleteMapping("/found/{id}")
	    public void deleteFoundItemByID(@PathVariable String id) {
	        foundItemDao.deleteFoundItemById(id);
	    }

	    @PutMapping("/found")
	    public void updateFoundItem(@RequestBody FoundItem foundItem) {
	        foundItemDao.updateFoundItem(foundItem);
	    }

	    @GetMapping("/found-id")
	    public String generateId() {
	        return foundService.generateFoundItemId();
	    }
	    
	    @GetMapping("/found-user")
	    public List<FoundItem> getFoundItemsByUsername() {
	    	String userId = service.getUser_id();
	        return foundItemDao.getFoundItemsByUsername(userId);
	    }
	    
	    @GetMapping("/found-id/{id}")
	    public List<FoundItemDTO> getFoundItemsByLostItem(@PathVariable String id){
	    	LostItem lostItem = lostItemDao.getLostItemById(id);
	    	return foundService.collectFoundItems(lostItem);
	    	
	    }
	    
	    @GetMapping("/found/user/{username}")
	    public List<FoundItem> getFoundItemsByUsername(@PathVariable String username) {
	        return foundItemDao.getFoundItemsByUsername(username);
	    }
	    	    

}
