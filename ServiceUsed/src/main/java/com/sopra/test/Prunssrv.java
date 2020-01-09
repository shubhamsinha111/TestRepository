package com.sopra.test;

public class Prunssrv {
	public static void prunsrvStartServer(String[] args) throws Exception {
	    String[] newArgs = new String[1];         
	    newArgs[0] = System.getProperty("8080"); // -Dprunsrv.port=8080         
	    ShubhamService.main(newArgs);
	  }          
	  public static void prunsrvStopServer(String[] args) throws Exception { 
	    String[] newArgs = new String[2];         
	    newArgs[0] = System.getProperty("localhost"); // -Dprunsrv.server=localhost         
	    newArgs[1] = System.getProperty("8080"); // -Dprunsrv.port=8080         
	    newArgs[1] = "shutdown";                  
	    ShubhamService.main(newArgs);
	  }
}
