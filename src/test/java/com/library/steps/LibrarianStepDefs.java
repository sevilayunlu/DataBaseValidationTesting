package com.library.steps;

import com.library.pages.DashBoardPage;
import com.library.pages.LoginPage;
import com.library.utility.BrowserUtil;
import com.library.utility.DB_Util;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class LibrarianStepDefs {
    LoginPage loginPage = new LoginPage();
    DashBoardPage dashBoardPage = new DashBoardPage();
    String actualBorrowedBooksNumber;

    @Given("the {string} on the home page")
    public void the_on_the_home_page(String librarian) {
        loginPage.login("librarian");


    }

    @When("the librarian gets borrowed books number")
    public void the_librarian_gets_borrowed_books_number() {

        //option1--webElement

        BrowserUtil.waitFor(2);
        actualBorrowedBooksNumber = dashBoardPage.borrowedBooksNumber.getText();

        //System.out.println("actualBorrowedBooksNumber = " + actualBorrowedBooksNumber);

        //option2-- method
        System.out.println("dashBoardPage.getModuleCount(\"Borrowed Books\") = " + dashBoardPage.getModuleCount("Borrowed Books"));


    }

    @Then("borrowed books number information must match with DB")
    public void borrowed_books_number_information_must_match_with_db() {
        String query = "select count(*) from book_borrow\n" +
                "where is_returned=0";
        DB_Util.runQuery(query);
        BrowserUtil.waitFor(2);
        String expectedBorrowedBooksNumber = DB_Util.getFirstRowFirstColumn();

        //System.out.println(expectedBorrowedBooksNumber);
        Assert.assertEquals(expectedBorrowedBooksNumber, actualBorrowedBooksNumber);


    }


}
