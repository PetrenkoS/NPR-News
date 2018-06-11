package NPR_News;

import org.junit.Test;

public class AppTests extends TestHelper {

    @Test
    public void testPrograms() {

        //Go to the main page, ignore alerts, press news -> programs and check that you are on the program page.

        ignoreAlerts();
        pressNewsHeaderButton();
        chooseProgramsOnHeader();
        checkProgramsOnHeader();
    }


    @Test
    public void testSearch() {
        // Go to search page, type in search field, click on magnifier button and check that you are on the search results page.

        goToSearchPage();
        typeInSearchField();
        pressMagnifierButton();
        checkSearchResultsPage();

    }


}
