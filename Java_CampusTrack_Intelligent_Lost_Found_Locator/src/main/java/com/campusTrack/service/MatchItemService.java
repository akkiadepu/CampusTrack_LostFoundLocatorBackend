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
