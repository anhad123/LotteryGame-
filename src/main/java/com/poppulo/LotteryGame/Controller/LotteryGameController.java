package com.poppulo.LotteryGame.Controller;

import com.poppulo.LotteryGame.Entity.LotteryTicket;
import com.poppulo.LotteryGame.Results.TicketResult;
import com.poppulo.LotteryGame.Service.LotteryGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lottery")
public class LotteryGameController {

    @Autowired
    LotteryGameService lotteryGameService;
    @GetMapping(value = "/ticket/check")
    @ResponseBody
    public  String checkTicket(@RequestParam(value="ticketId") Long ticketId){
        return "abcd";
        //return lotteryGameService.checkTicket(ticketId);
    }

    @PostMapping(value = "/ticket/create")
    @ResponseBody
    public LotteryTicket createLotteryTicket(@RequestParam(value="numberLines") Integer numberLines){
        return lotteryGameService.createLotteryTicket(numberLines);
    }
    @PutMapping(value = "/ticket/update")
    @ResponseBody
    public LotteryTicket addLineToTicket(@RequestParam(value="numberLines") Integer numberLines,@RequestParam(value="ticketId") Long ticketId){
        return lotteryGameService.addLineToTicket(numberLines,ticketId);
    }


}
