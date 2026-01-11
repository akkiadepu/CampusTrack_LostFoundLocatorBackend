package com.campusTrack.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.campusTrack.dao.LostItemDao;
import com.campusTrack.entity.FoundItem;
import com.campusTrack.entity.LostItem;
import com.campusTrack.service.LostFoundUsersService;
import com.campusTrack.service.LostItemService;



@RestController
@RequestMapping("/lostfound/")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")  // corrected "htts" → "http"
public class LostItemController {

    @Autowired
    private LostItemDao lostItemDao;
    
    @Autowired
    private LostFoundUsersService service;
    
    @Autowired
    private LostItemService lostService;

    @PostMapping("/lost")
    public void saveLostItem(@RequestBody LostItem lostItem) {
        lostItemDao.saveLostItem(lostItem);   // fixed incorrect method call
    }

    @GetMapping("/lost")
    public List<LostItem> getAllLostItems() {
        return lostItemDao.getAllLostItems();
    }

    @GetMapping("/lost/{id}")
    public LostItem getLostItemById(@PathVariable String id) {
        return lostItemDao.getLostItemById(id);
    }

    @DeleteMapping("/lost/{id}")
    public void deleteLostItemByID(@PathVariable String id) {
        lostItemDao.deleteLostItemById(id);
    }

    @PutMapping("/lost")
    public void updateLostItem(@RequestBody LostItem lostItem) {
        lostItemDao.updateLostItem(lostItem);
    }

    @GetMapping("/lost-id")
    public String generateId() {
        return lostService.generateLostItemId();
    }
    
    @GetMapping("/lost-user")
    public List<LostItem> getLostItemsByUsername() {
    	String userId = service.getUser_id();
        return lostItemDao.getLostItemsByUsername(userId);
    }
    
    @GetMapping("/lost/user/{username}")
    public List<LostItem> getFoundItemsByUsername(@PathVariable String username) {
        return lostItemDao.getLostItemsByUsername(username);
    }
    
    
}
