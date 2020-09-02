package com.games.business.house.controllers;

import java.util.HashMap;
import java.util.Map;

import com.games.business.house.model.Cell;
import com.games.business.house.model.Game;
import com.games.business.house.model.GameMoveData;
import com.games.business.house.model.HotelCell;
import com.games.business.house.model.Player;
import com.games.business.house.shared.CellType;

public class GameController
{
   private final GameStepMover gameStepMover;
   private final Game game;
   private Map<Player, Integer> curerntMoveIndexForEachPlayer = new HashMap<>();
   private final BankController bankController;

   private int currentPlayerIndex;

   public GameController(Game game)
   {
      this.bankController = BankController.getInstance();
      this.gameStepMover = new GameStepMover(bankController);
      this.game = game;
      this.currentPlayerIndex = -1;
   }

   public void startGame()
   {
      // J,H,L,H,E,L,H,L,H,J
      int[] diceOutputs = new int[] { 2, 2, 1, 4, 2, 3, 4, 1, 3, 2, 2, 7, 4, 7, 2, 4, 4, 2, 2, 2, 2 };
      // {2,2,1,4,4,2,4,4,2,2,2,1,4,4,2,4,4,2,2,2,1};
      for (int moves : diceOutputs)
      {
         Player currentPlayer = getNextPlayer();
         System.out.println("------------------------");

         int nextCellIndex = getNextCellIndex(currentPlayer, moves);
         Cell cell = game.getCells().get(nextCellIndex);

         System.out.println(String.format("%s moved %s steps into %s cell", currentPlayer.getName(), moves, cell.getCellType()));

         cell.getCellType().accept(gameStepMover, new GameMoveData(currentPlayer, cell));

         curerntMoveIndexForEachPlayer.put(currentPlayer, nextCellIndex);

         finalResult();
      }
   }

   private void finalResult()
   {
      for (Player player : game.getPlayers())
      {
         double assets = 0;
         for (Cell cell : game.getCells())
         {
            if (cell.getCellType() == CellType.HOTEL && ((HotelCell) cell).isOwenedBy(player.getIdentifier()))
            {
               assets += ((HotelCell) cell).getHotelType().getBuyValue();
            }
         }
         System.out.println(String.format("%s has total money %s and assets of amount: %s", player.getName(), player.getMoney(), assets));
      }
      System.out.println("Balance at bank: " + bankController.getMoney());
   }

   public void stopGame()
   {
      finalResult();
   }

   private Player getNextPlayer()
   {
      currentPlayerIndex++;
      if (currentPlayerIndex >= game.getPlayers().size())
      {
         currentPlayerIndex = 0;
      }
      return game.getPlayers().get(currentPlayerIndex);
   }

   private int getNextCellIndex(Player player, int moves)
   {
      int totalCells = game.getCells().size();
      int nextCellIndex = (curerntMoveIndexForEachPlayer.getOrDefault(player, -1) + moves) % totalCells;
      return nextCellIndex;
   }

}
