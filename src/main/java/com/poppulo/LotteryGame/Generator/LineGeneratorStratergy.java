package com.poppulo.LotteryGame.Generator;

import com.poppulo.LotteryGame.Entity.LotteryLine;
import org.springframework.stereotype.Component;

@Component
public interface LineGeneratorStratergy {
	LotteryLine generateLine();
}
