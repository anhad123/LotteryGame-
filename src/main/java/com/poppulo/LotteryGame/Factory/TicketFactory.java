package com.poppulo.LotteryGame.Factory;

import com.poppulo.LotteryGame.Entity.LotteryLine;
import com.poppulo.LotteryGame.Entity.LotteryTicket;
import com.poppulo.LotteryGame.Generator.LineGeneratorStratergy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TicketFactory {

    @Autowired
    LineGeneratorStratergy lineGeneratorStratergy;


    /**
     * Simple Factory to generate Tickets
     * @param numLines
     * @return
     */
    public LotteryTicket generateTicket(int numLines){
        List<LotteryLine> lines = new ArrayList<LotteryLine>(numLines);
        for(int i=0;i<numLines;i++){
            lines.add(lineGeneratorStratergy.generateLine());
        }
        return new LotteryTicket(lines);
    }


}