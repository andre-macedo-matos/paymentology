# Repository to The project challenger from Paymentology

**The Project**

- The concept behind the project is to perform a financial reconciliation between two different sets of data
  - Refere from the examples [files](https://drive.google.com/file/d/1c3bGFJgJfbUaWuPdTdYhoKJkYAp7bMys/view?usp=sharing) to test
- Compare the two files, and report on how many transactions match perfectly, versus transactions which cannot be matched
- And those transactions which cannot be matched will need to be reported on, so that a third party could refer to the report and investigate the exceptions
- Note that this is **_not_** a file comparison project, this is a transaction matching/reconciliation project. 
  - In other words, the project should do its best to identify for users all non-perfectly matched records possible matches based on a 
    reconciliation strategy you come up with , it might be you consider only the most important fields, or consider all fields but at different 
    level of importance...Defining the important fields is up to you.
- If a transaction cannot be matched perfectly, you should attempt to look for any close matches and suggest them as possibilities
- You do not need to store the results, or provide any further functionality once you have listed the exceptions (and potential matches where possible)
- Project is deploy on [Heroku](https://andremacedo-transactioncompare.herokuapp.com/)
