package com.library.steps;

import com.library.pages.BookPage;
import com.library.pages.BorrowedBooksPage;
import com.library.utility.DB_Util;
import com.library.utility.Driver;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class BorrowBookStepDefs extends BookPage {

    @When("the users search for {string} book")
    public void the_users_search_for_book(String book) {

        search.sendKeys(book);
    }

    @When("the user clicks Borrow Book")
    public void the_user_clicks_borrow_book() {
        Driver.getDriver().findElement(By.xpath("(//a[@onclick='Books.borrow_book(5799)'])[1]")).click();


    }
    @Then("verify that book is shown in {string} page")
    public void verify_that_book_is_shown_in_page(String string) {

        WebElement selfConfidenceBook = Driver.getDriver().findElement(By.xpath("(//table[@id='tbl_books']//tbody//td)[3]"));

        Assert.assertTrue(selfConfidenceBook.isDisplayed());


    }
    @Then("verify logged student has same book in database")
    public void verify_logged_student_has_same_book_in_database() {
        String query="select name from books where name='Self Confidence'";
        DB_Util.runQuery(query);

        String expectedBookName = DB_Util.getFirstRowFirstColumn();
        Assert.assertEquals(expectedBookName,"Self Confidence");
    }



}
