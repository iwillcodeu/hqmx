package com.betwin.contract.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.parity.Parity;
import org.web3j.tx.Contract;

import com.betwin.contract.autogenerated.FootballResultGambel;
import com.betwin.util.Web3jConstants;
import com.betwin.util.Web3jUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FootballGambelServiceTest {

	@Autowired
	private Parity web3j;



	@Value("${contract.platform:}")
	private String platform;
	
	@Value("${privatekey.platform:}")
	private String privateKeyplatform;
	
	@Value("${contract.orgnizor:}")
	private String orgnizor;
	
	@Value("${privatekey.orgnizor:}")
	private String privateKeyorgnizor;
	
	
	@Value("${contract.playerwin:}")
	private String playerwin;
	
	@Value("${privatekey.playerwin:}")
	private String privateKeyplayerwin;
	
	
	@Value("${contract.playerwin2:}")
	private String playerwin2;
	
	@Value("${privatekey.playerwin2:}")
	private String privateKeyplayerwin2;
	
	
	
	@Value("${contract.playerlose:}")
	private String playerlose;
	
	@Value("${privatekey.playerlose:}")
	private String privateKeyplayerlose;
	
	@Value("${contract.playerdraw:}")
	private String playerdraw;
	
	@Value("${privatekey.playerdraw:}")
	private String privateKeyplayerdraw;
	
	
	
	protected Contract contract;
	
	

	@Test
	public void testContract() throws Exception {
		long winamount = 10000000L;
		long winamount2 = 5000000L;
		long loseamount = 10000000L;
		long drawamount = 5000000L;
		List<BigInteger> resultList = new ArrayList<BigInteger>();
		BigInteger win = new BigInteger("1");
		BigInteger lose = new BigInteger("2");
		BigInteger draw = new BigInteger("3");
		System.out.println("win "+win);
		System.out.println("lose "+lose);
		System.out.println("draw "+draw);
		
		resultList.add(win);
		resultList.add(lose);
		resultList.add(draw);
		
		
		for (BigInteger result:resultList) {
			System.out.println(result);
		}
		System.out.println("privateKey="+privateKeyorgnizor);
		Credentials orgCredentials = Credentials.create("0x" + privateKeyorgnizor);
		contract = FootballResultGambel.deploy(web3j, orgCredentials, Web3jConstants.GAS_PRICE, Web3jConstants.GAS_LIMIT_GREETER_TX,resultList).send();
		System.out.println("contract is valid="+contract.isValid());
		
		FootballResultGambel gambel = (FootballResultGambel)contract;
		
		System.out.println(gambel.organizer().sendAsync().get());
		
		
		System.out.println("-----------------before---------------------");
		
		
		//投注
		Credentials winCredentials = Credentials.create("0x" + privateKeyplayerwin);
		FootballResultGambel playerwinContract = FootballResultGambel.load(gambel.getContractAddress(), web3j, winCredentials, Web3jConstants.GAS_PRICE, Web3jConstants.GAS_LIMIT_GREETER_TX);
		playerwinContract.supportResult(win, BigInteger.valueOf(winamount)).send();
		System.out.println("win result amount"+playerwinContract.getAmount("0x"+playerwin).sendAsync().get().longValue());
		
		
		Credentials win2Credentials = Credentials.create("0x" + privateKeyplayerwin2);
		FootballResultGambel playerwin2Contract = FootballResultGambel.load(gambel.getContractAddress(), web3j, win2Credentials, Web3jConstants.GAS_PRICE, Web3jConstants.GAS_LIMIT_GREETER_TX);
		playerwin2Contract.supportResult(win, BigInteger.valueOf(winamount2)).send();
		System.out.println("win2 result amount"+playerwin2Contract.getAmount("0x"+playerwin2).sendAsync().get().longValue());
		
		
		Credentials loseCredentials = Credentials.create("0x" + privateKeyplayerlose);
		FootballResultGambel losewinContract = FootballResultGambel.load(gambel.getContractAddress(), web3j, loseCredentials, Web3jConstants.GAS_PRICE, Web3jConstants.GAS_LIMIT_GREETER_TX);
		losewinContract.supportResult(lose, BigInteger.valueOf(loseamount)).send();
		System.out.println("lose result amount"+losewinContract.getAmount("0x"+playerlose).sendAsync().get().longValue());
		
		
		Credentials drawCredentials = Credentials.create("0x" + privateKeyplayerdraw);
		FootballResultGambel playerDrawContract = FootballResultGambel.load(gambel.getContractAddress(), web3j, drawCredentials, Web3jConstants.GAS_PRICE, Web3jConstants.GAS_LIMIT_GREETER_TX);
		playerDrawContract.supportResult(draw, BigInteger.valueOf(drawamount)).send();
		
		
		System.out.println("draw result amount"+playerDrawContract.getAmount("0x"+playerdraw).sendAsync().get().longValue());
		
		System.out.println("-----------------resultAmount---------------------");
		
		System.out.println(gambel.resultAmount(win).send());
		System.out.println(gambel.resultAmount(lose).send());
		System.out.println(gambel.resultAmount(draw).send());

		System.out.println("-----------------after---------------------");
		//计算权重
		gambel.caculateWeight().send();
		System.out.println("-----------------supporterWeight---------------------");
		
		System.out.println(gambel.supporterWeight("0x"+playerwin).send());
		System.out.println(gambel.supporterWeight("0x"+playerwin2).send());
		System.out.println(gambel.supporterWeight("0x"+playerlose).send());
		System.out.println(gambel.supporterWeight("0x"+playerdraw).send());
		
		
		gambel.addResult(win).send();
		System.out.println("win result amount"+playerwinContract.getAmount("0x"+playerwin).sendAsync().get().longValue());
		System.out.println("win2 result amount"+playerwinContract.getAmount("0x"+playerwin2).sendAsync().get().longValue());
		System.out.println("lose result amount"+losewinContract.getAmount("0x"+playerlose).sendAsync().get().longValue());
		System.out.println("draw result amount"+playerDrawContract.getAmount("0x"+playerdraw).sendAsync().get().longValue());
		
		
		
//		RawTransactionManager transactionManager=new RawTransactionManager(web3j, winCredentials);
//		transactionManager.sendTransaction(Web3jConstants.GAS_PRICE, Web3jConstants.GAS_LIMIT_GREETER_TX, gambel.getContractAddress(), gambel.supportResult(win, BigInteger.valueOf(winamount)).sendAsync().get()., BigInteger.valueOf(winamount));	
		
		
		
//		TransactionReceipt transactionReceipt=gambel.supportResult(win, BigInteger.valueOf(winamount)).send();
//		transactionReceipt.setFrom("0x"+playerwin);
//		transactionReceipt.setTo(gambel.getContractAddress());
//		transactionReceipt.
		
		
		
		
//		System.out.println("--------------------------------------");
//		System.out.println(gambel.sender().send());
//		gambel.supportResult(lose, BigInteger.valueOf(loseamount)).send().setFrom("0x"+playerlose);
//		gambel.supportResult(draw, BigInteger.valueOf(drawamount)).send().setFrom("0x"+playerdraw);
//		 System.out.println("----------------------------------");
////        Tuple3<String, BigInteger, BigInteger> tuple = ;
////        System.out.println(gambel.supporters("0x"+playerwin).sendAsync().get());
////        System.out.println(tuple.getValue2());
////        System.out.println(tuple.getValue3());
//		
//		System.out.println("win result amount"+gambel.getAmount("0x"+playerwin).sendAsync().get().longValue());
//		System.out.println("lose result amount"+gambel.getAmount("0x"+playerlose).sendAsync().get().longValue());
//		System.out.println("draw result amount"+gambel.getAmount("0x"+playerdraw).sendAsync().get().longValue());
		
		System.out.println("---------------before-----------------------");
		
		System.out.println(Web3jUtils.getBalanceEther(web3j, "0x"+playerwin));
		System.out.println(Web3jUtils.getBalanceWei(web3j, "0x"+playerwin2));
		System.out.println(Web3jUtils.getBalanceWei(web3j, "0x"+playerlose));
		System.out.println(Web3jUtils.getBalanceWei(web3j, "0x"+playerdraw));
		System.out.println("Contract address balance (before funding): " + Web3jUtils.weiToEther(Web3jUtils.getBalanceWei(web3j, gambel.getContractAddress())));
		
		System.out.println("---------------sendfunds-----------------------");
		//投注
		Credentials winCredentials2 = Credentials.create("0x" + privateKeyplayerwin);
		FootballResultGambel playerwinContract2 = FootballResultGambel.load(gambel.getContractAddress(), web3j, winCredentials2, Web3jConstants.GAS_PRICE, Web3jConstants.GAS_LIMIT_GREETER_TX);
		playerwinContract2.supportResult(win, BigInteger.valueOf(winamount)).send();
		System.out.println("win result amount"+playerwinContract2.getAmount("0x"+playerwin).sendAsync().get().longValue());
		sendFunds(playerwinContract2,0.05);
		
		
		System.out.println("---------------after-----------------------");
		System.out.println(Web3jUtils.getBalanceEther(web3j, "0x"+playerwin));
		System.out.println(Web3jUtils.getBalanceWei(web3j, "0x"+playerwin2));
		System.out.println(Web3jUtils.getBalanceWei(web3j, "0x"+playerlose));
		System.out.println(Web3jUtils.getBalanceWei(web3j, "0x"+playerdraw));
		System.out.println("Contract address balance (before funding): " + Web3jUtils.weiToEther(Web3jUtils.getBalanceWei(web3j, gambel.getContractAddress())));
		
		
		
	}

	

	private void sendFunds(FootballResultGambel contract,double amount) throws Exception {
		System.out.println("// Send 0.05 Ethers to contract");
		
		// trasfer ether to contract account
		String contractAddress = contract.getContractAddress();
		BigDecimal amountEther = BigDecimal.valueOf(amount);
		BigInteger amountWei = Web3jUtils.etherToWei(amountEther);
		System.out.println("// Send 111 Ethers to contract");
		Web3jUtils.transferFromCoinbaseAndWait(web3j, contractAddress, amountWei);			

		// check current # of deposits and balance
		BigInteger deposits = contract.deposit().send();
				

		System.out.println("Contract address balance (after funding): " + Web3jUtils.weiToEther(Web3jUtils.getBalanceWei(web3j, contractAddress)));
		System.out.println("Contract.deposits(): " + deposits.intValue() + "\n");
	}
}
