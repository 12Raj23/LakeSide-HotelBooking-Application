package com.daily.codework.lakeSide_hotel.service;

import java.io.IOException;
import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.daily.codework.lakeSide_hotel.exception.InternalServerException;
import com.daily.codework.lakeSide_hotel.exception.ResourceNotFoundException;
import com.daily.codework.lakeSide_hotel.model.Room;
import com.daily.codework.lakeSide_hotel.repository.RoomRepository;

import lombok.RequiredArgsConstructor;

import java.sql.Blob;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.sql.rowset.serial.SerialBlob;
@RequiredArgsConstructor
@Service

public class RoomService implements IRoomService {
	
	
	private final RoomRepository roomRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

	@Override
	public Room addNewRoom(MultipartFile file, String roomType, BigDecimal roomPrice) throws Exception {
		Room room=new Room();
		room.setRoomType(roomType);
		room.setRoomPrice(roomPrice);
		if(!file.isEmpty()) {
			byte[]photoBytes=file.getBytes();
			Blob photoBlob=new SerialBlob(photoBytes);
			room.setPhoto(photoBlob);
		}
		// TODO Auto-generated method stub
		return roomRepository.save(room);
	}

	@Override
	public List<String> getAllRoomTypes() {
		// TODO Auto-generated method stub
		return roomRepository.findDistinctRoomTypes();
	}

	@Override
	public List<Room> getAllRooms() {
		// TODO Auto-generated method stub
		return roomRepository.findAll();
	}

	@Override
	public byte[] getRoomPhotoByRoomId(Long roomId){
		// TODO Auto-generated method stub
		 Optional<Room>theRoom=roomRepository.findById(roomId);
		 if(theRoom.isEmpty()) {
			 throw new ResourceNotFoundException("sorry,room not found!");
		 }
	Blob photoBlob=theRoom.get().getPhoto();
	if(photoBlob!=null) {
		try {
			return photoBlob.getBytes(1, (int)photoBlob.length());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		return null;
	}

	@Override
	public void deleteRoom(Long roomId) {
		Optional<Room>theRoom=roomRepository.findById(roomId);
		if(theRoom.isPresent()) {
			roomRepository.deleteById(roomId);
		}
		
	}

	@Override
	public Room updateRoom(Long roomId, String roomType, BigDecimal roomPrice, byte[] photoBytes) {
		Room room=roomRepository.findById(roomId)
				.orElseThrow(()->new ResourceNotFoundException("Room not found"));
		if(roomType !=null) room.setRoomType(roomType);
		if(roomPrice!=null)room.setRoomPrice(roomPrice);
		if(photoBytes!=null&&photoBytes.length>0) {
			try {
				room.setPhoto(new SerialBlob(photoBytes));
				
			}catch(SQLException ex) {
				throw new InternalServerException("error updating rooms");
				
			}
		}
		
		return roomRepository.save(room);
	}
	
	@Override
	public Optional<Room> getRoomById(Long roomId) {
		return Optional.of(roomRepository.findById(roomId).get());
		}

	@Override
	public List<Room> getAvailableRooms(LocalDate checkInDate, LocalDate checkOutDate, String roomType) {
		// TODO Auto-generated method stub
		return roomRepository.findAvailableRoomsByDateAndType(checkInDate,checkOutDate,roomType);
	}
}
	
