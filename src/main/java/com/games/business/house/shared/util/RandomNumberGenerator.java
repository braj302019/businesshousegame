package com.games.business.house.shared.util;

import java.util.Random;

public class RandomNumberGenerator
{
   private RandomNumberGenerator()
   {}

   public static int nextRandomNumberInBetween(int min, int max)
   {
      return new Random().nextInt(max) + min;
   }

}
