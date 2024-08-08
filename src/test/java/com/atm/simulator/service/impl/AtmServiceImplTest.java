package com.atm.simulator.service.impl;

import com.atm.simulator.account.service.AccountService;
import com.atm.simulator.account.service.impl.AccountServiceImpl;
import com.atm.simulator.entity.AccountEntity;
import com.atm.simulator.model.AccountModel;
import com.atm.simulator.model.TransferSumaryModel;
import com.atm.simulator.model.WithdrawSummaryModel;
import com.atm.simulator.service.AtmService;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class AtmServiceImplTest {
    public AtmService init() {
        AccountEntity accountModel1 = new AccountEntity("112233", 100.0, "John Doe", "012108");
        AccountEntity accountModel2 = new AccountEntity("112244", 30.0, "Jane Doe", "932012");

        Map<String, AccountEntity> accountModelMap = new HashMap<>();
        accountModelMap.put(accountModel1.getAccountNumber(), accountModel1);
        accountModelMap.put(accountModel2.getAccountNumber(), accountModel2);

        return new AtmServiceImpl(new AccountServiceImpl(accountModelMap));
    }

    @Test
    void validatAccountTest() {
        AtmService atmService = init();
        AccountEntity account = atmService.validatAccount("112233","012108");
        assertEquals("John Doe", account.getName());
        assertEquals("112233", account.getAccountNumber());
    }

    @Test
    void validatAccountFail1Test() {
        AtmService atmService = init();
        AccountEntity account = atmService.validatAccount("11223","012108");
        assertNull(account);
    }

    @Test
    void validatAccountFail2Test() {
        AtmService atmService = init();
        AccountEntity account = atmService.validatAccount("112233","012118");
        assertNull(account);
    }

    @Test
    void validatAccountFail3Test() {
        AtmService atmService = init();
        AccountEntity account = atmService.validatAccount("112233","01210");
        assertNull(account);
    }

    @Test
    void validatAccountFail4Test() {
        AtmService atmService = init();
        AccountEntity account = atmService.validatAccount("11223a","012108");
        assertNull(account);
    }

    @Test
    void validateBalanceTrue() {
        AtmService atmService = init();
        atmService.validatAccount("112233","012108");
        Boolean bool = atmService.validateBalance(20);
        assertTrue(bool);
    }

    @Test
    void validateBalanceFalse() {
        AtmService atmService = init();
        atmService.validatAccount("112233","012108");
        Boolean bool = atmService.validateBalance(200);
        assertFalse(bool);
    }

    @Test
    void checkBalanceTest() {
        AtmService atmService = init();
        atmService.validatAccount("112233","012108");
        AccountModel account = atmService.checkBalance();
        assertEquals("John Doe", account.getName());
        assertEquals("112233", account.getAccountNumber());
    }

    @Test
    void withdrawTest() {
        AtmService atmService = init();
        atmService.validatAccount("112233","012108");
        WithdrawSummaryModel summaryModel = atmService.withdraw(20.0);
        assertEquals(20, summaryModel.getWithdrawAmount());
        assertEquals(80, summaryModel.getBalance());
    }

    @Test
    void withdrawFail1Test() {
        AtmService atmService = init();
        atmService.validatAccount("112233","012108");
        WithdrawSummaryModel summaryModel = atmService.withdraw(260.0);
        assertNull(summaryModel);
    }

    @Test
    void withdrawOtherTest() {
        AtmService atmService = init();
        atmService.validatAccount("112233","012108");
        WithdrawSummaryModel summaryModel = atmService.withdrawOther(30.0);
        assertEquals(30, summaryModel.getWithdrawAmount());
        assertEquals(70, summaryModel.getBalance());
    }

    @Test
    void withdrawOtherFail1Test() {
        AtmService atmService = init();
        atmService.validatAccount("112233","012108");
        WithdrawSummaryModel summaryModel = atmService.withdrawOther(32.0);
        assertNull(summaryModel);
    }

    @Test
    void withdrawOtherFail2Test() {
        AtmService atmService = init();
        atmService.validatAccount("112233","012108");
        WithdrawSummaryModel summaryModel = atmService.withdrawOther(132.0);
        assertNull(summaryModel);
    }

    @Test
    void withdrawOtherFail3Test() {
        AtmService atmService = init();
        atmService.validatAccount("112233","012108");
        WithdrawSummaryModel summaryModel = atmService.withdrawOther(1320.0);
        assertNull(summaryModel);
    }

    @Test
    void inqTransfer1Test() {
        AtmService atmService = init();
        atmService.validatAccount("112233","012108");
        TransferSumaryModel transferSumaryModel = atmService.inqTransfer("20", "112244", "123456");
        assertEquals(20, transferSumaryModel.getTransferAmount());
        assertEquals("123456", transferSumaryModel.getReffNumber());
        assertEquals(100, transferSumaryModel.getBalance());
    }

    @Test
    void inqTransfer2Test() {
        AtmService atmService = init();
        atmService.validatAccount("112233","012108");
        TransferSumaryModel transferSumaryModel = atmService.inqTransfer("20", "112244", "");
        assertEquals(20, transferSumaryModel.getTransferAmount());
        assertEquals(6, transferSumaryModel.getReffNumber().length());
        assertEquals(100, transferSumaryModel.getBalance());
    }

    @Test
    void inqTransferFail1Test() {
        AtmService atmService = init();
        atmService.validatAccount("112233","012108");
        TransferSumaryModel transferSumaryModel = atmService.inqTransfer("20", "11224a", "123456");
        assertNull(transferSumaryModel);
    }

    @Test
    void inqTransferFail2Test() {
        AtmService atmService = init();
        atmService.validatAccount("112233","012108");
        TransferSumaryModel transferSumaryModel = atmService.inqTransfer("2a", "112244", "123456");
        assertNull(transferSumaryModel);
    }

    @Test
    void inqTransferFail3Test() {
        AtmService atmService = init();
        atmService.validatAccount("112233","012108");
        TransferSumaryModel transferSumaryModel = atmService.inqTransfer("20", "112246", "123456");
        assertNull(transferSumaryModel);
    }

    @Test
    void inqTransferFail4Test() {
        AtmService atmService = init();
        atmService.validatAccount("112233","012108");
        TransferSumaryModel transferSumaryModel = atmService.inqTransfer("200", "112244", "123456");
        assertNull(transferSumaryModel);
    }

    @Test
    void inqTransferFail5Test() {
        AtmService atmService = init();
        atmService.validatAccount("112233","012108");
        TransferSumaryModel transferSumaryModel = atmService.inqTransfer("1200", "112244", "123456");
        assertNull(transferSumaryModel);
    }

    @Test
    void inqTransferFail6Test() {
        AtmService atmService = init();
        atmService.validatAccount("112233","012108");
        TransferSumaryModel transferSumaryModel = atmService.inqTransfer("0", "112244", "123456");
        assertNull(transferSumaryModel);
    }

    @Test
    void inqTransferFail7Test() {
        AtmService atmService = init();
        atmService.validatAccount("112233","012108");
        TransferSumaryModel transferSumaryModel = atmService.inqTransfer("20", "112244", "12345a");
        assertNull(transferSumaryModel);
    }

    @Test
    void transferFundTest() {
        AtmService atmService = init();
        atmService.validatAccount("112233","012108");
        TransferSumaryModel transferSumaryModel = atmService.transferFund(20,"112244","123123");
        assertEquals(20, transferSumaryModel.getTransferAmount());
        assertEquals(80, transferSumaryModel.getBalance());
    }

    @Test
    void transferFundFail1Test() {
        AtmService atmService = init();
        atmService.validatAccount("112233","012108");
        TransferSumaryModel transferSumaryModel = atmService.transferFund(120,"112244","123123");
        assertNull(transferSumaryModel);
    }
}