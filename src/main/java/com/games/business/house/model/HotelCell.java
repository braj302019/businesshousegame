package com.games.business.house.model;

import com.games.business.house.shared.CellType;
import com.games.business.house.shared.HotelType;

public class HotelCell extends Cell
{

   private final Hotel hotel;

   public HotelCell(Hotel hotel)
   {
      super(CellType.HOTEL);
      this.hotel = hotel;
   }

   public HotelType getHotelType()
   {
      return hotel.getHotelType();
   }

   public boolean isAlreadyOwned()
   {
      return hotel.getOwnedBy() != null;
   }

   public Player ownedBy()
   {
      return hotel.getOwnedBy();
   }

   public boolean isOwenedBy(String idetifier)
   {
      HasIdentifier ownedBy = hotel.getOwnedBy();
      return ownedBy != null ? ownedBy.getIdentifier().equals(idetifier) : false;
   }

   public void updgradeHotelTo(HotelType hotelType)
   {
      hotel.setHotelType(hotelType);
   }

   public void setHotelRentBy(Player rentBy)
   {
      hotel.setRentBy(rentBy);
   }

   public void setHotelOwnedBy(Player ownedBy)
   {
      hotel.setOwnedBy(ownedBy);
   }
}
