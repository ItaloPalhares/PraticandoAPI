package br.com.alura.screenmatch.exceptions;

public class YearSizeException extends RuntimeException{

	private String msg;
	
	public YearSizeException(String msg) {
		this.msg = msg;
	}
	
	@Override
	public String getMessage() {
		
		return this.msg;
	}
}
