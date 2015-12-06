// Fig. 8.37: AddressBookEntryFrame.java
// A subclass of JInternalFrame customized to display and
// an AddressBookEntry or set an AddressBookEntry's properties
// based on the current data in the UI.

// Java core packages
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.awt.*;

// Java extension packages
import javax.imageio.ImageIO;
import javax.swing.*;

public class Delete_Specific extends JInternalFrame {

    // HashMap to store JTextField references for quick access
    private AddressBookDataAccess database;
    private HashMap fields;
    private JButton deleteaddress,deleteaddress2, deleteaddress3, deleteemailaddress ,county, city,deletePhone;
    private JButton Updateddress,Updateaddress2, Updateaddress3, Updateemailaddress ,Updatecounty, Updatecity,UpdatePhone;
    private JButton AddAddress,AddAddress2, AddAddress3, Addemailaddress ,Addcounty, Addcity,AddPhone;

    // current AddressBookEntry set by AddressBook application
    private AddressBookEntry person;


    // panels to organize GUI
    private JPanel leftPanel, rightPanel, deletePanel;

    // static integers used to determine new window positions
    // for cascading windows
    private static int xOffset = 0, yOffset = 0;

    // static Strings that represent name of each text field.
    // These are placed on JLabels and used as keys in
    // HashMap fields.
    private static final String  ADDRESS1 = "Address 1",
            UPDATEA1 = "Update address1", UPDATEA2 = "Update address2",UPDATEA3 = "Update address3",UPDATEE1 = "Update email",
            UPDATEP1 = "Update phone",UPDATEC1 = "Update city",UPDATECO1 = "Update County",
            ADDRESS2 = "Address 2", CITY = "City", COUNTY = "County",
            ADDRESS3 = "Address 3 ", PHONE = "Phone", EMAIL = "Email",
            ADDRESS1ID ="addressID" ,ADDRESS2ID = "Address2ID", ADDRESS3ID ="Address3ID",
            EMAILADDRESSID="emailAdressID", CITYID = "cityID", COUNTYID = "CountyID",
            PHONEID= "PhoneID";

    // construct GUI
    public Delete_Specific(AddressBookDataAccess database) throws IOException {
        super( "Address Book Entry", true, true );
        this.database = database;
        BufferedImage buttonIcon = ImageIO.read(new File("D:\\AdressBook\\Address_book_v2\\src\\images\\Delete24.png"));

        deleteaddress = new JButton(new ImageIcon(buttonIcon));
        deleteaddress2 = new JButton(new ImageIcon(buttonIcon));
        deleteemailaddress  = new JButton(new ImageIcon(buttonIcon));
        deleteaddress3    = new JButton(new ImageIcon(buttonIcon));
        deleteaddress3  = new JButton(new ImageIcon(buttonIcon));
        deletePhone  = new JButton(new ImageIcon(buttonIcon));
        county  = new JButton(new ImageIcon(buttonIcon));
        city  = new JButton(new ImageIcon(buttonIcon));

        Updateddress = new JButton("Update");
        Updateaddress2 = new JButton("Update");
        Updateemailaddress  = new JButton("Update");
        Updateaddress3  = new JButton("Update");
        UpdatePhone  = new JButton("Update");
        Updatecity  = new JButton("Update");
        Updatecounty  = new JButton("Update");

        //AddAddress = new JButton("+");
      //  AddAddress2 = new JButton("+");
      //  Addemailaddress  = new JButton("+");
      //  AddAddress3  = new JButton("+");
      //  AddPhone  = new JButton("+");
      //  Addcounty  = new JButton("+");
      //  Addcity  = new JButton("+");

        fields = new HashMap();

        leftPanel = new JPanel();
        leftPanel.setLayout( new GridLayout( 8, 2, 0, 1 ) );
        rightPanel = new JPanel();
        rightPanel.setLayout( new GridLayout( 8, 3, 0, 1 ) );
        deletePanel = new JPanel();
        deletePanel.setLayout( new GridLayout (8, 2, 0, 1) );

        createRow( ADDRESS1 );
        createRow(UPDATEA1);
        createRow(ADDRESS1ID);
        createRow( ADDRESS2 );
        createRow(UPDATEA2);
        createRow(ADDRESS2ID);
        createRow( CITY );
        createRow(UPDATEC1);
        createRow(CITYID);
        createRow( COUNTY );
        createRow(UPDATECO1);
        createRow(COUNTYID);
        createRow( ADDRESS3 );
        createRow(UPDATEA3);
        createRow(ADDRESS3ID);
        createRow( PHONE );
        createRow(UPDATEP1);
        createRow(PHONEID);
        createRow( EMAIL );
        createRow(UPDATEE1);
        createRow(EMAILADDRESSID);

        Container container = getContentPane();
        container.add( leftPanel, BorderLayout.WEST );
        container.add( rightPanel, BorderLayout.CENTER );
        container.add( deletePanel, BorderLayout.EAST);

        deletePanel.add(deleteaddress);
        deletePanel.add(Updateddress);
        deletePanel.add(AddAddress);

        deletePanel.add(deleteaddress2);
        deletePanel.add(Updateaddress2);
        deletePanel.add(AddAddress2);

        deletePanel.add(deleteemailaddress);
        deletePanel.add(Updateemailaddress);
        deletePanel.add(Addemailaddress);

        deletePanel.add(deleteaddress3);
        deletePanel.add(Updateaddress3);
        deletePanel.add(AddAddress3);

        deletePanel.add(city);
        deletePanel.add(Updatecity);
        deletePanel.add(Addcity);

        deletePanel.add(deletePhone);
        deletePanel.add(UpdatePhone);
        deletePanel.add(AddPhone);

        deletePanel.add(county);
        deletePanel.add(Updatecounty);
        deletePanel.add(Addcounty);

        setBounds( xOffset, yOffset, 800, 300 );
        xOffset = ( xOffset + 30 ) % 300;
        yOffset = ( yOffset + 30 ) % 300;

        deleteaddress.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String address = getField(ADDRESS1);
                String ID = getField(ADDRESS1ID);


                if (address!= null )
                Delete_Specific.this.database.deleteAddress(address,ID);
}});
        deleteaddress2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String address = getField(ADDRESS2);
                String ID = getField(ADDRESS2ID);
                if (address!= null )
                    Delete_Specific.this.database.deleteAddress2(address,ID);
//
            }
        });
        deleteemailaddress.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String address = getField(EMAIL);
                String ID = getField(EMAILADDRESSID);
                if (address!= null )
                    Delete_Specific.this.database.deleteemailAddress(address,ID);
//
            }
        });
        deleteaddress3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String address = getField(ADDRESS3);
                String ID = getField(EMAILADDRESSID);
                if (address!= null )
                    Delete_Specific.this.database.deletespecificaddress3(address,ID);
//
            }
        });
        deletePhone.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String address = getField(PHONE);
                String ID = getField(PHONEID);
                if (address!= null )
                    Delete_Specific.this.database.phone(address,ID);
}
        });
        county.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String address = getField(COUNTY);
                String ID = getField(COUNTYID);
                if (address!= null )
                    Delete_Specific.this.database.county(address,ID);
            }
        });
        city.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String address = getField(CITY);
                String ID = getField(CITYID);
                if (address!= null )
                    Delete_Specific.this.database.city(address,ID);
            }
        });

        Updateddress.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String updateaddress = getField(UPDATEA1);
                String address = getField(ADDRESS1);
                String ID = getField(ADDRESS1ID);

                if (address!= null )
                    Delete_Specific.this.database.UpdateAddress(address,updateaddress,ID);
            }});
        Updateaddress2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String updateaddress2 = getField(UPDATEA2);
                String address = getField(ADDRESS2);
                String ID = getField(ADDRESS2ID);

                if (address!= null )
                    Delete_Specific.this.database.UpdateAddress2(address,updateaddress2,ID);
}
        });
        Updateemailaddress.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String address = getField(EMAIL);
                String email = getField(UPDATEE1);
                String ID = getField(EMAILADDRESSID);
                if (address!= null )
                    Delete_Specific.this.database.UpdateemailAddress(address,email,ID);
//
            }
        });
        Updateaddress3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String address = getField(ADDRESS3);
                String address3 = getField(UPDATEA3);
                String ID = getField(ADDRESS3ID);
                if (address!= null )
                    Delete_Specific.this.database.Updatespecificaddress3(address,address3,ID);
//
            }
        });
        UpdatePhone.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String address = getField(PHONE);
                String phone = getField(UPDATEP1);
                String ID = getField(PHONEID);
                if (address!= null )
                    Delete_Specific.this.database.Updatephone(address,phone,ID);
            }
        });
        Updatecounty.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String address = getField(COUNTY);
                String county = getField(UPDATECO1);
                String ID = getField(COUNTYID);
                if (address!= null )
                    Delete_Specific.this.database.Updatecounty(address,county,ID);
            }
        });
        Updatecity.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String address = getField(CITY);
                String city = getField(UPDATEC1);
                String ID = getField(CITYID);
                if (address!= null )
                    Delete_Specific.this.database.Updatecity(address,city,ID);
            }
        });

        AddAddress.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id=0;
                String address = getField(ADDRESS1);

                if (address!= null )
                    Delete_Specific.this.database.AddAddress(address);
            }});
        AddAddress2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String address = getField(ADDRESS2);
                if (address!= null )
                    Delete_Specific.this.database.AddAddress2(address);
            }
        });
        Addemailaddress.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String address = getField(EMAIL);

                if (address!= null )
                    Delete_Specific.this.database.AddemailAddress(address);
//
            }
        });
        AddAddress3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String address = getField(ADDRESS3);

                if (address!= null )
                    Delete_Specific.this.database.Addspecificaddress3(address);
//
            }
        });
        AddPhone.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String address = getField(PHONE);
                if (address!= null )
                    Delete_Specific.this.database.Addphone(address);
            }
        });
        Addcounty.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String address = getField(COUNTY);

                if (address!= null )
                    Delete_Specific.this.database.Addcounty(address);
            }
        });
        Addcity.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String address = getField(CITY);
                if (address!= null )
                    Delete_Specific.this.database.Addcity(address);
            }
        });



    }

    // set AddressBookEntry then use its properties to
    // place data in each JTextField
    public void setAddressBookEntry( AddressBookEntry entry )
    {
        person = entry;

        setField( ADDRESS1, person.getAddress1() );
        setField( ADDRESS2, person.getAddress2() );
        setField( CITY, person.getCity() );
        setField( COUNTY, person.getState() );
        setField( ADDRESS3, person.getAddress3() );
        setField( PHONE, person.getPhoneNumber() );
        setField( EMAIL, person.getEmailAddress() );
    }

    // store AddressBookEntry data from GUI and return
    // AddressBookEntry
    public AddressBookEntry getAddressBookEntry()
    {
        person.setAddress1( getField( ADDRESS1 ) );
        person.setAddress2( getField( ADDRESS2 ) );
        person.setCity( getField( CITY ) );
        person.setState( getField( COUNTY ) );
        person.setAddress3( getField( ADDRESS3 ) );
        person.setPhoneNumber( getField( PHONE ) );
        person.setEmailAddress( getField( EMAIL ) );
        return person;
    }

    // set text in JTextField by specifying field's
    // name and value
    private void setField( String fieldName, String value )
    {
        JTextField field =
                ( JTextField ) fields.get( fieldName );

        field.setText( value );
    }

    // get text in JTextField by specifying field's name
    private String getField( String fieldName )
    {
        JTextField field =
                ( JTextField ) fields.get( fieldName );

        return field.getText();
    }

    // utility method used by constructor to create one row in
    // GUI containing JLabel and JTextField
    private void createRow( String name )
    {
        JButton deletename = new JButton();
        JLabel label = new JLabel( name, SwingConstants.RIGHT );
       label.setBorder(
                BorderFactory.createEmptyBorder( 5, 5, 5, 5 ) );
       // leftPanel.add( label );
        JTextField field = new JTextField( 20 );
        rightPanel.add(label);
        rightPanel.add( field );

        fields.put( name, field );
    }
}  // end class AddressBookEntryFrame


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
