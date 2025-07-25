//***************************************************************
//
//  Developer:    Tiffany Pham
//
//  Program #:    Three
//
//  File Name:    HouseHolds.java
//
//  Course:       COSC 4301 Modern Programming
//
//  Instructor:   Prof. Fred Kumi 
//
//  Description:  HouseHold class. Represents household record:
//                ID number, annual income, number of household 
//                members, and state. Used to read and process said
//                data.
//
//***************************************************************

   //***************************************************************
   //
   //  Class:        Households
   // 
   //  Description:  Represents household data
   //
   //  Parameters:   N/A
   //
   //  Returns:      Household data. 
   //
   //**************************************************************

public class HouseHolds {
    
    private final int idNum;
    private double annualIncome;
    private int householdMembers;
    private final String state;

   //***************************************************************
   //
   //  Constructor:  Households
   // 
   //  Description:  Retrieves household data and sets up validation.
   //
   //  Parameters:   int idNum, double annualIncome, int householdMembers, 
   //                String state
   //
   //  Returns:      N/A 
   //
   //**************************************************************
    //constructor 
    public HouseHolds(int idNum, double annualIncome, int householdMembers, String state)
    {
        if (householdMembers < 0) {
            throw new IllegalArgumentException("Household members must be > 0");
        }

        if ( annualIncome < 0.0) {
            throw new IllegalArgumentException("Annual income must be > 0");
        }

        this.idNum = idNum;
        this.annualIncome = annualIncome;
        this.householdMembers = householdMembers;
        this.state = state;
    }
   //***************************************************************
   //
   //  Method:       getIdNum()
   // 
   //  Description:  Gets and returns ID Number 
   //
   //  Parameters:   N/A
   //
   //  Returns:      ID Number
   //
   //**************************************************************
    //get idNum
    public int getIdNum()
    {
        return idNum;
    }

   //***************************************************************
   //
   //  Method:       setAnnualIncome()
   // 
   //  Description:  sets and returns Annual Income, validates as well. 
   //
   //  Parameters:   double annualIncome
   //
   //  Returns:      Annual income or error. 
   //
   //**************************************************************

    //get annualIncome
    public void setAnnualIncome(double annualIncome)
    {
        if ( annualIncome < 0.0) {
            throw new IllegalArgumentException("Annual income must be > 0");
    }

    this.annualIncome = annualIncome;

    }
   //***************************************************************
   //
   //  Method:       getAnnualIncome()
   // 
   //  Description:  Gets and returns Annual Income.
   //
   //  Parameters:   N/A
   //
   //  Returns:      Annual income.
   //
   //**************************************************************
    public double getAnnualIncome()
    {
        return annualIncome;
    }

   //***************************************************************
   //
   //  Method:       setHouseholdMembers()
   // 
   //  Description:  Gets and returns number of household members,
   //                and validates as well.
   //
   //  Parameters:   int householdMembers
   //
   //  Returns:      Number of household members or error 
   //
   //**************************************************************

    //get householdMembers
    public void setHouseholdMembers(int householdMembers)
    {
        if (householdMembers < 0) {
            throw new IllegalArgumentException("Household members must be > 0");
    }

        this.householdMembers = householdMembers;
    }

   //***************************************************************
   //
   //  Method:       getHouseholdMembers()
   // 
   //  Description:  Gets and returns number of household members.
   //
   //  Parameters:   N/A
   //
   //  Returns:      Number of household members. 
   //
   //**************************************************************
    public int getHouseholdMembers()
    {
        return householdMembers;
    }
    
   //***************************************************************
   //
   //  Method:       getState()
   // 
   //  Description:  Gets and returns state. 
   //
   //  Parameters:   N/A
   //
   //  Returns:      State
   //
   //**************************************************************
    //get state
    public String getState()
    {
        return state;
    }

   //***************************************************************
   //
   //  Method:       getStringAnnualIncome()
   // 
   //  Description:  Formats Annual income  
   //
   //  Parameters:   N/A
   //
   //  Returns:      Formatted annual income 
   //
   //**************************************************************

    private String getStringAnnualIncome()
    {
        return String.format("$%,.2f", getAnnualIncome());
    }

    //return string representation of households object

   //***************************************************************
   //
   //  Method:       getString()
   // 
   //  Description:  Prints data with correct formatting 
   //
   //  Parameters:   N/A
   //
   //  Returns:      Household data
   //
   //**************************************************************
    @Override
    public String toString()
    {
        return String.format("ID Number: %-4 Annual Income: $%12s Household Members %-2 State: %-21s",
            getIdNum(), getAnnualIncome(), getHouseholdMembers(), getState());
    }
}