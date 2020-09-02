package com.games.business.house;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.games.business.house.controllers.GameController;
import com.games.business.house.model.Cell;
import com.games.business.house.model.Game;
import com.games.business.house.model.Game.GameFactory;
import com.games.business.house.model.Hotel;
import com.games.business.house.model.HotelCell;
import com.games.business.house.shared.CellType;

public class MainApplication
{
   public static void main(String[] args) throws IOException
   {
      System.out.println("Enter no of player and cell types (comma seperated values) in order");
      try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)))
      {
         int players = Integer.parseInt(reader.readLine());
         String cellTypes = reader.readLine();

         GameController gameController = setupGame(players, cellTypes);

         System.out.println("Game started");
         gameController.startGame();

         System.out.println("---------------------------");
         System.out.println("Game stopped");
         gameController.stopGame();
      }
   }

   private static GameController setupGame(int players, String cellTypes)
   {
      String[] cellTypeIndetifiers = cellTypes.split(",");

      Game game = GameFactory.createGame(players);
      for (String cellType : cellTypeIndetifiers)
      {
         CellType type = CellType.fromIdentifier(cellType);
         if (type == CellType.HOTEL)
         {
            game.addCell(new HotelCell(new Hotel()));
         }
         else
         {
            game.addCell(new Cell(type));
         }
      }

      return new GameController(game);
   }
}
