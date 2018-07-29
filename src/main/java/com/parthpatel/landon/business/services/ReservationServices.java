package com.parthpatel.landon.business.services;


import com.parthpatel.landon.business.domain.RoomReservation;
import com.parthpatel.landon.data.entity.Guest;
import com.parthpatel.landon.data.entity.Reservation;
import com.parthpatel.landon.data.entity.Room;
import com.parthpatel.landon.data.repository.GuestRepository;
import com.parthpatel.landon.data.repository.ReservationRepository;
import com.parthpatel.landon.data.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class ReservationServices {

    private RoomRepository roomRepository;
    private GuestRepository guestRepository;
    private ReservationRepository reservationRepository;

    @Autowired
    public ReservationServices(RoomRepository roomRepository, GuestRepository guestRepository, ReservationRepository reservationRepository) {
        this.roomRepository = roomRepository;
        this.guestRepository = guestRepository;
        this.reservationRepository = reservationRepository;
    }

    public List<RoomReservation> getRoomReservationForDate(Date date)
    {
        Iterable<Room> rooms=this.roomRepository.findAll();

        Map<Long, RoomReservation> roomReservationMap= new HashMap<>();

        rooms.forEach(room->{
            RoomReservation roomReservation=new RoomReservation();
            roomReservation.setRoomId(room.getId());
            roomReservation.setRoomNumber(room.getNumber());
            roomReservation.setRoomName(room.getName());
            roomReservationMap.put(room.getId(),roomReservation);
        });

        Iterable<Reservation> reservations=this.reservationRepository.findByDate(new java.sql.Date(date.getTime()));

        if(null!=reservations)
            reservations.forEach( reservation -> {
              Guest guest=this.guestRepository.findOne(reservation.getGuestId());

              if(null!=guest) {
                  RoomReservation roomReservation = roomReservationMap.get(reservation.getId());
                  roomReservation.setFirstName(guest.getFirstName());
                  roomReservation.setLastName(guest.getLastName());
                  roomReservation.setGuestId(guest.getId());
              }
            }
            );
        List<RoomReservation> roomReservations=new ArrayList<>();

        for(Long roomId:roomReservationMap.keySet())
        {
            roomReservations.add(roomReservationMap.get(roomId));
        }

        return roomReservations;
    }



}
