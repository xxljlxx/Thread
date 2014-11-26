package com.Thread.test1;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.Thread.State;

/**
 * 创建线程
 * @author lee
 *
 */
public class Main {
	public static void main(String[] args) {
		//循环创建线程
		/*for (int i = 0; i <= 10; i++) {
			Calculator calculator = new Calculator(i);
			Thread thread = new Thread(calculator);
			thread.start();
		}*/
		//数组创建线程
		Thread threads[] = new Thread[10];
		Thread.State status[]= new Thread.State[10];
		for (int i = 0; i < 10; i++) {
			threads[i] = new Thread(new Calculator(i));
			if ((i%2 == 0)) {
				threads[i].setPriority(Thread.MAX_PRIORITY);
			} else {
				threads[i].setPriority(Thread.MIN_PRIORITY);
			}
			threads[i].setName("Thread" + i);
		}
		
		try {
			 FileWriter file = new FileWriter(".\\src\\com\\Thread\\test1\\log.txt");  
	            PrintWriter pw = new PrintWriter(file);  
	            for(int i =0;i<10;i++){  
	                pw.println("main: status of thread "+i+": "+threads[i].getState());  
	                status[i] = threads[i].getState();  
	            }  
	              
	            for(int i =0;i<10;i++){  
	                threads[i].start();  
	            }  
	            boolean finish = false;  
	            while(!finish){  
	                for (int i = 0; i < 10; i++) {  
	                    if(threads[i].getState()!=status[i]){  
	                        writeThreadInfo(pw,threads[i],status[i]);  
	                    }  
	                }  
	                finish = true;  
	            }  
	            pw.close(); 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
	}

	private static void writeThreadInfo(PrintWriter pw, Thread thread,
			State state) {
		pw.printf("Main : Id %d - %s \n", thread.getId(),thread.getName());
		pw.printf("Main : Priority %d\n", thread.getPriority());
		pw.printf("Main : Old state %s \n", state);
		pw.printf("Main : New state %s \n", thread.getState());
		pw.printf("Main :===============================================\n");
		
	}
}
