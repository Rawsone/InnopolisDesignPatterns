package application;

import action.ActionContext;
import bank.BankAgency;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class BankActionContext implements ActionContext {
    private static BankActionContext instance;
    private BankAgency bankAgency;
    private Scanner scanner;
    private PrintStream printStream;

    private BankActionContext(){
        this.printStream = new PrintStream(System.out);
        this.scanner = new Scanner(System.in);
        this.bankAgency = AccessBankAgency.getBankAgency();
    }

    public static BankActionContext getInstance() {
        if(instance == null)
            instance = new BankActionContext();
        return instance;
    }

    public Scanner getScanner(){
        return scanner;
    }

    public PrintStream getPrintStream(){
        return printStream;
    }

    public BankAgency getBankAgency(){
        return bankAgency;
    }

    public void setInputStream(InputStream stream){
        scanner.close();
        scanner = new Scanner(stream);
    }

    public void close(){
        scanner.close();
        printStream.close();
    }
}