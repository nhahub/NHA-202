package POMpatternTests;

import org.testng.annotations.DataProvider;

public class TestData {
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
    @DataProvider(name ="loginInvalidData")
    public Object [][] inValidLogIn(){
        return new Object[][]{
                {"swag","secret_sauce"},
                {"standard_user","ret_sauce"},
                {"","Secret_sauce"},
                {"locked_out_user","secret_sauce"},
        };
    }
    @DataProvider(name="CheckOutStepOneMissingField")
    public Object[][]CheckOutStepOneMissingField(){
        return new Object[][]{
                {"","Shalaby","03","Error: First Name is required"},
                {"Abdelrahman","","03","Error: Last Name is required"},
                {"Abdelrahman","Shalaby","","Error: Postal Code is required"}, //Suggested edit
        };
    }
    @DataProvider(name="numbersAndSpecialCharacter")
    public Object [][] usingNumbersAndSpecialCharacter(){
        return new Object [][]{
                {"Abdelrahman12@3","Shalaby@123","adg@123"},
        };

    }


}
