package com.parthpatel.landon.web.service;

import com.parthpatel.landon.business.domain.RoomReservation;
import com.parthpatel.landon.business.services.ReservationServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.PrivateKey;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class ReservationServiceController {

    @Autowired
    private ReservationServices reservationServices;

    @RequestMapping(method = RequestMethod.GET,value = "/reservations/{date}")
    public List<RoomReservation> getAllReservationForDate(@PathVariable(value = "date")String dateString)
    {


        return this.reservationServices.getRoomReservationForDate(dateString);
    }
}
