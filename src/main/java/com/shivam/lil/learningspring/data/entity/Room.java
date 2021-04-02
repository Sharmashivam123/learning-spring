package com.shivam.lil.learningspring.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Room")
public class Room {

	@Id
	@Column(name = "ROOM_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long roomId;
	
	@Column(name = "ROOM_NUMBER")
	private String roomNumber;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "BED_INFO")
	private String bedInfo;

	public long getRoomId() {
		return roomId;
	}

	public String getRoomNumber() {
		return roomNumber;
	}

	public String getName() {
		return name;
	}

	public String getBedInfo() {
		return bedInfo;
	}

	public void setRoomId(long roomId) {
		this.roomId = roomId;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setBedInfo(String bedInfo) {
		this.bedInfo = bedInfo;
	}
	
}