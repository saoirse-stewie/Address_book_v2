// Fig. 8.34: AddressBookDataAccess.java
// Interface that specifies the methods for inserting,
// updating, deleting and finding records.

// Java core packages
import java.sql.*;
import java.util.List;

public interface AddressBookDataAccess {
      
   // Locate specified person by last name. Return 
   // AddressBookEntry containing information.
   public List<AddressBookEntry> findPerson(String lastName );
   
   // Update information for specified person.
   // Return boolean indicating success or failure.

   public boolean deleteAddress(String address,String addressID);
   public boolean deleteAddress2(String address2,String addressID);
   public boolean deleteemailAddress(String emailaddress2,String addressID);
   public boolean city(String city,String addressID);
   public boolean county(String county,String addressID);
   public boolean phone(String phone,String addressID);
   public boolean deletespecificaddress3(String address3,String addressID);

   public boolean UpdateAddress(String address,String updateaddress,String addressID);
   public boolean UpdateAddress2(String address2,String updateaddress2,String addressID);
   public boolean UpdateemailAddress(String address2, String updateemail,String addressID);
   public boolean Updatecity(String city, String updatecity,String addressID);
   public boolean Updatecounty(String county, String updatecounty,String addressID);
   public boolean Updatephone(String phone,String updatephone,String addressID);
   public boolean Updatespecificaddress3(String address3,String updateaddress3,String addressID);

   public boolean AddAddress(String address);
   public boolean AddAddress2(String address2);
   public boolean AddemailAddress(String emailaddress);
   public boolean Addcity(String city);
   public boolean Addcounty(String county);
   public boolean Addphone(String phone);
   public boolean Addspecificaddress3(String address3);



   public boolean savePerson(
      AddressBookEntry person ) throws DataAccessException;

   // Insert a new person. Return boolean indicating 
   // success or failure.
   public boolean newPerson( AddressBookEntry person )
      throws DataAccessException;
      
   // Delete specified person. Return boolean indicating if 
   // success or failure.
   public boolean deletePerson( 
      AddressBookEntry person ) throws DataAccessException;

      
   // close data source connection
   public void close(); 
}  // end interface AddressBookDataAccess


/**************************************************************************
 * (C) Copyright 2001 by Deitel & Associates, Inc. and Prentice Hall.     *
 * All Rights Reserved.                                                   *
 *                                                                        *
 * DISCLAIMER: The authors and publisher of this book have used their     *
 * best efforts in preparing the book. These efforts include the          *
 * development, research, and testing of the theories and programs        *
 * to determine their effectiveness. The authors and publisher make       *
 * no warranty of any kind, expressed or implied, with regard to these    *
 * programs or to the documentation contained in these books. The authors *
 * and publisher shall not be liable in any event for incidental or       *
 * consequential damages in connection with, or arising out of, the       *
 * furnishing, performance, or use of these programs.                     *
 *************************************************************************/
