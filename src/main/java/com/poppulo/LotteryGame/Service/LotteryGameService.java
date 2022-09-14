package com.poppulo.LotteryGame.Service;

import com.poppulo.LotteryGame.Entity.LotteryTicket;
import com.poppulo.LotteryGame.Exception.InvalidRequestException;
import com.poppulo.LotteryGame.Exception.TicketCheckedException;
import com.poppulo.LotteryGame.Exception.TicketNotFoundException;
import com.poppulo.LotteryGame.Factory.TicketFactory;
import com.poppulo.LotteryGame.Generator.LineGeneratorStratergy;
import com.poppulo.LotteryGame.Repository.LotteryGameRepository;
import com.poppulo.LotteryGame.Results.TicketResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class LotteryGameService {

    @Autowired
    TicketFactory ticketFactory;
    @Autowired
    LotteryGameRepository lotteryGameRepository;

    @Autowired
    LineGeneratorStratergy lineGeneratorStratergy;

    /**
     * {@inheritDoc}
     * @param numberLines
     * @return
     */
    public LotteryTicket createLotteryTicket(Integer numberLines) {
        if(numberLines == null || numberLines <= 0) throw new InvalidRequestException("Invalid Number of Lines");
        LotteryTicket lotteryTicket = ticketFactory.generateTicket(numberLines);
        lotteryGameRepository.save(lotteryTicket);
        return lotteryTicket;
    }


    /**
     * {@inheritDoc}
     * @param numberLines
     * @param ticketId
     * @return
     */
    public LotteryTicket addLineToTicket(Integer numberLines, Long ticketId) {
        if(ticketId == null || ticketId <= 0) throw new InvalidRequestException("Invalid TicketId");
        if(numberLines == null || numberLines <= 0) throw new InvalidRequestException("Invalid Number of Lines");
        Optional<LotteryTicket> lotteryTicket = lotteryGameRepository.findById(ticketId);
        if(lotteryTicket.isEmpty())throw new TicketNotFoundException();
        LotteryTicket lotteryTicketValue = lotteryTicket.get();
        if(!lotteryTicketValue.isChecked()){
            for(int i=0;i<numberLines;i++){
                lotteryTicketValue.addLine(lineGeneratorStratergy.generateLine());
            }
        }else{
            throw new TicketCheckedException();
        }
        lotteryGameRepository.save(lotteryTicketValue);
        return lotteryTicketValue;
    }

    /**
     * {@inheritDoc}
     * @param ticketId
     * @return
     */
    public TicketResult checkTicket(Long ticketId) {
        if(ticketId == null || ticketId <= 0) throw new InvalidRequestException("Invalid TicketId");
        Optional<LotteryTicket> lotteryTicket = lotteryGameRepository.findById(ticketId);
        if(lotteryTicket.isEmpty()) throw new TicketNotFoundException();
        LotteryTicket lotteryTicketValue = lotteryTicket.get();
        lotteryTicketValue.checkTicket();
        lotteryGameRepository.save(lotteryTicketValue);
        TicketResult ticketResult = new TicketResult(lotteryTicketValue);
        return ticketResult;
    }
}
