package com.games.business.house.controllers;

import com.games.business.house.model.GameMoveData;
import com.games.business.house.model.HotelCell;
import com.games.business.house.model.Player;
import com.games.business.house.shared.HotelType;
import com.games.business.house.shared.HotelType.Visitor;

public class HotelVisitor implements Visitor<GameMoveData, Void>
{

   private final BankController bankController;

   public HotelVisitor(BankController bankController)
   {
      this.bankController = bankController;
   }

   @Override
   public Void visitSilver(GameMoveData move)
   {
      HotelCell hotel = (HotelCell) move.getCell();
      Player player = move.getPlayer();

      if (hotel.isOwenedBy(player.getIdentifier()))
      {
         boolean upgraded = upgradeHotelIfPossible(HotelType.GOLD, hotel, player);
         if (!upgraded)
         {
            upgradeHotelIfPossible(HotelType.PLATINUM, hotel, player);
         }
      }
      else if (hotel.isAlreadyOwned())
      {
         hotelOnRentIfPossible(hotel.getHotelType(), hotel, player);
      }
      else
      {
         buyHotelIfPossible(hotel, player);
      }
      return null;
   }

   @Override
   public Void visitGold(GameMoveData move)
   {
      HotelCell hotel = (HotelCell) move.getCell();
      Player player = move.getPlayer();

      if (hotel.isOwenedBy(player.getIdentifier()))
      {
         upgradeHotelIfPossible(HotelType.PLATINUM, hotel, player);
      }
      else
      {
         hotelOnRentIfPossible(HotelType.GOLD, hotel, player);
      }
      return null;
   }

   @Override
   public Void visitPlatinum(GameMoveData move)
   {
      HotelCell hotel = (HotelCell) move.getCell();
      Player player = move.getPlayer();
      if (!hotel.isOwenedBy(player.getIdentifier()))
      {
         hotelOnRentIfPossible(HotelType.PLATINUM, hotel, player);
      }
      return null;
   }

   private boolean upgradeHotelIfPossible(HotelType upgradedHotelType, HotelCell hotel, Player player)
   {
      double moneyInHand = player.getMoney();

      double moneyRequiredToUpgrade = upgradedHotelType.getBuyValue() - hotel.getHotelType().getBuyValue();

      if (moneyInHand >= moneyRequiredToUpgrade)
      {
         hotel.updgradeHotelTo(upgradedHotelType);
         player.deductMoney(moneyRequiredToUpgrade);
         bankController.addMoney(moneyRequiredToUpgrade);

         return true;
      }
      return false;
   }

   private boolean hotelOnRentIfPossible(HotelType upgradedHotelType, HotelCell hotel, Player player)
   {
      double moneyInHand = player.getMoney();

      double moneyRequiredForRent = hotel.getHotelType().getRentAmount();

      if (moneyInHand >= moneyRequiredForRent)
      {
         hotel.setHotelRentBy(player);

         hotel.ownedBy().addMoney(moneyRequiredForRent);
         player.deductMoney(moneyRequiredForRent);

         return true;
      }
      return false;
   }

   private boolean buyHotelIfPossible(HotelCell hotel, Player player)
   {
      double moneyInHand = player.getMoney();

      double moneyRequiredToBuy = hotel.getHotelType().getBuyValue();

      if (moneyInHand >= moneyRequiredToBuy)
      {
         hotel.setHotelOwnedBy(player);

         player.deductMoney(moneyRequiredToBuy);
         bankController.addMoney(moneyRequiredToBuy);

         return true;
      }
      return false;
   }
}
