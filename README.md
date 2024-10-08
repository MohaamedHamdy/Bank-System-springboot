# Bank-System-springboot
Complete Bank management system in spring boot using Custom Exception handling, Lombok, JPA, Hibernate and connected to PSQL.

# EndPoints
## Branch
1- Create Branch => localhost:8080/branches **POST**  

{ "branchName" : "name", "branchAddress" : "Address", "branchCode" : "code"}  

2- Get branch details by id => localhost:8080/branches/1 **GET**  

3- Get all users in specific branch => localhost:8080/branches/allUsers/1 **GET**  

## User
1- Create User => localhost:8080/users  

{ "userName": "Mohamed Hamdy", "address": "123 Main St", "phoneNumber": "123-456-7890", "email": "john.doe@example.com", "branchId": 1}  

2- Get user details => localhost:8080/users/9  

## Account 
1- Create account => localhost:8080/accounts **POST**  

{ "accountNumber": "1234", "balance": 200.0, "accountType": "debit", "userId" : 3}  

2- Get account details => localhost:8080/accounts/1 **GET**  

3- DEPOSIT or WITHDRAW money = localhost:8080/accounts/update **PUT**  

{ "accountId: 1, "amount": 100.0, "type": "deposit"}  

## Transactions
1- Create transaction => localhost:8080/transactions **POST**  

{ "transactionType": "TRANSFER", "amount": 100.0, "sourceAccountId": 1, "targetAccountId": 2}  

2- Get all transactions for specific account => localhost:8080/transactions/1 **GET**  
