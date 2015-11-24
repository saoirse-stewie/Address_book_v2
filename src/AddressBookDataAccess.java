// Fig. 8.34: AddressBookDataAccess.java
// Interface that specifies the methods for inserting,
// updating, deleting and finding records.

// Java core packages
import java.sql.*;

public interface AddressBookDataAccess {
      
   // Locate specified person by last name. Return 
   // AddressBookEntry containing information.
   public AddressBookEntry findPerson( String lastName );
   
   // Update information for specified person.
   // Return boolean indicating success or failure.

   public boolean deleteAddress(String address);
   public boolean deleteAddress2(String address2);
   public boolean deleteemailAddress(String emailaddress2);
   public boolean city(String city);
   public boolean county(String county);
   public boolean phone(String phone);
   public boolean deletespecificaddress3(String address3);

   public boolean UpdateAddress(String address,String updateaddress);
   public boolean UpdateAddress2(String address2,String updateaddress2);
   public boolean UpdateemailAddress(String address2, String updateemail);
   public boolean Updatecity(String city, String updatecity);
   public boolean Updatecounty(String county, String updatecounty);
   public boolean Updatephone(String phone,String updatephone);
   public boolean Updatespecificaddress3(String address3,String updateaddress3);

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
