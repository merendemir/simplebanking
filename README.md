[Simple Bank Account Assignment](https://gitlab.eteration.com/academy/assignments/simplebanking#bonus-task-1-find-a-better-implementation-alternative)

# Account & Transaction Documentation

## Account

The [`Account`](src/main/java/com/eteration/simplebanking/model/Account.java) class contains the following fields:
- `accountNumber`: Holds the account number.
- `owner`: Holds the account owner's name.
- `balance`: Holds the current balance of the account.
- `createDate`: The date when the account was created (automatically generated).
- `transactions`: Keeps track of the transactions associated with the account.

### Methods

- **credit()**: Adds the provided amount to the recipient account's (`Account`) balance.
- **debit()**: Subtracts the provided amount from the recipient account's (`Account`) balance.
- **post()**: Calls the `apply()` method of the given `Transaction` object to execute the relevant transaction, and then adds the transaction details to the account.

**Example Usage:**

```java
public void credit(double amount) {
    this.balance += amount;
}
```
```java
public void debit(double amount) throws InsufficientBalanceException {
    if (this.balance < amount) {
        throw new InsufficientBalanceException();
    }

    this.balance -= amount;
}
```
```java
public void post(Transaction transaction) throws Exception {
    transaction.apply(this);
    transactions.add(transaction);
}
```

## Transaction

The Transaction class is an abstract class, meaning it provides the structure for all specific types of transactions such as deposits, withdrawals, or payments. <br /> 

Each transaction type must implement the apply() method, which handles how the transaction affects the account.

The [`Transaction`](src/main/java/com/eteration/simplebanking/model/transaction/Transaction.java) class includes the following fields:
- `date`: The date of the transaction (automatically generated).
- `amount`: The amount involved in the transaction.
- `type`: The type of transaction (e.g., Deposit, Withdrawal).
- `approvalCode`: The transaction's approval code (automatically generated after the apply() method is called).


## Polymorphism for Transaction Execution
The apply() method is used in each new Transaction subclass to define the specific logic for that type of transaction. <br />

This ensures that the system is extensible new transaction types can be added without modifying the existing code. <br />

For example, when a new transaction type such as DepositTransaction is introduced, it only needs to implement its own apply() method. <br />


```java
@Override
public void apply(Account account) {
    account.credit(getAmount());
    generateApprovalCode();  // Approval code is generated here after the transaction is applied
}
```

By leveraging polymorphism, adding new transaction types does not require changes to the Account class or the need to overload methods. <br />

The post() method in the Account class will work with any type of transaction as long as it implements the apply() method.

You can find other transaction implementations [`here`](src/main/java/com/eteration/simplebanking/model/transaction/impl)

## Tests
- `For Task 1:` You can find the test scenarios in [`Task1Tests.java`](src/test/java/com/eteration/simplebanking/Task1Tests.java) <br />
- `Task 1: Better Implementation Alternative:` The `post()` method within the [`Account.java`](src/main/java/com/eteration/simplebanking/model/Account.java) allows you to execute transactions by applying the specified `Transaction` object to the account.
- `For Task 2:` The written endpoints can be found in the [`AccountRestController.java`](src/main/java/com/eteration/simplebanking/controller/AccountRestController.java).The tests for this task can be found in [`Task2Tests.java`](src/test/java/com/eteration/simplebanking/Task2Tests.java).

