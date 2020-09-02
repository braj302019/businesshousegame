package com.games.business.house.model;

import com.games.business.house.shared.Constants;

public class Bank
{
   private static class InstanceHolder
   {
      private static final Bank BANK = new Bank();
   }

   public static Bank getInstance()
   {
      return InstanceHolder.BANK;
   }

   private double money = Constants.INITIAL_MONEY_IN_BANK;

   private Bank()
   {}

   public double getMoney()
   {
      return money;
   }

   public void addMoney(double money)
   {
      this.money += money;
   }

   public void deductMoney(double money)
   {
      this.money -= money;
   }
}
