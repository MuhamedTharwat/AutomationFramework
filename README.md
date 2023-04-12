💥Automation Framework by : https://www.linkedin.com/in/muhamed-tharwat-fcis/


📝 The main Framework include :
-Selenium Webdriver
-Maven
-TestNG

🏗️ Project Design:
-Page Object Model (POM) design pattern
-Data Driven approach "reading test data from excel file"

💥The Framework can be applied on 2 maib Websites with the following scenarios :


1-Amazon:  
Automate E2E Scenario on "https://www.amazon.eg/" Website

-Login with user data attached in the AmazonTestData excel file
-open “All” menu from the left side
-click on “video games” then choose “all video games”
-from the filter menu on the left side add filter “free shipping” & add the filter of condition “ new”
-in the right side open the sort menu then sort by price: high to low
-add all products below that its cost below 15k EGP, if no product below 15k EGP move to next page
-make sure that all products is already added to carts

2-Jumia:  
-Automate E2E Scenario on "https://www.jumia.com.eg/" Website
-Register with new user and Login using the newly created account
-Add two items to cart
-Verify that the subtotal amount is calculated correctly according to the added item prices

note: add the website URL u want to run the framework on in "config.properties" file from configurations/config.properties