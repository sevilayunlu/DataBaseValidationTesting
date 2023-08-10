package com.library.steps;

import com.library.pages.BookPage;
import com.library.utility.BrowserUtil;
import com.library.utility.DB_Util;
import com.library.utility.Driver;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Map;

public class BookInfoStepDefs extends BookPage {

    @When("the user searches for {string} book")
    public void the_user_searches_for_book(String book) {

        search.sendKeys(book);
    }

    @When("the user clicks edit book button")
    public void the_user_clicks_edit_book_button() {


        editBook("Jenkins With Grid").click();


    }

    @Then("book information must match the Database")
    public void book_information_must_match_the_database() {
        String query = "select * from books where name ='Jenkins With Grid'";
        DB_Util.runQuery(query);

        BrowserUtil.waitFor(2);

        //From DB -expected
        Map<String, String> rowMap = DB_Util.getRowMap(1);

        String expectedBookName = rowMap.get("name");
        String expectedIsbn = rowMap.get("isbn");
        String expectedYear = rowMap.get("year");
        String expectedAuthor = rowMap.get("author");
        String expectedDescription = rowMap.get("description");
        String expectedBookCategory = rowMap.get("book_category_id");

        //From UI -actual
        String actualBookName = bookName.getAttribute("value");
        String actualIsbn = isbn.getAttribute("value");
        String actualYear = year.getAttribute("value");
        String actualAuthor = author.getAttribute("value");
        String actualDescription = description.getAttribute("value");
        BrowserUtil.waitFor(2);

        Select select=new Select(categoryDropdown);
        select.selectByValue("18");

        BrowserUtil.waitFor(2);
        String actualBookCategory = categoryDropdown.getAttribute("value");

        Assert.assertEquals(expectedBookName, actualBookName);
        Assert.assertEquals(expectedIsbn, actualIsbn);
        Assert.assertEquals(expectedYear, actualYear);
        Assert.assertEquals(expectedAuthor, actualAuthor);
        Assert.assertEquals(expectedDescription, actualDescription);
        Assert.assertEquals(expectedBookCategory,actualBookCategory);


    }

}
