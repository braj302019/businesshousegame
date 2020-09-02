package com.games.business.house.model;

import com.games.business.house.shared.HotelType;

public class Hotel
{
   private HotelType hotelType = HotelType.SILVER;
   private Player ownedBy;
   private Player rentBy;

   public HotelType getHotelType()
   {
      return hotelType;
   }

   public void setHotelType(HotelType hotelType)
   {
      this.hotelType = hotelType;
   }

   public Player getOwnedBy()
   {
      return ownedBy;
   }

   public void setOwnedBy(Player ownedBy)
   {
      this.ownedBy = ownedBy;
   }

   public Player getRentBy()
   {
      return rentBy;
   }

   public void setRentBy(Player rentBy)
   {
      this.rentBy = rentBy;
   }

}
