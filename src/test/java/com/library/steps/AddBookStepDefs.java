package com.library.steps;

import com.library.pages.BookPage;
import com.library.utility.BrowserUtil;
import com.library.utility.DB_Util;
import com.library.utility.Driver;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.support.ui.Select;

import java.util.Map;

public class AddBookStepDefs extends BookPage {

    String name;
    String isbnNum;
    String yearInfo;
    String authorInfo;


    @When("the librarian click to add book")
    public void the_librarian_click_to_add_book() {

        addBook.click();

    }

    @When("the librarian enter book name {string}")
    public void the_librarian_enter_book_name(String name) {

        bookName.sendKeys(name);
    }

    @When("the librarian enter ISBN {string}")
    public void the_librarian_enter_ısbn(String isbnNum) {

        isbn.sendKeys(isbnNum);

    }

    @When("the librarian enter year {string}")
    public void the_librarian_enter_year(String yearInfo) {

        year.sendKeys(yearInfo);
    }

    @When("the librarian enter author {string}")
    public void the_librarian_enter_author(String authorInfo) {

        author.sendKeys(authorInfo);
    }

    @When("the librarian choose the book category {string}")
    public void the_librarian_choose_the_book_category(String category) {
        BrowserUtil.selectOptionDropdown(categoryDropdown,category);
        BrowserUtil.waitFor(2);
    }

    @When("the librarian click to save changes")
    public void the_librarian_click_to_save_changes() {

        saveChanges.click();
    }

    @Then("verify {string} message is displayed")
    public void verify_message_is_displayed(String expectedMessage) {
        BrowserUtil.waitFor(2);

        String actualMessage = toastMessage.getText();
        Assert.assertEquals(expectedMessage,actualMessage);
    }

    @Then("verify {string} information must match with DB")
    public void verify_information_must_match_with_db(String expectedBookName) {

        String query = "select name, author, isbn from books where name = '"+expectedBookName+"'";

        DB_Util.runQuery(query);

        Map<String, String> rowMap = DB_Util.getRowMap(1);

        String actualBookName = rowMap.get("name");

        Assert.assertEquals(expectedBookName,actualBookName);



    }

}
