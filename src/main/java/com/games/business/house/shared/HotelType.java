package com.games.business.house.shared;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum HotelType
{
   SILVER("S", 200, 50)
   {
      @Override
      public <IN, OUT> OUT accept(Visitor<IN, OUT> visitor, IN in)
      {
         return visitor.visitSilver(in);
      }
   },
   GOLD("G", 300, 150)
   {
      @Override
      public <IN, OUT> OUT accept(Visitor<IN, OUT> visitor, IN in)
      {
         return visitor.visitGold(in);
      }
   },
   PLATINUM("P", 500, 300)
   {
      @Override
      public <IN, OUT> OUT accept(Visitor<IN, OUT> visitor, IN in)
      {
         return visitor.visitPlatinum(in);
      }
   };

   private final String identifier;
   private final double buyValue;
   private final double rentAmount;

   HotelType(String identifier, double buyValue, double rentAmount)
   {
      this.identifier = identifier;
      this.buyValue = buyValue;
      this.rentAmount = rentAmount;
   }

   public String getIdentifier()
   {
      return identifier;
   }

   public double getBuyValue()
   {
      return buyValue;
   }

   public double getRentAmount()
   {
      return rentAmount;
   }

   public static HotelType fromIdentifier(String identifier)
   {
      for (HotelType cellType : HotelType.values())
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
      return Arrays.asList(HotelType.values()).stream().map(HotelType::getIdentifier).collect(Collectors.toList());
   }

   public abstract <IN, OUT> OUT accept(Visitor<IN, OUT> visitor, IN in);

   public interface Visitor<IN, OUT>
   {
      OUT visitSilver(IN in);

      OUT visitGold(IN in);

      OUT visitPlatinum(IN in);
   }
}
