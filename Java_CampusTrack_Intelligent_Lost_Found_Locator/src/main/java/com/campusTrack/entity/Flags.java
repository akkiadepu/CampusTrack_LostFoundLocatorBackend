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
public class Flags {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int FlagsId;
	
//	@Column(name = "lostFound_itemId", nullable = false)
	@ManyToOne
	@JoinColumn(name = "lostFound_itemId")
	private LostFounditems items;
	
//	@Column(name = "reportedUsers_Id", nullable = false)
	@ManyToOne
	@JoinColumn(name = "reportedUsers_Id")
	private Users reportedBy;
	
	@Column(columnDefinition = "Text NOT NULL", nullable = false)
	private String reason;
	
	@Column(name = "reviewed" , nullable = false)
	private boolean reviewed;
	
	@Column(name = "created_At", nullable = false)
	private LocalDateTime created_At;

	public Flags() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Flags(int flagsId, String reason, boolean reviewed) {
//		LostFounditems itemId, Users reportedBy
		super();
		FlagsId = flagsId;
//		this.itemId = itemId;
//		this.reportedBy = reportedBy;
		this.reason = reason;
		this.reviewed = reviewed;
		this.created_At = LocalDateTime.now();
	}



	public int getFlagsId() {
		return FlagsId;
	}

	public void setFlagsId(int flagsId) {
		FlagsId = flagsId;
	}

	public LostFounditems getItemId() {
		return items;
	}

	public void setItemId(LostFounditems itemId) {
		this.items = itemId;
	}

	public Users getReportedBy() {
		return reportedBy;
	}

	public void setReportedBy(Users reportedBy) {
		this.reportedBy = reportedBy;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public boolean isReviewed() {
		return reviewed;
	}

	public void setReviewed(boolean reviewed) {
		this.reviewed = reviewed;
	}

	public LocalDateTime getCreated_At() {
		return created_At;
	}

	public void setCreated_At(LocalDateTime created_At) {
		this.created_At = created_At;
	}

	@Override
	public String toString() {
		return "Flags [FlagsId=" + FlagsId +", reason=" + reason
				+ ", reviewed=" + reviewed + ", created_At=" + created_At + "]";
	}
//	 ", itemId=" + itemId + ", reportedBy=" + reportedBy + 
	
	
	
	
	
	
	
	
	
}
