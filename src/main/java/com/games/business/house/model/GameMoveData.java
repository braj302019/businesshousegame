package com.games.business.house.model;

public class GameMoveData
{
   private final Player player;
   private final Cell cell;

   public GameMoveData(Player player, Cell cell)
   {
      this.player = player;
      this.cell = cell;
   }

   public Player getPlayer()
   {
      return player;
   }

   public Cell getCell()
   {
      return cell;
   }

}
