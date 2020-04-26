package application;

import bank.BankAgency;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class ApplicationContextBankAgency {

    private static ApplicationContextBankAgency instance;

    /**
     * Attributes of the context
     */
    private BankAgency bankAgency;
    private Scanner scanner;
    private PrintWriter printWriter;

    private ApplicationContextBankAgency(){
        printWriter = new PrintWriter(System.out);
        scanner = new Scanner(System.in);
        bankAgency = AccesBankAgency.getBankAgency();
    }

    public static ApplicationContextBankAgency getInstance(){
        if(instance == null)
            instance = new ApplicationContextBankAgency();
        return instance;
    }

    public Scanner getScanner(){
        return scanner;
    }

    public PrintWriter getPrintWriter(){
        return printWriter;
    }

    public BankAgency getBankAgency(){
        return bankAgency;
    }

    public void setInputStream(InputStream stream){
        scanner.close();
        scanner = new Scanner(stream);
    }

    /**
     * Free the resources during finishing the context usage
     */
    public void close(){
        scanner.close();
        printWriter.close();
    }
}