package com.library.steps;

import com.library.utility.BrowserUtil;
import com.library.utility.DB_Util;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;

public class UserStepDefs {

    String actualCount;
    List<String> expectedList;

    @Given("Establish the database connection")
    public void establish_the_database_connection() {

        //make a connection with library
        //DB_Util.createConnection();
        System.out.println("-------CONNECTION WILL BE DONE BY BEFORE HOOK---------------");

    }

    @When("Execute query to get all IDs from users")
    public void execute_query_to_get_all_ı_ds_from_users() {
        String query = "select count(id) from users";
        DB_Util.runQuery(query);

        actualCount = DB_Util.getFirstRowFirstColumn();
        System.out.println("actualCount = " + actualCount);

    }

    @Then("verify all users has unique ID")
    public void verify_all_users_has_unique_ıd() {

        String query = "select count( distinct id) from users";
        DB_Util.runQuery(query);
        String expectedCount = DB_Util.getFirstRowFirstColumn();

        Assert.assertEquals(expectedCount, actualCount);



    }



    @When("Execute query to get all columns")
    public void execute_query_to_get_all_columns() {

        String query="select * from users";
        DB_Util.runQuery(query);

       expectedList = DB_Util.getAllColumnNamesAsList();
        System.out.println(expectedList);


    }
    @Then("verify the below columns are listed in result")
    public void verify_the_below_columns_are_listed_in_result(List<String> actualList) {

        System.out.println(actualList);

        Assert.assertEquals(expectedList,actualList);










        //Close connection

        // DB_Util.destroy();


        System.out.println("-------CONNECTION WILL BE DONE BY BEFORE HOOK---------------");


    }






}
