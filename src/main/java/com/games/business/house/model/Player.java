package com.games.business.house.model;

import static com.games.business.house.shared.Constants.INITIAL_MONEY_FOR_EACH_PALYER;

public class Player implements HasIdentifier, HasName<String>
{

   private final String identifier;
   private final String name;
   private double money;

   public Player(String identifier, String name)
   {
      this.identifier = identifier;
      this.name = name;
      this.money = INITIAL_MONEY_FOR_EACH_PALYER;
   }

   public void addMoney(double money)
   {
      this.money += money;
   }

   public void deductMoney(double money)
   {
      this.money -= money;
   }

   public double getMoney()
   {
      return money;
   }

   @Override
   public String getName()
   {
      return name;
   }

   @Override
   public String getIdentifier()
   {
      return identifier;
   }

   @Override
   public int hashCode()
   {
      return identifier.hashCode();
   }

}
