package POMpatternTests;

import org.testng.annotations.DataProvider;

//This class is used as data provider that invoked from test methods add it
public class TestData {

    //This data is used to provide login credentials using Data provider, data provider create 2 D array
    // containing all data at once,testing framework deal with the first array and test the assertion,
    // then moves to next array
    @DataProvider(name="loginValidData")
    public Object [][]loginValidData(){
        return new Object[][]
                {
                        {"standard_user","secret_sauce"},
                        {"problem_user","secret_sauce"},
                        {"performance_glitch_user","secret_sauce"},
                        {"error_user","secret_sauce"},
                        {"visual_user","secret_sauce"},
                };
    }

    //This data is used to provide invalid login data
    @DataProvider(name ="loginInvalidData")
    public Object [][] inValidLogIn(){
        return new Object[][]{
                {"swag","secret_sauce"},
                {"standard_user","ret_sauce"},
                {"","Secret_sauce"},
                {"locked_out_user","secret_sauce"},
        };
    }

    /*
    This data is used to provide 'Checkout Step One' page with one missing field each time,
    Missing 'First Name', 'Last Name', and 'Zip Code' respectively
    Also, it provides expected error message for each missing field
    */
    @DataProvider(name="CheckOutStepOneMissingField")
    public Object[][]CheckOutStepOneMissingField(){
        return new Object[][]{
                {"","Shalaby","03","Error: First Name is required"},
                {"Abdelrahman","","03","Error: Last Name is required"},
                {"Abdelrahman","Shalaby","","Error: Postal Code is required"}, //Suggested edit
        };
    }

    //This data is used to provide data contains numbers and special characters to all fields
    @DataProvider(name="numbersAndSpecialCharacter")
    public Object [][] usingNumbersAndSpecialCharacter(){
        return new Object [][]{
                {"Abdelrahman12@3","Shalaby@123","adg@123"},
        };

    }
}
