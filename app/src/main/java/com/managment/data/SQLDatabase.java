package com.managment.data;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Mychelle
 * Referenced http://www.vogella.com/tutorials/AndroidSQLite/article.html some code used from site example
 */
public class SQLDatabase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "BudgetCat";
    private static final String createDatabaseComment = "DataBase to store budget information for users";

    // ---------------------------------- BudgetCat Tables ----------------------------------
    private static final List<String> tableList = Arrays.asList("User", "Budget", "Account", "Category", "UserCategories", "AccountType", "Transaction");

    //--User-- Table Element Names
        private static final String userID = "USER_ID";
        private static final String userName = "USER_NAME";
        private static final String email = "EMAIL";
        private static final String lastLogIn = "LAST_LOG_IN";

    //--Budget-- Table Element Names
        private static final String budgetID = "BUDGET_ID";
        // Foreign ID USER_ID from User Table
        private static final String budgetName = "BUDGET_NAME";
        private static final String budgetFileName = "BUDGET_FILE_NAME";
        private static final String budgetDate = "BUDGET_DATE";

    //--Account-- Table Element Names
        private static final String accountID = "ACCOUNT_ID";
        // Foreign ID USER_ID from User Table
        private static final String accountName = "ACCOUNT_NAME";
        // Foreign ID ACCOUNT_TYPE_ID from accountType table
        private static final String interestAmount = "INTEREST_AMOUNT";
        private static final String balance = "BALANCE";
        private static final String balanceGoal = "BALANCE_GOAL";

    //--Category-- Table Element Names
        private static final String categoryID = "CATEGORY_ID";
        private static final String categoryName = "CATEGORY_NAME";

    //--UserCategories-- Table Element Names
        //ForeignID USER_ID from User table
        //ForeignID CATEGORY_ID from Category table
        // This table is used to connect a many to many relationship from UserTable to CategoryID
        // USER_ID CATEGORY_ID pair must be unique in table UserCategories

    //--AccountType-- Table Element Names
        private static final String accountTypeID = "ACCOUNT_TYPE_ID";
        private static final String accountTypeName = "ACCOUNT_TYPE_NAME";
        private static final String asset = "ASSET";

    //--Transactions-- Table Element Names
        private static final String transactionID = "TRANSACTION_ID";
        //Foreign Key ACCOUNT_ID from account table
        private static final String amount = "AMOUNT";
        private static final String date = "DATE_OF_TRANSACTION";
        private static final String location = "LOCATION";
        private static final String locationName = "LOCATION_NAME";
        private static final String notes = "NOTES";
        //Foreign ID CategoryID from CategoryTable

   // ---------------------------------- End of BudgetCat database tables ----------------------------------

    private static final int DATABASE_VERSION = 1;

    public SQLDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL("");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(SQLDatabase.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + "Budget");
        onCreate(db);
    }

}
