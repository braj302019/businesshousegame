package com.games.business.house.model;

import com.games.business.house.shared.CellType;

public class Cell
{
   private final CellType cellType;

   public Cell(CellType cellType)
   {
      this.cellType = cellType;
   }

   public CellType getCellType()
   {
      return cellType;
   }

}
