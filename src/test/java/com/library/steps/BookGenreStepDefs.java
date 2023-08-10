package com.library.steps;

import com.library.utility.DB_Util;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

public class BookGenreStepDefs {

    String expectedResult;

    @When("I execute query to find most popular book genre")
    public void ı_execute_query_to_find_most_popular_book_genre(){

        String query = "select bc.name, count(bc.name)\n" +
                "from books b\n" +
                "         join book_categories bc on bc.id = b.book_category_id\n" +
                "         join book_borrow bb on b.id = bb.book_id\n" +
                "group by bc.name\n" +
                "order by count(bc.name) desc;";
        DB_Util.runQuery(query);
        Map<String, String> firstRow = DB_Util.getRowMap(1);
        expectedResult = firstRow.get("name");

    }

    @Then("verify {string} is the most popular book genre.")
    public void verify_is_the_most_popular_book_genre(String actualResult) {

        Assert.assertEquals(expectedResult, actualResult);
    }


}
