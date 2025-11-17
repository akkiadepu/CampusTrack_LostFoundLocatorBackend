package com.campusTrack.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Matches {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int match_Id;
	
//	@Column(name = "lost_Item", nullable = false)
	@ManyToOne
	@JoinColumn(name = "lost_Item")
	private LostFounditems lost_Item;
	
//	@Column(name = "found_Item", nullable = false)
	@ManyToOne
	@JoinColumn(name = "found_Item")
	private LostFounditems found_Item;
	
	@Column(name = "match_score" , nullable = false)
	private double match_score;
	
	@Column(name = "matchs_status" , nullable = false)
	private String status;
	
	@Column(name = "created_At", nullable = false)
	private LocalDateTime created_At;

	public Matches() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Matches(int match_Id,  double match_score,
			String status) {
//		LostFounditems lost_Item, LostFounditems found_Item,
		super();
		this.match_Id = match_Id;
//		this.lost_Item = lost_Item;
//		this.found_Item = found_Item;
		this.match_score = match_score;
		this.status = status;
		this.created_At = LocalDateTime.now();
	}



	public int getMatch_Id() {
		return match_Id;
	}

	public void setMatch_Id(int match_Id) {
		this.match_Id = match_Id;
	}

	public LostFounditems getLost_Item() {
		return lost_Item;
	}

	public void setLost_Item(LostFounditems lost_Item) {
		this.lost_Item = lost_Item;
	}

	public LostFounditems getFound_Item() {
		return found_Item;
	}

	public void setFound_Item(LostFounditems found_Item) {
		this.found_Item = found_Item;
	}

	public double getMatch_score() {
		return match_score;
	}

	public void setMatch_score(double match_score) {
		this.match_score = match_score;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDateTime getCreated_At() {
		return created_At;
	}

	public void setCreated_At(LocalDateTime created_At) {
		this.created_At = created_At;
	}

	@Override
	public String toString() {
		return "Matches [match_Id=" + match_Id + ""+ ", match_score=" + match_score + ", status=" + status + ", created_At=" + created_At + "]";
	}
	
//	, lost_Item=" + lost_Item + ", found_Item=" + found_Item
	
	
	
	
	
	
}
