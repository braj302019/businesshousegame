package com.games.business.house.controllers;

import com.games.business.house.model.Bank;
import com.games.business.house.shared.TransactionException;

public class BankController
{
   private static class InstanceHolder
   {
      private static final BankController INSTANCE = new BankController(Bank.getInstance());
   }

   public static BankController getInstance()
   {
      return InstanceHolder.INSTANCE;
   }

   private final Bank bank;

   public BankController(Bank bank)
   {
      this.bank = bank;
   }

   public double getMoney()
   {
      return this.bank.getMoney();
   }

   public void addMoney(double money)
   {
      this.bank.addMoney(money);
      System.out.println(money + " added in bank");
   }

   public void deductMoney(double money)
   {
      double moneyInBank = this.bank.getMoney();
      if (moneyInBank < money)
      {
         throw new TransactionException("Not sufficient money in bank");
      }
      this.bank.deductMoney(money);
      System.out.println(money + " deducted from bank");
   }
}
