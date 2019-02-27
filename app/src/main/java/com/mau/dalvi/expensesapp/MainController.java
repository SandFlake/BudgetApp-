package com.mau.dalvi.expensesapp;


import android.database.sqlite.SQLiteConstraintException;
import android.util.Log;
import android.widget.Toast;

import java.util.List;


public class MainController {

    private MainActivity activity;
    private ExpensesFragment fragExpenses;
    private IncomeFragment fragIncome;
    private StartFragment fragStart;
    private LoginFragment fragLogin;
    private DBAccess dbAccess;
    private TotalFragment fragTotal;
    private List<IncomeTable> incomeList;
    private List<ExpensesTable> expenseList;
    private ChooseListDateFragment fragChooseList;
    private IncomeListFragment fragInListView;
    private ExpenseListFragment fragExListView;


    public MainController(MainActivity activity) {
        this.activity = activity;

        fragExpenses = new ExpensesFragment();
        fragStart = new StartFragment();
        fragIncome = new IncomeFragment();
        fragLogin = new LoginFragment();
        dbAccess = new DBAccess(activity);
        fragTotal = new TotalFragment();
        fragChooseList = new ChooseListDateFragment();
        fragInListView = new IncomeListFragment();
        fragExListView = new ExpenseListFragment();
        fragLogin.setController(this);
        fragStart.setMainController(this);
        activity.setFragment(fragLogin, false);
    }

    public void btnLoginClicked() {
        fragStart.setMainController(this);
        activity.setFragment(fragStart, true);
    }

    public void btnAddIncomeClicked() {
        fragIncome.setController(this);
        activity.setFragment(fragIncome, true);
    }

    public void btnAddExpenseClicked() {
        fragExpenses.setController(this);
        activity.setFragment(fragExpenses, true);
    }

    public void btnOverviewClicked() {
        fragTotal.setController(this);
        activity.setFragment(fragTotal, true);
    }

    public void btnUpdateClicked() {
        getAllIncome();
    }

    public void btnChooseListClicked() {
        fragChooseList.setController(this);
        activity.setFragment(fragChooseList, true);
    }

    public void btnIncomeListClicked() {
        fragInListView.setController(this);
        getInkomstForList();
        activity.setFragment(fragInListView, true);
    }

    public void btnExpenseListClicked() {
        fragExListView.setController(this);
        getExpensesForList();
        activity.setFragment(fragExListView, true);
    }

    public void btnBackClicked() {
        fragStart.setMainController(this);
        activity.setFragment(fragStart, true);
    }


    public void addIncome(final String title, final String amount, final String category, final String date) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    double MyAmount = Double.parseDouble(amount);
                    dbAccess.insertIncomeTable(new IncomeTable(title, MyAmount, category, date));
                    Log.d("tagss", "melons" + title + " " + MyAmount + " " + category + " " + date);
                } catch (NumberFormatException e) {
                } catch (SQLiteConstraintException e) {
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(activity, "Income NOT added. You already have that title", Toast.LENGTH_LONG).show();
                        }
                    });
                }
                activity.setFragment(fragStart, true);

            }
        }).start();
    }



    public void addExpense(final String title, final String price, final String category, final String date) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    double MyPrice = Double.parseDouble(price);
                    dbAccess.insertExpensesTable(new ExpensesTable(title, MyPrice, category, date));
                } catch (NumberFormatException e) {
                } catch (SQLiteConstraintException e) {
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(activity, "Expense NOT added. You already have that title", Toast.LENGTH_LONG).show();
                        }
                    });

                }

                activity.setFragment(fragStart, true);
            }
        }).start();
    }

    public void getAllIncome() {
        new Thread(new Runnable() {

            @Override
            public void run() {

                String fromDate = fragTotal.getFromDate();
                String toDate = fragTotal.getToDate();

                if (!fromDate.isEmpty() && !toDate.isEmpty()) {
                    incomeList = dbAccess.getIncomeForDate(fromDate, toDate);
                    expenseList = dbAccess.getExpensesForDate(fromDate, toDate);

                } else {
                    incomeList = dbAccess.fetchAllIncome();
                    expenseList = dbAccess.fetchAllExpenses();
                }

                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        fragTotal.setIncomeView(sumOfIncome(incomeList));
                        fragTotal.setExpenseView(sumOfExpenses(expenseList));
                        fragTotal.setAccountTotal(sumOfDifferences(incomeList, expenseList));
                    }
                });
            }
        }).start();
    }

    public double sumOfDifferences(List<IncomeTable> incomeList, List<ExpensesTable> expenseList) {
        return sumOfIncome(incomeList) + sumOfExpenses(expenseList);
    }

    public double sumOfIncome(List<IncomeTable> list) {
        double res = 0;

        for (IncomeTable incomeTable : list) {
            res += incomeTable.getAmount();
        }
        return res;
    }

    public double sumOfExpenses(List<ExpensesTable> list2) {
        double res = 0;

        for (ExpensesTable expensesTable : list2) {
            res -= expensesTable.getEx_price();
        }

        return res;
    }


    public void getInkomstForList() {
        new Thread(new Runnable() {

            @Override
            public void run() {
                Log.d("Tag", "getting ma income listesss");

                String fromDate = fragChooseList.getFromDate();
                String toDate = fragChooseList.getToDate();
                List<IncomeTable> incomeList;

                if (!fromDate.isEmpty() && !toDate.isEmpty()) {
                    incomeList = dbAccess.getIncomeForDate(fromDate, toDate);
                } else {
                    incomeList = dbAccess.fetchAllIncome();
                }

                fragInListView.setInkomstList(incomeList);

            }
        }).start();
    }

    public void getExpensesForList() {
        new Thread(new Runnable() {

            @Override
            public void run() {
                Log.d("Tag", "gettting ma expenses strawberries" + expenseList);
                String fromDate = fragChooseList.getFromDate();
                String toDate = fragChooseList.getToDate();

                List<ExpensesTable> expenseList;

                if (!fromDate.isEmpty() && !toDate.isEmpty()) {
                    expenseList = dbAccess.getExpensesForDate(fromDate, toDate);
                } else {
                    expenseList = dbAccess.fetchAllExpenses();
                }

                fragExListView.setExpenseList(expenseList);

            }
        }).start();
    }


}
