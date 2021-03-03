package main.com.carService.moneyBox;

import java.util.Calendar;

import main.com.carService.loginNeeds.user;
import main.com.carService.loginNeeds.userAppServiceImpl;
import main.com.carService.moneyTransactionDetails.moneybox_transaction_details;
import main.com.carService.moneyTransactionDetails.moneybox_transaction_detailsAppServiceImpl;

public class moneyboxConfig {

	public static void depositeMoney(float amount
			,user fromMe
			,userAppServiceImpl userFasced,moneyboxAppServiceImpl moneyfascede
			,moneybox_transaction_detailsAppServiceImpl moneyBoxtransactionFasced) {
		
		moneybox myMoneyBox = moneyfascede.getByUserId(fromMe.getId());
		float totalDeposited = myMoneyBox.getDepositedMoney();
		
		//Set the new amount
		myMoneyBox.setDepositedMoney(totalDeposited+amount);
		moneyfascede.addmoneybox(myMoneyBox);
		
		//add new transaction
		moneybox_transaction_details transaction=new moneybox_transaction_details();
		transaction.setAmount(amount);
		transaction.setAmountBefore(totalDeposited);
		transaction.setTypeOfTransaction(moneybox_transaction_details.depositeTypes.Deposite.getType());
		transaction.setDate(Calendar.getInstance().getTime());
		transaction.setMoneyBoxId(myMoneyBox);
		
		moneyBoxtransactionFasced.addmoneybox_transaction_details(transaction);
		
	}
	
	
	public static void makeaPayment(float amount
			,user fromMe
			,userAppServiceImpl userFasced,moneyboxAppServiceImpl moneyfascede
			,moneybox_transaction_detailsAppServiceImpl moneyBoxtransactionFasced) {
		
		moneybox myMoneyBox = moneyfascede.getByUserId(fromMe.getId());
		float totalDeposited = myMoneyBox.getDepositedMoney();
		float totalSpend = myMoneyBox.getTotalUsed();
		
		//Set the new amount
		myMoneyBox.setDepositedMoney(totalDeposited-amount);
		myMoneyBox.setTotalUsed(totalSpend+amount);
		moneyfascede.addmoneybox(myMoneyBox);
		
		//add new transaction
		moneybox_transaction_details transaction=new moneybox_transaction_details();
		transaction.setAmount(amount);
		transaction.setAmountBefore(totalDeposited);
		transaction.setTypeOfTransaction(moneybox_transaction_details.depositeTypes.Payment.getType());
		transaction.setDate(Calendar.getInstance().getTime());
		transaction.setMoneyBoxId(myMoneyBox);
		
		moneyBoxtransactionFasced.addmoneybox_transaction_details(transaction);
		
		//AddMoneyToTheMainUser
		user theMainAccount = userFasced.getById(1);
		moneybox mainAccountMoneyBox = moneyfascede.getByUserId(theMainAccount.getId());
		float totalDepositedMainAccount = mainAccountMoneyBox.getDepositedMoney();
		mainAccountMoneyBox.setDepositedMoney(totalDepositedMainAccount+amount);
		moneyfascede.addmoneybox(mainAccountMoneyBox);
		
		
		//add new transaction for MainAccount
		moneybox_transaction_details transactionMain=new moneybox_transaction_details();
		transactionMain.setAmount(amount);
		transactionMain.setAmountBefore(totalDepositedMainAccount);
		transactionMain.setTypeOfTransaction(moneybox_transaction_details.depositeTypes.Inserted.getType());
		transactionMain.setDate(Calendar.getInstance().getTime());
		transactionMain.setMoneyBoxId(mainAccountMoneyBox);
				
		moneyBoxtransactionFasced.addmoneybox_transaction_details(transactionMain);
		
	}
	
	
}
