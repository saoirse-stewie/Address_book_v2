// Fig. 8.36: CloudscapeDataAccess.java
// An implementation of interface AddressBookDataAccess that 
// performs database operations with PreparedStatements.

// Java core packages
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CloudscapeDataAccess implements AddressBookDataAccess {
      
   // reference to database connection
   private Connection connection;
      
   // reference to prepared statement for locating entry
   private PreparedStatement sqlFind;

   // reference to prepared statement for determining personID
   private PreparedStatement sqlPersonID;

   // references to prepared statements for inserting entry
   private PreparedStatement sqlInsertName;
   private PreparedStatement sqlInsertAddress;
   private PreparedStatement sqlInsertPhone;
   private PreparedStatement sqlInsertEmail;
   
   // references to prepared statements for updating entry
   private PreparedStatement sqlUpdateName;
   private PreparedStatement sqlUpdateAddress;
   private PreparedStatement sqlUpdatePhone;
   private PreparedStatement sqlUpdateEmail;
   
   // references to prepared statements for updating entry
   private PreparedStatement sqlDeleteName;
   private PreparedStatement sqlDeleteAddress;

    private PreparedStatement sqlupdateSpecificAddress;
    private PreparedStatement sqlupdateSpecificAddress2;
    private PreparedStatement sqlupdateSpecificAddress3;
    private PreparedStatement sqlupdateSpecificCounty;
    private PreparedStatement sqlupdateSpecificCity;
    private PreparedStatement sqlupdateSpecificemailAddress;
    private PreparedStatement sqlupdateSpecificPhone;

    private PreparedStatement sqlDeleteSpecificAddress;
    private PreparedStatement sqlDeleteSpecificAddress2;
    private PreparedStatement sqlDeleteSpecificAddress3;
    private PreparedStatement sqlDeleteSpecificCounty;
    private PreparedStatement sqlDeleteSpecificCity;
    private PreparedStatement sqlDeleteSpecificemailAddress;
    private PreparedStatement sqlDeleteSpecificPhone;

    private PreparedStatement sqlInsertSpecificAddress;
    private PreparedStatement sqlInsertSpecificAddress2;
    private PreparedStatement sqlInsertSpecificAddress3;
    private PreparedStatement sqlInsertSpecificCounty;
    private PreparedStatement sqlInsertSpecificCity;
    private PreparedStatement sqlInsertSpecificemailAddress;
    private PreparedStatement sqlInsertSpecificPhone;

    private PreparedStatement sqlDeletePhone;
    private PreparedStatement sqlDeleteEmail;


   // set up PreparedStatements to access database
   public CloudscapeDataAccess() throws Exception
   {
      // connect to addressbook database
      connect();

      // locate person
       sqlFind = connection.prepareStatement(
        "SELECT names.personID, firstName, lastName, " +
          "addressID, address1, address2, city, county, address3, " + " phoneID, phoneNumber, emailID, " +
           "emailAddress " + "FROM names, addresses, phoneNumbers, emailAddresses " +
       "WHERE lastName = ? AND " +
           "names.personID = addresses.personID AND " +
         "names.personID = phoneNumbers.personID AND " +
          "names.personID = emailAddresses.personID" );

     //  sqlFind = connection.prepareStatement(
       //        "select lastName , count(*) c from names group by lastName = ? " +
              //         "having  c > 1");
//

      // Obtain personID for last person inserted in database.
      // [This is a Cloudscape-specific database operation.]
      //sqlPersonID = connection.prepareStatement(
        // "VALUES ConnectionInfo.lastAutoincrementValue( " +
          //  "'APP', 'NAMES', 'PERSONID')" );

      sqlPersonID = connection.prepareStatement("select last_insert_id()");

      // Insert first and last names in table names. 
      // For referential integrity, this must be performed 
      // before sqlInsertAddress, sqlInsertPhone and
      // sqlInsertEmail.
      sqlInsertName = connection.prepareStatement(
         "INSERT INTO names ( firstName, lastName ) " +
         "VALUES ( ? , ? )" );


      //insert address in table addresses
      sqlInsertAddress = connection.prepareStatement
           ("INSERT INTO addresses ( personID, address1, " +
            "address2, city, county , address3 ) " +
        "VALUES ( ? , ? , ?, ? , ? , ? )" );


     // sqlInsertAddress.executeUpdate();

      // insert phone number in table phoneNumbers
      sqlInsertPhone = connection.prepareStatement(
         "INSERT INTO phoneNumbers " +
            "( personID, phoneNumber) " +
         "VALUES ( ? , ? )" );

      // insert email in table emailAddresses
      sqlInsertEmail = connection.prepareStatement(
         "INSERT INTO emailAddresses " +
            "( personID, emailAddress ) " +
         "VALUES ( ? , ? )" );

      // update first and last names in table names
      sqlUpdateName = connection.prepareStatement(
         "UPDATE names SET firstName = ?, lastName = ? " +
         "WHERE personID = ?" );

      // update address in table addresses
      sqlUpdateAddress = connection.prepareStatement(
         "UPDATE addresses SET address1 = ?, address2 = ?, " +
            "city = ?, county = ? , address3 = ? " +
         "WHERE addressID = ?" );

      // update phone number in table phoneNumbers
      sqlUpdatePhone = connection.prepareStatement(
         "UPDATE phoneNumbers SET phoneNumber = ? " +
         "WHERE phoneID = ?" );

      // update email in table emailAddresses
      sqlUpdateEmail = connection.prepareStatement(
         "UPDATE emailAddresses SET emailAddress = ? " +
         "WHERE emailID = ?" );

      // Delete row from table names. This must be executed 
      // after sqlDeleteAddress, sqlDeletePhone and
      // sqlDeleteEmail, because of referential integrity.
      sqlDeleteName = connection.prepareStatement(
         "DELETE FROM names WHERE personID = ?" );

      // delete address from table addresses
      sqlDeleteAddress = connection.prepareStatement(
         "DELETE FROM addresses WHERE personID = ?" );

       sqlDeleteSpecificAddress =connection.prepareStatement(
               "update addresses set address1 = null WHERE address1 = ? AND addressID =?" );
       sqlDeleteSpecificAddress2 =connection.prepareStatement(
               "update addresses set address2 = null WHERE address2 = ? AND addressID =?" );
       sqlDeleteSpecificAddress3 =connection.prepareStatement(
               "update addresses set address3 = null WHERE address3 = ? AND addressID =?" );
       sqlDeleteSpecificCity =connection.prepareStatement(
               "update addresses set city = null WHERE city = ? and personID = ?" );
       sqlDeleteSpecificemailAddress = connection.prepareStatement(
               "update emailAddresses set emailAddress = null WHERE emailAddress = ? AND addressID =? " );
       sqlDeleteSpecificCounty = connection.prepareStatement(
               "update addresses set county = null WHERE county = ? AND addressID =?" );
       sqlDeleteSpecificPhone = connection.prepareStatement(
               "update phonenumbers set phoneNumber = null WHERE phoneNumber = ? AND phoneID =?" );

       sqlupdateSpecificAddress =connection.prepareStatement(
               "update addresses set address1 = ? WHERE address1 = ? AND addressID = ?" );
       sqlupdateSpecificAddress2 =connection.prepareStatement(
               "update addresses set address2 = ? WHERE address2 = ? AND addressID = ?" );
       sqlupdateSpecificAddress3 =connection.prepareStatement(
               "update addresses set address3 = ? WHERE address3 = ? AND addressID = ?" );
       sqlupdateSpecificCity =connection.prepareStatement(
               "update addresses set city = ? WHERE city = ? AND addressID = ?" );
       sqlupdateSpecificemailAddress = connection.prepareStatement(
               "update emailAddresses set emailAddress = ? WHERE emailAddress = ? AND addressID = ?" );
       sqlupdateSpecificCounty = connection.prepareStatement(
               "update addresses set county = ? WHERE county = ? AND addressID = ?" );
       sqlupdateSpecificPhone = connection.prepareStatement(
               "update phonenumbers set phoneNumber = ? WHERE phoneNumber = ? AND phoneID = ?" );



       // delete phone number from table phoneNumbers
      sqlDeletePhone = connection.prepareStatement(
         "DELETE FROM phoneNumbers WHERE personID = ?" );

      // delete email address from table emailAddresses
      sqlDeleteEmail = connection.prepareStatement(
         "DELETE FROM emailAddresses WHERE personID = ?" );
   }  // end CloudscapeDataAccess constructor
   
   // Obtain a connection to addressbook database. Method may 
   // may throw ClassNotFoundException or SQLException. If so, 
   // exception is passed via this class's constructor back to 
   // the AddressBook application so the application can display
   // an error message and terminate.
   private void connect() throws Exception
   {
      // Cloudscape database driver class name
      String driver = "com.mysql.jdbc.Driver";
     
      // URL to connect to addressbook database
      String url = "jdbc:mysql://localhost:3306/addressbook?user=root&password=root";
      
      // load database driver class
      Class.forName( driver );

      // connect to database
      connection = DriverManager.getConnection( url);

      // Require manual commit for transactions. This enables 
      // the program to rollback transactions that do not 
      // complete and commit transactions that complete properly.
      connection.setAutoCommit( false );      
   }
   
   // Locate specified person. Method returns AddressBookEntry
   // containing information.
   public List<AddressBookEntry> findPerson(String lastName )
   {
       List<AddressBookEntry> find= new ArrayList();
      try {


         // set query parameter and execute query
         sqlFind.setString( 1, lastName );
         ResultSet resultSet = sqlFind.executeQuery();

         // if no records found, return immediately
         while ( resultSet.next() ) {

             // create new AddressBookEntry
             AddressBookEntry person = new AddressBookEntry(
                     resultSet.getInt(1));

             // set AddressBookEntry properties
             person.setFirstName(resultSet.getString(2));
             person.setLastName(resultSet.getString(3));

             person.setAddressID(resultSet.getInt(4));
             person.setAddress1(resultSet.getString(5));
             person.setAddress2(resultSet.getString(6));
             person.setCity(resultSet.getString(7));
             person.setState(resultSet.getString(8));
             person.setAddress3(resultSet.getString(9));
             //person.setZipcode( resultSet.getString( 9 ) );

             person.setPhoneID(resultSet.getInt(10));
             person.setPhoneNumber(resultSet.getString(11));

             person.setEmailID(resultSet.getInt(12));
             person.setEmailAddress(resultSet.getString(13));


             find.add(person);
         }
         // return AddressBookEntry
         return find;
      }

      // catch SQLException
      catch ( SQLException sqlException ) {
         return null;
      }
   }  // end method findPerson
   
   // Update an entry. Method returns boolean indicating 
   // success or failure.
   public boolean savePerson( AddressBookEntry person )
      throws DataAccessException
   {
      // update person in database
      try {
         int result;
         
         // update names table
         sqlUpdateName.setString( 1, person.getFirstName() );
         sqlUpdateName.setString( 2, person.getLastName() );
         sqlUpdateName.setInt( 3, person.getPersonID() );
         result = sqlUpdateName.executeUpdate();

         // if update fails, rollback and discontinue 
         if ( result == 0 ) {
            connection.rollback(); // rollback update
            return false;          // update unsuccessful
         }      
         
         // update addresses table
         sqlUpdateAddress.setString( 1, person.getAddress1() );
         sqlUpdateAddress.setString( 2, person.getAddress2() );
         sqlUpdateAddress.setString( 3, person.getCity() );
         sqlUpdateAddress.setString( 4, person.getState() );
         sqlUpdateAddress.setString( 5, person.getAddress3() );
         sqlUpdateAddress.setInt( 6, person.getAddressID() );
         result = sqlUpdateAddress.executeUpdate();
         
         // if update fails, rollback and discontinue 
         if ( result == 0 ) {
            connection.rollback(); // rollback update
            return false;          // update unsuccessful
         }      
         
         // update phoneNumbers table
         sqlUpdatePhone.setString( 1, person.getPhoneNumber() );
         sqlUpdatePhone.setInt( 2, person.getPhoneID() );
         result = sqlUpdatePhone.executeUpdate();
         
         // if update fails, rollback and discontinue 
         if ( result == 0 ) {
            connection.rollback(); // rollback update
            return false;          // update unsuccessful
         }      
         
         // update emailAddresses table
         sqlUpdateEmail.setString( 1, person.getEmailAddress() );
         sqlUpdateEmail.setInt( 2, person.getEmailID() );
         result = sqlUpdateEmail.executeUpdate();

         // if update fails, rollback and discontinue 
         if ( result == 0 ) {
            connection.rollback(); // rollback update
            return false;          // update unsuccessful
         }      
         
         connection.commit();   // commit update
         return true;           // update successful
      }  // end try
      
      // detect problems updating database
      catch ( SQLException sqlException ) {
      
         // rollback transaction
         try {
            connection.rollback(); // rollback update
            return false;          // update unsuccessful
         }
         
         // handle exception rolling back transaction
         catch ( SQLException exception ) {
            throw new DataAccessException( exception );
         }
      }
   }  // end method savePerson

   // Insert new entry. Method returns boolean indicating 
   // success or failure.
   public boolean deleteAddress(String address,String addressID)  {
       int result;
       try {
           sqlDeleteSpecificAddress.setString( 1, address );
           sqlDeleteSpecificAddress.setString( 2, addressID );



           result = sqlDeleteSpecificAddress.executeUpdate();
       if ( result == 0 ) {
           connection.rollback(); // rollback delete
           return false;          // delete unsuccessful
       }
       connection.commit();   // commit delete

       } catch (SQLException e) {
           e.printStackTrace();
       }
       return true;
   }

    public boolean deleteAddress2(String address2,String addressID)  {
        int result;
        try {
            sqlDeleteSpecificAddress2.setString( 1, address2 );
            sqlDeleteSpecificAddress2.setString( 2, addressID );


            result = sqlDeleteSpecificAddress2.executeUpdate();
            if ( result == 0 ) {
                connection.rollback(); // rollback delete
                return false;          // delete unsuccessful
            }
            connection.commit();   // commit delete

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
    public boolean deleteemailAddress(String emailaddress,String addressID)  {
        int result;
        try {
            sqlDeleteSpecificemailAddress.setString( 1, emailaddress );
            sqlDeleteSpecificemailAddress.setString( 2, addressID );


            result = sqlDeleteSpecificemailAddress.executeUpdate();
            if ( result == 0 ) {
                connection.rollback(); // rollback delete
                return false;          // delete unsuccessful
            }
            connection.commit();   // commit delete

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
    public boolean city(String city, String addressID)  {
        int result;
        try {
            sqlDeleteSpecificCity.setString( 1, city );
            sqlDeleteSpecificCity.setString( 2, addressID );


            result = sqlDeleteSpecificCity.executeUpdate();
            if ( result == 0 ) {
                connection.rollback(); // rollback delete
                return false;          // delete unsuccessful
            }
            connection.commit();   // commit delete

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
    public boolean county(String county,String addressID)  {
        int result;
        try {
            sqlDeleteSpecificCounty.setString( 1, county );
            sqlDeleteSpecificCounty.setString( 2, addressID );


            result = sqlDeleteSpecificCounty.executeUpdate();
            if ( result == 0 ) {
                connection.rollback(); // rollback delete
                return false;          // delete unsuccessful
            }
            connection.commit();   // commit delete

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
    public boolean deletespecificaddress3(String address3,String addressID)  {
        int result;
        try {
            sqlDeleteSpecificAddress3.setString( 1, address3 );
            sqlDeleteSpecificAddress3.setString( 2, addressID );

            result = sqlDeleteSpecificAddress3.executeUpdate();
            if ( result == 0 ) {
                connection.rollback(); // rollback delete
                return false;          // delete unsuccessful
            }
            connection.commit();   // commit delete

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
    public boolean phone(String phone,String addressID)  {
        int result;
        try {
            sqlDeleteSpecificPhone.setString( 1, phone );
            sqlDeleteSpecificPhone.setString( 2, addressID );


            result = sqlDeleteSpecificPhone.executeUpdate();
            if ( result == 0 ) {
                connection.rollback(); // rollback delete
                return false;          // delete unsuccessful
            }
            connection.commit();   // commit delete

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
    public boolean UpdateAddress(String address, String updateaddress,String addressID)
    {
        int result;
        try {
            sqlupdateSpecificAddress.setString( 1, address );
            sqlupdateSpecificAddress.setString( 2, updateaddress );
            sqlupdateSpecificAddress.setString( 3, addressID );

            result = sqlupdateSpecificAddress.executeUpdate();

            if ( result == 0 ) {
                connection.rollback(); // rollback delete
                return false;          // delete unsuccessful
            }
            connection.commit();   // commit delete

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
    public boolean UpdateAddress2(String address2, String updateaddress2,String addressID)
    {
        int result;
        try {
            sqlupdateSpecificAddress2.setString( 1, address2 );
            sqlupdateSpecificAddress2.setString( 2, updateaddress2 );
            sqlupdateSpecificAddress2.setString( 3, addressID );

            result = sqlupdateSpecificAddress2.executeUpdate();
            if ( result == 0 ) {
                connection.rollback(); // rollback delete
                return false;          // delete unsuccessful
            }
            connection.commit();   // commit delete

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
    public boolean UpdateemailAddress(String emailaddress, String updateemail,String addressID)
    {
        int result;
        try {
            sqlupdateSpecificemailAddress.setString( 1, emailaddress );
            sqlupdateSpecificemailAddress.setString( 2, updateemail );
            sqlupdateSpecificemailAddress.setString( 3, addressID );

            result = sqlupdateSpecificemailAddress.executeUpdate();
            if ( result == 0 ) {
                connection.rollback(); // rollback delete
                return false;          // delete unsuccessful
            }
            connection.commit();   // commit delete

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
    public boolean Updatecity(String city, String updatecity,String addressID)
    {
        int result;
        try {
            sqlupdateSpecificCity.setString( 1, city );
            sqlupdateSpecificCity.setString( 2, updatecity );
            sqlupdateSpecificCity.setString( 3, addressID );


            result = sqlupdateSpecificCity.executeUpdate();
            if ( result == 0 ) {
                connection.rollback(); // rollback delete
                return false;          // delete unsuccessful
            }
            connection.commit();   // commit delete

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
    public boolean Updatecounty(String county, String updatecounty, String addressID)
    {
        int result;
        try {
            sqlupdateSpecificCounty.setString( 1, county );
            sqlupdateSpecificCounty.setString( 2,updatecounty );
            sqlupdateSpecificCounty.setString( 3, addressID );

            result = sqlupdateSpecificCounty.executeUpdate();
            if ( result == 0 ) {
                connection.rollback(); // rollback delete
                return false;          // delete unsuccessful
            }
            connection.commit();   // commit delete

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
    public boolean Updatephone(String phone, String updatephone,String addressID)
    {
        int result;
        try {
            sqlUpdatePhone.setString( 1, phone );
            sqlUpdatePhone.setString( 2, updatephone );

            result = sqlupdateSpecificPhone.executeUpdate();
            if ( result == 0 ) {
                connection.rollback(); // rollback delete
                return false;          // delete unsuccessful
            }
            connection.commit();   // commit delete

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
    public boolean Updatespecificaddress3(String address3, String updateaddress3,String addressID)
    {
        int result;
        try {
            sqlupdateSpecificAddress3.setString( 1, address3 );
            sqlupdateSpecificAddress3.setString( 2, updateaddress3 );
            sqlupdateSpecificAddress.setString( 3, addressID );

            result = sqlupdateSpecificAddress3.executeUpdate();
            if ( result == 0 ) {
                connection.rollback(); // rollback delete
                return false;          // delete unsuccessful
            }
            connection.commit();   // commit delete

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }


    public boolean AddAddress(String address)
    {
        int result;
        try {

            ResultSet resultPersonID = sqlPersonID.executeQuery();
            int personID =  resultPersonID.getInt( 1 );
            sqlInsertSpecificAddress.setInt( 1, personID );
            sqlInsertSpecificAddress.setString( 2, address );


            result = sqlInsertSpecificAddress.executeUpdate();

            if ( result == 0 ) {
                connection.rollback(); // rollback delete
                return false;          // delete unsuccessful
            }
            connection.commit();   // commit delete

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
    public boolean AddAddress2(String address2)
    {
        int result;
        try {
            ResultSet resultPersonID = sqlPersonID.executeQuery();
            int personID =  resultPersonID.getInt( 1 );
            sqlInsertSpecificAddress2.setInt( 1, personID );
            sqlInsertSpecificAddress2.setString( 2, address2 );


            result = sqlInsertSpecificAddress2.executeUpdate();
            if ( result == 0 ) {
                connection.rollback(); // rollback delete
                return false;          // delete unsuccessful
            }
            connection.commit();   // commit delete

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
    public boolean AddemailAddress(String emailaddress)
    {
        int result;
        try {
            ResultSet resultPersonID = sqlPersonID.executeQuery();
           int personID =  resultPersonID.getInt( 1 );
            sqlInsertSpecificemailAddress.setInt( 1, personID );
            sqlInsertSpecificemailAddress.setString( 2, emailaddress );
            result = sqlInsertSpecificemailAddress.executeUpdate();
            if ( result == 0 ) {
                connection.rollback(); // rollback delete
                return false;          // delete unsuccessful
            }
            connection.commit();   // commit delete

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
    public boolean Addcity(String city)
    {
        int result;
        try {
            ResultSet resultPersonID = sqlPersonID.executeQuery();
           int personID =  resultPersonID.getInt( 1 );
            sqlInsertSpecificCity.setInt( 1, personID );
            sqlInsertSpecificCity.setString( 2, city );

            result = sqlInsertSpecificCity.executeUpdate();


            if ( result == 0 ) {
                connection.rollback(); // rollback delete
                return false;          // delete unsuccessful
            }
            connection.commit();   // commit delete

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
    public boolean Addcounty(String county)
    {
        int result;
        try {
            ResultSet resultPersonID = sqlPersonID.executeQuery();
           int personID =  resultPersonID.getInt( 1 );
            sqlInsertSpecificCounty.setInt( 1, personID );
            sqlInsertSpecificCounty.setString( 2, county );

            result = sqlInsertSpecificCounty.executeUpdate();

            if ( result == 0 ) {
                connection.rollback(); // rollback delete
                return false;          // delete unsuccessful
            }
            connection.commit();   // commit delete

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
    public boolean Addphone(String phone)
    {
        int result;
        try {
            ResultSet resultPersonID = sqlPersonID.executeQuery();
            int personID =  resultPersonID.getInt( 1 );
            sqlInsertSpecificPhone.setInt( 1, personID );
            sqlInsertSpecificPhone.setString( 2, phone );

            result = sqlInsertSpecificPhone.executeUpdate();

            if ( result == 0 ) {
                connection.rollback(); // rollback delete
                return false;          // delete unsuccessful
            }
            connection.commit();   // commit delete

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
    public boolean Addspecificaddress3(String address3)
    {
        int result;
        try {
            ResultSet resultPersonID = sqlPersonID.executeQuery();
            int personID =  resultPersonID.getInt( 1 );
            sqlInsertSpecificPhone.setInt( 1, personID );
            sqlupdateSpecificAddress3.setString( 1, address3 );

            result = sqlupdateSpecificAddress3.executeUpdate();
            if ( result == 0 ) {
                connection.rollback(); // rollback delete
                return false;          // delete unsuccessful
            }
            connection.commit();   // commit delete

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean newPerson( AddressBookEntry person)
      throws DataAccessException
   {
      // insert person in database
      try {
         int result;

         // insert first and last name in names table
         sqlInsertName.setString( 1, person.getFirstName() );
         sqlInsertName.setString( 2, person.getLastName() );
         result = sqlInsertName.executeUpdate();

         // if insert fails, rollback and discontinue 
         if ( result == 0 ) {
            connection.rollback(); // rollback insert
            return false;          // insert unsuccessful
         }      
         
         // determine new personID
         ResultSet resultPersonID = sqlPersonID.executeQuery();
         
         if ( resultPersonID.next() ) {
            int personID =  resultPersonID.getInt( 1 );
         
            // insert address in addresses table
            sqlInsertAddress.setInt( 1, personID );
            sqlInsertAddress.setString( 2, 
               person.getAddress1() );
            sqlInsertAddress.setString( 3, 
               person.getAddress2() );
            sqlInsertAddress.setString( 4, 
               person.getCity() );
            sqlInsertAddress.setString( 5, 
               person.getState() );
            sqlInsertAddress.setString( 6,
                    person.getAddress3() );
            result = sqlInsertAddress.executeUpdate();
         
            // if insert fails, rollback and discontinue 
            if ( result == 0 ) {
               connection.rollback(); // rollback insert
               return false;          // insert unsuccessful
            }      

            // insert phone number in phoneNumbers table
            sqlInsertPhone.setInt( 1, personID );
            sqlInsertPhone.setString( 2, 
               person.getPhoneNumber() );
            result = sqlInsertPhone.executeUpdate();
         
            // if insert fails, rollback and discontinue 
            if ( result == 0 ) {
               connection.rollback(); // rollback insert
               return false;          // insert unsuccessful
            }      

            // insert email address in emailAddresses table
            sqlInsertEmail.setInt( 1, personID );
            sqlInsertEmail.setString( 2, 
               person.getEmailAddress() );
            result = sqlInsertEmail.executeUpdate();

            // if insert fails, rollback and discontinue 
            if ( result == 0 ) {
               connection.rollback(); // rollback insert
               return false;          // insert unsuccessful
            }      
       
            connection.commit();   // commit insert
            return true;           // insert successful
         }
         
         else 
            return false;
      }  // end try
      
      // detect problems updating database
      catch ( SQLException sqlException ) {
         // rollback transaction
         try {
            connection.rollback(); // rollback update
            return false;          // update unsuccessful
         }
         
         // handle exception rolling back transaction
         catch ( SQLException exception ) {
            throw new DataAccessException( exception );
         }
      }
   }  // end method newPerson
      
   // Delete an entry. Method returns boolean indicating 
   // success or failure.

    public boolean deletePerson( AddressBookEntry person )
      throws DataAccessException
   {
      // delete a person from database
      try {
         int result;
                  
         // delete address from addresses table
         sqlDeleteAddress.setInt( 1, person.getPersonID() );
         result = sqlDeleteAddress.executeUpdate();

         // if delete fails, rollback and discontinue 
         if ( result == 0 ) {
            connection.rollback(); // rollback delete
            return false;          // delete unsuccessful
         }      

         // delete phone number from phoneNumbers table
         sqlDeletePhone.setInt( 1, person.getPersonID() );
         result = sqlDeletePhone.executeUpdate();
         
         // if delete fails, rollback and discontinue 
         if ( result == 0 ) {
            connection.rollback(); // rollback delete
            return false;          // delete unsuccessful
         }      

         // delete email address from emailAddresses table
         sqlDeleteEmail.setInt( 1, person.getPersonID() );
         result = sqlDeleteEmail.executeUpdate();

         // if delete fails, rollback and discontinue 
         if ( result == 0 ) {
            connection.rollback(); // rollback delete
            return false;          // delete unsuccessful
         }      

         // delete name from names table
         sqlDeleteName.setInt( 1, person.getPersonID() );
         result = sqlDeleteName.executeUpdate();

         // if delete fails, rollback and discontinue 
         if ( result == 0 ) {
            connection.rollback(); // rollback delete
            return false;          // delete unsuccessful
         }      

         connection.commit();   // commit delete
         return true;           // delete successful
      }  // end try
      
      // detect problems updating database
      catch ( SQLException sqlException ) {
         // rollback transaction
         try {
            connection.rollback(); // rollback update
            return false;          // update unsuccessful
         }
         
         // handle exception rolling back transaction
         catch ( SQLException exception ) {
            throw new DataAccessException( exception );
         }
      }
   }  // end method deletePerson

   // method to close statements and database connection
   public void close()
   {
      // close database connection
      try {
         sqlFind.close();
         sqlPersonID.close();
         sqlInsertName.close();
         sqlInsertAddress.close();
         sqlInsertPhone.close();
         sqlInsertEmail.close();
         sqlUpdateName.close();
         sqlUpdateAddress.close();
         sqlUpdatePhone.close();
         sqlUpdateEmail.close();
         sqlDeleteName.close();
         sqlDeleteAddress.close();
         sqlDeletePhone.close();
         sqlDeleteEmail.close();         
         connection.close();
      }  // end try
      
      // detect problems closing statements and connection
      catch ( SQLException sqlException ) {
         sqlException.printStackTrace();  
      }   
   }  // end method close

   // Method to clean up database connection. Provided in case
   // CloudscapeDataAccess object is garbage collected.
   protected void finalize()
   {
      close(); 
   }
}  // end class CloudscapeDataAccess


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
