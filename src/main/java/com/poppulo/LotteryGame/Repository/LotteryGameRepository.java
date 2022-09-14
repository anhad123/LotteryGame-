package com.poppulo.LotteryGame.Repository;

import com.poppulo.LotteryGame.Entity.LotteryTicket;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LotteryGameRepository extends CrudRepository<LotteryTicket,Long> {

}
