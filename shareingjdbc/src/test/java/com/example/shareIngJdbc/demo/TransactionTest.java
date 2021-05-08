package com.example.shareIngJdbc.demo;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

/**
 * @author :xie
 * Email: 1487471733@qq.com
 * Date: 2018/10/5
 * Time: 11:18
 * Describe:
 */
public class TransactionTest {


    /**
     *使用普通方式排序
     */
    @Test
    public void test1(){

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction(1, 10, Transaction.Type.GEOCERY));
        transactions.add(new Transaction(3, 30, Transaction.Type.GEOCERY));
        transactions.add(new Transaction(6, 60, Transaction.Type.GEOCERY));
        transactions.add(new Transaction(5, 50, Transaction.Type.GEOCERY));
        transactions.add(new Transaction(2, 20, Transaction.Type.A));
        transactions.add(new Transaction(4, 40, Transaction.Type.C));

        // JDK 7 发现type为grocery的所有交易
        List<Transaction> groceryTransactions = new ArrayList<>();
        for (Transaction t : transactions) {
            if (t.getType() == Transaction.Type.GEOCERY) {
                groceryTransactions.add(t);
            }
        }
        // 集合排序 交易值降序排序
        Collections.sort(groceryTransactions, new Comparator<Transaction>() {
            @Override
            public int compare(Transaction o1, Transaction o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });
        // 交易ID 获取
        List<Integer> transactionIds = new ArrayList<>();
        for (Transaction t : groceryTransactions) {
            transactionIds.add(t.getId());
        }

        System.out.println(transactionIds);//[6, 5, 3, 1]



    }

    /**
     * 使用steam 方式进行筛选和排序
     */
    @Test
    public  void test2(){
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction(1, 10, Transaction.Type.GEOCERY));
        transactions.add(new Transaction(3, 30, Transaction.Type.GEOCERY));
        transactions.add(new Transaction(16, 60, Transaction.Type.GEOCERY));
        transactions.add(new Transaction(5, 50, Transaction.Type.GEOCERY));
        transactions.add(new Transaction(2, 20, Transaction.Type.A));
        transactions.add(new Transaction(4, 40, Transaction.Type.C));

        List<Integer> transactionsIds =
                transactions.parallelStream().filter(t -> t.getType() == Transaction.Type.GEOCERY)
                        .sorted(Comparator.comparing(Transaction::getValue).reversed())
                        .map(Transaction::getId)
                        .collect(Collectors.toList());
        System.out.println(transactionsIds);//[6, 5, 3, 1]

        List<Integer> integers=transactions.stream().filter(t->t.getType()==Transaction.Type.GEOCERY)
                .sorted(Comparator.comparing(Transaction::getValue))
                //.limit(1)
                .map(Transaction::getId)
                .collect(Collectors.toList());
        System.out.println(integers);



    }

}