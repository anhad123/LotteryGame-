package com.poppulo.LotteryGame.Generator;

import com.poppulo.LotteryGame.Entity.LotteryLine;

import java.util.Random;

public class RandomLineGenerator implements LineGeneratorStratergy {
    private Random random = new Random(System.currentTimeMillis());

   private int generateLotteryNumber(){
       return random.nextInt(Integer.MAX_VALUE) % 3;
   }

    /**
     * Simple Strategy To Generate Random Lines
     * @return
     */
    @Override
    public LotteryLine generateLine() {
        return new LotteryLine(generateLotteryNumber(),generateLotteryNumber(),generateLotteryNumber());
    }
}
