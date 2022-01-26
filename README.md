# ex_pr_asign

#Steps to start the application.
1. Install jdk - 17
2. Install maven
3. run mvn clean install
4. set environment variables - 
   port=port_number; db_name=database_name; username=database_username; password=database_password

#example
    {
    port=3306;
    db_name=expense_tracker;
    username=root;
    password=root
    }


# Entitiy
##1. User - 

    @Primary Key
    private Long userId; - auto generated.

    @Column(nullable = false)
    private String firstName; - (example - aditya)

    @Column(nullable = false)
    private String lastName; - (example - chhajer)

    @Column(nullable = false, unique = true)
    private String email; - (example - aditya@gmail.com)

    @Size(min = 8,max = 500)
    @Column(nullable = false) - (example - aditya#1234) - this will be decrypted.
    private String password;

##2. Transactions -

    @Primary Key
    private Long transactionId; - auto generated.

    @Column(nullable = false)
    private Long userId; - 

    @Column(nullable = false)
    private TransactionType transactionType; (examples - CREDIT, DEBIT)

    @Column(nullable = false)
    private ModeOfPayment modeOfPayment; - (examples - CASH, CARD, ONLINE, UPI)

    @Column(nullable = false)
    private Date transactionDate; - (example - 2022-01-26)

    @Column(nullable = false)
    private Categories categories; - (examples - FOOD, SHOPPING, BIILS, TRANSPORT, GIFTS, MEDICALEXPENSES, ENTERTAINMENT,
    HOUSEHOLDS, INCOME, OTHER, RENT, ELECTRONICS)

    @Column(nullable = false)
    private String description; - (example - Expense for paying food bills.)

    @Column(nullable = false)
    private Double amount; - (example - 124)

    @Column(nullable = false)
    private String currencyType; - (example - INR, USD etc)

##3. UserDues -

    @Primary Key
    private Long dueId; - auto generated.

    @Column(nullable = false)
    private Long userId; - id of user.

    @Column(nullable = false)
    private Long oweId; - id of owe user.

    @Column(nullable = false)
    private Double amount; - how much amount has to pay.

    @Column(nullable = false)
    private Date repaymentDate; (example - 2022-01-26)

    @Column(name = "isPaid", columnDefinition = "boolean default false")
    private boolean isPaid; - default false


#Api's

##1. To add a new user
Method - POST

url - http://localhost:8080/user/

RequestBody -

    {
    "firstName" : "Aditya",
    "lastName" : "Chhajer",
    "email" : "aditya@gmail.com",
    "password" : "adityachhajer"
    }
Response

    status - 200
    body - Success

    or

    status - 400
    body - User with same email aditya@gmail.com already present


    


##2. To add a new transaction
Method - POST

url - http://localhost:8080/expenses/

RequestBody -

    {
    "emailId" : "aditya@gmail.com",
    "transactionType" : "CREDIT",
    "modeOfPayment" : "CASH",
    "transactionDate" : "2022-01-26",
    "categories" : "INCOME",
    "description" : "xyz",
    "amount" : 145,
    "currencyType" : "INR"
    }

Response

    status - 200
    body - Success

    or

    status - 400
    body - Sorry we are unable to process this request, Please try again later.


##3. To add a new due for user
Method - POST

url - http://localhost:8080/userdues/

RequestBody -

        {
        "userEmailId" : "aditya@gmail.com",
        "oweEmailId" : "mohit@gmail.com",
        "amount" : 2456,
        "repaymentDate" : "2022-03-26"
        }

Response

    status - 200
    body - Success

    or

    status - 400
    body - Sorry we are unable to process this request, Please try again later.


##4. To settle a existing due for user by first in first out fashion
Method - POST

url - http://localhost:8080/userdues/1

RequestBody -

        {
         "userEmailId" : "aditya@gmail.com",
         "amount" : 123456
        }

Response

    status - 200
    body - Success


##5. To settle a existing due for user by latest repayment date first
Method - POST

url - http://localhost:8080/userdues/2

RequestBody -

        {
         "userEmailId" : "aditya@gmail.com",
         "amount" : 123456
        }

Response

    status - 200
    body - Success
