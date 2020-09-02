package com.games.business.house.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Game
{
   public static class GameFactory
   {
      public static Game createGame(int players)
      {
         return new Game(players);
      }
   }

   private List<Cell> cells = new ArrayList<>();
   private final List<Player> players;

   private Game(int players)
   {
      this.players = IntStream.range(0, players).mapToObj(i -> new Player(String.valueOf(i), "Player-" + (i + 1))).collect(Collectors.toList());
   }

   public List<Cell> getCells()
   {
      return Collections.unmodifiableList(cells);
   }

   public void addCell(Cell cell)
   {
      this.cells.add(cell);
   }

   public List<Player> getPlayers()
   {
      return Collections.unmodifiableList(players);
   }

}
