package com.library.steps;

import com.library.pages.BasePage;
import com.library.pages.BookPage;
import com.library.pages.DashBoardPage;
import com.library.utility.BrowserUtil;
import com.library.utility.DB_Util;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class CategoriesStepDefs extends BookPage {

    List<String> actualCategories;


    @When("the user navigates to {string} page")
    public void the_user_navigates_to_page(String books) {
        navigateModule(books);
        BrowserUtil.waitFor(2);
    }

    @When("the user clicks book categories")
    public void the_user_clicks_book_categories() {

        actualCategories = BrowserUtil.getAllSelectOptions(mainCategoryElement);
        actualCategories.remove(0);
        //System.out.println("actualCategories = " + actualCategories);

    }

    @Then("verify book categories must match book_categories table from db")
    public void verify_book_categories_must_match_book_categories_table_from_db() {
        String query = "select name from book_categories";

        DB_Util.runQuery(query);

        BrowserUtil.waitFor(2);

        List<String> expectedCategories = DB_Util.getColumnDataAsList(1);

        BrowserUtil.waitFor(2);

        //System.out.println("expectedCategories = " + expectedCategories);

        Assert.assertEquals(expectedCategories, actualCategories);

    }

}
