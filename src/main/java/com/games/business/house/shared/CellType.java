package com.games.business.house.shared;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum CellType
{
   LOTTERY("L")
   {
      @Override
      public <IN, OUT> OUT accept(Visitor<IN, OUT> visitor, IN in)
      {
         return visitor.visitLottery(in);
      }
   },
   JAIL("J")
   {
      @Override
      public <IN, OUT> OUT accept(Visitor<IN, OUT> visitor, IN in)
      {
         return visitor.visitJail(in);
      }
   },
   HOTEL("H")
   {
      @Override
      public <IN, OUT> OUT accept(Visitor<IN, OUT> visitor, IN in)
      {
         return visitor.visitHotel(in);
      }
   },
   EMPTY("E")
   {
      @Override
      public <IN, OUT> OUT accept(Visitor<IN, OUT> visitor, IN in)
      {
         return visitor.visitEmpty(in);
      }
   };

   private String identifier;

   CellType(String identifier)
   {
      this.identifier = identifier;
   }

   public String getIdentifier()
   {
      return identifier;
   }

   public static CellType fromIdentifier(String identifier)
   {
      for (CellType cellType : CellType.values())
      {
         if (cellType.identifier.equals(identifier))
         {
            return cellType;
         }
      }
      throw new IllegalArgumentException(String.format("valid values are %s", getCellTypeIdentifiers()));
   }

   public static List<String> getCellTypeIdentifiers()
   {
      return Arrays.asList(CellType.values()).stream().map(CellType::getIdentifier).collect(Collectors.toList());
   }

   public abstract <IN, OUT> OUT accept(Visitor<IN, OUT> visitor, IN in);

   public interface Visitor<IN, OUT>
   {
      OUT visitLottery(IN in);

      OUT visitJail(IN in);

      OUT visitHotel(IN in);

      OUT visitEmpty(IN in);
   }
}
