package com.parthpatel.landon.web.applications;

import com.parthpatel.landon.business.domain.RoomReservation;
import com.parthpatel.landon.business.services.ReservationServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value="/reservations")
public class ReservationController {

    @Autowired
    private ReservationServices reservationServices;

    @RequestMapping(method = RequestMethod.GET)
    public String getReservations(@RequestParam(value="date", required = false) String dateString, Model model)
    {

        List<RoomReservation> roomReservationsList=this.reservationServices.getRoomReservationForDate(dateString);
        model.addAttribute("roomReservations",roomReservationsList);
        return "reservations";
    }
}
