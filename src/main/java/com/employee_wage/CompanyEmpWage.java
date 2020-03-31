package com.employee_wage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

    class CompanyEmpWage{
        public final String COMPANY;
        public final int MAX_HRS_IN_MONTH;
        public final int EMP_RATE_PER_HR;
        public final int NUM_WORKING_DAYS;

        public CompanyEmpWage(String COMPANY, int EMP_RATE_PER_HR, int MAX_HRS_IN_MONTH, int NUM_WORKING_DAYS){
            this.COMPANY = COMPANY;
            this.EMP_RATE_PER_HR = EMP_RATE_PER_HR;
            this.MAX_HRS_IN_MONTH = MAX_HRS_IN_MONTH;
            this.NUM_WORKING_DAYS = NUM_WORKING_DAYS;
        }
    }

public class EmployeeWageForMultiCompany {
    public static final int IS_PART_TIME = 1;
    public static final int IS_FULL_TIME = 2;

    private static HashMap<String, Integer> totalWage = new HashMap<String, Integer>();
    private static ArrayList<CompanyEmpWage> companyList = new ArrayList<CompanyEmpWage>();

    private void addCompanyEmpWage(String company,int empRatePerHrs,int maxHrsInMonth,int numOfWorkingDays){
        CompanyEmpWage compan = new CompanyEmpWage(company,empRatePerHrs,maxHrsInMonth,numOfWorkingDays);
        companyList.add(compan);
    }

    private void computeEmpWage(){
        for(CompanyEmpWage company : companyList){
            int totalEmpHrs=0;
            int totalWorkingDays=0;
            HashMap<String, Integer> dailyWage = new HashMap<String, Integer>();
            while(totalEmpHrs<company.MAX_HRS_IN_MONTH && totalWorkingDays<company.NUM_WORKING_DAYS){
                totalWorkingDays++;
                int empHrs=getWorkHrs((int)(Math.random()*3));
                totalEmpHrs=totalEmpHrs+empHrs;
                int dailySalary=empHrs * company.EMP_RATE_PER_HR;
                String day = "day"+String.valueOf(totalWorkingDays);
                dailyWage.put(day,Integer.valueOf(dailySalary));
            }
            int totalSalary=totalEmpHrs*company.EMP_RATE_PER_HR;
            String companyName = company.COMPANY;
            totalWage.put(companyName,Integer.valueOf(totalSalary));
            Set<String> keys = dailyWage.keySet();
            System.out.println("******  Company "+companyName+"  *******\n");
            System.out.println("Working Days  Wage Per Day");
            for(String key : keys){
                System.out.printf("  %-5s       %-3d\n",key,dailyWage.get(key));
            }
        }
    }

    public static int getWorkHrs(int empCheck){
        int empHrs;
        switch(empCheck){
            case  IS_FULL_TIME :  empHrs=8;
                break;

            case  IS_PART_TIME :  empHrs=4;
                break;

            default :  empHrs=0;
                break;
        }
        return empHrs;
    }
    public static void main(String[] args) {
        EmployeeWageForMultiCompany EmpBuilder = new EmployeeWageForMultiCompany();
        System.out.println("1.Add company in list");
        System.out.println("2.Display Daly Wages");
        System.out.println("3.Display total Wage of Company");
        System.out.println("4.Exit");
        Scanner scanner = new Scanner(System.in);
        boolean valid = true;
        while(valid){
            System.out.println("Enter the number Accordingaly above menu");
            int input = scanner.nextInt();
            switch(input){
                case 1 : System.out.println("Enter Company Name");
                    String cName = scanner.next();
                    System.out.println("Enter EmpRatePerHours");
                    int EmpRatePerHrs = scanner.nextInt();
                    System.out.println("Enter MaxHrInMonth");
                    int maxHrInMonth = scanner.nextInt();
                    System.out.println("Enter MaxWorkingDays");
                    int maxWorkDays = scanner.nextInt();
                    EmpBuilder.addCompanyEmpWage(cName,EmpRatePerHrs,maxHrInMonth,maxWorkDays);
                    break;
                case 2 : EmpBuilder.computeEmpWage();
                    break;

                case 3 : System.out.println("Enter Company Name for total Salary");
                    String name = scanner.next();
                    System.out.println("Total Wage : "+totalWage.get(name));
                    break;
                case 4 : System.out.println("You SuccesFully Exit");
                    valid=false;
                    break;
                default : System.out.println("Invalid Input");
            }
        }
        scanner.close();
    }

}
