package com.campusTrack.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.campusTrack.dao.FoundItemDao;
import com.campusTrack.dao.FoundItemRepository;
import com.campusTrack.dao.LostItemDao;
import com.campusTrack.dao.LostItemRepository;
import com.campusTrack.dao.MatchItemDao;
import com.campusTrack.dao.MatchItemRepository;
import com.campusTrack.entity.FoundItem;
import com.campusTrack.entity.FoundItemDTO;
import com.campusTrack.entity.LostItem;
import com.campusTrack.entity.MatchItem;
import com.campusTrack.entity.MatchItemDTO;

@Service
public class MatchItemService {

	@Autowired
	private MatchItemRepository matchRepository;
	
	@Autowired
	private LostItemDao lostItemDao;
	
	@Autowired
    private FoundItemRepository repository;
	
	@Autowired
	private FoundItemDao foundItemDao;
	
	@Autowired
	private LostItemRepository lostItemRepository;
	
	public void updateLostFoundItems(MatchItemDTO matchItemDTO) {
		
		String lostItemId = matchItemDTO.getLostItemId();
		String foundItemId = matchItemDTO.getFoundItemId();
		
		LostItem lostItem = lostItemDao.getLostItemById(lostItemId);
		FoundItem foundItem = foundItemDao.getFoundItemById(foundItemId);
		
		lostItem.setStatus(true);
		foundItem.setStatus(true);
		
		lostItemDao.saveLostItem(lostItem);
		foundItemDao.saveFoundItem(foundItem);
				
		 
		
	}
	
//	public List<FoundItemDTO> findMatchingFoundItems(String lostItemId) {
//
//	    LostItem lost = lostItemDao.getLostItemById(lostItemId);
//
//	    List<FoundItem> results = foundItemDao.matchFoundItems(
//	        lost.getLostItemName(),
//	        lost.getCategory(),
//	        lost.getColor(),
//	        lost.getBrand()
//	    );
//
//	    return results.stream()
//	        .map(FoundItemDTO::new)
//	        .collect(Collectors.toList());
//	}

//	public List<FoundItemDTO> findMatchingFoundItems(String lostItemId) {
//
//	    LostItem lost = lostItemDao.getLostItemById(lostItemId);
//
//	    List<FoundItem> candidates =
//	        foundItemDao.matchFoundItems(
//	            lost.getLostItemName(),
//	            lost.getCategory(),
//	            lost.getColor(),
//	            lost.getBrand(),
//	            lost.getLocation()
//	        );
//
//	    List<FoundItem> finalMatches = new ArrayList<>();
//
//	    for (FoundItem found : candidates) {
//
//	        // -------- Rule 1: Item name match (TOP PRIORITY) --------
//	        if (isNameMatch(lost.getLostItemName(), found.getFoundItemName())) {
//	            finalMatches.add(found);
//	            continue;
//	        }
//
//	        // -------- Rule 2: 2 or 3 other fields match --------
//	        int matchCount = 0;
//
//	        if (equalsIgnoreCase(lost.getCategory(), found.getCategory()))
//	            matchCount++;
//
//	        if (equalsIgnoreCase(lost.getColor(), found.getColor()))
//	            matchCount++;
//
//	        if (equalsIgnoreCase(lost.getBrand(), found.getBrand()))
//	            matchCount++;
//
//	        if (equalsIgnoreCase(lost.getLocation(), found.getLocation()))
//	            matchCount++;
//
//	        if (matchCount >= 2) {
//	            finalMatches.add(found);
//	        }
//	    }
//
//	    return finalMatches.stream()
//	            .map(FoundItemDTO::new)
//	            .collect(Collectors.toList());
//	}
	
//	public List<FoundItemDTO> findMatchingFoundItems(String lostItemId) {
//
//	    LostItem lost = lostItemDao.getLostItemById(lostItemId);
//
//	    List<FoundItem> candidates =
//	        foundItemDao.matchFoundItems(
//	            lost.getLostItemName(),
//	            lost.getCategory(),
//	            lost.getColor(),
//	            lost.getBrand(),
//	            lost.getLocation()
//	        );
//
//	    List<FoundItem> finalMatches = new ArrayList<>();
//
//	    for (FoundItem found : candidates) {
//
//	        // -------- Rule 1: Item name match (TOP PRIORITY) --------
//	        if (isNameMatch(lost.getLostItemName(), found.getFoundItemName())) {
//	            finalMatches.add(found);
//	            continue; // do not check fallback
//	        }
//
//	        // -------- Rule 2: At least ONE other field must match --------
//	        boolean otherFieldMatched =
//	               equalsIgnoreCase(lost.getCategory(), found.getCategory())
//	            || equalsIgnoreCase(lost.getColor(), found.getColor())
//	            || equalsIgnoreCase(lost.getBrand(), found.getBrand())
//	            || equalsIgnoreCase(lost.getLocation(), found.getLocation());
//
//	        if (otherFieldMatched) {
//	            finalMatches.add(found);
//	        }
//	    }
//
//	    return finalMatches.stream()
//	            .map(FoundItemDTO::new)
//	            .collect(Collectors.toList());
//	}
	
//	public List<FoundItemDTO> findMatchingFoundItems(String lostItemId) {
//
//	    LostItem lost = lostItemDao.getLostItemById(lostItemId);
//
//	    List<FoundItem> candidates =
//	        foundItemDao.matchFoundItems(
//	            lost.getLostItemName(),
//	            lost.getCategory(),
//	            lost.getColor(),
//	            lost.getBrand(),
//	            lost.getLocation()
//	        );
//
//	    List<FoundItem> finalMatches = new ArrayList<>();
//
//	    for (FoundItem found : candidates) {
//
//	        // -------- Rule 1: Item name match (TOP PRIORITY) --------
//	        if (isNameMatch(lost.getLostItemName(), found.getFoundItemName())) {
//	            finalMatches.add(found);
//	            continue; // skip Rule-2 check
//	        }
//
//	        // -------- Rule 2: At least TWO other fields must match --------
//	        int matchCount = 0;
//
//	        if (equalsIgnoreCase(lost.getCategory(), found.getCategory()))
//	            matchCount++;
//
//	        if (equalsIgnoreCase(lost.getColor(), found.getColor()))
//	            matchCount++;
//
//	        if (equalsIgnoreCase(lost.getBrand(), found.getBrand()))
//	            matchCount++;
//
//	        if (equalsIgnoreCase(lost.getLocation(), found.getLocation()))
//	            matchCount++;
//
//	        if (matchCount >= 2) {
//	            finalMatches.add(found);
//	        }
//	    }
//
//	    return finalMatches.stream()
//	            .map(FoundItemDTO::new)
//	            .collect(Collectors.toList());
//	}
//
//
//	private boolean isNameMatch(String lostName, String foundName) {
//	    if (lostName == null || foundName == null) return false;
//
//	    lostName = lostName.trim().toLowerCase();
//	    foundName = foundName.trim().toLowerCase();
//
//	    return foundName.contains(lostName) ||
//	           lostName.contains(foundName);
//	}
//
//	private boolean equalsIgnoreCase(String a, String b) {
//	    if (a == null || b == null) return false;
//	    return a.trim().equalsIgnoreCase(b.trim());
//	}

	
	public List<FoundItemDTO> collectFoundItems(LostItem lostItem) {

        // Step 1: get possible candidates
        Set<FoundItem> candidates = new HashSet<>();
        candidates.addAll(repository.searchByKeyword(lostItem.getLostItemName()));
        candidates.addAll(repository.fuzzySearchBySoundex(lostItem.getLostItemName()));
        candidates.addAll(repository.searchByKeyword(lostItem.getCategory()));
        candidates.addAll(repository.searchByKeyword(lostItem.getColor()));
        candidates.addAll(repository.searchByKeyword(lostItem.getBrand()));
        candidates.addAll(repository.searchByKeyword(lostItem.getLocation()));

        List<FoundItemDTO> finalMatches = new ArrayList<>();

        for (FoundItem found : candidates) {

            // ---------- Rule-1: Item name match ----------
            if (fuzzyMatch(lostItem.getLostItemName(), found.getFoundItemName())) {
                finalMatches.add(new FoundItemDTO(found));
                continue;
            }

            // ---------- Rule-2: At least 2 other fields ----------
            int matchCount = 0;

            if (fuzzyMatch(lostItem.getCategory(), found.getCategory())) matchCount++;
            if (fuzzyMatch(lostItem.getColor(), found.getColor())) matchCount++;
            if (fuzzyMatch(lostItem.getBrand(), found.getBrand())) matchCount++;
            if (fuzzyMatch(lostItem.getLocation(), found.getLocation())) matchCount++;

            if (matchCount >= 2) {
                finalMatches.add(new FoundItemDTO(found));
            }
        }

        return finalMatches;
    }

    // ---------- FUZZY MATCH ----------
    private boolean fuzzyMatch(String a, String b) {
        if (a == null || b == null) return false;

        a = a.trim().toLowerCase();
        b = b.trim().toLowerCase();

        if (a.equals(b) || a.contains(b) || b.contains(a)) return true;

        return soundex(a).equals(soundex(b));
    }

    // ---------- SIMPLE SOUNDEX ----------
    private String soundex(String s) {
        char[] x = s.toUpperCase().toCharArray();
        char first = x[0];

        for (int i = 0; i < x.length; i++) {
            switch (x[i]) {
                case 'B': case 'F': case 'P': case 'V': x[i] = '1'; break;
                case 'C': case 'G': case 'J': case 'K':
                case 'Q': case 'S': case 'X': case 'Z': x[i] = '2'; break;
                case 'D': case 'T': x[i] = '3'; break;
                case 'L': x[i] = '4'; break;
                case 'M': case 'N': x[i] = '5'; break;
                case 'R': x[i] = '6'; break;
                default: x[i] = '0';
            }
        }

        String output = "" + first;
        for (int i = 1; i < x.length; i++)
            if (x[i] != '0' && x[i] != x[i - 1])
                output += x[i];

        return (output + "0000").substring(0, 4);
    }

	
}
