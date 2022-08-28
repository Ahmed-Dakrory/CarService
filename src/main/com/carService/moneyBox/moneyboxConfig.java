package main.com.carService.moneyBox;

import java.util.Calendar;

import main.com.carService.car.car;
import main.com.carService.carLanding.carLanding;
import main.com.carService.loginNeeds.user;
import main.com.carService.loginNeeds.userAppServiceImpl;
import main.com.carService.moneyTransactionDetails.moneybox_transaction_details;
import main.com.carService.moneyTransactionDetails.moneybox_transaction_detailsAppServiceImpl;
import main.com.carService.product.product;

public class moneyboxConfig {

	public static void depositeMoney(float amount
			,user fromMe
			,userAppServiceImpl userFasced,moneyboxAppServiceImpl moneyfascede
			,moneybox_transaction_detailsAppServiceImpl moneyBoxtransactionFasced,String wire_transfer_number
			,car carId) {
		
		moneybox myMoneyBox = moneyfascede.getByUserId(fromMe.getId());
		float totalDeposited = myMoneyBox.getDepositedMoney();
		System.out.println("---@-------------");
		System.out.println(amount);
		System.out.println(totalDeposited);
		totalDeposited = Float.sum(amount, totalDeposited);
		System.out.println(totalDeposited);
		System.out.println("---@-3333----------");
		//Set the new amount
		try {
		myMoneyBox.setDepositedMoney(totalDeposited);
		totalDeposited = myMoneyBox.getDepositedMoney();
		}catch(Exception ex) {

			System.out.println("---@-111----------");
			System.out.println(ex);
		}catch(Error err) {

			System.out.println("---@-113----------");
			System.out.println(err);
		}
		System.out.println("---@-3----------");
		System.out.println(totalDeposited);
		moneyfascede.addmoneybox(myMoneyBox);
		System.out.println("---@-2----------");
		System.out.println(totalDeposited);
		
		//add new transaction
		moneybox_transaction_details transaction=new moneybox_transaction_details();
		transaction.setAmount(amount);
		transaction.setAmountBefore(totalDeposited);
		transaction.setTypeOfTransaction(moneybox_transaction_details.depositeTypes.Deposite.getType());
		transaction.setDate(Calendar.getInstance().getTime());
		transaction.setMoneyBoxId(myMoneyBox);
		transaction.setWire_transfer_number(wire_transfer_number);
		transaction.setCarId(carId);
		
		moneyBoxtransactionFasced.addmoneybox_transaction_details(transaction);
		
	}
	
	
	
	

	public static void depositeMoney(float amount
			,user fromMe
			,userAppServiceImpl userFasced,moneyboxAppServiceImpl moneyfascede
			,moneybox_transaction_detailsAppServiceImpl moneyBoxtransactionFasced,String wire_transfer_number) {
		
		moneybox myMoneyBox = moneyfascede.getByUserId(fromMe.getId());
		float totalDeposited = myMoneyBox.getDepositedMoney();
		totalDeposited = Float.sum(amount, totalDeposited);
		//Set the new amount
		try {
		myMoneyBox.setDepositedMoney(totalDeposited);
		totalDeposited = myMoneyBox.getDepositedMoney();
		}catch(Exception ex) {

			System.out.println("---@-111----------");
			System.out.println(ex);
		}catch(Error err) {

			System.out.println("---@-113----------");
			System.out.println(err);
		}
		System.out.println(totalDeposited);
		moneyfascede.addmoneybox(myMoneyBox);
		//add new transaction
		moneybox_transaction_details transaction=new moneybox_transaction_details();
		transaction.setAmount(amount);
		transaction.setAmountBefore(totalDeposited);
		transaction.setTypeOfTransaction(moneybox_transaction_details.depositeTypes.Deposite.getType());
		transaction.setDate(Calendar.getInstance().getTime());
		transaction.setMoneyBoxId(myMoneyBox);
		transaction.setWire_transfer_number(wire_transfer_number);
		
		moneyBoxtransactionFasced.addmoneybox_transaction_details(transaction);
		
	}
	
	
	public static void makeaPayment(float amount
			,user fromMe
			,userAppServiceImpl userFasced,moneyboxAppServiceImpl moneyfascede
			,moneybox_transaction_detailsAppServiceImpl moneyBoxtransactionFasced
			,String wire_transfer_number
			,car carId) {
		
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
		transaction.setWire_transfer_number(wire_transfer_number);
		transaction.setCarId(carId);
		
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
		transactionMain.setWire_transfer_number(wire_transfer_number);
		transactionMain.setCarId(carId);
				
		moneyBoxtransactionFasced.addmoneybox_transaction_details(transactionMain);
		
	}
	
	
	
	

	public static void makeaPayment(float amount
			,user fromMe
			,userAppServiceImpl userFasced,moneyboxAppServiceImpl moneyfascede
			,moneybox_transaction_detailsAppServiceImpl moneyBoxtransactionFasced
			,String wire_transfer_number
			,carLanding carId) {
		
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
		transaction.setWire_transfer_number(wire_transfer_number);
		transaction.setCarlandingId(carId);
		
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
		transactionMain.setWire_transfer_number(wire_transfer_number);
		transactionMain.setCarlandingId(carId);
				
		moneyBoxtransactionFasced.addmoneybox_transaction_details(transactionMain);
		
	}
	
	
	
	
	

	public static void makeaPayment(float amount
			,user fromMe
			,userAppServiceImpl userFasced,moneyboxAppServiceImpl moneyfascede
			,moneybox_transaction_detailsAppServiceImpl moneyBoxtransactionFasced
			,String wire_transfer_number
			,product productId) {
		
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
		transaction.setWire_transfer_number(wire_transfer_number);
		transaction.setProductId(productId);
		
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
		transactionMain.setWire_transfer_number(wire_transfer_number);
		transactionMain.setProductId(productId);
				
		moneyBoxtransactionFasced.addmoneybox_transaction_details(transactionMain);
		
	}
	
	
}
