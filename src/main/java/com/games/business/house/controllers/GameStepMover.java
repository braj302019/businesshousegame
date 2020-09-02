package com.games.business.house.controllers;

import static com.games.business.house.shared.Constants.MONEY_AWARDED_IN_LOTTERY;
import static com.games.business.house.shared.Constants.MONEY_DEDUCTED_IN_JAIL;

import com.games.business.house.model.GameMoveData;
import com.games.business.house.model.HotelCell;
import com.games.business.house.shared.CellType.Visitor;

public class GameStepMover implements Visitor<GameMoveData, Void>
{
   private final BankController bankController;
   private final HotelVisitor hotelVisitor;

   public GameStepMover(BankController bankController)
   {
      this.bankController = bankController;
      this.hotelVisitor = new HotelVisitor(bankController);
   }

   @Override
   public Void visitLottery(GameMoveData move)
   {
      // TODO handle in transaction in single unit of work
      bankController.deductMoney(MONEY_AWARDED_IN_LOTTERY);
      move.getPlayer().addMoney(MONEY_AWARDED_IN_LOTTERY);
      return null;
   }

   @Override
   public Void visitJail(GameMoveData move)
   {
      move.getPlayer().deductMoney(MONEY_DEDUCTED_IN_JAIL);
      bankController.addMoney(MONEY_DEDUCTED_IN_JAIL);
      return null;
   }

   @Override
   public Void visitHotel(GameMoveData move)
   {
      HotelCell cell = (HotelCell) move.getCell();
      cell.getHotelType().accept(hotelVisitor, move);
      return null;
   }

   @Override
   public Void visitEmpty(GameMoveData move)
   {
      // do nothing in this case
      return null;
   }

}
