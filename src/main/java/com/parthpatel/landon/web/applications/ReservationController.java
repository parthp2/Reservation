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

    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    private ReservationServices reservationServices;

    @RequestMapping(method = RequestMethod.GET)
    public String getReservations(@RequestParam(value="date", required = false) String dateString, Model model)
    {
        Date date=null;
        if(null!=dateString)
        {
            try{
                date=DATE_FORMAT.parse(dateString);
            }
            catch (ParseException pe)
            {
                date=new Date();
            }
        }else
        {
            date= new Date();
        }

        List<RoomReservation> roomReservationsList=this.reservationServices.getRoomReservationForDate(date);
        model.addAttribute("roomReservations",roomReservationsList);
        return "reservations";
    }
}
