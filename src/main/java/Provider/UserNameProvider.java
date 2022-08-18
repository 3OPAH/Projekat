package Provider;

import org.testng.annotations.DataProvider;

public class UserNameProvider {

    @DataProvider(name = "UserNameProvider")
    public static Object[][] getDataFromDataProvider(){
        return new Object[][] {
                { "standard_user", "secret_sauce"},
                { "problem_user", "secret_sauce"},
                { "performance_glitch_user", "secret_sauce"}
        };
    }

    @DataProvider(name = "InValidUserNameProvider")
    public static Object[][] getCredentialsFromDataProvider(){
        return new Object[][] {
                { "user_name", "password"},
                { "admin", "admin" },
                { "super_user", "123456"}
        };
    }

}
